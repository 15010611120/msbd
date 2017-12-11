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

<title>薪资记录</title>
<script type="text/javascript">
var session_email = '<%=session.getAttribute("email")%>';
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

//工资发放查询
function querySalarySumList(){
	var basicSalary= $("#basicSalary").val();
	var medicalInsurance= $("#medicalInsurance").val();
	var salaryStarTime= $("#salaryStarTime").val();
	var salaryEndTime= $("#salaryEndTime").val();
	var personal = $("#personal").val();
	window.location.href="${pageContext.request.contextPath}/querySalarySumList?basicSalary="+basicSalary
			+"&medicalInsurance="+medicalInsurance
			+"&salaryStarTime="+salaryStarTime
			+"&salaryEndTime="+salaryEndTime
			+"&personal="+personal;
}

function clearAll(){
	$("#consumptionName").val("");
	$("#consumptionType").val("");
	$("#consumptionStarTime").val("");
	$("#consumptionEndTime").val("");
	$("#consumptionOP").val("");
}
//添加
function addAndUpdateSalary(){
	var url = "${pageContext.request.contextPath}/addAndUpdateSalary"
	showPopWinNoBtn(url,'添加薪资记录','50%',260);
}
</script>
</head>
<body>
	<div class="frameBody">
	<div class="frameTitle">薪资发放记录 (っ´Ι`)っ</div>
	<fieldset style="padding:1 5px 0 5px;margin-bottom:5px;font-weight:100;weight:100%;margin-top:10px">
	<legend><span style="font-size:14px"> </span></legend>
		<table>
			<tr>
				<td>基本薪资：</td>
				<%-- ${mapP['productName']} 获取map的值 --%>
				<td><input id="basicSalary" name="basicSalary" value="${mapP['basicSalary']}"/></td>
				<td>医保：</td>
				<td><input id="medicalInsurance" name="medicalInsurance" value="${mapP['medicalInsurance']}"/></td>
				<td>发放时间：</td>
				<td><input type="text" onFocus="var salaryEndTime=$dp.$('salaryEndTime');WdatePicker({onpicked:function(){receiveType.focus();queryDay();},dateFmt:'yyyy-MM'})" class="Wdate" size="10" maxlength="20" value="${mapP['salaryStarTime']}" id="salaryStarTime" name="salaryStarTime" readonly="readonly"></td>
				<td>-</td>
				<td><input type="text" onFocus="WdatePicker({onpicked:function(){receiveType.focus();queryDay();},dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'salaryStarTime\')}'})" class="Wdate" size="10" maxlength="20" value="${mapP['salaryEndTime']}" id="salaryEndTime" name="salaryEndTime" readonly="readonly"></td>
				<td>人员名称：</td>
				<td><input id="personal" name="personal" value="${mapP['personal']}"/></td>
			</tr>
			<tr>
				<td>
				<input type="submit" id="btnSearch" name="btnSearch" onclick="querySalarySumList()" value="开始查询" class="btn_query" />
				</td>
				<td>
				<input type="button" id="exportProduct" name="exportProduct" value="导出" class="btn_query" onclick="exportProduct()" />
				<input type="button" id="btnClear" name="btnClear" value="重置" class="btn_cancel" onclick="clearAll()" />
				</td>
			</tr>
		</table>
	</fieldset>
	<br>
	<table width="100%" border="1" cellspacing="1" cellpadding="2" class="tableNormal">
		<thead>
			<tr>
				<th width="3%" style="text-align: center;">
					<input type="checkbox" id="allCheck" name="allCheck" onclick="selectAll()" />
				</th> 
				<th>序号</th>
				<th>发放时间</th>
				<th>基本薪资</th>
				<th>公积金</th>
				<th>医保</th>
				<th>补助</th>
				<th>人员名称</th>
				<th>公司名称</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.list}" var="p" varStatus="vs">
					<tr>
						<td width="3%" style="text-align: center;">
							<input type="checkbox" id="check" name="check" onclick="setSelectAll()" value="${p.id}"/></td>
						<td>${vs.index+1}</td>
						<td>${p.salaryTime}</td>
						<td>${p.basicSalary}</td>
						<td>${p.accumulationFund}</td>
						<td>${p.medicalInsurance}</td>
						<td>${p.subsidy}</td>
						<td>${p.personal}</td>
						<td>${p.company}</td>
						<td>${p.note}</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="width: 100%;" class="framePage" >${pages}</div>
	<table width="100%" border="0" cellspacing="1" cellpadding="3"class="tableNormal">
		<tr>
			<td style="text-align: left;">
				<input type="button" name="btnAdd" id="btnAdd" class="btn_create" onclick="addAndUpdateSalary()" value="新增薪资" />
				<input type="button" name="btnUpdate" id="btnUpdate" class="btn_create"  onclick="" value="修改薪资" />
				<input type="button" name="btnLog" id="btnLog" class="btn_act" onclick="" value="操作日志" />
			</td>
		</tr>
	</table>
	</div>
</body>
</html>