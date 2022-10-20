/* 갤러리 목록 */
function galleryListAjax( galleryListReturn ) {
	var galleryList = []; //  에디터에서 갤러리 선택시 사용

	$.ajax({
		url : '/gallerys',
		dataType : 'json',
		async: false,
		success : function( response ) {
			galleryList = response.gallerys;
		}
	});

	if ( galleryListReturn ) return galleryList;
}


/* 갤러리 정보( name, desc 설정 ) */
function galleryInfoAjax( module_id, gallery_id ) {
	var moduleSelctor = targetSelector( module_id );
	$.ajax({
		url : '/gallerys/' + gallery_id,
		dataType : 'json',
		async: true,
		success : function( response ) {
			$( moduleSelctor ).find('.galleryTitle').text( response.gallery.gallery_name );
			$( moduleSelctor ).find('.galleryDesc').text( response.gallery.gallery_desc );
		}
	});
}



/* 갤러리 렌더링 */
var galleryOptions;
function moduleGalleryRender( module_id, gallery_id, add_option ) {
	var moduleID, moduleSelector, $gallerySelector;
	if ( !module_id ) { // Loading
		if ( !$('[data-content-type1="gallery"]').length ) return false;

		$('[data-content-type1="gallery"]').each(function() {
			moduleID = $(this).parent('[data-module-id]').length ? $(this).parent('[data-module-id]').data('module-id') : $(this).parent('[data-minimodule-id]').data('minimodule-id');
			moduleSelector = targetSelector( moduleID );
			$gallerySelector = $( moduleSelector ).children('[data-content-type1="gallery"]');

			$( moduleSelector ).find( '.gallery_ul' ).empty();
			$( moduleSelector ).find( '.gallery_paging_1, gallery_paging_3' ).removeAttr( 'style' );

			galleryAjax( moduleID, 1 );
		});
	} else { // 최초 삽입 & Editing
		moduleID = module_id;
		moduleSelector = targetSelector( moduleID );
		$gallerySelector = $( moduleSelector ).children('[data-content-type1="gallery"]');

		if ( gallery_id ) {
			$gallerySelector.data( 'gallery-id', gallery_id ).attr( 'data-gallery-id', gallery_id );
		}

		if ( add_option == 'galleryAjax' ) {
			galleryAjax( moduleID, 1 ); // 데이터 연동
		}
	}

	$gallerySelector.data( 'gallery-currentpage', 1 ).attr( 'data-gallery-currentpage', 1 );
	galleryLastScrollTop = 0;
}


