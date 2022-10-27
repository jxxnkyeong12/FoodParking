var WINDOWWIDTH = window.innerWidth;

// 화면 넓이 체크
function windowWidthCheck() {
	WINDOWWIDTH = window.innerWidth;
}

// GNB 서브메뉴에 1뎁스 메뉴 추가( 서브 메뉴가 있는 경우 1뎁스 메뉴는 클릭 반응이 없으므로 )
/*
function gnbDepth1generation() {
	windowWidthCheck();
	$('#GnbMenu > li').each(function() {
		if ( WINDOWWIDTH < respBreakPoint ) {
			if ( $(this).find('.navi_depth2_wrap').length && !$(this).find('.depth1Copy').length ) {
				var gonHtml = $(this).children('a')[0].outerHTML;
				$(this).find('.navi_depth2_container').find('.navi_depth2').prepend( '<li class="depth1Copy">' + gonHtml + '</li>' );
			}
			$(this).find('.depth1Copy > a').css({ 'color': '', 'font-family': '', 'font-size': '' });
			$(this).find('.navi_depth2_wrap').hide();
		} else {
			$(this).find('.depth1Copy').remove();
			$(this).find('.navi_depth2_wrap').show();
		}
	});
}
*/



// 플라잉 모드시 지지대
function headerFlyingStand( delay, scrollTopFalse ) {
	var timeDelay;
	if ( delay ) timeDelay = delay;
	else timeDelay = ( document.documentElement.scrollTop < flyingBreakPoint ) ? 0 : 320; // 헤더 flying 에서 복구된 경우 높이값을 측정
	if ( !scrollTopFalse ) $('html').scrollTop(0);
	setTimeout(function() {
		$('#flyingStand').remove();
		if ( $('#skinLayoutWrap').attr('data-layout-type') == 'type1' && $('#skinLayoutWrap').attr('data-header-flying') == 'yes' ) {
			var flyingStandHeight = $('#skinHeaderSection .header_module').outerHeight();
			var flyingStandHtml = '<div id="flyingStand" style="height:' + flyingStandHeight + 'px;"></div>';
			$('#skinHeaderSection').prepend( flyingStandHtml );
		} else if ( $('#skinLayoutWrap').attr('data-layout-type') == 'type2' && $('#skinLayoutWrap').attr('data-header-flying') == 'yes' && WINDOWWIDTH < respBreakPoint ) {
			var flyingStandHeight = $('#skinHeaderSection .header_module').outerHeight();
			var flyingStandHtml = '<div id="flyingStand" style="height:' + flyingStandHeight + 'px;"></div>';
			$('#skinHeaderSection').prepend( flyingStandHtml );
		}
	}, timeDelay);
}

// type2에서 left 콘텐츠가 본문/화면보다 긴 경우 --> 본문 길이 조정
function type2ContentsHeight() {
	if ( $('#skinLayoutWrap').attr('data-layout-type') == 'type2' && $('#skinLayoutWrap').attr('data-header-flying') == 'no' && $('#skinHeaderSection .header_module').height() > $('#skinMainWrap').height() && $('#skinHeaderSection .header_module').height() > window.innerHeight ) {
		$('#skinMainWrap').height( $('#skinHeaderSection .header_module').height() );
	} else {
		//$('#skinMainWrap').height( 0 );
	}
}

// slide 리프레쉬
function slideRefresh( moduleSelector, add_option ) {
	if ( moduleSelector ) {
		$( moduleSelector + ' .slide_nav').slick('refresh');
		$( moduleSelector + ' .slide_banner').slick('refresh');
		if ( add_option == 'swiper' ) {
			$( moduleSelector + ' .productSwiperWrap')[0].swiper.update();
			if ( $( moduleSelector + ' .productSwiperWrap')[0].swiper.autoplay.running ) {
				$( moduleSelector + ' .productSwiperWrap')[0].swiper.autoplay.stop();
				$( moduleSelector + ' .productSwiperWrap')[0].swiper.autoplay.start();
			}
		}
	} else {
		$('.slide_nav').slick('unslick');
		$('.slide_banner').slick('unslick');

		$('.productSwiperWrap').each(function() { // swiper destroy는 각각 시켜 줘야 함
			if ( $(this)[0].swiper ) $(this)[0].swiper.destroy();
		});

		// slick, swiper 공통
		var slideScriptArray = [];
		$('script[class]').each(function() {
			slideScriptArray.push( $(this)[0].outerHTML );
			$('script[class]').remove();
		});
		$.each( slideScriptArray, function( key ) {
			$('body').append( slideScriptArray[key] );
		});
	}
}

// 레이어
function showCenterLayer( selector ) {
	layerCenterPosition( selector );
	$('#skinModal').show();
	$( selector ).show();
	//$('body').css( 'overflow', 'hidden' );
}
function layerCenterPosition( selector ) {
	var htmlSelector;
	if ( selector ) htmlSelector = $( selector );
	else htmlSelector = $('.resp_layer_pop');
	$(selector).css({
		'top': '50%',
		'left': '50%',
		'marginLeft': (htmlSelector.outerWidth() / 2) * -1,
		'marginTop': (htmlSelector.outerHeight() / 2) * -1
	});
}
function hideCenterLayer() {
	$('#skinModal').attr( 'class', 'modal_bg' ).hide();
	$('.resp_layer_pop').hide();
	//$('body').css( 'overflow', '' );
}

