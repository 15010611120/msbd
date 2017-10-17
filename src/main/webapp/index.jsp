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

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/bui-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/common/main-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/config-min.js"></script>
<script>


</script>
<style type="text/css">
    table{
    	margin:auto;
        border-collapse:collapse;
    }
    table td{
        border:1px solid #ccc;
        width:1200px;
    }
    .div{ margin:0 auto; width:1200px; height:300px; } 
    
   .bigBannerGif {
   	 top:130px;
     background: url(${pageContext.request.contextPath}/assets/img/ani_bg.jpg) no-repeat;
	}
	.le{
		float:left;
	}
	.ri{
		float:right;
	}
	.abc{ 
		color:black;
		font-size:16px
	} 
	.divMeg{
		top:1800px;
		margin:0 auto; width:1200px; height:300px;
	}
	divri{
	margin:0 auto; width:0px; height:30px; 
	margin-left:2000px
	}
</style>
<body>
    
<div class="header">

    <div class="dl-title">
    <div class="le">
    	MySpringBoot笔记首页
    </div>
    </div>
</div>
 <div class="ri">当前登录人 :${ userName }</div>
 </br>
 <div class="ri" id="timeShow"></div>
    <label id="time"></label>
<!-- header -->

<div class="webHead">
	<div class="head middle_new">

		<div class="webNav">
			<div id="chk1" class="nav" onmouseover="this.style.cursor='pointer'">
				<a hidefocus="true" onclick="changHeadStyle(1)" title="SpringBoot笔记">首页</a>
			</div>
			<div id="chk2" class="nav" onmouseover="this.style.cursor='pointer'">
				<a hidefocus="true" onclick="changHeadStyle(2)">个人笔记</a>
			</div>
			<div id="chk3" class="nav" onmouseover="this.style.cursor='pointer'">
				<a hidefocus="true" onclick="changHeadStyle(3)" title="源码">源码</a>
			</div>
			<div id="chk4" class="nav " onmouseover="this.style.cursor='pointer'">
				<a hidefocus="true" onclick="changHeadStyle(4)">demo</a>
			</div>
		</div>
		<div class="headRight">
			<div class="register" id="hideDiv">
				<a hidefocus="true" class="reg" href="">免费注册</a>
			</div>
				<a hidefocus="true" class="login" href="loginAction/loginjump" target="_blank">登录</a>
		</div>
	</div>
</div>

<div class="div bigBannerGif">
</div>
<div class="divMeg"></div>
<div class="abc divri">W3CShool:<a href="http://www.w3school.com.cn/">http://www.w3school.com.cn/</a></div>

<script>
var a="${userName}";
if(a!=""){
	$("#hideDiv").hide();
	$(".login").hide();
} 
$("#chk1").removeClass();
$("#chk1").addClass("nav");
/* $("#chk2").addClass("nav navCheck"); */
function changHeadStyle(a){
	if(a=="1"){
		$("#chk1").removeClass().addClass("nav");
		$("#chk2").removeClass().addClass("nav");
		$("#chk3").removeClass().addClass("nav");
		$("#chk4").removeClass().addClass("nav");
		$("#chk1").addClass("nav navCheck"); 
	}
	if(a=="2"){
		$("#chk1").removeClass().addClass("nav");
		$("#chk2").removeClass().addClass("nav");
		$("#chk3").removeClass().addClass("nav");
		$("#chk4").removeClass().addClass("nav");
		$("#chk2").addClass("nav navCheck"); 
	}
	if(a=="3"){
		$("#chk1").removeClass().addClass("nav");
		$("#chk2").removeClass().addClass("nav");
		$("#chk3").removeClass().addClass("nav");
		$("#chk4").removeClass().addClass("nav");
		$("#chk3").addClass("nav navCheck"); 
	}
	if(a=="4"){
		$("#chk1").removeClass().addClass("nav");
		$("#chk2").removeClass().addClass("nav");
		$("#chk3").removeClass().addClass("nav");
		$("#chk4").removeClass().addClass("nav");
		$("#chk4").addClass("nav navCheck"); 
	}
	
}

//页面展示动态时间
var t = null;
t = setTimeout(time,1000);//开始执行
function time()
{
   clearTimeout(t);//清除定时器
   dt = new Date();
   var h=dt.getHours();
   var m=dt.getMinutes();
   var s=dt.getSeconds();
   document.getElementById("timeShow").innerHTML =  "现在的时间为："+h+"时"+m+"分"+s+"秒";
   t = setTimeout(time,1000); //设定定时器，循环执行             
} 

   
   
</script>
</body>
</html>