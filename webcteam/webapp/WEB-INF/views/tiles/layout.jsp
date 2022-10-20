<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix='tiles' %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix='c'%>

<c:choose>
	<c:when test="${category eq 'in'}"><c:set var='title' value='- 소개'/></c:when>
	<c:when test="${category eq 'en'}"><c:set var='title' value='- 입점신청양식'/></c:when>


	<c:when test="${category eq 'no'}"><c:set var='title' value='- 공지/이벤트'/></c:when>
	<c:when test="${category eq 'bo'}"><c:set var='title' value='- 푸드 소식'/></c:when>
	<c:when test="${category eq 'da'}"><c:set var='title' value='- 이건 뭐할까'/></c:when>
	<c:when test="${category eq 'st'}"><c:set var='title' value='- 나의 가게'/></c:when>
	<c:when test="${category eq 'ho'}"><c:set var='title' value='- 가게'/></c:when>



</c:choose>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>푸드 파킹 ${title}</title>
<!-- 우리꺼 아이콘 -->
<link rel="icon" type = 'image/x-icon' href="images/icon.png">



<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="https://designskin13.clickn.co.kr/css/lib.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://designskin13.clickn.co.kr/vendors/Slick/slick.css" />
<link rel="stylesheet" type="text/css" href="https://designskin13.clickn.co.kr/vendors/Swiper/swiper.min.css" /><!-- Swiper js-->
<link rel="stylesheet" type="text/css" href="vendors/Swiper/swiper.min.js" /><!-- Swiper js-->
<link rel="stylesheet" type="text/css" href="https://designskin13.clickn.co.kr/vendors/Fontawesome/css/froala.css"><!-- Froala 에 Font Awesome 서비스하기 위함 -->
<link rel="stylesheet" type="text/css" href="https://designskin13.clickn.co.kr/vendors/Fontawesome/css/all.min.css"><!-- Font Awesome -->
<link rel="stylesheet" type="text/css" href="https://designskin13.clickn.co.kr/vendors/Froala/css/froala_editor.pkgd.css" /><!-- Froala -->
<link rel="stylesheet" type="text/css" href="https://designskin13.clickn.co.kr/vendors/Datepicker/css/datepicker.css" /><!-- datapicker -->
<link rel="stylesheet" type="text/css" href="https://designskin13.clickn.co.kr/vendors/Dropzone/css/dropzone.min.css" /><!-- Dropzone -->
<link rel="stylesheet" type="text/css" href="https://designskin13.clickn.co.kr/css/animation.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://designskin13.clickn.co.kr/css/skin_common.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_common.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_header.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_board.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_boardRecent.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_contents.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_form.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_image.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_layout.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_line.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_map.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_slide.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_text.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_video.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_gallery.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_table.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_footer.css?<%=new java.util.Date()%>" />
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_builderProductA.css?<%=new java.util.Date()%>" /><!-- 상품 디스플레이 -->
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_builderProductB.css?<%=new java.util.Date()%>" /><!-- 상품 문의 -->
<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/module_builderProductC.css?<%=new java.util.Date()%>" /><!-- 상품 후기 -->

<link href="https://fonts.googleapis.com/css2?family=Black+And+White+Picture&family=Black+Han+Sans&family=Cute+Font&family=Do+Hyeon&family=Dokdo&family=East+Sea+Dokdo&family=Gaegu:wght@400;700&family=Gamja+Flower&family=Gugi&family=Hi+Melody&family=Jua&family=Kirang+Haerang&family=Nanum+Brush+Script&family=Nanum+Gothic:wght@400;700&family=Nanum+Myeongjo:wght@400;700&family=Nanum+Pen+Script&family=Poor+Story&family=Single+Day&family=Song+Myung&family=Stylish&family=Sunflower:wght@300;700&family=Yeon+Sung&display=swap" rel="stylesheet" />
<link href="https://fonts.googleapis.com/css2?family=Amatic+SC:wght@400;700&family=Archivo:wght@400;700&family=Exo:wght@200;400;700&family=Fauna+One&family=Fjalla+One&family=Graduate&family=Iceland&family=Inconsolata:wght@300;500;700&family=Italianno&family=Josefin+Sans:wght@200;400;700&family=Lato:wght@100;300;400;700&family=Libre+Baskerville:wght@400;700&family=Montserrat:wght@200;300;400;500;600;700&family=Nixie+One&family=Nunito:wght@200;400;700&family=Oswald:wght@200;400;500;700&family=Playfair+Display:wght@400;500;700&family=Poppins:wght@200;400;700&family=Raleway:wght@200;400;700&family=Roboto:wght@100;300;400;500;700&family=Simonetta:wght@400;900&family=Space+Mono:wght@400;700&family=Special+Elite&family=Stardos+Stencil:wght@400;700&family=Titan+One&family=Titillium+Web:wght@200;400;700&family=Trocchi&family=Ultra&family=Wellfleet&family=Abril+Fatface&family=Bodoni+Moda:wght@400;600;800&family=DM+Serif+Display&family=DM+Serif+Text&family=EB+Garamond:wght@400;500&family=Open+Sans:wght@300;400;700&family=Petit+Formal+Script&family=Quicksand:wght@300;700&family=Cinzel&family=Lobster&family=MonteCarlo&family=Shrikhand&display=swap" rel="stylesheet" />

    
	<link rel="stylesheet" type="text/css" href="https://storage.clickn.co.kr//css/modules/skin_main.css?<%=new java.util.Date()%>" /><!-- 메인에서만 실행되어야하는 CSS -->

