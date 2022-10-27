<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"	prefix="c" %>
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
	width: 200px;
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
	width: 250px;
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
	font-size: 14px;
	color: #000000;
	height: 35px;
	float: left;
}


input[name=b_num], input[name=store_name],
input[name=open_close], input[name=store_tel],
input[name=addr], input[name=addr_more], input[name=store_category] {
	border: 1px solid #ececec;
	width: 100%;
	padding: 10px;
}

input[name=addr] {
	border: 1px solid #ececec;
	margin-top: 5px;
	margin-bottom: 5px;
	padding: 10px;
}

input[name=post] {
	border: 1px solid #ececec;
	width: 50%;
	padding: 10px;
}

input[name=store_logo] {
	border:none;
}

.join_form.idpwFind table input {
	width: 519px;
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

/* .join_form table th span:after {
	content: '*';
	font-size: 13px;
	color: #f95427;
	position: absolute;
	top: 0;
	right: 0px;
} */

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

td .store_comment {
	width: 100%;
	height: 150px;
	border: 1px solid #ececec;
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

.file a img {
	width: 25px;
	height: 25px;
}

.delete-file a img {
	width: 15px;
	height: 15px;
	margin-bottom: 5px;
	margin-left: 5px;
}

.menu-info {
	width: 25%;
	text-align: center;
	color: black;
}

#menu_info {
	color: black;
}

details td{
	color: black;
}
#delete, #attach-file, #delete-file, .attach-file, .delete-file { display:none; }


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
	<form method="post">
		<input type="hidden" name="id" value="${vo.id}">
		<input type="hidden" name="store_code" value="${vo.store_code}">
		<div class="wrap wd668">
			<div class="container">
				<div class="form_txtInput">
					<h2 class="sub_tit_txt">나의 가게 정보</h2>
					<p class="exTxt">푸드파킹에 입점한 가게입니다. 가게 삭제를 원할 시 폐업신청을 눌러주세요. </p>
					<div class="join_form">
						<table>
							<colgroup>
								<col width="30%" />
								<col width="auto" />
							</colgroup>
							<tbody>
								<tr>
									<th><span>사업자번호</span></th>
									<td><input type="text" name="b_num" maxlength="12" readonly value="${vo.b_num}"> </td>
								</tr>
								
								<tr>
									<th><span>가게 카테고리</span></th>
									<td>
										<c:choose>
											<c:when test="${vo.store_category eq 1}">
												<input type="text" name="store_category" value="한식" readonly>
											</c:when>
											<c:when test="${vo.store_category eq 2}">
												<input type="text" name="store_category" value="중식" readonly>
											</c:when>
											<c:when test="${vo.store_category eq 3}">
												<input type="text" name="store_category" value="분식" readonly>
											</c:when>
											<c:when test="${vo.store_category eq 4}">
												<input type="text" name="store_category" value="패스트푸드" readonly>
											</c:when>
											<c:when test="${vo.store_category eq 5}">
												<input type="text" name="store_category" value="양식" readonly>
											</c:when>
											<c:when test="${vo.store_category eq 6}">
												<input type="text" name="store_category" value="일식" readonly>
											</c:when>
											<c:when test="${vo.store_category eq 7}">
												<input type="text" name="store_category" value="카페/디저트" readonly>
											</c:when>
											<c:otherwise>
												<input type="text" name="store_category" value="기타" readonly>
											</c:otherwise>
										</c:choose>
									</td>
									
								</tr>
								
								<tr>
									<th><span>가게 이름</span></th>
									<td><input type="text" name="store_name" value="${vo.store_name}" readonly></td>
								</tr>
								
								<tr>
									<th><span>가게 소개글</span></th>
									<td>
										<textarea rows="1" cols="1" readonly="readonly" class="store_comment" name="store_comment">${vo.store_comment}
										</textarea>
									</td>
								</tr>
								
								<tr>
									<th><span>운영시간</span></th>
									<td><input type="text" name="open_close" value="${vo.open_close}" readonly></td>
								</tr>
								
								<tr>
									<th><span>가게 전화번호</span></th>
									<td><input type="text" name="store_tel" value="${vo.store_tel}" readonly></td>
								</tr>
								
								<tr>
									<th><span>주소</span></th>
									<td><input type="text" name="post" readonly value="${vo.store_post}">
										<input type="text" name="addr" class="full" readonly value="${vo.store_addr}">
										<input type="text" name="addr_more" readonly class="full" value="${vo.addr_more}"></td>
								</tr>

								<tr>
									<th><span>가게 로고</span></th>
									<td><c:choose>
											<c:when test="${!empty vo.store_logo}">
												<img src="${vo.store_logo}">
											</c:when>
											<c:otherwise>
												로고 없음
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								
								<tr>
									<th><span>가게 이미지</span></th>
									<td><c:choose>
											<c:when test="${!empty vo.store_image}">
												<img src="${vo.store_image}">
											</c:when>
											<c:otherwise>
												로고 없음
											</c:otherwise>
										</c:choose>
									</td>
									<%-- <c:forEach items="${vo.imageInfo}" var="i">
										<div class='file' data-id='${i.store_code}'>
											<div class="delete-file"><a><img src="resources/images/delete.png"></a></div>
											<div><img src="${vo.store_image}"></div>	
										</div>
									</c:forEach> --%>
								</tr>
						</table>
						
						<details>
					   		<summary id="menu_info" style="cursor: pointer;">🍚 메뉴 정보 보기 </summary>
					   		<table>
					   			<colgroup>
									<col width="30%" />
									<col width="auto" />
							</colgroup>
							<tbody>
								<tr>
									<td class="menu-info">사진</td>
									<td class="menu-info">이름</td>
									<td class="menu-info">가격</td>
								</tr>
								
								<c:forEach items="${vo.menuInfo}" var="m">
									<tr>
										<td class="menu-info"><img src="${m.menu_image}"></td>
										<td class="menu-info">${m.menu_name}</td>
										<td class="menu-info">${m.price}원</td>
									</tr>
								</c:forEach>
							</tbody>
					   		</table>
						</details>
						
						<div class="exform_txt">
							<a onclick="close_store()" style="cursor: pointer;">폐업신청하기 →</a>
						</div>
						
						<div class="btn_wrap">
							<a href="modify.st" style="cursor: pointer;">가게 정보 수정하기</a>
						</div>
					</div>
				</div>
				<!-- form_txtInput E -->
			</div>
			<!-- content E-->
		</div>
		<!-- container E -->
	</form>

<script src='js/common_ssb.js?<%=new java.util.Date()%>'></script>

<script>
function close_store() {
	var pw = prompt("비밀번호를 입력하세요");
	if(pw != null) {
		$.ajax({
			type : "POST",
			url : "<c:url value='/close_store.st'/>",
			data: {
			 store_code: ${vo.store_code},
			 pw : pw
			},
			success: function(result){
				if(result == 0) {
					alert("삭제되었습니다.");
				}else {
					alert("비밀번호가 틀렸습니다");
				}
			},
			error: function(xhr, status, error) {
				alert(error);
			}
		});
	}
 }
</script>

</body>
</html>