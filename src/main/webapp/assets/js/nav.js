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
			$('body>header').load('assets/subhtml/nav.html', function(resp){
				getUserInfo();
				//绑定事件
				bindEvents();
			});
			
		};
		
		// 获取登录用户信息
		var getUserInfo = function(){
			$.post('loadUserInfo.do', null, function(resp){
				if (resp.code) {
					$('nav ul[hwl]').show();
					//alert(data.message + '(' + data.code + ')');
				} else {
					$('nav ul[swl]').show();
					sessionStorage.setItem('userInfo', resp.data);
					// 设置nav上的昵称
					$('nav a[nickname]').html(resp.data.nickname + $('a[nickname]').html());
				}
			});
			
		};
		
		
		
		//绑定事件
		var bindEvents = function(){
			
			// 退出登录
			$('nav a[logout]').on('click', function(evt){
				$.post('logout.do', null, function(resp){
					
					location.href="index.html";
				});
				
			});
			
		};
		
		init();
		
	});
}(window, document);