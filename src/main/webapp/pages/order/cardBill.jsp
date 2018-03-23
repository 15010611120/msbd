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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tableNoraml.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugin/extjs/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugin/extjs/ext-all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/yxdWin.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/popWindow.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugin/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/yangxiaodong.js"></script>

<title>信用卡账单列表</title>
<script type="text/javascript">
<%-- var session_email = '<%=session.getAttribute("email")%>';
if(session_email == "null"){
	window.top.location="${pageContext.request.contextPath}/login";
}
 --%>
//初始化复选框
$(document).ready( function (){
	createLoadPromptMaskLayer('body','maskLayer','maskLayerPrompt','数据正在查询中，请稍后...','font_yh16');
	var selectid="${mapP['selectId']}";
	$("input[name=check]").each(function(){
		var sid = $(this).val().trim();
		if(selectid.indexOf(sid)!= -1){
			$(this).attr("checked",true);
		}
	});
});


//查询
function parsingCreditCardBills(){
	controlLoadPromptMaskLayer('#maskLayer','#maskLayerPrompt');
	var usersName = $("#usersName").val();
	var pwds = $("#pwds").val();
	window.location.href="${pageContext.request.contextPath}/parsingCreditCardBills?usersName="+usersName+"&pwds="+pwds;
}
</script>
</head>
<body>
	<div class="frameBody">
	<div class="frameTitle">信用卡账单列表 (っ´Ι`)っ</div>
	<fieldset style="padding:1 5px 0 5px;margin-bottom:5px;font-weight:100;weight:100%;margin-top:10px">
	<legend><span style="font-size:14px"></span></legend>
		<table>
			<tr>
				<td>用户名：</td>
				<td><input id="usersName" name="usersName" value="${ userNames }"/></td>
				<td>密码：</td>
				<td><input type="password" id="pwds" name="pwds" value="${ pwds }"/></td>
			</tr>
		</table>
		<table>
		<tr>
			<td style="text-align: left;">
				<input type="submit" id="btnSearch" name="btnSearch" onclick="parsingCreditCardBills()" value="导入账单" class="btn_query" />
				<input type="button" id="exportProduct" name="exportProduct" value="导出" class="btn_query" onclick="exportProduct()" />
				<input type="button" id="btnClear" name="btnClear" value="重置" class="btn_cancel" onclick="changeClear()" />
			</td>
		</tr>
	</table>
	</fieldset>
	<br>
	<table width="80%" border="1" cellspacing="1" cellpadding="2" class="tableNormal">
		<thead>
			<tr>
				<th width="1%" style="text-align: center;">
					<input type="checkbox" id="allCheck" name="allCheck" onclick="selectAll('allCheck','check')" />
				</th> 
				<th>序号</th>
				<th>银行名称</th>
				<th>账单日</th>
				<th>本期应还款</th>
				<th>最低还款</th>
				<th>还款日</th>
				<th>信用额度</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.list}" var="p" varStatus="vs">
					<tr>
						<td width="1%" style="text-align: center;">
							<input type="checkbox" id="check" name="check" onclick="setSelectAll()" value="${p.bankName}"/></td>
						<td>${vs.index+1}</td>
						<td>${p.bankName}</td>
						<td>${p.billDay}</td>
						<td>${p.bill}</td>
						<td>${p.minRepay}</td>
						<td>${p.repayDay}</td>
						<td>${p.quota}</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="width: 80%;" class="framePage" >${pages}</div>
	<table width="80%" border="0" cellspacing="1" cellpadding="3" class="tableNormal">
		<tr>
			<td style="text-align: left;">
				<input type="button" name="btnAdd" id="btnAdd" class="btn_create" onclick="addOrUpdatePage()" value="新增" />
				<input type="button" name="btnUpdate" id="btnUpdate" class="btn_create"  onclick="" value="修改" />
				<input type="button" name="btnDel" id="btnDel" class="btn_delete" onclick="" value="删除" />
				<input type="button" name="btnLog" id="btnLog" class="btn_act" onclick="" value="日志" />
			</td>
		</tr>
	</table>
	</div>
</body>
</html>