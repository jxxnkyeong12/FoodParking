<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"	prefix="c" %>


<link rel="shortcut icon" href="file/519550687">



<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://designskin13.clickn.co.kr/css/lib.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://designskin13.clickn.co.kr/vendors/Slick/slick.css" />
<link rel="stylesheet" type="text/css"
	href="https://designskin13.clickn.co.kr/vendors/Swiper/swiper.min.css" />
<!-- Swiper js-->
<link rel="stylesheet" type="text/css"
	href="https://designskin13.clickn.co.kr/vendors/Fontawesome/css/froala.css">
<!-- Froala 에 Font Awesome 서비스하기 위함 -->
<link rel="stylesheet" type="text/css"
	href="https://designskin13.clickn.co.kr/vendors/Fontawesome/css/all.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" type="text/css"
	href="https://designskin13.clickn.co.kr/vendors/Froala/css/froala_editor.pkgd.css" />
<!-- Froala -->
<link rel="stylesheet" type="text/css"
	href="https://designskin13.clickn.co.kr/vendors/Datepicker/css/datepicker.css" />
<!-- datapicker -->
<link rel="stylesheet" type="text/css"
	href="https://designskin13.clickn.co.kr/vendors/Dropzone/css/dropzone.min.css" />
<!-- Dropzone -->
<link rel="stylesheet" type="text/css"
	href="https://designskin13.clickn.co.kr/css/animation.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://designskin13.clickn.co.kr/css/skin_common.css?v=20221005" />

<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_common.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_header.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_board.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_boardRecent.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_contents.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_form.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_image.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_layout.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_line.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_map.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_slide.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_text.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_video.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_gallery.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_table.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_footer.css?v=20221005" />
<link rel="stylesheet" type="text/css"
	href="https://storage.clickn.co.kr//css/modules/module_builderProductA.css?v=20221005" />


<link
	href="https://fonts.googleapis.com/css2?family=Black+And+White+Picture&family=Black+Han+Sans&family=Cute+Font&family=Do+Hyeon&family=Dokdo&family=East+Sea+Dokdo&family=Gaegu:wght@400;700&family=Gamja+Flower&family=Gugi&family=Hi+Melody&family=Jua&family=Kirang+Haerang&family=Nanum+Brush+Script&family=Nanum+Gothic:wght@400;700&family=Nanum+Myeongjo:wght@400;700&family=Nanum+Pen+Script&family=Poor+Story&family=Single+Day&family=Song+Myung&family=Stylish&family=Sunflower:wght@300;700&family=Yeon+Sung&display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=Amatic+SC:wght@400;700&family=Archivo:wght@400;700&family=Exo:wght@200;400;700&family=Fauna+One&family=Fjalla+One&family=Graduate&family=Iceland&family=Inconsolata:wght@300;500;700&family=Italianno&family=Josefin+Sans:wght@200;400;700&family=Lato:wght@100;300;400;700&family=Libre+Baskerville:wght@400;700&family=Montserrat:wght@200;300;400;500;600;700&family=Nixie+One&family=Nunito:wght@200;400;700&family=Oswald:wght@200;400;500;700&family=Playfair+Display:wght@400;500;700&family=Poppins:wght@200;400;700&family=Raleway:wght@200;400;700&family=Roboto:wght@100;300;400;500;700&family=Simonetta:wght@400;900&family=Space+Mono:wght@400;700&family=Special+Elite&family=Stardos+Stencil:wght@400;700&family=Titan+One&family=Titillium+Web:wght@200;400;700&family=Trocchi&family=Ultra&family=Wellfleet&family=Abril+Fatface&family=Bodoni+Moda:wght@400;600;800&family=DM+Serif+Display&family=DM+Serif+Text&family=EB+Garamond:wght@400;500&family=Open+Sans:wght@300;400;700&family=Petit+Formal+Script&family=Quicksand:wght@300;700&family=Cinzel&family=Lobster&family=MonteCarlo&family=Shrikhand&display=swap"
	rel="stylesheet" />




