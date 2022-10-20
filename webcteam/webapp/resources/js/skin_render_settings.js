function snsLinkAjax() {
	$.ajax({
		url : '/modules/sns_links',
		dataType : 'json',
		success : function( response ) {
			console.log( response.sns_links );
		}
	});
}





$(function() {
	//snsLinkAjax();
});