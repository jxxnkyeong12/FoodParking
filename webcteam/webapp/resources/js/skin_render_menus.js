/* GNB 메뉴 클릭 모바일 */
function menuMobileClick() {
	$('#GnbMenu, #FnbMenu').on('click', '.btn_depth2_view', function() {
		if ( $(this).next('.navi_depth2_wrap').is(':hidden') ) {
			$('#GnbMenu .navi_depth2_wrap, #FnbMenu .navi_depth2_wrap').slideUp(200);
			$('#GnbMenu > li, #FnbMenu > li').removeClass('opened');

			$(this).next('.navi_depth2_wrap').slideDown(200);
			$(this).closest('li').addClass('opened');
		} else {
			$(this).next('.navi_depth2_wrap').slideUp(200);
			$(this).closest('li').removeClass('opened');
		}
	});
	$('#GnbMenu, #FnbMenu').on('click', '.btn_depth3_view', function() {
		if ( $(this).next('.navi_depth3').is(':hidden') ) {
			$(this).next('.navi_depth3').slideDown(200);
			$(this).addClass('opened');
		} else {
			$(this).next('.navi_depth3').slideUp(200);
			$(this).removeClass('opened');
		}
	});
}

/* 에디터에서 메뉴 변경 처리 */
function menuSelectChange( menu_name, menu_seq ) {
	switch ( menu_name ) {
		case 'gnb_menu' :
			$('#skinLayoutWrap').data( 'gnb-seq', menu_seq ).attr( 'data-gnb-seq', menu_seq );
			menuRender( '#GnbMenu' );
			break;
		case 'fnb_menu' :
			$('#skinFooterSection').data( 'fnb-seq', menu_seq ).attr( 'data-fnb-seq', menu_seq );
			menuRender( '#FnbMenu' );
			break;
		case 'mypage_menu_select' :
			$('#skinLayoutWrap').data( 'mypage-menu', menu_seq ).attr( 'data-mypage-menu', menu_seq );
			menuRender( '#MypageMenu' );
			break;
	}
}

/* 메뉴 렌더링 */
function menuRender( menu_Id ) {
	var menuSeq = 1;
	switch ( menu_Id ) {
		case '#GnbMenu' :
			menuSeq = $('#skinLayoutWrap').attr( 'data-gnb-seq' );
			break;
		case '#FnbMenu' :
			menuSeq = $('#skinFooterSection').attr( 'data-fnb-seq' );
			break;
		case '#MypageMenu' :
			menuSeq = $('#skinLayoutWrap').attr( 'data-mypage-menu' );
			break;
	}
	menuRenderAjax( menu_Id, menuSeq );
}

/* 링크주소 추출 210503 */
function linkUrlExt( link_type, link_url ) {
	var linkUrl;
	if ( link_type ) {
		linkUrl = '/' + link_type
        if (link_type == 'categories' && link_url) {
            linkUrl = linkUrl + '/' + link_url;
        }else if (link_type == 'call' && link_url) {
			linkUrl = link_url ;
		}
	} else {
		linkUrl = link_url;
		if ( !linkUrl ) {
			linkUrl = '#none';
		}
	}
	return linkUrl;
}

