$(document).ready(function() { // document and jquery ready

	var $alerts = $('div[class^="alert"]');
	$alerts.hide();

	$('#formPage').keydown(function(e) {
		var key = e.which;
		if (key == 13) {
			e.preventDefault();
			this.setAttribute('action', '/Investimentos/site/gravarRendaFixaSaida');
			submitPost(this);
		}
	});
});

function submitPost(frm) {
	var rendaFixa = new Object();

	$.each($('input, select ,textarea', $(frm)), function(k) {
		var input = $(this);

		var tempName = input.attr("name");
		if (tempName != "undefined" && tempName != "") {
			if (input.hasClass("dinheiro")) {
				var tempMoeda = input.val().replace('R$ ', '');
				// .replace(/\,/g, '') para remover todas as virgula
				if (tempMoeda.length < 8) {
					tempMoeda = tempMoeda.replace(',', '.')
				} else {
					tempMoeda = tempMoeda.replace('.', '').replace(',', '.')
				}
				// console.log(tempMoeda);

				rendaFixa[tempName] = parseFloat(tempMoeda);
			} else if (input.hasClass("porcentagem")) {
				var tempPercent = input.val().replace('%', '');
				// .replace(/\,/g, '') para remover todas as virgula
				if (tempPercent.length < 8) {
					tempPercent = tempPercent.replace(',', '.')
				} else {
					tempPercent = tempPercent.replace('.', '').replace(',', '.')
				}
				console.log(tempPercent);

				rendaFixa[tempName] = parseFloat(tempPercent) / 100;
				console.log(rendaFixa[tempName]);
			} else {
				rendaFixa[tempName] = input.val();
			}
		}

	});

	// console.log(JSON.stringify(cotacao));
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : $(frm).attr('method'),
		url : $(frm).attr('action'),
		data : JSON.stringify(rendaFixa),
		success : function(response) {
			ajaxResponseSucess(frm, response);
			closeModelProgressBar();
		},
		error : function(e) {
			ajaxResponseFail(e);
		}
	});
}

function tratarReturnList(result) {

}
