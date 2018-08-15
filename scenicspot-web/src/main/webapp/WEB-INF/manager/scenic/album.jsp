<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<link href="/assets/css/bootstrap-fileupload.css" rel="stylesheet">
<%@ include file="../common/top.jsp"%>
<!-- 百度上传插件 -->
<link rel="stylesheet" type="text/css"
	href="/statics/image-upload/webuploader.css">
<link rel="stylesheet" type="text/css"
	href="/statics/image-upload/style.css">
<link href="/assets/css/plugins/blueimp/css/blueimp-gallery.min.css"
	rel="stylesheet">
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

.form-control {
	width: 250px;
}

.delImg {
	width: 24px;
	height: 24px;
	text-indent: -9999px;
	overflow: hidden;
	margin: 5px 1px 1px;
	cursor: pointer;
	left: 0px;
}
</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<c:if test="${not empty albumModel}">
		<div class="row" style="background-color: #fff;">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<div class="lightBoxGallery">
							<c:forEach items="${albumModel}" var="item">
									<div style="float:left;width: 170px; height: 136px;margin-left:40px;">
										<a  href="${SETTINGPD.IMAGEPATH}${item.img_path}"  style="position: relative;" 
											data-gallery=""> <img class="album_p" onerror="this.src='/statics/img/no-img.jpg'" style="width: 160px; height: 106px;"
											src="${SETTINGPD.IMAGEPATH}${item.img_path}">
										</a>
										<span onclick="delGoodsImg('${item.id}');" class="delImg">移除</span>
									</div>
							</c:forEach>
							<div id="blueimp-gallery" class="blueimp-gallery"
								style="display: none;">
								<div class="slides" style="width: 61200px;"></div>
								<h3 class="title">图片</h3>
								<a class="prev">‹</a> <a class="next">›</a> <a class="close">×</a>
								<a class="play-pause"></a>
								<ol class="indicator"></ol>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${empty albumModel || fn:length(albumModel) < 5}">
			<div class="row" style="background-color: #fff;">
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-content">
							<div class="panel blank-panel">
								<div class="form-group">
								<div id="wrapper">
									<div id="container">
										<div id="uploader">
											<div class="queueList">
												<div id="dndArea" class="placeholder">
													<div id="filePicker" class="webuploader-container">
														<div class="webuploader-pick">点击选择图片</div>
														<div id="rt_rt_1beku61hm13qbl4fv2ok8r1cp41"
															style="position: absolute; top: 20px; left: 836px; width: 168px; height: 44px; overflow: hidden; bottom: auto; right: auto;">
															<input type="file" name="file"
																class="webuploader-element-invisible" multiple="multiple">
															<label
																style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
														</div>
													</div>
													<p>或将照片拖到这里，单次最多可选<span id="imgNum"></span>张</p>
												</div>
												<ul class="filelist"></ul>
											</div>
											<div class="statusBar" style="display: none;">
												<div class="progress" style="display: none;">
													<span class="text">0%</span> <span class="percentage"
														style="width: 0%;"></span>
												</div>
												<div class="info">共0张（0B），已上传0张</div>
												<div class="btns">
													<div id="filePicker2" class="webuploader-container">
														<div class="webuploader-pick">继续添加</div>
														<div id="rt_rt_1beku61hqjtq4qf7v6h61ngc6"
															style="position: absolute; top: 0px; left: 0px; width: 38px; height: 2px; overflow: hidden; bottom: auto; right: auto;">
															<input type="file" name="file"
																class="webuploader-element-invisible" multiple="multiple"><label
																style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
														</div>
													</div>
													<div class="uploadBtn state-pedding">开始上传</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								</div>
								<div class="form-group">
		                                <div class="col-sm-4 col-sm-offset-5">
		                                    <button class="btn btn-warning" type="button"  onclick="closed()"><i class="fa fa-check"></i>&nbsp;&nbsp;返    回&nbsp;&nbsp; </button>
		                                </div>
	                               </div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</div>
	<script type="text/javascript">
	  var goods_id = '${pd.scenic_id}';
	  var imgNum = '${fn:length(albumModel)}';
	  var serverUrl = "/sys/scenic/uploadImg";
	</script>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../common/foot.jsp"%>
	<!-- 百度上传插件 -->
	<script type="text/javascript"
		src="/statics/image-upload/dist/webuploader.js"></script>
	<script type="text/javascript" src="/statics/image-upload/upload.js"></script>
	<script src="/assets/js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>
	
	<script type="text/javascript">
	$("#imgNum").text(5-imgNum);
	  var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	  function closed(){
		  parent.layer.close(index);
	   }
	  function delGoodsImg(id){
		  bootbox.confirm("确定删除该图片？",function(result){
			  if(result){
				  Ajax.request("/sys/scenic/delImg",{"data":{"id":id},"success":function(data){
					  if(data.result == 200){
						  location.reload();
					  }else{
						  bootbox.alert(data.msg);
					  }
				  }});
			  }
		  });
	  }
	</script>
</body>
</html>