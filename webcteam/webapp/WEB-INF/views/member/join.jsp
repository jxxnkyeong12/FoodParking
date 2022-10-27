<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

html {
	height: 100%;
}

body {
	height: 100%;
	font-family: 'Noto Sans KR';
}

table, tr, td, th, div, p, em, ol, ul, li, dl, dt, dd, a, address, img,
	h1, h2, h3, h4, h5, h6 {
	font-size: 11pt;
	color: #666;
	text-decoration: none;
}

img {
	border: 0;
}

h1 {
	float: left;
	padding: 0 0 0 0px;
}

h2 {
	padding: 0 0 0 0px;
}

ul, li {
	list-style: none;
}

ul {
	list-style: none;
}

.red_txt {
	color: #ee7272;
}

.blind {
	display: block;
	overflow: hidden;
	position: absolute;
	left: 0;
	top: -5000px
}

.wrap {
	max-width: 960px;
	margin: 0 auto;
}

.wrap.wd668 {
	max-width: 628px;
}

.wrap.wd668.line {
	border: 1px solid #ececec;
	padding: 30px 20px;
}

.container {
	width: 100%;
}

.sub_tit_txt {
	margin: 80px 0 20px 20px;
	color: #2f2f2f;
	font-size: 25px;
}

.wrap.wd668.line .sub_tit_txt {
	margin: 0px 0 20px 20px;
}

.con_term .term_txt {
	border: 1px solid #ececec;
	padding: 30px;
	height: 210px;
	overflow-y: auto;
	margin: 0 0 15px;
}

.con_term .term_txt ul li, .con_term .term_txt p, .con_term .term_txt div
	{
	color: #818181;
	font-size: 12px;
	line-height: 17px;
	margin: 0 0 15px;
}

.left_margin {
	margin: 0 0 0 20px;
}

.con_term .term_txt div.txt_bold {
	font-weight: bold;
	margin: 0 0 25px;
}

.btn_wrap {
	text-align: center;
	margin: 40px 0 30px;
}

a.email_chk {
	text-align: center;
	float: right;
	color: #fff;
	background-color: #F25C05;
	font-weight: bold;
	padding: 7px;
	height: 34px;
	line-height: 21px;
	width: 100px;
	display: block;
	text-align: center;
	margin: 0 auto;
}

.btn_wrap a {
	color: #fff;
	background-color: #F25C05;
	font-weight: bold;
	padding: 10px;
	height: 52px;
	line-height: 30px;
	width: 168px;
	display: block;
	text-align: center;
	margin: 0 auto;
}

.btn_wrap a.wide {
	width: 587px;
	margin: 0 0 0 20px;
}

.find_txt ul li {
	color: #9a9a9a;
	font-size: 13px;
	text-align: center;
	line-height: 17px;
	margin: 0 0 20px;
}

.form_txtInput .checkbox_wrap {
	position: relative;
	padding: 5px;
	text-align: right;
}

.form_txtInput .checkbox_wrap input[type="checkbox"] {
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}

.form_txtInput .checkbox_wrap input[type="checkbox"]+label {
	display: inline-block;
	line-height: 14px;
	position: relative;
	padding-left: 20px;
	font-size: 13px;
	color: #818181;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
}

.form_txtInput .checkbox_wrap input[type="checkbox"]+label:before {
	content: '';
	position: absolute;
	top: 0px;
	left: 0;
	width: 12px;
	height: 12px;
	text-align: center;
	background: #fff;
	border: 1px solid #c2c2c2;
}

.form_txtInput .checkbox_wrap input[type="checkbox"]+label:active:before,
	.checkbox_wrap input[type="checkbox"]:checked+label:active:before {
	box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px 1px 3px
		rgba(0, 0, 0, 0.1);
}

.form_txtInput .checkbox_wrap input[type="checkbox"]:checked+label:before
	{
	background: #fff;
	border-color: #ddd;
}

.form_txtInput .checkbox_wrap input[type="checkbox"]:checked+label:after
	{
	content: '✓';
	color: #0094ff;
	position: absolute;
	top: 0px;
	left: 2px;
	width: 7px;
	height: 7px;
	font-size: 13px;
	font-weight: bold;
}

