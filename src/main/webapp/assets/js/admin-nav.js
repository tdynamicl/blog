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
			$('body>header').load('../assets/subhtml/admin-nav.html', function(){
				//绑定事件
				bindEvents();
				getUserInfo();
			});
			
		};
		
		// 获取登录用户信息
		var getUserInfo = function(){
			$.ajax({ 
				type: 'post', 
				url: 'check_login.do', 
				async: true, 
				data: null,
				dataType: 'json', 
				success: function(resp) {
					if (resp.code) {
						location.href="login.html";
					} else {
						$('nav a[nickname]').html(resp.data.account + $('a[nickname]').html());
						$('body').show();
					}
				}, 
				error: function(e) { 
					location.href="login.html";
				} 
			}); 
			
		};
		
		//绑定事件
		var bindEvents = function(){
			
			// 退出登录
			$('nav a[logout]').on('click', function(evt){
				$.post('logout.do', null, function(resp){
					location.href="login.html";
				});
				
			});
			
		};
		
		init();
		
	});
}(window, document);