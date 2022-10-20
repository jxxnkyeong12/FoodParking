/** 옵션 데이터 표시 이벤트 핸들러 */
function formOptionRender(module_id) {
    var formModuleId, formCss;
    if (!module_id) {
        $('[data-form-pilsu-color]').each(function () {
            var bgColor = $(this).data('form-button-bg') ? $(this).data('form-button-bg') : 'transparent';
            $(this).find('.form_title h4 i').css('color', $(this).data('form-pilsu-color'));
            $(this).find('.formBtnBorder').css('border-color', $(this).data('form-button-border'));
            $(this).find('.formBtnBg').css('background-color', bgColor);
            $(this).find('.formBtnText').css('color', $(this).data('form-button-text'));

            formModuleId = targetSelector($(this).closest('[data-module-id]').data('module-id'));
            formCss = '<style class="form_css_active_color">' + formModuleId + ' [data-content-type1="form"] input.on, ' + formModuleId + ' [data-content-type1="form"] textarea.on, ' + formModuleId + ' [data-content-type1="form"] select.on { border-color:' + $(this).data('form-active-color') + ' } ' + formModuleId + ' [data-content-type1="form"] .radio_checkbox_type1 input:checked + em:before { box-shadow: 0 0 0 1px ' + $(this).data('form-active-color') + ' } ' + formModuleId + ' [data-content-type1="form"] .radio_checkbox_type1 input:checked + em:after { color: ' + $(this).data('form-active-color') + ' }</style>';
            $(formModuleId).append(formCss);
        });
    } else {
        formModuleId = targetSelector(module_id);
        var $formOptionSelector = $(formModuleId).find('[data-module-name]');
        var bgColor = $formOptionSelector.data('form-button-bg') ? $formOptionSelector.data('form-button-bg') : 'transparent';
        $(formModuleId).find('.form_title h4 i').css('color', $formOptionSelector.data('form-pilsu-color'));
        $(formModuleId).find('.formBtnBorder').css('border-color', $formOptionSelector.data('form-button-border'));
        $(formModuleId).find('.formBtnBg').css('background-color', bgColor);
        $(formModuleId).find('.formBtnText').css('color', $formOptionSelector.data('form-button-text'));

        $(formModuleId).find('style.form_css_active_color').remove();
        formCss = '<style class="form_css_active_color">' + formModuleId + ' [data-content-type1="form"] input.on, ' + formModuleId + ' [data-content-type1="form"] textarea.on, ' + formModuleId + ' [data-content-type1="form"] select.on { border-color:' + $formOptionSelector.data('form-active-color') + ' } ' + formModuleId + ' [data-content-type1="form"] .radio_checkbox_type1 input:checked + em:before { box-shadow: 0 0 0 1px ' + $formOptionSelector.data('form-active-color') + ' } ' + formModuleId + ' [data-content-type1="form"] .radio_checkbox_type1 input:checked + em:after { color: ' + $formOptionSelector.data('form-active-color') + ' }</style>';
        $(formModuleId).append(formCss);
    }
}

