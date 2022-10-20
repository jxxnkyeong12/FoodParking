/* ----------------------------------------------
상품 디스플레이 모듈
---------------------------------------------- */

/* 상품그룹 목록 */
function productGroupListAjax( search_keyword ) {
	var productGroupList = [];
	var searchKeyword = search_keyword ? search_keyword : '';

	$.ajax({
		url : '/productGroups',
		type: 'get',
		data: { 'keyword': searchKeyword },
		async: false,
		success : function( response ) {
			productGroupList = response.products_groups;
		}
	});

	return productGroupList;
}


/* 상품그룹 정보( name, desc 설정 ) */
function productGroupInfoAjax( module_id, group_seq ) {
	var moduleSelctor = targetSelector( module_id );
	$.ajax({
		url : '/productGroups/' + group_seq + '/view',
		type: 'get',
		async: true,
		success : function( response ) {
			$( moduleSelctor ).find('.productGroupTitle').text( response.data.group_name );
			$( moduleSelctor ).find('.productGroupDesc').text( response.data.group_desc );
		}
	});
}


/* 상품디스플레이 렌더링 */
var productGroupOptions;
function productGroupRender( module_id, group_seq, add_option ) {
	var moduleID, moduleSelector, $productGroupSelector;
	if ( !module_id ) { // Loading
		if ( !$('[data-content-type1="builderProductA"]').length ) return false;

		$('[data-content-type1="builderProductA"]').each(function() {
			moduleID = $(this).parent('[data-module-id]').length ? $(this).parent('[data-module-id]').data('module-id') : $(this).parent('[data-minimodule-id]').data('minimodule-id');
			moduleSelector = targetSelector( moduleID );
			$productGroupSelector = $( moduleSelector ).children('[data-content-type1="builderProductA"]');

			$( moduleSelector ).find( '.productListAjax' ).empty();
			$( moduleSelector ).find( '.product_paging_1, .product_paging_3' ).removeAttr( 'style' );

			productAjax( moduleID, 1 );
		});
	} else { // 최초 삽입 & Editing
		moduleID = module_id;
		moduleSelector = targetSelector( moduleID );
		$productGroupSelector = $( moduleSelector ).children('[data-content-type1="builderProductA"]');

		if ( group_seq ) {
			$productGroupSelector.data( 'group-seq', group_seq ).attr( 'data-group-seq', group_seq );
		}
		if ( add_option == 'productAjax' ) {
			productAjax( moduleID, 1 ); // 데이터 연동
		} else if ( add_option == 'swiperAuto' ) {
			productSwiperProcess( module_id, moduleSelector );
		}
	}
	$productGroupSelector.data( 'product-currentpage', 1 ).attr( 'data-product-currentpage', 1 );
	productLastScrollTop = 0;
}

function benefitAjax(productDisplaySeqs, moduleId) {

	var params = {
		'product_display_seqs': productDisplaySeqs
	};

	$.ajax({
		url : '/products/benefits',
		type: 'get',
		dataType : 'json',
		data : params,
		success : function( response ) {
			if (response.result == true) {
				benefitDraw(response.data, moduleId);
			}
		},
		error: function( res ) {
			console.log( res );
		}
	});
}

function benefitDraw(benefitDatas, moduleId) {
	if ($.isArray(benefitDatas) && benefitDatas.length <= 0) {
		return false;
	}

	$.each(benefitDatas, function(i, benefit) {
		if (benefit.coupon > 0) {
			var discountCouponElement = $('#benefit_coupon_marker_'+moduleId+'_'+i);
			
			discountCouponElement.removeClass('hide');
		}
	});

	return;
}


