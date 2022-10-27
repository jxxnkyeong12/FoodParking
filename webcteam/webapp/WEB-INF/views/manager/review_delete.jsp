<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	margin: 0px auto;
	margin-top: 100px;
	width: 1200px;
	table-layout: fixed;
}

.tg td {
	border-color: black;
	border-style: solid;
	border-width: 1px;
	font-family: Arial, sans-serif;
	font-size: 14px;
	overflow: hidden;
	padding: 10px 5px;
	word-break: normal;
	
}

.tg th {
	border-color: black;
	border-style: solid;
	border-width: 1px;
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	overflow: hidden;
	padding: 10px 5px;
	word-break: normal;
}

.tg .tg-pelv {
	background-color: #efefef;
	border-color: #ffffff;
	color: black;
	text-align: center;
	vertical-align: top;
}


.tg .tg-2egc {
	background-color: #efefef;
	border-color: #ffffff;
	text-align: center;
	vertical-align: top;
}

.tg .tg-pgxb {
	background-color: #f8a102;
	border-color: #ffffff;
	color: #000000;
	font-size: 100%;
	text-align: center;
	vertical-align: top;
	
}

.tg .tg-pelv .app_btn {
	border: 0.1px solid black;
	background-color: black;
	color: white;
	cursor: pointer;
	padding: 5px;
	text-align: center;
}
</style>
</head>
<body>
<table class="tg">
<colgroup>
	<col width="80px">
	<col width ="80px">
	<col width="80px">
	<col width="120px">
	<col width="120px">
</colgroup>
<thead>
  <tr>
    <th class="tg-pgxb">리뷰번호</th>
    <th class="tg-pgxb">가게명</th>
    <th class="tg-pgxb">별점</th>
    <th class="tg-pgxb">닉네임</th>
    <th class="tg-pgxb">작성한날짜</th>
    <th class="tg-pgxb">신고된날짜</th>
    <th class="tg-pgxb">리뷰 이미지</th>
    <th class="tg-pgxb">리뷰 내용</th>
    <th class="tg-pgxb">심사결과</th>
  </tr>
</thead>
<tbody>
<c:forEach items="${list }" var="vo">
  <tr>
    <td class="tg-pelv">${vo.star_code}</td>
    <td class="tg-pelv">${vo.store_name}</td>
    <td class="tg-pelv">${vo.star_rating}</td>
    <td class="tg-pelv">${vo.nickname}</td>
    <td class="tg-pelv">${vo.writedate}</td>
    <td class="tg-pelv">${vo.com_writedate}</td>
    <td class="tg-pelv"><img src = '${vo.review_image}'></td>
    <td class="tg-pelv">${vo.review_content}</td>
    <td class="tg-pelv">
    	<!-- <input type="button" class='app_btn' onclick="finish()" value="완료">  -->
    	<a class="app_btn" onclick="finish(${vo.star_code})">삭제</a>
    	<a class="app_btn" onclick="location='admin_store_cancle?id=${vo.id}'">취소</a>
    </td>
  </tr>
  <form method='post' action='admin_review_delete'>
	<input type='hidden' name='star_code' value='${vo.star_code}' >
</form>
  </c:forEach>
  
  
</tbody>
</table>
<script src='js/common_ssb.js?<%=new java.util.Date()%>'></script>
<script type="text/javascript">
function finish(star_code) {
   if(confirm('삭제처리 하시겠습니까?')){
      $('form').attr('action', 'admin_review_delete');
      $('form').submit();
   }
}

//취소 눌렀을 때
function cancle(id) {
   if(confirm('삭제처리 하시겠습니까?')){
      var a = prompt("취소사유를 입력해 주세요");
      if(a == null){
         alert('승인취소를 취소하였습니다')
      }else{
         $('[name=cancle]').val(a);
         $('[name=id]').val(id);
         $('form').attr('action', 'admin_store_cancle');
         $('form').submit();
      }
   }
}
</script>






</body>




</html>