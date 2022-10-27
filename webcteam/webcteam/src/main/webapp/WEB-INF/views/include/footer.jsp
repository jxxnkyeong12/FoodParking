<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!-- 상/하단 바로가기 버튼 -->
<div id="btnBott" class="btn_bott_group">
	<a href="#skinLayoutWrap" class="btn_top"><span class="screenout">TOP</span></a>
	<a href="#skinFooterSection" class="btn_bott"><span class="screenout">BOTTOM</span></a>
</div>

<!-- 어사이드 닫기 버튼 -->
<a href="#none" id="asideCloseBtn" class="aside_close">Aside close</a>

<!-- 비밀글 보기 Layer -->
<div id="secretContentsPopup" class="resp_layer_pop small" style="display:none;">
	<h4 class="title"><b>비밀번호 확인</b></h4>
	<form onsubmit="return false"><!-- form 위치 -->
		<div class="y_scroll_auto">
			<div class="layer_pop_contents" style="text-align:center;">
				<p>
					비밀번호를 입력해야 게시글을 확인할 수 있습니다.<br />
					게시글 등록 시 입력했던 비밀번호를 입력해주세요.<br />
				</p>
				<p class="Mt30">
					<input type="password" name="secret_pw_input" placeholder="비밀번호 입력" class="input_style1" style="width:200px;" />
				</p>
			</div>
		</div>
		<div class="layer_bottom_btn_area">
			<ul class="basic_btn_area">
				<li><button type="button" class="btn_resp layer_btm_style style1" onclick="hideCenterLayer();">취소</button></li>
				<li><button type="button" id="btnSecretPwConfirm" class="btn_resp layer_btm_style style2">확인</button></li>
			</ul>
		</div>
	</form>
	<a href="javascript:void(0)" class="btn_pop_close" onclick="hideCenterLayer();"></a>
</div>

<!-- 모달 -->
<div id="skinModal" class="modal_bg"></div>

<!-- 캡차 -- skin_render_forms.js -- 489라인 .load('url') 방식으로 변경 - 210514 -->


<!-- 팝업모듈 -->
<div id="popup_module_modal" class="modal_bg"></div>
<div id="popupArea">
    <div id="popupArea_any"></div>
</div>

<!-- 갤러리 보기 Layer -->
<div id="galleryViewPopup" class="gallery_view_popup" data-gallery-id="" data-gallery-seq="" style="display:none;">
	<h4 class="title">
		<b><span class="gallery_name">테스트</span></b>
	</h4>
	<div class="gallery_view_wrap">
		<div class="gallery_view_container">
			<div class="gallery_image_area">
			</div>
		</div>
		<a href="#none" class="arrow_btn" data-direction="prev" title="Prev"></a>
		<a href="#none" class="arrow_btn" data-direction="next" title="Next"></a>
	</div>
	<div class="seq_area"><p class="num" style="font-size:22px;"><span class="current" style="font-size:24px;"></span> / <span class="last"></span></p></div>
	<a href="#none" class="btn_pop_close"></a>
</div>

<div class="resp_layer_pop small agree-pop-layer" id="daumPostWrapElement" data-key="daum_post" style="display:none;">
	<h4 class="title"><b>주소검색</b></h4>
	<form onsubmit="return false"><!-- form 위치 -->
		<div class="y_scroll_auto">
			<div class="layer_pop_contents" id="daum_post_element">
				<p>
					<pre class="match_typing detailContents"></pre>
				</p>
			</div>
		</div>
		<div class="layer_bottom_btn_area">
			<ul class="basic_btn_area">
				<li><button type="button" class="btn_resp layer_btm_style style2"  onclick="hideCenterLayer();">닫기</button></li>
			</ul>
		</div>
	</form>
	<a href="javascript:void(0)" class="btn_pop_close" onclick="hideCenterLayer();"></a>
</div>

