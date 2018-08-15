<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../../common/top.jsp"%>
</head>
<body>
 <div class="wrapper wrapper-content animated fadeInUp">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						<h5>菜单列表</h5>
						<div class="ibox-tools">
							<a href="/sys/upm/menu/edit" class="btn btn-primary btn-xs"><i class="fa fa-plus"></i>&nbsp;新增</a>
						</div>
					</div>
					<div class="ibox-content">
						 <div class="col-sm-6">
	                        <div id="tree" class="test"></div>
	                    </div>
	                    <div class="col-sm-6">
	                        <div id="event_output">
	                        	<form action="/sys/upm/menu/save" method="post" class="form-horizontal m-t" id="commentForm">
                             
							<input type="hidden" name="parent_id" value="${dataModel.parent_id}" />
							<input type="hidden" name="menu_id" value="${dataModel.menu_id}" />
							<div class="form-group">
    							<label class="col-sm-2 control-label">上级菜单：</label>
    							<div class="col-sm-8">
       								<input type="text" name="order_no" id="order_no" class="form-control" value="顶级菜单" disabled="disabled"/>
    							</div>
							</div>
							
							<div class="form-group">
    							<label class="col-sm-2 control-label">菜单名称：</label>
    							<div class="col-sm-8">
       							<input type="text" name="menu_name" id="menu_name" class="form-control" value="${dataModel.menu_name}" required/>
    							</div>
							</div>
							
							<div class="form-group">
    							<label class="col-sm-2 control-label">菜单地址：</label>
    							<div class="col-sm-8">
       								<input type="text" name="menu_url" id="menu_url" class="form-control" value="${dataModel.menu_url}"/>
    							</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">操作：</label>
								<div class="col-sm-8">
									<label class="checkbox-inline"> 
										<input	type="checkbox" name="actions" id="create" value="add">新增
									</label> 
									<label class="checkbox-inline"> 
										<input type="checkbox" name="actions" id="del" value="del">删除
									</label>
									<label class="checkbox-inline"> 
										<input type="checkbox" name="actions" id="edit" value="edit">编辑
									</label>
									<label class="checkbox-inline"> 
										<input type="checkbox" name="actions" id="export" value="export">导出
									</label>
									<label class="checkbox-inline"> 
										<input type="checkbox" name="actions" id="import" value="import">导入
									</label>
								</div>
							</div>
							
							<div class="form-group">
    							<label class="col-sm-2 control-label">排序号码：</label>
    							<div class="col-sm-8">
       								<input type="text" name="order_no" id="order_no" class="form-control" value="${dataModel.order_no}"/>
    							</div>
							</div>
							
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit"><i class="fa fa-check"></i>&nbsp;&nbsp;提   交&nbsp;&nbsp; </button>
                                </div>
                            </div>
                        </form>
	                        </div>
	                    </div>
	                    <div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../common/foot.jsp"%>
	<script src="/assets/js/plugins/treeview/bootstrap-treeview.js"></script>
</body>
</html>