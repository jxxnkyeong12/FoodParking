/* =========================================================================
에디터 옵션패널로부터 넘어온 값 --> data-값 변경
========================================================================= */
function moduleRender( module_id, option_name, data_array, add_option ) {
	var moduleID = targetSelector( module_id )
	var moduleSelector = $( moduleID );

	switch ( option_name ) {
		case 'layout_fullsize' :
			moduleSelector.children().children('[data-layout-fullsize]').data( 'layout-fullsize', data_array['layout-fullsize'] ).attr( 'data-layout-fullsize', data_array['layout-fullsize'] );
			if ( moduleSelector.find('[data-content-type1="slide"]').length ) {
				slideRefresh( moduleID );
			} else if ( moduleSelector.find('[data-content-type2="builderProductA-type-C"]').length ) {
				slideRefresh( moduleID, 'swiper' );
			}
			if ( moduleSelector.find('[data-content-type1="gallery"]').length ) {
				moduleSelector.find( '.gallery_ul' ).masonry( 'layout' );
			}
			break;
		case 'padding' :
			moduleSelector.children().children().children('.module_container').data( 'padding-top', data_array['padding-top'] ).attr( 'data-padding-top', data_array['padding-top'] );
			moduleSelector.children().children().children('.module_container').data( 'padding-bottom', data_array['padding-bottom'] ).attr( 'data-padding-bottom', data_array['padding-bottom'] );
			break;
		case 'background' :
			// 옵션값 없는 경우 디폴트 값이 필요한 옵션
			var backgroundPosition = data_array['background-position'] ? data_array['background-position'] : 'center center';
			var backgroundRepeat = data_array['background-repeat'] ? data_array['background-repeat'] : 'no-repeat';

			moduleSelector.children('[data-background-color]').data( 'background-color', data_array['background-color'] ).attr( 'data-background-color', data_array['background-color'] );
			moduleSelector.children('[data-background-image]').data( 'background-image', data_array['background-image'] ).attr( 'data-background-image', data_array['background-image'] );
			/*
			moduleSelector.children('[data-background-position]').data( 'background-position', backgroundPosition ).attr( 'data-background-position', backgroundPosition );
			moduleSelector.children('[data-background-repeat]').data( 'background-repeat', backgroundRepeat ).attr( 'data-background-repeat', backgroundRepeat );
			moduleSelector.children('[data-background-size]').data( 'background-size', data_array['background-size'] ).attr( 'data-background-size', data_array['background-size'] );
			*/
			moduleSelector.children('[data-background-image-options]').data( 'background-image-options', data_array['background-image-options'] ).attr( 'data-background-image-options', data_array['background-image-options'] );

			moduleSelector.children('[data-background-attachment]').data( 'background-attachment', data_array['background-attachment'] ).attr( 'data-background-attachment', data_array['background-attachment'] );
			moduleSelector.children('[data-background-filter]').data( 'background-filter', data_array['background-filter'] ).attr( 'data-background-filter', data_array['background-filter'] );
			moduleBackgroundRender( module_id );
			break;
		case 'text' :
			if ( add_option ) { // text-type-C
				moduleTextCRender( module_id, data_array, add_option );
			} else { // text-type-B
				moduleTextRender( module_id, data_array );
			}
			break;
		case 'image' :
			moduleSelector.find('[data-image-cutting]').data( 'image-cutting', data_array['image-cutting'] ).attr( 'data-image-cutting', data_array['image-cutting'] );
		case 'video' :
			//moduleSelector.find('[data-video-source]').data( 'video-source', data_array['video-source'] ).attr( 'data-video-source', data_array['video-source'] );
			//moduleVideoRender( module_id );

			//moduleSelector.find('.youtube_inner').html( data_array['video-source'] );
			/*
			$.each( data_array['video-source'], function ( key ) {
				moduleSelector.find('.youtube_inner').eq( key ).html( data_array['video-source'][key] );
			});
			*/
			moduleSelector.find('.youtube_inner').eq( add_option ).html( data_array['video-source'][add_option] );
			break;
		case 'map' :
			if(BASIC_CONFIG.map_type == "api") {
				moduleSelector.find('[data-map-address-seq]').data( 'map-address-seq', data_array['map-address-seq'] ).attr( 'data-map-address-seq', data_array['map-address-seq'] );
			}else{
				moduleSelector.find('.resp_googlemap_inner').html( data_array['map-source'] );
			}			
			
			moduleSelector.find('[data-map-screen-ratio]').data( 'map-screen-ratio', data_array['map-screen-ratio'] ).attr( 'data-map-screen-ratio', data_array['map-screen-ratio'] );
			moduleMapRender(moduleSelector.children());
			break;
		case 'line' :
			moduleSelector.find('[data-line-color]').data( 'line-color', data_array['line-color'] ).attr( 'data-line-color', data_array['line-color'] );
			moduleSelector.find('[data-line-bgcolor]').data( 'line-bgcolor', data_array['line-bgcolor'] ).attr( 'data-line-bgcolor', data_array['line-bgcolor'] );
			moduleSelector.find('[data-line-image]').data( 'line-image', data_array['line-image'] ).attr( 'data-line-image', data_array['line-image'] );
			moduleSelector.find('[data-line-height]').data( 'line-height', data_array['line-height'] ).attr( 'data-line-height', data_array['line-height'] );
			moduleLineRender( module_id );
			break;
		case 'slide' :
			moduleSelector.find('[data-slide-arrows]').data( 'slide-arrows', data_array['slide-arrows'] ).attr( 'data-slide-arrows', data_array['slide-arrows'] );
			moduleSelector.find('[data-slide-dots]').data( 'slide-dots', data_array['slide-dots'] ).attr( 'data-slide-dots', data_array['slide-dots'] );
			moduleSelector.find('[data-slide-autoplay]').data( 'slide-autoplay', data_array['slide-autoplay'] ).attr( 'data-slide-autoplay', data_array['slide-autoplay'] );
			moduleSelector.find('[data-slide-autoplay-speed]').data( 'slide-autoplay-speed', data_array['slide-autoplay-speed'] ).attr( 'data-slide-autoplay-speed', data_array['slide-autoplay-speed'] );
			moduleSelector.find('[data-slide-motion]').data( 'slide-motion', data_array['slide-motion'] ).attr( 'data-slide-motion', data_array['slide-motion'] );
			moduleSelector.find('[data-slide-motion-function]').data( 'slide-motion-function', data_array['slide-motion-function'] ).attr( 'data-slide-motion-function', data_array['slide-motion-function'] );
			moduleSelector.find('[data-slide-motion-speed]').data( 'slide-motion-speed', data_array['slide-motion-speed'] ).attr( 'data-slide-motion-speed', data_array['slide-motion-speed'] );
			moduleSelector.find('[data-slide-cutting]').data( 'slide-cutting', data_array['slide-cutting'] ).attr( 'data-slide-cutting', data_array['slide-cutting'] );
			moduleSelector.find('[data-slide-image-width]').data( 'slide-image-width', data_array['slide-image-width'] ).attr( 'data-slide-image-width', data_array['slide-image-width'] );
			moduleSelector.find('[data-slide-image-height]').data( 'slide-image-height', data_array['slide-image-height'] ).attr( 'data-slide-image-height', data_array['slide-image-height'] );
			moduleSelector.find('[data-slide-filter]').data( 'slide-filter', data_array['slide-filter'] ).attr( 'data-slide-filter', data_array['slide-filter'] );
			moduleSelector.find('[data-slide-tab-num]').data( 'slide-tab-num', data_array['slide-tab-num'] ).attr( 'data-slide-tab-num', data_array['slide-tab-num'] );
			moduleSlideRender( module_id, data_array['slide-contents'] );
			break;
		case 'contents' :
			moduleSelector.find('[data-contents-col-num]').data( 'contents-col-num', data_array['contents-col-num'] ).attr( 'data-contents-col-num', data_array['contents-col-num'] );
			moduleSelector.find('[data-contents-all-num]').data( 'contents-all-num', data_array['contents-all-num'] ).attr( 'data-contents-all-num', data_array['contents-all-num'] );
			moduleSelector.find('[data-contents-image-align]').data( 'contents-image-align', data_array['contents-image-align'] ).attr( 'data-contents-image-align', data_array['contents-image-align'] );
			moduleSelector.find('[data-contents-vertical-align]').data( 'contents-vertical-align', data_array['contents-vertical-align'] ).attr( 'data-contents-vertical-align', data_array['contents-vertical-align'] );

			//moduleContentsRender( module_id );
			break;
		case 'board' :
			if ( add_option == 'boardAjax' ) {
				moduleSelector.find('[data-board-id]').data( 'board-id', data_array['board-id'] ).attr( 'data-board-id', data_array['board-id'] );
				moduleSelector.find('[data-board-per-page]').data( 'board-per-page', data_array['board-per-page'] ).attr( 'data-board-per-page', data_array['board-per-page'] );
				moduleBoardRender( module_id, data_array['board-id'], 'boardAjax', data_array['auth-view'], data_array['auth-write'] ); // --> skin_render_board.js
			} else {
				//moduleSelector.find('[data-board-category]').data( 'board-category', data_array['board-category'] ).attr( 'data-board-category', data_array['board-search'] );
				moduleSelector.find('[data-board-search]').data( 'board-search', data_array['board-search'] ).attr( 'data-board-search', data_array['board-search'] );

				moduleSelector.find('[data-board-display-seq]').data( 'board-display-seq', data_array['board-display-seq'] ).attr( 'data-board-display-seq', data_array['board-display-seq'] );
				moduleSelector.find('[data-board-display-category]').data( 'board-display-category', data_array['board-display-category'] ).attr( 'data-board-display-category', data_array['board-display-category'] );
				moduleSelector.find('[data-board-display-subject]').data( 'board-display-subject', data_array['board-display-subject'] ).attr( 'data-board-display-subject', data_array['board-display-subject'] );
				moduleSelector.find('[data-board-display-writer]').data( 'board-display-writer', data_array['board-display-writer'] ).attr( 'data-board-display-writer', data_array['board-display-writer'] );
				moduleSelector.find('[data-board-display-date]').data( 'board-display-date', data_array['board-display-date'] ).attr( 'data-board-display-date', data_array['board-display-date'] );
				moduleSelector.find('[data-board-display-viewcount]').data( 'board-display-viewcount', data_array['board-display-viewcount'] ).attr( 'data-board-display-viewcount', data_array['board-display-viewcount'] );

				moduleSelector.find('[data-board-title-seq]').data( 'board-title-seq', data_array['board-title-seq'] ).attr( 'data-board-title-seq', data_array['board-title-seq'] );
				moduleSelector.find('[data-board-title-category]').data( 'board-title-category', data_array['board-title-category'] ).attr( 'data-board-title-category', data_array['board-title-category'] );
				moduleSelector.find('[data-board-title-subject]').data( 'board-title-subject', data_array['board-title-subject'] ).attr( 'data-board-title-subject', data_array['board-title-subject'] );
				moduleSelector.find('[data-board-title-writer]').data( 'board-title-writer', data_array['board-title-writer'] ).attr( 'data-board-title-writer', data_array['board-title-writer'] );
				moduleSelector.find('[data-board-title-date]').data( 'board-title-date', data_array['board-title-date'] ).attr( 'data-board-title-date', data_array['board-title-date'] );
				moduleSelector.find('[data-board-title-viewcount]').data( 'board-title-viewcount', data_array['board-title-viewcount'] ).attr( 'data-board-title-viewcount', data_array['board-title-viewcount'] );

				moduleBoardRender( module_id ); // --> skin_render_board.js
			}
			break;
		case 'boardRecent' :
			if ( add_option == 'boardAjax' ) {
				moduleSelector.find('[data-boardrecent-id]').data( 'boardrecent-id', data_array['boardrecent-id'] ).attr( 'data-boardrecent-id', data_array['boardrecent-id'] );
				moduleSelector.find('[data-boardrecent-per-page]').data( 'boardrecent-per-page', data_array['boardrecent-per-page'] ).attr( 'data-boardrecent-per-page', data_array['boardrecent-per-page'] );
				moduleBoardRecentRender( module_id, data_array['boardrecent-id'], 'boardAjax', data_array['auth-view'], data_array['auth-write'] ); // --> skin_render_board.js
			}
			break;
		case 'form' :
			moduleSelector.find('[data-form-pilsu-color]').data( 'form-pilsu-color', data_array['form-pilsu-color'] ).attr( 'data-form-pilsu-color', data_array['form-pilsu-color'] );
			moduleSelector.find('[data-form-active-color]').data( 'form-active-color', data_array['form-active-color'] ).attr( 'data-form-active-color', data_array['form-active-color'] );
			moduleSelector.find('[data-form-button-border]').data( 'form-button-border', data_array['form-button-border'] ).attr( 'data-form-button-border', data_array['form-button-border'] );
			moduleSelector.find('[data-form-button-bg]').data( 'form-button-bg', data_array['form-button-bg'] ).attr( 'data-form-button-bg', data_array['form-button-bg'] );
			moduleSelector.find('[data-form-button-text]').data( 'form-button-text', data_array['form-button-text'] ).attr( 'data-form-button-text', data_array['form-button-text'] );

			formOptionRender( module_id ); // --> skin_render_forms.js
			break;
		case 'gallery' :
			if ( add_option == 'galleryAjax' ) {
				moduleSelector.find('[data-gallery-id]').data( 'gallery-id', data_array['gallery-id'] ).attr( 'data-gallery-id', data_array['gallery-id'] );
				moduleSelector.find('[data-gallery-perpage]').data( 'gallery-perpage', data_array['gallery-perpage'] ).attr( 'data-gallery-perpage', data_array['gallery-perpage'] );
				moduleSelector.find('[data-gallery-pagingtype]').data( 'gallery-pagingtype', data_array['gallery-pagingtype'] ).attr( 'data-gallery-pagingtype', data_array['gallery-pagingtype'] );
				moduleSelector.find('[data-gallery-colnum]').data( 'gallery-colnum', data_array['gallery-colnum'] ).attr( 'data-gallery-colnum', data_array['gallery-colnum'] );
				moduleGalleryRender( module_id, data_array['gallery-id'], 'galleryAjax' ); // --> skin_render_gallery.js
			} else {
				moduleGalleryRender( module_id ); // --> skin_render_gallery.js
			}
			moduleSelector.find('[data-gallery-screen-ratio]').data( 'gallery-screen-ratio', data_array['gallery-screen-ratio'] ).attr( 'data-gallery-screen-ratio', data_array['gallery-screen-ratio'] );
			break;

		case 'builderProductA' : // 상품 디스플레이
			if ( add_option == 'productAjax' ) {
				moduleSelector.find('[data-group-seq]').data( 'group-seq', data_array['group-seq'] ).attr( 'data-group-seq', data_array['group-seq'] );
				moduleSelector.find('[data-product-pagingtype]').data( 'product-pagingtype', data_array['product-pagingtype'] ).attr( 'data-product-pagingtype', data_array['product-pagingtype'] );
				moduleSelector.find('[data-product-perpage]').data( 'product-perpage', data_array['product-perpage'] ).attr( 'data-product-perpage', data_array['product-perpage'] );
				moduleSelector.find('[data-col-num]').data( 'col-num', data_array['col-num'] ).attr( 'data-col-num', data_array['col-num'] );
				moduleSelector.find('[data-row-num]').data( 'row-num', data_array['row-num'] ).attr( 'data-row-num', data_array['row-num'] );
				productGroupRender( module_id, data_array['group-seq'], 'productAjax' ); // --> skin_render_builderProductA.js
			} else if ( add_option == 'swiperAuto' ) {
				moduleSelector.find('[data-list-padding]').data( 'list-padding', data_array['list-padding'] ).attr( 'data-list-padding', data_array['list-padding'] );
				moduleSelector.find('[data-swiper-auto]').data( 'swiper-auto', data_array['swiper-auto'] ).attr( 'data-swiper-auto', data_array['swiper-auto'] );
				moduleSelector.find('[data-swiper-delay]').data( 'swiper-delay', data_array['swiper-delay'] ).attr( 'data-swiper-delay', data_array['swiper-delay'] );
				productGroupRender( module_id, data_array['group-seq'], 'swiperAuto' ); // --> skin_render_builderProductA.js
			} else {
				moduleSelector.find('[data-list-padding]').data( 'list-padding', data_array['list-padding'] ).attr( 'data-list-padding', data_array['list-padding'] );
				moduleSelector.find('[data-image-type]').data( 'image-type', data_array['image-type'] ).attr( 'data-image-type', data_array['image-type'] );
				moduleSelector.find('[data-image-ratio]').data( 'image-ratio', data_array['image-ratio'] ).attr( 'data-image-ratio', data_array['image-ratio'] );
				moduleSelector.find('[data-product-rounding]').data( 'product-rounding', data_array['product-rounding'] ).attr( 'data-product-rounding', data_array['product-rounding'] );
				moduleSelector.find('[data-display-name]').data( 'display-name', data_array['display-name'] ).attr( 'data-display-name', data_array['display-name'] );
				moduleSelector.find('[data-display-desc]').data( 'display-desc', data_array['display-desc'] ).attr( 'data-display-desc', data_array['display-desc'] );
				moduleSelector.find('[data-display-retailprice]').data( 'display-retailprice', data_array['display-retailprice'] ).attr( 'data-display-retailprice', data_array['display-retailprice'] );
				moduleSelector.find('[data-display-saleprice]').data( 'display-saleprice', data_array['display-saleprice'] ).attr( 'data-display-saleprice', data_array['display-saleprice'] );
				moduleSelector.find('[data-display-discountpercent]').data( 'display-discountpercent', data_array['display-discountpercent'] ).attr( 'data-display-discountpercent', data_array['display-discountpercent'] );
				moduleSelector.find('[data-display-discountcoupon]').data( 'display-discountcoupon', data_array['display-discountcoupon'] ).attr( 'data-display-discountcoupon', data_array['display-discountcoupon'] );
				productGroupRender( module_id ); // --> skin_render_builderProductA.js
			}
			break;

		case 'module_fontcolor' : // 210628 폰트 스타일 추가
			moduleSelector.find('[data-module-fontcolor]').data( 'module-fontcolor', data_array ).attr( 'data-module-fontcolor', data_array );
			moduleFontcolorRender( module_id );
			break;
		case 'module_fontfamily' : // 210628 폰트 스타일 추가
			moduleSelector.find('[data-module-fontfamily]').data( 'module-fontfamily', data_array ).attr( 'data-module-fontfamily', data_array );
			moduleFontfamilyRender( module_id );
			break;

		case 'table' :
			moduleSelector.find('[data-th-padding]').data( 'th-padding', data_array['th-padding'] ).attr( 'data-th-padding', data_array['th-padding'] );
			moduleSelector.find('[data-td-padding]').data( 'td-padding', data_array['td-padding'] ).attr( 'data-td-padding', data_array['td-padding'] );
			moduleSelector.find('[data-table-minwidth]').data( 'table-minwidth', data_array['table-minwidth'] ).attr( 'data-table-minwidth', data_array['table-minwidth'] );
			break;

		case 'product_item_col' : // 상품상세블록 컬럼 옵션
			moduleSelector.find('[data-item-col]').data( 'item-col', data_array['item-col'] ).attr( 'data-item-col', data_array['item-col'] );
			break;
	}
}


