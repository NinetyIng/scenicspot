//新闻资讯的js
var Content = {
		paramter:{
			CONTENT_ID:0,
			isCommited:false
		},
		checked:function(){
			var content = ue.getContent();
			$("#CONTENT").val(content); //富文本内容放到隐藏域
			if($("#CATEGORY_ID").val().isEmpty()){
				$("#CATEGORY_ID").tips({
					side:3,
		            msg:'请选择栏目',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CATEGORY_ID").focus();
			    return false;
			}
			if($("#TITLE").val().isEmpty()){
				$("#TITLE").tips({
					side:3,
		            msg:'请输入标题',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TITLE").focus();
			    return false;
			}
			if($("#T_IMG").val()==""){
				$("#TIMG").tips({
					side:3,
		            msg:'请上传列表图',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TIMG").val('');
		    	document.getElementById("TIMG").files[0] = '请选择图片';
			    return false;
			}
			return true;
		},
		submitForm:function(state){
			$("#dataState").val(state);
			var url = $("#Form").attr("action");
			if(!Content.checked()){
				return;
			}
			if(Content.paramter.isCommited){ //是否重复提交
			    return;
			}
			if($("#ISHOT").is(":checked")){
				$("#ISHOT").val(true); 
			}else{
				$("#ISHOT").val(false);
			}
			if($("#ISFOCUS").is(":checked")){
				$("#ISFOCUS").attr("value",true); 
			}else{
				$("#ISFOCUS").attr("value",false);
			}
			if($("#RECOMMEND").is(":checked")){
				$("#RECOMMEND").attr("value",true); 
			}else{
				$("#RECOMMEND").attr("value",false);
			}
			if($("#DRAFT").is(":checked")){
				$("#DRAFT").attr("value",true);
			}else{
				$("#DRAFT").attr("value",false);
			}
			var data = $("#Form").serialize();
			Content.paramter.isCommited = true;
			 $.ajax({type:"post",url:url,dataType:"json",
		           data:data,
		           success:function(data){
		             if(data.result){
		                parent.location.reload();
		                parent.layer.close(index);
		             }else{
		            	 Content.paramter.isCommited = false;
		            	 layer.msg(data.msg);
		             }
		           }
		      });
		},
		publish:function(status){
		}
}