/* 갤러리 Ajax */
function galleryAjax( module_id, page, is_paging_click, gallery_id, gallery_perpage ) {
	var moduleSelector = targetSelector( module_id );
	var $gallerySelector = $( moduleSelector ).children( '[data-content-type1="gallery"]' );

	var galleryID = gallery_id ? gallery_id : $gallerySelector.data('gallery-id');
	var galleryPerPage = gallery_perpage ? gallery_perpage : $gallerySelector.data('gallery-perpage');
	var galleryPagingType = $gallerySelector.data('gallery-pagingtype');

	if ( page == 1 ) {
		$gallerySelector.data( 'gallery-currentpage', 1 ).attr( 'data-gallery-currentpage', 1 );
	}

	$.ajax({
		url : '/gallerys/'+ galleryID + '/items?perpage=' + galleryPerPage + '&page=' + page,
		dataType : 'json',
		async: true,
		success : function( response ) {
			console.log(response);
			if ( response.error == 'not_found' ) {
				// 갤러리 자체가 없는 경우
				var $noGalleryHtml = '<div class="skin_user_text">연결된 갤러리가 없습니다.</div><div class="no_board_text">연결된 갤러리가 없습니다.<br />갤러리를 연결하시려면 아래 버튼을 클릭해주세요.<br /><br /></div><div class="no_board_button"><button data-select-type="gallery" class="btnBoardSelect btn_resp btn_board_select">갤러리 선택</button></div>';
				$( moduleSelector ).find( '.no_data_area' ).html( $noGalleryHtml ).show();
				$( moduleSelector ).find( '.gallery_ul' ).hide();
			} else {
				//$( moduleSelector + ' .gallery_paging').removeAttr('style');

				if ( response.page.total == 0 ) { // 데이터가 없는 경우
					$( moduleSelector + ' .gallery_ul').hide();
					$( moduleSelector + ' .gallery_paging').hide();
					$( moduleSelector + ' .no_data_area').show();
				} else {
					$( moduleSelector + ' .gallery_ul').show();
					$( moduleSelector + ' .no_data_area').hide();

					var galleryType = response.sns_connect ? response.sns_connect : '';
					var galleryGon = '';
					$.each( response.items, function( key ) {
						galleryGon +=	'<li>';
						galleryGon +=		'<ul class="gallery_wrap">';
						if ( $gallerySelector.data('content-type2') == 'gallery-type-A' || $gallerySelector.data('content-type2') == 'gallery-type-C' ) {
							if ( galleryType == 'instargram' ) { // 인스타그램인 경우
								galleryGon +=	'<li class="img_area" data-item-seq="' + response.items[key].item_seq + '" data-gallery-type="' + galleryType + '"><a href="' + response.items[key].sns_link + '" target="_blank" title="새창"><img src="' + response.items[key].sns_url + '" alt="" /></a></li>';
							} else {
								galleryGon +=	'<li class="img_area" data-item-seq="' + response.items[key].item_seq + '"><img src="' + response.items[key].url + '" alt="" /></li>';
							}
						} else {
							if ( galleryType == 'instargram' ) { // 인스타그램인 경우
								galleryGon +=		'<li class="img_area" data-item-seq="' + response.items[key].item_seq + '" data-gallery-type="' + galleryType + '" style="background-image:url(' + response.items[key].sns_url + ');"><a class="full_link" href="' + response.items[key].sns_link + '" target="_blank" title="새창"></a></li>';
							} else {
								galleryGon +=		'<li class="img_area" data-item-seq="' + response.items[key].item_seq + '" style="background-image:url(' + response.items[key].url + ');"></li>';
							}
						}
						galleryGon +=		'</ul>';
						galleryGon +=	'</li>';
					});

					var $elems = $( galleryGon );

					// $elems append ( Mansory, 일반 )
					galleryAppend( moduleSelector, page, $elems, galleryPagingType );

					/* 페이징 */
					var pagingTotal = response.page.last_page;
					switch ( galleryPagingType ) {
						case 'paging_1' : // 더보기
							if ( !is_paging_click ) {
								$( moduleSelector + ' .gallery_paging_1 > a' ).off( 'click' );
								galleryPaging_1( module_id, pagingTotal );
							}
							break;
						case 'paging_2' : // 스크롤
							if ( !is_paging_click ) {
								setTimeout(function() {
									galleryPaging_2_st( module_id, pagingTotal );
								}, 1000 );
							}
							break;
						case 'paging_3' : // 숫자 버튼
							if ( !is_paging_click ) {
								$( moduleSelector + ' .paging_navigation' ).twbsPagination('destroy');
								galleryPaging3Render( module_id, pagingTotal );
							}
							break;
					}
					if (response.sns_connect == 'instargram')
					{
						snsGalleryInstargramController(galleryID);
					}
				}
			}
		},
		error: function( res ) {
			console.log( res );
		}
	});
}

function snsGalleryInstargramController (gallery_id)
{
	$.ajax({
		url		: '/gallerys/'+ gallery_id + '/snsreplace',
		dataType: 'json',
		async	: true,
		success	: function (response) {
			console.log(response);
		},
		error: function( res ) {
			console.log( res );
		}
	});
}

/* elements append ( Mansory, 일반 ) */
function galleryAppend( module_selector, page, $elements, paging_type ) {
	var $removeItems = $( module_selector + ' .gallery_ul > li' );
	var galleryType = $( module_selector ).children( '[data-content-type1="gallery"]' ).data( 'content-type2');

	if ( galleryType == 'gallery-type-A' || galleryType == 'gallery-type-C' ) { // masonry type
		if ( page == 1 ) {
			// masonry 초기화
			$( module_selector ).find( '.gallery_ul' ).masonry({
				stagger: 0
			});
			$( module_selector + ' .gallery_ul' ).masonry( 'remove', $removeItems );
			$( module_selector ).find( '.gallery_ul' ).prepend( $elements ).masonry( 'prepended', $elements );
		} else if ( page != 1 && paging_type == 'paging_3' ) {
			$( module_selector ).find( '.gallery_ul' ).prepend( $elements ).masonry( 'prepended', $elements );
			$( module_selector + ' .gallery_ul' ).masonry( 'remove', $removeItems );
		} else {
			$( module_selector ).find( '.gallery_ul' ).append( $elements ).masonry( 'appended', $elements );
		}

		$( module_selector + ' .gallery_ul' ).find('img').one('load', function() {
			if (  galleryType == 'gallery-type-C' ) {
				var newGalleryImg = new Image();
				newGalleryImg.src = $(this).attr('src');
				if ( newGalleryImg.width >= 1280 ) {
					$(this).closest( '.gallery_wrap' ).parent( 'li' ).addClass( 'w2' );
				}
			}
			$( module_selector ).find( '.gallery_ul' ).masonry( 'layout' );
		});
	} else { // none masonry type
		if ( page == 1 ) {
			$( module_selector ).find( '.gallery_ul' ).html( $elements );
		} else {
			if ( paging_type == 'paging_3' ) {
				$( module_selector ).find( '.gallery_ul' ).html( $elements );
			} else {
				$( module_selector ).find( '.gallery_ul' ).append( $elements );
			}
		}
	}
}