/* =========================================================================
스킨 모듈 data-값 --> 렌더링
========================================================================= */
// 모듈 셀렉터 추출( 일반, 미니, 프리뷰 )
function targetSelector( module_id ) {
	var moduleSelector;
	if ( $('[data-minimodule-id=' + module_id + ']').length ) {
		moduleSelector = '[data-minimodule-id=' + module_id + ']';
	} else {
		moduleSelector = '[data-module-id=' + module_id + ']';
	}
	return moduleSelector;
}

function childFindModuleID( obj ) {
	var moduleID;
	if ( $( obj ).closest( '[data-content-type1]' ).parent( '[data-minimodule-id]' ).length ) {
		moduleID = $( obj ).closest( '[data-content-type1]' ).parent( '[data-minimodule-id]' ).data( 'minimodule-id' );
	} else {
		moduleID = $( obj ).closest( '[data-content-type1]' ).parent( '[data-module-id]' ).data( 'module-id' );
	}
	return moduleID;
}

function childFindModuleID2( childModule ) {
	var moduleID;
	if ( $( childModule ).parent( '[data-minimodule-id]' ).length ) {
		moduleID = $( childModule ).parent( '[data-minimodule-id]' ).data( 'minimodule-id' );
	} else {
		moduleID = $( childModule ).parent( '[data-module-id]' ).data( 'module-id' );
	}
	return moduleID;
}

