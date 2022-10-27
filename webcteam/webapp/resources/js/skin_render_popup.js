/* Show */
function showPopupModule( popup_selector, popup_position ) {
	switch ( popup_position ) {
		case 'left-top' :
			$( popup_selector ).css({
				'left': '10px',
				'top': '10px'
			});
			break;
		case 'top' :
			$( popup_selector ).css({
				'left': '50%',
				'top': '10px',
				'marginLeft': ( $( popup_selector ).outerWidth() / 2 ) * -1
			});
			break;
		case 'right-top' :
			$( popup_selector ).css({
				'right': '10px',
				'top': '10px'
			});
			break;
		case 'left' :
			$( popup_selector ).css({
				'left': '10px',
				'top': '50%',
				'marginTop': ( $( popup_selector ).outerHeight() / 2 ) * -1
			});
			break;
		case 'center' :
			$( popup_selector ).css({
				'left': '50%',
				'top': '50%',
				'marginLeft': ( $( popup_selector ).outerWidth() / 2 ) * -1,
				'marginTop': ( $( popup_selector ).outerHeight() / 2 ) * -1
			});
			break;
		case 'right' :
			$( popup_selector ).css({
				'right': '10px',
				'top': '50%',
				'marginTop': ( $( popup_selector ).outerHeight() / 2 ) * -1
			});
			break;
		case 'left-bottom' :
			$( popup_selector ).css({
				'left': '10px',
				'bottom': '10px'
			});
			break;
		case 'bottom' :
			$( popup_selector ).css({
				'left': '50%',
				'bottom': '10px',
				'marginLeft': ( $( popup_selector ).outerWidth() / 2 ) * -1
			});
			break;
		case 'right-bottom' :
			$( popup_selector ).css({
				'right': '10px',
				'bottom': '10px'
			});
			break;
	}

	$('#popup_module_modal').show();
	$( popup_selector ).show();
}

/* Hide */
function hidePopupModule( popup_selector ) {
	$( popup_selector ).hide();
	if ( $('#popupArea .popup_module:visible').length < 1 ) {
		$('#popup_module_modal').hide();
	}
}


/* 오늘 하루 열지 않음 */
function todayPopupClose( popup_id ) {
	var cookieName = popup_id + '_cookie';
	setCookie( cookieName, "done" , 24 );
	hidePopupModule( '#' + popup_id );
}

/* 쿠키를 맛있게 구워요 */
function setCookie( name, value, expirehours ) {
	var todayDate = new Date();
	todayDate.setHours( todayDate.getHours() + expirehours );
	document.cookie = name + '=' + escape( value ) + '; path=/; expires=' + todayDate.toGMTString() + ';';
}

/* Popup Display Control( 오늘 하루 닫기 포함 ) */
function popupModuleDisplay( popup_id, popup_position, display_pc, display_mobile ) {
	var cookieName = popup_id + '_cookie';

	if ( COOKIEDATA.indexOf( cookieName + '=done' ) < 0 ) {
		//console.log( cookieName + ' END' );
		$( '#' + popup_id ).find('img').one('load', function() {
			if ( display_pc == 'Y' && !isMobile ) {
				showPopupModule( '#' + popup_id, popup_position );
			}
			if ( display_mobile == 'Y' && isMobile ) {
				showPopupModule( '#' + popup_id, popup_position );
			}
		});
	} else {
		hidePopupModule( '#' + popup_id );
	}
}

/* Popup Ajax */
function popupAjax( popup_page ) {
	var popupUrl = ( popup_page == 'all' ) ? '/popups/all' : '/popups/main';
	$.ajax({
		url : popupUrl,
		dataType : 'json',
		success : function( response ) {
			var popupArray = response.popups;
			var popupZindex;
			var popupLinkTarget;
			$.each( popupArray, function( key ) {
				if ( $( '#popupArea_' + popupArray[key].popup_target ).length ) { // 팝업 대상 영역이 존재한다면
					popupZindex = 2000 - popupArray[key].popup_index;
					popupLinkTarget = ( popupArray[key].popup_link_target == 'WINDOW' ) ? 'target="_blank"' : '';

					var popupGon = '';
					popupGon += '<div class="popup_module" id="popup_' + popupArray[key].popup_seq + '" data-popup-target="' + popupArray[key].popup_target + '" data-popup-position="' + popupArray[key].popup_pc_position + '" style="width:' + popupArray[key].popup_width + 'px; z-index:' + popupZindex + ';">';
					popupGon +=  	'<div class="popup_module_wrap">';
					popupGon +=  		'<div class="popup_module_container">';
					if ( !popupArray[key].popup_link ) {
						popupGon +=  			'<img src="' + popupArray[key].image_url + '" alt="' + popupArray[key].popup_name + '" class="popup_image" />';
					} else {
						popupGon +=  			'<a href="' + popupArray[key].popup_link + '" ' + popupLinkTarget + '><img src="' + popupArray[key].image_url + '" alt="' + popupArray[key].popup_name + '" class="popup_image" /></a>';
					}
					popupGon +=  		'</div>';
					popupGon +=  	'</div>';
					popupGon +=  	'<ul class="popup_module_footer">';
					popupGon +=  		'<li class="today_close_area">';
					if ( popupArray[key].popup_hide_not_open != 'Y' ) {
						popupGon += 			'<label><input type="checkbox" name="today_close" />' + popupArray[key].popup_phrase_not_open + '</label>';
					}
					popupGon +=  		'</li>';
					popupGon +=  		'<li class="close_area">';
					popupGon +=  			'<a href="#none" class="popupClose">' + popupArray[key].popup_phrase_close + '</a>';
					popupGon +=  		'</li>';
					popupGon +=  	'</ul>';
					popupGon += '</div>';

					$( '#popupArea_' + popupArray[key].popup_target ).append( popupGon ); // Append

					// Display
					var popupID = 'popup_' + popupArray[key].popup_seq;
					var popupPosition = popupArray[key].popup_pc_position;
					var displayPC = popupArray[key].popup_device_pc;
					var displayMobile = popupArray[key].popup_device_mobile;
					popupModuleDisplay( popupID, popupPosition, displayPC, displayMobile );
				}
			});
		}
	});
}


$(function() {
	// 팝업 실행
	popupAjax( 'all' );

	// 모달 클릭
	$('#popup_module_modal').click(function() {
		$(this).hide();
	});

	// 팝업 닫기
	$('#popupArea').on('click', '.popupClose', function( e ) {
		var popupID = $(this).closest( '.popup_module' ).attr( 'id' );
		hidePopupModule( '#' + popupID );
		e.preventDefault();
	});

	// 오늘 하루 열지 않음
	$('#popupArea').on('change', 'input[name="today_close"]', function() {
		if ( $(this).prop( 'checked' ) ) {
			var popupID = $(this).closest( '.popup_module' ).attr( 'id' );
			todayPopupClose( popupID );
		}
	});
});
