$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-moeda').maskMoney({ decimal : ',', thousands: '.', allowZero : true });
	
	$('.js-atualizar-status').on('click', function(event) {
		event.preventDefault();
		
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		var resposta = $.ajax({
			url: urlReceber,
			type : 'PUT'
		});
		
		resposta.done(function(e) {
			botaoReceber.hide();
		});
		
		resposta.fail(function(e) {
			console.log('Error', e);
		});
		
	});
});

$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	var idTitulo = button.data('id');
	var descricao = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	
	if(!action.endsWith()) {
		action += '/';
	}
	
	form.attr('action', action + idTitulo);
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + 
			descricao + '</strong>?');
	
});