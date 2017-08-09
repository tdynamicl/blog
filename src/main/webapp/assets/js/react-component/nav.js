;+function(window, document, undefined){
	var NavContainerEL = document.getElementById('nav-box');
	
	var Nav=React.createClass({
		getInitialState: function() {
			return {
				loginUserInfo: null
			};
		},
		render: function() {
			return (
				<nav className="navbar navbar-default" role="navigation">
					<div className="container-fluid">
						<div className="navbar-header">
							<a className="navbar-brand" href="index.html">BOLOG</a>
						</div>
						<div>
							<ul className="nav navbar-nav">
								<li><a href="articles.html">文章</a></li>
								<li><a href="images.html">图片</a></li>
							</ul>
							{
							this.props.loginUserInfo?(
								<NavMenuUser loginUserInfo={this.props.loginUserInfo} />
									
							):(
								<NavMenuSign />
							)	
							}
						</div>

					</div>
				</nav>	
			);
		}
		
		
	});
	
	var NavMenuUser=React.createClass({
		render: function() {
			return (
				<ul className="nav navbar-nav navbar-right">
					<li className="dropdown"><a className="dropdown-toggle"
						data-toggle="dropdown">{this.props.loginUserInfo.nickname}<b 
							className="caret"></b></a>
						<ul className="dropdown-menu">
							<li><a href="myhome.html">个人中心</a></li>
							<li><a href="setting.html">设置</a></li>
							<li className="divider"></li>
							<li><a logout>退出登录</a></li>
						</ul>
					</li>
				</ul>
			);
		}
	});
	
	var NavMenuSign=React.createClass({
		render: function() {
			return (
				<ul className="nav navbar-nav navbar-right">
					<li><a href="register.html"> <span
							className="glyphicon glyphicon-user"></span> 注册
					</a></li>
					<li><a href="login.html"> <span
							className="glyphicon glyphicon-log-in"></span> 登录
					</a></li>
				</ul>
			);
		}
	});
	
	var checkLogin=function() {
		$.ajax({ 
			type: 'post', 
			url: 'loadUserInfo.do', 
			async: true, 
			data: null,
			dataType: 'json', 
			success: function(resp) {
				if (resp.code) {
					ReactDOM.render(
							<Nav />,
							NavContainerEL
					);
				} else {
					ReactDOM.render(
							<Nav loginUserInfo={resp.data} />,
							NavContainerEL
					);
				}
			}, 
			error: function(e) { 
				ReactDOM.render(
						<Nav />,
						NavContainerEL
				);
			} 
		});
	};
	
	var init = function() {
		
		checkLogin();
	};
	
	init();
	
}(window, document);