.exTxt {
	font-size: 14px;
	color: #9a9a9a;
	display: block;
	margin: 0 0 45px 20px;
}

.join_form {
	width: 100%;
	max-width: 668px;
}

.join_form table {
	border-spacing: 0;
	margin: 0;
	padding: 0;
	border: 0;
	width: 100%;
}

.join_form table input {
	border: 1px solid #ececec;
	font-size: 14px;
	color: #000000;
	height: 35px;
	padding: 10px;
	float: left;
}

.join_form table input.email {
	width: 75%;
	display: inline-block;
}

.join_form table input.email2 {
	width: 40%;
}

.join_form.idpwFind table input {
	width: 519px;
}

.join_form.idpwFind table input.email {
	width: 223px;
}

.join_form.idpwFind table input.email2 {
	width: 263px
}

.join_form.idpwFind table input.phone {
	width: 182px;
	margin: 0 12px 0 11px;
}

.join_form.idpwFind table input.phone2 {
	width: 182px;
}

.join_form table input.send_number {
	width: 383px;
}

.join_form table th span {
	color: #404040;
	font-size: 14px;
	display: inline-block;
	position: relative;
	padding: 0 10px 0 0;
	text-indent: 20px;
}

.join_form table th {
	text-align: left;
}

.join_form table td {
	padding: 15px 0;
}

.join_form table th span:after {
	content: '*';
	font-size: 13px;
	color: #f95427;
	position: absolute;
	top: 0;
	right: 0px;
}

.join_form.idpwFind table th span:after {
	content: none;
}

.join_form table td a.btn_confirm {
	display: inline-block;
	float: left;
	width: 115px;
	height: 48px;
	margin: 5px 0 0 0px;
	border: 1px solid #cfcfcf;
	background: #dedede;
	color: #626262;
	text-align: center;
	vertical-align: top;
	line-height: 50px;
}

