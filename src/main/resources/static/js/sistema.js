$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-moeda').maskMoney({ decimal : ',', thousands: '.', allowZero : true });
});

$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	
	let button = $(event.relatedTarget);
	let codigo = button.data('codigo');
	let descricao = button.data('descricao');

	let modal = $(this);
	let form = modal.find('form');
	let action = form.data('url-base');
	
	if(!action.endsWith()) {
		action += '/';
	}
	
	form.attr('action', action + codigo);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + 
			descricao + '</strong>?');
	
});

$('.js-atualizar-status').on('click', function(event) {
	event.preventDefault();
	
	let botaoReceber = $(event.currentTarget);
	let urlReceber = botaoReceber.attr('href');
	
	let resposta = $.ajax({
		url: urlReceber,
		type : 'PUT'
	});
	
	resposta.done(function(e) {
		let codigo = botaoReceber.data('codigo');
		$('[data-role=' + codigo + ']').html('<span class="label label-success">' + e + '</span>');
		botaoReceber.hide();
	});
	
	resposta.fail(function(e) {
		console.log('Error', e);
		alert('Um erro ocorreu ao receber o título selecionado.');
	});
	
});