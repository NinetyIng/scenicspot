/**
 * 普通删除
 * @param id
 * @param url
 * @returns
 */
function del(id,url){
		layer.confirm('删除订单？', {
			  btn: ['物理删除','逻辑删除'],icon: 2,title:'删除' //按钮
			}, function(){
				$.ajax({
					   type: "POST",
					   url: url,
					   dataType : "json",
					   data : {
						   id: id,
						   delType:'0'
					   },
					   success: function(data){
						  layer.msg(data.msg, {icon: 1});
						  setTimeout('window.location.reload()',1200);
					   },
					   error : function(data){
						  layer.msg(data.msg, {icon: 5});
					   }
					});
			  	
			}, function(){
				$.ajax({
					   type: "POST",
					   url: url,
					   dataType : "json",
					   data : {
						   id: id,
						   delType:'1'
					   },
					   success: function(data){
						  layer.msg(data.msg, {icon: 1});
						  setTimeout('window.location.reload()',1200);
					   },
					   error : function(data){
						  layer.msg(data.msg, {icon: 5});
					   }
					});
			});
	}

/**
 * 表单提交失败提示
 */
function errorMsg(msg){
	if(msg != ''){
		layer.msg(msg, {icon: 5});
	}
}