// background-color, background-image 처리
function moduleBackgroundRender( module_id ) {
	var bgSelector, bgColor, bgImage;
	if ( !module_id ) {
		$('[data-background-color]').each(function() {
			bgColor = $(this).data('background-color');
			if ( bgColor ) {
				$(this).css('background-color', function () {
					return bgColor;
				});
			} else {
				$(this).css('background-color', function () {
					return 'transparent';
				});
			}
		});
		$('[data-background-image]').each(function() {
			bgImage = $(this).data('background-image');
			$(this).css('background-image', function () {
				var filterName = $(this).data('background-filter');
                var result = 'url("/images/bg_' + filterName + '.png")';
                if (bgImage) {
                    result += ', url(' + bgImage + ')';
                }
				return result;
			});
		});
	} else {
		bgSelector = targetSelector( module_id );
		bgColor = $( bgSelector ).children('[data-background-color]').data('background-color');
		bgImage = $( bgSelector ).children('[data-background-image]').data('background-image');
		if ( bgColor ) {
			$( bgSelector ).children('[data-background-color]').css('background-color', function () {
				return bgColor;
			});
		} else {
			$( bgSelector ).children('[data-background-color]').css('background-color', function () {
				return 'transparent';
			});
		}
		$( bgSelector ).children('[data-background-image]').css('background-image', function () {
			var filterName = $( bgSelector ).children('[data-background-image]').data('background-filter');
			return 'url("/images/bg_' + filterName + '.png"), url(' + bgImage + ')';
		});
	}
}

