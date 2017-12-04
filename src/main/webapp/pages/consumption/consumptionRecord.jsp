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

<title>消费记录</title>
<script type="text/javascript">
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

//消费查询
function consumptionListQuery(){
	var consumptionName= $("#consumptionName").val();
	var consumptionType= $("#consumptionType").val();
	var consumptionStarTime= $("#consumptionStarTime").val();
	var consumptionEndTime= $("#consumptionEndTime").val();
	var consumptionOP = $("#consumptionOP").val();
	window.location.href="/consumptionListQuery?consumptionName="+consumptionName
			+"&consumptionType="+consumptionType
			+"&consumptionStarTime="+consumptionStarTime
			+"&consumptionEndTime="+consumptionEndTime
			+"&consumptionOP="+consumptionOP;
}

function clearAll(){
	$("#consumptionName").val("");
	$("#consumptionType").val("");
	$("#consumptionStarTime").val("");
	$("#consumptionEndTime").val("");
	$("#consumptionOP").val("");
}
//添加
function addOrUpConsumption(){
	var url = "/addConsumptionJump"
	showPopWinNoBtn(url,'添加消费记录','50%',260);
}
</script>
</head>
<body>
	<div class="frameBody">
	<div class="frameTitle">个人消费记录 (っ´Ι`)っ</div>
	<fieldset style="padding:1 5px 0 5px;margin-bottom:5px;font-weight:100;weight:100%;margin-top:10px">
	<legend><span style="font-size:14px"> </span></legend>
		<table>
			<tr>
				<td>商品名称：</td>
				<%-- ${mapP['productName']} 获取map的值 --%>
				<td><input id="consumptionName" name="consumptionName" value="${mapP['consumptionName']}"/></td>
				<td>商品类型：</td>
				<td><input id="consumptionType" name="consumptionType" value="${mapP['consumptionType']}"/></td>
				<td>消费时间：</td>
				<td><input type="text" onFocus="var consumprionEndTime=$dp.$('consumptionEndTime');WdatePicker({onpicked:function(){receiveType.focus();queryDay();},dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-{%d}'})" class="Wdate" size="10" maxlength="20" value="${mapP['consumptionStarTime']}" id="consumptionStarTime" name="consumptionStarTime" readonly="readonly"></td>
				<td>-</td>
				<td><input type="text" onFocus="WdatePicker({onpicked:function(){receiveType.focus();queryDay();},dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'consumptionStarTime\')}'})" class="Wdate" size="10" maxlength="20" value="${mapP['consumptionEndTime']}" id="consumptionEndTime" name="consumptionEndTime" readonly="readonly"></td>
				<td>消费人：</td>
				<td><input id="consumptionOP" name="consumptionOP" value="${mapP['consumptionOP']}"/></td>
			</tr>
			<tr>
				<td>
				<input type="submit" id="btnSearch" name="btnSearch" onclick="consumprionListQuery()" value="开始查询" class="btn_query" />
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
				<th>商品名称</th>
				<th>商品类型</th>
				<th>消费时间</th>
				<th>消费人</th>
				<th>消费金额</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.list}" var="p" varStatus="vs">
					<tr>
						<td width="3%" style="text-align: center;">
							<input type="checkbox" id="check" name="check" onclick="setSelectAll()" value="${p.id}"/></td>
						<td>${vs.index+1}</td>
						<td>${p.consumptionName}</td>
						<td>${p.consumptionType}</td>
						<td>${p.consumptionTime}</td>
						<td>${p.consumptionOP}</td>
						<td>${p.consumpAmount}</td>
						<td>${p.note}</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="width: 100%;" class="framePage" >${pages}</div>
	<table width="100%" border="0" cellspacing="1" cellpadding="3"class="tableNormal">
		<tr>
			<td style="text-align: left;">
				<input type="button" name="btnAdd" id="btnAdd" class="btn_create" onclick="addOrUpConsumption()" value="新增消费" />
				<input type="button" name="btnUpdate" id="btnUpdate" class="btn_create"  onclick="" value="修改消费" />
				<input type="button" name="btnLog" id="btnLog" class="btn_act" onclick="" value="操作日志" />
			</td>
		</tr>
	</table>
	</div>
</body>
</html>