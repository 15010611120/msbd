<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>MySpringBootDemo</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="header">

    <div class="dl-title">
      MySpringBoot登陆
    </div>

</div>

<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="assets/js/bui-min.js"></script>
<script type="text/javascript" src="assets/js/common/main-min.js"></script>
<script type="text/javascript" src="assets/js/config-min.js"></script>
<script>
    function submitBootLogin(){
    	var userName=$("#userName").val();
    	var passWorld=$("#passWord").val();
    	$.ajax({   
            type:'post',        
            url:'/loginAction/login',    
            data:{
            	userName:userName,
            	passWorld:passWorld
            },    
            cache:false,    
            dataType:'json',    
            success:function(data){   
               if(data.rspCode=="00"){
               	 alert(data.rspMsg);
               }else{
               	alert(data.rspMsg);
               }
            }
        }); 
    }
</script>

		<table class="table table-bordered table-hover definewidth m10">
			<tr class="pos">
				<td class="tableleft" style="text-align: right">UserName</td>
				<td><input type="text" name="userName" id="userName"/></td>
			</tr>
			<tr class="pos">
				<td class="tableleft" style="text-align: right">PassWorld</td>
				<td><input type="password" name="passWord" id="passWord"></textarea></td>
			</tr>
			<tr class="pos">
				<td class="tableleft" style="text-align: right">
					<button type="button" class="btn btn-primary"  onclick="submitBootLogin();">BootLogin</button>
				</td>
			</tr>
		</table>
</body>
</html>