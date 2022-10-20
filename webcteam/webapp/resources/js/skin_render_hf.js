/* **********************************************************
@@ 스킨 헤더 렌더 함수 @@
- optionName : 옵션명
- menuData : 옵션 데이터
- onlyDisplayChanged : 에디터 옵션 설정에서 디스플레이만 변경되었는지 여부( skin render 시 필요 없음 )
********************************************************** */
function headerRender( optionName, menuData, onlyDisplayChanged ) {
    switch ( optionName ) {
		/* -------- PC 레이아웃 타입 -------- */
        case 'header_logo' :
            var logoSelector = $('#skinHeaderSection #headerLogo');
            logoSelector.attr( src, menuData );
            break;

        /* -------- PC 레이아웃 타입 -------- */
        case 'layout_type' :
            $('[data-layout-type]').data( 'layout-type', menuData ).attr( 'data-layout-type', menuData );
            break;

		/* -------- 헤더 모듈 name -------- */
        case 'header_module_name' :
            $('#skinHeaderSection .header_module[data-module-name]').data( 'module-name', menuData ).attr( 'data-module-name', menuData );
            break;
		/* -------- 헤더 모듈 parents -------- */
        case 'header_module_parents' :
            $('#skinHeaderSection .header_module[data-module-parents]').data( 'module-parents', menuData ).attr( 'data-module-parents', menuData );
            break;
		/* -------- 헤더 서브메뉴 디자인 -------- */
        case 'header_submenu_design' :
            $('#skinHeaderSection .header_module[data-submenu-design]').data( 'submenu-design', menuData ).attr( 'data-submenu-design', menuData );
            break;
		/* -------- 헤더 서브메뉴 방향 -------- */
        case 'header_submenu_direction' :
            $('#skinHeaderSection .header_module[data-submenu-direction]').data( 'submenu-direction', menuData ).attr( 'data-submenu-direction', menuData );
            break;

        /* -------- 셀렉트 링크 메뉴 -------- */
        case 'select_link_menu' :
            if ( menuData[0] || menuData[0] == undefined ) $('[data-header-selectmenu]').data( 'header-selectmenu', 'yes' ).attr( 'data-header-selectmenu', 'yes' );
            else $('[data-header-selectmenu]').data( 'header-selectmenu', 'no' ).attr( 'data-header-selectmenu', 'no' );
            if ( onlyDisplayChanged ) break;
            $('#skinHeaderSection .select_link_area .defaule_text').text( menuData[1] );
            $('#skinHeaderSection .select_link_area .link_menu_list').empty();
            $.each( menuData, function( key ) {
                if ( key > 1 ) {
                    htmlMarkup = '<li><a href="' + menuData[key][1] + '" target="' + menuData[key][2] + '">' + menuData[key][0] + '</a></li>';
                    $('#skinHeaderSection .select_link_area .link_menu_list').append( htmlMarkup );
                }
            });
            break;

        /* -------- 로그인 메뉴 -------- */
        case 'login_menu' :
            if ( menuData[0] || menuData[0] == undefined ) $('[data-header-login]').data( 'header-login', 'yes' ).attr( 'data-header-login', 'yes' );
            else $('[data-header-login]').data( 'header-login', 'no' ).attr( 'data-header-login', 'no' );
            if ( onlyDisplayChanged ) break;
            var loginSelector = $('#skinHeaderSection .login_section');
            loginSelector.find('.login a').text( menuData[1][0] );
            loginSelector.find('.logout a').text( menuData[1][1] );
            loginSelector.find('.join a').text( menuData[1][2] );
            loginSelector.find('.greeting .before').text( menuData[1][3] );
            loginSelector.find('.greeting .after').html( '<span id="userID">' + USER + '</span>' + '<span id="greetingAfter">' + menuData[1][4] + '</span>' );
            break;

        /* -------- 유틸 메뉴 -------- */
        case 'util_menu' :
            if ( menuData[0] || menuData[0] == undefined ) $('[data-header-util]').data( 'header-util', 'yes' ).attr( 'data-header-util', 'yes' );
            else $('[data-header-util]').data( 'header-util', 'no' ).attr( 'data-header-util', 'no' );
            if ( onlyDisplayChanged ) break;
			var utilMenuCss = $('#skinHeaderSection .util_menu_list a').attr( 'style' ) ? $('#skinHeaderSection .util_menu_list a').attr( 'style' ) : '';
            $('#skinHeaderSection .util_menu_list').empty();
            $.each( menuData, function( key ) {
                if ( key > 0 ) {
					var linkTarget = ( !menuData[key][2] || menuData[key][2] == undefined ) ? '_self' : menuData[key][2];
                    htmlMarkup = '<li><a href="' + menuData[key][1] + '" target="' + linkTarget + '" style="' + utilMenuCss + '">' + menuData[key][0] + '</a></li>';
                    $('#skinHeaderSection .util_menu_list').append( htmlMarkup );
                }
            });
            break;

        /* -------- 헤더 flying -------- */
        case 'header_flying' :
            if ( menuData ) $('[data-header-flying]').data( 'header-flying', 'yes' ).attr( 'data-header-flying', 'yes' );
            else $('[data-header-flying]').data( 'header-flying', 'no' ).attr( 'data-header-flying', 'no' );
            break;

		/* -------- 헤더 floating -------- */
        case 'header_floating' :
            if ( menuData ) $('[data-header-floating]').data( 'header-floating', 'yes' ).attr( 'data-header-floating', 'yes' );
            else $('[data-header-floating]').data( 'header-floating', 'no' ).attr( 'data-header-floating', 'no' );
            break;

		/* -------- 헤더 백그라운드 컬러, 밝기 -------- */
        case 'header_bgcolor' :
			$('[data-header-bgcolor]').data( 'header-bgcolor', menuData ).attr( 'data-header-bgcolor', menuData );
			headerBgColorRender();
			break;
		case 'header_brightness' :
			$('[data-header-brightness]').data( 'header-brightness', menuData ).attr( 'data-header-brightness', menuData );
			break;

		/* -------- 헤더 유틸 컬러 -------- */
        case 'header_util_color' :
			//$('[data-header-utilcolor]').data( 'header-utilcolor', menuData ).attr( 'data-header-utilcolor', menuData );
			//headerUtilColorRender();
			break;

		/* -------- 카테고리 네비게이션 컬러, 폰트, 폰트사이즈 -------- */
        case 'navigation_color' :
			$('[data-header-utilcolor]').data( 'header-utilcolor', menuData ).attr( 'data-header-utilcolor', menuData );
			$('[data-navigation-color]').data( 'navigation-color', menuData ).attr( 'data-navigation-color', menuData );
			headerUtilColorRender();
			headerNavigationColorRender();
			break;
        case 'navigation_font' :
			$('[data-navigation-font]').data( 'navigation-font', menuData ).attr( 'data-navigation-font', menuData );
			headerNavigationFontRender();
			break;
		case 'navigation_fontsize' :
			$('[data-navigation-fontsize]').data( 'navigation-fontsize', menuData ).attr( 'data-navigation-fontsize', menuData );
			headerNavigationFontSizeRender();
			break;

		/* -------- [커머스] -------- */
		 case 'shopping_my' : // 마이페이지
			$('[data-shopping-my]').data( 'shopping-my', menuData ).attr( 'data-shopping-my', menuData );
            break;
        case 'shopping_cart' : // 장바구니
			$('[data-shopping-cart]').data( 'shopping-cart', menuData ).attr( 'data-shopping-cart', menuData );
            break;
        case 'shopping_search' : // 검색
			$('[data-shopping-search]').data( 'shopping-search', menuData ).attr( 'data-shopping-search', menuData );
            break;
		 case 'shopping_menuorder' : // 메뉴 순서
			$('[data-shopping-menuorder]').data( 'shopping-menuorder', menuData ).attr( 'data-shopping-menuorder', menuData );
            break;
    }
    //heaerUtilAreaDisplay(); // 헤더 유틸 영역 노출 여부 200703 CSS로 대체
	//headerFlyingStand(); // 헤더 플라잉 --> design_loader.js 에서 여러번 호출 오류
}