/**  폼 데이터 연동 이벤트 핸들러 */
function moduleFormRender(module_id) {
    var formModuleId;
    if (module_id) {
        formModuleId = targetSelector(module_id);
    } else {
        formModuleId = '[data-content-type1="form"]';
    }
    // ajax 비동기 처리를 위한 변수
    var formType;
    // 210604 - 기존 콘텐츠 노출 깜빡임 현상 제거
    $(formModuleId).find('form[name="form_details"] .form_contents').html('');
    // 컨택폼 구성 데이터 로드
    $.ajax({
        type: 'get',
        async: true,
        url: '/contactform',
        data: {},
        success: function (response) {
            // 컨택폼 구성 정보
            var formArr = response.contact_cfg.forms;
            var formGon = '';

            $.each(formArr, function (key) {
                var formName = formArr[key]["form_name"];
                var formDesc = formArr[key]["form_memo"];
                var formPlaceholder = formArr[key]["form_placeholder"];
                var formType = formArr[key]["form_type"];
                var formPilsu = (formArr[key]["form_required"] == 1) ? ' pilsu' : '';
                var formSeq = formArr[key]["sort_seq"];
                var formPlaceholder = formArr[key]["form_placeholder"];
                var formOptions = formArr[key]["form_options"];

                formGon += '<li class="form_type_' + formType + formPilsu + '">';
                formGon += '<ul class="form_section">';
                if (formType != 'text' && formType != 'submit') {
                    formGon += '<li class="form_texts">';
                    formGon += '<div class="form_title"><h4>' + formName + ' <span class="icon_pilsu"></span></h4></div>';
                    formGon += '<div class="form_desc"><p>' + formDesc + '</p></div>';
                    formGon += '</li>';
                }
                formGon += '<li class="form_items">';

                switch (formType) {
                    case 'input':
                        formGon += '<input type="text" name="form_' + formSeq + '" placeholder="' + formPlaceholder + '" />';
                        formGon += '<p class="word_counter"></p>';
                        break;
                    case 'phone':
                        formGon += '<ul class="item_article">';
                        if (formOptions.form_country_code_type == '1') {
                            formGon += '<li class="nation_select_area">';
                            formGon += '<select name="form_' + formSeq + '_code" class="phoneNationSelect">';
                            //formGon += 			nationList( formType );
                            formGon += '</select>';
                            formGon += '</li>';
                        }
                        formGon += '<li class="phone_number">';
                        formGon += '<input type="tel" name="form_' + formSeq + '" placeholder="' + formPlaceholder + '" />';
                        formGon += '</li>';
                        formGon += '</ul>';
                        break;
                    case 'textarea':
                        formGon += '<textarea name="form_' + formSeq + '" placeholder="' + formPlaceholder + '"></textarea>';
                        break;
                    case 'radio':
                        formGon += '<div class="radio_check_group">';
                        var chkVal;
                        $.each(formOptions.option_items, function (k) {
                            if (k == 0) chkVal = 'checked';
                            else chkVal = '';
                            formGon += '<label><input type="radio" name="form_' + formSeq + '" value="' + formOptions.option_items[k] + '" ' + chkVal + ' /><em>' + formOptions.option_items[k] + '</em></label>';
                        });
                        formGon += '</div>';
                        break;
                    case 'checkbox':
                        formGon += '<div class="radio_check_group" data-select-count=' + formOptions.multi_select_count + '>';
                        $.each(formOptions.option_items, function (k) {
                            formGon += '<label><input type="checkbox" name="form_' + formSeq + '[]" value="' + formOptions.option_items[k] + '" /><em>' + formOptions.option_items[k] + '</em></label>';
                        });
                        formGon += '</div>';
                        break;
                    case 'selectbox':
                        formGon += '<select name="form_' + formSeq + '">';
                        formGon += '<option value="">&nbsp;선택&nbsp;</option>';
                        $.each(formOptions.option_items, function (k) {
                            formGon += '<option value="' + formOptions.option_items[k] + '">&nbsp;' + formOptions.option_items[k] + '&nbsp;</option>';
                        });
                        formGon += '</select>';
                        break;
                    case 'address':
                        formGon += '<ul class="item_article2">';
                        formGon += '<li>';
                        if (formOptions.form_country_code_type == '1') {
                            formGon += '<select name="form_' + formSeq + '_code" class="addressNationSelect">';
                            //formGon += 			nationList( formType );
                            formGon += '</select>&nbsp;';
                        }
                        formGon += '<span class="addressKor">';
                        formGon += '<input type="text" name="form_' + formSeq + '_zipcode" class="zipCodeKor" style="width:80px;" placeholder="우편번호" readonly />&nbsp;<button type="button" name="btn_zip_search" class="btn_resp color4">검색</button>';
                        formGon += '</span>';
                        formGon += '<span class="addressOverseas">';
                        formGon += '<input type="text" name="form_' + formSeq + '_zipcode" style="width:200px;" placeholder="Zip / Postal Code" disabled="disabled" />';
                        formGon += '</span>';
                        formGon += '</li>';
                        formGon += '<li class="addressKor"><input type="text" name="form_' + formSeq + '_address" class="addressKor" readonly /></li>';
                        formGon += '<li class="addressKor"><input type="text" name="form_' + formSeq + '_address_detail" class="addressKorDetail" /></li>';
                        formGon += '<li class="addressOverseas"><input type="text" name="form_' + formSeq + '_address" placeholder="State / Region" disabled="disabled" /></li>';
                        formGon += '<li class="addressOverseas"><input type="text" name="form_' + formSeq + '_address_detail" placeholder="City" disabled="disabled" /></li>';
                        formGon += '<li class="addressOverseas"><input type="text" name="form_' + formSeq + '_address_detail2" placeholder="Detail Address" disabled="disabled" /></li>';
                        formGon += '</ul>';
                        break;
                    case 'email':
                        formGon += '<input type="email" name="form_' + formSeq + '" placeholder="' + formPlaceholder + '" />';
                        break;
                    case 'date':
                        formGon += '<label class="calendar_select">';
                        formGon += '<input type="text" name="form_' + formSeq + '_sdate" style="width:124px;" class="startDate" autocomplete="off" />';
                        formGon += '<i class="fa fa-calendar"></i>';
                        formGon += '</label>&nbsp;';
                        timeType = formOptions.time_select;
                        if (formOptions.time_select) {
                            formGon += '<select name="form_' + formSeq + '_stime" data-time-type="' + formOptions.time_select + '">';
                            //formGon += 	timeList( formOptions.time_select );
                            formGon += '</select>';
                        }
                        if (formOptions.form_range_select_type == '1') {
                            formGon += '<br class="mo_420_show" />&nbsp;~&nbsp;<br class="mo_420_show" /><label class="calendar_select">';
                            formGon += '<input type="text" name="form_' + formSeq + '_edate" style="width:124px;" class="endDate" autocomplete="off" />';
                            formGon += '<i class="fa fa-calendar calendar_icon"></i>';
                            formGon += '</label>&nbsp;';
                            if (formOptions.time_select) {
                                formGon += '<select name="form_' + formSeq + '_etime" data-time-type="' + formOptions.time_select + '">';
                                //formGon += 	timeList( formOptions.time_select );
                                formGon += '</select>';
                            }
                        }
                        break;
                    case 'captcha':
                        formGon += '<table class="captcha_table">';
                        formGon += '<tr>';
                        formGon += '<td rowspan="2" class="img_area"><div class="captchaDetail" title="Refresh"></div></td>';
                        formGon += '<td class="btn_area"><button class="btn_resp captcha_refresh"><i class="fa fa-refresh"></i></button></td>';
                        formGon += '</tr>';
                        formGon += '<tr>';
                        formGon += '<td class="input_area"><input type="text" id="captcha" name="captcha" class="captcha_input" placeholder="보안문자 입력" autocomplete="off"></td>';
                        formGon += '</tr>';
                        formGon += '</table>';
                        break;
                    case 'attach':
                        formGon += '<div class="form_attach_area">';
                        formGon += '<input type="hidden" name="form_' + formSeq + '_org_filename" class="fileName" />';
                        formGon += '<input type="hidden" name="form_' + formSeq + '_file_seq" class="fileSeq" />';
                        formGon += '<input type="hidden" name="form_' + formSeq + '_file" class="fileUrl" />';
                        formGon += '<button type="button" name="form_' + formSeq + '_button" class="form_attach_btn btn_resp color4" data-idx="' + formSeq + '">파일 선택</button>';
                        formGon += '<span class="form_attach_filename"></span>';
                        formGon += '</div>';
                        break;
                    case 'text':
                        formGon += formName;
                        break;
                    case 'submit':
                        formGon += '<button type="button" class="btn_resp form_send formBtnBorder formBtnBg formBtnText" onclick="formSend()">' + formName + '</button>';
                        break;
                }
                formGon += '</li>';
                formGon += '</ul>';
                formGon += '</li>';
            });

            if (module_id) { // 처음 넣는 경우
                $(formModuleId).find('.formTitle').text(response.contact_cfg.cf_name);
                $(formModuleId).find('.formDesc').text(response.contact_cfg.cf_memo);
            }
            $(formModuleId).find('form[name="form_details"] .form_contents').html(formGon);
        },
        complete: function () {
            formOptionRender(module_id);

            addressNationSelect(); // 주소 입력에서 국가 선택 옵션이 있는 경우
            phoneNationSelect();
            timeList();

            formDropzone();
            //inputWordLimit(); // 글자수 제한
            addressSearch();
            datepickerSingle('.singleDate', 'ko'); // Single Datepicker
            datepickerRange('.startDate', '.endDate', 'ko'); // Range Datepicker
            formCaptcha();
            formRadioDefaultCheck();
            formCheckboxMultiCheck(); // 체크박스 멀티 입력 Validation

            formUITypeA();

            // 210628 폰트 스타일 추가
            if (!module_id) {
                var moduleID = childFindModuleID2(formModuleId);
                moduleFontcolorRender(moduleID);
                moduleFontfamilyRender(moduleID);
            }
        }
    });

    if($(formModuleId).find('.resp_googlemap_inner').length > 0){
        if (module_id) {
            moduleMapRender($(formModuleId).children())
        }else{
            moduleMapRender($(formModuleId))
        }}
    }
    

