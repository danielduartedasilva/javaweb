$(function() {
	try {
		var page = window.location.pathname.split("/").pop(); // -------------------------pop() pega o ultimo elemento do array, no caso da barra de enderço da pagina do browser
		$('a[href=\'' + page + '\']').addClass('selected');
	} catch (e) {
		// nada a fazer
	}
});