function headerBgColorRender() {
	var bgColor = $('#skinLayoutWrap').attr('data-header-bgcolor');
	$('#skinHeaderSection, #skinHeaderSection .header_module, #skinHeaderSection .search_layer').css( 'background-color', bgColor );
}
function headerUtilColorRender() {
	//var utilColor = $('#skinLayoutWrap').attr('data-header-utilcolor');
	var utilColor = $('#skinLayoutWrap').attr('data-navigation-color');
	$('#gnbUtilArea').find('.selectLinkMenu .defaule_text, .login_section .greeting, .login_section a, .util_menu_list a, .shopping_menu > li, input[name="search_input_text"]').css( 'color', utilColor );
}
function headerNavigationColorRender() {
	var naviColor = $('#skinLayoutWrap').attr('data-navigation-color');
	$('#GnbMenu > li > a').css( 'color', naviColor );
}
function headerNavigationFontRender() {
	var naviFont = $('#skinLayoutWrap').attr('data-navigation-font');
	$('#GnbMenu').css( 'font-family', naviFont );
	$('#gnbUtilArea .util_area .login_section > li > a, #gnbUtilArea .util_area .util_menu_list > li > a, #gnbUtilArea .util_area .login_section > li.greeting, #gnbUtilArea .shopping_menu').css( 'font-family', naviFont ); // old style 제거;
}
function headerNavigationFontSizeRender() {
	var naviFontSize = $('#skinLayoutWrap').attr('data-navigation-fontsize');
	$('#GnbMenu > li > a').css( 'font-size', naviFontSize + 'px' );
}
function headerCartNum( selector ) { // [커머스] 장바구니 수치
	$.ajax({
		url:'/cart/count',
		async: true,
		success : function( response ) {
			$( selector ).text( response.data );
		},
		error: function( res ) {
			$( selector ).text( '0' );
		}
	});
}
headerCartNum( '#cartNum' ); // 빠른 로딩 처리



