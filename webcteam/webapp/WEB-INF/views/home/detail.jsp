<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style type="text/css">
/* 초기화 */
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, embed, 
figure, figcaption, footer, header, hgroup, 
menu, nav, output, ruby, section, summary,
time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: middle;
}

body {
 background-color:  #f0edeb;
}


/* 아래에서 위로 올라오는 모션 페이드인! */
  @keyframes fadeInUp {
        0% {
            opacity: 0;
            transform: translate3d(0, 100%, 0);
        }
        to {
            opacity: 1;
            transform: translateZ(0);
        }
    }
 .detail_card{
 	margin-top: 100px;
 }
    
.top {
    background: #fff;
    border : 1px solid #d6d5d4;
    margin-bottom: 50px;
	border-radius: 10px;
	width: 400px;
	height: 330px;
	float: left;
	justify-content:space-around;
	margin-left:50px;
	position: absolute;
	left: 10px;
	right :20px;
	overflow: hidden;
	position: relative;
    animation: fadeInUp 1s;
    padding: 20px;
  
}


.top:first-child {
	margin-left: 271px;
}


.top-review {
  background: #fff;
    border : 1px solid #d6d5d4;
    margin-bottom: 50px;
	border-radius: 10px;
	width: 69%;
	height: 330px;
	float: left;
	justify-content:space-around;
	margin-left:271px;
	position: absolute;
	left: 10px;
	right :20px;
	overflow: hidden;
	position: relative;
    animation: fadeInUp 1s;
    padding: 20px;
}

table tr {
 padding : 25px;
}


th{
	font-weight: bold;
	font-style: italic;
}



.stroe_name{
	font-style: italic;
	font-weight: bold;
}

p {
	float: left;
}

/* 가운데 이미지 마우스오버 효과 내려고 */
  .container2 {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
        height: 100%;
        margin-top: 50px;
        margin-bottom: 100px;
      }

.img-1 {
/*  position: relative; */
        width: 400px;
        height: 280px;
    
}
 .img-2 {
	  width: 160px;
	  height: 140px;
	  display: block;
	  
} 

/* 별점하려고 */
  #star a{
   text-decoration: none;
   color: gray;
  }
  #star a.on{
   color: yellow;
  } 
  
/* 리뷰 리스트 옆으로 세울라고 */  
 .review_content  ul  {
   float: left;

 }
 
 .review_content2 p{
    margin-bottom: 5px;
 }
 
 .review_content img {
  width: 50px; 
  height: 50px; border-radius: 50%; border: 1px solid #fff;

 }
 
  .review_content img:first-child {
 	 margin-top: 5px;
 }
 
 #review_image img {
  width: 50px; 
  height: 50px; border-radius: 20%; border: 1px solid #fff;
  
 }

#review_image img:first-child{
	margin-top: 5px;
}
 .star img {
 width: 15px;
 height: 15px;
 } 
  

.review-contentbox {
	vertical-align: middle;
}  
  
/*리뷰 검색기능 넣으려고  */
#list-top {
	margin: 0 auto;
	display: flex; 
	justify-content: space-between;
}

#list-top ul { display: flex;} 
#list-top ul li:not(:first-child) { margin-left: 5px} 

