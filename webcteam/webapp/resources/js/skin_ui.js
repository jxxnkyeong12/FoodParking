var respBreakPoint = 1024;
var flyingBreakPoint = 40;
var modal_bg = '<div id="modal_bg"></div>';
var winWidth = $(window).width();

$(function() {

	// 모듈 렌더링( 문서 시작시 저장된 데이터 기반 )
	moduleBackgroundRender();
	//moduleVideoRender(); --> module_contents 기반으로 변경되어 필요 없음
	$('[data-content-type1="map"]').each(function() { moduleMapRender($(this));})	
	if ( $('[data-content-type1="line"]').length ) moduleLineRender();
	if ( $('[data-content-type1="slide"]').length ) moduleSlideRender();
	if ( $('[data-content-type1="form"]').length ) moduleFormRender();
	if ( $('[data-content-type1="gallery"]').length ) moduleGalleryRender();
	if ( $('[data-content-type1="board"]').length ) moduleBoardRender();
	if ( $('[data-content-type1="boardRecent"]').length ) moduleBoardRecentRender();
	if ( $('[data-content-type1="builderProductA"]').length ) productGroupRender();

	// 게시판 타입 data-auth-view, data-auth-write 설정
	if ( $('[data-content-type1="board"], [data-content-type1="boardRecent"]').length ) {
		boardAuthViewWrite(); // --> skin_render_board.js
	}

	//gnbDepth1generation(); // 모바일 GNB 서브메뉴에 1뎁스 메뉴 추가
	//gnbMobileClick(); // 모바일 GNB 메뉴 클릭
	// --> skin_render_menus.js

	//  스킨 바닥 아무데나 클릭시 리셋시킬 것들
	$(document).on('click', function() {
		$('.selectLinkMenu').removeClass('on'); // 디자인셀렉트링크메뉴 초기화
		$('.board_search_area').removeClass('on');
	});

	// 어사이드 열기 클릭
	$('#asideOpenrBtn').on('click', function() {
		$(this).addClass('on');
		$('#skinHeaderSection .header_module').append( modal_bg );
		$('#gnbUtilArea').addClass('on');
		$('#asideCloseBtn').addClass('on');
		//setTimeout(function() { $('body').css( 'overflow', 'hidden' ) }, 500);
		return false;
	});

	// 어사이드 닫기, 모달 클릭
	$('body').on('click', '#modal_bg, #asideCloseBtn', function() {
		$('#gnbUtilArea').removeClass('on');
		$('#asideCloseBtn').removeClass('on');
		//$('body').css( 'overflow', '' );
		setTimeout(function() {
			$('#modal_bg').remove();
			$('#asideOpenrBtn').removeClass('on');
		}, 500);
		return false;
	});

	/*
	$('#gnbUtilArea').on('click', function() {
		event.stopPropagation();
	});
	*/

	// 셀렉트 링크 메뉴
	$('.selectLinkMenu .defaule_text').on('click', function() {
		if ( $(this).closest('.selectLinkMenu').hasClass('on') ) {
			 $(this).closest('.selectLinkMenu').removeClass('on');
		} else {
			 $(this).closest('.selectLinkMenu').addClass('on');
		}
		return false;
	});
	$('.selectLinkMenu').on('click', function() {
		if ( !$('body').hasClass('edit_mode') ) {
			event.stopPropagation();
		}
	});

	// 슬라이드 배너 관련
	$('[data-slide-motion="vertical"] .slide_banner').on('init', function() {
		$('[data-slide-motion="vertical"] .slide_banner').find('img').one('load', function() {
			$('[data-slide-motion="vertical"] .slide_banner').slick('slickGoTo', 0);
		});
	});

	$('[data-content-type1="slide"]').on('click', '.slick-arrow, .slick-dots', function() {
		$(this).closest('.slide_banner').find('.css_ani_stop').removeClass('css_ani_stop');
	});

	// 게시판 검색 - 입력 박스 포커스 UI( Mobile )
	$('body').on('click', '.board_search_area', function() {
		if ( $(this).prev('.board_category_area').is(':visible') && window.innerWidth < 600 ) {
			$(this).addClass('on');
		}
		event.preventDefault();
		event.stopPropagation();
		return false;
	});

	// #57771 푸터 카피라이트 영역 글자 입력 오류 개선
	if ( $('#skinFooterSection .footer_section.copyright').find('[contenteditable]').length ) {
		$('#skinFooterSection .footer_section.copyright').html( $('#skinFooterSection .footer_section.copyright p').parent().html() );
	}

	// [커머스] 헤더 검색 Open 버튼
	$('#skinHeaderSection .btn_search_open').on('click', function() {
		$('#skinHeaderSection .search_layer').addClass( 'on' );
		$('#skinHeaderSection input[name="search_input_text"]').focus();
	});
	// [커머스] 헤더 검색 Close 버튼
	$('#skinHeaderSection .btn_search_close').on('click', function() {
		$('body').hasClass('edit_mode')
		$('#skinHeaderSection .search_layer').removeClass( 'on' );
	});
	// [커머스] 헤더 상품 검색
	$('#skinHeaderSection .btn_search_header').on('click', function() {
		if ( $('body').hasClass('edit_mode') ) {
			alert( window.i18n.skin_js.msg.editormode_not_support );
			return false;
		}
		var searchKeyword = $('#skinHeaderSection input[name="search_input_text"]').val();
		if ( searchKeyword ) {
			var linkUrl = '/search?search_keyword=' + searchKeyword;
			location.href = linkUrl;
		} else {
			alert( '검색어를 입력하세요.' );
		}
	});
	// [커머스] 헤더 상품 검색창 엔터키
	$('#skinHeaderSection input[name="search_input_text"]').on('keydown', function( e ) {
		if( e.keyCode == 13 ) {
			$('#skinHeaderSection .btn_search_header').click();
		}
	});

});