/** 국가 목록 표시 이벤트 핸들러 */
function nationList(form_type) {
    var formNationGon = '';
    $.ajax({
        type: 'get',
        async: true,
        url: '/contactform/nationals',
        success: function (response) {
            // 국가 목록
            var nationals = response.nationals;
            $.each(nationals, function (key) {
                if (form_type == 'phone') {
                    formNationGon += '<option value="' + nationals[key].code + '">&nbsp;' + nationals[key].name + '(+' + nationals[key].code + ')&nbsp;</option>';
                } else if (form_type == 'address') {
                    formNationGon += '<option value="' + key + '">&nbsp;' + nationals[key].name + '&nbsp;</option>';
                }
            });
        },
        complete: function () {
            if (form_type == 'phone') {
                $('[data-content-type1="form"] .phoneNationSelect').each(function () {
                    $(this).html(formNationGon);
                });
            } else if (form_type == 'address') {
                $('[data-content-type1="form"] .addressNationSelect').each(function () {
                    $(this).html(formNationGon);
                    $(this).on('change', function () {
                        var $addressSelector = $(this).closest('.form_type_address');
                        if ($(this).val() == 'KOR') {
                            $addressSelector.find('.addressKor').show();
                            $addressSelector.find('.addressKor input').attr('disabled', false)
                            $addressSelector.find('.addressOverseas').hide();
                            $addressSelector.find('.addressOverseas input').attr('disabled', true);
                        } else {
                            $addressSelector.find('.addressKor').hide();
                            $addressSelector.find('.addressKor input').attr('disabled', true);
                            $addressSelector.find('.addressOverseas').show();
                            $addressSelector.find('.addressOverseas input').attr('disabled', false);
                        }
                    });
                });
            }
        }
    });
}