input:focus, select:focus {outline: none; border-color: #F25C05;}

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


tr {
	border-bottom: 1px solid #f5f3f0;
	margin-bottom: 10px !importent;
}

  
</style>
<title>Insert title here</title>
</head>
<body>

 <div style="text-align: center;" class = 'container2'>
 <img alt="" src="${vo.store_image}" class = 'img-1'
 	onmouseenter="zoomIn(event)"
    onmouseleave="zoomOut(event)" >
 <img class = "img-1" src="images/img_1.png"
 	onmouseenter="zoomIn(event)"
    onmouseleave="zoomOut(event)">
  <div class = 'imgdiv'>  
    <c:forEach items="${list}" var = 'list'>
   
 <img class = "img-2" src="${list.menu_image}">

 </c:forEach>
  </div>
</div>	


<div id = 'detail_card'>
 <div class = 'top'>
 <p class = 'stroe_name'> 평가 및 리뷰 </p> <p> </p><br/>
  <p class = 'stroe_name'> -  ${vo.review_cnt} </p> <p> 건의 리뷰	</p><br/>
  <br/>
  <br/>
  <p class = 'stroe_name'> 평점 </p><br/>
  		 <p> - 청결 </p> <p>:	${vo.clean} </p>
  		 	<br/>
  		 <p> - 맛 </p> <p>:	${vo.taste} </p><br/>
  		 <p> - 분위기 </p> <p>:	${vo.mood} </p><br/>
  		 <p> - 친절 </p> <p>:	${vo.kind} </p><br/>
 </div>
 
 
 
  <div class = 'top'>
 <p class = 'stroe_name'> 상세 정보 </p><br/>

 <c:if test="${vo.store_category eq 1}">
  <p>요리 : 한식 </p><br/>
  </c:if>
 <c:if test="${vo.store_category eq 2}">
  <p>요리 : 중식 </p><br/>
  </c:if>
 <c:if test="${vo.store_category eq 3}">
  <p>요리 : 분식 </p><br/>
  </c:if>
 <c:if test="${vo.store_category eq 4}">
  <p>요리 : 패스트푸드 </p><br/>
  </c:if>
 <c:if test="${vo.store_category eq 5}">
  <p>요리 : 양식 </p><br/>
  </c:if>
 <c:if test="${vo.store_category eq 6}">
  <p>요리 : 일식 </p><br/>
  </c:if>
 <c:if test="${vo.store_category eq 7}">
  <p>요리 : 카페 </p><br/>
  </c:if>
   <br/>
  <br/>
  <p class = 'stroe_name'> 식사시간 </p> <p>:	${vo.open_close} </p><br/>
  <p class = 'stroe_name'> 특징 </p> <p>:	좌석, 테이블 서비스, 예약 </p>
 </div>
 
  <div class = 'top'>
 <p class = 'stroe_name'> 위치 및 문의 정보 </p><br/>
 	<div id = 'store_location' style="width: 320px; height: 220px; margin: 0 !importent; margin-left: 15px ; margin-bottom: 5px">
 		<input type="hidden" name ='store_lat' value = '${vo.store_lat}'>
 		<input type="hidden" name ='store_lon' value = '${vo.store_lon}'>
 	</div>
 	
  <p> ${vo.store_addr } ${vo.addr_more } </p> 
  
 </div> 
 
<div class = 'top-review' style="overflow: scroll;">
<p class = 'stroe_name'> 리뷰 </p> <p style="color: gray; font-weight: bold;"> (  ${vo.review_cnt } 건) </p>
	<div id = 'list-top'>
	<ul>
		<li>
		 <select class ='w-px100' style="height: 30px; margin-left: 5px;" name ='search' >
		 <option value ='all' ${page.search eq 'all' ? 'selected' : '' }>전체</option>
		 <option value ='writedate'  ${page.search eq 'all' ? 'selected' : '' }>최신순</option>
		 <option value = 'star_rating'  ${page.search eq 'all' ? 'selected' : '' }>별점높은순</option>
		 <option value = 'star_rating'  ${page.search eq 'all' ? 'selected' : '' }>별점낮은순</option>
		 </select>
		</li>
		<li><input type = 'text' name = 'keyword'  style="height: 30px;" class = 'w-px300' value = '${page.keyword }'></li>
		<li style="margin-top: 5px;"><a class = 'btn-fill'  onclick="$('form').submit()">검색</a></li>
	</ul>
</div>
<br/>

<form method="post" action="admin_insert.ho">
<table class = 'review_content'>
<c:forEach items="${review}" var = 'review'> 
	<input type="hidden"  name = 'star_code'  value = '${review.star_code}'>
	<tr style=" margin-bottom: 5px;">
		<td class = 'profile' style="display: block; text-align: center;"><img src="${review.profile_image}"><br/>
		  ${review.nickname}
		</td>
		<td class = 'star'  style="width: 30px;  padding-left: 10px;" ><img  src="images/star.png">  ${review.star_rating } /  ${review.writedate}　
		　<c:if test="${loginInfo.id eq vo.id}"><a style="cursor: pointer; font-weight: bold; color: red;" onclick = 'admin_waring(${review.star_code})'> 신고하기</a>
		</c:if> 
		<br/><span style=" white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${review.review_content}</span> <br/>
		<c:if test="${not empty review.review_image}">
		<a id = 'review_image'><img src = "${review.review_image}"></a>
		</c:if>
		
		</td>
		
		
	</tr>
<%-- 	<tr>
		<td style="text-align: center;"> ${review.nickname}</td>
	
	</tr> --%>
	</c:forEach> 
   	
	
</table>
</form>





</div>
 

</div>

<!--카카오 지도 API JK  -->
<script src ='js/common_ssb.js.js?<%= new java.util.Date()%>'></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=912cf98bacf67dfbfdcbc38c95ec1762"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=912cf98bacf67dfbfdcbc38c95ec1762&libraries=LIBRARY"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=912cf98bacf67dfbfdcbc38c95ec1762&libraries=services,clusterer,drawing"></script>

<script>





//카카오 지도 일단 넣어볼려고 jk
var container = document.getElementById('store_location');  //지도를 담을 영역의 DOM 레퍼런스
var store_lat = $('[name=store_lat]').val();
var store_lon = $('[name=store_lon]').val();
var options = { 
	center: new kakao.maps.LatLng(store_lat, store_lon), 
	level: 3 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options);


var imageSrc = 'images/foodmap.png',      
imageSize = new kakao.maps.Size(60, 60), 
imageOption = {offset: new kakao.maps.Point(27, 69)};

//마커의 이미지정보를 가지고 있는 마커 생성
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
    markerPosition = new kakao.maps.LatLng(store_lat, store_lon); // 마커 위치

//마커를 생성
var marker = new kakao.maps.Marker({
    position: markerPosition, 
    image: markerImage // 마커이미지 설정 
});
//마커가 지도 위에 표시되도록 설정
marker.setMap(map);





//신고할때 확인용
 function admin_waring(star_code) {
	if( confirm('해당 댓글을 신고 하시겠습니까?') ){
		$.ajax({
			url : 'admin_detail.ho',  
			data : { star_code },
			success: function ( response ) {
				console.log(response);
				if( ! response ){
					alert('신고하기가 완료 되었습니다.\n처리 기간은 1주일 정도입니다');
					console.log(star_code);	
					$.ajax({
						url: "admin_insert.ho",
						data: { star_code },
						success: function(result){
							console.log(result);
							alert(result);
						}
					});
				}else { /* 그게 아니라면 */
					console.log(star_code);
					alert('이미 신고한 댓글입니다!');
					return;
				}
			}
		});
		
	}
}



//별점 넣으려고 jk
$('#star a').click(function(){ 
	 $(this).parent().children("a").removeClass("on");    
	 $(this).addClass("on").prevAll("a").addClass("on");
	 console.log($(this).attr("value"));
});


/* 메인사진 커지게 작아지게 효과주려고 */
  function zoomIn(event) {
    event.target.style.width = "500px";
    event.target.style.height = "300px";
    event.target.style.transition = "all 0.5s";
  }

  function zoomOut(event) {
    event.target.style.width = "400px";
    event.target.style.height = "280px";
    event.target.style.transition = "all 0.5s";
  }
  
  function zoomIn1(event) {
	    event.target.style.width = "300px";
	    event.target.style.height = "150px";
	    event.target.style.transition = "all 0.5s";
	  }

	  function zoomOut2(event) {
	    event.target.style.width = "200px";
	    event.target.style.height = "140px";
	    event.target.style.transition = "all 0.5s";
	  }
  
</script> 
 
</body>
</html>