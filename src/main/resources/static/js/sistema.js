$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-moeda').maskMoney({ decimal : ',', thousands: '.', allowZero : true });
});

