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
						      选择命令
						</h5>
					</div>
					<form action="/command/doCommand" method="post"	class="form-horizontal" id="commentForm">
					      <input type="hidden" id="ids" value="${pd.device_no}"> 
					      <table class="table table-hover">
                                         <tr>
                                            <th><a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}"  tag="IP"><i class="fa fa-folder"></i> 设置ip端口 </a></th>
                                            <th><a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="FACTORY"><i class="fa fa-cogs"></i> 恢复出厂设置 </a></th>
                                            <th><a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="RESET"><i class="fa fa-train"></i> 重新启动 </a></th>
										</tr>
										 <tr>
										     <th><a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="POWEROFF"><i class="fa fa-minus-square"></i> 关机 </a></th>
	                                        <th><a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="FIND"><i class="fa fa-eye"></i> 查找手环 </a> </th>
										    <th><a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="UPGRADE"><i class="fa fa-mars-stroke-h"></i> 远程升级 </a></th>
										 </tr>
										  <tr>
										     <th><a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="COST1"><i class="fa fa-shirtsinbulk"></i> 短信指令 </a></th>
	                                        <th><a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="TS"><i class="fa fa-bullhorn"></i> 参数查询 </a> </th>
										    <th><a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="CR"><i class="fa fa-globe"></i> 定位指令 </a></th>
										 </tr>
										  <tr>
										     <th><a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="MONITOR"><i class="fa fa-volume-down"></i> 监听 </a></th>
	                                        <th><a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="MESSAGE"><i class="fa fa-krw"></i> 文字推送 </a> </th>
										    <th><a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="CAPT"><i class="fa fa-video-camera"></i> 远程监拍 </a></th>
										 </tr>
										   <tr>
										     <th><a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="TRACKER"><i class="fa fa-cab"></i> 跟踪模式 </a></th>
										     <th><a href="javascript:;" class="btn btn-white btn-sm opera"  device_no="${item.device_no}" tag="TS"><i class="fa fa-cab"></i> 查询配置参数</a></th>
										 </tr>
										   <tr>
										     <th></th>
										     <th></th>
										     <th> <button type="button" onclick="goBack();" class="btn btn-w-m btn-success">返回</button></th>
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
     $(".opera").on("click",function(){
 		var tag = $(this).attr("tag");
 		var device_no = $("#ids").val();
	    var title = "发送命令";
	    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	    parent.layer.open({
					 type: 2,
					  title: title,
					  shadeClose: true,
					  shade: 0.5,
					  area: ['510px', '510px'],
					  content: '/device/commandPage?device_no='+device_no+'&command='+tag
		});
	    parent.layer.close(index);
 	});
     function goBack(){
  	   parent.location.href='/device/index';
     } 
     </script>
</body>
</html>