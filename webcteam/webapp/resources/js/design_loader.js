var SPECIAL_PAGE_CODE = false; // 스페셜 페이지 코드 처리

if ( PAGE_CODE == 'login' || PAGE_CODE == 'forget' || PAGE_CODE == 'terms' || PAGE_CODE == 'policy' || PAGE_CODE == 'intro_members' || PAGE_CODE == 'dormancy_complete' || PAGE_CODE == 'dormancy_complete'|| PAGE_CODE == 'dormancy' ) {
	SPECIAL_PAGE_CODE = PAGE_CODE;
} else if ( PAGE_CODE == 'Join.join' ) {
	SPECIAL_PAGE_CODE = 'joinJoin';
} else if ( PAGE_CODE == 'Skin.categories.list' ) { // 카테고리
	SPECIAL_PAGE_CODE = 'category';
} else if ( PAGE_CODE == 'Skin.products.search' ) { // 검색
	SPECIAL_PAGE_CODE = 'search';
} else if ( PAGE_CODE == 'Skin.products.cart' ) { // 장바구니
	SPECIAL_PAGE_CODE = 'cart';
} else if ( PAGE_CODE == 'Skin.orders.create' ) { // 주문결제
	SPECIAL_PAGE_CODE = 'order';
} else if ( PAGE_CODE == 'Skin.mypages' ) { // 마이페이지
	SPECIAL_PAGE_CODE = 'mypage';
}



// 레이아웃 랜더링( 일반 )
function renderBlockLayout (data)
{
    var layout = $('<div></div>');

    layout.append('<div class="skin_block" data-module-id="" data-module-type="normal" data-is-empty="no"></div>');
    layout.find('div.skin_block').data('module-id', data.block_id).attr('data-module-id', data.block_id);
    //if (data.block_template.search(/^layout/) > -1) { // 210121 sjg
	if (data.module.content_type1 == 'layout') { // 콘텐츠 모듈 중 레이아웃 모듈로 구성된 경우로 인해 수정 --> 210121 sjg
        layout.find('div.skin_block').data('module-type', 'layout').attr('data-module-type', 'layout');
    } else {
        layout.find('div.skin_block').data('module-type', 'normal').attr('data-module-type', 'normal');
    }
    layout.find('div.skin_block').append(data.module.module_layout);

    if (data.childs) { // 미니모듈이 있는 경우
        var idx = 0;
        $.each(data.childs, function (k, v){
            layout.find('li').eq(idx).attr('data-minimodule-id', v.block_id);
            layout.find('li').eq(idx).append(v.module.module_layout);
            layout.find('li').eq(idx).find('div.normal_module').removeAttr('data-module-type');
            idx++;
        });
    }

    return layout.html();
}

// 레이아웃 랜더링( 스페셜 )
function renderBlockLayoutSpecial() {

	// 스페셜 블록 key 값 추출
	var special_block_key = 9999;
	var i = 0;
	$.each(BLOCK_LAYOUTS, function ( key, val ) {
		if ( key == 'module-' + SPECIAL_PAGE_CODE ) {
			special_block_key = i;
		}
		i++;
	});

	var j = 0;
	$.each(BLOCK_LAYOUTS, function ( key ) {
		var normalLayout = '<div class="skin_block" data-module-id="' + key + '" data-module-type="normal" data-is-empty="no"></div>';
		if ( j < special_block_key ) {
			$('#skinMainSection [data-module-id="module-' + SPECIAL_PAGE_CODE + '"]').before( normalLayout );
		} else if ( j > special_block_key ) {
			$('#skinMainSection').append( normalLayout );
		}
		j++;
	});

	$.each( BLOCK_LAYOUTS, function ( key, val ) {
		if ( key == 'module-' + SPECIAL_PAGE_CODE ) {
			// 스페셜 블록 : 참고) 저장하지 않으면 key 값은 없음
		} else {
			if ( BLOCK_LAYOUTS[key].module.content_type1 == 'layout' ) { // 콘텐츠 모듈 중 레이아웃 모듈로 구성된 경우로 인해 수정 --> 210121 sjg
				$('#skinMainSection').find( '[data-module-id=' + key + ']' ).data('module-type', 'layout').attr('data-module-type', 'layout');
			} else {
				$('#skinMainSection').find( '[data-module-id=' + key + ']' ).data('module-type', 'normal').attr('data-module-type', 'normal');
			}
			$('#skinMainSection').find( '[data-module-id=' + key + ']' ).html( BLOCK_LAYOUTS[key].module.module_layout );

			if ( BLOCK_LAYOUTS[key].childs ) { // 미니모듈이 있는 경우( 요건 테스트 더 해봐야함 )
				var idx = 0;
				$.each( BLOCK_LAYOUTS[key].childs, function ( k, v ) {
					$('#skinMainSection').find( '[data-module-id=' + key + ']' ).find('li').eq(idx).attr('data-minimodule-id', v.block_id);
					$('#skinMainSection').find( '[data-module-id=' + key + ']' ).find('li').eq(idx).append(v.module.module_layout);
					$('#skinMainSection').find( '[data-module-id=' + key + ']' ).find('li').eq(idx).find('div.normal_module').removeAttr('data-module-type');
					idx++;
				});
			}
		}
	});
}