<script>
var LOGIN_TYPE = 'non-members';

var PAGE_CODE = 'main';
var PAGE_MODE = 'real';

    var HEADER_CONFIG = {"layout_type":"type1","header_module_name":"header_A010","header_module_parents":"header_A001","header_submenu_design":"design4","header_submenu_direction":"right","mypage_menu":"5","header_logo":"\file\/822781866","login_menu":"yes","login_menu_name":"LOGIN","logout_menu_name":"LOGOUT","join_menu_name":"JOIN","login_before_greeting":null,"login_after_greeting":"님 환영합니다!","gnb_menu":"3","navigation_fontsize":"16","util_menu":"no","header_brightness":"light","header_bgcolor":"#fff","navigation_color":null,"navigation_color_default":"on","navigation_font":"Noto Sans KR","shopping_menuorder":null,"shopping_my":"no","shopping_cart":"no","shopping_search":"no","header_flying":"yes","page_code":"page_about","util_link":"[{\"name\":\"MY SHOPPING\",\"address\":\"\\\/mypages\",\"target\":\"_self\"},{\"name\":\"ORDER\",\"address\":\"#\",\"target\":\"_self\"}]"};
    var FOOTER_CONFIG = {"footer_module_name":"footer_A009","footer_module_parents":"footer_A001","footer_textfield1":"no","footer_textfield1_source":null,"footer_textfield2":"no","footer_textfield2_source":null,"footer_menu":"yes","fnb_menu":"4","footer_logo_src":"\/file\/297741583","footer_info":"yes","footer_copyright":"yes","footer_copyright_source":"<p>COPYRIGHT (c) (주)회사명 ALL RIGHTS RESERVED.<\/p><p data-f-id=\"pbf\" style=\"text-align: center; font-size: 14px; margin-top: 30px; opacity: 0.65;\">Powered by <a href=\"https:\/\/www.froala.com\/wysiwyg-editor?pb=1\" title=\"Froala Editor\">Froala Editor<\/a><\/p>","footer_escro":"no","footer_brightness":null,"footer_bgcolor2":null,"footer_fontcolor":null,"footer_fontcolor_default":"on","save_seq":"301","save_mode":"real","escro":"[{\"img_src\":\"\\\/file\\\/1919701584\",\"link_address\":\"#escro2\",\"link_target\":\"_blank\"}]"};

    var BASIC_CONFIG = {"site_name":"The Medical Clinic","co_name":"(주)회사명","ceo_name":"홍길동","co_phone":"02-1544-1718","co_email":"clickn@gabiacns.com","co_zipcode":"13494","co_address":"경기 성남시 분당구 대왕판교로 670 (유스페이스2)","co_address_detail":"000호","co_regist_num":"000-00-00000","co_it_num":"0000-성남분당-0000","site_desc":null,"co_nation":"kr","co_fax":null,"timezone":"Asia\/Seoul","date_format":"Y.m.d","currency":"krw","sns_img":null,"fav_img":"\/file\/519550687","fav_img_tmp":"\/file\/1555885741","map_type":"direct"};

    var MAP_CONFIG = {"map_type":"direct","map_service_type":"kakao","map_api":{"naver":{"client_id":null,"client_secret":null},"kakao":{"api_key":null},"google":{"api_key":null}},"map_feature":{"map_marker_icon":null,"map_zoom":null,"map_zoom_control":null},"map_script":{"KAKAO":"https:\/\/dapi.kakao.com\/v2\/maps\/sdk.js?libraries=services,clusterer,drawing&appkey=","NAVER":"https:\/\/openapi.map.naver.com\/openapi\/v3\/maps.js?submodules=geocoder&ncpClientId=","GOOGLE":"https:\/\/maps.googleapis.com\/maps\/api\/js?key="}};

var PAGE_CONFIG = '';

    var _PATH = "\/" ;

var MODULES_STORAGE_BASE_URI = "https:\/\/storage.clickn.co.kr\/" ;
</script>
<script src="https://designskin13.clickn.co.kr/lang/skin_js.js"></script>
<script src="https://designskin13.clickn.co.kr/lang/common.js?m=front&amp;g=common"></script>
<script src="https://designskin13.clickn.co.kr/vendors/Jquery/jquery.min.js"></script>
<script src="https://designskin13.clickn.co.kr/vendors/Twbs-pagination/jquery.twbsPagination.js"></script>
<script src="https://designskin13.clickn.co.kr/js/common.js?<%=new java.util.Date()%>"></script>
<script src="https://designskin13.clickn.co.kr/js/skin_ui_functions.js?<%=new java.util.Date()%>"></script><!-- 우선 순위 높아야하는 UI Functions -->


<script>
var USER = ""
</script>


</head>
<body>
<tiles:insertAttribute name='header'/>
<div id='container'>
<tiles:insertAttribute name='container'/>
</div>
<tiles:insertAttribute name='footer'/>

</body>
</html>