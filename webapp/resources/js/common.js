 /**
  * 쿠키 데이터 변수
  /
  * @type {string}

 var COOKIEDATA = document.cookie;

 /**
  * 모바일 기기 검증 변수
  * 
  * @type {boolean}
  */
 var isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ? true : false;

 /**
  * 보안문자 새로고침 함수
  *
  * @param obj
  * @return {boolean}
  */
 function captchaReload(obj) {
    var imgObj = $(obj).closest('.captcha-lay').find('img');
    imgObj.click();

    return false;
}




/**
 * 자바스크립트 동적 호출 함수
 */
function dynamicLoadJs(url, implementationCode, location) {
    var scriptTag = document.createElement('script');
    scriptTag.src = url;
    scriptTag.onload = implementationCode;
    scriptTag.onreadystatechange = implementationCode;

    location.appendChild(scriptTag);
}

 /**
  * 문자 자릿수 채우기(str pad) 함수
  *
  * @description 문자열을 지정한 길이가 되도록 다른 문자열로 채우는 함수
  * @param str 문자
  * @param max 총 자릿수
  * @param pad 채울 문자
  * @param type 문자를 채울 위치
  * @return {*|string}
  */
 function str_pad (str, max, pad, type) {
    str = str.toString();
    if (type == 'right') {
        return str.length < max ? str_pad(str + pad, max) : str;
    } else {
        return str.length < max ? str_pad(pad + str, max) : str;
    }
}

/* 숫자 입력 필터링 */
function fnReplaceNumber(input, replaceType) {
    var result = input;
    
    var reg =/[^\d]/g;
    var regOnlyNumber = /[^\d]/g;
    var regWithComma = /[^\d\,]/g;
    var regWithCommaAndDot = /[^\d\,\.]/g;
    
    if (typeof(replaceType) == 'undefined') {
        replaceType = 'regWithCommaAndDot';
    }
    
    if (replaceType == 'regWithCommaAndDot') {
        reg = regWithCommaAndDot;
    } else if (replaceType == 'regWithComma') {
        reg = regWithComma;
    } else {
        reg = regOnlyNumber;
    }
    
    result = result + ''; // 문자로 변환
    result = result.replace(reg, '');
    return result;
}
function onlyNumberInput(obj) {
    var value = obj.val();
    if (typeof(value) !== 'undefined') {
        var replaceType = obj.attr('data-onlyNumberType'); 
        var replaceValue = fnReplaceNumber(value, replaceType);
        obj.val(replaceValue);
    }
}
/**
 * 변수 초기값 검증 함수
 */
function defineVar(value, init_value) {
    if (typeof (value) === 'undefined' || value === null || value === '') {
        value = init_value;
    }
    return value;
}

/**
 * 공통 알림창 호출 함수
 *
 * @param msg 내용
 * @param userfunc 콜백 함수
 */
function commonModalAlert(msg, userfunc) {
    msg = msg.replace(/<br\s*[\/]?>/gi, '\n');
    alert(msg);
    if (userfunc) {
        if (typeof(userfunc) == 'function'){
            var Func = userfunc;
        }else{
            var Func = window[userfunc];
        }
        Func();
    }
}

/* 난수 생성 */
var usedRandomNumber = [];
function randomNumber(){
    var i = 0;
    var createdRandomNumber = makeRandomNumber(i);
    while (usedRandomNumber.indexOf(createdRandomNumber) > -1) {
        createdRandomNumber = makeRandomNumber(i);
        i++;
    }
    usedRandomNumber.push(createdRandomNumber);
    return createdRandomNumber;
}
function makeRandomNumber(seed) {
    var time = new Date(),
        time = String(time.getTime()),
        idx = time.slice(7,-1);
    
    var createdRandomNumber = 'tmp_'+idx+seed;
    return createdRandomNumber;
}

 $(document).ready(function () {
     // ajax 속성 설정 이벤트
     $.ajaxSetup({
         headers: {
             'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
         }
     });
     
     $(document).on('keyup', '.onlyNumberInput', function () {
        onlyNumberInput($(this));
    });
 });

 function alertPageMoveBack(msg = '')
 {
    alert(msg);
    history.go(-1);
 }

 
/**
 * input 요소 입력 최대 길이 제한 함수
 *
 * @description input 엘리멘트의 maxlength 항목이 있으면 span.input-group-text 엘리벤트의 data-for 엘리멘트가
 * 해당 input의 id를 찾아 maxlength를 표시하고, 해당 maxlength를 넘지 않게 처리함.
 */
function maxLengthCheck() {
    // 최대 길이 값 유효성 검증
    $("[maxlength]:not([maxlength=''])").each(function() {
        var getLength = function($e) {
            var length = Number($e.val().length);
            var maxlength = Number($e.attr('maxlength'));
            return {'length':length, 'maxlength':maxlength};
        };

        var setLength = function(id, length, maxlength) {
            $("span[data-for='" + id + "']").html("<span class='cur_cnt'>"+length + "</span>/" + maxlength);
        };

        var lengthObj = getLength($(this));
        setLength($(this).attr('id'), lengthObj.length, lengthObj.maxlength);
        $(this).on('input', function() {
            var lengthObj = getLength($(this));
            if(lengthObj.length > lengthObj.maxlength) {
                $(this).val($(this).val().slice(0, lengthObj.maxlength));
                lengthObj = getLength($(this));
            }
            setLength($(this).attr('id'), lengthObj.length, lengthObj.maxlength);
        });
    });
}

/**
 * 다국어 메시지 파라미터 치환
 * 
 * @description 다국어 메시지에 포함된 :attribute를 파라미터값으로 치환
 */
function getExpandMessage(message, param){
    
    if(message === undefined){
        message = '';
    }

    if(param === undefined){
        param = {};
    }

    var reg = /:([a-z]*)/g;
    var arrAttr = message.match(reg);

    $.each(arrAttr,function(idx, attribute) {
        if(Object.keys(param).length !== 0){
            if(param[attribute] !== undefined){
                message = message.replace(attribute, param[attribute]);
            }
        }
    });

    return message;    
}

function callLink(el){
    event.preventDefault();
    var tel = $(el).attr('href');
    var target = $(el).is('[target]');
    if(isMobile){
        if(target === true){
            window.open("tel:"+tel);
        }else{
            location.href="tel:"+tel;
        }
    }else{
        alert(getExpandMessage(window.i18n.common.common.call_link_pc_not_use.message))
        return false;
    }
}