/**
 * JS弹出工具类
 */
/**
 * 公共参数
 */
var objIframe; // 嵌入页面显示层
var objTitle; // 创建标题按钮层（关闭操作）
var objDetailedInfor; // 创建显示信息底层(底层边框)
var objBottom; // 创建灰色透明底层（透明度0.5）
var opacityValue = 20;

/**
 * 创建弹出窗口
 * 
 * @param url
 */
function loadWin(url) {
	if (document.getElementById('bottomDiv')) {
		document.getElementById('detailedInforDiv').removeChild(objIframe);
		document.getElementById('detailedInforDiv').removeChild(objTitle);
		document.body.removeChild(objDetailedInfor);
	} else {
		// var sWidth = document.documentElement.clientWidth; // 当前浏览器的宽度
		var sHeight = 0;

		var checkHeight = (document.body.offsetHeight - document.documentElement.clientHeight);

		if (checkHeight > 0) {
			sHeight = document.body.offsetHeight; // 当前body的高度
		} else {
			sHeight = document.documentElement.clientHeight; // 当前浏览器的高度
		}

		var bodyHeight = sHeight;
		// var winWidth = sWidth * 0.51; // 弹出窗体宽体
		var winHeight = 400; // 弹出窗体高度sHeight * 0.8
		var boxWidth = 1000; // 信息显示底层宽度
		// var boxHeight = (winHeight - 345) // 信息显示底层高度
		var titHeight = 18; // 标题层高度
		var ifrWidth = 994; // 嵌入显示页宽度
		var ifrHeight = (winHeight - 35); // 嵌入显示页高度
		// var returnStateValues = '';

		// 创建灰色透明底层（透明度0.5）
		objBottom = document.createElement("div");
		objBottom.id = 'bottomDiv';
		objBottom.style.position = "absolute";
		objBottom.style.top = "0px";
		objBottom.style.left = "0px";
		objBottom.style.backgroundColor = "#000000";
		objBottom.style.MozOpacity = opacityValue / 100;
		objBottom.style.opacity = opacityValue / 100;
		objBottom.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=" + opacityValue + ")";
		objBottom.style.width = "100%";
		objBottom.style.border = "0px";
		objBottom.style.height = bodyHeight + "px";
		objBottom.style.overflow = "hidden";
		objBottom.style.zIndex = "10000";
		objBottom.style.padding = "0";
		objBottom.style.margin = "0 auto";
		document.body.appendChild(objBottom);
		fadeIn(document.getElementById("bottomDiv"), 20, opacityValue);

		// 创建显示信息底层(底层边框)
		objDetailedInfor = document.createElement("div");
		objDetailedInfor.id = 'detailedInforDiv';
		objDetailedInfor.align = 'center';
		objDetailedInfor.style.position = "fixed";
		objDetailedInfor.style.backgroundColor = "white";
		objDetailedInfor.style.border = "3px solid #DDD";
		objDetailedInfor.style.width = boxWidth + "px";
		objDetailedInfor.style.height = winHeight + "px";
		objDetailedInfor.style.top = (document.documentElement.clientHeight - winHeight) / 2 + "px";
		objDetailedInfor.style.left = (document.documentElement.scrollLeft + (document.documentElement.clientWidth - boxWidth) / 2) + "px";
		objDetailedInfor.style.zIndex = 10001;
		document.body.appendChild(objDetailedInfor);

		// 创建标题按钮层（关闭操作）
		objTitle = document.createElement("div");
		objTitle.id = 'msgTitle';
		objTitle.align = 'right';
		objTitle.style.backgroundColor = "#DDD";
		objTitle.style.border = "0px";
		// objTitle.style.opacity = 0.5;
		// objTitle.style.MozOpacity = 0.5;
		objTitle.style.height = titHeight + "px";
		objTitle.style.margin = "2px";
		objTitle.style.padding = "5px";
		objTitle.innerHTML = "<input type='button' class='btn_cancel'/>";
		objTitle.onclick = removeObjDetailedInfor;
		document.getElementById("detailedInforDiv").appendChild(objTitle);

		// 嵌入页面显示层
		objIframe = document.createElement("iframe");
		objIframe.id = 'dataLoading';
		objIframe.name = 'dataLoading';
		objIframe.setAttribute("src", url);
		objIframe.setAttribute("align", "center");
		objIframe.setAttribute("frameborder", "0", 0);
		objIframe.style.backgroundColor = "white";
		objIframe.style.height = ifrHeight + "px";
		objIframe.style.width = ifrWidth + "px";
		objIframe.style.margin = "0 auto";
		document.getElementById("detailedInforDiv").appendChild(objIframe);
	}
	function scall() {
		$('detailedInforDiv').style.top = (document.documentElement.scrollTop + (document.documentElement.clientHeight - $('detailedInforDiv').offsetHeight) / 2) + "px";
		$('detailedInforDiv').style.left = (document.documentElement.scrollLeft + (document.documentElement.clientWidth - $('detailedInforDiv').offsetWidth) / 2) + "px";
	}
	function removeObjDetailedInfor() {
		document.getElementById("detailedInforDiv").removeChild(objIframe);
		document.getElementById("detailedInforDiv").removeChild(objTitle);
		document.body.removeChild(objDetailedInfor);
		fadeOut(objBottom, document.getElementById("bottomDiv"), 10);
	}
	function fadeIn(elem, speed, opacity) {
		speed = speed || 20;
		opacity = opacity || 50;
		var val = 0;
		(function() {
			elem.filters ? elem.style.filter = 'alpha(opacity=' + val + ')' : elem.style.opacity = val / 100;
			val += 4;
			if (val <= opacity) {
				setTimeout(arguments.callee, speed);
			}
		})();
	}
	function fadeOut(objId, elem, speed, opacity) {
		speed = speed || 20;
		opacity = opacity || 0;
		var val = 50;
		(function() {
			elem.filters ? elem.style.filter = 'alpha(opacity=' + val + ')' : elem.style.opacity = val / 100;
			val -= 4;
			if (val >= opacity) {
				setTimeout(arguments.callee, speed);
			} else if (val < 0) {
				document.body.removeChild(objId);
			}
		})();
	}
}