/** 시간 목록 표시 이벤트 핸들러 */
function timeList() {
    if (!$('[data-content-type1="form"] [data-time-type]').length) return false;

    var timeHourGon = '';
    var timeHalfGon = '';

    $.ajax({
        type: 'get',
        async: true,
        url: '/modules/json_configs/contactform_times',
        success: function (response) {
            $.each(response['hour'], function (key) {
                timeHourGon += '<option value="' + response['hour'][key]["val"] + '">&nbsp;' + response['hour'][key]["name"] + '&nbsp;</option>';
            });
            $.each(response['half'], function (key) {
                timeHalfGon += '<option value="' + response['half'][key]["val"] + '">&nbsp;' + response['half'][key]["name"] + '&nbsp;</option>';
            });
        },
        complete: function () {
            $('[data-time-type]').each(function () {
                if ($(this).data('time-type') == 'hour') {
                    $(this).html(timeHourGon);
                } else if ($(this).data('time-type') == 'half') {
                    $(this).html(timeHalfGon);
                }
            });
        }
    });
}

/** 첨부 파일 업로드 이벤트 핸들러 */
function formDropzone() {
    var $formDropZone;
    $('#contactForm .form_attach_area').one('mouseenter', '.form_attach_btn', function () {
        var dropzoneSelector = $(this);
        $formDropZone = dropzoneSelector.dropzone({
            url: '/contactform/upload',
            method: 'post',
            headers: {
                'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
            },
            parallelUploads: 1,
            success: function (file, serverRes) {
                var filename = serverRes.orgname;
                var fileurl = serverRes.fileurl;
                var fileSeq = serverRes.file_seq;

                dropzoneSelector.closest('.form_attach_area').find('.form_attach_filename').text(filename);
                dropzoneSelector.closest('.form_attach_area').find('.fileName').val(filename);
                dropzoneSelector.closest('.form_attach_area').find('.fileSeq').val(fileSeq);
                dropzoneSelector.closest('.form_attach_area').find('.fileUrl').val(fileurl);

                this.removeFile(file); // 메세지창 제거
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus.error);
            }
        });
    });
}