<script>
	var LOGIN_TYPE = 'non-members';

	var PAGE_CODE = 'main';
	var PAGE_MODE = 'real';

	var HEADER_CONFIG = {
		"layout_type" : "type1",
		"header_module_name" : "header_A010",
		"header_module_parents" : "header_A001",
		"header_submenu_design" : "design4",
		"header_submenu_direction" : "right",
		"mypage_menu" : "5",
		"header_logo" : "\file\/822781866",
		"login_menu" : "yes",
		"login_menu_name" : "LOGIN",
		"logout_menu_name" : "LOGOUT",
		"join_menu_name" : "JOIN",
		"login_before_greeting" : null,
		"login_after_greeting" : "님 환영합니다!",
		"gnb_menu" : "3",
		"navigation_fontsize" : "16",
		"util_menu" : "no",
		"header_brightness" : "light",
		"header_bgcolor" : "#fff",
		"navigation_color" : null,
		"navigation_color_default" : "on",
		"navigation_font" : "Noto Sans KR",
		"shopping_menuorder" : null,
		"shopping_my" : "no",
		"shopping_cart" : "no",
		"shopping_search" : "no",
		"header_flying" : "yes",
		"page_code" : "page_about",
		"util_link" : "[{\"name\":\"MY SHOPPING\",\"address\":\"\\\/mypages\",\"target\":\"_self\"},{\"name\":\"ORDER\",\"address\":\"#\",\"target\":\"_self\"}]"
	};
	var FOOTER_CONFIG = {
		"footer_module_name" : "footer_A009",
		"footer_module_parents" : "footer_A001",
		"footer_textfield1" : "no",
		"footer_textfield1_source" : null,
		"footer_textfield2" : "no",
		"footer_textfield2_source" : null,
		"footer_menu" : "yes",
		"fnb_menu" : "4",
		"footer_logo_src" : "\/file\/297741583",
		"footer_info" : "yes",
		"footer_copyright" : "yes",
		"footer_copyright_source" : "<p>COPYRIGHT (c) (주)회사명 ALL RIGHTS RESERVED.<\/p><p data-f-id=\"pbf\" style=\"text-align: center; font-size: 14px; margin-top: 30px; opacity: 0.65;\">Powered by <a href=\"https:\/\/www.froala.com\/wysiwyg-editor?pb=1\" title=\"Froala Editor\">Froala Editor<\/a><\/p>",
		"footer_escro" : "no",
		"footer_brightness" : null,
		"footer_bgcolor2" : null,
		"footer_fontcolor" : null,
		"footer_fontcolor_default" : "on",
		"save_seq" : "301",
		"save_mode" : "real",
		"escro" : "[{\"img_src\":\"\\\/file\\\/1919701584\",\"link_address\":\"#escro2\",\"link_target\":\"_blank\"}]"
	};
	var BLOCK_LAYOUTS = {
		"module-1" : {
			"seq" : 1860,
			"save_seq" : 295,
			"block_id" : "module-1",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "slide_B018",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:19",
			"childs" : [],
			"module" : {
				"module_name" : "slide_B018",
				"content_type1" : "slide",
				"content_type2" : "slide-type-B",
				"module_possible" : "all",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/slide\/slide_B018.jpg",
				"module_parents" : "slide_B001",
				"module_category1" : "slide",
				"module_category2" : "",
				"module_distribute" : "yes",

			}
		},
		"module-2" : {
			"seq" : 1861,
			"save_seq" : 295,
			"block_id" : "module-2",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "line_B003",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:19",
			"childs" : [],
			"module" : {
				"module_name" : "line_B003",
				"content_type1" : "line",
				"content_type2" : "line-type-B",
				"module_possible" : "all",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/line\/line_B003.jpg",
				"module_parents" : "line_B003",
				"module_category1" : "line",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-module-name='line_B003' data-module-parents='line_B003' data-content-type1='line' data-content-type2='line-type-B' data-background-color='' data-background-image='' data-background-image-options='' data-background-attachment='' data-background-filter='' data-line-color='#bbbbbb'><div class='module_wrap fullsize_max' data-layout-fullsize='yes'><div class='module_container' data-padding-top='40' data-padding-bottom='40'><\/div><\/div><\/div>",
				"module_contents" : "<div class='line'><svg viewBox='0 0 323 21' xmlns='http:\/\/www.w3.org\/2000\/svg'><path d='M151.53.18,172,20.45' \/><path d='M172,.9,151.53,21.18' \/><path d='M323,11.18H183'\/><path d='M140,11.18H0' \/><\/svg><\/div>"
			}
		},
		"module-3" : {
			"seq" : 1862,
			"save_seq" : 295,
			"block_id" : "module-3",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "text_A008",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:19",
			"childs" : [],
			"module" : {
				"module_name" : "text_A008",
				"content_type1" : "text",
				"content_type2" : "text-type-A",
				"module_possible" : "all",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/text\/text_A008.jpg",
				"module_parents" : "text_A001",
				"module_category1" : "text",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-content-type1='text' data-content-type2='text-type-A' data-module-name='text_A008' data-module-parents='text_A001' data-background-color='' data-background-image='' data-background-image-options='' data-background-attachment='' data-background-filter=''><div class='module_wrap' data-layout-fullsize='no'><div class='module_container' data-padding-top='40' data-padding-bottom='40'><\/div><\/div><\/div>",

				"module_contents" : "<div data-text-editable='true' style='font-family: Quicksand; font-size: 20px; font-weight: 400; text-align: center;'><p>About us<\/p><p style='font-size:10px;'>&nbsp;<\/p><\/div><div data-text-editable='true' style='font-size: 46px; font-family: Montserrat; font-weight: 100; text-align: center; color:#333;'><p style='line-height: 1.6;'>푸드파킹에 입점한 가게<\/p><\/div><div data-text-editable='true' style='font-size: 16px; color: rgb(110, 110, 110); text-align: center;'><p style='line-height: 1.6;'><span>&nbsp;<\/span><\/p><p style='line-height: 1.6;'><span><\/span><\/p><p style='line-height: 1.6;'><span>푸드파킹은 점주님들과 원활한 의사소통을 추구합니다.<\/span><\/p><p style='line-height: 1.6;'><span>&nbsp;<\/span><\/p><\/div><div data-text-editable='true' style='font-size: 13px; letter-spacing: 0.1em; color: #000000; text-align: center;'><p><span style=' text-decoration: underline;'><a href='#none' target=''>view more<\/a><\/span><\/p><\/div>"

			}
		},
		"module-4" : {
			"seq" : 1863,
			"save_seq" : 295,
			"block_id" : "module-4",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "line_B003",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:19",
			"childs" : [],
			"module" : {
				"module_name" : "line_B003",
				"content_type1" : "line",
				"content_type2" : "line-type-B",
				"module_possible" : "all",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/line\/line_B003.jpg",
				"module_parents" : "line_B003",
				"module_category1" : "line",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-module-name='line_B003' data-module-parents='line_B003' data-content-type1='line' data-content-type2='line-type-B' data-background-color='' data-background-image='' data-background-image-options='' data-background-attachment='' data-background-filter='' data-line-color='#bbbbbb'><div class='module_wrap fullsize_max' data-layout-fullsize='yes'><div class='module_container' data-padding-top='40' data-padding-bottom='40'><\/div><\/div><\/div>",
				"module_contents" : "<div class='line'><svg viewBox='0 0 323 21' xmlns='http:\/\/www.w3.org\/2000\/svg'><path d='M151.53.18,172,20.45' \/><path d='M172,.9,151.53,21.18' \/><path d='M323,11.18H183'\/><path d='M140,11.18H0' \/><\/svg><\/div>"
			}
		},
		"module-5" : {
			"seq" : 1864,
			"save_seq" : 295,
			"block_id" : "module-5",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "text_A001",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:20",
			"childs" : [],
			"module" : {
				"module_name" : "text_A001",
				"content_type1" : "text",
				"content_type2" : "text-type-A",
				"module_possible" : "all",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/text\/text_A001.jpg",
				"module_hashtag" : "",
				"module_parents" : "text_A001",
				"module_category1" : "text",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-content-type1='text' data-content-type2='text-type-A' data-module-name='text_A001' data-module-parents='text_A001' data-background-color='' data-background-image='' data-background-image-options='' data-background-attachment='' data-background-filter=''><div class='module_wrap' data-layout-fullsize='no'><div class='module_container' data-padding-top='40' data-padding-bottom='40'><\/div><\/div><\/div>",
				"module_contents" : "<div data-text-editable='true' style='text-align: center; font-size: 17px; font-weight: 300;'><p>우리 브랜드에 대하여<\/p><p style='font-size:10px;'>&nbsp;<\/p><\/div><div data-text-editable='true' style='text-align: center; font-size: 46px; font-family: Montserrat; font-weight: 100;'><p style='line-height: 1.2;'>About our Brand<\/p><\/div><div data-text-editable='true' style='text-align: center; font-size: 15px; font-weight: 300; color: rgb(153, 153, 153);'><p>&nbsp;<\/p><p>언덕 것은 비둘기, 아름다운 까닭이요, 있습니다. 벌레는 어머님, 별빛이 북간도에 않은 밤이 애기 그랬었습니다.<\/p><p>없이 나는 소녀들의 계십니다. 보고, 것은 이 마디씩 흙으로 무성할 동경과 멀리 계십니다. 무엇인지 마디씩 없이 묻힌 소녀들의 봅니다.<\/p><p>내 이웃 내 시인의 하나 불러 헤는 같이 이름을 까닭입니다.<\/p><\/div>"
			}
		},
		"module-6" : {
			"seq" : 1865,
			"save_seq" : 295,
			"block_id" : "module-6",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "contents_L025",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:20",
			"childs" : [],
			"module" : {
				"module_name" : "contents_L025",
				"content_type1" : "contents",
				"content_type2" : "contents-type-L",
				"module_possible" : "normal",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/contents\/contents_L025.jpg",
				"module_parents" : "layout_N4_01",
				"module_category1" : "contents",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-module-name='contents_L025' data-module-parents='layout_N4_01' data-layout-type='T2' data-content-type1='contents' data-content-type2='contents-type-L' data-background-color='' data-background-image='' data-background-image-options='' data-background-attachment='' data-background-filter=''><div class='module_wrap' data-layout-fullsize='no'><div class='module_container' data-padding-top='40' data-padding-bottom='40'><\/div><\/div><\/div>",

			}
		},
		"module-7" : {
			"seq" : 1866,
			"save_seq" : 295,
			"block_id" : "module-7",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "line_B003",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:20",
			"childs" : [],
			"module" : {
				"module_name" : "line_B003",
				"content_type1" : "line",
				"content_type2" : "line-type-B",
				"module_possible" : "all",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/line\/line_B003.jpg",
				"module_parents" : "line_B003",
				"module_category1" : "line",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-module-name='line_B003' data-module-parents='line_B003' data-content-type1='line' data-content-type2='line-type-B' data-background-color='' data-background-image='' data-background-image-options='' data-background-attachment='' data-background-filter='' data-line-color='#bbbbbb'><div class='module_wrap fullsize_max' data-layout-fullsize='yes'><div class='module_container' data-padding-top='40' data-padding-bottom='40'><\/div><\/div><\/div>",
				"module_contents" : "<div class='line'><svg viewBox='0 0 323 21' xmlns='http:\/\/www.w3.org\/2000\/svg'><path d='M151.53.18,172,20.45' \/><path d='M172,.9,151.53,21.18' \/><path d='M323,11.18H183'\/><path d='M140,11.18H0' \/><\/svg><\/div>"
			}
		},
		"module-18" : {
			"seq" : 1867,
			"save_seq" : 295,
			"block_id" : "module-18",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "text_A001",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:20",
			"childs" : [],
			"module" : {
				"module_name" : "text_A001",
				"content_type1" : "text",
				"content_type2" : "text-type-A",
				"module_possible" : "all",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/text\/text_A001.jpg",
				"module_hashtag" : "",
				"module_parents" : "text_A001",
				"module_category1" : "text",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-content-type1='text' data-content-type2='text-type-A' data-module-name='text_A001' data-module-parents='text_A001' data-background-color='' data-background-image='' data-background-image-options='' data-background-attachment='' data-background-filter=''><div class='module_wrap' data-layout-fullsize='no'><div class='module_container' data-padding-top='40' data-padding-bottom='40'><\/div><\/div><\/div>",
				"module_contents" : "<div data-text-editable='true' style='text-align: center; font-size: 17px; font-weight: 300;'><p>우리 브랜드에 대하여<\/p><p style='font-size:10px;'>&nbsp;<\/p><\/div><div data-text-editable='true' style='text-align: center; font-size: 46px; font-family: Montserrat; font-weight: 100;'><p style='line-height: 1.2;'>About our Brand<\/p><\/div><div data-text-editable='true' style='text-align: center; font-size: 15px; font-weight: 300; color: rgb(153, 153, 153);'><p>&nbsp;<\/p><p>언덕 것은 비둘기, 아름다운 까닭이요, 있습니다. 벌레는 어머님, 별빛이 북간도에 않은 밤이 애기 그랬었습니다.<\/p><p>없이 나는 소녀들의 계십니다. 보고, 것은 이 마디씩 흙으로 무성할 동경과 멀리 계십니다. 무엇인지 마디씩 없이 묻힌 소녀들의 봅니다.<\/p><p>내 이웃 내 시인의 하나 불러 헤는 같이 이름을 까닭입니다.<\/p><\/div>"
			}
		},
		"module-20" : {
			"seq" : 1868,
			"save_seq" : 295,
			"block_id" : "module-20",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "contents_L017",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:21",
			"childs" : [],
			"module" : {
				"module_name" : "contents_L017",
				"content_type1" : "contents",
				"content_type2" : "contents-type-B",
				"module_possible" : "normal",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/contents\/contents_L017.jpg",
				"module_parents" : "layout_N3_01",
				"module_category1" : "contentsB",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-content-type1='contents' data-content-type2='contents-type-B' data-module-name='contents_L017' data-module-parents='layout_N3_01' data-layout-type='T4' data-background-color='' data-background-image='' data-background-image-options='' data-background-attachment='' data-background-filter='' data-contents-all-num='3'><div class='module_wrap' data-layout-fullsize='no'><div class='module_container' data-padding-top='80' data-padding-bottom='50'><\/div><\/div><\/div>"
			}
		},
		"module-10" : {
			"seq" : 1869,
			"save_seq" : 295,
			"block_id" : "module-10",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "line_B003",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:21",
			"childs" : [],
			"module" : {
				"module_name" : "line_B003",
				"content_type1" : "line",
				"content_type2" : "line-type-B",
				"module_possible" : "all",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/line\/line_B003.jpg",
				"module_parents" : "line_B003",
				"module_category1" : "line",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-module-name='line_B003' data-module-parents='line_B003' data-content-type1='line' data-content-type2='line-type-B' data-background-color='' data-background-image='' data-background-image-options='' data-background-attachment='' data-background-filter='' data-line-color='#bbbbbb'><div class='module_wrap fullsize_max' data-layout-fullsize='yes'><div class='module_container' data-padding-top='40' data-padding-bottom='40'><\/div><\/div><\/div>",
				"module_contents" : "<div class='line'><svg viewBox='0 0 323 21' xmlns='http:\/\/www.w3.org\/2000\/svg'><path d='M151.53.18,172,20.45' \/><path d='M172,.9,151.53,21.18' \/><path d='M323,11.18H183'\/><path d='M140,11.18H0' \/><\/svg><\/div>"
			}
		},
		"module-11" : {
			"seq" : 1870,
			"save_seq" : 295,
			"block_id" : "module-11",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "text_A001",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:21",
			"childs" : [],
			"module" : {
				"module_name" : "text_A001",
				"content_type1" : "text",
				"content_type2" : "text-type-A",
				"module_possible" : "all",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/text\/text_A001.jpg",
				"module_hashtag" : "",
				"module_parents" : "text_A001",
				"module_category1" : "text",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-content-type1='text' data-content-type2='text-type-A' data-module-name='text_A001' data-module-parents='text_A001' data-background-color='' data-background-image='' data-background-image-options='' data-background-attachment='' data-background-filter=''><div class='module_wrap' data-layout-fullsize='no'><div class='module_container' data-padding-top='40' data-padding-bottom='40'><\/div><\/div><\/div>",
				"module_contents" : "<div data-text-editable='true' style='text-align: center; font-size: 17px; font-weight: 300;'><p>우리 브랜드에 대하여<\/p><p style='font-size:10px;'>&nbsp;<\/p><\/div><div data-text-editable='true' style='text-align: center; font-size: 46px; font-family: Montserrat; font-weight: 100;'><p style='line-height: 1.2;'>About our Brand<\/p><\/div><div data-text-editable='true' style='text-align: center; font-size: 15px; font-weight: 300; color: rgb(153, 153, 153);'><p>&nbsp;<\/p><p>언덕 것은 비둘기, 아름다운 까닭이요, 있습니다. 벌레는 어머님, 별빛이 북간도에 않은 밤이 애기 그랬었습니다.<\/p><p>없이 나는 소녀들의 계십니다. 보고, 것은 이 마디씩 흙으로 무성할 동경과 멀리 계십니다. 무엇인지 마디씩 없이 묻힌 소녀들의 봅니다.<\/p><p>내 이웃 내 시인의 하나 불러 헤는 같이 이름을 까닭입니다.<\/p><\/div>"
			}
		},
		"module-29" : {
			"seq" : 1871,
			"save_seq" : 295,
			"block_id" : "module-29",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "table_A001",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:21",
			"childs" : [],
			"module" : {
				"module_name" : "table_A001",
				"content_type1" : "table",
				"content_type2" : "table-type-A",
				"module_possible" : "normal",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/table\/table_A001.jpg",
				"module_parents" : "",
				"module_category1" : "table",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-module-name='table_A001' data-module-parents='' data-content-type1='table' data-content-type2='table-type-A' data-background-color='' data-background-image='' data-background-image-options='' data-background-attachment='' data-background-filter=''><div class='module_wrap' data-layout-fullsize='no'><div class='module_container' data-padding-top='40' data-padding-bottom='40'><\/div><\/div><\/div>",
				"module_contents" : "<div data-text-editable='true' class='table_module_wrap' data-table-type='table_module_a' data-table-name='table_module_a001' data-th-padding='5' data-td-padding='5' data-table-minwidth='800'><table><thead><tr><th>시간<\/th><th>체결가<\/th><th>전일대비<\/th><th>매도호가<\/th><th>매수호가<\/th><th>매도잔량<\/th><th>매수잔량<\/th><\/tr><\/thead><tbody><tr><td style='width: 14.28%;'>15:49:30<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><\/tr><tr><td style='width: 14.28%;'>15:49:30<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><\/tr><tr><td style='width: 14.28%;'>15:49:30<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><\/tr><tr><td style='width: 14.28%;'>15:49:30<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><\/tr><tr><td style='width: 14.28%;'>15:49:30<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><td style='width: 14.28%;'>0<\/td><\/tr><\/tbody><\/table><\/div>"
			}
		},
		"module-28" : {
			"seq" : 1872,
			"save_seq" : 295,
			"block_id" : "module-28",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "text_L003",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:22",
			"childs" : [],
			"module" : {
				"module_name" : "text_L003",
				"content_type1" : "text",
				"content_type2" : "text-type-L",
				"module_possible" : "normal",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/text\/text_L003.jpg",
				"module_parents" : "layout_N4_02",
				"module_category1" : "text",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-content-type1='text' data-content-type2='text-type-L' data-module-name='text_L003' data-module-parents='layout_N4_02' data-layout-type='T3' data-background-color='' data-background-image='https:\/\/storage.clickn.co.kr\/images\/module_contents\/text_L003.jpg' data-background-image-options='size_cover' data-background-attachment='fixed' data-background-filter=''><div class='module_wrap' data-layout-fullsize='no'><div class='module_container' data-padding-top='60' data-padding-bottom='60'><\/div><\/div><\/div>",

				"module_contents" : "<ul><li><ul><li><div data-is-vacant='no'><div data-text-editable='true' style='font-size: 45px; font-weight: 700; color: #ffffff; text-align: center;'><p style='line-height: 1.2;'>푸드파킹 시간 안내<\/p><\/div><div data-text-editable='true' style='font-size: 18px; color: #d2d2d2; font-weight: 300; text-align: center;'><p style='line-height:1.4;'>&nbsp;<\/p><p style='line-height:1.4;'>예약페이지에서 진료예약 서비스를 이용하실 수 있습니다.<\/p><p style='line-height:1.4;'>대기환자가 많은 경우 접수마감시간이 앞당겨 질 수 있습니다.<\/p><p style='line-height:1.4;'>&nbsp;<\/p><p style='line-height:1.4;'>&nbsp;<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant='no'><div data-text-editable='true' style='font-size: 24px; font-weight: 400; color: #ffffff; text-align: center;'><p style='line-height: 1;'>평일<\/p><p style='line-height: 1;'>&nbsp;<\/p><\/div><div data-text-editable='true' style='font-family: Roboto; font-size: 34px; font-weight: 500; color: #ffffff; text-align: center;'><p style='line-height: 1; '>09:00 - 18:00<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li class='line'><div data-is-vacant='no'><div data-text-editable='true' style='font-size: 24px; font-weight: 400; color: #ffffff; text-align: center;'><p style='line-height: 1;'>토\/일요일<\/p><p style='line-height: 1;'>&nbsp;<\/p><\/div><div data-text-editable='true' style='font-family: Roboto; font-size: 34px; font-weight: 500; color: #ffffff; text-align: center;'><p style='line-height: 1; '>09:00 - 16:00<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li class='line'><div data-is-vacant='no'><div data-text-editable='true' style='font-size: 24px; font-weight: 400; color: #ffffff; text-align: center;'><p style='line-height: 1;'>점심시간<\/p><p style='line-height: 1;'>&nbsp;<\/p><\/div><div data-text-editable='true' style='font-family: Roboto; font-size: 34px; font-weight: 500; color: #ffffff; text-align: center;'><p style='line-height: 1; '>13:00 - 14:00<\/p><\/div><\/div><\/li><\/ul><\/li><\/ul>"

			}
		},
		"module-14" : {
			"seq" : 1873,
			"save_seq" : 295,
			"block_id" : "module-14",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "line_B003",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:22",
			"childs" : [],
			"module" : {
				"module_name" : "line_B003",
				"content_type1" : "line",
				"content_type2" : "line-type-B",
				"module_possible" : "all",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/line\/line_B003.jpg",
				"module_parents" : "line_B003",
				"module_category1" : "line",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-module-name='line_B003' data-module-parents='line_B003' data-content-type1='line' data-content-type2='line-type-B' data-background-color='' data-background-image='' data-background-image-options='' data-background-attachment='' data-background-filter='' data-line-color='#bbbbbb'><div class='module_wrap fullsize_max' data-layout-fullsize='yes'><div class='module_container' data-padding-top='40' data-padding-bottom='40'><\/div><\/div><\/div>",
				"module_contents" : "<div class='line'><svg viewBox='0 0 323 21' xmlns='http:\/\/www.w3.org\/2000\/svg'><path d='M151.53.18,172,20.45' \/><path d='M172,.9,151.53,21.18' \/><path d='M323,11.18H183'\/><path d='M140,11.18H0' \/><\/svg><\/div>"
			}
		},
		"module-15" : {
			"seq" : 1874,
			"save_seq" : 295,
			"block_id" : "module-15",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "text_A001",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:22",
			"childs" : [],
			"module" : {
				"module_name" : "text_A001",
				"content_type1" : "text",
				"content_type2" : "text-type-A",
				"module_possible" : "all",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/text\/text_A001.jpg",
				"module_hashtag" : "",
				"module_parents" : "text_A001",
				"module_category1" : "text",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-content-type1='text' data-content-type2='text-type-A' data-module-name='text_A001' data-module-parents='text_A001' data-background-color='' data-background-image='' data-background-image-options='' data-background-attachment='' data-background-filter=''><div class='module_wrap' data-layout-fullsize='no'><div class='module_container' data-padding-top='40' data-padding-bottom='40'><\/div><\/div><\/div>",
				"module_contents" : "<div data-text-editable='true' style='text-align: center; font-size: 17px; font-weight: 300;'><p>우리 브랜드에 대하여<\/p><p style='font-size:10px;'>&nbsp;<\/p><\/div><div data-text-editable='true' style='text-align: center; font-size: 46px; font-family: Montserrat; font-weight: 100;'><p style='line-height: 1.2;'>About our Brand<\/p><\/div><div data-text-editable='true' style='text-align: center; font-size: 15px; font-weight: 300; color: rgb(153, 153, 153);'><p>&nbsp;<\/p><p>언덕 것은 비둘기, 아름다운 까닭이요, 있습니다. 벌레는 어머님, 별빛이 북간도에 않은 밤이 애기 그랬었습니다.<\/p><p>없이 나는 소녀들의 계십니다. 보고, 것은 이 마디씩 흙으로 무성할 동경과 멀리 계십니다. 무엇인지 마디씩 없이 묻힌 소녀들의 봅니다.<\/p><p>내 이웃 내 시인의 하나 불러 헤는 같이 이름을 까닭입니다.<\/p><\/div>"
			}
		},
		"module-17" : {
			"seq" : 1875,
			"save_seq" : 295,
			"block_id" : "module-17",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "contents_L027",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:22",
			"childs" : [],
			"module" : {
				"module_name" : "contents_L027",
				"content_type1" : "contents",
				"content_type2" : "contents-type-L",
				"module_possible" : "normal",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/contents\/contents_L027.jpg",
				"module_parents" : "layout_N8_02",
				"module_category1" : "contents",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-module-name='contents_L027' data-module-parents='layout_N8_02' data-layout-type='T2' data-content-type1='contents' data-content-type2='contents-type-L' data-background-color='' data-background-image='' data-background-image-options='' data-background-attachment='' data-background-filter=''><div class='module_wrap' data-layout-fullsize='no'><div class='module_container' data-padding-top='60' data-padding-bottom='60'><\/div><\/div><\/div>",

			}
		},
		"module-16" : {
			"seq" : 1876,
			"save_seq" : 295,
			"block_id" : "module-16",
			"parent_block" : null,
			"child_num" : null,
			"block_template" : "map_A010",
			"sort_num" : 0,
			"regist_date" : "2022-07-25 10:07:23",
			"childs" : [],
			"module" : {
				"module_name" : "map_A010",
				"content_type1" : "map",
				"content_type2" : "map-type-A",
				"module_possible" : "normal",
				"module_preview_src" : "https:\/\/storage.clickn.co.kr\/images\/module_list\/map\/map_A010.jpg",
				"module_parents" : "map_A001",
				"module_category1" : "map",
				"module_category2" : "",
				"module_distribute" : "yes",
				"module_layout" : "<div class='normal_module' data-module-name='map_A010' data-module-parents='layout_N3_01' data-layout-type='T2' data-content-type1='map' data-content-type2='map-type-A' data-option-buttons='yes' data-button-align='center' data-background-color='' data-background-image='' data-background-image-options='' data-background-attachment='' data-background-filter='' data-map-address-seq='0' data-map-screen-ratio='no'><div class='module_wrap' data-layout-fullsize='no'><div class='module_container' data-padding-top='100' data-padding-bottom='80'><\/div><\/div><\/div>",
				"module_contents" : "<ul class='mapContents'><li><ul><li><div data-is-vacant='no' class='line C'><a href='#none' data-link-type='' target=''><b><img src='https:\/\/storage.clickn.co.kr\/images\/module_contents\/map_A010_a.png' \/><\/b><\/a><div data-text-editable='true' style='font-size: 20px; font-weight: 300; color: #ffffff;'><p style='line-height: 1.3;'>&nbsp;<\/p><p style='line-height: 1.3;'>경기도 성남시 분당구 대왕판교로 670<\/p><p style='line-height: 1.3;'>&nbsp;<\/p><\/div><div data-text-editable='true' style='font-size: 14px; font-weight: 200; color: #ffffff;'><p style='line-height: 1.4;'>신분당선 판교역 4번출구 도보 10분<\/p><p style='line-height: 1.4;'>버스 330, 350, 380<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant='no' class='line C'><a href='#none' data-link-type='' target=''><b><img src='https:\/\/storage.clickn.co.kr\/images\/module_contents\/map_A010_b.png' \/><\/b><\/a><div data-text-editable='true' style='font-size: 34px; font-weight: 500; color: #ffffff;'><p style='line-height: 1;'>&nbsp;<\/p><p style='line-height: 1;'><a href='tel:02-1544-1718'>02-1544-1718<\/a><\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant='no' class='line C'><a href='#none' data-link-type='' target=''><b><img src='https:\/\/storage.clickn.co.kr\/images\/module_contents\/map_A010_c.png' \/><\/b><\/a><div data-text-editable='true' style='font-weight: Roboto; font-size: 20px; font-weight: 300; color: #ffffff;'><p style='line-height: 1.4;'>&nbsp;<\/p><p style='line-height: 1.4;'>평일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;AM 09:00 - PM 06:00<\/p><p style='line-height: 1.4;'>토\/일요일&nbsp;&nbsp;&nbsp;&nbsp;AM 09:00 - PM 04:00<\/p><p style='line-height: 1.4;'>점심시간&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PM 01:00 - PM 02:00<\/p><\/div><\/div><\/li><\/ul><\/li><\/ul><div class='mapContents button_area'><a href='#none' data-border-color='#000000' data-bg-color='#000000' data-text-color='#ffffff' data-button-style='style1' style='padding: 15px 75px; font-size: 20px; font-weight: 300; cursor:pointer;' onclick='map_A010(this)'><em>지도보기<\/em><\/a><\/div><div class='resp_googlemap_wrap'><div class='resp_googlemap_inner'><iframe src='https:\/\/www.google.com\/maps\/embed?pb=!1m14!1m8!1m3!1d6338.983049937281!2d127.10276897351075!3d37.401855550675684!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x28eb51d2e770552c!2sGabia!5e0!3m2!1sko!2skr!4v1616552882500!5m2!1sko!2skr&region=0' style='border: 0;' allowfullscreen='' loading='lazy'><\/iframe><\/div><\/div>"
			}
		}
	};
	var BLOCK_CONTENTS = [
			{
				"block_id" : "module-1",
				"parent_block" : null,
				"html" : "<div class=\"slide_banner\"><a class=\"sslide\" href=\"\" target=\"\" style=\"\"><img class=\"simg\" src=\"\/file\/311019131\" alt=\"\"><div class=\"slide_contents\" style=\"background-image: url(&quot;..\/images\/bg_.png&quot;);\" ..=\"\" images=\"\" bg_.png\");\"=\"\"><div class=\"text_wrap\"><div class=\"text_contents title\"><div data-text-editable=\"true\" style=\"font-size: 68px; color: #fff; font-weight: 200; line-height: 1.1;\" class=\"\"><p>여러분의 든든한 가족 주치의<\/p><\/div><div data-text-editable=\"true\" style=\"font-family: Roboto; font-size: 24px; color: #fff; font-weight: 200; line-height: 1.2;\" class=\"\"><p>&nbsp;<\/p><p>FOR YOUR HEALTHY FAMILY DOCTOR'S<\/p><\/div><\/div><\/div><\/div><\/a><a class=\"sslide\" href=\"\" target=\"\" style=\"\"><img class=\"simg\" src=\"\/file\/1933463516\" alt=\"\"><div class=\"slide_contents\" style=\"background-image: url(&quot;..\/images\/bg_.png&quot;);\" ..=\"\" images=\"\" bg_.png\");\"=\"\"><div class=\"text_wrap\"><div class=\"text_contents title\"><div data-text-editable=\"true\" style=\"font-size: 68px; color: #fff; font-weight: 200; line-height: 1.1;\" class=\"\"><p>여러분의 든든한 가족 주치의<\/p><\/div><div data-text-editable=\"true\" style=\"font-family: Roboto; font-size: 24px; color: #fff; font-weight: 200; line-height: 1.2;\" class=\"\"><p>&nbsp;<\/p><p>FOR YOUR HEALTHY FAMILY DOCTOR'S<\/p><\/div><\/div><\/div><\/div><\/a><\/div>",
				"options" : "{\"layout-fullsize\":\"yes\",\"padding-top\":\"0\",\"padding-bottom\":\"0\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null,\"slide-arrows\":\"yes\",\"slide-dots\":\"yes\",\"slide-autoplay\":\"yes\",\"slides-to-show\":\"1\",\"slide-autoplay-speed\":\"6000\",\"slide-motion\":\"fade\",\"slide-motion-function\":\"ease\",\"slide-motion-speed\":\"1200\",\"slide-cutting\":\"40\",\"slide-image-width\":\"1920\",\"slide-image-height\":\"700\",\"slide-contents\":[{\"slide-image-src\":\"\\\/file\\\/311019131\",\"slide-link-url\":null,\"slide-link-target\":null,\"slide-text-contents\":\"<div class=\\\"slide_contents\\\" style=\\\"background-image: url(&quot;..\\\/images\\\/bg_.png&quot;);\\\" ..=\\\"\\\" images=\\\"\\\" bg_.png\\\");\\\"=\\\"\\\"><div class=\\\"text_wrap\\\"><div class=\\\"text_contents title\\\"><div data-text-editable=\\\"true\\\" style=\\\"font-size: 68px; color: #fff; font-weight: 200; line-height: 1.1;\\\" class=\\\"\\\"><p>여러분의 든든한 가족 주치의<\\\/p><\\\/div><div data-text-editable=\\\"true\\\" style=\\\"font-family: Roboto; font-size: 24px; color: #fff; font-weight: 200; line-height: 1.2;\\\" class=\\\"\\\"><p>&nbsp;<\\\/p><p>FOR YOUR HEALTHY FAMILY DOCTOR'S<\\\/p><\\\/div><\\\/div><\\\/div><\\\/div>\"},{\"slide-image-src\":\"\\\/file\\\/1933463516\",\"slide-link-url\":null,\"slide-link-target\":null,\"slide-text-contents\":\"<div class=\\\"slide_contents\\\" style=\\\"background-image: url(&quot;..\\\/images\\\/bg_.png&quot;);\\\" ..=\\\"\\\" images=\\\"\\\" bg_.png\\\");\\\"=\\\"\\\"><div class=\\\"text_wrap\\\"><div class=\\\"text_contents title\\\"><div data-text-editable=\\\"true\\\" style=\\\"font-size: 68px; color: #fff; font-weight: 200; line-height: 1.1;\\\" class=\\\"\\\"><p>여러분의 든든한 가족 주치의<\\\/p><\\\/div><div data-text-editable=\\\"true\\\" style=\\\"font-family: Roboto; font-size: 24px; color: #fff; font-weight: 200; line-height: 1.2;\\\" class=\\\"\\\"><p>&nbsp;<\\\/p><p>FOR YOUR HEALTHY FAMILY DOCTOR'S<\\\/p><\\\/div><\\\/div><\\\/div><\\\/div>\"}],\"slide-filter\":null}"
			},
			{
				"block_id" : "module-2",
				"parent_block" : null,
				"html" : "<div class=\"line\"><svg viewBox=\"0 0 323 21\" xmlns=\"http:\/\/www.w3.org\/2000\/svg\" style=\"stroke: rgb(187, 187, 187);\"><path d=\"M151.53.18,172,20.45\"><\/path><path d=\"M172,.9,151.53,21.18\"><\/path><path d=\"M323,11.18H183\"><\/path><path d=\"M140,11.18H0\"><\/path><\/svg><\/div>",
				"options" : "{\"layout-fullsize\":\"yes\",\"padding-top\":\"100\",\"padding-bottom\":\"60\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null,\"line-color\":\"#bbbbbb\"}"
			},

	/* 		{
				"block_id" : "module-3",
				"parent_block" : null,
				"html" : "<div data-text-editable=\"true\" style=\"font-family: Quicksand; font-size: 20px; font-weight: 400; text-align: center; color: rgb(0, 0, 0); line-height: 1.2;\" class=\"\"><p>About us<\/p><p><br><\/p><\/div><div data-text-editable=\"true\" style=\"font-size: 46px; font-family: Montserrat; font-weight: 100; text-align: center; color:#000; line-height: 1.5;\" class=\"\"><p>푸드파킹에 입점한 가게<\/p><\/div><div data-text-editable=\"true\" style=\"font-size: 16px; color: rgb(110, 110, 110); text-align: center; line-height: 1.6; font-weight: 300;\" class=\"\"><p>&nbsp;<\/p><p><br><\/p><p>푸드파킹과 함께하는 입점가게를 보시죠<\/p><p>푸드파킹은 점주님들과 원활한 의사소통을 추구합니다.<\/p><p>&nbsp;<\/p><\/div><div data-text-editable=\"true\" style=\"font-size: 13px; letter-spacing: 0.1em; color: #000000; text-align: center;\" class=\"\"><p><u><a href=\"#none\" target=\"\">view more<\/a><\/u><\/p><\/div>",
				"options" : "{\"layout-fullsize\":\"no\",\"padding-top\":\"40\",\"padding-bottom\":\"40\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null}"
			}, */

			{
				"block_id" : "module-4",
				"parent_block" : null,
				"html" : "<div class=\"line\"><svg viewBox=\"0 0 323 21\" xmlns=\"http:\/\/www.w3.org\/2000\/svg\" style=\"stroke: rgb(187, 187, 187);\"><path d=\"M151.53.18,172,20.45\"><\/path><path d=\"M172,.9,151.53,21.18\"><\/path><path d=\"M323,11.18H183\"><\/path><path d=\"M140,11.18H0\"><\/path><\/svg><\/div>",
				"options" : "{\"layout-fullsize\":\"yes\",\"padding-top\":\"100\",\"padding-bottom\":\"20\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null,\"line-color\":\"#bbbbbb\"}"
			},
			{
				"block_id" : "module-5",
				"parent_block" : null,

				"html" : "<div data-text-editable=\"true\" style=\"font-size: 32px; font-weight: 300; text-align:center;\" class=\"\"><p>행사중인 입점가게<\/p><\/div>",

				"options" : "{\"layout-fullsize\":\"no\",\"padding-top\":\"20\",\"padding-bottom\":\"20\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null}"
			},
			{
				"block_id" : "module-6",
				"parent_block" : null,

				"html" : "<ul><li><ul><li><div data-is-vacant=\"no\"><div class=\"content_img\"><div class=\"img_crop\"><a href=\"#none\" data-link-type=\"\" data-cutting-apply=\"yes\"><b><img src=\"https:\/\/storage.clickn.co.kr\/images\/module_contents\/contents_L025_a.jpg\" alt=\"\"><\/b><\/a><\/div><\/div><div class=\"content_txt\"><div class=\"top\"><div data-text-editable=\"true\" style=\"font-size: 42px; font-weight: 500; color: #ffffff;\" class=\"\"><p style=\"line-height: 1.2;\">점주님<\/p><p style=\"line-height: 1.2;\">뭐하지<\/p><\/div><\/div><div class=\"bott\"><div data-text-editable=\"true\" style=\"font-size: 14px; color: #ffffff; font-weight: 300;\" class=\"\"><p style=\"line-height: 1.3;\">각종 소화기 질환에 대해<\/p><p style=\"line-height: 1.3;\">푸드파킹과 함께<\/p><p style=\"line-height: 1.3;\">&nbsp;<\/p><\/div><div data-text-editable=\"true\" style=\"font-family: Open Sans; font-size: 12px; font-weight: 300; color: #ffffff; \" class=\"\"><p style=\"line-height: 1.2;\"><u>MORE READ<\/u><\/p><\/div><\/div><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\"><div class=\"content_img\"><div class=\"img_crop\"><a href=\"#none\" data-link-type=\"\" data-cutting-apply=\"yes\"><b><img src=\"images\/tst.png\" alt=\"\"><\/b><\/a><\/div><\/div><div class=\"content_txt\"><div class=\"top\"><div data-text-editable=\"true\" style=\"font-size: 42px; font-weight: 500; color: #F25C05;\" class=\"\"><p style=\"line-height: 1.2;\"><\/p><p style=\"line-height: 1.2;\"><\/p><\/div><\/div><div class=\"bott\"><div data-text-editable=\"true\" style=\"font-size: 14px; color: #ffffff; font-weight: 300;\" class=\"\"><p style=\"line-height: 1.3;\">일반건강 검진 및 프리미엄 검진<\/p><p style=\"line-height: 1.3;\">내시경 및 X-ray 진료가 가능합니다.<\/p><p style=\"line-height: 1.3;\">&nbsp;<\/p><\/div><div data-text-editable=\"true\" style=\"font-family: Open Sans; font-size: 12px; font-weight: 300; color: #ffffff; \" class=\"\"><p style=\"line-height: 1.2;\"><u>MORE READ<\/u><\/p><\/div><\/div><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\"><div class=\"content_img\"><div class=\"img_crop\"><a href=\"#none\" data-link-type=\"\" data-cutting-apply=\"yes\"><b><img src=\"images/event4.png\" alt=\"\"><\/b><\/a><\/div><\/div><div class=\"content_txt\"><div class=\"top\"><div data-text-editable=\"true\" style=\"font-size: 42px; font-weight: 500; color: #ffffff;\" class=\"\"><p style=\"line-height: 1.2;\"><\/p><p style=\"line-height: 1.2;\"><\/p><\/div><\/div><div class=\"bott\"><div data-text-editable=\"true\" style=\"font-size: 14px; color: #ffffff; font-weight: 300;\" class=\"\"><p style=\"line-height: 1.3;\">여기뭐할지미정<\/p><p style=\"line-height: 1.3;\"><\/p><p style=\"line-height: 1.3;\">&nbsp;<\/p><\/div><div data-text-editable=\"true\" style=\"font-family: Open Sans; font-size: 12px; font-weight: 300; color: #ffffff; \" class=\"\"><p style=\"line-height: 1.2;\"><u>MORE READ<\/u><\/p><\/div><\/div><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\"><div class=\"content_img\"><div class=\"img_crop\"><a href=\"#none\" data-link-type=\"\" data-cutting-apply=\"yes\"><b><img src=\"https:\/\/storage.clickn.co.kr\/images\/module_contents\/contents_L025_d.jpg\" alt=\"\"><\/b><\/a><\/div><\/div><div class=\"content_txt\"><div class=\"top\"><div data-text-editable=\"true\" style=\"font-size: 42px; font-weight: 500; color: #ffffff;\" class=\"\"><p style=\"line-height: 1.2;\">알러지<\/p><p style=\"line-height: 1.2;\">클리닉<\/p><\/div><\/div><div class=\"bott\"><div data-text-editable=\"true\" style=\"font-size: 14px; color: #ffffff; font-weight: 300;\" class=\"\"><p style=\"line-height: 1.3;\">아토피, 알러지, 비염, 천식 등<\/p><p style=\"line-height: 1.3;\">원인을 찾아 근본적인 치료를 진행합니다.<\/p><p style=\"line-height: 1.3;\">&nbsp;<\/p><\/div><div data-text-editable=\"true\" style=\"font-family: Open Sans; font-size: 12px; font-weight: 300; color: #ffffff; \" class=\"\"><p style=\"line-height: 1.2;\"><u>MORE READ<\/u><\/p><\/div><\/div><\/div><\/div><\/li><\/ul><\/li><\/ul>",

				"options" : "{\"layout-fullsize\":\"no\",\"padding-top\":\"40\",\"padding-bottom\":\"40\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null}"
			},
			{
				"block_id" : "module-7",
				"parent_block" : null,
				"html" : "<div class=\"line\"><svg viewBox=\"0 0 323 21\" xmlns=\"http:\/\/www.w3.org\/2000\/svg\" style=\"stroke: rgb(187, 187, 187);\"><path d=\"M151.53.18,172,20.45\"><\/path><path d=\"M172,.9,151.53,21.18\"><\/path><path d=\"M323,11.18H183\"><\/path><path d=\"M140,11.18H0\"><\/path><\/svg><\/div>",
				"options" : "{\"layout-fullsize\":\"yes\",\"padding-top\":\"120\",\"padding-bottom\":\"20\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null,\"line-color\":\"#bbbbbb\"}"
			},
			{
				"block_id" : "module-18",
				"parent_block" : null,

				"html" : "<div data-text-editable=\"true\" style=\"font-size: 32px; font-weight: 300; text-align:center;\" class=\"\"><p>왜? 시작은 푸드파킹?<\/p><\/div>",
				"options" : "{\"layout-fullsize\":\"no\",\"padding-top\":\"20\",\"padding-bottom\":\"30\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null}"
			},
			{
				"block_id" : "module-20",
				"parent_block" : null,

				"html" : "<ul class=\"contents_ul\" data-child-list-deleteable=\"yes\"><li><ul><li><div data-is-vacant=\"no\"><ul class=\"contents_wrap\"><li class=\"img_area\"><div class=\"img_crop circle\"><a href=\"#none\" data-link-type=\"\" target=\"\"><b><img src=\"images/one1.png\" alt=\"\"><\/b><\/a><\/div><\/li><li class=\"info_area\" style=\"padding:20px 0;\"><div data-text-editable=\"true\" style=\"font-family: Noto Sans KR; font-size: 20px; font-weight: 700; color: rgb(51, 51, 51); text-align: center; line-height: 1.3;\" class=\"\"><p>초기비용 0원<\/p><\/div><div data-text-editable=\"true\" style=\"font-size: 14px; color: rgb(118, 118, 118); font-family: Noto Sans KR; font-weight: 400; text-align: center;\" class=\"\"><p>&nbsp;<\/p><p>주문이 발생 했을 때만<\/p><p>주문 중개 이용료가<\/p><p>부과 됩니다<\/p><\/div><\/li><\/ul><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\"><ul class=\"contents_wrap\"><li class=\"img_area\"><div class=\"img_crop circle\"><a href=\"#none\" data-link-type=\"\" target=\"\"><b><img src=\"images/evnet3.png\" alt=\"\"><\/b><\/a><\/div><\/li><li class=\"info_area\" style=\"padding:20px 0;\"><div data-text-editable=\"true\" style=\"font-family: Noto Sans KR; font-size: 20px; font-weight: 700; color: rgb(51, 51, 51); text-align: center; line-height: 1.3;\" class=\"\"><p>원장 김수진<\/p><\/div><div data-text-editable=\"true\" style=\"font-size: 14px; color: rgb(118, 118, 118); font-family: Noto Sans KR; font-weight: 400; text-align: center;\" class=\"\"><p>&nbsp;<\/p><p>경희대학교 의학전문대학 졸업<\/p><p>경희대학교병원 인턴 수료<\/p><\/div><\/li><\/ul><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\"><ul class=\"contents_wrap\"><li class=\"img_area\"><div class=\"img_crop circle\"><a href=\"#none\" data-link-type=\"\" target=\"\"><b><img src=\"images/one1.png\" alt=\"\"><\/b><\/a><\/div><\/li><li class=\"info_area\" style=\"padding:20px 0;\"><div data-text-editable=\"true\" style=\"font-family: Noto Sans KR; font-size: 20px; font-weight: 700; color: rgb(51, 51, 51); text-align: center; line-height: 1.3;\" class=\"\"><p>원장 정재훈<\/p><\/div><div data-text-editable=\"true\" style=\"font-size: 14px; color: rgb(118, 118, 118); font-family: Noto Sans KR; font-weight: 400; text-align: center;\" class=\"\"><p>&nbsp;<\/p><p>주문이 발생했을 때만<\/p><p>서울대학교병원 인턴 수료<\/p><p>서울대학교병원 안과 전공의 수료<\/p><\/div><\/li><\/ul><\/div><\/li><\/ul><\/li><\/ul>",

				"options" : "{\"layout-fullsize\":\"no\",\"padding-top\":\"60\",\"padding-bottom\":\"50\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null}"
			},
			{
				"block_id" : "module-10",
				"parent_block" : null,
				"html" : "<div class=\"line\"><svg viewBox=\"0 0 323 21\" xmlns=\"http:\/\/www.w3.org\/2000\/svg\" style=\"stroke: rgb(187, 187, 187);\"><path d=\"M151.53.18,172,20.45\"><\/path><path d=\"M172,.9,151.53,21.18\"><\/path><path d=\"M323,11.18H183\"><\/path><path d=\"M140,11.18H0\"><\/path><\/svg><\/div>",
				"options" : "{\"layout-fullsize\":\"yes\",\"padding-top\":\"100\",\"padding-bottom\":\"20\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null,\"line-color\":\"#bbbbbb\"}"
			},
			{
				"block_id" : "module-11",
				"parent_block" : null,
				"html" : "<div data-text-editable=\"true\" style=\"font-size: 32px; font-weight: 300; text-align:center;\" class=\"\"><p>진료 스케쥴<\/p><\/div>",
				"options" : "{\"layout-fullsize\":\"no\",\"padding-top\":\"20\",\"padding-bottom\":\"40\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null}"
			},
			{
				"block_id" : "module-29",
				"parent_block" : null,
				"html" : "<div data-text-editable=\"true\" class=\"table_module_wrap\" data-table-type=\"table_module_a\" data-table-name=\"table_module_a001\" data-th-padding=\"3\" data-td-padding=\"3\" data-table-minwidth=\"800\"><table style=\"margin-right: calc(0%); width: 100%;\"><thead><tr><th style=\"width: 11.2635%;background-color: rgb(233, 233, 233);\"><br><\/th><th colspan=\"2\" style=\"width: 11.1371%;background-color: rgb(233, 233, 233);color: rgb(0, 0, 0);\">월<\/th><th colspan=\"2\" style=\"width: 11.1371%;background-color: rgb(233, 233, 233);color: rgb(0, 0, 0);\">화<\/th><th colspan=\"2\" style=\"width: 11.1371%;background-color: rgb(233, 233, 233);color: rgb(0, 0, 0);\">수<\/th><th colspan=\"2\" style=\"width: 11.1371%;background-color: rgb(233, 233, 233);color: rgb(0, 0, 0);\">목<\/th><th colspan=\"2\" style=\"width: 11.1371%;background-color: rgb(233, 233, 233);color: rgb(0, 0, 0);\">금<\/th><th colspan=\"2\" style=\"width: 11.1371%;background-color: rgb(233, 233, 233);color: rgb(0, 0, 0);\">토<\/th><th style=\"background-color: rgb(233, 233, 233); color: rgb(0, 0, 0); width: 8.5495%;\">일<br><\/th><\/tr><\/thead><tbody><tr><td style=\"width: 11.2635%; background-color: rgb(245, 245, 245);\"><br><\/td><td style=\"width: 5.5685%; background-color: rgb(245, 245, 245);\"><span style=\"color: rgb(151 151 151);font-size: 13px;\">오전<\/span><\/td><td style=\"width: 5.5685%; background-color: rgb(245, 245, 245);\"><span style=\"color: rgb(151 151 151);font-size: 13px;\">오후<\/span><\/td><td style=\"width: 5.5685%; background-color: rgb(245, 245, 245);\"><span style=\"color: rgb(151 151 151);font-size: 13px;\">오전<\/span><\/td><td style=\"width: 5.5685%; background-color: rgb(245, 245, 245);\"><span style=\"color: rgb(151 151 151);font-size: 13px;\">오후<\/span><\/td><td style=\"width: 5.5685%; background-color: rgb(245, 245, 245);\"><span style=\"color: rgb(151 151 151);font-size: 13px;\">오전<\/span><\/td><td style=\"width: 5.5685%; background-color: rgb(245, 245, 245);\"><span style=\"color: rgb(151 151 151);font-size: 13px;\">오후<\/span><\/td><td style=\"width: 5.5685%; background-color: rgb(245, 245, 245);\"><span style=\"color: rgb(151 151 151);font-size: 13px;\">오전<\/span><\/td><td style=\"width: 5.5685%; background-color: rgb(245, 245, 245);\"><span style=\"color: rgb(151 151 151);font-size: 13px;\">오후<\/span><\/td><td style=\"width: 5.5685%; background-color: rgb(245, 245, 245);\"><span style=\"color: rgb(151 151 151);font-size: 13px;\">오전<\/span><\/td><td style=\"width: 5.5685%; background-color: rgb(245, 245, 245);\"><span style=\"color: rgb(151 151 151);font-size: 13px;\">오후<\/span><\/td><td style=\"width: 5.5685%; background-color: rgb(245, 245, 245);\"><span style=\"color: rgb(151 151 151);font-size: 13px;\">오전<\/span><\/td><td style=\"width: 5.5685%; background-color: rgb(245, 245, 245);\"><span style=\"color: rgb(151 151 151);font-size: 13px;\">오후<\/span><\/td><td style=\"background-color: rgb(245, 245, 245); width: 8.5495%;\"><span style=\"color: rgb(151 151 151);font-size: 13px;\">순환근무<\/span><\/td><\/tr><tr><td style=\"width: 11.2635%;\">원장 이수영<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 8.5495%;\"><br><\/td><\/tr><tr><td style=\"width: 11.2635%;\">원장 김수진<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 8.5495%;\"><br><\/td><\/tr><tr><td style=\"width: 11.2635%;\">원장 정재훈<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 5.5685%;\">-<\/td><td style=\"width: 5.5685%;\">O<\/td><td style=\"width: 8.5495%;\"><br><\/td><\/tr><\/tbody><\/table><\/div>",
				"options" : "{\"layout-fullsize\":\"no\",\"padding-top\":\"20\",\"padding-bottom\":\"150\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null,\"th-padding\":\"3\",\"td-padding\":\"3\",\"table-minwidth\":\"800\"}"
			},
			{
				"block_id" : "module-28",
				"parent_block" : null,

				"html" : "<ul><li><ul><li><div data-is-vacant=\"no\"><div data-text-editable=\"true\" style=\"font-size: 45px; font-weight: 700; color: #ffffff; text-align: center;\" class=\"\"><p style=\"line-height: 1.2;\">푸드 파킹 안내 시간<\/p><\/div><div data-text-editable=\"true\" style=\"font-size: 18px; color: #d2d2d2; font-weight: 300; text-align: center;\" class=\"\"><p style=\"line-height:1.4;\">&nbsp;<\/p><p style=\"line-height:1.4;\">해당 시간에 고객 센터 서비스를 이용하실 수 있습니다.<\/p><p style=\"line-height:1.4;\">대기 인원이 많아지면 약간의 지연이 발생할 수 있습니다.<\/p><p style=\"line-height:1.4;\">&nbsp;<\/p><p style=\"line-height:1.4;\">&nbsp;<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\"><div data-text-editable=\"true\" style=\"font-size: 24px; font-weight: 400; color: #ffffff; text-align: center;\" class=\"\"><p style=\"line-height: 1;\">평일<\/p><p style=\"line-height: 1;\">&nbsp;<\/p><\/div><div data-text-editable=\"true\" style=\"font-family: Roboto; font-size: 34px; font-weight: 500; color: #ffffff; text-align: center;\" class=\"\"><p style=\"line-height: 1;\">09:00 - 18:00<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\" class=\"line\"><div data-text-editable=\"true\" style=\"font-size: 24px; font-weight: 400; color: #ffffff; text-align: center;\" class=\"\"><p style=\"line-height: 1;\">토\/일요일<\/p><p style=\"line-height: 1;\">&nbsp;<\/p><\/div><div data-text-editable=\"true\" style=\"font-family: Roboto; font-size: 34px; font-weight: 500; color: #ffffff; text-align: center;\" class=\"\"><p style=\"line-height: 1;\">09:00 - 16:00<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\" class=\"line\"><div data-text-editable=\"true\" style=\"font-size: 24px; font-weight: 400; color: #ffffff; text-align: center;\" class=\"\"><p style=\"line-height: 1;\">점심시간<\/p><p style=\"line-height: 1;\">&nbsp;<\/p><\/div><div data-text-editable=\"true\" style=\"font-family: Roboto; font-size: 34px; font-weight: 500; color: #ffffff; text-align: center;\" class=\"\"><p style=\"line-height: 1;\">13:00 - 14:00<\/p><\/div><\/div><\/li><\/ul><\/li><\/ul>",
				"options" : "{\"layout-fullsize\":\"no\",\"padding-top\":\"60\",\"padding-bottom\":\"60\",\"background-color\":null,\"background-image\":\"images/main.png\",\"background-image-options\":\"size_cover\",\"background-attachment\":\"fixed\",\"background-filter\":null}"

			},
			{
				"block_id" : "module-14",
				"parent_block" : null,
				"html" : "<div class=\"line\"><svg viewBox=\"0 0 323 21\" xmlns=\"http:\/\/www.w3.org\/2000\/svg\" style=\"stroke: rgb(187, 187, 187);\"><path d=\"M151.53.18,172,20.45\"><\/path><path d=\"M172,.9,151.53,21.18\"><\/path><path d=\"M323,11.18H183\"><\/path><path d=\"M140,11.18H0\"><\/path><\/svg><\/div>",
				"options" : "{\"layout-fullsize\":\"yes\",\"padding-top\":\"120\",\"padding-bottom\":\"30\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null,\"line-color\":\"#bbbbbb\"}"
			},
			{
				"block_id" : "module-15",
				"parent_block" : null,
				"html" : "<div data-text-editable=\"true\" style=\"font-size: 32px; font-weight: 300; text-align:center;\" class=\"\"><p>검사안내<\/p><\/div>",
				"options" : "{\"layout-fullsize\":\"no\",\"padding-top\":\"20\",\"padding-bottom\":\"40\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null}"
			},
			{
				"block_id" : "module-17",
				"parent_block" : null,
				"html" : "<ul><li><ul><li><div data-is-vacant=\"no\" class=\"line\"><div class=\"content_img\"><div class=\"img_crop\"><a href=\"#none\" data-link-type=\"\" target=\"\"><b><img src=\"https:\/\/storage.clickn.co.kr\/images\/module_contents\/contents_L027_a.jpg\"><\/b><\/a><\/div><\/div><div data-text-editable=\"true\" class=\"C\" style=\"font-size: 20px; font-weight: 500; color: #283c82;\"><p>내시경 검사<\/p><p>&nbsp;<\/p><p>&nbsp;<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\" class=\"line\"><div class=\"content_img\"><div class=\"img_crop\"><a href=\"#none\" data-link-type=\"\" target=\"\"><b><img src=\"https:\/\/storage.clickn.co.kr\/images\/module_contents\/contents_L027_b.jpg\"><\/b><\/a><\/div><\/div><div data-text-editable=\"true\" class=\"C\" style=\"font-size: 20px; font-weight: 500; color: #283c82;\"><p>성장클리닉<\/p><p>&nbsp;<\/p><p>&nbsp;<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\" class=\"line\"><div class=\"content_img\"><div class=\"img_crop\"><a href=\"#none\" data-link-type=\"\" target=\"\"><b><img src=\"https:\/\/storage.clickn.co.kr\/images\/module_contents\/contents_L027_c.jpg\"><\/b><\/a><\/div><\/div><div data-text-editable=\"true\" class=\"C\" style=\"font-size: 20px; font-weight: 500; color: #283c82;\"><p>혈액 검사<\/p><p>&nbsp;<\/p><p>&nbsp;<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\" class=\"line\"><div class=\"content_img\"><div class=\"img_crop\"><a href=\"#none\" data-link-type=\"\" target=\"\"><b><img src=\"https:\/\/storage.clickn.co.kr\/images\/module_contents\/contents_L027_d.jpg\"><\/b><\/a><\/div><\/div><div data-text-editable=\"true\" class=\"C\" style=\"font-size: 20px; font-weight: 500; color: #283c82;\"><p>정밀 검사<\/p><p>&nbsp;<\/p><p>&nbsp;<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\" class=\"line\"><div class=\"content_img\"><div class=\"img_crop\"><a href=\"#none\" data-link-type=\"\" target=\"\"><b><img src=\"https:\/\/storage.clickn.co.kr\/images\/module_contents\/contents_L027_e.jpg\"><\/b><\/a><\/div><\/div><div data-text-editable=\"true\" class=\"C\" style=\"font-size: 20px; font-weight: 500; color: #283c82;\"><p>각종 알러지 검사<\/p><p>&nbsp;<\/p><p>&nbsp;<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\" class=\"line\"><div class=\"content_img\"><div class=\"img_crop\"><a href=\"#none\" data-link-type=\"\" target=\"\"><b><img src=\"https:\/\/storage.clickn.co.kr\/images\/module_contents\/contents_L027_f.jpg\"><\/b><\/a><\/div><\/div><div data-text-editable=\"true\" class=\"C\" style=\"font-size: 20px; font-weight: 500; color: #283c82;\"><p>수면 검사<\/p><p>&nbsp;<\/p><p>&nbsp;<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\" class=\"line\"><div class=\"content_img\"><div class=\"img_crop\"><a href=\"#none\" data-link-type=\"\" target=\"\"><b><img src=\"https:\/\/storage.clickn.co.kr\/images\/module_contents\/contents_L027_g.jpg\"><\/b><\/a><\/div><\/div><div data-text-editable=\"true\" class=\"C\" style=\"font-size: 20px; font-weight: 500; color: #283c82;\"><p>MRI 검사<\/p><p>&nbsp;<\/p><p>&nbsp;<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\" class=\"line\"><div class=\"content_img\"><div class=\"img_crop\"><a href=\"#none\" data-link-type=\"\" target=\"\"><b><img src=\"https:\/\/storage.clickn.co.kr\/images\/module_contents\/contents_L027_h.jpg\"><\/b><\/a><\/div><\/div><div data-text-editable=\"true\" class=\"C\" style=\"font-size: 20px; font-weight: 500; color: #283c82;\"><p>금연 클리닉<\/p><p>&nbsp;<\/p><p>&nbsp;<\/p><\/div><\/div><\/li><\/ul><\/li><\/ul>",
				"options" : "{\"layout-fullsize\":\"no\",\"padding-top\":\"30\",\"padding-bottom\":\"150\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null}"
			},
			{
				"block_id" : "module-16",
				"parent_block" : null,
				"html" : "<ul class=\"mapContents\"><li><ul><li><div data-is-vacant=\"no\" class=\"line C\"><a href=\"#none\" data-link-type=\"\" target=\"\"><b><img src=\"https:\/\/storage.clickn.co.kr\/images\/module_contents\/map_A010_a.png\"><\/b><\/a><div data-text-editable=\"true\" style=\"font-size: 20px; font-weight: 300; color: #ffffff;\" class=\"\"><p style=\"line-height: 1.3;\">&nbsp;<\/p><p style=\"line-height: 1.3;\">경기도 성남시 분당구 대왕판교로 670<\/p><p style=\"line-height: 1.3;\">&nbsp;<\/p><\/div><div data-text-editable=\"true\" style=\"font-size: 14px; font-weight: 200; color: #ffffff;\" class=\"\"><p style=\"line-height: 1.4;\">신분당선 판교역 4번출구 도보 10분<\/p><p style=\"line-height: 1.4;\">버스 330, 350, 380<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\" class=\"line C\"><a href=\"#none\" data-link-type=\"\" target=\"\"><b><img src=\"https:\/\/storage.clickn.co.kr\/images\/module_contents\/map_A010_b.png\"><\/b><\/a><div data-text-editable=\"true\" style=\"font-size: 34px; font-weight: 500; color: #ffffff;\" class=\"\"><p style=\"line-height: 1;\">&nbsp;<\/p><p style=\"line-height: 1;\"><a href=\"tel:0200000000\">02.1544.<\/a>1718<\/p><\/div><\/div><\/li><\/ul><\/li><li><ul><li><div data-is-vacant=\"no\" class=\"line C\"><a href=\"#none\" data-link-type=\"\" target=\"\"><b><img src=\"https:\/\/storage.clickn.co.kr\/images\/module_contents\/map_A010_c.png\"><\/b><\/a><div data-text-editable=\"true\" style=\"font-weight: Roboto; font-size: 20px; font-weight: 300; color: #ffffff;\" class=\"\"><p style=\"line-height: 1.4;\">&nbsp;<\/p><p style=\"line-height: 1.4;\">평일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;AM 09:00 - PM 06:00<\/p><p style=\"line-height: 1.4;\">토\/일요일&nbsp;&nbsp;&nbsp;&nbsp;AM 09:00 - PM 04:00<\/p><p style=\"line-height: 1.4;\">점심시간&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PM 01:00 - PM 02:00<\/p><\/div><\/div><\/li><\/ul><\/li><\/ul><div class=\"mapContents button_area\"><a href=\"#none\" data-border-color=\"#000000\" data-bg-color=\"#000000\" data-text-color=\"#ffffff\" data-button-style=\"style1\" style=\"padding: 15px 75px; font-size: 20px; font-weight: 300; cursor: pointer; border-color: rgb(0, 0, 0); background: rgb(0, 0, 0); color: rgb(255, 255, 255);\" onclick=\"map_A010(this)\"><em style=\"color: rgb(255, 255, 255);\">지도보기<\/em><\/a><\/div><div class=\"resp_googlemap_wrap\"><div class=\"resp_googlemap_inner\"><iframe src=\"https:\/\/www.google.com\/maps\/embed?pb=!1m14!1m8!1m3!1d6338.983049937281!2d127.10276897351075!3d37.401855550675684!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x28eb51d2e770552c!2sGabia!5e0!3m2!1sko!2skr!4v1616552882500!5m2!1sko!2skr&amp;region=0\" style=\"border: 0;\" allowfullscreen=\"\" loading=\"lazy\"><\/iframe><\/div><\/div>",
				"options" : "{\"layout-fullsize\":\"no\",\"padding-top\":\"100\",\"padding-bottom\":\"80\",\"background-color\":null,\"background-image\":null,\"background-image-options\":null,\"background-attachment\":null,\"background-filter\":null,\"map-screen-ratio\":\"no\",\"map-source\":\"<iframe src=\\\"https:\\\/\\\/www.google.com\\\/maps\\\/embed?pb=!1m14!1m8!1m3!1d6338.983049937281!2d127.10276897351075!3d37.401855550675684!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x28eb51d2e770552c!2sGabia!5e0!3m2!1sko!2skr!4v1616552882500!5m2!1sko!2skr&amp;region=0\\\" style=\\\"border: 0;\\\" allowfullscreen=\\\"\\\" loading=\\\"lazy\\\"><\\\/iframe>\",\"button-align\":\"center\",\"text-button-c\":[{\"button-name\":\"지도보기\",\"button-link\":\"#none\",\"button-target\":null,\"button-border-color\":\"#000000\",\"button-bg-color\":\"#000000\",\"button-text-color\":\"#ffffff\",\"button-style\":\"style1\"}]}"
			} ];
	var BASIC_CONFIG = {
		"site_name" : "The Medical Clinic",
		"co_name" : "(주)회사명",
		"ceo_name" : "홍길동",
		"co_phone" : "02-1544-1718",
		"co_email" : "clickn@gabiacns.com",
		"co_zipcode" : "13494",
		"co_address" : "경기 성남시 분당구 대왕판교로 670 (유스페이스2)",
		"co_address_detail" : "000호",
		"co_regist_num" : "000-00-00000",
		"co_it_num" : "0000-성남분당-0000",
		"site_desc" : null,
		"co_nation" : "kr",
		"co_fax" : null,
		"timezone" : "Asia\/Seoul",
		"date_format" : "Y.m.d",
		"currency" : "krw",
		"sns_img" : null,
		"fav_img" : "\/file\/519550687",
		"fav_img_tmp" : "\/file\/1555885741",
		"map_type" : "direct"
	};

	var MAP_CONFIG = {
		"map_type" : "direct",
		"map_service_type" : "kakao",
		"map_api" : {
			"naver" : {
				"client_id" : null,
				"client_secret" : null
			},
			"kakao" : {
				"api_key" : null
			},
			"google" : {
				"api_key" : null
			}
		},
		"map_feature" : {
			"map_marker_icon" : null,
			"map_zoom" : null,
			"map_zoom_control" : null
		},
		"map_script" : {
			"KAKAO" : "https:\/\/dapi.kakao.com\/v2\/maps\/sdk.js?libraries=services,clusterer,drawing&appkey=",
			"NAVER" : "https:\/\/openapi.map.naver.com\/openapi\/v3\/maps.js?submodules=geocoder&ncpClientId=",
			"GOOGLE" : "https:\/\/maps.googleapis.com\/maps\/api\/js?key="
		}
	};

	var PAGE_CONFIG = '';

	var _PATH = "\/";

	var MODULES_STORAGE_BASE_URI = "https:\/\/storage.clickn.co.kr\/";