// Text 처리( text-type-B )
function moduleTextRender( module_id, optionArray ) {
	if ( module_id ) {
		var textModuleId = targetSelector( module_id );
		var targetTextButton = optionArray['text-button-item'];

		$( textModuleId ).find('.button_area a:eq(' + targetTextButton + ')').css({
			'border-color' : optionArray['text-button-border'],
			'background' : optionArray['text-button-bg'],
			'color' : optionArray['text-button-color']
		});
		$( textModuleId ).find('.button_area a:eq(' + targetTextButton + ') span').css({
			'color' : optionArray['text-button-color']
		});

		$( textModuleId ).find('.button_area a:eq(' + targetTextButton + ')').attr( 'data-button-style', optionArray['text-button-style'] );
	}
}

// Text 처리( text-type-C )
function moduleTextCRender( module_id, optionArray, add_option ) {
	var textModuleId = targetSelector( module_id );

	switch ( add_option ) {
		case 'onlyColor' :
			$.each(optionArray, function ( key ) {
				$( textModuleId ).find('a[data-button-style]:eq(' + key + ')').attr({
					'data-border-color' : optionArray[key]['button-border-color'],
					'data-bg-color' : optionArray[key]['button-bg-color'],
					'data-text-color' : optionArray[key]['button-text-color'],
					'data-button-style' : optionArray[key]['button-style']
				});
				$( textModuleId ).find('a[data-button-style]:eq(' + key + ')').css({
					'border-color' : optionArray[key]['button-border-color'],
					'background' : optionArray[key]['button-bg-color'],
					'color' : optionArray[key]['button-text-color']
				});
				$( textModuleId ).find('a[data-button-style]:eq(' + key + ') em').css({
					'color' : optionArray[key]['button-text-color']
				});
			});
			break;
		case 'onlyLinkText' :
			$.each(optionArray, function ( key ) {
				var buttonLinkTarget = ( optionArray[key]['button-target'] == 'checked' ) ? '_blank' : '';
				$( textModuleId ).find('a[data-button-style]:eq(' + key + ') em').text( optionArray[key]['button-name'] );
				$( textModuleId ).find('a[data-button-style]:eq(' + key + ')').attr({
					'href' : optionArray[key]['button-link'],
					'target' : buttonLinkTarget
				});
			});
			break;
		case 'onlyButtonAlign' :
			$( textModuleId ).find('[data-button-align]').data( 'button-align', optionArray ).attr( 'data-button-align', optionArray );
			break;
	}
}