/* 상품디스플레이 Ajax */
function productAjax( module_id, page, is_paging_click, group_seq, perpage ) {
	var moduleSelector = targetSelector( module_id );
	var $productGroupSelector = $( moduleSelector ).children( '[data-content-type1="builderProductA"]' );
	var moduleType2 = $productGroupSelector.data( 'content-type2' );

	var productGroupID = group_seq ? group_seq : $productGroupSelector.data('group-seq');
	var productGroupPerPage = perpage ? perpage : $productGroupSelector.data('product-perpage');
	var productGroupPagingType = $productGroupSelector.data('product-pagingtype');

	if ( page == 1 ) {
		$productGroupSelector.data( 'product-currentpage', 1 ).attr( 'data-product-currentpage', 1 );
	}

	$.ajax({
		url : '/productGroups/' + productGroupID + '/items?perpage=' + productGroupPerPage + '&page=' + page,
		type: 'get',
		dataType : 'json',
		async: true,
		success : function( response ) {
			var $productErrorContetns;
			if ( response.error ) {
				// 상품그룹 자체가 없는 경우
				$productErrorContetns = '<div class="skin_user_text">연결된 상품그룹이 없습니다.</div><div class="no_board_text">연결된 상품그룹이 없습니다.<br />상품그룹을 연결하시려면 아래 버튼을 클릭해주세요.<br /><br /></div><div class="no_board_button"><button data-select-type="builderProductA" class="btnBoardSelect btn_resp btn_board_select">상품그룹 선택</button></div>';
				$( moduleSelector ).find( '.no_data_area' ).html( $productErrorContetns ).show();
				$( moduleSelector ).find( '.productListAjax' ).hide();
			} else {
				if ( response.items.length == 0 ) { // 데이터가 없는 경우
					$productErrorContetns = '<div class="product_no_item">상품이 없습니다.</div>';
					$( moduleSelector + ' .productListAjax').hide();
					$( moduleSelector + ' .product_paging').hide();
					$( moduleSelector ).find( '.no_data_area' ).html( $productErrorContetns ).show();
				} else {
					$( moduleSelector + ' .productListAjax').show();
					$( moduleSelector + ' .no_data_area').hide();

					// 화폐 처리
					switch ( response.currency ) {
						case '원' :
							$productGroupSelector.data( 'currency', 'won' ).attr( 'data-currency', 'won' );
							break;
						case 'krw' :
							$productGroupSelector.data( 'currency', 'krw' ).attr( 'data-currency', 'krw' );
							break;
						case 'dollar' :
							$productGroupSelector.data( 'currency', 'dollar' ).attr( 'data-currency', 'dollar' );
							break;
						default :
							$productGroupSelector.data( 'currency', 'won' ).attr( 'data-currency', 'won' );
					}

					var salesStaus, stockStaus;
					var salePrice, retailPrice, discountPercent; // 숫자 가공을 위함
					var productLink;
					var productGroupGon = '';
					var addOptions = [];
					var displaySeqs = [];
					$.each( response.items, function( key ) {
						salesStaus = (response.items[key].sales_staus == 'Y') ? 'yes' : 'no';  // 판매상태( 판매 or 판매중지 )
						stockStaus = response.items[key].stock_staus; // 품절상태( 정상 or soldout )

						// 화폐 콤마 처리, 할인율 소숫점 올림
						salePrice = Number( response.items[key].sale_price ).toLocaleString();
						retailPrice = Number( response.items[key].retail_price ).toLocaleString();
						discountPercent = Math.ceil( response.items[key].discount_percent );

						// 할인혜택 마커를 위한 display_seq	취합
						displaySeqs.push(response.items[key].item_seq);

						// 링크 관리( 정상, 판매중지 )
						if ( salesStaus == 'yes' ) productLink = '/products/' + response.items[key].item_seq;
						else productLink = '#none';

						// addOptions
						switch ( moduleType2 ) {
							case 'builderProductA-type-C' :
								addOptions[0] = 'class="swiper-slide"';
								break;
							default :
								addOptions[0] = '';
						}

						// ========= 데이터 바인딩 ========= //
						productGroupGon += '<li data-sales-staus="' + salesStaus + '" data-stock-staus="' + stockStaus + '" ' + addOptions[0] + '>';
						productGroupGon += 	'<a class="list_type_inner" href="' + productLink + '">';
						productGroupGon += 		'<ul class="list_contents">';
						productGroupGon += 			'<li class="img_area">';
						if ( response.items[key].images.length == 0 ) { // 이미지가 없는 경우
							productGroupGon += 			'<p class="img_wrap no_images" title="no images"><em></em></p>';
						} else {
							productGroupGon += 			'<p class="img_wrap"><em><img class="item_img" src="' + response.items[key].images[0] + '" alt="' + response.items[key].name + '" onerror="productImgLoadError(this)" /></em></p>';
						}
						productGroupGon += 				'<p class="status"><em></em></p>';
						if ( salePrice != retailPrice ) {
							productGroupGon += 				'<p class="discountpercent"><em>' + discountPercent + '</em></p>';
						}						
						productGroupGon += 			'</li>';
						productGroupGon += 			'<li class="info_area">';
						productGroupGon += 				'<ul class="info_list">';
						productGroupGon += 					'<li class="name"><em>' + response.items[key].name + '</em></li>';
						if ( response.items[key].desc ) {
							productGroupGon += 				'<li class="desc"><em>' + response.items[key].desc + '</em></li>';
						} else {
							productGroupGon += 				'<li class="desc hide"><em>' + response.items[key].desc + '</em></li>';
						}
						productGroupGon += 					'<li class="saleprice"><em>' + salePrice + '</em></li>';
						if ( salePrice != retailPrice ) {
							productGroupGon += 					'<li class="retailprice"><em>' + retailPrice + '</em></li>';
							productGroupGon += 					'<li class="discountpercent"><em>' + discountPercent + '</em></li>';
						}	
						productGroupGon += 					'<li class="discountcoupon hide" id="benefit_coupon_marker_'+module_id+'_'+response.items[key].item_seq+'"><em>할인쿠폰</em></li>';					
						productGroupGon += 				'</ul>';
						productGroupGon += 			'</li>';
						productGroupGon += 		'</ul>';
						productGroupGon += 	'</a>';
						productGroupGon += '</li>';
					});

					var $elems = $( productGroupGon );

					// $elems append ( Mansory, 일반 )
					productAppend( moduleSelector, page, $elems, productGroupPagingType );

					benefitAjax(displaySeqs, module_id);

					if ( moduleType2 == 'builderProductA-type-C' ) {
						productSwiperProcess( module_id, moduleSelector );
					}

					// 페이징
					var pagingTotal = response.page.last_page;
					switch ( productGroupPagingType ) {
						case 'paging_1' : // 더보기
							if ( !is_paging_click ) {
								$( moduleSelector + ' .product_paging_1 > a' ).off( 'click' );
								productPaging_1( module_id, pagingTotal );
							}
							break;
						case 'paging_2' : // 스크롤
							if ( !is_paging_click ) {
								setTimeout(function() {
									productPaging_2_st( module_id, pagingTotal );
								}, 1000 );
							}
							break;
						case 'paging_3' : // 숫자 버튼
							if ( !is_paging_click ) {
								$( moduleSelector + ' .paging_navigation' ).twbsPagination('destroy');
								productPaging3Render( module_id, pagingTotal );
							}
							break;
					}
				}
			}
		},
		error: function( res ) {
			console.log( res );
		}
	});
}