<!-- ===================== 하단 스크립트 ===================== -->
<script src="https://designskin13.clickn.co.kr/vendors/Slick/slick.min.js"></script>
<script src="https://designskin13.clickn.co.kr/vendors/Swiper/swiper.min.js"></script><!-- Swiper js-->
<script src="https://designskin13.clickn.co.kr/vendors/Froala/js/froala_editor.pkgd.min.js"></script>
<script src="https://designskin13.clickn.co.kr/vendors/Froala/js/languages/ko.js"></script>
<script src="https://designskin13.clickn.co.kr/vendors/Fontawesome/font_awesome.min.js"></script>
<script src="https://designskin13.clickn.co.kr/vendors/Wow/wow.min.js"></script><!-- Wow.js -->
<script src="https://designskin13.clickn.co.kr/vendors/Datepicker/js/datepicker.min.js"></script><!-- datapicker -->
<script src="https://designskin13.clickn.co.kr/vendors/Datepicker/lang/datepicker.lang.js"></script><!-- datapicker language -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script><!-- 카카오 우편번호 API -->

<!-- 지도 API -->

<script src="https://designskin13.clickn.co.kr/js/skin_render_map.js?<%=new java.util.Date()%>"></script>

<!-- 주소 변환 -->
<script src="https://designskin13.clickn.co.kr/js/postcode.js?<%=new java.util.Date()%>"></script>
<script src="https://designskin13.clickn.co.kr/vendors/Dropzone/js/dropzone.min.js"></script>
<script src="https://designskin13.clickn.co.kr/vendors/Masonry/js/masonry.pkgd.min.js"></script>
<script src="https://designskin13.clickn.co.kr/js/design_loader.js?v=20221005"></script>
<script src="https://designskin13.clickn.co.kr/js/skin_render_hf.js?v=20221005"></script>
<script src="https://designskin13.clickn.co.kr/js/skin_render_module.js?<%=new java.util.Date()%>"></script>
<script src="https://designskin13.clickn.co.kr/js/skin_render_menus.js?<%=new java.util.Date()%>"></script>
<script src="https://designskin13.clickn.co.kr/js/skin_render_slide.js?<%=new java.util.Date()%>"></script>
<script src="https://designskin13.clickn.co.kr/js/skin_render_board.js?<%=new java.util.Date()%>"></script>
<script src="https://designskin13.clickn.co.kr/js/skin_render_forms.js?<%=new java.util.Date()%>"></script>
<script src="https://designskin13.clickn.co.kr/js/skin_render_popup.js?<%=new java.util.Date()%>"></script>
<script src="https://designskin13.clickn.co.kr/js/skin_render_settings.js?<%=new java.util.Date()%>"></script>
<script src="https://designskin13.clickn.co.kr/js/skin_render_gallery.js?<%=new java.util.Date()%>"></script>
<script src="https://designskin13.clickn.co.kr/js/skin_basic_info.js?<%=new java.util.Date()%>"></script><!-- 사이트 정보 연동, sns 링크 연동 -->
<script src="https://designskin13.clickn.co.kr/js/skin_render_builderProductA.js?<%=new java.util.Date()%>"></script><!-- 상품 디스플레이 -->
<script src="https://designskin13.clickn.co.kr/js/skin_render_builderProductB.js?<%=new java.util.Date()%>"></script><!-- 상품 문의 -->
<script src="https://designskin13.clickn.co.kr/js/skin_render_builderProductC.js?<%=new java.util.Date()%>"></script><!-- 상품 후기 -->
<script src="https://designskin13.clickn.co.kr/js/skin_ui.js?<%=new java.util.Date()%>"></script>
<script src="https://storage.clickn.co.kr//js/modules/modules_ui.js?<%=new java.util.Date()%>"></script>


