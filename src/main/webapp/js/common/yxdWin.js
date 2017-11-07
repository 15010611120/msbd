/**
 * EXTJS弹窗工具类
 */
/**
 * 弹出单选多选数据列表界面，将选择的数据显示在主界面上
 * 
 * @param type radio:单选 ； checkbox：多选 ;
 * @param url 弹出层里要显示的页面地址
 * @param fromName 弹出层里的页面控件name
 * @param toTextId 用来显示数据的控件id
 * @param toValueId 用来保存数据值的控件id
 */
function showWin(type, url, fromName, toTextId, toValueId) {
	var win = Ext.get('winPanel')
	if (win) {
		win.close();
		return;
	}

	// 弹出window窗口
	var win = new Ext.Window({
		title : '',
		id : 'winPanel',
		layout : 'fit',
		width : '60%',
		height : 400,
		closeAction : 'close',
		plain : true,
//		resizable : false,
		constrainHeader : true,
		modal : true,
		draggable : true,
		maximizable: true,
		html : '<iframe id="ifram_Window" scrolling="auto" frameborder="0" width="100%" height="100%" src="' + url + '"></iframe>',
		buttons : [ {
			text : '取消',
			handler : function() {
				win.close();
			}
		}, {
			text : '确定',
			handler : function() {
				var valueString = "";
				var text = "";
				var value = "";
				var fromValueStr = "document.getElementById('ifram_Window').contentWindow.document.getElementsByName('" + fromName + "');";
				var r = eval(fromValueStr)
				for ( var i = 0; i < r.length; i++) {
					if (r[i].checked) {
						valueString = r[i].value;
						var valueArray = valueString.split("|");
						if (type == 'radio') {
							text = valueArray[0];
							value = valueArray[1];
							break;
						}
						if (type == 'checkbox') {
							text += valueArray[0];
							text += ";";
							value += valueArray[1];
							value += ";";
						}
					}
				}
				var toTextObj = document.getElementById(toTextId);
				var toValueObj = document.getElementById(toValueId);
				toTextObj.value = text;
				toValueObj.value = value;
				win.close();
			}
		} ]
	});
	win.show();
}
/**
 * 弹出添加数据表单，将新添加的数据显示在下拉列表中
 * 该方法的使用方法为：调用iframe界面里的js方法（fun），fun方法返回下拉列表显示和保存的值，格式为“text|value”
 * @param url 弹出层里要显示的页面地址
 * @param selectId 主界面中的下拉列表id
 * @param fun 子界面中的ajax方法，该方法执行两个功能，1：保存 2：查询下拉列表option数据
 */
function showFormWin(url, selectId, fun) {
	var win = Ext.get('winPanel')
	if (win) {
		win.close();
		return;
	}

	// 弹出window窗口
	var win = new Ext.Window({
		title : '',
		id : 'winPanel',
		layout : 'fit',
		width : '60%',
		height : 400,
		closeAction : 'close',
		plain : true,
//		resizable : false,
		constrainHeader : true,
		modal : true,
		draggable : true,
		maximizable: true,
		html : '<iframe id="ifram_Window" scrolling="auto" frameborder="0" width="100%" height="100%" src="' + url + '"></iframe>',
		buttons : [ {
			text : '确定',
			handler : function() {
				// 调用iframe界面里的js方法（fun），fun方法返回下拉列表显示和保存的值，格式为“text|value”
				var operation = "document.getElementById('ifram_Window').contentWindow." + fun;
				var optionData = eval(operation);
				var textValue = optionData.split('|');
				var selectObj = document.getElementById(selectId);// 下拉列表
				selectObj.options[selectObj.length] = new Option(textValue[0], textValue[1]);
				win.close();
			}
		}, {
			text : '取消',
			handler : function() {
				win.close();
			}
		} ]
	});
	win.show();
}
/**
 * 弹出窗口
 * @param url 弹出窗口里需要显示的页面地址
 * @param fun 子页面里的方法（是提交表单，还是将界面的值传到主页面，都在此方法完成）
 * @param funPlace fun方法是子界面的还是父界面的，子界面：child，父界面：parent
 * @param title 窗口标题
 * @param width 窗口宽
 * @param height 窗口高
 * @param formId 子窗口关闭返回时要重新刷新的查询Form表单ID
 */

