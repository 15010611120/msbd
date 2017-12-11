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


<title>添加薪资</title>
<script type="text/javascript">

function jumpBlack(){
	parent.window.location="${pageContext.request.contextPath}/salaryJump";
}

function addOrUpdate(){
	var consumptionName= $("#consumptionName").val();
	$.ajax({
		type:"POST",
		url:"${pageContext.request.contextPath}/addOrUpdate",
		data: $("#frmAdd").serialize(),
		success: function(result) {
			 var obj = jQuery.parseJSON(result);
				if (obj.success) {
				alert("薪资记录保存成功！！！");
				parent.window.location="${pageContext.request.contextPath}/querySalarySumList";
				closewin("winPanel");
				//window.history.go(-1);
			}else {
				alert("薪资记录保存失败！");
				parent.window.location="${pageContext.request.contextPath}/querySalarySumList";
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
	<div class="frameTitle">添加薪资记录 (っ´Ι`)っ</div>
		<form name="frmAdd" id="frmAdd" method="post" action="/addOrUpdateConsumption" enctype="multipart/form-data" >
			<fieldset style="padding:1 5px 0 5px;margin-bottom:5px;font-weight:100;weight:100%;margin-top:10px">
			<legend><span style="font-size:14px"> </span></legend>
				<table>
					<tr>
						<td>基本薪资：</td>
						<td><input id="basicSalary" name="basicSalary" onkeyup="value=value.replace(/[^\d\.]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d\.]/g,'')"  style="width:108px" value=""/></td>
						<td>公&nbsp;&nbsp;积&nbsp;&nbsp;金：</td>
						<td><input id="accumulationFund" name="accumulationFund" onkeyup="value=value.replace(/[^\d\.]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d\.]/g,'')"  style="width:108px" value="${name}"/></td>
					</tr>
					<tr>
					 	<td>薪资时间：</td>
						<td><input type="text" onFocus="WdatePicker({onpicked:function(){receiveType.focus();queryDay();},dateFmt:'yyyy-MM-dd',minDate:'%y-%M'})" 
						class="Wdate" size="10" maxlength="20" value="" id="salaryTime" name="salaryTime" readonly="readonly"></td>
						<td>医&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;保：</td>
						<td><input id="medicalInsurance" name="medicalInsurance" style="width:108px" value=""/></td>
					</tr>
					<tr>
						<td>人员名称：</td>
						<td>
							<input id="personal" name="personal" style="width:108px" value=""/>
						</td>
						<td>公司名称：</td>
						<td>
							<input id="company" name="company" style="width:108px" value=""/>
						</td>
					</tr>
					<tr>
						<td>补&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;助：</td>
						<td>
							<input id="subsidy" name="subsidy" onkeyup="value=value.replace(/[^\d\.]/g,'')" onafterpaste="this.value=this.value.replace(/[^\d\.]/g,'')" style="width:108px" value=""/>
						</td>
						<td>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
						<td>
							<textarea name="note" id="note" maxlength="20" rows="3"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<input type="button" id="btnSave" name="btnSave" class="btn_create" value="保存" onclick="addOrUpdate();">
							<input type="button" class="btn_cancel" name="btnCancel" id="btnCancel" value="返回" onclick="jumpBlack();">
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
</body>
</html>