/* 슬라이드 type B, type D 모바일 텍스트 overflow 최적화( 에디터 반응형 view 에서 사용 ) */
function slideMobileTextOpt( add_option ) {
	$('[data-content-type2="slide-type-B"], [data-content-type2="slide-type-D"]').each(function() {
		if ( Number($(this).children('.module_wrap').children('.module_container').data('slides-to-show')) == 1 ) {
			$(this).find('.slide_contents').each(function() {
				$(this).removeClass( 'sm_text_opt' );
				$(this).css({ 'align-items' : '', 'left' : '', 'right' : '', 'top' : '' });
				$(this).find('.text_wrap').removeAttr( 'style' );

				var slideAreaHeight = $(this).height();
				var textAreaHeight = $(this).find('.text_wrap').height();
				if ( textAreaHeight > slideAreaHeight ) {
					var textScale = (slideAreaHeight / textAreaHeight).toFixed(2);
					if ( textScale > 0.6 ) textScale = 0.6; // 최소 scale 설정
					var slideScale = ( (100 - textScale * 100) / (textScale * 2) ).toFixed(2) * -1;
					$(this).addClass( 'sm_text_opt' );
					$(this).css({
						'align-items' : 'center',
						'left' : slideScale + 'vw',
						'right' : slideScale + 'vw',
						'top' : '-20px'
					});
					$(this).find('.text_wrap').css({
						'transform' : 'scale(' + textScale + ')',
						'top' : 'auto' // slide_B003
					});
				} else {
					if ( add_option == 'editorView' ) {
						$(this).removeClass( 'sm_text_opt' );
						$(this).css({ 'align-items' : '', 'left' : '', 'right' : '', 'top' : '' });
						$(this).find('.text_wrap').removeAttr( 'style' );
					}
				}
			});
		}
	});
}
/* // 슬라이드 type B, type D 모바일 텍스트 overflow 최적화( 에디터 반응형 view 에서 사용 ) */



/* ========== 스페셜 페이지 에디터 모드 처리 ========== */
// [커머스] 카테고리 페이지
function categoryEditModeUi() {
	var lineMapHtml = '<li><a href="#none">홈</a></li><li><a href="#none">1차카테고리</a></li><li><a href="#none">2차카테고리</a></li><li><a href="#none">3차카테고리</a></li><li><a href="#none">4차카테고리</a></li>';
	var categoryTitle = '카테고리명';
	categoryPageAjaxContents( lineMapHtml, categoryTitle );
	$( document ).on('ajaxComplete', function() {
		if ( $('[data-module-id="module-category"] .special_title .main_category').text() != categoryTitle ) {
			categoryPageAjaxContents( lineMapHtml, categoryTitle );
		}
	});
	$('[data-module-id="module-category"]').find('.bottom_navi_map, .sorting_left, .category_list').hide();
	$('[data-module-id="module-category"]').find( 'select, input' ).attr( 'disabled', 'disabled' );
}
function categoryPageAjaxContents( line_map, category_title, list_num ) {
	$('[data-module-id="module-category"] .top_navi_map').html( line_map );
	$('[data-module-id="module-category"] .special_title .main_category').text( category_title );
}

// [커머스] 검색 페이지
function searchEditModeUi() {
	$('[data-module-id="module-search"]').find( 'select, input, button' ).attr( 'disabled', 'disabled' );
}

// [커머스] 장바구니 페이지
function cartEditModeUi() {
	cartPageAjaxContents();
	$( document ).on('ajaxComplete', function() {
		cartPageAjaxContents();
	});
}
function cartPageAjaxContents() {
	$('[data-module-id="module-cart"]').find( 'button, input' ).attr( 'disabled', 'disabled' );
	$('[data-module-id="module-cart"]').find( '.objProductCart, .objProductCartEmpty' ).hide();
	$('[data-module-id="module-cart"]').find( '#all_check' ).prop( 'checked', false );
	$('[data-module-id="module-cart"]').find('.sum_product_price, .sum_fee_price, .sum_order_price, .js_btn_cnt .no').text( '0' );
	$('[data-module-id="module-cart"]').find('.bottom_bts button').css( 'opacity', '1' );
}

// [커머스] 주문 페이지
function orderEditModeUi() {
	$('#frame-order-payment').remove(); // 결제 iframe 삭제
	orderPageAjaxContents();
	$( document ).on('ajaxComplete', function() {
		orderPageAjaxContents();
	});
}
function orderPageAjaxContents() {
	$('[data-module-id="module-order"]').find( 'button, input, select' ).attr( 'disabled', 'disabled' );
	$('[data-module-id="module-order"]').find( '#div-order-products' ).hide();
	$('[data-module-id="module-order"]').find( '[data-field="sum_product_price"], [data-field="sum_fee_price"], [data-field="sum_payment_price"]' ).text( '0' );
}

// 마이페이지
function mypageEditModeUi() {
	mypageAjaxContents();
	$( document ).on('ajaxComplete', function() {
		mypageAjaxContents();
	});
}
function mypageAjaxContents() {
	$('#mypageMainContents').find('.list_board').hide();
	$('#mypageMainContents').find('.order_count .count').text('0');
}
/* ========== // 스페셜 페이지 에디터 모드 처리 ========== */

// 탭선택에 따른 영역 show/hide
function tabShowHide(el) {
	var tabId = el.attr('data-tab-button');
	
	$(document).find('[data-tab-button]').each(function(){
		var $this = $(this);
		var elTabId = $this.attr('data-tab-button');
		if( elTabId == tabId ){
			$this.addClass('on');
			$(document).find('[data-tab-area="'+elTabId+'"]').removeClass('hide');
		}
		else{
			$this.removeClass('on');
			if( !$(document).find('[data-tab-area="'+elTabId+'"]').hasClass('hide') )
				$(document).find('[data-tab-area="'+elTabId+'"]').addClass('hide');
		}
	});
}