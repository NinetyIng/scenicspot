var Login ={
		url:"/main/doLogin.do",
		check:function(){
			if ($("#loginname").val() == "") {
				$("#loginname").tips({
					side : 2,
					msg : '用户名不得为空',
					bg : '#AE81FF',
					time : 3
				});
				$("#loginname").focus();
				return false;
			} else {
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}
			if ($("#password").val() == "") {
				$("#password").tips({
					side : 2,
					msg : '密码不得为空',
					bg : '#AE81FF',
					time : 3
				});
				$("#password").focus();
				return false;
			}
			$("#loginbox").tips({
				side : 1,
				msg : '正在登录 , 请稍后 ...',
				bg : '#68B500',
				time : 10
			});
			return true;
		},
		doLogin:function(){
             if(this.check()){
				var loginname = $("#loginname").val();
				var password = $("#password").val();
				var code = loginname+",ea,"+password;
				Ajax.request(this.url,{"data":{KEYDATA:code,tm:new Date().getTime()},"success":function(data){
					if(data.result == "200"){
						window.location.href="/main/index.do";
					}else{
						alert(data.msg);
					}
				}});
			}
		},
		/**
		 * 如果选择记住密码直接保存到客户端
		 */
		saveCookie: function() {
			if ($("#saveid").attr("checked")) {
				$.cookie('loginname', $("#loginname").val(), {
					expires : 7
				});
				$.cookie('password', $("#password").val(), {
					expires : 7
				});
			}
		}
}