/* **********************************************************
@@ 스킨 푸터 렌더 함수 @@
- optionName : 옵션명
- menuData : 옵션 데이터
- onlyDisplayChanged : 에디터 옵션 설정에서 디스플레이만 변경되었는지 여부( skin render 시 필요 없음 )
********************************************************** */
function footerRender( optionName, menuData, onlyDisplayChanged ) {
    switch ( optionName ) {
		/* -------- 푸터 모듈 name -------- */
        case 'footer_module_name' :
            $('#skinFooterSection .footer_module[data-module-name]').data( 'module-name', menuData ).attr( 'data-module-name', menuData );
            break;
		/* -------- 헤더 모듈 parents -------- */
        case 'header_module_parents' :
            $('#skinFooterSection .footer_module[data-module-parents]').data( 'module-parents', menuData ).attr( 'data-module-parents', menuData );
            break;

		/* -------- Footer logo -------- */
        case 'footer_logo' :
            if ( menuData[0] || menuData[0] == undefined ) $('[data-footer-logo]').data( 'footer-logo', 'yes' ).attr( 'data-footer-logo', 'yes' );
            else $('[data-footer-logo]').data( 'footer-logo', 'no' ).attr( 'data-footer-logo', 'no' );
            if ( onlyDisplayChanged ) break;
			var logoSelector = $('#skinFooterSection #footerLogo');
            logoSelector.attr( 'src', menuData[1] );
            break;

		/* -------- Footer menu -------- */
        case 'footer_menu' :
            if ( menuData[0] || menuData[0] == undefined ) $('[data-footer-menu]').data( 'footer-menu', 'yes' ).attr( 'data-footer-menu', 'yes' );
            else $('[data-footer-menu]').data( 'footer-menu', 'no' ).attr( 'data-footer-menu', 'no' );
            break;

		/* -------- Footer SNS -------- */
        case 'footer_sns' :
            if ( menuData || menuData == undefined ) $('[data-footer-sns]').data( 'footer-sns', 'yes' ).attr( 'data-footer-sns', 'yes' );
            else $('[data-footer-sns]').data( 'footer-sns', 'no' ).attr( 'data-footer-sns', 'no' );
            break;

		/* -------- Footer info -------- */
        case 'footer_info' :
            if ( menuData || menuData == undefined ) $('[data-footer-info]').data( 'footer-info', 'yes' ).attr( 'data-footer-info', 'yes' );
            else $('[data-footer-info]').data( 'footer-info', 'no' ).attr( 'data-footer-info', 'no' );
            break;

		/* -------- Footer 사업자등록번호 링크 -------- */
        case 'footer_coregistlink' :
            if ( menuData || menuData == undefined ) $('[data-footer-coregistlink]').data( 'footer-coregistlink', 'yes' ).attr( 'data-footer-coregistlink', 'yes' );
            else $('[data-footer-coregistlink]').data( 'footer-coregistlink', 'no' ).attr( 'data-footer-coregistlink', 'no' );
            break;

		/* -------- Footer hosting -------- */
        case 'footer_hosting' :
            if ( menuData || menuData == undefined ) $('[data-footer-hosting]').data( 'footer-hosting', 'yes' ).attr( 'data-footer-hosting', 'yes' );
            else $('[data-footer-hosting]').data( 'footer-hosting', 'no' ).attr( 'data-footer-hosting', 'no' );
            break;

		/* -------- Footer escro -------- */
        case 'footer_escro' :
            if ( menuData[0] || menuData[0] == undefined ) $('[data-footer-escro]').data( 'footer-escro', 'yes' ).attr( 'data-footer-escro', 'yes' );
            else $('[data-footer-escro]').data( 'footer-escro', 'no' ).attr( 'data-footer-escro', 'no' );
            if ( onlyDisplayChanged ) break;
			$('#skinFooterSection .escro_area > ul').empty();
            $.each( menuData, function( key ) {
                if ( key > 0 ) {
                    htmlMarkup = '<li><a href="' + menuData[key][1] + '" target="' + menuData[key][2] + '"><img src="' + menuData[key][0] + '" alt=""></a></li>';
                    $('#skinFooterSection .escro_area > ul').append( htmlMarkup );
                }
            });
            break;

		/* -------- Footer copyright -------- */
        case 'footer_copyright' :
            if ( menuData[0] || menuData[0] == undefined ) $('[data-footer-copyright]').data( 'footer-copyright', 'yes' ).attr( 'data-footer-copyright', 'yes' );
            else $('[data-footer-copyright]').data( 'footer-copyright', 'no' ).attr( 'data-footer-copyright', 'no' );
            break;

		/* -------- Footer Text Field -------- */
        case 'footer_textfield1' :
            $('[data-footer-textfield1]').data( 'footer-textfield1', menuData[0] ).attr( 'data-footer-textfield1', menuData[0] );
            break;

		 case 'footer_textfield2' :
            $('[data-footer-textfield2]').data( 'footer-textfield2', menuData[0] ).attr( 'data-footer-textfield2', menuData[0] );
            break;


		/* -------- Footer Style - 210621 -------- */
        case 'footer_bgcolor2' :
			$('[data-footer-bgcolor2]').data( 'footer-bgcolor2', menuData ).attr( 'data-footer-bgcolor2', menuData );
			footerBgColorRender();
			break;
		case 'footer_brightness' :
			$('[data-footer-brightness]').data( 'footer-brightness', menuData ).attr( 'data-footer-brightness', menuData );
			break;
        case 'footer_fontcolor' :
			$('[data-footer-fontcolor]').data( 'footer-fontcolor', menuData ).attr( 'data-footer-fontcolor', menuData );
			footerFontColorRender();
			break;
        case 'footer_fontfamily' :
			$('[data-footer-fontfamily]').data( 'footer-fontfamily', menuData ).attr( 'data-footer-fontfamily', menuData );
			footerFontFamilyRender();
			break;
	}
}

