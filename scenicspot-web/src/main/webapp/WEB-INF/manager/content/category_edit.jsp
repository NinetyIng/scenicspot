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
	<style type="text/css">
		.sel{
		    border: none !important;height: 32px !important;text-align: center!important;
		}
		.input-addon{
			padding: 0px !important; width: 80px !important;
		}
		.curr-val{
		}
	</style>
	<%@ include file="../common/top.jsp"%>
	   <link rel="stylesheet" href="/assets/ace/css/bootstrap.css" />
		<link rel="stylesheet" href="/assets/ace/css/font-awesome.css" />
		<link rel="stylesheet" href="/assets/ace/css/ace-fonts.css" />
		<link rel="stylesheet" href="/assets/ace/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<script src="/assets/ace/js/ace-extra.js"></script>
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>编辑类目</h5>
                    </div>
                    <div class="ibox-content">
                        <form action="/sys/content/category/editAction.do" method="post" class="form-horizontal m-t" id="editForm" enctype="multipart/form-data">
                          <%--  <input type="hidden" name=user_id value="${dataModel.user_id}" />
                          CATEGORY_ICON,CATEGORY_NAME,CATEGORY_TAGS,PARENT_ID,ORDER_BY,DISABLED
                           --%>
                            <input type="hidden" name="CATEGORY_ID" value="${dataModel.CATEGORY_ID}" />
							<div class="row">
	                            <div class="col-sm-6 b-r">
                                	<div class="form-group">
		    							<label class="col-sm-2 control-label">类别名字：</label>
		    							<div class="col-sm-8">
		       						         <div class="input-group">
				                                <input type="text" class="form-control" name="CATEGORY_NAME" value="${dataModel.CATEGORY_NAME}">
				                            </div>
		    							</div>
									</div>
									<div class="form-group">
		    							<label class="col-sm-2 control-label">ICON：</label>
		    							<div class="col-sm-8">
		       						         	<div class="fileupload fileupload-new" data-provides="fileupload">
													<div class="fileupload-new thumbnail" style="max-height: 200px;">
														<img src="${SETTINGPD.IMAGEPATH}${dataModel.CATEGORY_ICON}" height="100" width="200">
													</div>
													<div class="fileupload-preview fileupload-exists thumbnail"
														style="max-height: 200px; line-height: 20px;"></div>
													<div>
														<span class="btn btn-default btn-file"> 
															<span class="fileupload-new">选择文件</span> 
															<span class="fileupload-exists">重选</span> 
															 <input type="file" name="CATEGORY_ICON" id="CATEGORY_ICON"/>
														</span> 
														<a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">移除</a>
													</div>
										        </div>
		    							</div>
									</div>
									<div class="form-group">
		    							<label class="col-sm-2 control-label">搜索标签：</label>
		    							<div class="col-sm-8">
		       						         <div class="input-group">
				                                <input type="text" class="form-control" name="CATEGORY_TAGS"  value="${dataModel.CATEGORY_TAGS}" />
				                            </div>
		    							</div>
									</div>
									<!-- 排序 -->
									<div class="form-group">
		    							<label class="col-sm-2 control-label">排序：</label>
		    							<div class="col-sm-8">
		       						         <div class="input-group">
				                                <input type="number" class="form-control" name="ORDER_BY" value="${dataModel.ORDER_BY}" />
				                            </div>
		    							</div>
									</div>
									 <div class="form-group">
		                                <div class="col-sm-4 col-sm-offset-3">
		                                    <button class="btn btn-primary" type="submit"><i class="fa fa-check"></i>&nbsp;&nbsp;提   交&nbsp;&nbsp; </button>
		                                    <button class="btn btn-warning" type="button" onclick="history.go(-1)"><i class="fa fa-close"></i>&nbsp;&nbsp;返   回&nbsp;&nbsp; </button>
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
	<%@ include file="../common/foot.jsp"%>
	<script src="/assets/js/bootstrap-fileupload.js"></script>
	<script type="text/javascript">
	
	    $(function(){
			$(".fileupload-exists").click(function(){
	    		$(this).parents(".fileupload").children().eq(0).remove();
	    	});
	   });
	
		function submitForm(){
			app.submitForm("editForm",onSuccess,onError);
		}
		function onSuccess(data){
			app.toast("不可思议,居然做对了", "老子是个提示!","success");
		}
		function onError(){
			app.toast("你特码的是不是傻!", "老子是个提示!","error");
		}
	</script>
	
</body>
</html>