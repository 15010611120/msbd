<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/calculator.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/yxdWin.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugin/extjs/resources/css/ext-all.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/extjs/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/extjs/ext-all.js"></script>
<title>Insert title here</title>
<script>
        var num = 0;  // 定义第一个输入的数据
        function jsq(num) {
            //获取当前输入
            if(num=="%"){
                document.getElementById('screenName').value=Math.round(document.getElementById('screenName').value)/100;
            }else{
                document.getElementById('screenName').value += document.getElementById(num).value;
            }
        }
        function eva() {
            //计算输入结果
            document.getElementById("screenName").value = eval(document.getElementById("screenName").value);
        }
        function clearNum() {
            //清0
            document.getElementById("screenName").value = null;
            document.getElementById("screenName").focus();
        }
        function tuiGe() {
            //退格
            var arr = document.getElementById("screenName");
            arr.value = arr.value.substring(0, arr.value.length - 1);
        }
    </script>
</head>
<body>
<div id="calculator">
    <div class="LOGO">
        <span class="name">计算器</span>
        <span class="verson"></span>
    </div>
    <div id="shuRu">
        <!--screen输入栏-->
        <div class="screen">
            <input type="text" id="screenName" name="screenName" class="screen">
        </div>
    </div>
    <div id="keys">
        <!-- j -->
        <!--第一排-->
        <input type="button" id="7" onclick="jsq(this.id)" value="7" class="buttons">
        <input type="button" id="8" onclick="jsq(this.id)" value="8" class="buttons">
        <input type="button" id="9" onclick="jsq(this.id)" value="9" class="buttons">
        <input type="button" id="Back" onclick="tuiGe()" value="Back" class="buttons">
        <input type="button" id="C" onclick="clearNum()" value="C" class="buttons" style="margin-right:0px">
        <!--第二排-->
        <input type="button" id="4" onclick="jsq(this.id)" value="4" class="buttons">
        <input type="button" id="5" onclick="jsq(this.id)" value="5" class="buttons">
        <input type="button" id="6" onclick="jsq(this.id)" value="6" class="buttons">
        <input type="button" id="*" onclick="jsq(this.id)" value="X" class="buttons">
        <input type="button" id="/" onclick="jsq(this.id)" value="/" class="buttons" style="margin-right:0px">
        <!--第三排-->
        <input type="button" id="1" onclick="jsq(this.id)" value="1" class="buttons">
        <input type="button" id="2" onclick="jsq(this.id)" value="2" class="buttons">
        <input type="button" id="3" onclick="jsq(this.id)" value="3" class="buttons">
        <input type="button" id="+" onclick="jsq(this.id)" value="+" class="buttons">
        <input type="button" id="-" onclick="jsq(this.id)" value="-" class="buttons" style="margin-right:0px">
        <!--第四排-->
        <input type="button" id="0" onclick="jsq(this.id)" value="0" class="buttons">
        <input type="button" id="00" onclick="jsq(this.id)" value="00" class="buttons">
        <input type="button" id="." onclick="jsq(this.id)" value="." class="buttons">
        <input type="button" id="%" onclick="jsq(this.id)" value="%" class="buttons">
        <input type="button" id="eva" onclick="eva()" value="=" class="buttons" style="margin-right:0px">
    </div>
    <div class="footer">
        <span class="aside">欢迎使用JavaScript计算器</span>
            <span class="link">
                <a href="#" title="声明" target="_blank">反馈</a>
            </span>
    </div>
</div>
</body>
</html>