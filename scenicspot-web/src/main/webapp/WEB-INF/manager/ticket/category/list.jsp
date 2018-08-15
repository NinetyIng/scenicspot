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
					<form action="/sys/ticket/categoryList" method="post" name="Form"
						id="Form">
						<div class="ibox-title">
							<h5>景区列表</h5>
							<div class="ibox-tools">
								<a href="javascript:openEdit();" class="btn btn-primary btn-xs"><i
									class="fa fa-plus"></i>&nbsp;新增景区</a>
							</div>
						</div>
						<div class="ibox-content">
							<div class="search-condition row">
							<div class="col-md-2">
									<div class="input-group">
										<span class="input-group-addon">景区名称：</span> <input type="text"
											name="scenic_name" class="form-control" value="${pd.scenic_name}">
									</div>
								</div>
								<div class="col-md-1">
									<select class="form-control" name="scenic_status">
										<option value=''>景区状态</option>
										<option value='1'
											<c:if test="${pd.scenic_status eq 1}">selected = 'selected'</c:if>>
											正常</option>
										<option value='2'
											<c:if test="${pd.scenic_status eq 2}">selected = 'selected'</c:if>>
											隐藏</option>
									</select>
								</div>
								<div class="col-md-2">
									<div class="input-group">
										<button class="btn btn-primary" type="submit">
											<i class="fa fa-search"></i>&nbsp;查询&nbsp;
										</button>
									</div>
								</div>
							</div>
						</div>
						<div class="ibox-content">
							<div class="project-list">
								<table id="simple-table"
									class="center table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>票类目名称</th>
											<th>适用人群</th>
											<th>排序值</th>
											<th class="center" style="width: 200px">操作</th>
										</tr>
									</thead>
									<tbody>
										<!-- 开始循环 -->
										<c:choose>
											<c:when test="${not empty dataModel}">
												<c:forEach items="${dataModel}" var="item">
													<tr>
														<td>${item.cate_name}</td>
														<td>
														   ${item.cate_fit}
														</td>
														<td>${item.cate_desc}</td>
														<td class="center">
														<%-- <c:if test="${QX.edit == 1}"> --%>
														<a
															href="javascript:openEdit('${item.id}');"
															class="btn btn-primary btn-sm" title="编辑"> <i
																class="fa fa-pencil"></i>
														</a> 
														<%-- </c:if> --%>
														<a
															href="javascript:del('${item.id}','${item.cate_name}');" 
															class="btn btn-warning btn-sm" title="删除"> <i
																class="fa fa-trash"></i>
														</a>
														</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr class="main_info">
													<td colspan="12" class="center">没有相关数据</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
									<tfoot>
										<tr>
											<td colspan="12">
												<div class="dataTables_paginate paging_bootstrap pull-right">
													${page.pageStr}</div>
											</td>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../common/foot.jsp"%>
		<script type="text/javascript">
		//景区相册
		function del(scenic_id,name){
			bootbox.confirm("确定删除"+name,function(result){
				if(result){
					Ajax.request("/sys/ticket/categoryDel",{"data":{"id":scenic_id},"success":function(data){
						if(data.result == 200){
							bootbox.alert("成功删除");
							window.location.reload();
						}
					}});
				}
			});
		}
		function openEdit(scenic_id){
			var url = "/sys/ticket/categoryEditPage";
			if(scenic_id){
				url += "?id="+scenic_id;
			}
			var index = layer.open({
	            type: 2,
	            title: '票类目编辑',
	            maxmin: true,
	            shadeClose: true, //点击遮罩关闭层
	            area : ['850px' , '600px'],
	            content: url,
	        });
		}
	</script>
</body>
</html>