<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#naver {
	background: url('images/naver.png') no-repeat center;
	background-size: cover;
}

#kakao {
	background: url('images/kakao.png') no-repeat center;
	background-size: cover;
}
</style>
</head>
<body>



	<div class="normal_module" data-module-name="text_A001"
		data-content-type1="text" data-content-type2="text-type-A"
		data-background-color="" data-background-image=""
		data-background-position="" data-background-repeat=""
		data-background-size="" data-background-attachment=""
		data-background-filter=""
		style="background-color: transparent; background-image: url(&quot;/images/bg_.png&quot;);">
		<div class="module_wrap" data-layout-fullsize="no">
			<div class="module_container" data-padding-top="100"
				data-padding-bottom="40">
				<div class="title" data-text-editable="true"
					style="font-family: Montserrat, 'Noto Sans KR'; font-size: 32px; color: #000; font-weight: 500;">
					<p style="text-align: center;">로그인</p>
				</div>
			</div>
		</div>
	</div>


	<div class="skin_block" data-module-id="module-login"
		data-module-type="special" data-is-empty="no" style="display: block;">
		<div class="special_module" data-module-name="login_A001"
			data-module-parents="login_A001" data-content-type1="member"
			data-content-type2="login" data-background-color=""
			data-background-image="" data-background-image-options=""
			data-background-attachment="" data-background-filter=""
			style="background-color: transparent; background-image: url(&quot;/images/bg_.png&quot;);">
			<div class="module_wrap" data-layout-fullsize="no">
				<div class="module_container" data-padding-top="0"
					data-padding-bottom="100">
					<form id="login-form" class="radio_checkbox_type2"
						action="memberlogin" method="post">
						<input type="hidden" name="return_url"
							value="https://designskin13.clickn.co.kr/">
						<div class="member_wrap">
							<ul class="login_form form_contents">
								<li class="form_type_input">
									<ul class="form_section">
										<li class="form_items"><input type="text" name="id"
											placeholder="이메일" value="" id='email' autofocus=""></li>
									</ul>
								</li>
								<li class="form_type_pass">
									<ul class="form_section">
										<li class="form_items"><input type="password" name="pw"
											placeholder="비밀번호" id='pw'></li>
									</ul>
								</li>
								<li class="form_type_checkbox">
									<ul class="form_section">
										<li class="form_items">
											<div>
												<label><input type="checkbox"
													name="memorizing_login_id" value="1"><em>아이디
														저장</em></label>
											</div>
										</li>
									</ul>
									<div class="text_menu member_nav">
										<ul>
											<li><a href="forget_pw">비밀번호 찾기</a></li>
											<li><a href="join.mb">회원가입</a></li>

										</ul>
									</div>
								</li>
								<li class="form_type_submit">
									<ul class="form_section">
										<li class="form_items"><input type='button'
											class="btn_resp size_d color2 form_send" value='로그인'
											onclick='login()'></li>

									</ul>
								</li>

								<li><input type='button' class="btn_resp size_d  "
									id='naver' onclick='location="naverlogin"'></li>

								<li><input type='button' class="btn_resp size_d  "
									id='kakao' onclick='location="kakaologin"'></li>

							</ul>


						</div>
					</form>
				</div>
			</div>
		</div>
		<link rel="stylesheet" type="text/css"
			href="https://storage.clickn.co.kr//css/modules/special_common.css?v=20221005">
		<link rel="stylesheet" type="text/css"
			href="https://storage.clickn.co.kr//css/modules/module_member.css?v=20221005">
	</div>


</body>
<script src='js/common_ssb.js?<%=new java.util.Date()%>'></script>
<script type="text/javascript">
	$('#pw').keypress(function(e) {
		if (e.keyCode == 13) {
			login();
		}
	});

	function login() {
		if (!emptyCheck())
			return;

		$.ajax({
			url : 'memberlogin',
			data : {
				email : $('#email').val(),
				pw : $('#pw').val()
			},
			success : function(response) {
				if (response)
					location = '<c:url value="/"/>';
				else {
					alert('아이디나 비밀번호가 일치하지 않습니다!');
					$('#id').focus();
				}
			}
		});

	}
</script>
</html>