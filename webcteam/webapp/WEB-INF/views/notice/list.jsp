<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
th,  td { color: #000; }
input {
	height: 30px;
	padding: 0 5px;
	font-size: 13px;
	border: 1px solid #b0b0b0;
	border-radius: 5px;
	margin-bottom: 5px;
}
input:focus, select:focus, textarea:focus {outline: none; border-color: #a181de;}

select {
	height: 32px;
	cursor: pointer;
	padding-left: 10px;
	border: 1px solid black;
	border-radius: 5px;
	margin-bottom: 5px;
	
}


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



article, aside, details, figcaption, figure, footer, header, hgroup,

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

	display: inline-block;

	margin-top: 30px;

	margin-left: 30px;

	margin-bottom: 30px;

	position: relative;

	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);

	overflow: hidden;

}



.card-header {

	-webkit-transition: 0.5s; /*사파리 & 크롬*/

    -moz-transition: 0.5s;  /*파이어폭스*/

    -ms-transition: 0.5s;	/*인터넷 익스플로러*/

    -o-transition: 0.5s;  /*오페라*/

    transition: 0.5s;

	width: 100%;

	height: 270px;

	border-radius: 15px 15px 0 0;

	background-image: url("images/korea.jpeg");

	background-size: 100% 280px;

	background-repeat: no-repeat;	

}



.card:hover .card-header  {

	opacity: 0.8;

	height: 100px;

}



.card-header-is_closed{

    background-color: #EF5A31 ;

    color: #FFF ;

    font-weight: bold ;

    text-align: center ;

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



.card-body {

}



.card-body-header{

	line-height: 25px;

	margin: 10px 20px 0px 20px;

}



.card-body-description  {

    opacity: 0;

    color: #757B82;

    line-height: 25px;

    -webkit-transition: .2s ease-in-out; /*사파리&크롬*/

    -moz-transition: .2s ease-in-out; /*파이어폭스*/

    -ms-transition: .2s ease-in-out; /*익스플로러*/

    -o-transition: .2s ease-in-out; /*오페라*/

    transition : .2s ease-in-out;

    overflow: hidden;

	height: 180px;

	margin: 5px 20px;

}



.card:hover .card-body-description {

    opacity: 1;

    -webkit-transition: .5s ease-in-out;

    -moz-transition: .5s ease-in-out;

    -ms-transition: .5s ease-in-out;

    -o-transition: .5s ease-in-out;

    transition : .5s ease-in-out;

    overflow: scroll;

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



.reg_date {

	float: right;

} 
</style>
<link rel="stylesheet" type="text/css"  href="css/common.css?<%=new java.util.Date()%>" />
</head>
<body>
<h2 class = 'h3'>공지사항</h2>

<form method="post" action="list.no">
<input type = 'hidden' name = 'curPage' value = '1'>
<div id = 'list-top' class = "w-px1200">
	<ul>
		<li>
		 <select class ='w-px100' name ='search' >
		 <option value ='all' ${page.search eq 'all' ? 'selected' : '' }>전체</option>
		 <option value ='title'  ${page.search eq 'all' ? 'selected' : '' }>제목</option>
		 <option value = 'content'  ${page.search eq 'all' ? 'selected' : '' }>내용</option>
		 <option value = 'writer'  ${page.search eq 'all' ? 'selected' : '' }>작성자</option>
		 <option value = 't_c' ${page.search eq 'all' ? 'selected' : '' }>제목+내용</option>
		 </select>
		</li>
		<li><input type = 'text' name = 'keyword' class = 'w-px300' value = '${page.keyword }'   style="height: 33px;"></li>
		<li><a class = 'btn-fill' onclick="$('form').submit()">검색</a></li>
	</ul>
	<ul>

		<c:if test="${loginInfo.manager eq 'M'}">
		<li><a class = 'btn-fill' href ='new.no'>글쓰기</a></li>
		</c:if>
	</ul>
</div>
</form>

<table class = 'tb-list w-px1200' >
<colgroup>
	<col width="100px">
	<col>
	<col width="120px">
	<col width="120px">
	<col width="80px">
</colgroup>
<tr>
	<th>번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일자</th>
	<th>조회수</th>
</tr>
 <c:forEach items='${page.list}' var = 'vo'>
<tr>
	<td style="text-align: center;">${vo.no}</td>
	<td class = 'left'>
	<!-- 답글인 경우 보여주는 말풍선 아이콘 -->
	 <c:forEach var = 'i' begin="1" end = "${vo.indent}">
	   ${i eq vo.indent ? '<i class="font-b fa-regular fa-comment-dots"></i>' : '&nbsp;&nbsp;' } <!-- 들여쓰기도 해주자 -->
		
	 </c:forEach>
		<a href = 'detail.no?id=${vo.id}'>${vo.title}</a></td>
	<td style="text-align: center;">${vo.nickname}</td>
	<td style="text-align: center;">${vo.writedate}</td>
	<td style="text-align: center;">${vo.readcnt}</td>
	
</c:forEach>
</table>
<div class = 'btnSet'>
</div>


</body>
</html>