/* =======================================================
* SNS 링크 연동
========================================================= */
function snsLink() {
	$.ajax({
		url:'/modules/sns_links',
		dataType:'json',
		success : function( response ) {
			var snsLinksGon = '';
			$.each( response.sns_links, function( key ) {
				if ( response.sns_links[key].is_hidden != 'Y' ) {
					snsLinksGon += '<li><a href="' + response.sns_links[key].link_url + '" target="_blank" title="새창"><img src="' + response.sns_links[key].icon_url + '" alt="" /></a></li>';
				}
			});
			$( 'ul.snsLinks' ).html( snsLinksGon );
		}
	});
}


/* =======================================================
* 푸터 회사 정보
========================================================= */
function companyInfo( targetSelector ) {
	var coInfoGon = '';
	if ( BASIC_CONFIG.co_name ) coInfoGon += '<li><span class="title">회사명 : </span><span class="desc">' + BASIC_CONFIG.co_name + '</span></li>';
	if ( BASIC_CONFIG.ceo_name ) coInfoGon += '<li><span class="title">대표자 : </span><span class="desc">' + BASIC_CONFIG.ceo_name + '</span></li>';
	if ( BASIC_CONFIG.co_address ) coInfoGon += '<li><span class="title">주소 : </span><span class="desc">' + BASIC_CONFIG.co_address + ' ' + BASIC_CONFIG.co_address_detail + '</span></li>';
	if ( BASIC_CONFIG.co_email ) coInfoGon += '<li><span class="title">이메일 : </span><span class="desc">' + BASIC_CONFIG.co_email + '</span></li>';
	if ( BASIC_CONFIG.co_phone ) coInfoGon += '<li><span class="title">전화 : </span><span class="desc">' + BASIC_CONFIG.co_phone + '</span></li>';
	if ( BASIC_CONFIG.co_fax ) coInfoGon += '<li><span class="title">팩스 : </span><span class="desc">' + BASIC_CONFIG.co_fax + '</span></li>';
	if ( BASIC_CONFIG.co_regist_num ) coInfoGon += '<li><span class="title">사업자등록번호 : </span><span class="desc">' + BASIC_CONFIG.co_regist_num + '</span></li>';
	if ( BASIC_CONFIG.co_it_num ) coInfoGon += '<li><span class="title">통신판매업신고 : </span><span class="desc">' + BASIC_CONFIG.co_it_num + '</span></li>';
	if ( BASIC_CONFIG.co_regist_num ) {
		var coRegistNo = BASIC_CONFIG.co_regist_num.replace(/-/g, '');
		var coRegistUrl = 'https://www.ftc.go.kr/bizCommPop.do?wrkr_no=' + coRegistNo;
		coInfoGon += '<li class="co_regist_link"><a class="desc" href="' + coRegistUrl + '" target="_blank">[사업자정보확인]</a></span></li>';
	}
	coInfoGon += '<li class="hosting_info"><span class="title">호스팅서비스 : </span><span class="desc">가비아씨엔에스</span></li>';

	$( targetSelector ).html( coInfoGon );
}




$(function() {
	snsLink();
	companyInfo( '#footerCompanyInfo' ) ;
});
