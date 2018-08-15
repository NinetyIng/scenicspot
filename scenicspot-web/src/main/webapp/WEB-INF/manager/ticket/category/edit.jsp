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
<link href="/statics/css/phone.css" rel="stylesheet">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<form action="/sys/ticket/categoryEdit" method="post"
							class="form-horizontal m-t" id="commentForm"
							>
									<div class="panel-body">
										<input type="hidden" name="id" id="id" value="${dataModel.id}" />
										<div class="form-group">
											<label class="col-sm-2 control-label"><font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>类目名称：</label>
											<div class="col-sm-6">
												<input type="text" name="cate_name" id="cate_name"
													class="form-control" value="${dataModel.cate_name}" />
											</div>
											<div class="col-sm-2">
												<label class="control-label"><font
												color="red" size="3px"
												style="font-size:11px;">1到50个字符&nbsp;</font>
												</label>
											</div>
										</div>
										<!-- 音频文件 -->
										<div class="form-group">
											<label class="col-sm-2 control-label">使用人群（500字内）：</label>
											<div class="col-sm-8">
												<textarea rows="5" cols="45" name="cate_fit"
													id="cate_fit">${dataModel.cate_fit}</textarea>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label"><font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>序号：</label>
											<div class="col-sm-6">
												<input type="text" name="cate_desc" id="cate_desc"
													class="form-control" value="${dataModel.cate_desc}" />
											</div>
											<div class="col-sm-2">
												<label class="control-label"><font
												color="red" size="3px"
												style="font-size:11px;">1到100之间正整数&nbsp;</font>
												</label>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-4 col-sm-offset-3">
												<button class="btn btn-primary" type="button"
													onclick="checkForm();">
													<i class="fa fa-check"></i>&nbsp;&nbsp;提 交&nbsp;&nbsp;
												</button>
												<button class="btn btn-warning" onclick="closed()">
													<i class="fa fa-close"></i>&nbsp;&nbsp;关闭&nbsp;&nbsp;
												</button>
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
	<%@ include file="../../common/foot.jsp"%>
	<script type="text/javascript">
	
	  var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	  function closed(){
		  parent.layer.close(index);
	   }
		function checkForm() {
			if ($("#cate_name").val().length > 500) {
				bootbox.alert("类目提示应该在500个字符以内");
				return false;
			}
			
			if ($("#cate_fit").val().length > 500) {
				bootbox.alert("使用人群提示应该在500个字符以内");
				return false;
			}
			if ($("#cate_desc").val() == null
					|| $("#cate_desc").val() == ''
					|| !$("#cate_desc").val().isNumber()
					|| $("#cate_desc").val() < 0) {
				bootbox.alert("排序值为正整数");
				return false;
			}
			Ajax.request("/sys/ticket/categoryEdit",{"data":$("#commentForm").serialize(),"success":function(data){
				if(data.result == 200){
					parent.location.reload();
					closed();
					
				}
			}})
		}
	</script>
</body>
</html>