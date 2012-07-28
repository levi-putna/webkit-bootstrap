$(document).ready(function() {
	// Your main content that will be replaces
	var body = "article";
	var $body = $(body);

	$("nav").delegate("a", "click", function() {
		window.location.hash = $(this).attr("href");
		return false;
	});

	$(window).bind('hashchange', function() {
		var newHash = window.location.hash.substring(1);
		if (newHash) {
			$body.fadeOut(200, function() {
				$body.load(newHash + " " + body, function() {
					$body.show(200, function() {
						//prettyPrint();
					});
				});
			});
		}
		;
	});

	$(window).trigger('hashchange');
});