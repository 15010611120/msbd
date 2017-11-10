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


<title>产品列表</title>
<script type="text/javascript">

function jumpBlack(){
	parent.window.location="/loginAction/productListJump";
	closewin("winPanel");
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
	<div class="frameTitle">产品添加 (っ´Ι`)っ</div>
		<form name="frmAdd" id="frmAdd" method="post" action="saveProduct" enctype="multipart/form-data" >
			<fieldset style="padding:1 5px 0 5px;margin-bottom:5px;font-weight:100;weight:100%;margin-top:10px">
			<legend><span style="font-size:14px"> </span></legend>
				<table>
					<tr>
						<td>产品名称：</td>
						<td><input id="productName" name="productName" style="width:108px" value=""/></td>
						<td>产品类型：</td>
						<td><input id="productType" name="productType" style="width:108px" value="${name}"/></td>
					</tr>
					<tr><td>入库时间：</td>
						<td><input type="text" onFocus="var checkOutDate=$dp.$('checkOutDate');WdatePicker({onpicked:function(){receiveType.focus();queryDay();},dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-{%d}'})" 
						class="Wdate" size="10" maxlength="20" value="${ checkInDateVo }" id="checkInDate" name="checkInDate" readonly="readonly"></td>
						<td>出库时间</td>
						<td><input type="text" onFocus="WdatePicker({onpicked:function(){receiveType.focus();queryDay();},dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'checkInDate\')}'})" class="Wdate" size="10" maxlength="20" value="${ checkOutDateVo }" id="checkOutDate" name="checkOutDate" readonly="readonly"></td>
					</tr>
					<tr>
						<td>操作人：</td>
						<td><input id="operator" name="operator" style="width:108px" value=""/></td>
						<td>备注：</td>
						<td>
							<textarea name="productNote" id="productNote" maxlength="20" rows="3" cols="16"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<input type="button" id="saveProduct" class="btn_create" value="保存"/>
							<input type="button" class="btn_cancel" name="btnCancel" id="btnCancel" value="返回" onclick="jumpBlack();">
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
</body>
</html>