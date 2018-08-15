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
					<div class="ibox-title">
						<h5>
							<c:if test="${not empty dataModel}">编辑</c:if>
							<c:if test="${empty dataModel}">新增</c:if>
							攻略
						</h5>
					</div>
					<div class="ibox-content">
						<form action="/sys/scenic/addScenicLine" method="post"
							class="form-horizontal m-t" id="commentForm"
							enctype="multipart/form-data">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#tab-1"
									aria-expanded="true">基本信息</a></li>
								<li class=""><a data-toggle="tab" href="#tab-2"
									aria-expanded="true">添加景点</a></li>
							</ul>
							<div class="tab-content">
								<div id="tab-1" class="tab-pane active">
									<div class="panel-body">
										<input type="hidden" name="id" id="id" value="${dataModel.id}" />
										<div class="form-group">
											<label class="col-sm-2 control-label"> <font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>攻略名称：
											</label>
											<div class="col-sm-6">
												<input type="text" name="line_name" id="line_name"
													class="form-control" value="${dataModel.line_name}" />
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
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>攻略图片：</label>
											<div class="col-sm-8">
												<div class="fileupload fileupload-new"
													data-provides="fileupload">
													<div class="fileupload-new thumbnail"
														style="max-height: 200px;">
														<img   onerror="this.src='/statics/img/no-img.jpg'"
															src="<c:if test="${!empty dataModel.line_logo}">${SETTINGPD.IMAGEPATH}${dataModel.line_logo}</c:if>"
															height="100" width="200">
													</div>
													<div class="fileupload-preview fileupload-exists thumbnail"
														style="max-height: 200px; line-height: 20px;"></div>
													<div>
														<span class="btn btn-default btn-file"> <span
															class="fileupload-new">选择文件</span> <span
															class="fileupload-exists">重选</span> <input type="file"
															name="line_logo" id="line_logo" accept="image/*" />
														</span> <a href="#" class="btn btn-default fileupload-exists"
															data-dismiss="fileupload">移除</a>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">导语：</label>
											<div class="col-sm-8">
												<textarea rows="10" cols="30" name="line_lead"
													id="line_lead">${dataModel.line_lead}</textarea>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label"> <font
												color="red" size="3px"
												style="font-weight: bold; font-style: italic;">*&nbsp;</font>攻略排序：</label>
											<div class="col-sm-6">
												<input type="number" name="line_order" id="line_order"
													class="form-control" value="${dataModel.line_order}" />
											</div>
												<div class="col-sm-2">
												<label class="control-label"><font
												color="red" size="3px"
												style="font-size:11px;">0到100之前的正整数&nbsp;</font>
												</label>
								</div>
											
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label">状态：</label>
											<div class="col-sm-8">
												<label class="radio-inline"> <input type="radio"
													<c:if test="${empty dataModel.line_status || dataModel.line_status eq '1'}">checked='checked'</c:if>
													value="1" name="line_status">正常
												</label> <label class="radio-inline"> <input type="radio"
													value="2"
													<c:if test="${dataModel.line_status eq '2'}">checked='checked'</c:if>
													name="line_status">隐藏
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

									<div class="ibox-tools">
										<a href="javascript:addScenic();"
											class="btn btn-primary btn-xs"><i class="fa fa-plus"></i>&nbsp;新增景点</a>
									</div>
									<div class="panel-body">
										<div class="row">
											<div class="col-sm-12">
												<div class="wrapper wrapper-content animated fadeInUp">
													<ul class="notes">

														<c:forEach items="${scenics}" var="item">
															<li>
																<div>
																	<small></small>
																	<h4>${item.scenic_name}</h4>
																	<p>
																		<img alt="" style='width: 170px; height: 100px;'
																			src="${SETTINGPD.IMAGEPATH}${item.scenic_logo}">
																	</p>
																	<a
																		href="javascript:;" onclick="deleteScenic('${item.id}',this,'${item.line_scenic_desc}');"><i
																		class="fa fa-trash-o "></i></a>
																</div>
															</li>
														</c:forEach>
													</ul>
													<input type="hidden" name="scenicLine" id="scenicLineStr" />
												</div>
											</div>
										</div>
										<div class="row">
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
	<script src="/statics/js/jquery-upload.js"></script>
	<script type="text/javascript">
    $(function(){
		$(".fileupload-exists").click(function(){
    		$(this).parents(".fileupload").children().eq(0).remove();
    	});
   });
	
	
	 //扩展数组remove方法
		 Array.prototype.remove = function(val) {
			var index = this.indexOf(val);
			if (index > -1) {
			   this.splice(index, 1);
			}
		}
		//定义提交到后台的景点数组  数组里面是
		var subDataModel = [];
		var scenic_ids = [];
		if('${scenicIds}'){
			scenic_ids = '${scenicIds}'.split(",");
		}
		if ('${lineScenicStr}') {
			subDataModel.push('${lineScenicStr}');
		}
		function addScenic() {
			var excludeScenicIds = scenic_ids.join(",");
			var index = layer
					.open({
						type : 2,
						title : '添加景点',
						maxmin : true,
						shadeClose : true, //点击遮罩关闭层
						area : [ '750px', '500px' ],
						content : "/sys/scenic/addLineScenicPage?excludeScenicIds="+excludeScenicIds+"&line_id=${dataModel.id}"
					});
			layer.full(index);
		}
		//子页面回调回来的
		function selectCallBack(scenic_id, scenic_name, scenic_logo, order_by) {
			subDataModel.push(scenic_id + "," + order_by);
			scenic_ids.push(scenic_id);
			$(".notes")
					.append(
							"<li><div><small></small> <h4>"
									+ scenic_name
									+ "</h4><p><img alt='' style='    width: 170px;height: 100px;' src='${SETTINGPD.IMAGEPATH}"+scenic_logo+"'> </p><a href='javascript:;'  onclick='deleteScenic("
									+ scenic_id
									+ ",this,"+order_by+");'><i class='fa fa-trash-o'></i></a> </div></li>");
		}
		//提交的表单进行参数校验
		function checkForm() {
			if ($("#line_name").val().isEmpty() || $("#line_name").val().length <= 0 || $("#line_name").val().length > 20) {
				bootbox.alert("攻略名称1到20个字符");
				return;
			}
			if ($("#line_lead").val().isEmpty() || $("#line_lead").val().length > 500) {
				bootbox.alert("导语500个字符以内");
				return;
			}
	    	if($("#line_order").val().isEmpty() ||!$("#line_order").val().isNumber()|| parseInt($("#line_order").val()) >100 || parseInt($("#line_order").val()) < 0){
	    		bootbox.alert("排序为0到100之前的正整数");
	    		return;
	    	}
			if (subDataModel.length == 0) {
				bootbox.alert("您还未选择景点信息");
				return;
			}
			$("#scenicLineStr").val(subDataModel.join("|"));
			$("#commentForm").submit();
		}
		//删除攻略景点
		function deleteScenic(id,thisObj,orderBy) {
			bootbox.confirm("确认删除该景点吗?", function(result) {
				if (result) {
				var line_id = '${dataModel.id}';
				var item = id+","+orderBy;
				subDataModel.remove(item);
				for(var i =0;i<scenic_ids.length;i++){
					if(id == scenic_ids[i]){
						scenic_ids.splice(i, 1);
					}
				}
				if(!line_id){
					$(thisObj).parent().parent().remove();
				}else{
							Ajax.request("/sys/scenic/deleteLineScenic", {
								"data" : {
									"ls_scenic_id" : id,
									"ls_line_id":line_id
								},
								"success" : function(data) {
									if (data.result == 200) {
										$(thisObj).parent().parent().remove();
									} else {
										bootbox.alert(data.msg);
									}
								}
							})
						
				}
				}
			});
		}
			
	</script>
</body>
</html>