function moduleTextCLoading( module_id ) {
	var textModuleId = targetSelector( module_id );
	$( textModuleId ).find('a[data-button-style]').each(function() {
		$(this).css({
			'border-color' : $(this).data('border-color'),
			'background' : $(this).data('bg-color'),
			'color' : $(this).data('text-color')
		});
		$( this ).find( 'em' ).css({
			'color' : $(this).data('text-color')
		});
	});
}

// video 처리
function moduleVideoRender( module_id ) {
	var videoSelector, videoSource;
	if ( !module_id ) {
		$('[data-video-source]').each(function() {
			if ( $(this).closest('[data-content-type2]').data('content-type2') == 'video-type-A' ) {
				videoSource = $(this).data('video-source');
				$(this).find('.youtube_inner').html( $(this).data('video-source') );
			}
		});
	} else {
		videoSelector = targetSelector( module_id );
		videoSource = $( videoSelector ).find('[data-video-source]').data('video-source');
		$( videoSelector ).find('.youtube_inner').html( videoSource );
	}
}


// line 처리
function moduleLineRender( module_id ) { 
	var lineSelector, lineColor, lineImage, lineHeight;
	if ( !module_id ) {
		$('[data-line-color]').each(function() {
			if ( $(this).data('line-color') ) {
				lineColor = $(this).data('line-color');
				switch ( $(this).data('content-type2') ) {
					case 'line-type-A' :
						$(this).find('.line').css( 'border-color' , lineColor );
						break;
					case 'line-type-B' :
						$(this).find('.line svg').css( 'stroke' , lineColor );
						break;
				}
			}
		});
		$('[data-line-bgcolor]').each(function() { // 210218 - line-type-C
			if ( $(this).data('line-bgcolor') ) {
				lineColor = $(this).data('line-bgcolor');
				$(this).find('.line svg').css( 'fill' , lineColor );
			}
		});
		$('[data-line-image]').each(function() {
			lineImage = $(this).data('line-image');
			lineHeight = $(this).data('line-height');
			$(this).find('.line').css( 'padding-top', lineHeight + 'px' );
			$(this).find('.line').css('background-image', function () {
                var result = 'url(' + lineImage + ')';
				return result;
			});
		});
	} else {
		lineSelector = targetSelector( module_id );
		switch ( $( lineSelector ).children('[data-content-type2]').data('content-type2') ) {
			case 'line-type-A' :
				lineColor = $( lineSelector ).children('[data-line-color]').data('line-color');
				$( lineSelector ).find('.line').css( 'border-color' , lineColor );
				break;
			case 'line-type-B' :
				lineColor = $( lineSelector ).children('[data-line-color]').data('line-color');
				$( lineSelector ).find('.line svg').css( 'stroke' , lineColor );
				break;
			case 'line-type-C' :
				lineColor = $( lineSelector ).children('[data-line-bgcolor]').data('line-bgcolor');
				$( lineSelector ).find('.line svg').css( 'fill' , lineColor );
				break;
			case 'line-type-D' :
				lineImage = $( lineSelector ).children('[data-line-image]').data('line-image');
				lineHeight = $( lineSelector ).children('[data-line-height]').data('line-height');
				$( lineSelector ).find('.line').css( 'padding-top', lineHeight + 'px' );
				$( lineSelector ).find('.line').css('background-image', function () {
					var result = 'url(' + lineImage + ')';
					return result;
				});
				break;
		}

	} 
}