/** 폼 데이터 전송 이벤트 핸들러 */
function formSend() {
    var formData = $('#contactForm :not([disabled])').serialize();
    $.ajax({
        type: 'post',
        url: '/contactform',
        data: formData,
        success: function (res) {
            if (res.result == false) {
                alert(res.error);
                // 보안 문자 새로고침 처리
                if ($('#contactForm .form_type_captcha').length) {
                    $('#contactForm .form_type_captcha .captcha_refresh').click();
                    $('#contactForm .form_type_captcha input[name="captcha"]').blur();
                }
            } else {
                alert('전송되었습니다.');
                location.reload();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // 오류&유효성 문구 안내
            alert(jqXHR.responseJSON.error);
            // 포커스 이벤트
            var target = Object.keys(jqXHR.responseJSON.data)[0];
            $('#contactForm [name="' + target + '"').focus();
            // 자동입력방지 문구 새로고침
            if (target === 'captcha') {
                formCaptcha();
            }
        }
    });
}

/** 글자수 제한 이벤트 핸들러 */
/*
function inputWordLimit() {
	if ( !$('[data-content-type1="form"] .inputWordLimit').length ) return false;
	var wordLimit = 50;
	$('[data-content-type1="form"] .inputWordLimit').each(function() {
		$(this).next('.word_counter').html( 0 + '/' + wordLimit ); // 카운터 초기 세팅
		$(this).on('keyup', function() {
			var wordCount = $(this).val().length;
			var wordCountHtml = ( $(this).val().length > wordLimit ) ? '<span class="warnning_text">' + wordCount + '</span>' : wordCount;
			$(this).next('.word_counter').html( wordCountHtml + '/' + wordLimit );
		});
	});
}
*/

/** 라디오박스 기본 값 선택 이벤트 핸들러 */
function formRadioDefaultCheck() {
    if (!$('[data-content-type1="form"] .form_type_radio').length) return false;
    $('[data-content-type1="form"] .form_type_radio').each(function () {
        $(this).find('input[type="radio"]').first().attr('checked', true);
    });
}

/** 주소 국가 선택 이벤트 핸들러 */
function addressNationSelect() {
    if (!$('[data-content-type1="form"] .addressNationSelect').length) return false;
    nationList('address');
}

/** 전화번호 국가 코드 설정 이벤트 핸들러 */
function phoneNationSelect() {
    var cnt = $('[data-content-type1="form"] .phoneNationSelect').length
    if (!$('[data-content-type1="form"] .phoneNationSelect').length) return false;
    nationList('phone');
}

/** 주소 검색 이벤트 핸들러 */
function addressSearch() {
	if ( !$('[data-content-type1="form"] button[name="btn_zip_search"]').length ) return false;
	
	$('[data-content-type1="form"] button[name="btn_zip_search"]').on('click', function() {
		var daumPostElement = $('#daum_post_element').get(0);
		var $addressSelector = $(this).closest( '.form_type_address' );
		new daum.Postcode({
			width: '100%',
			oncomplete: function(data) {
                var address = getAddress(data);
				$addressSelector.find( 'input.zipCodeKor' ).addClass( 'on' ).val( address.zipcode ); // 우편번호
				$addressSelector.find( 'input.addressKor' ).addClass( 'on' ).val( address.streetFull ); // 주소 1단계
				$addressSelector.find( 'input.addressKorDetail' ).focus();
				hideCenterLayer();
			}
		}).embed(daumPostElement, {
			q: "",
			autoClose: false,
		});
		// embed를 통해 먼저 레이어 팝업에 그린 뒤, show 하도록 함
		showCenterLayer("#daumPostWrapElement[data-key='daum_post']");
	});
}

/** 단일 데이트피커 이벤트 핸들러 */
function datepickerSingle(singleS, lang) {
    if (!$(singleS).length || $('#main_pannel_frame', parent.document).length) return false; // froala, datepicker 충돌 처리
    var dp = $(singleS).datepicker({
        language: lang,
        showOtherMonths: false,
        selectOtherMonths: false,
    });
}

/** 기간 데이트피커 이벤트 핸들러 */
function datepickerRange(startS, endS, lang) {
    if (!$(startS).length || $('#main_pannel_frame', parent.document).length) return false; // froala, datepicker 충돌 처리
    $(startS).each(function () {
        var $dpSelectorS = $(this);
        $dpSelectorS.datepicker({
            language: lang,
            showOtherMonths: false,
            selectOtherMonths: false,
            onSelect: function (resDate) {
                $dpSelectorS.closest('li').find(endS).datepicker({
                    minDate: new Date(resDate),
                });
            }
        });
    });
    $(endS).each(function () {
        var $dpSelectorE = $(this);
        $dpSelectorE.datepicker({
            language: lang,
            showOtherMonths: false,
            selectOtherMonths: false,
            onSelect: function (resDate) {
                $dpSelectorE.closest('li').find(startS).datepicker({
                    maxDate: new Date(resDate),
                });
            }
        });
    });
}

/** 보안 문자 설정 이벤트 핸들러 */
function formCaptcha() {
    if (!$('[data-content-type1="form"] .form_type_captcha').length) return false;
    $('[data-content-type1="form"] .captchaDetail').load('/captcha/image_tag');
    $('[data-content-type1="form"]').on('click', '.captcha_refresh', function () {
        $(this).closest('.form_type_captcha').find('.captchaDetail > img').click();
        return false;
    });
}

/** 체크박스 멀티 입력 개수 유효성 검증 이벤트 핸들러 */
function formCheckboxMultiCheck() {
    $('[data-content-type1="form"] [data-select-count]').each(function () {
        var multiCheckLimit = Number($(this).data('select-count')); // 최대 가능 개수
        if (multiCheckLimit > 0) {
            var checkedLength; // 지정 개수
            $(this).find('input[type="checkbox"]').on('change', function () {
                checkedLength = $(this).closest('[data-select-count]').find('input[type="checkbox"]:checked').length;
                if (checkedLength > multiCheckLimit) {
                    alert('선택할 수 있는 최대 개수를 초과하였습니다.');
                    $(this).prop('checked', false);
                }
            });
        }
    });
}

/** form-type-A UI */
function formUITypeA() {
    // 폼 모듈에 기본 값이 있는 경우
    $('[data-content-type2="form-type-A"] .form_items input, [data-content-type2="form-type-A"] .form_items textarea, [data-content-type2="form-type-A"] .form_items select').each(function () {
        if ($(this).val()) $(this).addClass('on');
        else $(this).removeClass('on');
    });
    $('body').on('focus', '[data-content-type2="form-type-A"] .form_items input, [data-content-type2="form-type-A"] .form_items textarea', function () {
        $(this).addClass('on');
        $(this).on('blur', function () {
            if ($(this).val() == '') {
                $(this).removeClass('on');
            }
        });
    });
    $('body').on('change', '[data-content-type2="form-type-A"] .form_items select', function () {
        if ($(this).val()) $(this).addClass('on');
        else $(this).removeClass('on');
    });
}
