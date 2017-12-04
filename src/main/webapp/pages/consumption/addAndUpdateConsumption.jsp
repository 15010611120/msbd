<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored ="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/plugin/extjs/resources/css/ext-all.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/btnCss.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugin/extjs/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugin/extjs/ext-all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/yxdWin.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/popWindow.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugin/My97DatePicker/WdatePicker.js"></script>


<title>消费记录</title>
<script type="text/javascript">

function jumpBlack(){
	parent.window.location="/consumptionJump";
}

function saveConsumption(){
	var consumptionName= $("#consumptionName").val();
	$.ajax({
		type:"POST",
		url:"/addOrUpdateConsumption",
		data: $("#frmAdd").serialize(),
		success: function(result) {
			 var obj = jQuery.parseJSON(result);
				if (obj.success) {
				alert("消费记录添加成功！！！");
				parent.window.location="";
				closewin("winPanel");
				//window.history.go(-1);
			}else {
				alert("消费记录添加失败！");
				parent.window.location="";
				closewin("winPanel");
			}
		}
	});
}
</script>
<style type="text/css">
.tableNormal {
	border:1px solid #ccc;
	border-collapse:collapse;
	font-size:14px;
	padding-left:10px;
	}</style>
</head>
<body>
	<div class="frameBody">
	<div class="frameTitle">添加消费记录 (っ´Ι`)っ</div>
		<form name="frmAdd" id="frmAdd" method="post" action="/addOrUpdateConsumption" enctype="multipart/form-data" >
			<fieldset style="padding:1 5px 0 5px;margin-bottom:5px;font-weight:100;weight:100%;margin-top:10px">
			<legend><span style="font-size:14px"> </span></legend>
				<table>
					<tr>
						<td>商品名称：</td>
						<td><input id="consumptionName" name="consumptionName" style="width:108px" value=""/></td>
						<td>商品类型：</td>
						<td><input id="consumptionType" name="consumptionType" style="width:108px" value="${name}"/></td>
					</tr>
					<tr>
					 	<td>消费时间：</td>
						<td><input type="text" onFocus="WdatePicker({onpicked:function(){receiveType.focus();queryDay();},dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-{%d}'})" 
						class="Wdate" size="10" maxlength="20" value="" id="consumprionTime" name="consumprionTime" readonly="readonly"></td>
						<td>消费人：</td>
						<td><input id="consumptionOP" name="consumptionOP" style="width:108px" value=""/></td>
					</tr>
					<tr>
						<td>消费金额：</td>
						<td>
							<input id="consumpAmount" name="consumpAmount" onkeyup="value=value.replace(/[^\d\.]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d\.]/g,'')" style="width:108px" value=""/>
						</td>
						<td>备注：</td>
						<td>
							<textarea name="note" id="note" maxlength="20" rows="3"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<input type="button" id="btnSave" name="btnSave" class="btn_create" value="保存" onclick="saveConsumption();">
							<input type="button" class="btn_cancel" name="btnCancel" id="btnCancel" value="返回" onclick="jumpBlack();">
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
</body>
</html>