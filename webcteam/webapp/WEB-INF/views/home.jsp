<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="csrf-token"
	content="nYSSODj6EMfS0RB5T88KkitnykMvKzY5qUJ8PvoC">
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/skin_main.css?v=20221005" />
<!-- 메인에서만 실행되어야하는 CSS -->
<link rel="canonical" href="https://skinsample13.clickn.co.kr/">


<style type="text/css">
.slide_div_wrap {
    margin-top: 50px;
	position: relative !important;
}

.slide_div img {
	width: 80% !important;
	vertical-align: middle !important;
	height: 693px !important;
}


/*<!--jk 맨위에 입점 가게들 css임  -->  */
html, body, div, span, applet, object, iframes, h1, h2, h3, h4, h5, h6,
	p, blockquote, pre, a, abbr, acronym, address, big, quotes, code, del,
	dfn, em, img, ins, kbd, q, s, samp, small, strike, sub, sup, tt, var, u,
	i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table,
	caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas,
	details, embed, figure, figcaption, footer, header, hgroup, menu, nav,
	output, ruby, section, summary, time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	do: inherit;
	vertical-align: baseline;
}

article, aside, details, figcaption, figure,  header, hgroup,
	menu, nav, section {
	display: block;
}

blockquote, q {
	quotes: none;
}

blockquote : before, blockquote : after, q : before, q : after {
	content: '';
	content: none;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
}

/*css 초기화*/
.card {
	height: 400px;
	width: 350px;
	border-radius: 15px;
	display: inline-flex;
	margin-top: 30px;
	margin-left: 30px;
	margin-bottom: 30px;
	position: relative;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	overflow: hidden;
	overflow-x: hidden; overflow-y: hidden; 
 	float: left;
 	animation: bannermove 5s linear infinite;
}


.card-header {
	-webkit-transition: 0.5s; 
	transition: 1.0s;
	width: 100%;
	height: 270px;
	border-radius: 15px 15px 0 0;
	background-size: 100% 280px;
	background-repeat: no-repeat;
	
}

.card:hover .card-header {
	/* 아래께 있으면 글자들이 희미해진다 그래서 임시로 지워둠 jk */
	/*   	opacity: 0.8;   */
	height: 100px;
	transition: 0.5s;
	transform: translateY(-260%);
	top: 100px;
}

.card-header-is_closed {
	background-color: #EF5A31;
	color: #FFF;
	font-weight: bold;
	text-align: center;
	float: right;
	margin: 15px 15px 0 0;
	border-radius: 50%;
	font-weight: bold;
	padding: 10px 10px;
	line-height: 20px;
}

h1 {
	font-size: 22px;
	font-weight: bold;
}

.card-body:hover .card-header {
	opacity: 20;
}

.card-body-header {
	line-height: 25px;
	margin: 10px 20px 0px 20px;
}

.card-body-description {
	/* 	opacity: 0; */
	color: #757B82;
	line-height: 25px;
	-webkit-transition: .2s ease-in-out; /*사파리&크롬*/
	transition: .2s ease-in-out;
	overflow: hidden;
	height: 180px;
	margin: 5px 20px;
	text-align: left;
}

.card:hover .card-body-description {
	opacity: 1;
	-webkit-transition: .5s ease-in-out;
	-moz-transition: .5s ease-in-out;
	-ms-transition: .5s ease-in-out;
	-o-transition: .5s ease-in-out;
	transition: .5s ease-in-out;
	/* 	overflow: scroll; */
}

.card-body-description img {
	width: 15px;
	height: 15px;
}

.card-body-hashtag {
	color: #2478FF;
	font-style: italic;
}

.card-body-footer {
	position: absolute;
	margin-top: 15px;
	margin-bottom: 6px;
	bottom: 0;
	width: 314px;
	font-size: 14px;
	color: #9FA5A8;
	padding: 0 15px;
}

.icon {
	display: inline-block;
	vertical-align: middle;
	margin-right: 2px;
}

.icon-view_count {
	width: 25px;
	height: 17px;
	background: url("images/eye.jpg") no-repeat;
}

.icon-comments_count {
	margin-left: 5px;
	width: 25px;
	height: 17px;
	background: url("images/comment.jpg") no-repeat;
}

.card-header img {
	height: 256.7px;
	width: 350px;
	transition: .2s ease-in-out;
	opacity: 10;
}

.reg_date {
	float: right;
}

.card_top {
 width: 5280px;
 overflow: hidden;
 flex-wrap:nowrap;
 display:flex;

 
}
.card_top:first-child {
	margin-top: 100px;

}

