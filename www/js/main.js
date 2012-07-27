$(document).ready(
		function() {
			// files = getFeed("http://ozblog.com.au/feed/");

			files = getFeed("/");

			var listHTML = "";

			for (i = 0; i < files.length; i++) {
				listHTML += '<li><a href="#">' + files[i] + ' <span>6</span></a></li>';
			}

			$(".menu").append(
					"<li><a href='#'>oZblog.com.au <span>" + files.length
							+ "</span></a><ul>" + listHTML + "</ul></li>");
		});