/* 메뉴 렌더딩 데이터 연동 */
function menuRenderAjax( menu_selector, menu_seq ) {
	$.ajax({
		url:'/gnb/' + menu_seq,
		dataType:'json',
		async: true,
		success : function( response ) {
			if ( response.gnb.gnb_name == undefined ) {
				var noMenuHtml = '<span class="editor_display">디자인 블록 설정에서 <span style="display:inline-block;">메뉴를 선택하세요.</span></span>';
				if ( menu_selector == '#MypageMenu' ) {
					$( menu_selector ).html( '<div>' + noMenuHtml + '</div>' );
				} else {
					$( menu_selector ).html( '<li>' + noMenuHtml + '</li>' );
				}
			} else {
				var menuArray = response.gnb.menus;
				var linkURL1, linkURL2, linkURL3, linkTarget1, linkTarget2, linkTarget3, fnCallLink1, fnCallLink2,fnCallLink3;
				var menuGon = '';
				if ( menu_selector == '#MypageMenu' ) { /* --------- 마이페이지인 경우 --------- */
					$.each( menuArray, function( key ) {
						linkURL1 = linkUrlExt( menuArray[key].link_type, menuArray[key].link_url ); // 링크주소 추출
						linkTarget1 = ( menuArray[key].open_window == 1 ) ? 'target="_blank"' : '';
						fnCallLink1 = ( menuArray[key].link_type === 'call') ? 'onclick="callLink(this)"' : '';
						menuGon += '<div class="lnb_section">';
						menuGon += 	'<p class="lnb_title"><a href="' + linkURL1 + '" ' + linkTarget1 + ' '+ fnCallLink1 +'>' + menuArray[key].menu_name + '</a></p>';
						if ( menuArray[key].childs ) { // 2뎁스
							menuGon += 	'<ul class="lnb_menu">';
							$.each( menuArray[key].childs, function( key2 ) {
								linkURL2 = linkUrlExt( menuArray[key].childs[key2].link_type, menuArray[key].childs[key2].link_url );
								linkTarget2 = ( menuArray[key].childs[key2].open_window == 1 ) ? 'target="_blank"' : '';
								fnCallLink2 = ( menuArray[key].childs[key2].link_type === 'call') ? 'onclick="callLink(this)"' : '';
								menuGon += 		'<li class="lnb">';
								menuGon += 			'<a href="' + linkURL2 + '" ' + linkTarget2 + ' '+ fnCallLink2 +'>' + menuArray[key].childs[key2].menu_name + '</a>';
								if ( menuArray[key].childs[key2].childs ) { // 3뎁스
									menuGon += 			'<ul class="depth3">';
									$.each( menuArray[key].childs[key2].childs, function( key3 ) {
										linkURL3 = linkUrlExt( menuArray[key].childs[key2].childs[key3].link_type, menuArray[key].childs[key2].childs[key3].link_url );
										linkTarget3 = ( menuArray[key].childs[key2].childs[key3].open_window == 1 ) ? 'target="_blank"' : '';
										fnCallLink3 = ( menuArray[key].childs[key2].childs[key3].link_type === 'call') ? 'onclick="callLink(this)"' : '';
										menuGon += 				'<li><a href="' + linkURL3 + '" ' + linkTarget3 + ' '+ fnCallLink3 +'>' + menuArray[key].childs[key2].childs[key3].menu_name + '</a></li>';
									});
									menuGon += 			'</ul>';
								}
								menuGon += 		'</li>';
							});
							menuGon += 	'</ul>';
						}
						menuGon += '</div>';
					});
				} else { /* --------- 그외인 경우 --------- */
					$.each( menuArray, function( key ) {
						linkURL1 = linkUrlExt( menuArray[key].link_type, menuArray[key].link_url ); // 링크주소 추출
						linkTarget1 = ( menuArray[key].open_window == 1 ) ? 'target="_blank"' : '';
						fnCallLink1 = ( menuArray[key].link_type === 'call') ? 'onclick="callLink(this)"' : '';
						menuGon += '<li>';
						menuGon += 	'<a href="' + linkURL1 + '" ' + linkTarget1 + ' '+ fnCallLink1 +'>' + menuArray[key].menu_name + '</a>';
						if ( menuArray[key].childs ) { // 2뎁스
							menuGon += '<button type="button" class="btn_depth2_view"></button>';
							menuGon += '<div class="navi_depth2_wrap">';
							menuGon += 	'<div class="navi_depth2_container">';
							menuGon += 		'<ul class="navi_depth2">';
							$.each( menuArray[key].childs, function( key2 ) {
								linkURL2 = linkUrlExt( menuArray[key].childs[key2].link_type, menuArray[key].childs[key2].link_url );
								linkTarget2 = ( menuArray[key].childs[key2].open_window == 1 ) ? 'target="_blank"' : '';
								fnCallLink2 = ( menuArray[key].childs[key2].link_type === 'call') ? 'onclick="callLink(this)"' : '';
								menuGon += 		'<li>';
								if ( menuArray[key].childs[key2].childs ) { // 3뎁스
									menuGon +=		'<button type="button" class="btn_depth3_view"></button>';
									menuGon += 		'<ul class="navi_depth3">';
									$.each( menuArray[key].childs[key2].childs, function( key3 ) {
										linkURL3 = linkUrlExt( menuArray[key].childs[key2].childs[key3].link_type, menuArray[key].childs[key2].childs[key3].link_url );
										linkTarget3 = ( menuArray[key].childs[key2].childs[key3].open_window == 1 ) ? 'target="_blank"' : '';
										fnCallLink3 = ( menuArray[key].childs[key2].childs[key3].link_type === 'call') ? 'onclick="callLink(this)"' : '';
										menuGon += 		'<li><a href="' + linkURL3 + '" ' + linkTarget3 + ' '+ fnCallLink3 +'>' + menuArray[key].childs[key2].childs[key3].menu_name + '</a></li>';
									});
									menuGon += 		'</ul>';
								}
								
								menuGon +=			'<a href="' + linkURL2 + '" ' + linkTarget2 + ' '+ fnCallLink2 +'>' + menuArray[key].childs[key2].menu_name + '</a>';
								menuGon +=		'</li>';
							});
							menuGon += 		'</ul>';
							menuGon += 	'</div>';
							menuGon += '</div>';
						}
						menuGon += '</li>';
					});
				}

				$( menu_selector ).html( menuGon );
			}
		},
		complete : function() {
			

			switch ( menu_selector ) {
				case '#GnbMenu' :
					headerNavigationColorRender(); // 카테고리 네비게이션 컬러
					headerNavigationFontRender(); // 카테고리 네비게이션 폰트 패밀리
					headerNavigationFontSizeRender(); // 카테고리 네비게이션 폰트 사이즈
					headerFlyingStand();
					break;
				case '#FnbMenu' :
					footerFontColorRender(); // 푸터 폰트컬러
					footerFontFamilyRender(); // 푸터 폰트패밀리
					break;
				case '#MypageMenu' :
					mypageActiveOn( '#MypageMenu' ); // 메뉴 활성화
					break;
			}
		}
	});
}

/* 메뉴 활성화 */
function mypageActiveOn( menu_selector ) {
	var url = window.location.pathname;
	var url2;
	$(menu_selector).find('a').each(function() {
		url2 = $(this).attr('href');
		if ( url.indexOf(url2) > -1 ) {
			$(this).parent().addClass('on');
		}
	});
}


$(function() {
	menuMobileClick(); // 메뉴 모바일 클릭

	menuRender( '#GnbMenu' );
	menuRender( '#FnbMenu' );
	if ( PAGE_CODE == 'Skin.mypages' ) menuRender( '#MypageMenu' );

	// 푸터 메뉴가 다중인 경우 햄버거 클릭시
	$('#fnbOpenrBtn').on('click', function() {
		if ( $(this).hasClass('on') ) {
			$(this).removeClass('on');
			$(this).next('.fnb_container').slideUp(200);
		} else {
			$(this).addClass('on');
			$(this).next('.fnb_container').slideDown(200);
		}
	});

});