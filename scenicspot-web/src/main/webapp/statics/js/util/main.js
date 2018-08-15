(function () {
	window.ZC=function(){
		var app=this;
		app.version = '1.0';
		/**
		* 提交AJAX请求
		**/
		app.post = function(data,url,onSuccess,onError,showLoading){
			var loadingPanel=null;
			if(showLoading){
				loadingPanel=layer.load();
			}
			$.ajax({
			     type: "POST",
			     url: url,
			     data: data,
			     dataType: "json",
			     success: function(data){
			    	 if(loadingPanel){
			    		 layer.close(loadingPanel);
			    	 }
			     	if(data.result=="200"){
			     		onSuccess(data);
			     	}else{
			     		onError(data);
			     	}
			     },  
			     error : function(e) {  
			    	 if(loadingPanel){
			    		 layer.close(loadingPanel);
			    	 }
			    	 onError(e);
			    }
			 });
		};
		app.get = function(data,url,onSuccess,onError){
			$.ajax({
			     type: "GET",
			     url: url,
			     data: data,
			     dataType: "json",
			     success: function(data){
			     	if(data.result=="200"){
			     		onSuccess(data);
			     	}else{
			     		onError(data);
			     	}
			     },  
			     error : function(e) {  
			    	 onError(e);
			    }
			 });
		};
		app.submitForm = function (form,onSuccess,onError){
			var valStr="";
			var url="";
			if(typeof(form)=="string"){
				valStr=$("#"+form).serialize();
				url=$("#"+form).attr("action");
			}else if(typeof(form)=="object"){
				valStr=$(form).serialize();
				url=$(form).attr("action");
			}
			valStr = valStr.replace(/&/g, "','" );
			valStr = valStr.replace(/=/g, "':'" );
			valStr = "({'" +valStr + "'})" ;
			var data = eval(valStr);
			app.post(data,url,onSuccess,onError,true);
		};
		app.toast=function(msg,title,type){
			if(""==title||null==title){
				title="信息";
			}
			toastr.options = {
					  "closeButton": true,
					  "debug": false,
					  "progressBar": true,
					  "positionClass": "toast-top-full-width",
					  "onclick": null,
					  "showDuration": "400",
					  "hideDuration": "500",
					  "timeOut": "7000",
					  "extendedTimeOut": "1000",
					  "showEasing": "swing",
					  "hideEasing": "linear",
					  "showMethod": "fadeIn",
					  "hideMethod": "fadeOut"
					}
			if(type=="error"||type=="错误"||type=="出错"){
				toastr.error(title, msg);
			}else if(type=="success"||type=="成功"){
				toastr.success(title, msg);
			}else if(type=="info"||type=="提示"||type=="提醒"){
				toastr.info(title, msg);
			}else if(type=="warning"||type=="警告"||type=="警示"){
				toastr.warning(title, msg);
			}else{
				toastr.info(title, msg);
			}
		};
		app.confirm=function (title,text,onOkClickFun,data){
			swal({title: title,
					text: text,
					type: "warning",
					showCancelButton: true,
					cancelButtonText: "取消",
					confirmButtonColor: "#DD6B55",
					confirmButtonText: "确定",
					closeOnConfirm: false 
				}, function(){
					onOkClickFun(data);
				});
		};
		app.success=function (text,title){
			if(title==null||title==""){
				title="提示";
			}
			swal(title, text, "success");
		};
		app.error=function (text,title){
			if(title==null||title==""){
				title="出错了";
			}
			swal(title, text, "error");
		};
	};
})();
var app = new ZC();