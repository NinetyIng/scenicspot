<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<link href="/assets/css/bootstrap-fileupload.css" rel="stylesheet">
<%@ include file="../common/top.jsp"%>
<link href="/statics/css/phone.css" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							<c:if test="${not empty dataModel}">编辑</c:if>
							<c:if test="${empty dataModel}">新增</c:if>
							景区
						</h5>
					</div>
					<div class="ibox-content">
						<form action="/sys/scenic/save" method="post"
							class="form-horizontal m-t" id="commentForm"
							enctype="multipart/form-data">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#tab-1"
									aria-expanded="true">基本信息</a></li>
								<li class=""><a data-toggle="tab" href="#tab-2"
									aria-expanded="true">位置信息</a></li>
								<li class=""><a data-toggle="tab" href="#tab-4"
									aria-expanded="true">描述</a></li>
							</ul>
							<div class="tab-content">
								<div id="tab-1" class="tab-pane active">
									<div class="panel-body">
										<input type="hidden" name="id" id="id" value="${dataModel.id}" />
										<input type="hidden" name="category_id" id="category_id"
													class="form-control" value="-2">
										<div class="form-group">
											<label class="col-sm-2 control-label"><font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>景区名称：</label>
											<div class="col-sm-6">
												<input type="text" name="scenic_name" id="scenic_name"
													class="form-control" value="${dataModel.scenic_name}" />
											</div>
											
												<div class="col-sm-2">
												<label class="control-label"><font
												color="red" size="3px"
												style="font-size:11px;">1到50个字符&nbsp;</font>
												</label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label"><font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>联系电话：</label>
											<div class="col-sm-6">
												<input type="text" name="scenic_phone" id="scenic_phone"
													class="form-control" value="${dataModel.scenic_phone}" />
											</div>
												<div class="col-sm-2">
												<label class="control-label"><font
												color="red" size="3px"
												style="font-size:11px;">1到20个字符&nbsp;</font>
												</label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label"><font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>景区图片：</label>
											<div class="col-sm-8">
												<div class="fileupload fileupload-new"
													data-provides="fileupload">
													<div class="fileupload-new thumbnail"
														style="max-height: 200px;">
														<img onerror="this.src='/statics/img/no-img.jpg'"
															src="<c:if test="${!empty dataModel.scenic_logo}">${SETTINGPD.IMAGEPATH}${dataModel.scenic_logo}</c:if>"
															height="100" width="200">
													</div>
													<div class="fileupload-preview fileupload-exists thumbnail"
														style="max-height: 200px; line-height: 20px;"></div>
													<div>
														<span class="btn btn-default btn-file"> <span
															class="fileupload-new">选择文件</span> <span
															class="fileupload-exists">重选</span> <input type="file"
															name="scenic_logo" id="scenic_logo" accept="image/*" />
														</span> <a href="#" class="btn btn-default fileupload-exists"
															data-dismiss="fileupload">移除</a>
													</div>
												</div>
											</div>
										</div>
										<!-- 音频文件 -->
										<div id="voice_file" class="form-group">
											<label class="col-sm-2 control-label"><font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>音频介绍：</label>
											<div class="col-sm-8">
												<div class="fileupload fileupload-new"
													data-provides="fileupload">
													<div class="fileupload-new thumbnail"
														style="max-height: 200px;">
														<audio controls="controls" >
														  <source src="${SETTINGPD.IMAGEPATH}${dataModel.scenic_voice_path}" type="audio/mpeg" />
														</audio>
													</div>
													<div class="fileupload-preview fileupload-exists thumbnail"
														style="max-height: 200px; line-height: 20px;"></div>
													<div>
														<span class="btn btn-default btn-file"> <span
															class="fileupload-new">选择文件</span> <span
															class="fileupload-exists">重选</span> <input type="file"
															name="scenic_voice_path" id="scenic_voice_path"  accept="audio/mpeg"/>
														</span> <a href="#" class="btn btn-default fileupload-exists"
															data-dismiss="fileupload">移除</a>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">点击次数：</label>
											<div class="col-sm-8">
												<input type="number" name="scenic_click" id="scenic_click"
													class="form-control" value="${dataModel.scenic_click}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label"><font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>景区简述（500字内）：</label>
											<div class="col-sm-6">
												<textarea rows="5" cols="45" name="scenic_desc"
													id="scenic_desc">${dataModel.scenic_desc}</textarea>
											</div>
											<div class="col-sm-2">
												<label class="control-label"><font
												color="red" size="3px"
												style="font-size:11px;">1到500个字符&nbsp;</font>
												</label>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label">景区提示（500字内）：</label>
											<div class="col-sm-8">
												<textarea rows="5" cols="45" name="scenic_tip"
													id="scenic_tip">${dataModel.scenic_tip}</textarea>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">状态：</label>
											<div class="col-sm-8">
												<label class="radio-inline"> <input type="radio"
													<c:if test="${empty dataModel.scenic_status || dataModel.scenic_status eq '1'}">checked='checked'</c:if>
													value="1" name="scenic_status">正常
												</label> <label class="radio-inline"> <input type="radio"
													value="2"
													<c:if test="${dataModel.venue_stutas eq '2'}">checked='checked'</c:if>
													name="scenic_status">隐藏
												</label>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-4 col-sm-offset-3">
												<button class="btn btn-primary" type="button"
													onclick="checkForm();">
													<i class="fa fa-check"></i>&nbsp;&nbsp;提 交&nbsp;&nbsp;
												</button>
												<button class="btn btn-warning" onclick="history.go(-1)">
													<i class="fa fa-close"></i>&nbsp;&nbsp;返 回&nbsp;&nbsp;
												</button>
											</div>
										</div>
									</div>
								</div>
								<div id="tab-2" class="tab-pane">
									<div class="panel-body">
										<div class="form-group">
											<label class="col-sm-2 control-label"><font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>详细地址：</label>
											<div class="col-sm-4">
												<textarea rows="2" cols="60" name="scenic_address"
													id="scenic_address" class="form-control"
													placeholder="请输入景区的详细地址点击一键定位">${dataModel.scenic_address}</textarea>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">景区半径：</label>
											<div class="col-sm-8">
												<input type="text" name="scenic_radius" id="scenic_radius"
													class="form-control" value="${dataModel.scenic_radius}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label"></label> <input
												type="hidden" name="scenic_lng" id="scenic_lng"
												value="${dataModel.scenic_lng}" /> <input type="hidden"
												name="scenic_lat" id="scenic_lat"
												value="${dataModel.scenic_lat}" />
											<div class="col-sm-4" id="map_show_id"
												style="width: 550px; height: 450px; border: 1px solid gray; margin: auto;">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-4 col-sm-offset-3">
												<button class="btn btn-primary" type="button"
													id="location_button">
													<i class="fa fa-check"></i>&nbsp;&nbsp;一键定位&nbsp;&nbsp;
												</button>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-4 col-sm-offset-3">
												<button class="btn btn-primary" type="button"
													onclick="checkForm();">
													<i class="fa fa-check"></i>&nbsp;&nbsp;提 交&nbsp;&nbsp;
												</button>
												<button class="btn btn-warning" onclick="history.go(-1)">
													<i class="fa fa-close"></i>&nbsp;&nbsp;返 回&nbsp;&nbsp;
												</button>
											</div>
										</div>
									</div>
								</div>
								<div id="tab-4" class="tab-pane">
									<div class="panel-body">
										<div class="ibox-content no-padding">
											<input type="hidden" name="scenic_content"
												id="scenic_content" />
											<div class="col-sm-7">
												<script id="editor" type="text/plain"
													style="width: 100%; height: 280px;">${dataModel.scenic_content}</script>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-4 col-sm-offset-3">
												<button class="btn btn-primary" type="button"
													onclick="checkForm();">
													<i class="fa fa-check"></i>&nbsp;&nbsp;提 交&nbsp;&nbsp;
												</button>
												<button class="btn btn-warning" onclick="history.go(-1)">
													<i class="fa fa-close"></i>&nbsp;&nbsp;返 回&nbsp;&nbsp;
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<script src="http://api.map.baidu.com/api?v=1.3"></script>
	<%@ include file="../common/foot.jsp"%>
	<script src="/statics/js/util/BaiduMap.js"></script>
	<script src="/assets/js/bootstrap-fileupload.js"></script>
	<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = "<%=path%>/statics/js/uedit/";
	</script>
	<script type="text/javascript" charset="utf-8"
		src="/statics/js/uedit/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="/statics/js/uedit/ueditor.all.min.js">
	</script>
	<script type="text/javascript" charset="utf-8"
		src="/statics/js/uedit/lang/zh-cn/zh-cn.js"></script>
	<script src="/statics/js/jquery-upload.js"></script>
	<script type="text/javascript">
		var ue = UE.getEditor('editor');
		function checkForm() {
			if (!$("#scenic_name").val().isNotEmpty() || $("#scenic_name").val().trim().length > 50) {
				alert("景区名字长度为0到50个字符之间");
				return false;
			}
			if (!$("#scenic_address").val().isNotEmpty()) {
				alert("景区地址不能为空");
				return false;
			}
			if ($("#scenic_phone").val().isEmpty() || $("#scenic_phone").val().trim().length > 20) {
				alert("联系电话长度为0到20个字符");
				return false;
			}
			var editF = '${dataModel.scenic_voice_path}';
			if(!editF && !$("#scenic_voice_path").val()){
				bootbox.alert("请选择音频文件");
				return false;
			}
			if ($("#scenic_desc").val().length > 500) {
				bootbox.alert("景区简述应该在500个字符以内");
				return false;
			}
			if (!$("#scenic_click").val()) {
				$("#scenic_click").val(0);
			}
			if ($("#scenic_click").val()) {
				if(!$("#scenic_click").val().isNumber()
						|| $("#scenic_click").val() < 0){
					bootbox.alert("点击次数必须为正整数");
					return false;
				}
			}
			if (!$("#scenic_address").val().isNotEmpty()) {
				alert("景区地址不能为空");
				return false;
			}
			if (!$("#scenic_radius").val()) {
				$("#scenic_radius").val(0);
			}
			if ($("#scenic_radius").val()) {
				if(!$("#scenic_radius").val().isNumber()
						|| $("#scenic_radius").val() < 0){
					bootbox.alert("景区半径必须为正整数");
					return false;
				}
			}
			$("#commentForm").submit();
		}
		//得到地图对象
		var map = BaiduMap.getMap("map_show_id");
		if ('${dataModel.scenic_address}' != '') {
			BaiduMap.searchByPosition(map, {
				keyword : '${dataModel.scenic_address}',
				lngId : "scenic_lng",
				latId : "scenic_lat"
			}, 6);
		} else {
			var point = new BMap.Point(114.069562, 22.555991);
			map.centerAndZoom(point, 12);
		}
		/*  BaiduMap.searchByAddress(map, "深圳市", 12);*/
		$("#location_button").click(function() {
			BaiduMap.searchByPosition(map, {
				keyword : $("#scenic_address").val(),
				lngId : "scenic_lng",
				latId : "scenic_lat"
			}, 12);
		});
		//详细地址栏失去焦点定位
		$("#scenic_address").blur(
				function() {
					if ($("#scenic_address").val().isNotEmpty()) {
						if (!$("#scenic_lng").val().isNotEmpty()
								|| !$("#scenic_lat").val().isNotEmpty()) {
							BaiduMap.searchByPosition(map, {
								keyword : $("#scenic_address").val(),
								lngId : "scenic_lng",
								latId : "scenic_lat"
							}, 12);
						} else {
							if (window.confirm('是否重新定位？')) {
								BaiduMap.searchByPosition(map, {
									keyword : $("#scenic_address").val(),
									lngId : "scenic_lng",
									latId : "scenic_lat"
								}, 12);
							}
						}
					}
				});
	</script>
</body>
</html>