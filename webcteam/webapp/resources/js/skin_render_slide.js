var $SLIDESLECTOR;

function moduleSlideRender( module_id, contentsData, reSlick="true" ) {
	var moduleID, slideSelctor, slideContents, slideTabContents;

	if ( !module_id ) {
		$('[data-content-type1="slide"]').each(function() {
			moduleID = $(this).parent('[data-module-id]').length ? $(this).parent('[data-module-id]').data('module-id') : $(this).parent('[data-minimodule-id]').data('minimodule-id');
			slideSelctor = targetSelector( moduleID );
			slideScriptOptionProcess( moduleID, slideSelctor );
		});
	} else {
		slideSelctor = targetSelector( module_id );

		// slideContents 처리
		if ( contentsData ) {			
			if(reSlick){			
				if ( $(slideSelctor + ' [data-content-type2]').data('content-type2') == 'slide-type-C' ) $( slideSelctor + ' .slide_nav' ).slick('unslick');
				$( slideSelctor + ' .slide_banner' ).slick('unslick');
			}

			slideContents = '';
			slideTabContents = '';

			if ( $(slideSelctor + ' [data-content-type2]').data('content-type2') == 'slide-type-A' ) {
				$.each( contentsData, function( key ) {
					slideContents += '<div class="sslide"><a class="slink" href="' + contentsData[key]['slide-link-url'] + '" target="' + contentsData[key]['slide-link-target'] + '"><img class="simg" src="' + contentsData[key]['slide-image-src'] + '" alt="" /></a></div>';
				});
				//$(slideSelctor).find('.module_container').html( '<div class="slide_banner">' + slideContents + '</div>' );
				$(slideSelctor).find('.module_container .slide_banner').html( slideContents );
			} else if ( $(slideSelctor + ' [data-content-type2]').data('content-type2') == 'slide-type-B' ) {
				$.each( contentsData, function( key ) {
					slideContents += '<a class="sslide" href="' + contentsData[key]['slide-link-url'] + '" target="' + contentsData[key]['slide-link-target'] + '"><img class="simg" src="' + contentsData[key]['slide-image-src'] + '" alt="" /></a>';
				});
				//$(slideSelctor).find('.module_container').html( '<div class="slide_banner">' + slideContents + '</div>' );
				$(slideSelctor).find('.module_container .slide_banner').html( slideContents );
				$(slideSelctor).find('.sslide').each(function(e) {
					$(this).append( contentsData[e]['slide-text-contents'] );
				});
			} else if ( $(slideSelctor + ' [data-content-type2]').data('content-type2') == 'slide-type-C' ) {
				$.each( contentsData, function( key ) {
					slideContents += '<div class="sslide"><a class="slink" href="' + contentsData[key]['slide-link-url'] + '" target="' + contentsData[key]['slide-link-target'] + '"><img class="simg" src="' + contentsData[key]['slide-image-src'] + '" alt="" /></a></div>';
					slideTabContents += '<div class="stab"><a href="#none">' + contentsData[key]['slide-tab-text'] +'</a></div>';
				});
				$(slideSelctor).find('.module_container').html( '<div class="slide_banner">' + slideContents + '</div><div class="slide_nav_wrap"><div class="slide_nav_container"><div class="slide_nav">' + slideTabContents + '</div></div></div>');
			} else if ( $(slideSelctor + ' [data-content-type2]').data('content-type2') == 'slide-type-D' ) {
				$.each( contentsData, function( key ) {
					slideContents += '<a class="sslide" href="' + contentsData[key]['slide-link-url'] + '" target="' + contentsData[key]['slide-link-target'] + '"><img class="simg" src="' + contentsData[key]['slide-image-src'] + '" alt="" /></a>';
					slideTabContents += '<div class="stab"><a href="#none">' + contentsData[key]['slide-tab-text'] +'</a></div>';
				});
				$(slideSelctor).find('.module_container').html( '<div class="slide_banner">' + slideContents + '</div><div class="slide_nav_wrap"><div class="slide_nav_container"><div class="slide_nav">' + slideTabContents + '</div></div></div>' );
				$(slideSelctor).find('.sslide').each(function(e) {
					$(this).append( contentsData[e]['slide-text-contents'] );
				});
			}
		}
		slideScriptOptionProcess( module_id, slideSelctor );
		slideMaxIndex( slideSelctor );
		setTimeout(function(){ $( slideSelctor ).find('.slider_before_loading').removeClass('slider_before_loading'); }, 100);
	}
}

