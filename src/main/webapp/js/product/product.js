//查询
function productListQuery(){
	var productName = $("#productName").val();
	
	//被选中的复选框
	var selectId="";
	$('input[name="check"]:checked').each(function(){   
		if(selectId==""){
			selectId=$(this).val(); 
		}else{
			selectId = selectId+","+$(this).val();  
		}
	});
	
	window.location.href="/loginAction/productListQuery?productName="+productName+"&selectId="+selectId;
}

function exportProduct(){
	window.location.href="/loginAction/exportProduct?productName="+productName;
}
//添加
function addOrUpdatePage(){
	var url = "/loginAction/productAdd"
	showPopWinNoBtn(url,'添加产品','40%',220);
}
//重置
function changeClear(url){
	$("#productName").val("");
	$("#productType").val("");
	$("#checkInDateVo").val("");
	$("#checkOutDateVo").val("");
	$("#operator").val("");
}

//复选框全选
function selectAll(){  
    if($("#allCheck").is(':checked') == true){
		$("input[name='check']").each(function(){
		   $(this).prop("checked",true);
		  });
	}else{
		$("input[name='check']").each(function(){
		   $(this).prop("checked",false);
		  });
	} 
}  
//子复选框的事件  
function setSelectAll(){  
    //当没有选中某个子复选框时，SelectAll取消选中  
    if (!$("input[name='check']").checked) {  
        $("#allCheck").attr("checked", false);  
    }  
    var chsub = $("input[type='checkbox'][name='check']").length; //获取subcheck的个数  
    var checkedsub = $("input[type='checkbox'][id='subcheck']:checked").length; //获取选中的subcheck的个数  
    if (checkedsub == chsub) {  
        $("#allCheck").attr("checked", true);  
    }  
}