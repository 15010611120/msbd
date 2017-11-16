<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored ="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login My--后台管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<link rel="shortcut icon" href="images/icons/favicon.gif" />
<link href="css/loginCommon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/loginJs/jquery.js"></script>
<script type="text/javascript" src="js/loginJs/jquery.i18n.properties-1.0.9.js" ></script>
<script type="text/javascript" src="js/loginJs/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="js/loginJs/jquery.validate.js"></script>
<script type="text/javascript" src="js/loginJs/md5.js"></script>
<script type="text/javascript" src="js/loginJs/page_regist.js?lang=zh"></script>
<script type="text/javascript">
//登陆验证
function login(){
	var email=$("#email").val();
	var userPwd=$("#password").val();
	$.ajax({   
        type:'post',        
        url:'/loginChecked',    
        data:{
        	email:email,
        	userPwd:userPwd
        },    
        cache:false,    
        dataType:'json',    
        success:function(data){   
           if(data.rspCode=="00"){
           	 window.location.href="/loginIndex";
           }else{
           	alert(data.rspMsg);
           }
        }
    }); 
}

//注册
function register(){
	window.location.href="/register";
}

</script>
</head>
<body class="loginbody">
<div class="dataEye">
	<div class="loginbox registbox">
		<div style="margin-top:100px">
				<h1><small style="margin-left: 70px;">欢迎使用yxd后台管理系统</small></h1>
		</div>
		<div class="login-content reg-content">
			<div class="loginbox-title">
				<h3>登陆</h3>
			</div>
			<form action="/loginIndex" method="post" id="login-form">
				<div class="login-error"></div>
				<div class="row">
					<label class="field" for="email">注册邮箱</label>
					<input type="text" value="" class="input-text-user noPic input-click" name="email" id="email">
				</div>
				<div class="row">
					<label class="field" for="password">密码</label>
					<input type="password" value="" class="input-text-password noPic input-click" name="password" id="password">
				</div>
				<div class="row btnArea">
					<a class="login-btn" id="submit" onClick="login()">登录</a>
				</div>
			</form>
		</div>
		<div class="go-regist">
			<a href="#" class="link" onclick="register('loginAction/productListJump')">注册</a>&nbsp;|&nbsp;已有帐号,<a href="#" class="link">忘记密码</a>
		</div>
	</div>
</div>
<div class="loading">
	<div class="mask">
		<div class="loading-img">
		<img src="images/loginImg/loading.gif" width="31" height="31">
		</div>
	</div>
</div>
</body>
</html>