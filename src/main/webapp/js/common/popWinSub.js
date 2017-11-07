/**
 * EXTJS弹出框子页面js方法
 */
/**
 * EXTJS关闭弹出框
 * @param winId
 */
function closeWin(winId) {
	if (winId == undefined) {
		winId = 'winPanel';
	}
	var win = parent.Ext.getCmp(winId);
	if (win) {
		win.close();
	}
}