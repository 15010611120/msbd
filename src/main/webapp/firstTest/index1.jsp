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
    .div{ margin:0 auto; width:1200px; height:300px;border:1px solid #F00; clear:both; } 
    
   .bigBannerGif {
   	 top:130px;
     background: url(${pageContext.request.contextPath}/assets/img/ani_bg.jpg) no-repeat;
     background-size:cover;
	}
	.le{
		float:left;
	}
	.ri{
		float:right;
	}
	.abc{ 
	 	margin:0 auto;
		color:black;
		font-size:16px
	} 
	.div{ margin:0 auto; width:1200px; height:300px;border:1px solid #F00; clear:both; } 
	.divMeg{
		margin:0 auto; width:600px; height:40px;border:1px solid #F00; clear:both;
	}
	divri{
	margin:0 auto; width:0px; height:30px; 
	margin-left:2000px
	}
</style>
<body>
    


<!-- header -->

<div class="webHead">
	<div class="header">
	
	    <div class="dl-title">
		    <div class="le">
		    	MySpringBoot笔记首页
		    </div>
	    </div>
	</div>
	<div class="head middle_new">
		 <div class="ri" id="sh">当前登录人 :${ userName }</div>
	 	 <div class="ri" id="hid">当前登录人 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
		 </br>
		 <div class="ri" id="timeShow"></div>
		 <div class="webNav">
			<div id="chk1" class="nav navCheck" onmouseover="this.style.cursor='pointer'">
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
<div class="div"><img src="${pageContext.request.contextPath}/assets/img/activity-lottery-2.png"></div>
<div class="div">
	<div class="abc"><a href="http://www.runoob.com/php/php-variables.html">菜鸟php</a>
	</div>
	</br>
	<div class="abc"><a href="https://www.ibm.com/developerworks/cn/java/j-lo-spring-boot/#table1">使用 Spring Boot 快速构建 Spring 框架应用</a>
	</div>
	</br>
	<div class="abc"><a href="http://blog.csdn.net/hzmhy123456/article/details/69525280">Spring boot跳转页面配置</a>
	</div>
	</br>
	<div class="abc"><a href="http://blog.csdn.net/qq_31852701/article/details/52944312">通过命令上传项目到git</a>
	</div>
	</br>
	<div class="abc"><a href="http://www.w3school.com.cn/">http://www.w3school.com.cn/;</a>
	</div>
	</br>
	<div class="abc"><a href="http://blog.csdn.net/tingzhiyi/article/details/76101031">eclipse 集成git 更新项目</a>
	</div>
	</br>
</div>

<script>
var a="${userName}";
if(a!=""){
	$("#hid").hide();
	$("#hideDiv").hide();
	$(".login").hide();
}else{
	$("#sh").hide();
} 
/* $("#chk1").removeClass();
$("#chk1").addClass("nav"); */
/* $("#chk2").addClass("nav navCheck"); */
function changHeadStyle(a){//首页
	if(a=="1"){
		$("#chk1").removeClass().addClass("nav");
		$("#chk2").removeClass().addClass("nav");
		$("#chk3").removeClass().addClass("nav");
		$("#chk4").removeClass().addClass("nav");
		$("#chk1").addClass("nav navCheck"); 
	}
	if(a=="2"){//个人笔记
		$("#chk1").removeClass().addClass("nav");
		$("#chk2").removeClass().addClass("nav");
		$("#chk3").removeClass().addClass("nav");
		$("#chk4").removeClass().addClass("nav");
		$("#chk2").addClass("nav navCheck"); 
	}
	if(a=="3"){//源码
		$("#chk1").removeClass().addClass("nav");
		$("#chk2").removeClass().addClass("nav");
		$("#chk3").removeClass().addClass("nav");
		$("#chk4").removeClass().addClass("nav");
		$("#chk3").addClass("nav navCheck"); 
		window.open("https://github.com/15010611120/msbd");
	}
	if(a=="4"){//demo
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