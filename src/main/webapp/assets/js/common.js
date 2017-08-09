;+function(window, document, undefined){
	$(document).ready(function(){
		
		var init = function(){
			// 将工具方法绑定到window
			window.tools = tools;
		};
		
		// 定义各种工具方法
		var tools = (function(){
			
			// 获取url参数
			var getQueryString = function(name) { 
				var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
				var r = window.location.search.substr(1).match(reg); 
				if (r != null) 
					return decodeURI(r[2]); 
				return null; 
			};
			
			// 处理错误代码
			var codeHandler = function(code) {
				switch (code) {
				// 未知错误
				case 1000:
					
					break;
				// 登录权限的问题
				case 1201:
				case 1202:
				case 1203:
					location.href = 'login.html';
					break;

				default:
					break;
				}
			};
			
			var getArticleStatusName = function(code) {
				var statusName;
				switch (code) {
				case 1:
					statusName = "正常";
					break;
				case 2:
					statusName = "禁用";
					break;
				case 3:
					statusName = "";
					break;
				case 4:
					statusName = "";
					break;
				default:
					break;
				}
				return statusName;
			};
			
			// 暴露的工具方法
			return {
				getQueryString: getQueryString, 
				codeHandler: codeHandler,
				getArticleStatusName: getArticleStatusName
			};
		})();
		
		init();
		
	});
}(window, document);