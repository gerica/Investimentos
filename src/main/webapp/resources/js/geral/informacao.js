$(document).ready(function() { // document and jquery ready
	// notas
	$('div[id^="div_hightliht_"]').css('background', '#f0dff0');
	$('div[id^="div_hightliht_"]').css('cursor', 'pointer');
	$('div[id^="div_hightliht_"]').css('-webkit-box-shadow', ' 9px 9px 15px 1px rgba(0,0,0,0.75)');
	$('div[id^="div_hightliht_"]').css('-moz-box-shadow', ' 9px 9px 15px 1px rgba(0,0,0,0.75)');
	$('div[id^="div_hightliht_"]').css('box-shadow', '9px 9px 15px 1px rgba(0,0,0,0.75)');
	$('div[id^="div_hightliht_"]').css('border-radius', '10px 10px 10px 10px');
	$('div[id^="div_hightliht_"]').css('-moz-border-radius', ' 10px 10px 10px 10px');
	$('div[id^="div_hightliht_"]').css('-webkit-border-radius', ' 10px 10px 10px 10px');
	$('div[id^="div_hightliht_"]').css('border', ' 2px solid #dbc3db');
	$($('div[id^="div_hightliht_"]')).hover(function() {
		// Ao posicionar o cursor sobre a div
		$(this).css('background', '#FFD700');
//		$(this).transition({
//			scale : 1.2
//		});
	}, function() { // Ao remover o cursor da div
		$(this).css('background', '#f0dff0');
//		$(this).transition({
//			scale : 1.0
//		});
	});

});
