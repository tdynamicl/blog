;+function(window, document, undefined){
	$(document).ready(function(){
		
		var init = function(){
			$.ajaxSetup ({
				cache: false //close AJAX cache
			});
			load();
		};
		
		// 加载html
		var load = function(){
			$('body>footer').load('assets/subhtml/footer.html', function(resp) {
				bindEvents();
			});
			
		};
		
		//绑定事件
		var bindEvents = function(){
			
		};
		
		init();
		
	});
}(window, document);