// slide 처리 --> skin_render_slide.js


// 콘텐츠 처리 -- type-A, type-B 현재 옵션조정에 따른 변경사항만 있음
function moduleContentsRender( module_id ) {
	var contentsSelector = targetSelector( module_id );
	var contetnsType = $(contentsSelector + ' [data-content-type2]').data('content-type2');

	if ( contetnsType == 'contents-type-A' || contetnsType == 'contents-type-B' ) {
		var beforeContentsNum = $( contentsSelector + ' ul.contents_ul > li' ).length;
		var afterContentsNum = Number( $( contentsSelector ).find('[data-contents-all-num]').attr('data-contents-all-num') );
		var addListNum = afterContentsNum - beforeContentsNum;

		if ( addListNum > 0 ) {
			var listHtmlCopy = $( contentsSelector + ' ul.contents_ul > li:first-child' )[0].outerHTML;
			for ( var i = addListNum; i > 0 ; i-- ) {
				$( contentsSelector + ' ul.contents_ul' ).append( listHtmlCopy );
			}
		}
	}
}
// 콘텐츠 "ADD" 버튼 클릭으로 인한 추가
function contetnsListAdd( module_id ) {
	var contentsSelector = targetSelector( module_id );
	var listHtmlCopy = '';
	listHtmlCopy = $( contentsSelector + ' ul.contents_ul > li:first-child' )[0].outerHTML;
	$( contentsSelector + ' ul.contents_ul' ).append( listHtmlCopy );

	var listNum = $( contentsSelector + ' ul.contents_ul > li' ).length;
	$( contentsSelector + ' [data-contents-all-num]' ).attr( 'data-contents-all-num', listNum )
}


