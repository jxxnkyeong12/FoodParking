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
<thead>
  <tr>
    <th class="tg-pgxb">사업자번호</th>
    <th class="tg-pgxb">사장이름</th>
    <th class="tg-pgxb">전화번호</th>
    <th class="tg-pgxb">가게이름</th>
    <th class="tg-pgxb">우편번호</th>
    <th class="tg-pgxb">가게주소</th>
    <th class="tg-pgxb">가게상세주소</th>
    <th class="tg-pgxb">사업자 등록증</th>
    <th class="tg-pgxb">영업신고증</th>
    <th class="tg-pgxb">심사결과</th>
  </tr>
</thead>
<tbody>
<c:forEach items="${list }" var="vo">
  <tr>
    <td class="tg-pelv">${vo.b_num }</td>
    <td class="tg-pelv">${vo.b_name }</td>
    <td class="tg-pelv">${vo.b_phone }</td>
    <td class="tg-pelv">${vo.store_name }</td>
    <td class="tg-pelv">${vo.post }</td>
    <td class="tg-pelv">${vo.store_addr }</td>
    <td class="tg-pelv">${vo.store_addr_more }</td>
    <td class="tg-pelv"><a href='download?id=${vo.id }&b_enter_copy=${vo.b_enter_copy }' >${vo.b_enter_copy }</a></td>
    <td class="tg-pelv"><a href='download?id=${vo.id }&b_enter_copy=${vo.b_enter_copy2 }' >${vo.b_enter_copy2 }</a></td>
    <td class="tg-pelv">
    	<!-- <input type="button" class='app_btn' onclick="finish()" value="완료">  -->
    	<a class="app_btn" onclick="finish()">완료</a>
    	<!-- <input type="button" class='app_btn cancle' onclick="cancle()" value="취소">  -->
    	<a class="app_btn" onclick="location='admin_store_cancle?id=${vo.id}'">취소</a>
    </td>
  </tr>
  <form method='post' action='admin_make_store'>
	<input type='hidden' name='id' value='${vo.id}' >
	<input type='hidden' name='b_num' value='${vo.b_num}' >
	<input type='hidden' name=b_name value='${vo.b_name }' >
	<input type='hidden' name=b_phone value='${vo.b_phone }' >
	<input type='hidden' name=store_name value='${vo.store_name }' >
	<input type='hidden' name='post' value='${vo.post }' >
	<input type='hidden' name='store_addr' value='${vo.store_addr }' >
	<input type='hidden' name='store_addr_more' value='${vo.store_addr_more }' >
</form>
  </c:forEach>
  
  
</tbody>
</table>


<!-- b_num, store_addr, b_name, b_phone, b_enter_copy,b_enter_copy2
            , store_name, store_addr_more, post; 
   private int enter_status, id; -->
</body>

<script src='js/common_ssb.js?<%=new java.util.Date()%>'></script>
<script type="text/javascript">
function finish() {
	if(confirm('신청수락하시겠습니까?')){
		$('form').submit();
	}
}
</script>
</html>