/* elements append ( Mansory, 일반 ) */
function productAppend( module_selector, page, $elements, paging_type ) {
	if ( page == 1 ) {
		$( module_selector ).find( '.productListAjax' ).html( $elements );
	} else {
		if ( paging_type == 'paging_3' ) {
			$( module_selector ).find( '.productListAjax' ).html( $elements );
		} else {
			$( module_selector ).find( '.productListAjax' ).append( $elements );
		}
	}
}

// Swiper 스크립트 처리
function productSwiperProcess( module_id, productSelctor ) {
	var swiperScript = '';
	var swiperOptionSelector = $( productSelctor ).find('[data-swiper-auto]');
	var swiperData = {};

	swiperData.auto = swiperOptionSelector.data('swiper-auto');
	swiperData.delay = swiperOptionSelector.data('swiper-delay');
	swiperData.speed = swiperOptionSelector.data('swiper-speed');

	var swiperVar = 'swiper' + module_id.replace( '-', '' );
	swiperScript = '<script class="' + swiperVar + '">';
	swiperScript += 'var ' + swiperVar + ' = new Swiper("' + productSelctor + ' .productSwiperWrap", { slidesPerView: "auto", scrollbar: { el: ".swiper-scrollbar", draggable : true, }, ';
	if ( swiperData.auto == 'yes' ) {
		swiperScript += 'autoplay: {delay: ' + swiperData.delay + ', disableOnInteraction: true, }, speed : ' + swiperData.speed + ',';
	}
	swiperScript += '});</script>';

	if ( $('script.' + swiperVar).length ) {
		if ( swiperOptionSelector.find('.productSwiperWrap')[0].swiper ) {
			swiperOptionSelector.find('.productSwiperWrap')[0].swiper.destroy();
		}
		swiperOptionSelector.find('.swiper-scrollbar').removeAttr('style').html('');
		$('script.' + swiperVar).remove();
	}
	$('body').append( swiperScript );
}


/* paging_1 ( '더보기' 페이징 ) */
function productPaging_1( module_id, paging_total ) {
	var moduleSelector = targetSelector( module_id );

	if ( paging_total == 1 ) {
		$( moduleSelector ).find( '.product_paging_1' ).hide();
		return false;
	}

	// 초기화
	var nowPage = 1;
	$( moduleSelector ).children( '[data-content-type1="builderProductA"]' ).data( 'product-currentpage', nowPage ).attr( 'data-product-currentpage', nowPage );
	$( moduleSelector ).find( '.product_paging_1' ).removeAttr( 'style' );

	// 클릭시
	$( moduleSelector + ' .product_paging_1 > a' ).on( 'click', function() {
		nowPage = $( moduleSelector ).children( '[data-content-type1="builderProductA"]' ).data( 'product-currentpage' );
		var nextPage = nowPage + 1;

		productAjax( module_id, nextPage, true );

		$( moduleSelector ).children( '[data-content-type1="builderProductA"]' ).data( 'product-currentpage', nextPage ).attr( 'data-product-currentpage', nextPage );

		if ( nextPage >= paging_total ) {
			$( moduleSelector ).find( '.product_paging_1' ).hide();
		} else {
			$( moduleSelector ).find( '.product_paging_1' ).removeAttr( 'style' );
		}
	});
}