/* wow */
/*
function afterReveal( el ) {
	el.addEventListener('animationend', function( event ) {
		el.parentNode.classList.remove('over_hide');
	});
}
function wowInit() { new WOW({ callback: afterReveal, offset:160 }).init(); }
*/


function moduleFontcolorRender( module_id ) {
	var moduleSelector, moduleType1, fontColor;
	moduleSelector = targetSelector( module_id );
	moduleType1 = $(moduleSelector).children('[data-content-type1]').attr('data-content-type1');
	fontColor = $( moduleSelector ).children('[data-module-fontcolor]').data('module-fontcolor');

	switch ( moduleType1 ) {
		case 'form' :
			$( moduleSelector ).find( '.form_title h4, .form_desc, .radio_check_group em, .form_items select, .form_items button:not(.form_send), .form_items input, .form_items textarea, .form_type_text .form_items, .form_attach_filename' ).css('color', function () {
				return fontColor;
			});
			break;
		case 'board' :
			$( moduleSelector ).find( '.list_board li, .image_board li, .faq_board .faq_link, [data-module-parents="board_C001"] .item_category, select[name="board_category"], select[name="board_search_select"], button[name="board_search_button"], input[name="board_search_input"]' ).css('color', function () {
				return fontColor;
			});
			break;
		case 'boardRecent' :
			$( moduleSelector ).find( '.recent_board li, .recent_board .item_subject > a' ).css('color', function () {
				return fontColor;
			});
			break;
	}
}