var controlPannelPos = 0;
$( window ).scroll(function() {
	// 스크롤시 헤더 플라잉
	if ( $('#skinLayoutWrap').attr('data-header-flying') == 'yes' ) {
		/*
		if ( $(document).scrollTop() > 1 ) {
			$('#skinHeaderSection').find('.header_module').addClass('flying_z');
		} else {
			$('#skinHeaderSection').find('.header_module').removeClass('flying_z');
			//if ( $('body').hasClass('edit_mode') && $('#flyingStand').length ) { // 에디터 모드에서 data-header-floating="yes", data-header-flying="yes" 인 경우 따라다니다가 내부에서 모듈이 추가된 경우 flyingStand 크기 재계산 - 210522
			if ( $('#flyingStand').length ) { // 실사이트 페이지가 중간부터 시작된 경우, 스크롤Top 에서 재계산
				headerFlyingStand( 210, true );
			}
		}
		if ( $(document).scrollTop() > flyingBreakPoint ) {
			$('#skinHeaderSection').find('.header_module').addClass('flying');
		} else {
			$('#skinHeaderSection').find('.header_module').removeClass('flying');
		}
		*/

		if ( $(document).scrollTop() > flyingBreakPoint ) {
			$('#skinHeaderSection').find('.header_module').addClass('flying');
			$('#skinHeaderSection').find('.header_module').addClass('flying_z');
		} else {
			$('#skinHeaderSection').find('.header_module').removeClass('flying');
			$('#skinHeaderSection').find('.header_module').removeClass('flying_z');
		}
		if ( $(document).scrollTop() < 1 && ('#flyingStand').length ) {
			headerFlyingStand( 210, true );
		}
	} else {
		$('#skinHeaderSection').find('.header_module').removeClass('flying_z flying');
	}

	// 에디터 모드에서 스크롤 시, 컨트롤 패널 위치 변경
	if ( $('body').hasClass('edit_mode') && $('.btn_module_section_control').length && !$('.btn_module_section_control').closest('#skinHeaderSection').length && !$('.btn_module_section_control').closest('#skinFooterSection').length ) {
		if ( controlPannelPos == 0) {
			controlPannelPos = $('.btn_module_section_control').offset().top;
		}
		var scrollPos = $(document).scrollTop();
		if ( controlPannelPos < scrollPos + 180 ) {
			$('.btn_module_section_control').addClass('fix');
		} else {
			$('.btn_module_section_control').removeClass('fix');
			controlPannelPos = 0;
		}
	}


	// 상/하단 바로가기 버튼
	if(winWidth < respBreakPoint ){
		var winPos = $(document).scrollTop();
		if( winPos > 200 ){
			if( !$('#btnBott').hasClass('show') ){
				$('#btnBott').addClass('show');
				// 무료배너
				if( $('.level1_bnr').length ) $('.level1_bnr').addClass('move');
			}
		}
		else{
			if( $('#btnBott').hasClass('show') ) {
				$('#btnBott').removeClass('show');
				// 무료배너
				if( $('.level1_bnr').length ) $('.level1_bnr').removeClass('move');
			}
		}
	}

	

	//주문페이지 총 결제금액 플러팅
	if( $("#div-orderer-info").length == 0) return;

	var layoutType = $('#skinLayoutWrap').attr('data-layout-type');
	var headerFlying = $('#skinLayoutWrap').attr('data-header-flying');
	var paymentFlyingStartPosY = $("#div-orderer-info").offset().top - $(".header_module").height() - flyingBreakPoint;
	var paymentFlyingEndPosY = $(document).innerHeight()-$(window).innerHeight()-$("#pageConfigFooter").height();
	var flyingModePaymentTop = 20;	

	if(layoutType=="type2") paymentFlyingStartPosY =  $("#div-orderer-info").offset().top - flyingBreakPoint;	
	if(headerFlying == 'yes' && layoutType == "type1") flyingModePaymentTop = $(".header_module").height() + flyingBreakPoint;
	
	if ( $(document).scrollTop() > paymentFlyingStartPosY) {
		$("#div-orderer-info").addClass("flying");
		
		//결제금액 하단 고정
		if($(document).scrollTop() > paymentFlyingEndPosY){			
			$("#div-orderer-info").addClass("bottomFix");
		}else{
			$("#div-orderer-info").removeClass("bottomFix");
			$(".payment_info.flying .price_area").css("top", flyingModePaymentTop)
		}
	}else{		
		$("#div-orderer-info").removeClass("flying bottomFix");
		$(".payment_info.flying .price_area").css("top","auto");
	}

	if($(document).innerWidth() < 1024) $("#div-orderer-info").removeClass("flying bottomFix");
});

$(window).on('load', function(){
	$('.slider_before_loading').removeClass('slider_before_loading');

	type2ContentsHeight(); // type2에서 left 콘텐츠가 본문/화면보다 긴 경우 --> 본문 길이 조정
});

$(window).resize(function(winWidth){
	winWidth = $(window).width();
});