function footerBgColorRender() {
	var bgColor = $('#skinFooterSection').attr('data-footer-bgcolor2');
	$('#skinFooterSection .footer_module').css( 'background-color', bgColor );
}
function footerFontColorRender() {
	var footerFontColor;
	if ( $('#skinFooterSection').attr('data-footer-fontcolor') == 'none' ) {
		footerFontColor = '';
	} else {
		footerFontColor = $('#skinFooterSection').attr('data-footer-fontcolor');
	}
	$('#FnbMenu, #FnbMenu > li > a, #footerCompanyInfo span, #skinFooterSection .copyright p, #skinFooterSection .text_field p').css( 'color', footerFontColor );
}
function footerFontFamilyRender() {
	var footerFontFamily;
	if ( $('#skinFooterSection').attr('data-footer-fontfamily') == 'none' ) {
		footerFontFamily = '';
	} else {
		footerFontFamily = $('#skinFooterSection').attr('data-footer-fontfamily');
	}
	$('#FnbMenu, #footerCompanyInfo, #skinFooterSection .copyright p, #skinFooterSection .text_field p').css( 'font-family', footerFontFamily );
}


/* **********************************************************
@@ 헤더 모듈 교체 함수( 에디터단에서 모듈 변경시 처리됨 ) @@
********************************************************** */
function moduleHeaderRender( module_name, module_parents, module_type, hf_options, submenu_design, submenu_direction ) {
	var layoutType;
	if ( module_type == 'header-type-A' ) layoutType = 'type1';
	else if ( module_type == 'header-type-B' ) layoutType = 'type2';

	$('#skinLayoutWrap').attr( 'data-layout-type', layoutType );
	$('#skinHeaderSection .header_module').attr( 'data-module-parents', module_parents );
	$('#skinHeaderSection .header_module').attr( 'data-module-name', module_name );
	$('#skinHeaderSection .header_module').attr( 'data-submenu-design', submenu_design );
	$('#skinHeaderSection .header_module').attr( 'data-submenu-direction', submenu_direction );

	$.each(hf_options, function( k ) {
		$('#skinLayoutWrap').attr( 'data-' + k, hf_options[k] );
	});

	setTimeout(function() {
		headerBgColorRender();
		headerUtilColorRender();
		headerNavigationColorRender();
		headerNavigationFontRender();
		headerNavigationFontSizeRender();

		headerFlyingStand(210);
		type2ContentsHeight();
	}, 20);
}




/* **********************************************************
@@ 푸터 모듈 교체 함수( 에디터단에서 모듈 변경시 처리됨 ) @@
********************************************************** */
function moduleFooterRender( module_name, module_parents, hf_options ) {
	$('#skinFooterSection .footer_module').attr( 'data-module-parents', module_parents );
	$('#skinFooterSection .footer_module').attr( 'data-module-name', module_name );

	$.each(hf_options, function( k ) {
		$('#skinFooterSection').attr( 'data-' + k, hf_options[k] );
	});

	setTimeout(function() {
		footerBgColorRender();
		footerFontColorRender();
		footerFontFamilyRender();
	}, 20);
}