function moduleFontfamilyRender( module_id ) {
	var moduleSelector, moduleType1, fontFamily;
	moduleSelector = targetSelector( module_id );
	moduleType1 = $(moduleSelector).children('[data-content-type1]').attr('data-content-type1');
	fontFamily = $( moduleSelector ).children( '[data-module-fontfamily]').data('module-fontfamily');

	switch ( moduleType1 ) {
		case 'form' :
			$( moduleSelector ).find('.form_title h4, .form_desc, .radio_check_group em, .form_items select, .form_items button, .form_items input, .form_items textarea, .form_type_text .form_items, .form_attach_filename' ).css('font-family', function () {
				return fontFamily;
			});
			break;
		case 'board' :
			$( moduleSelector ).find('.list_board li, .image_board .section_text li, .image_board .item_desc span, .faq_board .item_subject > a, select[name="board_category"], select[name="board_search_select"], button[name="board_search_button"], input[name="board_search_input"], .btn_write' ).css('font-family', function () {
				return fontFamily;
			});
			break;
		case 'boardRecent' :
			$( moduleSelector ).find('[data-content-type2="boardRecent-type-A"] .recent_board li, [data-content-type2="boardRecent-type-B"] .section_text li, [data-content-type2="boardRecent-type-B"] .item_desc span, [data-content-type2="boardRecent-type-C"] .faq_link' ).css('font-family', function () {
				return fontFamily;
			});
			break;
	}
}