/* paging_1 ( '더보기' 페이징 ) */
function galleryPaging_1( module_id, paging_total ) {
	var moduleSelector = targetSelector( module_id );

	// 초기화
	var nowPage = 1;
	$( moduleSelector ).children( '[data-content-type1="gallery"]' ).data( 'gallery-currentpage', nowPage ).attr( 'data-gallery-currentpage', nowPage );
	$( moduleSelector ).find( '.gallery_paging_1' ).removeAttr( 'style' );

	// 클릭시
	$( moduleSelector + ' .gallery_paging_1 > a' ).on( 'click', function() {
		nowPage = $( moduleSelector ).children( '[data-content-type1="gallery"]' ).data( 'gallery-currentpage' );
		var nextPage = nowPage + 1;

		galleryAjax( module_id, nextPage, true );

		$( moduleSelector ).children( '[data-content-type1="gallery"]' ).data( 'gallery-currentpage', nextPage ).attr( 'data-gallery-currentpage', nextPage );

		if ( nextPage >= paging_total ) {
			$( moduleSelector ).find( '.gallery_paging_1' ).hide();
		} else {
			$( moduleSelector ).find( '.gallery_paging_1' ).removeAttr( 'style' );
		}
	});
}


/* paging_2 ( Mansory - append ) */
var galleryLastScrollTop = 0;

function galleryPaging_2_st( module_id, paging_total ) {
	var scrollTop;
	var moduleID, moduleSelector, $gallerySelector;
	var galleryID, galleryPerPage;
	var nowPage, lastPage;

	moduleID = module_id
	moduleSelector = targetSelector( module_id );
	$gallerySelector = $( moduleSelector ).children( '[data-content-type1="gallery"]' );

	$gallerySelector.data( 'gallery-lastpage', paging_total ).attr( 'data-gallery-lastpage', paging_total );

	nowPage = Number( $gallerySelector.attr( 'data-gallery-currentpage' ) );
	lastPage = Number( $gallerySelector.attr( 'data-gallery-lastpage' ) );

	if ( nowPage < lastPage  ) {
		var windowHeight = $(window).height();
		scrollTop = $(window).scrollTop();
		var galleryPositionTop = $( moduleSelector ).offset().top;
		var galleryBottom = $( moduleSelector ).height() + 50; // 하단에서 50px

		if ( scrollTop >= galleryLastScrollTop && ( windowHeight + scrollTop ) > ( galleryPositionTop + galleryBottom ) ) { // scroll down & 스크롤 바닥 감지
			galleryID = $gallerySelector.attr( 'data-gallery-id' );
			galleryPerPage = $gallerySelector.attr( 'data-gallery-perpage' );
			galleryAjax( moduleID, nowPage + 1, false, galleryID, galleryPerPage );
			$gallerySelector.data( 'gallery-currentpage', nowPage + 1 ).attr( 'data-gallery-currentpage', nowPage + 1 );
		}
	}
	galleryScrollFlag = true;
	return false;
}

/* paging_2 ( Mansory - scroll ) */
function galleryPaging_2_sc() {
	var scrollTop;
	var moduleID, moduleSelector, $gallerySelector;
	var nowPage, lastPage;

	$( '[data-gallery-pagingtype="paging_2"]' ).each(function() {
		moduleID = childFindModuleID( this );
		moduleSelector = targetSelector( moduleID );
		$gallerySelector = $( moduleSelector ).children( '[data-content-type1="gallery"]' );
		nowPage = Number( $gallerySelector.attr( 'data-gallery-currentpage' ) );
		lastPage = Number( $gallerySelector.attr( 'data-gallery-lastpage' ) );
		if ( nowPage < lastPage ) {
			return false;
		}
	});

	var windowHeight = $(window).height();
	scrollTop = $(window).scrollTop();
	var galleryPositionTop = $( moduleSelector ).offset().top;
	var galleryBottom = $( moduleSelector ).height() + 50; // 하단에서 50px

	if ( scrollTop >= galleryLastScrollTop && ( windowHeight + scrollTop ) > ( galleryPositionTop + galleryBottom ) && nowPage < lastPage ) {
		galleryID = $gallerySelector.attr( 'data-gallery-id' );
		galleryPerPage = $gallerySelector.attr( 'data-gallery-perpage' );
		galleryAjax( moduleID, nowPage + 1, false, galleryID, galleryPerPage );
		$gallerySelector.data( 'gallery-currentpage', nowPage + 1 ).attr( 'data-gallery-currentpage', nowPage + 1 );
		galleryScrollFlag = false;
	}

	galleryLastScrollTop = scrollTop;

	return false;
}