</script>
<script src="https://designskin13.clickn.co.kr/lang/skin_js.js"></script>
<script
	src="https://designskin13.clickn.co.kr/lang/common.js?m=front&amp;g=common"></script>
<script
	src="https://designskin13.clickn.co.kr/vendors/Jquery/jquery.min.js"></script>
<script
	src="https://designskin13.clickn.co.kr/vendors/Twbs-pagination/jquery.twbsPagination.js"></script>
<script src="https://designskin13.clickn.co.kr/js/common.js?v=20221005"></script>
<script
	src="https://designskin13.clickn.co.kr/js/skin_ui_functions.js?v=20221005"></script>
<!-- 우선 순위 높아야하는 UI Functions -->


<script>
	var USER = ""
</script>



</head>
<body>
	<div id="skinLayoutWrap" class="skin_layout_wrap"
		data-layout-type="type1" data-header-hidden="no"
		data-footer-hidden="no" data-header-flying="yes"
		data-header-floating="no" data-header-util="no"
		data-header-selectmenu="yes" data-header-login="yes"
		data-header-bgcolor="#fff" data-header-brightness="light"
		data-header-utilcolor="" data-navigation-color=""
		data-navigation-font="Noto Sans KR" data-navigation-fontsize="16"
		data-gnb-seq="3" data-wow-use="no" data-wow-ani="fadeInRight"
		data-shopping-my="no" data-shopping-cart="no"
		data-shopping-search="no" data-shopping-menuorder="my-cart-search"
		data-mypage-menu="5">
		<div id="pageConfigHeader"
			class="skin_layout_container skin_layout_container1">
			<!-- ===================== Skin Header ===================== -->
			<div id="skinHeaderSection" class="front_header_section">

				<div class='header_module' data-module-name="header_A010"
					data-module-parents="header_A001" data-submenu-design="design4"
					data-submenu-direction="right">
					<!-- 헤더 모듈 -->
					<div class='module_wrap'>
						<div class='module_container'>
							<div class="logo_area">
								<p>
									<a href="<c:url value='/'/>"><b><img id="headerLogo"
											src="file/maindgs.png" alt=""
											onerror="this.src='images/module_contents/maindgs.png';" /></b></a>
								</p>
							</div>
							<div id="gnbUtilArea" class="gnb_area">
								<!-- 쇼핑몰 UTIL 영역 -->
								<div class="util_area">
									<div class='module_wrap'>
										<div class='module_container'>
											<!-- 상단 셀렉트 메뉴( 다국어 사이트나 패밀리 사이트로 활용 가능 ) -->
											<!--
										<div class="select_link_area">
											<div class="selectLinkMenu select_link_menu">
												<a class="defaule_text" href="#none"></a>
												<ul class="link_menu_list">
												</ul>
											</div>
										</div>
										-->
											<!-- 로그인 -->
											<ul class="login_section">
												<li class="greeting" style="font-family: Noto Sans KR">
													<span class="before" style=""></span> <span class="after"
													style="display: none;"></span>
												</li>
												<!-- 로그인 정보가 없다면 로그인, 회원가입이 보이는거고 -->
												<c:if test="${empty loginInfo}">

													<li class="login" style="">
														<a href="login.mb" style="font-family: Noto Sans KR">LOGIN</a>
													</li>
													<li class="join" style="">
														<a href="join.mb" style="font-family: Noto Sans KR">JOIN</a>
													</li>
												</c:if>
												<!-- 로그인한 경우에는 로그아웃이 보이게 -->
												<c:if test="${not empty loginInfo}">
													<li class="myInfo" style="">
														<a href="myInfo" style="font-family: Noto Sans KR">${loginInfo.name}</a>
													</li>
													<li class="logout">
														<a href="logout" style="font-family: Noto Sans KR">LOGOUT</a>
													</li>
												</c:if>
											</ul>

										</div>
									</div>
								</div>

								<!-- GNB -->
								<c:if test="${not empty loginInfo}">
									<ul id="GnbMenu" class="gnb" style="font-family: Noto Sans KR">
										<li><a href='/pages/about' style='font-size: 16px'>소개</a>
											<button type="button" class='btn_depth2_view'></button>
											<div class='navi_depth2_wrap'>
												<ul class='navi_depth2'>
													<li><a href='/pages/about'>인사말</a></li>
													<li><a href='/pages/history'>푸드파킹 둘러보기</a></li>
												</ul>
											</div></li>
										<li><a href='enter.en' style="font-size: 16px;">입정신청양식</a>
										</li>
										<li><a href='/pages/news' style="font-size: 16px;">푸드
												소식</a></li>
										<c:if test="${loginInfo.manager.equals('Y') }">
											<li><a href='mystore.st' style="font-size: 16px;">가게 정보</a></li>
										</c:if>
											<c:if test="${loginInfo.manager.equals('M') }">
											<li><a href='admin_store' style='font-size: 16px'>관리자 업무</a>
											<button type="button" class='btn_depth2_view'></button>
											<div class='navi_depth2_wrap'>
												<ul class='navi_depth2'>
													<li><a href='admin_store'>입점희망</a></li>
													<li><a href='admin_review'>삭제요청 리뷰 확인하기</a></li>
												</ul>
											</div></li>
										</c:if>

									</ul>
								</c:if>

								<c:if test="${empty loginInfo}">

									<ul id="GnbMenu" class="gnb" style="font-family: Noto Sans KR">
										<li><a href='/pages/about' style='font-size: 16px'>소개</a>
											<button type="button" class='btn_depth2_view'></button>
											<div class='navi_depth2_wrap'>
												<ul class='navi_depth2'>
													<li><a href='/pages/about'>인사말</a></li>
													<li><a href='/pages/history'>푸드파킹 둘러보기</a></li>
												</ul>
											</div></li>
										<li><a href='/pages/service' style="font-size: 16px;">입정신청양식</a>
										</li>
										<li><a href='pages/so' style="font-size: 16px;">푸드파킹
												소개</a></li>
										<li><a href='/pages/news' style="font-size: 16px;">푸드
												소식</a></li>
										<li><a href='myStore'
											style="font-size: 16px; display: none;">가게 정보</a></li>

									</ul>


								</c:if>
							</div>

							<!-- Aside open button( 햄거버 ) -->
							<div class="hambuger_area">
								<a id="asideOpenrBtn" href="#none"><strong>Aside
										open</strong></a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<script>
				//headerFlyingStand(); // 요 스크립트 위치 변경 금지!
			</script>
			<!-- ===================== // Skin Header ===================== -->
		</div>