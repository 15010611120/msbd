<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>MySpringBootDemo</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${pageContext.request.contextPath}/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/css/main-min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/maincss/css/maincss.css" rel="stylesheet" type="text/css" />
</head>

<script type="text/javascript" src="${pageContext.request.contextPath}assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}assets/js/bui-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}assets/js/common/main-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}assets/js/config-min.js"></script>
<script>
</script>
<body>

<div class="header">

    <div class="dl-title">
      MySpringBoot笔记首页
    </div>

</div>
<!-- 
<div id="fkjzVideoPreBox">
	<div id="fkjzVideoLittleClose"></div>
</div> 
 -->
<!-- header -->




<div class="webHead">
	<div class="head middle_new">

		<div class="webNav">
			<div class="nav navCheck">
				<a hidefocus="true" href="" title="SpringBoot笔记">首页</a>
			</div>
			<div class="nav ">
				<a hidefocus="true" href="">个人笔记</a>
			</div>
			<div class="nav ">
				<a hidefocus="true" href="https://github.com/15010611120/msbd" title="源码">源码</a>
			</div>
			<div class="nav ">
				<a hidefocus="true" href="">demo</a>
			</div>
		</div>
		<div class="headRight">
			<div id="divId">
				当前登录人 ${ userName }
			</div>
			<div class="register" id="hideDiv">
				<a hidefocus="true" class="reg" href="">免费注册</a>
			</div id="hideDiv">
				<a hidefocus="true" class="login" href="http://localhost:8087/login.jsp" target="_blank">登录</a>
		</div>
	</div>
</div>

</body>
</html>