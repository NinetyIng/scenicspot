<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<link href="/assets/css/bootstrap-fileupload.css" rel="stylesheet">
	<%@ include file="../../common/top.jsp"%>
	<link href="/assets/css/plugins/summernote/summernote.css" rel="stylesheet">
	<link href="/assets/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
	<style type="text/css">
	.file-pick {
	    font-size: 18px;
	    background: #00b7ee;
	    border-radius: 3px;
	    line-height: 44px;
	    padding: 0 30px;
	    color: #fff;
	    display: inline-block;
	    margin: 0 auto 20px auto;
	    cursor: pointer;
	    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
	}
	</style>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
						    <c:if test="${pd.command eq 'MORE'}"> 更多命令</c:if>
						    <c:if test="${pd.command ne 'MORE'}">发送命令</c:if>
						</h5>
					</div>
					<form action="/command/doCommand" method="post"	class="form-horizontal" id="commentForm">
					      <input name="ids" type="hidden" value="${pd.device_no}"  />
					      <input name="command" type="hidden" value="${command}">
					      <table class="table table-hover">
                                           <tr>
											<th>说明</th>
											<th>${command.desc}</th>
										</tr>
										 <c:if test="${command eq 'IP' }">
											 <tr>
												<th>ip设置</th>
												<th>
											     <input type="text" id="ip" name="ip" class="form-control"/>
											     </th>
											<tr>
												<th>端口设置</th>
												<th><input type="text" id="port" name="port" class="form-control"/></th>
											 </tr>
											  </tr>
										 </c:if>
										 <c:if test="${command eq 'MESSAGE'}">
										     <tr>
												<th>推送内容</th>
												<th>
												   <textarea rows="5" cols="10" name=""></textarea>
												</th>
											 </tr>
										 
										 </c:if>
										 <tr>
										   <th>
										   <button type="button" onclick="goBack();" class="btn btn-w-m btn-success">返回</button>
										   </th>
										   <th>
										     <button type="button" onclick="send();" class="btn btn-w-m btn-danger">发送</button>
										   </th>
										 </tr>
                          </table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../common/foot.jsp"%>
	<script src="/assets/js/bootstrap-fileupload.js"></script>
<!-- 自定义js -->
	<script src="/assets/js/plugins/summernote/summernote.min.js"></script>
    <script src="/assets/js/plugins/summernote/summernote-zh-CN.js"></script>
     <script src="/statics/js/util/InheritString.js"></script>
    <script type="text/javascript">
       function goBack(){
    	   parent.location.href='/device/index';
       } 
       function send(){
    	   var data = $("#commentForm").serialize();
    	   if($("#ip")[0]){
    		   if(!$("#ip").val().checkIP()){
    			   bootbox.alert("输入的ip不合法"); 
    			   return;
    		   }
    	   }
    	   if($("#port")[0]){
    		   if(!$("#port").val().checkPort()){
    			   bootbox.alert("输入的端口号不合法"); 
    			   return;
    		   }
    	   }
    	   Ajax.request("/device/doCommand",{"data":data,"success":function(item){
    		   if(item.result != "200"){
    			   bootbox.alert(item.msg); 
    		   }else{
    			   parent.location.href='/device/index';
    		   }
    	   }});
       }
    </script>
</body>
</html>