<script src="modules/js?m=members&js=login&v=20221005"></script>
    
	<script src="https://designskin13.clickn.co.kr/js/skin_main.js?<%=new java.util.Date()%>"></script><!-- 메인에서만 실행되어야하는 JS -->
    
    
    
	<div id="pageConfigFooter" class="skin_main_container skin_main_container2">
					<!-- ===================== Skin Footer ===================== -->
					<div id="skinFooterSection" class="skin_footer_section" data-footer-logo="yes" data-footer-menu="yes" data-footer-sns="yes" data-footer-info="yes" data-footer-hosting="yes" data-footer-coregistlink="no" data-footer-escro="no" data-footer-copyright="yes" data-footer-textfield1="no" data-footer-textfield2="no" data-fnb-seq="2" data-footer-bgcolor2="" data-footer-brightness="" data-footer-fontcolor="" data-footer-fontfamily="">
						<div class='footer_module' data-module-name="footer_A009" data-module-parents="footer_A001" style="display:none;">
							<div class='module_wrap'>
								<div class='module_container'>
									<div class="footer_section logo_area">
										<a href="/"><b><img id="footerLogo" src="file/maindgs.png" alt="" onerror="this.src='images/module_contents/maindgs.png';" /></b></a>
									</div>
									<div class="footer_section fnb_area">
										<!-- FNB 햄버거
										<div class="fnb_open_area" id="fnbOpenrBtn">
											<span class="fnb_open_btn"><strong>Footer Menu Open</strong></span>
										</div> -->

										<div class="fnb_container">
											<ul id="FnbMenu" class="fnb">
											<li>
												<a href = '/main'>홈</a>
											</li>
											<li>
												<a href = '/pages/about'>소개소개</a>
											</li>
											<li>
												<a href = '/pages/terms'>이용약관</a>
											</li>
											<li>
												<a href = '/pages/policy'>개인정보처리방침</a>
											</li>
											</ul>
										</div>
                                    </div>
									<ul class="footer_section mall_info">
										<li>
											<span class = 'title'>회사명</span>
											<span class = 'desc'>(주)푸드파킹</span>
										</li>
										<li>
											<span class = 'title'>대표자</span>
											<span class = 'desc'>sssk</span>
										</li>
											<li>
											<span class = 'title'>주소 :</span>
											<span class = 'desc'>광주 광역시 서구 농성동 한울 301호</span>
										</li>
										<li>
											<span class = 'title'>이메일 :</span>
											<span class = 'desc'>foodparkin@naver.com</span>
										</li>
										<li>
											<span class = 'title'>전화 :</span>
											<span class = 'desc'>02-1543-7777</span>
										</li>
										<li>
											<span class = 'title'>사업자등록번호 :</span>
											<span class = 'desc'>000-00-00000</span>
										</li>
										<li>
											<span class = 'title'>통신판매업신고 :</span>
											<span class = 'desc'>000-농성동당-00000</span>
										</li>
									</ul>
									<div class="footer_section text_field text_field1"><div data-text-editable="true"><p><span style="font-size:15px; font-weight:500;">고객센터</span></p><p><br></p><p>경기도 성남시 분당구 대왕판교로 670</p><p>유스페이스 B동 322, 323호 / A동 403호</p><p>clickn@gabiacns.com</p></div></div>
									<div class="footer_section text_field text_field2"><div data-text-editable="true"><p><span style="font-size:15px; font-weight:500;">ACCOUNT INFO</span></p><p><br></p><p>국민은행</p><p>00000-00-000000</p><p>(주)CLICKN</p></div></div>
									<div class="footer_section sns_list">
										<ul class="snsLinks">
										</ul>
									</div>
									<div class="footer_section escro_area">
										<ul>
											<li><a href="#escro1"><img src="https://storage.clickn.co.kr//images/module_contents/hf_escro_01.png" alt="" /></a></li>
											<li><a href="#escro2"><img src="https://storage.clickn.co.kr//images/module_contents/hf_escro_02.png" alt="" /></a></li>
										</ul>
                                    </div>
									<div class="footer_section copyright" data-text-editable='true'><p>COPYRIGHT (c) (주)회사명 ALL RIGHTS RESERVED.</p></div>
								</div>
							</div>
						</div>
												<!-- +++++++++ free 배너 +++++++++ -->
						<div class="level1_bnr_wrap">
							<a href="https://www.clickn.co.kr" target="_blank" class="level1_bnr">
								<img src="/images/logo_small.png" alt="CLICKN" class="logo">
								<span class="txt">푸드파킹테스트</span>
							</a>
						</div>
						<!-- +++++++++ //free 배너 +++++++++ -->
											</div>
					<!-- ===================== // Skin Main ===================== -->
				</div>
			</div>