/* paging_3 ( 숫자 페이징 ) */
var galleryPagingVisibleNum = 5; // 페이징 넘버 개수
function galleryPaging3Render( module_id, paging_total ) {
	var moduleSelector = targetSelector( module_id );
	$( moduleSelector + ' .paging_navigation' ).twbsPagination({
		totalPages: paging_total,
		visiblePages: galleryPagingVisibleNum,
		startPage : 1,
		initiateStartPageClick: false, // 플러그인이 시작시 페이지 버튼 클릭 여부
		first : '<i class="fa fa-angle-double-left"></i>',
		prev : '<i class="fa fa-angle-left"></i>',
		next : '<i class="fa fa-angle-right"></i>',
		last : '<i class="fa fa-angle-double-right"></i>',
		firstClass : 'direction first',
		prevClass : 'direction prev',
		nextClass : 'direction next',
		lastClass : 'direction last',
		pageClass : 'page-item',
		activeClass : 'on',
		disabledClass : 'disabled',

		onPageClick: function (event, page) {
			galleryAjax( module_id, page, true );
		}
	});
}

/* 갤러리 상세 레이어 */
function galleryDetailAjax( gallery_id, seq, is_init ) {
	var lastPage;
	$.ajax({
		url : '/gallerys/'+ gallery_id + '/items?perpage=1&page=' + seq,
		dataType : 'json',
		async: false,
		success : function( response ) {
			lastPage = response.page.last_page;

			// seq, total 설정
			$('#galleryViewPopup .num .current').text( seq );
			$('#galleryViewPopup .num .last').text( lastPage );
			$('#galleryViewPopup').data( 'gallery-id', gallery_id ).attr( 'data-gallery-id', gallery_id );
			$('#galleryViewPopup').data( 'gallery-seq', seq ).attr( 'data-gallery-seq', seq );

			// 상세 이미지 설정
			var galleryImageGon = '<img src="' +  response.items[0].url + '" alt="" />';
			$('#galleryViewPopup .gallery_image_area').html( galleryImageGon );
		}
	});

	if ( is_init ) {
		$.ajax({
			url : '/gallerys/' + gallery_id,
			dataType : 'json',
			async: false,
			success : function( response ) {
				// 레이어 타이틀 설정
				$('#galleryViewPopup .gallery_name').text( response.gallery.gallery_name );
			}
		});

		$('#skinModal').addClass('dark').fadeIn( 200 );
		$('#galleryViewPopup').delay(200).fadeIn( 200 );
	}

	// 1번, 끝번 버튼 노출 제어
	$('#galleryViewPopup .arrow_btn').show();
	if ( seq == 1 ) {
		$('#galleryViewPopup .arrow_btn[data-direction="prev"]').hide();
	} else if ( seq == lastPage ) {
		$('#galleryViewPopup .arrow_btn[data-direction="next"]').hide();
	}
	// 토탈 개수가 1개인 경우
	if ( lastPage == 1 ) {
		$('#galleryViewPopup .arrow_btn[data-direction="prev"]').hide();
		$('#galleryViewPopup .arrow_btn[data-direction="next"]').hide();
	}
}


$(function() {

	/* 갤러리 상세 레이어 열기 */
	$('#skinMainWrap').on('click', '[data-content-type1="gallery"] .img_area', function() {
		if ( $(this).data('gallery-type') != 'instargram' ) {
			var galleryID = $(this).closest( '[data-content-type1="gallery"]' ).data( 'gallery-id' );
			var seq = $(this).data( 'item-seq' );
			galleryDetailAjax( galleryID, seq, true );
		}
	});

	/* 갤러리 상세 좌우 버튼 */
	$('#galleryViewPopup .arrow_btn').on('click', function() {
		var galleryID = $( '#galleryViewPopup' ).data( 'gallery-id' );
		var seq = Number( $( '#galleryViewPopup' ).data( 'gallery-seq' ) );

		switch ( $(this).data('direction') ) {
			case 'prev' : // 더보기
				galleryDetailAjax( galleryID, seq - 1 );
				break;
			case 'next' : // 더보기
				galleryDetailAjax( galleryID, seq + 1 );
				break;
		}
	});

	/* 갤러리 상세 레이어 닫기 */
	$('#galleryViewPopup .btn_pop_close').click(function() {
		$('#skinModal').removeClass('dark').fadeOut( 200 );
		$('#galleryViewPopup').fadeOut( 200 );
	});
});


var galleryScrollFlag = true;
$( window ).scroll(function() {
	if ( $('[data-gallery-pagingtype="paging_2"]').length ) {
		setTimeout(function() {
			if ( galleryScrollFlag ){
				galleryPaging_2_sc();
			}
		}, 200 );  // paging_2 scroll function
	}
});