function showPopWin(url, fun, funPlace, title, width, height, formId) {
	var win = Ext.get('winPanel')
	if (win) {
		win.close();
		return;
	}
	if (width == "") {
		width = 600;
	}
	if (height == "") {
		height = 400;
	}
	// 弹出window窗口
	var win = new Ext.Window({
		title : title,
		id : 'winPanel',
		layout : 'fit',
		width : width,
		height : height,
		closeAction : 'close',
		plain : true,
//		resizable : false,
		constrainHeader : true,
		modal : true,
		draggable : true,
		maximizable: true,
		html : '<iframe id="ifram_Window" scrolling="auto" frameborder="0" width="100%" height="100%" src="' + url + '"></iframe>',
		buttons : [ {
			id: "saveBtn",
			text : '确定',
			handler : function() {
				if (formId=="frmSearchAd"||formId=="frmSearchType"){
					 Ext.getCmp('saveBtn').disable(true);
				}
				if (funPlace == "child") {
					// 调用iframe界面里的js方法（fun），fun方法返回下拉列表显示和保存的值，格式为“text|value”
					if(fun==''){
						win.close();
					}else{
						var operation = "document.getElementById('ifram_Window').contentWindow." + fun;
						var optionData = eval(operation);
					}
				}
				if (funPlace == "parent") {
					eval(fun);
					win.close();
				}
			}
		}, {
			text : '关闭',
			handler : function() {
                if(funPlace == "child") {
                    if(formId){
                        var href=window.location.href;
                        href=href.indexOf("?")>0?href.substring(0,href.indexOf("?")):href;
                        window.location.href=href+"?"+$("#"+formId).serialize();
                    }
                }
                win.close();
            }
		} ]
	});
    win.on("close",function(){
        if(funPlace == "child") {
            if(formId){
                var href=window.location.href;
                href=href.indexOf("?")>0?href.substring(0,href.indexOf("?")):href;
                window.location.href=href+"?"+$("#"+formId).serialize();
            }
        }
        //显示父窗口的滚动条
        var framesArray = window.parent.frames;
        for (i = 0; i < framesArray.length; i++) {
    		framesArray[i].document.body.style.overflow='auto';
        }
    });
    win.on("show",function(){
    	//隐藏父窗口的滚动条
        var framesArray = window.parent.frames;
        for (i = 0; i < framesArray.length; i++) {
    		framesArray[i].document.body.style.overflow='hidden';
        }
    });
	win.show();
}

/**
 * 弹出窗口,没有确定按钮和关闭按钮
 * @author liuyandong
 * 
 * @param url 弹出窗口里需要显示的页面地址
 * @param title 窗口标题
 * @param width 窗口宽
 * @param height 窗口高
 */

function showPopWinNoBtn(url, title, width, height) {
	var win = Ext.get('winPanel')
	if (win) {
		win.close();
		return;
	}
	if (width == "") {
		width = 600;
	}
	if (height == "") {
		height = 400;
	}
	// 弹出window窗口
	var win = new Ext.Window({
		title : title,
		id : 'winPanel',
		layout : 'fit',
		width : width,
		height : height,
		closeAction : 'close',
		plain : false,
//		resizable : false,
		constrainHeader : true,
		modal : true,
		draggable : true,
		maximizable: true,
		bodyStyle : "background-color:#ffffff;",
		html : '<iframe id="ifram_Window" scrolling="auto" frameborder="0" width="100%" height="100%" src="' + url + '"></iframe>'
	});
    win.on("close",function(){
        //显示父窗口的滚动条
    	var framesArray = window.parent.frames;
        for (i = 0; i < framesArray.length; i++) {
    		framesArray[i].document.body.style.overflow='auto';
        }
    });
    win.on("show",function(){
    	//隐藏父窗口的滚动条
    	var framesArray = window.parent.frames;
        for (i = 0; i < framesArray.length; i++) {
    		framesArray[i].document.body.style.overflow='hidden';
        }
    });
	win.show();
}
/**
 * 弹出窗口,没有确定按钮和关闭按钮
 * 
 * @param url 弹出窗口里需要显示的页面地址
 * @param title 窗口标题
 * @param width 窗口宽
 * @param height 窗口高
 */

