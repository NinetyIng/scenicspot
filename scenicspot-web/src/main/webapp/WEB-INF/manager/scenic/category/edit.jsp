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
<%@ include file="../../common/top.jsp"%>
<link href="/assets/css/plugins/summernote/summernote.css"
	rel="stylesheet">
<link href="/assets/css/plugins/summernote/summernote-bs3.css"
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
</style>
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
							景区分类
						</h5>
					</div>
					<div class="ibox-content">
						<form action="/sys/scenic/categorysave" method="post"
							class="form-horizontal m-t" id="commentForm"
							enctype="multipart/form-data">

							<input type="hidden" name="id" id="id" value="${dataModel.id}" />
							<div class="form-group">
								<label class="col-sm-2 control-label"><font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>分类名称：</label>
								<div class="col-sm-6">
									<input type="text" name="scenic_category" id="scenic_category"
										class="form-control" value="${dataModel.scenic_category}"
										required />
								</div>
								<div class="col-sm-2">
												<label class="control-label"><font
												color="red" size="3px"
												style="font-size:11px;">1到6个字符&nbsp;</font>
												</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">是否需要音频文件：</label>
								<div class="col-sm-8">
						            <label class="radio-inline">
						                <input type="radio" <c:if test="${dataModel.scenic_is_voice eq 2}">checked="checked"</c:if>  value="2"  name="scenic_is_voice">不需要</label>
						            <label class="radio-inline">
						                <input type="radio" value="1" <c:if test="${dataModel.scenic_is_voice eq 1}">checked="checked"</c:if>  name="scenic_is_voice">需要</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">分类简述：</label>
								<div class="col-sm-8">
									<textarea placeholder="" name="scenic_desc" id="scenic_desc"
										class="form-control">${dataModel.scenic_desc}</textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label"><font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>分类icon（用于标注在百度地图上）：</label>
								<div class="col-sm-8">
									<div class="fileupload fileupload-new"
										data-provides="fileupload">
										<div class="fileupload-new thumbnail"
											style="max-height: 200px;">
											<img src="${SETTINGPD.IMAGEPATH}${dataModel.scenic_logo}" onerror="this.src='/statics/img/no-img.jpg'"
												height="65px" width="40px">
										</div>
										<div class="fileupload-preview fileupload-exists thumbnail"
											style="max-height: 200px; line-height: 20px;"></div>
										<div>
											<span class="btn btn-default btn-file"> <span
												class="fileupload-new">选择文件</span> <span
												class="fileupload-exists">重选</span> <input type="file"
												name="scenic_logo" id="scenic_logo"  accept="image/*"/>
											</span> <a href="#" class="btn btn-default fileupload-exists"
												data-dismiss="fileupload">移除</a>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">分类状态：</label>
								<div class="col-sm-8">
									<label class="radio-inline"><input type="radio"
										name="scenic_status" id="scenic_status" value="1"
										<c:if test="${empty dataModel.scenic_status || dataModel.scenic_status eq 1}">checked="checked"</c:if> />显示</label>
									<label class="radio-inline"><input type="radio"
										name="scenic_status" id="scenic_status" value="2"
										<c:if test="${not empty dataModel.scenic_status &&  dataModel.scenic_status eq 2}">checked="checked"</c:if> />隐藏</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label"><font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>排序：</label>
								<div class="col-sm-3">
									<input type="number" name="scenic_order_by"
										id="scenic_order_by" class="form-control"
										value="${dataModel.scenic_order_by}" required />
								</div>
									<div class="col-sm-2">
												<label class="control-label"><font
												color="red" size="3px"
												style="font-size:11px;">排序100以内&nbsp;</font>
												</label>
											</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-3">
									<button class="btn btn-primary" type="button"
										onclick="valication()">
										<i class="fa fa-check"></i>&nbsp;&nbsp;提 交&nbsp;&nbsp;
									</button>
									<button class="btn btn-warning" onclick="history.go(-1)">
										<i class="fa fa-close"></i>&nbsp;&nbsp;返 回&nbsp;&nbsp;
									</button>
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
	<%@ include file="../../common/foot.jsp"%>
	<script src="/assets/js/bootstrap-fileupload.js"></script>
	<!-- 自定义js -->
	<script type="text/javascript">
		function valication() {
			if ($("#scenic_category").val().isEmpty() || $("#scenic_category").val().length > 6) {
				bootbox.alert("分类名称在0到6个字符");
				return false;
			}
			if ($("#scenic_category").val().length > 10) {
				bootbox.alert("分类名称应该在10个字符以内");
				return false;
			}
			if (!$("#scenic_status").val() && $("#scenic_status").val() == null
					|| $("#scenic_status").val() == '') {
				bootbox.alert("请选择分类状态");
				return false;
			}
			if ($("#scenic_order_by").val().isEmpty()
					|| $("#scenic_order_by").val() < 0
					|| $("#scenic_order_by").val() > 100) {
				bootbox.alert("请输入正确的排序数字");
				return false;
			}
			$("#commentForm").submit();
		}
	</script>


</body>
</html>