// slide 스크립트 처리 함수
function slideScriptOptionProcess( module_id, slideSelctor ) {
	var slideScript, lastSlideScript, slideTabScript;
	var slideOptionSelector = $( slideSelctor ).find('[data-slide-autoplay]');

	// slide filter 처리
	$( slideSelctor ).find('.slide_contents').css('background-image', function () {
		var filterName = $( slideSelctor ).find('[data-slide-filter]').data('slide-filter');
		return 'url("../images/bg_' + filterName + '.png")';
	});

	var slideData = {};
	// 옵션은 data- 값에서 직접 뽑아온다
	slideData.arrows = ( slideOptionSelector.data('slide-arrows') == 'yes' ) ? true : false;
	slideData.dots = ( slideOptionSelector.data('slide-dots') == 'yes' ) ? true : false;
	slideData.autoplay = ( slideOptionSelector.data('slide-autoplay') == 'yes' ) ? true : false;
	slideData.autoplayspeed = slideOptionSelector.data('slide-autoplay-speed');
	slideData.motion = slideOptionSelector.data('slide-motion');
	slideData.motionFunction = slideOptionSelector.data('slide-motion-function');
	slideData.motionspeed = slideOptionSelector.data('slide-motion-speed');
	slideData.slidestoshow = slideOptionSelector.data('slides-to-show');
	slideData.slidestoscroll = slideOptionSelector.data('slides-to-scroll');
	slideData.centerMode = ( slideOptionSelector.data('slides-center-mode') == 'yes' ) ? true : false;
	slideData.variableWidth = ( slideOptionSelector.data('slides-variable-width') == 'yes' ) ? true : false;
	slideData.tabNum = slideOptionSelector.data('slide-tab-num');
	slideData.slideImageWidth = slideOptionSelector.data('slide-image-width'); // 반응형 처리를 위해 필요 - old --> slidestoshow 기반으로 변경됨 210126
	slideData.slidesRows = slideOptionSelector.data('slides-rows')

	if ( slideData.motion && slideData.motion == 'vertical' ) {
		lastSlideScript = slideData.motion + ':true, verticalSwiping:true,' ;
	} else if ( slideData.motion && slideData.motion != 'vertical' ) {
		lastSlideScript = slideData.motion + ':true,';
	} else {
		lastSlideScript = '';
	}
	if ( slideData.centerMode ) lastSlideScript += 'centerMode: true,';
	if ( slideData.variableWidth ) lastSlideScript += 'variableWidth: true,';
	if ( slideData.slidesRows ) lastSlideScript += 'rows: ' + slideData.slidesRows + ',';

	// slick 반응형 처리
	/*
	if ( slideData.slidestoshow > 1 ) {
		lastSlideScript += 'swipeToSlide : true, ';
		if ( slideData.slideImageWidth >= 500 ) {
			lastSlideScript += 'responsive: [{breakpoint: 1024, settings: {arrows: false,variableWidth: false,centerPadding: "40px",slidesToShow: 2}},{breakpoint: 540, settings: {arrows: false, variableWidth: false, centerPadding: "40px", slidesToShow: 1}}],';
		}
	}
	*/
	if ( slideData.slidestoshow > 1 ) {
		lastSlideScript += 'swipeToSlide : true, ';
		switch ( slideData.slidestoshow ) {
			case 3 :
			case '3' :
				lastSlideScript += 'responsive: [{breakpoint: 1024, settings: {arrows: false, centerMode: true, variableWidth: false, centerPadding: "40px", slidesToShow: 2}},{breakpoint: 540, settings: {arrows: false, centerMode: true, variableWidth: false, centerPadding: "40px", slidesToShow: 1}}], ';
				break;
			case 4 :
			case '4' :
				lastSlideScript += 'responsive: [{breakpoint: 1024, settings: {arrows: false, centerMode: true, variableWidth: false, centerPadding: "30px", slidesToShow: 3}},{breakpoint: 540, settings: {arrows: false, centerMode: true, variableWidth: false, centerPadding: "30px", slidesToShow: 2}}], ';
				break;
		}
	}

	// Type C, Type D를 위한 스크립트 추가
	slideTabScript = '';
	if ( $( slideSelctor ).find('[data-content-type2]').data('content-type2') == 'slide-type-C' || $( slideSelctor ).find('[data-content-type2]').data('content-type2') == 'slide-type-D' ) {
		/*
		lastSlideScript += 'asNavFor: "' + slideSelctor + ' .slide_nav",';
		slideTabScript = '$("' + slideSelctor + ' .slide_nav").slick({ asNavFor: "' + slideSelctor + ' .slide_banner", focusOnSelect: true, slidesToShow: ' + slideData.tabNum + ', slidesToScroll: 1, speed:0, centerMode: true, centerPadding: "0px", responsive: [{breakpoint: 1024, settings: {slidesToShow: 3}},{breakpoint: 768, settings: {slidesToShow: 2, centerPadding: "40px", centerMode: false}}], });';
		*/
		lastSlideScript += 'asNavFor: "' + slideSelctor + ' .slide_nav", responsive: [{breakpoint: 1024, settings: {dots: true}}],';
		slideTabScript = '$("' + slideSelctor + ' .slide_nav").slick({ asNavFor: "' + slideSelctor + ' .slide_banner", focusOnSelect: true, slidesToShow: ' + slideData.tabNum + ', autoplay:' + slideData.autoplay + ', autoplaySpeed:' + slideData.autoplayspeed + ', speed:' + slideData.motionspeed + ', slidesToScroll: 1, speed:0, });';
	}

	slideScript = '';
	slideScript = '$("' + slideSelctor + ' .slide_banner").slick({ pauseOnHover: true, pauseOnDotsHover: true, pauseOnFocus:true, accessibility:false, autoplay:' + slideData.autoplay + ', autoplaySpeed:' + slideData.autoplayspeed + ', cssEase:"' + slideData.motionFunction + '", speed:' + slideData.motionspeed + ', arrows:' + slideData.arrows + ', dots:' + slideData.dots + ', slidesToShow:' + slideData.slidestoshow + ', slidesToScroll:' + slideData.slidestoscroll + ', ' + lastSlideScript +  '});';

	slideScript = '<script class="' + module_id + '">$(function() { ' + slideScript + slideTabScript + ' });</script>';

	$('script.' + module_id).remove();
	$('body').append( slideScript );

	/* 슬라이드 type B, type D 모바일 텍스트 overflow 최적화 */
	if ( $(slideSelctor + ' [data-content-type2]').data('content-type2') == 'slide-type-B' || $(slideSelctor + ' [data-content-type2]').data('content-type2') == 'slide-type-D' ) {
		$SLIDESLECTOR = $( slideSelctor + ' .slide_banner' );
		$SLIDESLECTOR.on('init', function() {
			$(this).find('.slide_contents').each(function() {
				var $slideConts = $(this);
				var $slideTexts = $(this).find('.text_wrap');

				$slideConts.removeClass( 'sm_text_opt' );
				$slideConts.css({ 'align-items' : '', 'left' : '', 'right' : '', 'top' : '' });
				$slideTexts.removeAttr( 'style' );

				$(this).prev('img').one('load', function() {
					var slideAreaHeight = $slideConts.height();
					var textAreaHeight = $slideTexts.height();

					if ( textAreaHeight > slideAreaHeight ) {
						var textScale = (slideAreaHeight / textAreaHeight).toFixed(2);
						if ( textScale > 0.6 ) textScale = 0.6; // 최소 scale 설정
						var slideScale = ( (100 - textScale * 100) / (textScale * 2) ).toFixed(2) * -1;
						$slideConts.addClass( 'sm_text_opt' );
						$slideConts.css({
							'align-items' : 'center',
							'left' : slideScale + 'vw',
							'right' : slideScale + 'vw',
							'top' : '-20px'
						});
						$slideTexts.css({
							'transform' : 'scale(' + textScale + ')',
							'top' : 'auto' // slide_B003
						});
					}
				}).each(function() {
					if( this.complete ) {
						var slideAreaHeight = $slideConts.height();
						var textAreaHeight = $slideTexts.height();

						if ( textAreaHeight > slideAreaHeight ) {
							var textScale = (slideAreaHeight / textAreaHeight).toFixed(2);
							if ( textScale > 0.6 ) textScale = 0.6; // 최소 scale 설정
							var slideScale = ( (100 - textScale * 100) / (textScale * 2) ).toFixed(2) * -1;
							$slideConts.addClass( 'sm_text_opt' );
							$slideConts.css({
								'align-items' : 'center',
								'left' : slideScale + 'vw',
								'right' : slideScale + 'vw',
								'top' : '-20px'
							});
							$slideTexts.css({
								'transform' : 'scale(' + textScale + ')',
								'top' : 'auto' // slide_B003
							});
						}
					}
				});
			});
		});
	}
	/* // 슬라이드 type B, type D 모바일 텍스트 overflow 최적화 */

}

/* 슬라이드배너 도트 페이징 data 값 추가(슬라이드배너 인덱스 최대값 설정) */
function slideMaxIndex( target ) {
	if ( target ) {
		$( target ).find('.slide_banner').on('init', function( event, slick ) {
			$(this).find('.slick-dots').attr( 'data-max-index', slick.slideCount ).data( 'max-index', slick.slideCount );
		});
	} else {
		$('[data-content-type1="slide"]').each(function() {
			if ( $(this).attr( 'data-content-type2' ) == 'slide-type-A' || $(this).attr( 'data-content-type2' ) == 'slide-type-B' ) {
				$(this).find('.slide_banner').on('init', function( event, slick ) {
					$(this).find('.slick-dots').attr( 'data-max-index', slick.slideCount ).data( 'max-index', slick.slideCount );
				});
			}
		});
	}
}