@keyframes bannermove {
  0% {
      transform: translate(0, 0);
  }
  100% {
      transform: translate(-50%, 0);
  }
}


</style>

<meta property="og:title" content="The Medical Clinic">
<title>푸드파킹</title>







<div class="skin_layout_container skin_layout_container2">
	<div class="front_main_area">
		<div id="skinMainWrap" class="skin_main_wrap">
			<div class="skin_main_container skin_main_container1">

				<!-- ===================== Skin Main ===================== -->
				<div id="skinMainSection" class="skin_main_section">


					<div class="swiper-container">



						<div class="slide_div_wrap">
							<div class="slide_div">
								<div>
									<a> <img alt="" src="images/mainbanner01.png">
									</a>
								</div>

								<div>
									<a> <img alt="" src="images/maingbanner02.png">
									</a>
								</div>
								<div>
									<a> <img alt="" src="images/mainbanner03.png">
									</a>
								</div>
							</div>
						</div>
					</div>

					<div style="font-family: Quicksand; font-size: 20px; font-weight: 400; text-align: center; color: rgb(0, 0, 0); line-height: 1.2;">
						<p></p>
						<p><br></p></div>
						<div style=" font-size: 46px; font-family: Montserrat; font-weight: 100; text-align: center; color:#000; line-height: 1.5;">
							<p>자리예약은 푸드파킹</p></div>
							<div style="font-size: 16px; color: rgb(110, 110, 110); text-align: center;  line-height: 1.6; font-weight: 300;" >
								<p>&nbsp;</p>
								<p><br></p>
								<p>푸드파킹은 자리예약 시스템입니다</p>
								<p>푸드파킹은 점주님들과 원활한 의사소통을 추구합니다.</p>
								<p>&nbsp;</p></div>
								<div data-text-editable= "true" style= "font-size: 13px; letter-spacing: 0.1em; color:#000000; text-align: center;" >
									<p><a href= "#none">view more</a></p></div>

 

												<form action="detail.ho" method="post"  >
												<div class='card_top' style="text-align: center;">
													<c:forEach items="${vo}" var="vo">
														<a onclick="detail(${vo.store_code})">
 														<input type = "hidden" name = 'store_code' >
															<div class="card">
																<div class = 'card_content'>


																<!-- 카드 헤더 -->

																<div class="card-header">
																<c:if test="${not empty vo.store_image}">
																	<div>
																		<img src="${vo.store_image}">
																	</div>
																</c:if>
																<c:if test="${empty vo.store_image}">
																	<div>
																		<img src="images/default.png">
																	</div>
																</c:if>
																	<div class="card-header-is_closed">

																		<div class="card-header-text">신규</div>

																		<div class="card-header-number">10 / 11</div>

																	</div>

																</div>



																<!--  카드 바디 -->

																<div class="card-body">



																	<!--  카드 바디 헤더 -->

																	<div class="card-body-header">
																		<%-- <c:forEach items="${vo}" var="vo"> --%>
																		<h1>${vo.store_name}</h1>
																		<%-- 			</c:forEach>	 --%>
																		<p class="card-body-hashtag">#여수 #순천 #광양</p>

																		<p class="card-body-nickname">주소: ${vo.store_addr }</p>

																	</div>
																    <p><br></p>
																	<p class="card-body-description">
																		<img src='images/star.png'>${vo.star_rating}
																		<br/>
																		${vo.store_comment}
																	</p>
																	

																	<!--  카드 바디 본문 -->

																	<!--  카드 바디 푸터 -->
<!-- 
																	<div class="card-body-footer">

																		<hr
																			style="margin-bottom: 8px; opacity: 0.5; border-color: #EF5A31">

																		<i class="icon icon-view_count"></i>조회 38회 <i
																			class="icon icon-comments_count"></i>댓글 4개 <i
																			class="reg_date"> 2022/10/12 </i>

																	</div>
 -->


																</div>

																</div>

															</div>

														</a>

													</c:forEach>
												</div>
												</form>
								</div>

							</div>
							<!-- ===================== // Skin Main ===================== -->
						</div>

					</div>
				</div>
</div>





<footer> </footer>




<script type="text/javascript">
$(document).ready(function () {
	$('.slide_div').slick({
		
			dots: true,
			autoplay : true,
			autoplaySpeed: 2000
	
		});
});

function detail(store_code) {
	$('[name=store_code]').val( store_code );
	$('form').submit();
}

   

	


</script>

</body>
</html>
