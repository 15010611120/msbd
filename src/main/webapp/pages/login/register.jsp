<!DOCTYPE html>
<html>
<head>
<title>注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<link href="css/loginCommon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/loginJs/jquery.js"></script>
<script type="text/javascript" src="js/loginJs/jquery.i18n.properties-1.0.9.js" ></script>
<script type="text/javascript" src="js/loginJs/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="js/loginJs/jquery.validate.js"></script>
<script type="text/javascript" src="js/loginJs/md5.js"></script>
<script type="text/javascript" src="js/loginJs/page_regist.js?lang=zh"></script>
</head>
<body class="loginbody">
<div class="dataEye">
	<div class="loginbox registbox">
		<div class="login-content reg-content">
			<div class="loginbox-title">
				<h3>注册</h3>
			</div>
			<form id="signupForm">
			<div class="login-error"></div>
			<div class="row">
				<label class="field" for="email">注册邮箱</label>
				<input type="text" value="" class="input-text-user noPic input-click" name="email" id="email">
			</div>
			<div class="row">
				<label class="field" for="password">密码</label>
				<input type="password" value="" class="input-text-password noPic input-click" name="password" id="password">
			</div>
			<div class="row">
				<label class="field" for="passwordAgain">确认密码</label>
				<input type="password" value="" class="input-text-password noPic input-click" name="passwordAgain" id="passwordAgain">
			</div>
			<div class="row">
				<label class="field" for="contact">联系人</label>
				<input type="text" value="" class="input-text-user noPic input-click" name="contact" id="contact">
			</div>
			<div class="row">
				<label class="field" for="company">公司名</label>
				<input type="text" value="" class="input-text-user noPic input-click" name="company" id="company">
			</div>
			<div class="row">
				<label class="field" for="tel">公司电话</label>
				<input type="text" value="" class="input-text-user noPic input-click" name="tel" id="tel">
			</div>
			<div class="row">
				<label class="field" for="address">公司地址</label>
				<input type="text" value="" class="input-text-user noPic input-click" name="address" id="address">
			</div>
			<div class="row">
				<label class="field" for="qq">QQ</label>
				<input type="text" value="" class="input-text-user noPic input-click" name="qq" id="qq">
			</div>
			<div class="row tips">
				<input type="checkbox" id="checkBox">
				<label>
				我已阅读并同意
				<a href="#" target="_blank">隐私政策、服务条款</a>
				</label>
			</div>
			<div class="row btnArea">
				<a class="login-btn" id="submit">注册</a>
			</div>
			</form>
		</div>
		<div class="go-regist">
			已有帐号,请<a href="#" class="link">登录</a>
		</div>
	</div>
	
<div id="footer">
	<div class="dblock">
		<div class="inline-block"><img src="resources/images/logo-gray.png"></div>
		<div class="inline-block copyright">
			<p><a href="#">关于我们</a> | <a href="#">微博</a> | <a href="#">隐私政策</a> | <a href="#">人员招聘</a></p>
			<p> Copyright © 2013 JS代码网</p>
		</div>
	</div>
</div>
</div>
<div class="loading">
	<div class="mask">
		<div class="loading-img">
		<img src="resources/images/loading.gif" width="31" height="31">
		</div>
	</div>
</div>
</body>
</html>

