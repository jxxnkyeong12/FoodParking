<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>   
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
	vertical-align: baseline;
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
	width: 300px;
	height: 230px;
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
	margin-left: 200px;
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
  <p class = 'stroe_name'> 평점 </p><br/>
  		 <p> - 청결 </p> <p>:	${vo.clean} </p>
  		 	<p id = 'star'>
  		 				   <a value="${vo.clean}">★</a> 
						   <a value="${vo.clean}">★</a>
						   <a value="${vo.clean}">★</a>
						   <a value="${vo.clean}">★</a>
						   <a value="${vo.clean}">★</a>
						  
  		    </p><br/>
  		 <p> 맛 </p> <p>:	${vo.taste} </p><br/>
  		 <p> 분위기 </p> <p>:	${vo.mood} </p><br/>
  		 <p> 친절 </p> <p>:	${vo.kind} </p><br/>
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
  <p class = 'stroe_name'> 식사시간 </p> <p>:	${vo.open_close} </p>
  <p class = 'stroe_name'> 특징 </p> <p>:	좌석, 테이블 서비스, 예약 </p>
 </div>
 
  <div class = 'top'>
 <p class = 'stroe_name'> 위치 및 문의 정보 </p><br/>
  <p> ${vo.store_addr } ${vo.addr_more } </p> 
 </div> 
 

 
 
</div>


<!--카카오 지도 API JK  -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=912cf98bacf67dfbfdcbc38c95ec1762"></script>
<script>
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