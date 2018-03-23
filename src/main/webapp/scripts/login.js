//主处理
$(function(){
	//给登录按钮绑定单击处理
$("#login").click(userLogin);
$("#regist_button").click(userRegist);	
		
		
		
});
//用户注册
function userRegist(){
	$("#warning_1 span").html("");
	$("#warning_2 span").html("");
	$("#warning_3 span").html("");
		var name=$("#regist_username").val().trim();
		var password=$("#regist_password").val().trim();
		var nickname=$("#nickname").val().trim();
		var password1=$("#final_password").val().trim();
		var ok=true;
		//检查格式
		if(name==""){
			$("#warning_1 span").html("用户名为空");
			$("#warning_1").show();
			ok=false;
		}
		if(password==""){
			$("#warning_2 span").html("密码不能为空");
			$("#warning_2").show();
			ok=false;
		}else if(password.length<=6&&password.length>0){
			$("#warning_2 span").html("密码长度过低");
			$("#warning_2").show();
			ok=false;
		}
		//检测确认密码
		if(password1==""){
			$("#warning_3 span").html("确认密码为空");
			$("#warning_3").show();
			ok=false;
		}else if(password1!=password){
			$("#warning_3 span").html("密码不一致");
			$("#warning_3").show();
			ok=false;
		}
		if(ok=true){
			$.ajax({
				url:path+"/user/add.do",
				type:"post",
				data:{"name":name,"nick":nickname,
					"password":password},
				dataType:"json",
				success:function(result){
					if(result.status==0){//成功
						alert(result.msg);//提示注册成功
						$("#back").click();//返回到登录界面
					}else if(result.status==1){//用户名被占用
						$("#warning_1 span").html(result.msg);
						$("#warning_1").show();
					}
				},
				error:function(){
					alert("注册失败");
				}
			});
		}
}
//用户登录
function userLogin(){
	//清除原有消息
	$("#count_span").html("");
	$("#password_span").html("");
	//获取请求参数
	var name = $("#count").val().trim();
	var password = $("#password").val().trim();
	//检测格式
	var ok = true;//是否通过校验
	if(name==""){
		$("#count_span").html("用户名为空");
		ok = false;
	}
	if(password==""){
		$("#password_span").html("密码为空");
		ok = false;
	}
	//发送Ajax请求
	if(ok){//(通过格式检测)
		$.ajax({
			url:"http://localhost:8090/cloud_note/user/login.do",
			type:"post",
			data:{"name":name,"password":password},
			dataType:"json",
			success:function(result){
				//result是服务器返回的JSON结果
				//{"status":xx,"msg":xx,"data":xx}
				if(result.status==0){//成功
					//TODO 将用户信息写入Cookie
					var userId=result.data.cn_user_id;
					var token=result.data.cn_user_token;
					addCookie("token",token,2);
					addCookie("userId",userId,2);
					window.location.href="edit.html";
				}else if(result.status==1){//用户名错
					$("#count_span").html(result.msg);
				}else if(result.status==2){//密码错
					$("#password_span").html(result.msg);
				}

			},
			error:function(){
				alert("登录失败");
			}
		});
	}
};