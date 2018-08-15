var User = {
		delUser:function(userId,msg){
			bootbox.confirm("确定要删除["+msg+"]吗?", function(result) {
				if(result){
					Ajax.request("/user/deleteU.do?USERID="+userId+"&tm="+new Date().getTime(),{"success":function(item){
					   if(item.result == "200"){
					       window.location.reload();
					   }else{
					      alert(item.msg);
					   }
					}});
				}
			})
		},
		add:function(){
			layer.open({
				 type: 2,
				  title: '添加管理员',
				  shadeClose: true,
				  shade: 0.5,
				  area: ['450px', '620px'],
				  content: 'user/goAddU.do'
				});
		},
		editUser:function(user_id){
			layer.open({
				 type: 2,
				  title: '编辑管理员',
				  shadeClose: true,
				  shade: 0.5,
				  area: ['450px', '600px'],
				  content: '/user/goEditU.do?USER_ID='+user_id
				});
		},
		submitForm:function(){
			$("#Form").submit();
		},
		save:function(){
			if(!$("#role_id").val().isNotEmpty()){
				$("#juese").tips({
					side:3,
		            msg:'选择角色',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#role_id").focus();
				return false;
			}
			if(!$("#loginname").val().isNotEmpty() || $("#loginname").val()=="此用户名已存在!"){
				$("#loginname").tips({
					side:3,
		            msg:'输入用户名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#loginname").focus();
				$("#loginname").val('');
				$("#loginname").css("background-color","white");
				return false;
			}else{
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}
			if(!$("#NUMBER").val().isNotEmpty()){
				$("#NUMBER").tips({
					side:3,
		            msg:'输入编号',
		            bg:'#AE81FF',
		            time:3
		        });
				$("#NUMBER").focus();
				return false;
			}else{
				$("#NUMBER").val($.trim($("#NUMBER").val()));
			}
			if(!$("#user_id").val().isNotEmpty() && !$("#password").val().isNotEmpty()){
				$("#password").tips({
					side:3,
		            msg:'输入密码',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#password").focus();
				return false;
			}
			if($("#password").val()!=$("#chkpwd").val()){
				$("#chkpwd").tips({
					side:3,
		            msg:'两次密码不相同',
		            bg:'#AE81FF',
		            time:3
		        });
				$("#chkpwd").focus();
				return false;
			}
			if(!$("#name").val().isNotEmpty()){
				$("#name").tips({
					side:3,
		            msg:'输入姓名',
		            bg:'#AE81FF',
		            time:3
		        });
				$("#name").focus();
				return false;
			}
			if(!$("#PHONE").val().isNotEmpty()){
				
				$("#PHONE").tips({
					side:3,
		            msg:'输入手机号',
		            bg:'#AE81FF',
		            time:3
		        });
				$("#PHONE").focus();
				return false;
			}else if($("#PHONE").val().length != 11 && !$("#PHONE").val().isPhone()){
				$("#PHONE").tips({
					side:3,
		            msg:'手机号格式不正确',
		            bg:'#AE81FF',
		            time:3
		        });
				$("#PHONE").focus();
				return false;
			}
			if(!$("#EMAIL").val().isNotEmpty()){
				$("#EMAIL").tips({
					side:3,
		            msg:'输入邮箱',
		            bg:'#AE81FF',
		            time:3
		        });
				$("#EMAIL").focus();
				return false;
			}else if(!$("#EMAIL").val().isEmail()){
				$("#EMAIL").tips({
					side:3,
		            msg:'邮箱格式不正确',
		            bg:'#AE81FF',
		            time:3
		        });
				$("#EMAIL").focus();
				return false;
			}
			var url = $("#userForm").attr("action");
			var submitData = $("#userForm").serialize();
			Ajax.request(url,{"data":submitData,"success":function(item){
				if(item.result == "200"){
					parent.location.reload();
				}else{
					alert(item.msg);
				}
			}});
		},
		/**
		 * 判断用户名邮箱电话号码是否存在
		 * @param strJson
		 * @param id
		 */
		hasExist:function(strJson){
			var name = $("#"+strJson.id).attr("name");
			var value = $("#"+strJson.id).val();
			var oldValue = strJson.oldValue;
			if(!value.isNotEmpty()){
			   return;
			}
			if(oldValue.isNotEmpty() && oldValue ==  value){
			    return;
			}
			if(!value.isNotEmpty()){
				return;
			}
			if($("#"+strJson.id).prev().val() == $("#"+strJson.id).val()){
			   return;
			} 
			Ajax.request("/user/hasExist?"+name+"="+value,{"success":function(item){
				if(item.result != "200"){
					 $("#"+strJson.id).tips({
							side:3,
				            msg:strJson.msg,
				            bg:'#AE81FF',
				            time:3
				        });
					 $("#"+strJson.id).val('');
				}
			}});
		}
}