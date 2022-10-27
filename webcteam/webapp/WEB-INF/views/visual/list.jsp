<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.tab li {
	width: 140px;
	line-height: 40px;
	color: #3367d6;
	border: 1px solid #3367d6;
	border-bottom: none;
	cursor: pointer;
}

.tab li:not(:first-child) {
	border-left: none;
}

.tab li.active {
	background-color: #3367d6;
	color: #fff;
}

#tab-content {
	width: 1200px;
	height: 550px;
	margin: 20px auto;
}

.c3-axis, .c3-chart {
	font-size: 15px;
}

.legend {
	width: 15px;
	height: 15px;
	margin-right: 5px
}

.tab {
	margin-bottom: 30px; 
	display : flex;
	border-bottom: 1px solid black;
	display: flex;
	
}

#price {
margin-left: 300px;
}

#menu{
margin-left: 400px;
}

</style>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.css" />
<script src="https://d3js.org/d3.v3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>

</head>
<body>
	<div id='tab-content'>
		<div class='tab'>
			<label><input type='radio' name='chart' id="price" value='bar'>월별 매출현황</label> 
			<label><input type='radio' name='chart' id="menu" value='donut'>일별 매출현황</label>
		</div>


		<div id='view_chart'></div>
	</div>
	<script>

function init(){
	$('#view_chart').empty();
}

$('[name=chart]').change(function(){ //매출현황/인기메뉴 선택 변경시
	init();
	if( $('[id=price]:checked').val()=='bar' ){
		month_total_price();		
	}else{
		day_total_price();
	}
});

function day_total_price(){
	$.ajax({
		url: 'visual/day_total_price',
		data: {
			id: ${loginInfo.id} , 
		},
		success: function( response ){
			
			var month = [ '일별' ], price = [ '매출' ], info = [] ;
			$(response).each(function(){
				month.push( this.MONTH+'일' );
				price.push( this.PRICE );
			});
			make_chart( [month, price] ); 	

		
			
		},error: function(){
			
		}
	});
}






function month_total_price(){
	$.ajax({
		url: 'visual/total_price',
		data: {
			id: ${loginInfo.id} , 
		},
		success: function( response ){
			
			var month = [ '월별' ], price = [ '매출' ], info = [] ;
			$(response).each(function(){
				month.push( this.MONTH+'월' );
				price.push( this.PRICE );
			});
			make_chart( [month, price] ); 	

		
			
		},error: function(){
			
		}
	});
}

 function make_chart(info){
	line_chart(info);
}
 

//선그래프
 function line_chart(info){
	var chart = c3.generate({
 	  bindto: "#view_chart",
 	  data: {
 	    columns: info,
 	   	x: info[0][0],
 	  }, 
 	 axis: {
			x: { type:'category' },
			y: { label:{ text: info[1][0], position:'outer-top' } },
		},
 	 size: { height:450 },
 	 grid: { y:{ show:true }, }, //y축 그리드
 	
 	}); 
	

	
}
 


</script>
</body>
</html>