.join_form.idpwFind table td select {
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
	background:
		url(https://secsales.interparkb2b.co.kr/data/G94/main/sele_arr.gif)
		no-repeat 95% 50%;
	text-indent: 20px;
	width: 124px;
	height: 50px;
	border: 1px solid #ececec;
	font-size: 14px;
	color: #ccc;
	float: left;
}

.join_form.idpwFind table td select::-ms-expand {
	display: none;
}

.join_form.idpwFind table td  tr.phone td select {
	text-align: center;
	text-indent: 10px;
}

.join_form.idpwFind table tr.email input:after, .mar10:after, .join_form.idpwFind table td select:after,
	.join_form table td a.btn_confirm:after {
	content: '';
	display: block;
	clear: both;
}

.agree_wrap {
	margin: 36px 0 0;
}

.agree_wrap .checkbox_wrap {
	text-align: left;
}

::placeholder {
	color: #c4c4c4;
	opacity: 1;
}

:-ms-input-placeholder {
	color: #c4c4c4 !important;
}

::-ms-input-placeholder {
	color: #c4c4c4 !important;
}

.mar27 {
	margin: 27px 0 0;
}

.mar10 {
	margin: 7px 8px 0 8px;
	display: inline-block;
	padding: 0;
	float: left;
}

.exform_txt {
	padding: 12px 0 19px 0;
	text-align: right;
	color: #9a9a9a;
	font-size: 13px;
	border-bottom: 1px solid #ececec;
}

.exform_txt span {
	display: inline-block;
	position: relative;
	padding-left: 10px
}

.exform_txt span:after {
	content: '*';
	position: absolute;
	left: 0;
	top: 0;
	color: #f95427;
	font-size: 13px;
	font-weight: bold;
}

.explan_txt {
	margin: 3px 0 0 22px;
}

.explan_txt li {
	color: #b2b2b2;
	font-size: 13px;
	line-height: 17px;
}

.popWrap {
	border: 1px solid #ececec;
	padding: 30px;
	max-width: 400px;
	width: 100%;
	margin: 30px auto 0;
	display: none;
	position: absolute;
	top: 0;
	left: 50%;
	transform: translateX(-50%);
	background: #fff;
	z-index: 5;
}

.popWrap.is-open {
	display: block;
}

.popWrap .pop_txt_con .pop_exTxt {
	font-size: 14px;
	color: #9a9a9a;
	line-height: 20px;
}

.popWrap .pop_txt_con .pop_exTxt span {
	color: #254ee9
}

.popWrap .pop_btnWrap {
	margin: 30px 0 0;
}

.popWrap .pop_btnWrap a {
	color: #fff;
	background-color: #4380ce;
	font-weight: bold;
	padding: 10px;
	height: 22px;
	line-height: 22px;
	width: 103px;
	display: block;
	text-align: center;
}

.join_form table input.send_number::placeholder {
	text-align: right;
}

.join_form table input.send_number:-ms-input-placeholder {
	text-align: right;
}

.join_form table input.send_number::-ms-input-placeholder {
	text-align: right;
}

.overlayer {
	position: fixed;
	display: none;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: rgba(0, 0, 0, 0.5);
	z-index: 2;
	cursor: pointer;
}

input[name=phone1], input[name=phone2], input[name=phone3] {
	width: 30%
}

input[name=name], input[name=pw], input[name=pw_ck], input[name=b_num],
	input[name=addr], input[name=addr_more] {
	width: 100%
}

input[name=addr] {
	margin-top: 8px;
	margin-bottom: 8px;
}

.overlayer.is-open {
	display: block;
}

div.valid {
	color: #bababa;
	font-size: 14px;
	float: left;
	margin-top: 2px;
}

div.chked {
	color: #bababa;
	font-size: 14px;
	float: left;
	margin-top: 2px;
	width: 100%;
}

div.invalid {
	color: #ff0000;
	font-size: 14px;
	float: left;
	margin-top: 2px;
	width: 100%;
}

@media ( max-width : 1023px ) {
	.wrap {
		margin: 30px auto;
	}
	.wrap, .wrap.wd668.line {
		max-width: 80vw;
	}
	.wrap.wd668 {
		width: 90vw;
	}
	.join_form {
		max-width: 90vw;
	}
	.join_form table th span {
		text-indent: 0;
		font-size: 13px;
	}
	.join_form table {
		width: 100%;
	}
	.join_form table input {
		border: 1px solid #ececec;
		font-size: 14px;
		color: #4c4c4c;
		height: 37px;
		padding: 10px;
		width: 100%;
	}
	.join_form table input.email {
		width: 31%;
		display: inline-block;
	}
	.join_form table input.email2 {
		width: 60%;
	}
	.join_form.idpwFind table input {
		width: 100%;
	}
	.join_form.idpwFind table tr.email input {
		float: left;
	}
	.mar10 {
		margin: 5px 2% 0 2%;
		float: left;
	}
	.join_form.idpwFind table input.email {
		width: 75%;
	}
	.join_form.idpwFind table input.email2 {
		width: 53%
	}
	.join_form.idpwFind table input.phone {
		width: 30%;
		margin: 0 4% 0 4%;
	}
	.join_form.idpwFind table input.phone2 {
		width: 31%;
	}
	.join_form table input.send_number {
		width: 53%;
	}
	.exTxt {
		margin: 0 0 25px 0px
	}
	.wrap.wd668.line .sub_tit_txt, .wrap .sub_tit_txt {
		margin: 0 0 10px 0;
	}
	.btn_wrap a, .btn_wrap a.wide {
		width: auto;
		margin: 0;
	}
	.btn_wrap {
		overflow: hidden;
	}
	.email_chk {
		text-align: center;
		width: 100%;
		float: left;
	}
	.email_chk a {
		color: #fff;
		background-color: #4380ce;
		font-weight: bold;
		padding: 7px;
		height: 34px;
		line-height: 21px;
		width: 100%;
		display: block;
		text-align: center;
		margin: 8px auto;
	}
	.join_form.idpwFind table td select {
		width: 30%;
		height: 37px;
		float: left;
	}
	.join_form.idpwFind table tr.email input:after, .mar10:after, .join_form.idpwFind table td select:after
		{
		content: '';
		display: block;
		clear: both;
	}
	.popWrap {
		right: auto;
		max-width: 80vw;
		margin: 30px auto 0;
		left: 50%;
		transform: translateX(-50%);
	}
	.join_form table td a.btn_confirm {
		width: 100px;
		height: 35px;
		line-height: 35px;
		font-size: 12px;
		margin: 5px 0 0;
	}
	.join_form table input.send_number+a {
		margin: 0 0 0 5px;
	}
}
</style>
</head>
<body>
	<form method="post" action="join">
		<div class="wrap wd668">
			<div class="container">
				<div class="form_txtInput">
					<h2 class="sub_tit_txt">회원가입</h2>
					<p class="exTxt">회원가입시 이메일 인증을 반드시 진행하셔야 합니다.</p>
					<div class="join_form">
						<table>
							<colgroup>
								<col width="30%" />
								<col width="auto" />
							</colgroup>
							<tbody>
								<tr class="email">
									<th><span>이메일</span></th>
									<td><input type="text" name="email" class="email chk"
										placeholder="food@parking.com" autofocus> <a
										class="email_chk" onclick="email_check()"
										style="cursor: pointer;">중복확인</a>
										<div class="valid">이메일을 입력하세요</div></td>
								</tr>

								<tr>
									<th><span>이름</span></th>
									<td><input type="text" name="name" class="chk"
										placeholder="김푸파">
										<div class="valid">이름을 입력하세요 (순수한글만 가능)</div></td>
								</tr>
								<tr>
									<th><span>비밀번호</span></th>
									<td><input type="password" name="pw" class="chk"
										placeholder="비밀번호를 입력해주세요.">
										<div class="valid">비밀번호를 입력하세요 (영문 대/소문자, 숫자 모두 포함)</div></td>
								</tr>
								<tr>
									<th><span>비밀번호 확인</span></th>
									<td><input type="password" name="pw_ck" class="chk"
										placeholder="비밀번호를 확인하세요">
										<div class="valid">비밀번호를 다시 입력하세요</div></td>
								</tr>

								<tr>
									<th><span>사업자번호</span></th>
									<td><input type="text" name="b_num" maxlength="12"
										placeholder="사업자 번호를 입력하세요."></td>
								</tr>

								<tr>
									<th><span>핸드폰 번호</span></th>
									<td><input type="text" name="phone1" value="010" readonly>
										<span class="mar10">-</span> <input type="text" name="phone2"
										placeholder="0000" maxlength="4"><span class="mar10">-</span>
										<input type="text" name="phone3" placeholder="0000"
										maxlength="4"></td>
								</tr>

								<tr>
									<th><span>주소</span></th>
									<td><a class="email_chk" onclick="post()" style="cursor: pointer;">우편번호 찾기</a> <input
										type="text" name="post" readonly> <input type="text"
										name="addr" class="full" readonly> <input type="text"
										name="addr_more" class="full"></td>
								</tr>
						</table>
						<div class="exform_txt">
							<span>표시는 필수적으로 입력해주셔야 가입이 가능합니다.</span>
						</div>
					</div>
					<!-- join_form E  -->
					<div class="agree_wrap">
						<div class="checkbox_wrap">
							<input type="checkbox" id="news_letter" name="news_letter"
								class="agree_chk"> <label for="news_letter">[선택]뉴스레터
								수신동의</label>
						</div>
						<div class="checkbox_wrap mar27">
							<input type="checkbox" id="marketing" name="marketing"
								class="agree_chk"> <label for="marketing">[선택]마케팅
								목적 개인정보 수집 및 이용에 대한 동의</label>
							<ul class="explan_txt">
								<li><span class="red_txt">항목 : 성별, 생년월일</span></li>
								<li>고객님께서는 위의 개인정보 및 회원정보 수정 등을 통해 추가로 수집하는 개인정보에<br /> 대해
									동의하지 않거나 개인정보를 기재하지 않음으로써 거부하실 수 있습니다.<br /> 다만 이때 회원 대상 서비스가
									제한될 수 있습니다.
								</li>
							</ul>
						</div>
					</div>
					<div class="btn_wrap">
						<a onclick='join()' style="cursor: pointer;">회원가입</a>
					</div>
				</div>
				<!-- form_txtInput E -->
			</div>
			<!-- content E-->
		</div>
		<!-- container E -->
	</form>

	<script src='js/member.js?<%=new java.util.Date()%>'></script>

	<script>
//회원가입 처리
function join() {
	if($('[name=email]').hasClass('chked')) {
		if($('[name=email]').siblings('div').hasClass('invalid')) {
			alert('이미 사용 중인 이메일 \n' + member.email.valid.desc);
			return;
		}//if
	}else {
		if(tagIsInvalid($('[name=email]'))) return;
		else {
			alert('중복 확인 안 함 \n' + member.email.valid.desc);
			$('[name=email]').focus();
			return
		}
	}//if
	
	if(tagIsInvalid($('[name=pw]'))) return;
	if(tagIsInvalid($('[name=pw_ck]'))) return;
	if(tagIsInvalid($('[name=name]'))) return;
	if(tagIsInvalid($('[name=b_num]'))) return;
	
	$('form').submit();
}


//입력 상태가 invalid면 폼 제출 불가
function tagIsInvalid(tag) {
	var status = member.tag_status(tag);
	if( status.code == 'invalid') {
		alert('회원 가입이 불가능합니다 \n' + status.desc);
		tag.focus();
		return true;
	}else {
		return false;
	}//if
}//tagIsInvalid()


//이메일 중복확인
function email_check() {
	var $email = $('[name = email]');
	
	if($email.hasClass('chked')) return;
	
	var status = member.tag_status($email);
	if(status.code == 'invalid') {
		alert('이메일 중복확인 불필요 \n' + status.desc);
		$email.focus();
		return;
	}
	
	$.ajax({
		url: 'email_check',
		data : {email : $email.val()},
		success: function(response) {
			response = member.email_check(response);
			$email.siblings('div').text(response.desc).removeClass().addClass(response.code);
			$email.addClass('chked');
		}, error: function(req, status) {
			alert(status + ':' + req.status);
		}
	});
}


//체크 클래스
$('.chk').keyup(function(e){
	//이메일를 중복확인한 후, 다시 입력한다면 중복확인하지 않은 상태를 처리
	if($(this).hasClass('chked')) $(this).removeClass('chked');
	
	var status = member.tag_status($(this));
	$(this).siblings('div').text(status.desc).removeClass().addClass(status.code);
})



//우편번호 및 주소 설정
function post() {
	new daum.Postcode({
		oncomplete : function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
			// 예제를 참고하여 다양한 활용법을 확인해 보세요.

			console.log(data); //파라매터 콘솔(크롬)으로 확인
			var address = data.userSelectedType == 'R' ? data.roadAddress
					: data.jibunAddress;

			//빌딩 이름이 있는 경우
			if (data.buildingName != '')
				address += " (" + data.buildingName + ")";
			$('[name = addr]').eq(0).val(address);
			$('[name = post]').val(data.zonecode);
		}
	}).open();
}


//핸드폰 번호 4자리 입력하면 자동 탭
$('[name=phone2]').on('keyup', function() {
	if(this.value.length == 4) {
		$('[name=phone3]').focus()
	}
});


//사업자 번호 자동 하이픈
$('[name=b_num]').on('keyup', function(){
	var num = $('[name=b_num]').val();
	num.trim(); 
	this.value = AutoHypen(num) ;
});

function AutoHypen(b_num){
	b_num = b_num.replace(/[^0-9]/g, '');
    var tmp = '';   

    if(b_num.length < 4){
		return b_num;
    }else if(b_num.length < 6){
    	tmp += b_num.substr(0,3);
    	tmp += '-';
    	tmp += b_num.substr(3,2);
		return tmp;
    }else if(b_num.length < 11){
    	tmp += b_num.substr(0,3);
    	tmp += '-';
    	tmp += b_num.substr(3,2);
    	tmp += '-';
		tmp += b_num.substr(5);
      return tmp;
    }else{
    	tmp += b_num.substr(0,3);
		tmp += '-';
		tmp += b_num.substr(3,2);
      	tmp += '-';
      	tmp += b_num.substr(5);
      return tmp;
    }
  }
</script>
</body>
</html>