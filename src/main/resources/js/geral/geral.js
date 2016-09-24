$(document).ready(function() { // document and jquery ready
	// select all anchors with a class of 'confirm'
	$('a.confirm').click(function(event) {

		// prevent the default action of opening the link
		event.preventDefault()

		// grab the url of the current link
		var url = $(this).attr('href');

		// set the confirmation message
		// var confirm_box = fnOpenNormalDialog();
		var confirm_box = confirm('Deseja inativar esse registro?');

		// if the confirmation is true (user clicks ok) direct the browser to
		// the link's url
		if (confirm_box) {
			window.location = url;
			// uncomment below and remove above if you want the link to open in
			// a new window
			// window.open(url,'_blank');
		}
	});

	// Mascara para dinheriro
	$("input.dinheiro").maskMoney({
		prefix : 'R$ ',
		allowNegative : true,
		thousands : '.',
		decimal : ',',
		affixesStay : false
	});
	// Mascara para porcentagem
	$("input.porcentagem").maskMoney({
		allowNegative : false,
		thousands : '.',
		decimal : ',',
	});

});
