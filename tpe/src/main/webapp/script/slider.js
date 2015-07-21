$(function() {
	slider.init();
});
var slider = {
	num : -1,
	cur : 0,
	cr : [],
	al : null,
	at : 10 * 1000,
	ar : true,
	init : function() {
		if (!slider.data || !slider.data.length)
			return false;

		var d = slider.data;
		slider.num = d.length;
		var pos = Math.floor(Math.random() * 1);// slider.num);
		for (var i = 0; i < slider.num; i++) {
			$('#' + d[i].id).css({
				left : ((i - pos) * 10000)
			});
			$('#slide-nav').append(
					'<a id="slide-link-' + i
							+ '" href="#" onclick="slider.slide(' + i
							+ ');return false;" onfocus="this.blur();"></a>');
		}

		$('img,div#slide_text,div#slide_arrows,.info_title',
				$('div#slide-holder')).fadeIn();
		slider.text(d[pos]);
		slider.on(pos);
		slider.cur = pos;
		$('#slide_next').click(function() {
			if (!slider.ar)
				return false;

			var next = slider.cur + 1;
			if (next >= slider.num)
				next = 0;

			slider.slide(next);
		});
		$('#slide_prev').click(function() {
			if (!slider.ar)
				return false;
			var next = slider.cur - 1;

			if (next < 0)
				next = slider.num - 1;

			slider.slide(next);
		});
		window.setTimeout('slider.auto();', slider.at);
	},
	auto : function() {
		if (!slider.ar)
			return false;

		var next = slider.cur + 1;
		if (next >= slider.num)
			next = 0;
		slider.slide(next);
	},
	slide : function(pos) {
		if (pos < 0 || pos >= slider.num || pos == slider.cur)
			return;

		window.clearTimeout(slider.al);
		slider.al = window.setTimeout('slider.auto();', slider.at);

		var d = slider.data;
		for (var i = 0; i < slider.num; i++)
			$('#' + d[i].id).stop().animate({
				left : ((i - pos) * 10000)
			}, 1000, 'swing');

		slider.on(pos);
		slider.text(d[pos]);
		slider.cur = pos;
	},

	on : function(pos) {
		$('#slide-nav a').removeClass('on');
		$('#slide-nav a#slide-link-' + pos).addClass('on');
	},
	text : function(di) {
		$('#slide-info').html(di.info);
		$('.slide-link').html(di.title);
		$('.slide-link').attr('href', di.url);
	}

};
