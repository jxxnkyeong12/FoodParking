<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<%-- <link rel="stylesheet" href="css/common.css?<%= new Date() %>" type="text/css" > --%>


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
	color: #fff;
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

.btn_wrap a {
	color: #fff;
	background-color: #F25C05;
	font-weight: bold;
	padding: 10px;
	height: 51px;
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

.enter_form {
	width: 100%;
	max-width: 668px;
}

.enter_form table {
	border-spacing: 0;
	margin: 0;
	padding: 0;
	border: 0;
	width: 100%;
}

.enter_form table input {
	border: 1px solid #ececec;
	font-size: 14px;
	color: #4c4c4c;
	height: 34px;
	padding: 10px;
	width: 100%;
	float: left;
}

.enter_form table input.email {
	width: 162px;
	display: inline-block;
}

.enter_form table input.email2 {
	width: 192px;
}

.enter_form.idpwFind table input {
	width: 519px;
}

.enter_form.idpwFind table input.email {
	width: 223px;
}

.enter_form.idpwFind table input.email2 {
	width: 263px
}

.enter_form.idpwFind table input.phone {
	width: 182px;
	margin: 0 12px 0 11px;
}

.enter_form.idpwFind table input.phone2 {
	width: 182px;
}

.enter_form table input.send_number {
	width: 383px;
}

.enter_form table th span {
	color: #404040;
	font-size: 15px;
	display: inline-block;
	position: relative;
	padding: 0 10px 0 0;
	text-indent: 20px;
}

.enter_form table th {
	text-align: left;
}

.enter_form table td {
	padding: 6px 0;
	position: relative;
}

.enter_form table td:nth-child(4) {
	padding: 6px 0;
	width: 60px;
}

.enter_form table th span:after {
	content: '*';
	font-size: 13px;
	color: #f95427;
	position: absolute;
	top: 0;
	right: 0px;
}

.enter_form.idpwFind table th span:after {
	content: none;
}

.enter_form table td a.btn_confirm {
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

.enter_form.idpwFind table td select {
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

.enter_form.idpwFind table td select::-ms-expand {
	display: none;
}

.enter_form.idpwFind table td  tr.phone td select {
	text-align: center;
	text-indent: 10px;
}

.enter_form.idpwFind table tr.email input:after, .mar10:after,
	.enter_form.idpwFind table td select:after, .enter_form table td a.btn_confirm:after
	{
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
	margin: 14px 5px 0 10px;
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

.enter_form table input.send_number::placeholder {
	text-align: right;
}

.enter_form table input.send_number:-ms-input-placeholder {
	text-align: right;
}

.enter_form table input.send_number::-ms-input-placeholder {
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

.overlayer.is-open {
	display: block;
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
	.enter_form {
		max-width: 90vw;
	}
	.enter_form table th span {
		text-indent: 0;
		font-size: 13px;
	}
	.enter_form table {
		width: 100%;
	}
	.enter_form table input {
		border: 1px solid #ececec;
		font-size: 16px;
		color: #4c4c4c;
		height: 37px;
		padding: 10px;
		width: 100%;
	}
	.enter_form table input.email {
		width: 30%;
		display: inline-block;
	}
	.enter_form table input.email2 {
		width: 60%;
	}
	.enter_form.idpwFind table input {
		width: 100%;
	}
	.enter_form.idpwFind table tr.email input {
		float: left;
	}
	.mar10 {
		margin: 5px 2% 0 2%;
		float: left;
	}
	.enter_form.idpwFind table input.email {
		width: 36%;
	}
	.enter_form.idpwFind table input.email2 {
		width: 53%
	}
	.enter_form.idpwFind table input.phone {
		width: 30%;
		margin: 0 4% 0 4%;
	}
	.enter_form.idpwFind table input.phone2 {
		width: 31%;
	}
	.enter_form table input.send_number {
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
	.enter_form.idpwFind table td select {
		width: 30%;
		height: 37px;
		float: left;
	}
	.enter_form.idpwFind table tr.email input:after, .mar10:after,
		.enter_form.idpwFind table td select:after {
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
	.enter_form table td a.btn_confirm {
		width: 100px;
		height: 35px;
		line-height: 35px;
		font-size: 12px;
		margin: 5px 0 0;
	}
	.enter_form table input.send_number+a {
		margin: 0 0 0 5px;
	}
}

.btn-fill, a.btn-empty {
	cursor: pointer;
	height: 28px;
	padding: 3px 15px;
	border: 1px solid #F25C05;
	width: 114px;
	border-radius: 3px;
	/* 	box-shadow: 2px 2px 3px #6a438f; */
}

/* 채워진 버튼 하나 */
.btn-fill {
	background-color: #F25C05;
	color: #fff;
}
/* 안채워진 버튼! */
.btn-empty {
	background-color: #fff;
	color: #9262bf;
}

a.btn-fill {
	float: right;
	position: absolute;
	border: 1px solid #F25C05;
	background-color: #F25C05;
	color: #fff;
	width: 60px;
	margin-left: 5px;
}

label a img {
	width: 30px;
	height: 30px;
}

#img-delete, #img-delete2 {
	widows: 13px;
	height: 13px;
}

[name=address] {
	margin-top: 3px
}

.attach-file, .attach-file2, .delete-file, .delete-file2 {
	display: none;
}

div.valid {
	color: #008000;
	font-size: 14px;
	font-weight: bold;
	margin-top: 3px;
}

div.invalid {
	color: #ff0000;
	font-size: 14px;
	font-weight: bold;
	margin-top: 3px;
}

.enter_form table td input:nth-child(2 ) {
	width: 85px;
}

.file-name, .file-name2 {
	color: #404040;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <div class="wrap wd668">
      <div class="container">
        <div class="form_txtInput">
          <h2 class="sub_tit_txt">입점신청</h2>
          <p class="exTxt">사업자등록번호 인증을 반드시 진행하셔야 합니다.</p>
          <div class="enter_form">
            <form method="post" action="insert.en" enctype="multipart/form-data">
             <input type = 'hidden' name = 'id' value = '${loginInfo.id}'>
 			 
            <table>
              <colgroup>
                <col width="30%"/>
                <col width="auto"/>
            
              </colgroup>
              
              
              
              <tbody>
                <tr>
                  <th><span>사업자등록번호</span></th>
                  <td> 
                  <input type="text"  id='b_num'  name = 'b_num' placeholder="사업자등록번호를 입력하세요"    maxlength="12" class = 'chk ' >
                  <a class = 'btn-fill' onclick="b_check()"> 확인 </a>
                  <div class = 'valid' ></div>
                  </td>
                
                </tr>
                <tr>
                  <th><span>대표자명</span></th>
                  <td><input type="text"  name = 'b_name' placeholder="대표자명을 입력해주세요" class = 'chk'>
                   <div class = 'valid' ></div>
                  </td>
                </tr>
             
                <tr>
                  <th><span>대표자 휴대폰 번호</span></th>
                  <td>
                    <input type="text" id ="b_phone"   name = 'b_phone' class="b_phone chk" placeholder="전화번호를 입력후 인증버튼을 눌러주세요" style="margin-top: 3px;"  maxlength="13" >
                  	  <a style="margin-top: 3px;" class = 'btn-fill doubleChk chk' id = 'phoneChk' > 인증 </a>
                  	 <input type="text" id = 'phone2' name = 'phone2' class="phone2" placeholder="인증번호 입력" class = 'chk' maxlength="4" style="margin-top: 3px;" disabled required>  
                 	  <span class="point successPhoneChk">휴대폰 번호 입력후 인증번호 보내기를 해주세요.</span>
					 <input type="hidden" id="phoneDoubleChk"/>
					 <div class = 'valid' ></div>

                  </td>
                </tr>
                   <tr>
                  <th><span>가게명</span></th>
                  <td><input type="text"  name = 'store_name' placeholder="가게명을 입력해주세요" class = 'chk'>
<!--                     <div class = 'valid' ></div> -->
                  </td>
                </tr>
                
            <tr>
				<th><span>가게 주소</span></th>
					<td>
					<div class = 'btn-fill' style="float: left; margin-top: 3px;"><a onclick="post()" >우편번호찾기</a></div>
					<input type = 'text' style="margin-left: 5px; margin-bottom: 3px;" name = 'post' class = 'w-px60'  readonly>
					<input type = 'text' name = 'store_addr' class = 'full chk'  style="margin-bottom: 3px;" readonly>
					<input type = 'text' name = 'store_addr_more' class = 'full chk'>
					 <div class = 'valid' ></div>
					</td>
				</tr>
                 <tr>
                  <th><span>사업자등록증 사본</span></th>
                  <td>
                  <label>
                    <input type="file"  name = 'file'  class='attach-file chk'  accept="image/*">
                  <a><img src = 'images/file.png'></a><a style="color: #404040;"></a>
                   <span class = 'file-name'>파일 업로드</span>
                  	</label>
					<a class = 'delete-file'><img src ='images/delete.png' id = 'img-delete'></a>
                  	 <div class = 'valid' ></div>
                  </td>
                </tr>
                 <tr>
                  <th><span>영업신고증 사본</span></th>
                  <td>
                 <label>
                    <input type="file"  name = 'file2'   class ='attach-file2 chk'  accept="image/*">
                  	<a><img src ='images/file.png'></a><a style="color: #404040;"></a>
                  	<span class = 'file-name2'>파일 업로드</span>
                  	</label>
					<a class ='delete-file2'><img src ='images/delete.png' id = 'img-delete2'></a>
                  	 <div class = 'valid' ></div>
                  </td>
                </tr>
              </tbody>
            </table>
            
            </form>
            <div class="exform_txt"><span>표시는 필수적으로 입력해주셔야 가입이 가능합니다.</span></div>
          </div><!-- enter_form E  -->
          <div class="agree_wrap">
            <div class="checkbox_wrap">
              <input type="checkbox" id="news_letter" name="news_letter" class="agree_chk">
              <label for="news_letter">[선택]뉴스레터 수신동의</label>
            </div>
            <div class="checkbox_wrap mar27">
              <input type="checkbox" id="marketing" name="marketing" class="agree_chk">
              <label for="marketing">[선택]마케팅 목적 개인정보 수집 및 이용에 대한 동의</label>
              <ul class="explan_txt">
                <li><span class="red_txt">항목 : 성별, 생년월일</span></li>
                <li>고객님께서는 위의 개인정보 및 회원정보 수정 등을 통해 추가로 수집하는 개인정보에<br/>
                  대해 동의하지 않거나 개인정보를 기재하지 않음으로써 거부하실 수 있습니다.<br/>
                  다만 이때 회원 대상 서비스가 제한될 수 있습니다.
                </li>
              </ul>
            </div>
          </div>
          <div class="btn_wrap">
            <a onclick="insert()" style="cursor: pointer;" >입점 신청하기</a>
          </div>
        </div> <!-- form_txtInput E -->
      </div><!-- content E-->
    </div> <!-- container E -->
   
   
    
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>    
<script src ='js/checked.js?<%= new java.util.Date()%>'></script>
<script src ='js/files.js?<%= new java.util.Date()%>'></script>
<script >



$('.chk').keyup(function () {
	var status =b_num_chk.tag_status( $(this) );
	$(this).siblings('div').text( status.desc )
		.removeClass().addClass(status.code);
});
  

//우편번호 
function post() {
	new daum.Postcode({
        oncomplete: function(data) {
           var address = data.userSelectedType=='R' ? data.roadAddress : data.jibunAddress;
           if( data.buildingName !='' ) address += " ("+ data.buildingName +")"; // 빌딩 이름이 있다면 address에 담는다. 
            $('[name = store_addr]').eq(0).val( address );
            $('[name = post]').val( data.zonecode );
        }
    }).open();
}


//이름 유효성 검사 
function name_check() {
	var $name = $('[name=b_name]');
	if($name.hasClass('chked')) return; //중복확인이 되어 있다면 다시 중복확인 불필요!
	console.log('이름 확인'); 
	
	var status = b_num_chk.tag_status( $name );
	if( status.code=='invalid' ) {
		alert('다시 확인 필요\n' + status.desc );
		$id.focus();
		return;
	}
}

  //주소 체크되게
function addr_check() {
	var $name = $('[name=store_addr]');
	if($name.hasClass('chked')) return; //중복확인이 되어 있다면 다시 중복확인 불필요!
	console.log('주소 확인'); 
	
	var status = b_num_chk.tag_status( $name );
	if( status.code=='invalid' ) {
		alert('다시 확인 필요\n' + status.desc );
		$id.focus();
		return;
	}
}
 

  

//사업자등록번호 입력시 이벤트 발생하게 하는 처리
$('.chk').keyup(function (e) {
	//중복확인한 후에 다시 입력한다면 중복확인하지 않은 상태가 되도록 chked 없애는 처리
	
	if($(this).hasClass('chked') ) $(this).removeClass('chked');
	//아이디 태그에 엔터 키 입력인 경우는 사업자 중복확인
	if($(this).attr('name')=='b_num' && e.keyCode==13) {
		b_check();
	}else {
		var status = b_num_chk.tag_status( $(this) );
		$(this).siblings('div').text( status.desc )
			   .removeClass().addClass( status.code );
		
	}
	
});



//입점처리
function insert() {
	/*  if($('[name = name]').val() =='') {
		var title= $('[name = name]').closest('tr').children('span').text();
		alert( title + '을123 입력하세요');
		$('[name = name]').focus();
		return;
	} 
	 */
	
	if($('[name=b_num]').hasClass('chked') ) {
	//중복확인한 경우
	//이미 사용중인 경우만 처리!
		if( $('[name=b_num]').siblings('div').hasClass('invalid') ){
			alert( '신청 불가\n '+ b_num_chk.b_num.unusable.desc );
			$('[name=b_num]').focus();
			return;
		}
	
	
	}else {
	//중복확인 하지 않은 경우
	//입력자체가 유효하지 않은 입력값
		if(tagIsInvalid( $('[name=b_num]') ) ) return; 
		else {
			//아이디 중복확인을 해야하는 경우
			alert( '신청 불가\n '+  b_num_chk.b_num.valid.desc );
			$('[name=b_num]').focus();
			return;
		}
	
	}
	
	
		
 	if( tagIsInvalid( $('[name=b_name]') ) ) return;
	if( tagIsInvalid( $('[name=b_phone]') ) ) return;
 	if( tagIsInvalid( $('[name=store_name]') ) ) return;
	if( tagIsInvalid( $('[name=store_addr]') ) ) return;
 	if( !file_check( $('[name=file]') ) ) return;
	if( !file_check( $('[name=file2]') ) ) return; 
	$('form').submit();

}

//태그의 입력상태가 invalid 하면 form submit 불가
 function tagIsInvalid( tag ) {
	var status =b_num_chk.tag_status( tag );
	if( status.code =='invalid'){
		alert( '신청 불가\n '+ status.desc );
		tag.focus();
		return true; //넵!
	}else {
		return false; //아니요!
	}
} 

function file_check( name ) {
	var file = $('[name = file]');
	var file2 = $('[name = file2]'); 
	if(!file && !file2) {
		alert( '신청 불가\n 파일을 넣어주세요');
		return false;
	}else {
		return true;
	}
}


//사업자등록번호 입력시 이벤트 발생하게 하는 처리
$('.chk').keyup(function (e) {
	//중복확인한 후에 다시 입력한다면 중복확인하지 않은 상태가 되도록 chked 없애는 처리
	
	if($(this).hasClass('chked') ) $(this).removeClass('chked');
	//아이디 태그에 엔터 키 입력인 경우는 사업자 중복확인
	if($(this).attr('name')=='b_num' && e.keyCode==13) {
		b_check();
	}else {
		var status = b_num_chk.tag_status( $(this) );
		$(this).siblings('div').text( status.desc )
			   .removeClass().addClass( status.code );
		
	}
	
});



//사업자번호 중복확인할 함수 선언
function b_check(){
	//올바른 아이디 입력값 상태인지 확인
	var $id = $('[name = b_num]');
	if($id.hasClass('chked')) return; //중복확인이 되어 있다면 다시 중복확인 불필요!
	console.log('중복확인'); //이제 버튼 눌러도 중복확인이 계속 진행되지 않는다.
	
	var status = b_num_chk.tag_status( $id );
	if( status.code=='invalid' ) {
		alert('다시 확인 필요\n' + status.desc );
		$id.focus();
		return;
	}
	
 	//DB에 입력한 사업자번호가 있다면 입점이 불가능한 사업자번호!
	$.ajax({
		url : 'b_check',
		data: { b_num:$id.val() },
		success: function (response) {
			//존재하는 경우 : 1 (true), 아니면 0 (false) 
			
			 response = b_num_chk.b_num_check( response ); //멤버 에 있는 아이디 체크 함수 소환
			 $id.siblings('div').text( response.desc )
			 					.removeClass().addClass(response.code);
			 $id.addClass('chked');
		}, error: function (req,status) {
			alert(status + ':' + req.status );
		}
		
		
	}); 
	
}





//사업자 등록번호 자동 하이픈 처리
$('#b_num').on('keyup', function(){
        var num = $('#b_num').val();
        num.trim(); 
        this.value = AutoHypen(num) ;
});


function AutoHypen(companyNum){
    companyNum = companyNum.replace(/[^0-9]/g, '');
    var tempNum = '';   

    if(companyNum.length < 4){
      return companyNum;
    }
    else if(companyNum.length < 6){
      tempNum += companyNum.substr(0,3);
      tempNum += '-';
      tempNum += companyNum.substr(3,2);
      return tempNum;
    }
    else if(companyNum.length < 11){
      tempNum += companyNum.substr(0,3);
      tempNum += '-';
      tempNum += companyNum.substr(3,2);
      tempNum += '-';
      tempNum += companyNum.substr(5);
      return tempNum;
    }
    else{        
      tempNum += companyNum.substr(0,3);
      tempNum += '-';
      tempNum += companyNum.substr(3,2);
      tempNum += '-';
      tempNum += companyNum.substr(5);
      return tempNum;
    }
  }





//핸드폰 번호 자동 하이픈 이벤트 

$('[name=b_phone]').on('keyup', function(){
	var num = $('[name=b_phone]').val();
	num.trim(); 
	this.value = autoHypenPhone(num) ;
});



function autoHypenPhone(str){
            str = str.replace(/[^0-9]/g, '');
            var tmp = '';
            if( str.length < 4){
                return str;
            }else if(str.length < 7){
                tmp += str.substr(0, 3);
                tmp += '-';
                tmp += str.substr(3);
                return tmp;
            }else if(str.length < 11){
                tmp += str.substr(0, 3);
                tmp += '-';
                tmp += str.substr(3, 3);
                tmp += '-';
                tmp += str.substr(6);
                return tmp;
            }else{              
                tmp += str.substr(0, 3);
                tmp += '-';
                tmp += str.substr(3, 4);
                tmp += '-';
                tmp += str.substr(7);
                return tmp;
            }
            return str;
        }



//알없어서.. 잠깐 막아둠 ㅎㅎ;; 핸드폰 sms 인증 번호 날리기 
 var code2 = "";
$("#phoneChk").click(function(){

	alert("인증번호 발송이 완료되었습니다.\n휴대폰에서 인증번호 확인을 해주십시오.");
	 var phone = $("#b_phone").val();
	$.ajax({
        type:"GET",
        url:"phoneCheck?phone=" + phone,
        cache : false,
        success:function(data){
        	if(data == "error"){
        		alert("휴대폰 번호가 올바르지 않습니다.")
				$(".successPhoneChk").text("유효한 번호를 입력해주세요.");
				$(".successPhoneChk").css("color","red");
				$("#phone").attr("autofocus",true);
        	}else{	        		
        		$("#phone2").attr("disabled",false);
        		$("#phoneChk2").css("display","inline-block");
        		$(".successPhoneChk").text("인증번호를 입력한 뒤 본인인증을 눌러주십시오.");
        		$(".successPhoneChk").css("color","green");
        		$("#phone").attr("readonly",true);
        		code2 = data;
        	}
        } 
     });

	
	
});



//휴대폰 인증번호 대조 하는중
	$("#phone2").keyup(function () {
		if($("#phone2").val() == code2){
			$(".successPhoneChk").text("인증번호가 일치합니다.");
			$(".successPhoneChk").css("color","green");
			$("#phoneDoubleChk").val("true");
			$("#phone2").attr("disabled",true);
		}else{
			$(".successPhoneChk").text("인증번호가 일치하지 않습니다. 확인해주시기 바랍니다.");
			$(".successPhoneChk").css("color","red");
			$("#phoneDoubleChk").val("false");
			$(this).attr("autofocus",true);
		}
	});
	
  

	
</script>

</body>
</html>