/* =========================================================================
게시판, 최근게시물  공통
========================================================================= */
/*  게시판 목록 */
function moduleBoardListReturn( boardListReturn ) {
	var boardList = []; //  에디터에서 게시판 선택시 사용
	var i = 0;
	$.ajax({
		type: 'get',
		async: false, // true로 변경시 return 받지 못함.
		url: '/boards',
		data: {},
		success: function( response ) {
			$.each( response.data, function( key, value ) {
				boardList[i] = {};
				boardList[i]['board_id'] = value['board_id'];
				boardList[i]['board_name'] = value['board_name'];
				boardList[i]['auth_view'] = value['auth_view'];
				boardList[i]['auth_write'] = value['auth_write'];
				i++;
			});
		}
	});
	if ( boardListReturn ) return boardList; // ajax 내부에서의 return은 결과가 undefined 된다.
}

function boardCategoryUse( module_id ) {
	var boardID, useCategory;
	$.ajax({
		type: 'get',
		async: true,
		url: '/boards',
		data: {},
		success: function( response ) {
			$.each( response.data, function( key, value ) {
				boardID = value['board_id'];
				useCategory = value['use_category'];
				//  스킨단 카테고리 사용 여부 처리
				if ( useCategory == 1) {
					$('[data-board-id=' + boardID + ']').data( 'board-category', 'yes' ).attr( 'data-board-category', 'yes' );
					//if ( module_id ) boardCategoryList( module_id, boardID );
				} else {
					$('[data-board-id=' + boardID + ']').data( 'board-category', 'no' ).attr( 'data-board-category', 'no' );
				}
			});
		}
	});
}

/*  카테고리 목록( 카테고리 사용인 경우 ) */
function boardCategoryList( module_id, board_id ) {
	var moduleSelctor = targetSelector( module_id );
	var boardSelector = $( moduleSelctor ).children('[data-content-type1="board"]');
	$.ajax({
		type: 'get',
		async: true,
		url: '/boards',
		data: { 'bid': board_id },
		success: function( response ) {
			var boardCateGon = '<option value="">&nbsp;분류&nbsp;</option>';
			$.each( response.category, function( key ) {
				boardCateGon += '<option value="' + response.category[key]["category_name"] + '">&nbsp;' + response.category[key]["category_name"] + '&nbsp;</option>';
			});
			$( moduleSelctor + ' select[name="board_category"]').html( boardCateGon );
		}
	});
}

/* 비밀글 Process */
function secretPostProcess( writerType, postSeq, postPw ) {
	var postPw = ( writerType == 'non-members' ) ? postPw : '';
	var postViewUrl = $('[data-post-seq="'+ postSeq +'"').attr('href');

	$.ajax({
		type: 'post',
		async: true,
		url: '/boards/' + postSeq + '/chkpass',
		data: { 'chk_pass': postPw },
		success: function( response ) {
			if ( response.result ) {				
				if ( $('body').hasClass('edit_mode') ) { // 디자인 편집 모드
					window.open( postViewUrl );
				} else {
					location.href = postViewUrl;
				}
			} else {
				alert( '비밀번호가 일치하지 않거나 권한이 없습니다.' );
			}
		}
	});
}

/* board_name, board_desc 삽입 - 210222 */
function moduleBoardTitle( module_id, board_id ) {
	var boardName, boardDesc;
	var moduleSelctor = targetSelector( module_id );
	$.ajax({
		type: 'get',
		async: true,
		url: '/boards',
		data: {},
		success: function( response ) {
			// 게시판명은 필수 입력사항
			$( moduleSelctor ).find( '.boardTitle' ).text( response.data[board_id].board_name );

			if ( response.data[board_id].board_desc ) {
				$( moduleSelctor ).find( '.boardDesc' ).text( response.data[board_id].board_desc );
			} else {
				$( moduleSelctor ).find( '.boardDesc' ).text( '   ' );
			}
		}
	});
}


/* =========================================================================
게시판
========================================================================= */
var boardOptions;
var boardTitles;
var pagingVisibleNum = 5; // 페이징 넘버 개수

var today = new Date();
var todayYmdNumber = today.getFullYear() + ('0' + (today.getMonth() + 1)).slice(-2) + ('0' + today.getDate()).slice(-2);

