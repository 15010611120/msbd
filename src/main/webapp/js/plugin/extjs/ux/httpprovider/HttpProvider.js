
var _state = new function(){};
//客户端状态的保存
Ext.ux.HttpProvider = Ext.extend(Ext.state.Provider, {
	get: function(name, defaultValue){
		if (_state[name])
		    return Ext.util.JSON.decode(_state[name]);
		return defaultValue;
	},
	set: function(name, value){//每次状态改变的时候调用
		  var valueString = Ext.util.JSON.encode(value)
		  _state[name] = valueString;
		  Ext.Ajax.request({
		     url: 'setExtClientState',
		     method: 'POST',
		     params: {
		    	 key: name,
		    	 value: valueString
		     }
		  });
	},
	clear: function(name){//name为grid的状态id
		_state[name] = "";
		this.setValue(name, "");
	},
	// 保存数据  
    setValue: function(name, value) {
    	Ext.Ajax.request({
			async : false,
		    url: 'setExtClientState',
		    method: 'POST',
		    params: {
		    	key: name,
		    	value: value
		    },
		    success : function(data) {
		    	window.location.reload(); 
	    	} 
	  });
    }
});
/**
 * 状态的加载
 * stateId: 状态id
 * state： 状态值
 */
Ext.ux.HttpProvider.loadState = function(stateId, state){
	 _state[stateId] = state;
};