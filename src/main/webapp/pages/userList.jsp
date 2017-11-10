<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/yxdWin.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/plugin/extjs/resources/css/ext-all.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/extjs/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugin/extjs/ext-all.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	function query(){
		var 
		window.location.href="";
	}
</script>
</head>
<body>
	<div>
	<div class="frameTitle">我是用户列表 (っ´Ι`)っ</div>
		<fieldset style="padding:0 5px 0 5px;margin-bottom:5px;font-weight:100;weight:100%;margin-top:10px">
		<legend><span style="font-size:14px">用户列表</span></legend>
			<table width="100%" border="0" cellspacing="1" cellpadding="3" class="tableNormal">
				
				<tr>
					<td style="text-align: right;">用户姓名:</td>
					<td>
						<input type="text" id="userName" name="userName" readonly="readonly" onclick="" value="杨晓东" placeholder="点击选择服务部门" />
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">职务归属:</td>
					<td>
						<input type="text" id="serviceDeptName" name="serviceDeptName" readonly="readonly" onclick="" value="业务开发部" placeholder="点击选择服务部门" />
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">用户手机:</td>
					<td>
						<input type="text" id="userPhone" name=""userPhone"" onkeyup="clearNoNum(this)" onblur="clearNoNum(this);" value="15010611110" readonly="readonly" placeholder="" />
					</td>
				</tr>
				<tr>
					<td style="text-align: right;"> 备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:</td>
					<td>
						<textarea name="tdNote" id="tdNote" maxlength="20" rows="3" cols="22"></textarea>
					</td>
				</tr>
				<tr>
				<td>&nbsp;</td>
				<td colspan="3">
					<input type="button" class="btn_submit" name="btnSubmit" id="btnSubmit" value="提交" onclick="saveGu()">
					<input type="button" class="btn_cancel" name="btnCancel" id="btnCancel" value="放弃" onclick="toUrl('findGutrainingDepositRadix');" >
				</td>
			</tr>
			</table>
		</fieldset>
	</div>
</body>
</html>