function showPopWinNoBtnAndClose(url, title, width, height) {
	var win = Ext.get('winPanel')
	if (win) {
		win.close();
		return;
	}
	if (width == "") {
		width = 600;
	}
	if (height == "") {
		height = 400;
	}
	// 弹出window窗口
	var win = new Ext.Window({
		title : title,
		id : 'winPanel',
		layout : 'fit',
		width : width,
		height : height,
		closable: false,
		closeAction : 'close',
		plain : false,
//		resizable : false,
		constrainHeader : true,
		modal : true,
		draggable : true,
		maximizable: true,
		bodyStyle : "background-color:#ffffff;",
		html : '<iframe id="ifram_Window" scrolling="auto" frameborder="0" width="100%" height="100%" src="' + url + '"></iframe>'
	});
	win.show();
}
/**
 * 弹出窗口,窗体有关闭按钮，没有确定按钮
 * 
 * @param url 弹出窗口里需要显示的页面地址
 * @param title 窗口标题
 * @param width 窗口宽
 * @param height 窗口高
 */
function showPopWinNoConfirmBtn(url,funPlace, title, width, height,formId) {
	var win = Ext.get('winPanel')
	if (win) {
		win.close();
		return;
	}
	if (width == "") {
		width = 600;
	}
	if (height == "") {
		height = 400;
	}
	// 弹出window窗口
	var win = new Ext.Window({
		title : title,
		id : 'winPanel',
		layout : 'fit',
		width : width,
		height : height,
		closeAction : 'close',
		plain : false,
//		resizable : false,
		constrainHeader : true,
		modal : true,
		draggable : true,
		maximizable: true,
		bodyStyle : "background-color:#ffffff;",
		html : '<iframe id="ifram_Window" scrolling="auto" frameborder="0" width="100%" height="100%" src="' + url + '"></iframe>',
		buttons : [ {
			text : '关闭',
			handler : function() {
                if(funPlace == "child") {
                    if(formId){
                        var href=window.location.href;
                        href=href.indexOf("?")>0?href.substring(0,href.indexOf("?")):href;
                        window.location.href=href+"?"+$("#"+formId).serialize();
                    }
                }
                win.close();
            }
		} ]
	});
	win.show();
}


/**
 * 弹出窗口,不借助iframe的src请求路径 而是弹出普通文本 
 * 没有确定按钮和关闭按钮
 * 
 * @param title 窗口标题
 * @param content窗口弹出的内容
 * @param width 窗口宽
 * @param height 窗口高
 */
function showCurrnetPageContent(title,content,width, height) {
	var win = Ext.get('winPanel');
	if (win) {
		win.close();
		return;
	}
	if (width == "") {
		width = 600;
	}
	if (height == "") {
		height = 400;
	}
	// 弹出window窗口
	var win = new Ext.Window({
		title : title,
		id : 'winPanel',
		layout : 'fit',
		autoScroll : true,
		width : width,
		height : height,
		closeAction : 'close',
		plain : false,
//		resizable : false,
		constrainHeader : true,
		modal : true,
		draggable : true,
		maximizable: true,
		bodyStyle : "background-color:#ffffff;",
		html : content
	});
	win.show();
}

/**
 * 弹出窗口,窗体没有按钮
 * 新增弹出框
 * @param url 弹出窗口里需要显示的页面地址
 * @param title 窗口标题
 * @param width 窗口宽
 * @param height 窗口高
 */
function showPopWinNoBtns(url,funPlace, title, width, height,formId) {
	var win = Ext.get('winPanel')
	if (win) {
		win.close();
		return;
	}
	if (width == "") {
		width = 600;
	}
	if (height == "") {
		height = 400;
	}
	// 弹出window窗口
	var win = new Ext.Window({
		title : title,
		id : 'winPanel',
		layout : 'fit',
		width : width,
		height : height,
		closable: false,//false 时无关闭按钮
		closeAction : 'close',
		plain : false,
		resizable : false,//false 时弹出框不会拖动改变大小
		constrainHeader : true,
		modal : true,
		draggable : true,
		//maximizable: true,最大化
		bodyStyle : "background-color:#ffffff;",
		html : '<iframe id="ifram_Window" scrolling="auto" frameborder="0" width="100%" height="100%" src="' + url + '"></iframe>'
	});
	win.show();
}


