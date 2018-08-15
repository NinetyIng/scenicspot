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
	<%@ include file="../../common/top.jsp"%>
</head>
<body>
 <div class="wrapper wrapper-content animated fadeInUp">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						<h5>模块列表</h5>
						<div class="ibox-tools">
							<a href="/sys/upm/template/templateEdit" class="btn btn-primary btn-xs"><i class="fa fa-plus"></i>&nbsp;新增</a>
						</div>
					</div>
					<div class="ibox-content">
						<form action="/sys/upm/template/templateList" method="post" name="Form" id="Form">
							<div class="project-list">
 								<table id="simple-table" class="center table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>编号</th>
											<th>所属页面</th>
											<th>排序</th>
											<th>模块标题</th>
											<th>模块类型</th>
											<th>间距</th>
											<th>标题显示</th>
											<th>是否启用</th>
											<th>创建人</th>
											<th>创建时间</th>
											<th class="center" style="width: 85px">操作</th>
										</tr>
									</thead>

									<tbody>
										<!-- 开始循环 -->
										<c:choose>
											<c:when test="${not empty varList}">
												<c:forEach items="${varList}" var="item">
													<tr>
														<td>${item.template_id}</td>
														<td>${item.page_code}</td>
														<td>${item.sort}</td>
														<td>${item.template_title}</td>
														<td>${item.template_type}</td>
														<td><c:if test="${item.is_margin == 1}">是</c:if>
                                                            <c:if test="${item.is_margin == 0}">否</c:if>
                                                        </td>
                                                        <td><c:if test="${item.is_show_title == 1}">是</c:if>
                                                            <c:if test="${item.is_show_title == 0}">否</c:if>
                                                        </td>
														<td>
														    <c:if test="${item.is_enable == 1}">是</c:if>
                                                            <c:if test="${item.is_enable == 0}">否</c:if>
														</td>
														<td>${item.creator}</td>
														<td>${item.create_time}</td>
														<td class="center" style="width: 200px;">
															<a href="/sys/upm/template/templateEdit?id=${item.template_id}" class="btn btn-primary btn-sm" title="编辑">
                           										<i class="fa fa-pencil"></i>
                       										</a>
                       										<c:choose>
                       										    <c:when test="${item.is_enable == 0}"><a href="/sys/upm/template/templateUpdate?template_id=${item.template_id}&type=1" class="btn btn-primary btn-sm" title="启用">
                           										<i class="fa fa-eye"></i></a></c:when>
                       										    <c:when test="${item.is_enable == 1}"><a href="/sys/upm/template/templateUpdate?template_id=${item.template_id}&type=0" class="btn btn-danger btn-sm" title="禁用">
                           										<i class="fa fa-eye-slash"></i></a></c:when>
                       										</c:choose>
                       										<a href="javascript:void(0);" onclick="deleteTemplate(${item.template_id});" class="btn btn-warning btn-sm" title="删除">
                           										<i class="fa fa-trash"></i>
                       										</a>
                       									</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr class="main_info">
													<td colspan="7" class="center">没有相关数据</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
										<tfoot>
	                                        <tr>
	                                            <td colspan="12">
	                                               	<div class="dataTables_paginate paging_bootstrap pull-right">
	                                               		${page.pageStr}
													</div>	
	                                            </td>
	                                        </tr>
	                                    </tfoot>
								</table>
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
	//模板删除
	function deleteTemplate(template_id){
		var r=confirm("你确认要彻底删除吗！");
		if (r==true){
			$.ajax({
				   type: "POST",
				   url: "/sys/upm/template/templateDelete",
				   dataType : "json",
				   data : {
					   template_id: template_id
				   },
				   success: function(data){
				      alert(data.msg);
				      window.location.href='/sys/upm/template/templateList';
				   },
				   error : function(data){
					  alert(data.msg);
					  window.location.href='/sys/upm/template/templateList';
				   }
				});
		 }
		
	}
	
	</script>
</body>
</html>