/* 옵션 렌더링 */
function moduleBoardRender( module_id, board_id, add_option, auth_view, auth_write ) {
	var moduleID, moduleSelctor, boardSelector;
	if ( !module_id ) { // Loading 인 경우
		$('[data-content-type1="board"]').each(function() {
			moduleID = $(this).parent('[data-module-id]').length ? $(this).parent('[data-module-id]').data('module-id') : $(this).parent('[data-minimodule-id]').data('minimodule-id');
			moduleSelctor = targetSelector( moduleID );
			boardSelector = $( moduleSelctor ).children('[data-content-type1="board"]');

			boardOptionSetting( moduleID );
			boardAjax( moduleID, 1 ); // 데이터 연동

			if ( boardSelector.data( 'board-category' ) == 'yes' ) {
				boardCategoryList( moduleID, boardSelector.data( 'board-id' ) );
			}

			$( moduleSelctor ).find( '.btn_write' ).attr( 'href', '/boards/' + boardOptions.boardID + '/write' + (typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' ) ); // 201124 - 쓰기 링크
		});
	} else { // Editing 인 경우
		moduleID = module_id;
		moduleSelctor = targetSelector( moduleID );
		boardSelector = $( moduleSelctor ).children('[data-content-type1="board"]');

		boardOptionSetting( moduleID, board_id );
		if ( add_option == 'boardAjax' ) {
			boardAjax( moduleID, 1 ); // 데이터 연동

			if ( boardSelector.data( 'board-category' ) == 'yes' ) {
				boardCategoryList( module_id, board_id );
			}
		}

		// 게시판 권한에 따른 읽기/쓰기 - 목록에서 처리
		boardSelector.attr( 'data-auth-view', auth_view );
		boardSelector.attr( 'data-auth-write', auth_write );

		boardSelector.find('.list_board .tbody li.item_viewcount .mo_title').text( boardSelector.data('board-title-viewcount') ); // 반응형 tbody 내에서 바꿔져야할 것들( boardAjax 뒤에 위치해야함 )
		$( moduleSelctor ).find( '.btn_write' ).attr( 'href', '/boards/' + boardOptions.boardID + '/write' + (typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' ) ); // 201124 - 쓰기 링크
	}
}

/* 옵션 처리 */
function boardOptionSetting( module_id, board_id ) {
	var moduleSelctor, boardSelector;

	moduleSelctor = targetSelector( module_id );
	boardSelector = $( moduleSelctor ).children('[data-content-type1="board"]');

	if ( board_id ) {
		boardSelector.data( 'board-id', board_id ).attr( 'data-board-id', board_id );
	}
	boardCategoryUse( module_id ); // 카테고리 사용 여부

	boardOptions = {};
	boardOptions.boardID = boardSelector.data('board-id');
	boardOptions.boardPerPage = boardSelector.data('board-per-page');
	boardOptions.boardKeywordType = boardSelector.data('board-keyword-type');
	boardOptions.boardKeywordText = boardSelector.data('board-keyword-text');

	boardSelector.find('.list_board .thead li.item_seq').text( boardSelector.data('board-title-seq') );
	boardSelector.find('.list_board .thead li.item_category').text( boardSelector.data('board-title-category') );
	boardSelector.find('.list_board .thead li.item_subject').text( boardSelector.data('board-title-subject') );
	boardSelector.find('.list_board .thead li.item_writer').text( boardSelector.data('board-title-writer') );
	boardSelector.find('.list_board .thead li.item_date').text( boardSelector.data('board-title-date') );
	boardSelector.find('.list_board .thead li.item_viewcount').text( boardSelector.data('board-title-viewcount') );
}

// 게시판 데이터 연동
function boardAjax( module_id, page, is_paging_click ) {
	var moduleSelctor = targetSelector( module_id );

	$( moduleSelctor + ' .list_board .tbody').remove();
	$( moduleSelctor + ' .image_board').html( '' );
	$( moduleSelctor + ' .faq_board').html( '' );

	$.ajax({
		type: 'get',
		async: true, // 동기식 처리 ( each() 내부의 $.ajax 는 each() 의 각각이 실행되기 전에 단독으로 실행되는 버그 있음 )
		url: '/boards/' + boardOptions.boardID,
		data: { 'page': page, 'per_page': boardOptions.boardPerPage, 'keyword_type': boardOptions.boardKeywordType, 'keyword': boardOptions.boardKeywordText },
		success: function( response ) {
			if ( response.error == 'error : not_found' ) {
				// 게시판이 없는 경우
				var $noBoardHtml = '<div class="skin_user_text">연결된 게시판이 없습니다.</div><div class="no_board_text">연결된 게시판이 없습니다.<br />게시판을 연결하시려면 아래 버튼을 클릭해주세요.<br /><br /></div><div class="no_board_button"><button data-select-type="board" class="btnBoardSelect btn_resp btn_board_select">게시판 선택</button></div>';
				$( moduleSelctor ).find( '.no_data_area' ).html( $noBoardHtml ).show();
				$( moduleSelctor ).find( '.list_board .tbody, .image_board .tbody, .faq_board .tbody, .board_footer' ).hide();
			} else {
				$( moduleSelctor ).find( '.board_footer' ).show();
				var boardNoticesGon = '';
				var boardGon = '';

				switch ( $( moduleSelctor ).children( '[data-content-type2]' ).data( 'content-type2' ) ) {
					// ++++++++ board-type-A ++++++++ //
					case 'board-type-A' :
						// 공지글
						$.each( response.notices, function( key ) {
							var postDate = response.notices[key]["regist_date"].slice(0, 10);
							var postTime = response.notices[key]["regist_date"].slice(11, 16);
							var postDateNumber = Number( postDate.split('-').join('') );

							var postCategory = response.notices[key]["post_category"] ? response.notices[key]["post_category"] : '';

							boardNoticesGon += '<ul class="tbody notice">';
							boardNoticesGon += '<li class="item_seq"><span class="notice_icon"></span></li>';
							boardNoticesGon += '<li class="item_category"><span class="bracket">' + postCategory + '</span></li>';
							boardNoticesGon += '<li class="item_subject">';
							boardNoticesGon += '<a href="/boards/' + response.notices[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'">' + response.notices[key]["post_subject"] + '</a>';

							boardNoticesGon += ' <span class="post_icons">';
							if ( response.notices[key]["post_title_img"] ) boardNoticesGon += '<span class="post_icon image"></span>';
							if ( response.notices[key]["attach_yn"] == 'y' ) boardNoticesGon += '<span class="post_icon addfile"></span>';
							if ( response.notices[key]["comment_cnt"] >= 1 ) boardNoticesGon += '<span class="post_icon comment">' + response.notices[key]["comment_cnt"] + '</span>';
							if ( postDateNumber + 1 > todayYmdNumber ) boardNoticesGon += '<span class="post_icon new"></span>';
							boardNoticesGon += '</span>';

							boardNoticesGon += '</li>';
							boardNoticesGon += '<li class="item_writer">' + response.notices[key]["disp_writer_name"] + '</li>';
							if ( postDateNumber == todayYmdNumber ) boardNoticesGon += '    <li class="item_date">' + postTime + '</li>';
							else boardNoticesGon += '<li class="item_date">' + postDate + '</li>';
							boardNoticesGon += '<li class="item_viewcount"><span class="mo_title">조회</span>' + response.notices[key]["view_count"] + '</li>';
							boardNoticesGon += '</ul>';
						});

						// 일반글
						$.each( response.data, function( key ) {
							var postDate = response.data[key]["regist_date"].slice(0, 10);
							var postTime = response.data[key]["regist_date"].slice(11, 16);
							var postDateNumber = Number( postDate.split('-').join('') );

							var postCategory = response.data[key]["post_category"] ? response.data[key]["post_category"] : '';

							if ( response.data[key]["depth_num"] > 0 ) boardGon += '<ul class="tbody reply reply' + response.data[key]["depth_num"] + '">';
							else boardGon += '<ul class="tbody">';
							boardGon += '<li class="item_seq mo_hide">' + response.data[key]["num"] + '</li>';
							boardGon += '<li class="item_category"><span class="bracket">' + postCategory + '</span></li>';
							boardGon += '<li class="item_subject">';
							if ( response.data[key]["secret_post"] ) {
								var writerType; // 비밀글 작성자 타입
								if ( response.data[key]["writer_type"] == 1 ) {
									writerType = 'manager';
								} else if ( response.data[key]["writer_type"] == 0 && response.data[key]["writer_id"] ) {
									writerType = 'members';
								} else {
									writerType = 'non-members';
								}
								boardGon += '<a href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'" class="secretPost" data-writer-type="' + writerType + '" data-post-seq="' + response.data[key]["post_seq"] + '">' + response.data[key]["post_subject"] + '</a>';
							} else {
								boardGon += '<a href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'">' + response.data[key]["post_subject"] + '</a>';
							}
							boardGon += ' <span class="post_icons">';
							if ( response.data[key]["secret_post"] ) boardGon += '<span class="post_icon lock"></span>'; // 비밀글
							if ( response.data[key]["post_title_img"] ) boardGon += '<span class="post_icon image"></span>';
							if ( response.data[key]["attach_yn"] == 'y' ) boardGon += '<span class="post_icon addfile"></span>';
							if ( response.data[key]["comment_cnt"] >= 1 ) boardGon += '<span class="post_icon comment">' + response.data[key]["comment_cnt"] + '</span>';
							if ( postDateNumber + 1 > todayYmdNumber ) boardGon += '<span class="post_icon new"></span>';
							boardGon += '</span>';
							boardGon += '</li>';
							boardGon += '<li class="item_writer">' + response.data[key]["disp_writer_name"] + '</li>';
							if ( postDateNumber == todayYmdNumber ) boardGon += '    <li class="item_date">' + postTime + '</li>';
							else boardGon += '<li class="item_date">' + postDate + '</li>';
							boardGon += '<li class="item_viewcount"><span class="mo_title">조회</span>' + response.data[key]["view_count"] + '</li>';
							boardGon += '</ul>';
						});

						$( moduleSelctor + ' .list_board .tbody').remove();
						$( moduleSelctor + ' .list_board').append( boardNoticesGon );
						$( moduleSelctor + ' .list_board').append( boardGon );

						break;

					// ++++++++ board-type-B ++++++++ //
					case 'board-type-B' :
						$.each( response.data, function( key ) {
							var postDate = response.data[key]["regist_date"].slice(0, 10);
							var postTime = response.data[key]["regist_date"].slice(11, 16);
							var postDateNumber = Number( postDate.split('-').join('') );

							var postCategory = response.data[key]["post_category"] ? '<span class="item_category">' + response.data[key]["post_category"] + '</span>' : '';

							var writerType; // 비밀글 작성자 타입
							if ( response.data[key]["writer_type"] == 1 ) writerType = 'manager';
							else if ( response.data[key]["writer_type"] == 0 && response.data[key]["writer_id"] ) writerType = 'members';
							else writerType = 'non-members';

							boardGon += '<div class="tbody">';
							boardGon += '<ul class="section_image">';
							boardGon += '<li>';
							if ( response.data[key]["secret_post"] ) {
								boardGon += '<a class="secretPost board_image_wrap" href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'" data-writer-type="' + writerType + '" data-post-seq="' + response.data[key]["post_seq"] + '">';
							} else {
								boardGon += '<a class="board_image_wrap" href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'">';
							}
							if ( response.data[key]["post_title_img"] ) {
								boardGon +=		'<span class="board_image" style="background-image:url(' + response.data[key]["post_title_img"] + ')"></span>';
							} else {
								boardGon +=		'<span class="board_image no_image"></span>';
							}
							boardGon +=		'</a>';
							boardGon += '</li>';
							boardGon += '</ul>';
							boardGon += '<ul class="section_text">';
							boardGon += '<li class="item_subject">';

							if ( response.data[key]["secret_post"] ) {
								boardGon += '<a href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'" class="secretPost" data-writer-type="' + writerType + '" data-post-seq="' + response.data[key]["post_seq"] + '">';
							} else {
								boardGon += '<a href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'">';
							}
							boardGon += postCategory;
							boardGon += response.data[key]["post_subject"];
							boardGon += ' <span class="post_icons">';
							if ( response.data[key]["secret_post"] ) boardGon += '<span class="post_icon lock"></span>'; // 비밀글
							if ( response.data[key]["post_title_img"] ) boardGon += '<span class="post_icon image"></span>';
							if ( response.data[key]["attach_yn"] == 'y' ) boardGon += '<span class="post_icon addfile"></span>';
							if ( response.data[key]["comment_cnt"] >= 1 ) boardGon += '<span class="post_icon comment">' + response.data[key]["comment_cnt"] + '</span>';
							if ( postDateNumber + 1 > todayYmdNumber ) boardGon += '<span class="post_icon new"></span>';
							boardGon += '</span>';
							boardGon += '</a>';
							boardGon += '</li>';

							boardGon += '<li class="item_desc">';
							if ( response.data[key]["secret_post"] ) {
								boardGon += '<a href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'" class="secretPost" data-writer-type="' + writerType + '" data-post-seq="' + response.data[key]["post_seq"] + '">';
							} else {
								boardGon += '<a href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'">';
							}
							boardGon += response.data[key]["post_content"];
							boardGon +=		'</a>';
							boardGon += '</li>';

							 boardGon += '<li class="item_writer">' + response.data[key]["disp_writer_name"] + '</li>';

							if ( postDateNumber == todayYmdNumber ) boardGon += '<li class="item_date">' + postTime + '</li>';
							else boardGon += '<li class="item_date">' + postDate + '</li>';
							boardGon += '</ul>';
							boardGon += '</div>';
						});

						$( moduleSelctor + ' .image_board').html( boardGon );

						break;

					// ++++++++ board-type-C ++++++++ //
					case 'board-type-C' :
						$.each( response.data, function( key ) {
							var postCategory = response.data[key]["post_category"] ? response.data[key]["post_category"] : '';

							boardGon += '<ul class="tbody">';
							boardGon +=	'<li class="item_subject">';
							if ( postCategory ) {
								boardGon +=	'<a href="#none" class="faq_link"><span class="item_category"><span class="bracket">' + postCategory + '</span></span> ' + response.data[key]["post_subject"] + '</a>';
							} else {
								boardGon +=	'<a href="#none" class="faq_link">' + response.data[key]["post_subject"] + '</a>';
							}
							boardGon +=		'<div class="faq_contents">' + response.data[key]["post_content"] + '</div>';
							boardGon +=	'</li>';
							boardGon += '</ul>';
						});

						$( moduleSelctor + ' .faq_board').html( boardGon );

						break;
				}

				var pagingTotal = Math.ceil( response.total / boardOptions.boardPerPage );
				if ( !is_paging_click && response.total > 0 ) {
					$( moduleSelctor + ' .paging_navigation' ).twbsPagination('destroy');
					pagingRender( module_id, pagingTotal );
					$( moduleSelctor + ' .no_data_area').hide();
				} else if ( response.total == 0 ) {
					$( moduleSelctor + ' .paging_navigation' ).twbsPagination('destroy');
					$( moduleSelctor + ' .no_data_area').html( '게시물이 없습니다.' ).show();
				}
			}
		},
		complete: function() {
			// 210628 폰트 스타일 추가
			moduleFontcolorRender( module_id );
			moduleFontfamilyRender( module_id );
		},
		error: function( res ) {
			console.log( res );
		}
	});
}

// 페이징 렌더
function pagingRender( module_id, paging_total ) {
	var moduleSelctor = targetSelector( module_id );
	$( moduleSelctor + ' .paging_navigation' ).twbsPagination({
		totalPages: paging_total,
		visiblePages: pagingVisibleNum,
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
			boardOptionSetting( module_id );
			boardAjax( module_id, page, true );
		}
	});
}

// 게시판 타입 data-auth-view, data-auth-write 설정
function boardAuthViewWrite() {
	$.ajax({
		type: 'get',
		async: true,
		url: '/boards',
		success: function( response ) {
			var boardID, viewAuth, viewWrite;
			$('[data-content-type1="board"], [data-content-type1="boardRecent"]').each(function() {
				if ( $(this).attr( 'data-content-type1' ) == 'board' ) boardID = $(this).attr('data-board-id');
				else if ( $(this).attr( 'data-content-type1' ) == 'boardRecent' )  boardID = $(this).attr('data-boardrecent-id');
				$.each( response.data, function( key, val ) {
					if ( boardID == val.board_id ) {
						viewAuth = val.auth_view;
						viewWrite = val.auth_write;
					}
				});
				$(this).attr({
					'data-auth-view' : viewAuth,
					'data-auth-write' : viewWrite
				});
			});
		},
		complete: function() {
			$('[data-content-type1="board"]').each(function() {
				if ( $(this).attr( 'data-auth-write' ) == 1 ) {
					if ( LOGIN_TYPE == 'members' || LOGIN_TYPE == 'manager' ) {
						$(this).find( '.board_btns' ).show();
					}
				} else if ( $(this).attr( 'data-auth-write' ) == 2 ) {
					if ( LOGIN_TYPE == 'manager' ) {
						$(this).find( '.board_btns' ).show();
					}
				} else {
					$(this).find( '.board_btns' ).show();
				}
			});
		}
	});
}


/* =========================================================================
최근 게시물
========================================================================= */
function moduleBoardRecentRender( module_id, board_id, add_option, auth_view, auth_write ) {
	var moduleID, moduleSelctor, boardRecentSelector;
	if ( !module_id ) { // Loading 인 경우
		$('[data-content-type1="boardRecent"]').each(function() {
			moduleID = $(this).parent('[data-module-id]').length ? $(this).parent('[data-module-id]').data('module-id') : $(this).parent('[data-minimodule-id]').data('minimodule-id');
			moduleSelctor = targetSelector( moduleID );

			boardRecentOptionSetting( moduleID );
			boardRecentAjax( moduleID, 1 ); // 데이터 연동
		});
	} else { // Editing 인 경우
		moduleID = module_id;
		moduleSelctor = targetSelector( moduleID );
		boardRecentSelector = $( moduleSelctor ).children('[data-content-type1="boardRecent"]');

		boardRecentOptionSetting( moduleID, board_id );
		if ( add_option == 'boardAjax' ) {
			boardRecentAjax( moduleID, 1 ); // 데이터 연동
		}

		// 최근게시물 권한에 따른 읽기/쓰기 - 목록에서 처리
		boardRecentSelector.attr( 'data-auth-view', auth_view );
		boardRecentSelector.attr( 'data-auth-write', auth_write );
	}
}

/* 옵션 처리 */
var boardRecentOptions;
function boardRecentOptionSetting( module_id, board_id ) {
	var moduleSelctor, boardRecentSelector;

	moduleSelctor = targetSelector( module_id );
	boardRecentSelector = $( moduleSelctor ).children('[data-content-type1="boardRecent"]');

	if ( board_id ) {
		boardRecentSelector.data( 'boardrecent-id', board_id ).attr( 'data-boardrecent-id', board_id );
	}

	boardRecentOptions = {};
	boardRecentOptions.boardID = boardRecentSelector.data('boardrecent-id');
	boardRecentOptions.boardPerPage = boardRecentSelector.data('boardrecent-per-page');
}

/* 상세 렌더 */
function boardRecentAjax( module_id, page ) {
	var moduleSelctor = targetSelector( module_id );
	$.ajax({
		type: 'get',
		async: true, // 동기식 처리 ( each() 내부의 $.ajax 는 each() 의 각각이 실행되기 전에 단독으로 실행되는 버그 있음 )
		url: '/boards/' + boardRecentOptions.boardID,
		data: { 'page': page, 'per_page': boardRecentOptions.boardPerPage },
		success: function( response ) {
			if ( response.error == 'error : not_found' ) {
				// 관리자에서 해당 게시판 삭제한 경우
				var $noBoardHtml = '<div class="skin_user_text">연결된 게시판이 없습니다.</div><div class="no_board_text">연결된 게시판이 없습니다.<br />게시판을 연결하시려면 아래 버튼을 클릭해주세요.<br /><br /></div><div class="no_board_button"><button data-select-type="boardRecent" class="btnBoardSelect btn_resp btn_board_select">게시판 선택</button></div>';
				$( moduleSelctor ).find( '.no_data_area' ).html( $noBoardHtml ).show();
				$( moduleSelctor ).find( '.recent_board' ).hide();
			} else {
				var boardRecentGon = '';

				switch ( $( moduleSelctor ).children( '[data-content-type2]' ).data( 'content-type2' ) ) {
					case 'boardRecent-type-A' : // boardRecent-type-A
						$.each( response.data, function( key ) {
							var postDate = response.data[key]["regist_date"].slice(0, 10);
							var postTime = response.data[key]["regist_date"].slice(11, 16);
							var postDateNumber = Number( postDate.split('-').join('') );

							boardRecentGon += '<ul class="tbody">';
							boardRecentGon += '<li class="item_subject">';

							if ( response.data[key]["secret_post"] ) {
								var writerType; // 비밀글 작성자 타입
								if ( response.data[key]["writer_type"] == 1 ) writerType = 'manager';
								else if ( response.data[key]["writer_type"] == 0 && response.data[key]["writer_id"] ) writerType = 'members';
								else writerType = 'non-members';
								boardRecentGon += '<a href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'" class="secretPost" data-writer-type="' + writerType + '" data-post-seq="' + response.data[key]["post_seq"] + '">';
							} else {
								boardRecentGon += '<a href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'">';
							}
							boardRecentGon += response.data[key]["post_subject"];
							boardRecentGon += ' <span class="post_icons">';
							if ( response.data[key]["secret_post"] ) boardRecentGon += '<span class="post_icon lock"></span>'; // 비밀글
							if ( response.data[key]["post_title_img"] ) boardRecentGon += '<span class="post_icon image"></span>';
							if ( response.data[key]["attach_yn"] == 'y' ) boardRecentGon += '<span class="post_icon addfile"></span>';
							if ( response.data[key]["comment_cnt"] >= 1 ) boardRecentGon += '<span class="post_icon comment">' + response.data[key]["comment_cnt"] + '</span>';
							if ( postDateNumber + 1 > todayYmdNumber ) boardRecentGon += '<span class="post_icon new"></span>';
							boardRecentGon += '</span>';
							boardRecentGon += '</a>';
							boardRecentGon += '</li>';
							if ( postDateNumber == todayYmdNumber ) boardRecentGon += '<li class="item_date">' + postTime + '</li>';
							else boardRecentGon += '<li class="item_date">' + postDate + '</li>';
							boardRecentGon += '</ul>';
						});
						break;
					case 'boardRecent-type-B' : // boardRecent-type-B
						$.each( response.data, function( key ) {
							var postDate = response.data[key]["regist_date"].slice(0, 10);
							var postTime = response.data[key]["regist_date"].slice(11, 16);
							var postDateNumber = Number( postDate.split('-').join('') );

							var writerType; // 비밀글 작성자 타입
							if ( response.data[key]["writer_type"] == 1 ) writerType = 'manager';
							else if ( response.data[key]["writer_type"] == 0 && response.data[key]["writer_id"] ) writerType = 'members';
							else writerType = 'non-members';

							boardRecentGon += '<div class="tbody">';
							boardRecentGon += '<ul class="section_image">';
							boardRecentGon += '<li>';
							if ( response.data[key]["secret_post"] ) {
								boardRecentGon += '<a class="board_image_wrap" href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'" class="secretPost" data-writer-type="' + writerType + '" data-post-seq="' + response.data[key]["post_seq"] + '">';
							} else {
								boardRecentGon += '<a class="board_image_wrap" href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'">';
							}
							if ( response.data[key]["post_title_img"] ) {
								boardRecentGon +=		'<span class="board_image" style="background-image:url(' + response.data[key]["post_title_img"] + ')"></span>';
							} else {
								boardRecentGon +=		'<span class="board_image no_image"></span>';
							}
							boardRecentGon +=		'</a>';
							boardRecentGon += '</li>';
							boardRecentGon += '</ul>';
							boardRecentGon += '<ul class="section_text">';
							boardRecentGon += '<li class="item_subject">';

							if ( response.data[key]["secret_post"] ) {
								boardRecentGon += '<a href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'" class="secretPost" data-writer-type="' + writerType + '" data-post-seq="' + response.data[key]["post_seq"] + '">';
							} else {
								boardRecentGon += '<a href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'">';
							}
							boardRecentGon += response.data[key]["post_subject"];
							boardRecentGon += ' <span class="post_icons">';
							if ( response.data[key]["secret_post"] ) boardRecentGon += '<span class="post_icon lock"></span>'; // 비밀글
							if ( response.data[key]["post_title_img"] ) boardRecentGon += '<span class="post_icon image"></span>';
							if ( response.data[key]["attach_yn"] == 'y' ) boardRecentGon += '<span class="post_icon addfile"></span>';
							if ( response.data[key]["comment_cnt"] >= 1 ) boardRecentGon += '<span class="post_icon comment">' + response.data[key]["comment_cnt"] + '</span>';
							if ( postDateNumber + 1 > todayYmdNumber ) boardRecentGon += '<span class="post_icon new"></span>';
							boardRecentGon += '</span>';
							boardRecentGon += '</a>';
							boardRecentGon += '</li>';

							boardRecentGon += '<li class="item_desc">';
							if ( response.data[key]["secret_post"] ) {
								boardRecentGon += '<a href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'" class="secretPost" data-writer-type="' + writerType + '" data-post-seq="' + response.data[key]["post_seq"] + '">';
							} else {
								boardRecentGon += '<a href="/boards/' + response.data[key]["post_seq"] + '/view'+(typeof _PATH === 'string' ? '?referer='+encodeURIComponent(_PATH) : '' )+'">';
							}
							boardRecentGon += response.data[key]["post_content"];
							boardRecentGon +=		'</a>';
							boardRecentGon += '</li>';

							 boardRecentGon += '<li class="item_writer">' + response.data[key]["disp_writer_name"] + '</li>';

							if ( postDateNumber == todayYmdNumber ) boardRecentGon += '<li class="item_date">' + postTime + '</li>';
							else boardRecentGon += '<li class="item_date">' + postDate + '</li>';
							boardRecentGon += '</ul>';
							boardRecentGon += '</div>';
						});
						break;

					case 'boardRecent-type-C' : // boardRecent-type-C
						$.each( response.data, function( key ) {
							boardRecentGon += '<ul class="tbody">';
							boardRecentGon +=	'<li class="item_subject">';
							boardRecentGon +=		'<a href="#none" class="faq_link">' + response.data[key]["post_subject"] + '</a>';
							boardRecentGon +=		'<div class="faq_contents">' + response.data[key]["post_content"] + '</div>';
							boardRecentGon +=	'</li>';
							boardRecentGon += '</ul>';
						});
						break;
				}

				$( moduleSelctor + ' .recent_board').html( boardRecentGon );

				if ( response.total > 0 ) {
					$( moduleSelctor + ' .recent_board').show();
					$( moduleSelctor + ' .no_data_area').hide();
				} else {
					$( moduleSelctor + ' .recent_board').hide();
					$( moduleSelctor + ' .no_data_area').html( '게시물이 없습니다.' ).show();
				}
			}
		},
		complete: function() {
			// 210628 폰트 스타일 추가
			moduleFontcolorRender( module_id );
			moduleFontfamilyRender( module_id );
		},
		error: function( res ) {
			console.log( res );
		}
	});
}

/* FAQ view */
function faqView( $selector ) {
	if ( $selector.next('.faq_contents').is(':hidden') ) {
		$selector.closest('.item_subject').addClass('on');
		$selector.next('.faq_contents').slideDown(300);
	} else {
		$selector.closest('.item_subject').removeClass('on');
		$selector.next('.faq_contents').slideUp(300);
	}
}

function checkSecretPostPass(obj, e){
	postWriterType = $(obj).data( 'writer-type' );
	secretPostSeq = $(obj).data( 'post-seq' );

	if ( postWriterType == 'non-members' ) {
		$('#secretContentsPopup input[name="secret_pw_input"]').val('');
		
		$.post('/boards/' + secretPostSeq + '/chkpass', function (res) {
			if(res.result === false){
				showCenterLayer( '#secretContentsPopup' );
			}else{
				secretPostPw = '';
				secretPostProcess( postWriterType, secretPostSeq, secretPostPw );
			}
		});
		
		$('#secretContentsPopup input[name="secret_pw_input"]').focus();
		e.preventDefault();
		e.stopPropagation();
	}

	if ( ( LOGIN_TYPE == 'non-members' || LOGIN_TYPE == 'manager' ) && postWriterType == 'members' ) {
		e.preventDefault();
		e.stopPropagation();
		alert( '권한이 없습니다.' );
	}

	return {'postWriterType':postWriterType, 'secretPostSeq':secretPostSeq};
}

$(function() {

	// 게시판 검색
	$('body').on('click', '[data-content-type1="board"] .board_top_area button[name="board_search_button"]', function() {
		var sBoardSelector = $(this).closest('[data-content-type1]');
		var sModuleID = sBoardSelector.parent('[data-module-id]').length ? sBoardSelector.parent('[data-module-id]').data('module-id') : sBoardSelector.parent('[data-minimodule-id]').data('minimodule-id');
		var sMmoduleSelctor = targetSelector( sModuleID );

		sBoardSelector.data( 'board-keyword-type', sBoardSelector.find('select[name="board_search_select"]').find('option:selected').val() );
		sBoardSelector.data( 'board-keyword-text', sBoardSelector.find('input[name="board_search_input"]').val() );

		boardOptionSetting( sModuleID );
		boardAjax( sModuleID, 1, false );
	});
	$('body').on('keypress', '[data-content-type1="board"] .board_search input[name="board_search_input"]', function(e) {
		var keyCode = e.keyCode || e.which;
		if ( keyCode === 13 ) {
			$(this).closest('.board_search_area').find('.search_btn').click();
		}
	});

	// 카테고리 검색
	$('body').on('change', '[data-content-type1="board"] .board_top_area select[name="board_category"]', function() {
		var sBoardSelector = $(this).closest('[data-content-type1]');
		var sModuleID = sBoardSelector.parent('[data-module-id]').length ? sBoardSelector.parent('[data-module-id]').data('module-id') : sBoardSelector.parent('[data-minimodule-id]').data('minimodule-id');

		sBoardSelector.data( 'board-keyword-type', 'category' );
		sBoardSelector.data( 'board-keyword-text', $(this).find('option:selected').val() );

		boardOptionSetting( sModuleID );
		boardAjax( sModuleID, 1, false );
	});

	// 비밀글 처리
	var postInfo, postWriterType, secretPostSeq, secretPostPw;
	$('body').on('click', '.item_subject a.secretPost, .section_image a.secretPost, .item_desc a.secretPost', function () {
		postInfo = checkSecretPostPass($(this), event);
		postWriterType = postInfo.postWriterType;
		secretPostSeq = postInfo.secretPostSeq;
	});
	$('#btnSecretPwConfirm').on('click', function() {
		secretPostPw = $('#secretContentsPopup input[name="secret_pw_input"]').val();
		secretPostProcess( postWriterType, secretPostSeq, secretPostPw );
	});
	$('input[name="secret_pw_input"]').on('keyup', function(e) {
		if ( e.keyCode == 13 ) {
			$('#btnSecretPwConfirm').click();
		}
	});


	// board, boardRecent -- auth view 처리
	$('body').on('click', '[data-content-type1="board"] .item_subject > a, [data-content-type1="boardRecent"] .item_subject > a, [data-content-type2="board-type-B"] .item_desc > a, [data-content-type2="boardRecent-type-B"] .item_desc > a, [data-content-type2="board-type-B"] a.board_image_wrap, [data-content-type2="boardRecent-type-B"] a.board_image_wrap', function(e) {
		if ( $(this).closest( '[data-auth-view]' ).attr( 'data-auth-view' ) == 1 ) {
			if ( LOGIN_TYPE == 'members' || LOGIN_TYPE == 'manager' ) {
				if ( $(this).closest( '[data-content-type2]' ).data( 'content-type2' ) == 'board-type-C' || $(this).closest( '[data-content-type2]' ).data( 'content-type2' ) == 'boardRecent-type-C' ) {
					faqView( $(this) );
				}
			} else {
				alert('로그인 후 이용가능한 서비스입니다.');
				e.preventDefault();
				e.stopPropagation();
			}
		} else if ( $(this).closest( '[data-auth-view]' ).attr( 'data-auth-view' ) == 2 ) {
			if ( LOGIN_TYPE == 'manager' ) {
				if ( $(this).closest( '[data-content-type2]' ).data( 'content-type2' ) == 'board-type-C' || $(this).closest( '[data-content-type2]' ).data( 'content-type2' ) == 'boardRecent-type-C' ) {
					faqView( $(this) );
				}
			} else {
				alert('권한이 없습니다.');
				e.preventDefault();
				e.stopPropagation();
			}
		} else {
			faqView( $(this) );
		}
	});

});