/* paging_3 ( 숫자 페이징 ) */
var productPagingVisibleNum = 5; // 페이징 넘버 개수
function productPaging3Render( module_id, paging_total ) {
	var moduleSelector = targetSelector( module_id );
	$( moduleSelector + ' .paging_navigation' ).twbsPagination({
		totalPages: paging_total,
		visiblePages: productPagingVisibleNum,
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
			productAjax( module_id, page, true );
		}
	});
}


/* paging_2 ( start )  */
var productLastScrollTop = 0;
function productPaging_2_st( module_id, paging_total ) {
	var scrollTop;
	var moduleID, moduleSelector, $productSelector;
	var productID, productPerPage;
	var nowPage, lastPage;

	moduleID = module_id
	moduleSelector = targetSelector( module_id );
	$productSelector = $( moduleSelector ).children( '[data-content-type1="builderProductA"]' );

	$productSelector.data( 'product-lastpage', paging_total ).attr( 'data-product-lastpage', paging_total );

	nowPage = Number( $productSelector.attr( 'data-product-currentpage' ) );
	lastPage = Number( $productSelector.attr( 'data-product-lastpage' ) );

	if ( nowPage < lastPage  ) {
		var windowHeight = $(window).height();
		scrollTop = $(window).scrollTop();
		var productPositionTop = $( moduleSelector ).offset().top;
		var productBottom = $( moduleSelector ).height() + 50; // 하단에서 50px

		if ( scrollTop >= productLastScrollTop && ( windowHeight + scrollTop ) > ( productPositionTop + productBottom ) ) { // scroll down & 스크롤 바닥 감지
			productID = $productSelector.attr( 'data-group-seq' );
			productPerPage = $productSelector.attr( 'data-product-perpage' );
			productAjax( moduleID, nowPage + 1, false, productID, productPerPage );
			$productSelector.data( 'product-currentpage', nowPage + 1 ).attr( 'data-product-currentpage', nowPage + 1 );
		}
	}
	productScrollFlag = true;
	return false;
}

/* paging_2 ( scroll ) */
function productPaging_2_sc() {
	var scrollTop;
	var moduleID, moduleSelector, $productSelector;
	var nowPage, lastPage;

	$( '[data-product-pagingtype="paging_2"]' ).each(function() {
		moduleID = childFindModuleID( this );
		moduleSelector = targetSelector( moduleID );
		$productSelector = $( moduleSelector ).children( '[data-content-type1="builderProductA"]' );
		nowPage = Number( $productSelector.attr( 'data-product-currentpage' ) );
		lastPage = Number( $productSelector.attr( 'data-product-lastpage' ) );
		if ( nowPage < lastPage ) {
			return false;
		}
	});

	var windowHeight = $(window).height();
	scrollTop = $(window).scrollTop();
	var productPositionTop = $( moduleSelector ).offset().top;
	var productBottom = $( moduleSelector ).height() + 50; // 하단에서 50px

	if ( scrollTop >= productLastScrollTop && ( windowHeight + scrollTop ) > ( productPositionTop + productBottom ) && nowPage < lastPage ) {
		productID = $productSelector.attr( 'data-group-seq' );
		productPerPage = $productSelector.attr( 'data-product-perpage' );
		productAjax( moduleID, nowPage + 1, false, productID, productPerPage );
		$productSelector.data( 'product-currentpage', nowPage + 1 ).attr( 'data-product-currentpage', nowPage + 1 );
		productScrollFlag = false;
	}

	productLastScrollTop = scrollTop;

	return false;
}

var productScrollFlag = true;
$( window ).scroll(function() {
	if ( $('[data-product-pagingtype="paging_2"]').length ) {
		setTimeout(function() {
			if ( productScrollFlag ){
				productPaging_2_sc();
			}
		}, 200 );  // paging_2 scroll function
	}
});

/* 이미지 오류 처리 */
function productImgLoadError( obj ) {
	$( obj ).closest('.img_wrap').addClass('no_images');
}