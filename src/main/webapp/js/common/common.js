/* getQuery */
String.prototype.getQuery = function(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = this.substr(this.indexOf("\?") + 1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
};

// 扩展，去除字符串(序列)中的指定文本
String.prototype.removeString = function(removeText, splitText) {
	var s = this;
	splitText = (splitText == undefined || splitText == null || splitText == '') ? ',' : splitText;
	var __removeText = removeText + splitText;
	var _removeText = splitText + removeText;
	var __index = s.indexOf(__removeText);
	var _index = s.indexOf(_removeText);

	if (__index != -1) s = s.slice(0, __index) + s.slice(__index + __removeText.length);
	else if (_index != -1) s = s.slice(0, _index) + s.slice(_index + _removeText.length);
	else if (s.length > 0) s = '';
	return s;
};


/*检查包含中文字符串长度，是否超出指定maxLength*/
String.prototype.checkCNLength = function(maxLength){
    if(this.replace(/[^\x00-\xff]/g,"01").length>maxLength){
        return false;
    }else {
        return true;
    }
}

// 获取下一个节点
function nextNode(n) {
	var x = n.nextSibling;
	while (x.nodeType != 1) {
		x = x.nextSibling;
	}
	return x;
}

// 获取地址传参
function getQueryString(name) {
	try {
		return (new String(window.location).getQuery(name));
	} catch (e) {
	}
}
// 简写
function $$QS(name) {
	return (new String(window.location).getQuery(name));
}

/*
 * 收缩菜单 isDisp为none，返回显示菜单 obj为获取页面对象名称
 */
function menuDisplay(isDisp, obj) {
	if (isDisp == 'none' || isDisp == '+' || isDisp == undefined || isDisp == "") {
		eval("document.getElementById('" + obj + "').style.display=\"block\";");
	} else {
		eval("document.getElementById('" + obj + "').style.display=\"none\";");
	}
}

function hideElement(isDisp, obj) {
	if (isDisp == 'none' || isDisp == '+' || isDisp == undefined || isDisp == "") {
		obj.style.display = 'block';
	} else {
		obj.style.display = 'none';
	}
}

// 去左空格
function ltrim(s) {
	return s.replace(/^\s*/, "");
}
// 去右空格
function rtrim(s) {
	return s.replace(/\s*$/, "");
}
// 去两边空格
function trim(s) {
	return s.replace(/(^\s*)|(\s*$)/g, "");
}

// getSelect itemValue
function getSelectValue(o) {
	for ( var i = 0; i < o.length; i++)
		if (o[i].selected) return o[i].value;
	return 0;
}

// getCheckBox selected options Text
function getCheckBoxOptionsText(o) {
	var strID = '';
	for ( var i = 0; i < o.length; i++) {
		if (o[i].checked) {
			strID = strID + o[i].text + ',';
		}
	}
	return rCutString(strID, 1);
}

// getCheckBox selected itemValue
function getCheckBoxValues(o) {
	var strID = '';
	for ( var i = 0; i < o.length; i++) {
		if (o[i].checked) {
			strID = strID + o[i].value + ',';
		}
	}
	return rCutString(strID, 1);
}

// get input value
function getFormInputValues(element) {
	var strID = '', els = document.getElementsByName(element);
	for ( var i = 0; i < els.length; i++) {
		if (els[i].value != 0) strID = strID + els[i].value + ',';
		else strID = strID + '0' + ',';
	}
	return rCutString(strID, 1);
}

// cut right string
function rCutString(str, len) {
	var sLen = str.length;
	if (str == '' || str == null) {
		return null;
	} else {
		if (len < 0) len = sLen;
		if (isNaN(len)) len = sLen;
		return str.substr(0, sLen - len);
	}
}

// window open
function openNew(url, width, height) {
	if (width == undefined || width == null) width = 700;
	if (height == undefined || height == null) height = 550;
	window.open(url, '_blank', 'width=' + width + ', height=' + height + ',left=' + (window.screen.width - width) / 2 + ',top=' + (window.screen.height - height) / 2) + ',scrollbars=yes';
}

// window.location to url
function toUrl(strUrl, target) {
	if (strUrl != null && strUrl != "") {
		if (target == '_blank') window.open(strUrl, target);
		else if (target != null) eval(target).location = strUrl;
		else window.location = strUrl;
	}
}

// 初始化下拉列表框(进行匹配选中)
function initDropDownList(strSltTagName, strMatchValue) {
	var objOptSchBy = $(strSltTagName).getElementsByTagName("option");
	for ( var i = 0; i < objOptSchBy.length; i++) {
		if (objOptSchBy[i].getAttribute("value") == strMatchValue) objOptSchBy[i].setAttribute("selected", true);
	}
}

// 表格操作变量
var list = {
	// 方法：selectRow()——选择一行
	// 参数：objTr： 当前行对象(引用类型)
	// objLastTr：上一个被选中的<TR>(引用类型)
	// strCss： 行被替换的样式(字符串类型)
	// isApp： 是否应用样式替换(布尔类型)
	selectRow : function(objTr, objLastTr, strCss, isApp) {
		var objInput = objTr.cells[0].getElementsByTagName("input")[0];
		if (objInput.type.toLowerCase() == "radio") {
			if ((objInput.getAttribute("type") == "radio") | (objInput.getAttribute("type") == "checkbox")) {
				objInput.setAttribute("checked", true);
			}
		} else if (objInput.type.toLowerCase() == "checkbox") {
			// objInput.getAttribute("checked") == true ?
			// objInput.setAttribute("checked",false) :
			// objInput.setAttribute("checked",true);
			// objInput.setAttribute("checked",true);
		}
		// 改变行样式
		if (isApp) {
			if (objLastTr != null) objLastTr.className = "";
			objTr.className = strCss;
			return objTr;
		}
	},

	// 方法：selectMultRow()——选择一行或多行
	// 参数：objChkBox ：当前<input type="checkbox">对象(引用类型)
	// strCss ：行被替换的样式(字符串类型)
	// isApp ：是否应用样式替换(布尔类型)
	selectMultRow : function(objChkBox, strCss, isApp) {
		var objParent = objChkBox.parentNode;

		while (objParent.nodeName.toLowerCase() != "tr") {
			objParent = objParent.parentNode;
		}

		if (objChkBox.type.toLowerCase() == "radio") {
			// if((objInput.getAttribute("type") == "radio") |
			// (objInput.getAttribute("type") == "checkbox")) {
			// objInput.setAttribute("checked",true);
			// }
		} else if (objChkBox.type.toLowerCase() == "checkbox") {
			// objInput.getAttribute("checked") == true ?
			// objInput.setAttribute("checked",false) :
			// objInput.setAttribute("checked",true);
			// objInput.setAttribute("checked",true);
			// 改变行样式
			if (isApp) {
				if (objChkBox.checked == true) {
					objParent.className = strCss;
				} else {
					objParent.className = "";
				}
			}
		}
	},

	// 方 法：selectAll()——选择所有行
	// 参 数：strTagName ：需要被判断的标签名(例：radio / checkbox)
	// bFlag ：是否选中标识
	selectAll : function(strTagName, bFlag) {
		// alert($("checkAll").getAttribute("checked"));
		var objs = document.getElementsByName(strTagName);
		if (bFlag) {
			for ( var i = 0; i < objs.length; i++) {
				objs[i].setAttribute("checked", "checked");
			}
		} else {
			for ( var i = 0; i < objs.length; i++) {
				objs[i].setAttribute("checked", "");
			}
		}
	},

	// 方 法：hasOneSelected()——是否仅有一行被选中
	// 参 数：strTagName ：需要被判断的标签名(例：radio / checkbox)
	// strMsg ：少于或多于一行时的提示
	// 返回值：选中值 / 没选中(false)
	hasOneSelected : function(strTagName, strMsg) {
		var counter = 0;
		var arrObjTag = document.getElementsByName(strTagName);
		var objTagTemp = "";
		for ( var i = 0; i < arrObjTag.length; i++) {
			if (arrObjTag[i].getAttribute("checked") == true) {
				counter++;
				objTagTemp = arrObjTag[i].getAttribute("value");
			}
		}
		if (counter == 1) {
			return objTagTemp;
		} else {
			if (strMsg != undefined) alert(strMsg);
			return false;
		}
	},

	// 方 法：getMultSelected()——获取多行选择
	// 参 数：strTagName ：需要被判断的标签名(例：radio / checkbox)
	// strMsg ：没有选择行时的提示
	// 返回值：以逗号分割的字符串
	getMultSelected : function(strTagName, strMsg) {
		var counter = 0;
		var arrObjTag = document.getElementsByName(strTagName);
		var objTagTemp = "";

		for ( var i = 0; i < arrObjTag.length; i++) {
			if (arrObjTag[i].getAttribute("checked") == true) {
				counter++;
				objTagTemp += arrObjTag[i].getAttribute("value") + ",";
			}
		}

		if (counter >= 1) {
			return objTagTemp.substr(0, objTagTemp.length - 1);
		} else {
			if (strMsg != undefined) alert(strMsg);
			return false;
		}
	},

	// 方法：dispTrBgColor()——显示行背景色
	// 参数：objTr：当前行对象；strOldTrCss：原有样式；strNewTrCss：新样式；isDispBgColor：是否显示背景色
	dispTrBgColor : function(objTr, strOldTrCss, strNewTrCss, isDispBgColor) {
		// 显示背景色
		if (isDispBgColor) {
			if (objTr.className != strOldTrCss) {
				objTr.className = strNewTrCss;
			}
		}
		// 移出背景色
		if (!isDispBgColor) {
			if (objTr.className != strOldTrCss) {
				objTr.className = "";
			}
		}
	},

	// 判断Radio list 是否被选择
	HasCheckedByRadioList : function(elementName) {
		var isChecked = false;
		var els = document.getElementsByName(elementName);
		for ( var i = 0; i < els.length; i++) {
			if (els[i].checked) {
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
};

// DOM 文档对象属性
var DOMProperty = {

	docNode : document.documentElement,

	getDocWidth : function() {
		return this.docNode.clientWidth;
	},

	getDocHeight : function() {
		return this.docNode.clientHeight;
	},

	autoElementSize : function(element, wOffset, hOffset) {
		try {
			var wHeight = this.getDocHeight();
			if (hOffset != null && hOffset != '') $(element).style.height = wHeight - hOffset;

			var wWidth = this.getDocWidth();
			if (wOffset != null && wOffset != '') $(element).style.width = wWidth - wOffset;
		} catch (ee) {
		}
	},

	getLeft : function(element) {
		return Position.page(element).toString().split(',')[0];
	},

	getTop : function(element) {
		return Position.page(element).toString().split(',')[1];
	}
};

// 父窗体重载
function reloadParentWin(strReg) {
	var objParent = window.opener;
	var regUrl = new RegExp(".+" + strReg + ".*", "i");
	try {
		if (window.opener) {
			if (new String(window.opener.location).search(regUrl) == 0) {
				objParent.location = objParent.location;
			}
		}
	} catch (e) {
	}
}

// 方法：formatDate()：格式化日期
// 参数：strDate ：原始日期
// separator ：分隔符(返回用)
// 返回值："yyyy-mm-dd"形式的日期
function formatDate(strDate, separator) {
	var sYear, sMonth, sDate;
	if (separator == null) {
		separator = "-";
	}
	if (/^(\d{2,})-(\d{1,})-(\d{1,})$/.test(strDate)) {
		sYear = RegExp.$1.length != 4 ? "00" + RegExp.$1 : RegExp.$1;
		sMonth = RegExp.$2.length != 2 ? "0" + RegExp.$2 : RegExp.$2;
		sDate = RegExp.$3.length != 2 ? "0" + RegExp.$3 : RegExp.$3;
	} else {
		sYear = (strDate.getYear().toString().length == 2) ? "20" + strDate.getYear().toString() : strDate.getYear().toString();
		sMonth = (strDate.getMonth().toString().length == 1) ? "0" + (strDate.getMonth() + 1).toString() : (strDate.getMonth() + 1).toString();
		sDate = (strDate.getDate().toString().length == 1) ? "0" + strDate.getDate() : strDate.getDate();
	}
	return sYear + separator + sMonth + separator + sDate;
}

//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
var o = {
   "M+": this.getMonth() + 1, //月份 
   "d+": this.getDate(), //日 
   "h+": this.getHours(), //小时 
   "m+": this.getMinutes(), //分 
   "s+": this.getSeconds(), //秒 
   "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
   "S": this.getMilliseconds() //毫秒 
};
if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
for (var k in o)
if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
return fmt;
}

//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 

// 方法：addDate()——对象Date的扩展，获取指定时间间隔后的时间
// 参数：strDate ：原始时间
// strInterval：表示要添加的时间间隔类型(ss-秒，mi-分，hh-时，dd-日，ww-周，mm-月，yy-年)
// iNumber ：间隔大小(整型)
Date.prototype.addDate = function(strDate, strInterval, iNumber) {
	var objDate = new Date(strDate.replace(/-/g, "/"));

	var iSecond = 1000; // 秒
	var iMinute = iSecond * 60; // 分
	var iHour = iMinute * 60; // 时
	var iDate = iHour * 24; // 天
	var iWeek = iDate * 7; // 周
	var number = parseInt(iNumber); // 间隔数
	var iTime; // 毫秒

	var strYear, strMonth, strDate;

	if (isNaN(objDate)) {
		objDate = new Date();
	}
	iTime = Date.parse(objDate);

	switch (strInterval) {
		case "ss":
			objDate = new Date(iTime + iSecond * number);
			break;
		case "mi":
			objDate = new Date(iTime + iMinute * number);
			break;
		case "hh":
			objDate = new Date(iTime + iHour * number);
			break;
		case "dd":
			objDate = new Date(iTime + iDate * number);
			break;
		case "ww":
			objDate = new Date(iTime + iWeek * number);
			break;
		case "mm":
			objDate = new Date(objDate.getFullYear(), objDate.getMonth() + number, objDate.getDate(), objDate.getHours(), objDate.getMinutes(), objDate.getSeconds());
			break;
		case "yy":
			objDate = new Date(objDate.getFullYear() + number, objDate.getMonth(), objDate.getDate(), objDate.getHours(), objDate.getMinutes(), objDate.getSeconds());
			break;
	}
	strYear = objDate.getFullYear().toString();
	strMonth = (objDate.getMonth() + 1).toString();
	strDate = objDate.getDate().toString();
	// alert(strYear + "-" + strMonth + "-" + strDate);
	return strYear + "-" + strMonth + "-" + strDate;
};
// 返回：日期指定日期
// 参数：srcDate 为原始日期; addDayNumber 为天数
Date.prototype.AddDate = function(srcDate, addDayNumber) {
	var year, month, day, newDate;
	newDate = srcDate.replace(/-/g, '/');
	newDate = new Date(new Date(newDate).getTime() + addDayNumber * 24 * 60 * 60 * 1000);
	year = newDate.getFullYear();
	month = newDate.getMonth() + 1;
	day = newDate.getDate();
	if (month < 10) month = '0' + month;
	if (day < 10) day = '0' + day;
	return (year + '-' + month + '-' + day);
};

// 自动适应高度
// 参数：iHeadHeight ：上部高度
// iBottomHeight ：底部高度
// iOffset ：偏移量
function autoFitHeight(iHeadHeight, iBottomHeight, strTag, iOffset) {
	var iContentHeight = document.documentElement.clientHeight - iHeadHeight - iBottomHeight - iOffset; // 内容部分高度

	$(strTag).style.overflow = "auto";
	$(strTag).style.height = iContentHeight + "px";
}

// 行选中事件
var _select_saveNode = '';
var RowEvent = {

	// 全选与反选
	selectAllLine : function(elementName) {
		RowEvent.selectAll(elementName);
	},

	// 选中
	selectThisLine : function(tdNode, obj, cssStyle) {
		RowEvent.selectThis(tdNode, obj, cssStyle);
	},

	// 选中行
	selectedLine : function(lineNode, cssName) {
		if (_select_saveNode != '') _select_saveNode.className = '';
		if (cssName == null || cssName == '') cssName = 'listSelected';
		_select_saveNode = lineNode;
		lineNode.className = cssName;
	},

	selectAll : function(elementName, cssStyle) {
		var elements = document.getElementsByName(elementName);
		for ( var i = 0; i < elements.length; i++) {
			elements[i].checked = event.srcElement.checked;
			RowEvent.selectThis(elements[i].parentNode, elements[i], cssStyle);
		}
	},

	selectThis : function(tdNode, obj, cssStyle) {
		var trNode = tdNode.parentNode;
		if (cssStyle == null || cssStyle == '') cssStyle = 'listSelected';
		if (obj.checked) trNode.className = cssStyle;
		else trNode.className = '';
	}
};

/**
 * 公共列表操作方法，增删改查等
 */
var Act = {

	/**
	 * 列表操作方法，根据输入参数，拼接动作URL
	 * 
	 * @param action ACTION或URL名称，例如：loadList
	 * @param keyName 标识名称并为首个参数名称，例如：id
	 * @param keyValue 标识值，例如：
	 * @param urlOther 其他参数，例如：&a=1&b=2
	 * @param target 是否新开窗口， 新开：“_blank”, 不新开：""
	 */
	op : function(action, keyName, urlOther, target) {
		var url = "";
		var keyValue = Act.getId();
		keyName = (keyName == null) ? "" : keyName;
		urlOther = (urlOther == null) ? "" : urlOther;
		if (keyName != "" && keyValue == "0") {
			alert("请先选择项目，再进行操作。");
			return false;
		}
		if (keyName != "") {
			url += "?" + keyName + "=" + keyValue;
			if (urlOther != "") {
				url += urlOther;
			}
		} else {
			if (urlOther != "") {
				url += "?t=0" + urlOther;
			}
		}
		url = action + url;
		toUrl(url, target);
	},

	/**
	 * 获取行标识 GET 隐含域的值
	 * 
	 * @returns 隐含域的值
	 */
	getId : function() {
		var keyId = document.getElementById("_pageKeyId");
		if (keyId == undefined || keyId == null) {
			return "0";
		} else {
			return keyId.value.toString();
		}
	},

	/**
	 * 设定行标识 SET 到隐含域
	 * 
	 * @param value 标识ID
	 */
	setId : function(value) {
		var keyId = document.getElementById("_pageKeyId");
		var hideInput = document.createElement("input");
		if (keyId == undefined || keyId == null) {
			hideInput.setAttribute("id", "_pageKeyId");
			hideInput.setAttribute("name", "_pageKeyId");
			hideInput.setAttribute("type", "hidden");
			hideInput.setAttribute("value", value);
			document.body.appendChild(hideInput);
		} else {
			keyId.value = value;
		}
	},

	/**
	 * 获取行标识 GET 隐含域的值
	 * 
	 * @returns 隐含域的值
	 */
	getName : function() {
		var keyName = document.getElementById("_pageKeyName");
		if (keyName == undefined || keyName == null) {
			return "";
		} else {
			return keyName.value.toString();
		}
	},

	/**
	 * 设定行标识 SET 到隐含域
	 * 
	 * @param value 标识名称
	 */
	setName : function(value) {
		var keyName = document.getElementById("_pageKeyName");
		var hideInput = document.createElement("input");
		if (keyName == undefined || keyName == null) {
			hideInput.setAttribute("id", "_pageKeyName");
			hideInput.setAttribute("name", "_pageKeyName");
			hideInput.setAttribute("type", "hidden");
			hideInput.setAttribute("value", value);
			document.body.appendChild(hideInput);
		} else {
			keyName.value = value;
		}
	},

	/**
	 * 选定并设定行标识到隐含域
	 */
	setLine : function(rowElement, value, name) {
		RowEvent.selectedLine(rowElement);
		Act.setId(value);
		if (name != null && name != '') {
			Act.setName(name);
		}
	}

};

// 加载onload方法
var WinLoad = {
	excute : function(method) {
		window.onload = function() {
			method();
		};
	},
	// 单击input选中内容
	inputSelect : function() {
		var inputs = document.getElementsByTagName('input');
		for ( var i = 0; i < inputs.length; i++) {
			try {
				if (inputs[i].type.toUpperCase() == 'TEXT') {
					inputs[i].onfocus = function() {
						this.select();
					};
				}
			} catch (exx) {
				return;
			}
		}
	}
};
// 保留两位小数四舍五入input文本框限制
var changeTwoDecimal_f = function(floatvar) {
	var f_x = parseFloat(floatvar);
	if (isNaN(f_x)) { return '0.00'; }
	var f_x = Math.round(f_x * 100) / 100;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if (pos_decimal < 0) {
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while (s_x.length <= pos_decimal + 2) {
		s_x += '0';
	}
	return s_x;
};

/**
 * 页面加载动态创建弹出遮罩Loading层 maskLayer
 * 
 * @param objParentElem : 主容器对象 (body)
 * @param objParent : 遮罩层底层元素Id (动态)
 * @param objItem : 遮罩层提示元素层Id (动态)
 * @param objPrompt : 提示语 (动态)
 * @param objPromptStyle: 提示层字体显示样式
 */
function createLoadPromptMaskLayer(objParentElem, objParent, objItem, objPrompt, objPromptStyle) {
	if ($(objParent).length > 0) $(objParent).remove();

	$("<div>", {
		id : objParent,
		"class" : "maskLayerGray"
	}).appendTo($(objParentElem));
	$("<div>", {
		id : objItem,
		"class" : "maskLayerGrayPrompt"
	}).appendTo($('#' + objParent));

	var objLoadImg = '<img src="../images/loading.gif" border="0" hspace="3" align="absmiddle" />&nbsp;';
	var objText = "数据努力加载中......";
	if (objPrompt != '') objText = objPrompt;

	var delVal = 0;
	$('#' + objItem).empty();
	$("<div>", {
		id : 'loadTit',
		html : objLoadImg + objText,
		"class" : objPromptStyle
	}).appendTo($('#' + objItem));
}

/**
 * 绑定显示事件假象Loading数据
 * 
 * @param objParent : 遮罩层底层元素Id (动态)
 * @param objItem : 遮罩层提示元素层Id (动态)
 */
function controlLoadPromptMaskLayer(objParent, objItem) {
	$(objParent).show();
	$(objItem).show();
}

/**
 * 页面加载动态创建弹出图片遮罩
 * @param objImgPath : 入参图片有效地址
 */
function createLoadMaskImgLayer(objImgPath) {
	if ($('#maskImg').length > 0) $('#maskImg').remove();
	if ($('#maskImgLayer-box-table-content').length > 0) $('#maskImgLayer-box-table-content').remove();
	if ($('#maskImgLayer-box-table-icon').length > 0) $('#maskImgLayer-box-table-icon').remove();
	if ($('#maskImgLayer-box-table').length > 0) $('#maskImgLayer-box-table').remove();
	if ($('#maskImgLayer-box').length > 0) $('#maskImgLayer-box').remove();
	if ($('#maskImgLayer').length > 0) $('#maskImgLayer').remove();

	$('<div>', {
		id : 'maskImgLayer',
		'class' : 'maskImgLayer'
	}).appendTo($('body'));
	
	$('<div>', {
		id : 'maskImgLayer-box',
		'class' : 'maskImgLayer-box'
	}).appendTo($('#maskImgLayer'));
	
	$('<div>', {
		id : 'maskImgLayer-box-table',
		'class' : 'maskImgLayer-box-table'
	}).appendTo($('#maskImgLayer-box'));
	
	$('<div>', {
		id : 'maskImgLayer-box-table-icon',
		'class' : 'maskImgLayer-box-table-icon'
	}).appendTo($('#maskImgLayer-box-table'));
	
	var objImg = '<img id="maskImg" src="'+ objImgPath +'" width="430" border="0" align="absmiddle" />';
	$("<div>", {
		id : 'maskImgLayer-box-table-content',
		html : objImg,
		"class" : 'maskImgLayer-box-table-content'
	}).appendTo($('#maskImgLayer-box'));
	
	$('.maskImgLayer-box-table-icon').bind("click", 
		function() {
			if ($('#maskImg').length > 0) $('#maskImg').remove();
			if ($('#maskImgLayer-box-table-content').length > 0) $('#maskImgLayer-box-table-content').remove();
			if ($('#maskImgLayer-box-table-icon').length > 0) $('#maskImgLayer-box-table-icon').remove();
			if ($('#maskImgLayer-box-table').length > 0) $('#maskImgLayer-box-table').remove();
			if ($('#maskImgLayer-box').length > 0) $('#maskImgLayer-box').remove();
			if ($('#maskImgLayer').length > 0) $('#maskImgLayer').remove();
		}
	);
}

/**
 * 页面加载动态创建弹出Iframe遮罩
 * @param objImgPath : 入参图片有效地址 
 */
function createLoadMaskIframeLayer(obj) {
	if ($('#maskImg').length > 0) $('#maskImg').remove();
	if ($('#maskImgLayer-box-table-content').length > 0) $('#maskImgLayer-box-table-content').remove();
	if ($('#maskImgLayer-box-table-icon').length > 0) $('#maskImgLayer-box-table-icon').remove();
	if ($('#maskImgLayer-box-table').length > 0) $('#maskImgLayer-box-table').remove();
	if ($('#maskImgLayer-box').length > 0) $('#maskImgLayer-box').remove();
	if ($('#maskImgLayer').length > 0) $('#maskImgLayer').remove();

	$('<div>', {
		id : 'maskImgLayer',
		'class' : 'maskImgLayer'
	}).appendTo($('body'));
	
	$('<div>', {
		id : 'maskImgLayer-box',
		'class' : 'maskImgLayer-box'
	}).appendTo($('#maskImgLayer'));
	
	$('<div>', {
		id : 'maskImgLayer-box-table',
		'class' : 'maskImgLayer-box-table'
	}).appendTo($('#maskImgLayer-box'));
	
	$('<div>', {
		id : 'maskImgLayer-box-table-icon',
		'class' : 'maskImgLayer-box-table-icon'
	}).appendTo($('#maskImgLayer-box-table'));
	
	var objIframe = '<iframe id="maskIframe" width="800px" height="500px" src="'+ obj +'" />';
	$("<div>", {
		id : 'maskImgLayer-box-table-content',
		html : objIframe,
		"class" : 'maskImgLayer-box-table-content'
	}).appendTo($('#maskImgLayer-box'));
	
	$('.maskImgLayer-box-table-content').css({'width':'800px','background':'white'});
	$('.maskImgLayer-box-table').css('width','770px');
	$('.maskImgLayer-box-table-icon').bind("click", 
		function() {
			if ($('#maskImg').length > 0) $('#maskImg').remove();
			if ($('#maskImgLayer-box-table-content').length > 0) $('#maskImgLayer-box-table-content').remove();
			if ($('#maskImgLayer-box-table-icon').length > 0) $('#maskImgLayer-box-table-icon').remove();
			if ($('#maskImgLayer-box-table').length > 0) $('#maskImgLayer-box-table').remove();
			if ($('#maskImgLayer-box').length > 0) $('#maskImgLayer-box').remove();
			if ($('#maskImgLayer').length > 0) $('#maskImgLayer').remove();
		}
	);
}

/**
 * JS原生方法扩展，通过className获取节点
 * 
 * @param className
 * @returns {Array}
 */
document.getElementsByClassName = function(className) {
	var all = document.all ? document.all : document.getElementsByTagName('*');
	var elements = new Array();
	for ( var i = 0; i < all.length; i++) {
		if (all[i].className == className) {
			elements[elements.length] = all[i];
			break;
		}
	}
	return elements;
}

/**
 * 判断请求Ajax是否成功
 * 
 * @param data 响应数据
 */
function isAjaxSuccess(data) {
	if (data.indexOf("登录系统") > 0) {
		parent.parent.window.location = "logout";
		return false;
	} else if (data.indexOf("没有权限操作") > 0) {
		alert("您没有该功能的操作权限 ,请联系管理员");
		return false;
	} else {
		return true;
	}
}

/**
 * 刷新框架
 */
function refreshFrameTopAndLeft() {
	parent.topFrame.location = 'web/indexTop.jsp';
	parent.leftFrame.location = 'web/indexMenu.jsp';
}

// star获取Form表单参数
function inputSelector(element) {
	if (element.checked) return [ element.name, element.value ];
}
function input(element) {
	switch (element.type.toLowerCase()) {
		case 'hidden':
		case 'password':
		case 'text':
			return [ element.name, element.value ];
		case 'select':
			return [ element.name, element.value ];
		case 'checkbox':
		case 'radio':
			return inputSelector(element);
	}
	return false;
}
function serializeElement(element) {
	var method = element.tagName.toLowerCase();
	var parameter = input(element);
	if (parameter) {
		var key = encodeURIComponent(parameter[0]);
		if (key.length == 0) return;
		if (parameter[1].constructor != Array) parameter[1] = [ parameter[1] ];
		var values = parameter[1];
		var results = [];
		for ( var i = 0; i < values.length; i++) {
			results.push(key + '=' + encodeURIComponent(values[i]));
		}
		return results.join('&');
	}
}
function getFormParameter(formId) {
	var form = document.getElementById(formId);
	var elements = new Array();
	var tagElements = form.getElementsByTagName('input');
	for ( var j = 0; j < tagElements.length; j++) {
		elements.push(tagElements[j]);

	}
	var queryComponents = new Array();
	for ( var i = 0; i < elements.length; i++) {
		var queryComponent = serializeElement(elements[i]);
		if (queryComponent) queryComponents.push(queryComponent);
	}
	return queryComponents.join('&');
}
//校验索引码是否超过一位
function checkEasyCode(obj){
	if(/[^\a-\z\A-\Z]/g.test(obj.value)){
		obj.value='';
	}else{
		$(obj).val($(obj).val().toUpperCase());
		if($(obj).val().length>1){
			obj.value='';
		}
	}
}
// end获取Form表单参数     