// 컨텐츠 랜더링
function renderBlockContents (data)
{
    var result = $('<div></div>');

    // 저장될 html 적용
    if (data.html) {
        result.append(data.html);
    }

    // 옵션 처리
    if (data.options) {
        var opts = $.parseJSON(data.options);

        // 아래거 옵션 없는 경우 문제가 있어서 요걸로 수정 200721 sjg
		$.each(opts, function (k, v) {
			var gon_value;
			if ( v == undefined || v == null ) gon_value = '';
			else gon_value = v;
			if ( typeof( gon_value ) == 'object' ) gon_value = JSON.stringify( gon_value ); // 200901 게시판 data- object 처리
			$('[data-module-id="' + data.block_id + '"]').find( '[data-' + k + ']' ).data( k, gon_value ).attr( 'data-' + k, gon_value );
		});
    }

    return result.html();
}


$(document).ready(function() {

    /******** 헤더값 처리 *********/
    if (HEADER_CONFIG) {
        var idx = 0;

        // 헤더 타입
        if (HEADER_CONFIG.layout_type) {
            headerRender( 'layout_type', HEADER_CONFIG.layout_type );
        }

        // 플라잉 헤더
        if (HEADER_CONFIG.header_flying == 'yes') {
            headerRender( 'header_flying', true );
        } else {
            headerRender( 'header_flying', false );
        }

		// 헤더 플로팅
        if (HEADER_CONFIG.header_floating == 'yes') {
            headerRender( 'header_floating', true );
        } else {
            headerRender( 'header_floating', false );
        }

		// 헤더 모듈 name - 210208
        if (HEADER_CONFIG.header_module_name) {
            headerRender( 'header_module_name', HEADER_CONFIG.header_module_name );
        }
		// 헤더 모듈 parents - 210208
        if (HEADER_CONFIG.header_module_parents) {
            headerRender( 'header_module_parents', HEADER_CONFIG.header_module_parents );
        }

        // 선택 메뉴
        idx = 2;
        var selectMenuData = [];
        selectMenuData[0] = (HEADER_CONFIG.select_link_menu == 'yes') ? true : false;
        if (HEADER_CONFIG.setect_link) {
            HEADER_CONFIG.setect_link = $.parseJSON(HEADER_CONFIG.setect_link);
        }
        selectMenuData[1] = HEADER_CONFIG.select_link_title;
        $.each(HEADER_CONFIG.setect_link, function (k, v) {
            selectMenuData[idx] = [];
            selectMenuData[idx].push( v.name );
            selectMenuData[idx].push( v.address );
            selectMenuData[idx].push( v.target );
            idx++;
        });
        headerRender( 'select_link_menu', selectMenuData );

        // 로그인
        var loginMenuData = [];
        loginMenuData[0] = (HEADER_CONFIG.login_menu == 'yes') ? true : false;
        loginMenuData[1] = [];
        loginMenuData[1].push( HEADER_CONFIG.login_menu_name );
        loginMenuData[1].push( HEADER_CONFIG.logout_menu_name );
        loginMenuData[1].push( HEADER_CONFIG.join_menu_name );
        loginMenuData[1].push( HEADER_CONFIG.login_before_greeting );
        loginMenuData[1].push( HEADER_CONFIG.login_after_greeting );
        headerRender( 'login_menu', loginMenuData );

        // 유틸 메뉴
        idx = 1;
        var utilMenuData = [];
        utilMenuData[0] = (HEADER_CONFIG.util_menu == 'yes') ? true : false;
        if (HEADER_CONFIG.util_link) {
            HEADER_CONFIG.util_link = $.parseJSON(HEADER_CONFIG.util_link);
        }
        $.each(HEADER_CONFIG.util_link, function (k, v) {
            utilMenuData[idx] = [];
            utilMenuData[idx].push( v.name );
            utilMenuData[idx].push( v.address );
            utilMenuData[idx].push( (v.target == undefined) ? '_self' : v.target );
            idx++;
        });
        headerRender( 'util_menu', utilMenuData );

		headerBgColorRender(); // 헤더 BG color
		headerUtilColorRender(); // 유틸 컬러

        // 수정모드에서 옵션창 적용
        if (PAGE_MODE == 'edit') {
            parent.headerOptionRender(HEADER_CONFIG);
        }
    }

	headerFlyingStand(); // headerRender() 에서 옮겨옴 200813



	/********푸터값 처리 *********/
    if (FOOTER_CONFIG) {
		// 푸터 로고
		if ( FOOTER_CONFIG.footer_logo_src ) {
			var footerLogoData = [];
			footerLogoData[0] = ( FOOTER_CONFIG.footer_logo == 'yes' ) ? true : false;
			footerLogoData[1] = FOOTER_CONFIG.footer_logo_src;
			footerRender( 'footer_logo', footerLogoData );
		}

		// FNB 메뉴
		if ( FOOTER_CONFIG.footer_menu == 'yes' ) {
			$('[data-footer-menu]').data( 'footer-menu', 'yes' ).attr( 'data-footer-menu', 'yes' );
		} else {
			$('[data-footer-menu]').data( 'footer-menu', 'no' ).attr( 'data-footer-menu', 'no' );
		}
        if ( FOOTER_CONFIG.fnb_menu ) {
            menuSelectChange( 'fnb_menu', FOOTER_CONFIG.fnb_menu );
        }

		// SNS 링크
		if ( FOOTER_CONFIG.footer_sns == 'yes' ) {
			footerRender( 'footer_sns', true );
		} else {
			footerRender( 'footer_sns', false );
		}

		// 비지니스 정보
		if ( FOOTER_CONFIG.footer_info == 'yes' ) {
			footerRender( 'footer_info', true );
		} else {
			footerRender( 'footer_info', false );
		}

		// 사업자등록번호 링크
		if ( FOOTER_CONFIG.footer_coregistlink == 'yes' ) {
			footerRender( 'footer_coregistlink', true );
		} else {
			footerRender( 'footer_coregistlink', false );
		}

		// 호스팅 정보
		if ( FOOTER_CONFIG.footer_hosting == 'yes' ) {
			footerRender( 'footer_hosting', true );
		} else {
			footerRender( 'footer_hosting', false );
		}

		// 카피라이트
		if ( FOOTER_CONFIG.footer_copyright == 'yes' ) {
			$('[data-footer-copyright]').data( 'footer-copyright', 'yes' ).attr( 'data-footer-copyright', 'yes' );
		} else {
			$('[data-footer-copyright]').data( 'footer-copyright', 'no' ).attr( 'data-footer-copyright', 'no' );
		}
		if ( FOOTER_CONFIG.footer_copyright_source ) {
			$('#skinFooterSection .copyright').html( FOOTER_CONFIG.footer_copyright_source );
		}

		// 텍스트필드 1, 2
		if ( FOOTER_CONFIG.footer_textfield1 == 'yes' ) {
			$('[data-footer-textfield1]').data( 'footer-textfield1', 'yes' ).attr( 'data-footer-textfield1', 'yes' );
			$('#skinFooterSection .text_field1').html( FOOTER_CONFIG.footer_textfield1_source );
		} else {
			$('[data-footer-textfield1]').data( 'footer-textfield1', 'no' ).attr( 'data-footer-textfield1', 'no' );
		}

		if ( FOOTER_CONFIG.footer_textfield2 == 'yes' ) {
			$('[data-footer-textfield2]').data( 'footer-textfield2', 'yes' ).attr( 'data-footer-textfield2', 'yes' );
			$('#skinFooterSection .text_field2').html( FOOTER_CONFIG.footer_textfield2_source );
		} else {
			$('[data-footer-textfield2]').data( 'footer-textfield2', 'no' ).attr( 'data-footer-textfield2', 'no' );
		}

		// 푸터 이미지 메뉴( 에스크로 )
		if ( FOOTER_CONFIG.escro ) {
			var footerEscroData = [];
			idx = 1;
	        footerEscroData[0] = ( FOOTER_CONFIG.footer_escro == 'yes' ) ? true : false;
			FOOTER_CONFIG.escro = $.parseJSON( FOOTER_CONFIG.escro );
			$.each(FOOTER_CONFIG.escro, function (k, v) {
				footerEscroData[idx] = [];
				footerEscroData[idx].push( v.img_src );
				footerEscroData[idx].push( v.link_address );
				footerEscroData[idx].push( v.link_target );
				idx++;
			});
			footerRender( 'footer_escro', footerEscroData );
		}

		footerBgColorRender();
		//footerFontColorRender(); footerFontFamilyRender(); --> skin_render_menus.js ( 메뉴 로딩 이후에 적용되어야할 요소 있음 )

		$('#skinFooterSection .footer_module').show();
	}


	/* ***** 레이아웃 생성 ***** */
	//console.log(PAGE_CODE);
	//console.log(BLOCK_LAYOUTS);
	//console.log(BLOCK_CONTENTS);

	// 스페셜 모듈 내 저장된 값이 있는 경우 처리
	if ( (SPECIAL_PAGE_CODE || PAGE_CODE == 'intro_private') && BLOCK_LAYOUTS ) {
		// 서브페이지에서 저장한 값이 없으면 #startingBlock 노출
		$('#startingBlock, .startingBlock').remove();
	} else {
		$('#startingBlock, .startingBlock').show();
	}

	// 스페셜 블록 로딩 UI 개선
	$('#skinMainSection [data-module-type="special"]').show();

	if ( SPECIAL_PAGE_CODE ) {
		/* 스페셜 모듈인 경우 */
		renderBlockLayoutSpecial(); // --> 여기서 special 모듈은 제외시켜야 함
	} else {
		/* 일반 모듈인 경우 */
		if (BLOCK_LAYOUTS) {
			$.each(BLOCK_LAYOUTS, function (k, v) {
				var html = renderBlockLayout(v);
				$('div#skinMainSection').append(html);
			});
		}
	}
	/* ***** // 레이아웃 생성 ***** */


    /* ***** 컨텐츠 생성( 일반, 스페셜 ) ***** */
    if (BLOCK_CONTENTS) {
        $.each(BLOCK_CONTENTS, function (k, v) {
			if ( BLOCK_CONTENTS[k].block_id == 'module-' + SPECIAL_PAGE_CODE ) { // 스페셜모듈 옵션 적용
				var opts = $.parseJSON( BLOCK_CONTENTS[k].options );
				$.each( opts, function( key, val ) {
					var gon_value_sp;
					if ( val == undefined || val == null ) gon_value_sp = '';
					else gon_value_sp = val;
					$('[data-module-id="module-' + SPECIAL_PAGE_CODE + '"]').find( '[data-' + key + ']' ).data( key, gon_value_sp ).attr( 'data-' + key, gon_value_sp );
				});
			} else {
				var html = renderBlockContents(v);
				if (v.parent_block) {
					$("[data-minimodule-id='" + v.block_id + "']").find('.module_container').html(html);
				} else {
					$("[data-module-id='" + v.block_id + "']").find('.module_container').html(html);
				}
			}
        });		
    }

	if (PAGE_MODE == 'edit') {
		parent.moduleCounterExtract();
	}
	
	/* ***** // 컨텐츠 생성 ***** */

	$('.slider_before_loading').removeClass('slider_before_loading');
	slideMaxIndex(); // 슬라이드배너 도트 페이징 data 값 추가

	$( 'p[data-f-id="pbf"]' ).remove(); // 210210

	// 200922 - wow
	/*
	if ( $('#skinLayoutWrap').attr('data-wow-use') == 'yes' ) {
		setTimeout(function() {
			$('#skinMainWrap .skin_main_section .module_wrap').addClass('over_hide');
			$('#skinMainWrap .skin_main_section .module_container').addClass('wow');
			wowInit();
		}, 10);
	}
	*/

});
