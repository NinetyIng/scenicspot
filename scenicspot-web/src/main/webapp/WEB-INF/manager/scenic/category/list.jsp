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
						<h5>景区分类</h5>
						<div class="ibox-tools">
								<a href="/sys/scenic/categoryeditPage"
									class="btn btn-primary btn-xs"><i class="fa fa-plus"></i>&nbsp;新增</a>
						</div>
					</div>
					<div class="ibox-content">
						<form action="/sys/scenic/categorylist" method="post"
							name="Form" id="Form">
							<div class="project-list">
								<table id="simple-table"
									class="center table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th style="width: 250px;">分类名称</th>
											<th>icon（用于将分类标注在百度地图上）</th>
											<th>分类简述</th>
											<th>是否需要音频描述</th>
											<th>分类状态</th>
											<th>创建时间</th>
											<th class="center" style="width: 85px">操作</th>
										</tr>
									</thead>

									<tbody class="tree-grid">
										<!-- 开始循环 -->
										<c:choose>
											<c:when test="${not empty dataModel}">
												<c:forEach items="${dataModel}" var="item">
													<tr>
														<td>${item.scenic_category}
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														<td><img alt="" onerror="this.src='/statics/img/no-img.jpg'"
															src="${SETTINGPD.IMAGEPATH}${item.scenic_logo}"
															height="65px" width="40px"></td>
														<td>${item.scenic_desc}</td>
														<td>
														  <c:if test="${item.scenic_is_voice eq '1'}">
														 <span class="badge badge-danger">需要</span> 
														  </c:if>
														  <c:if test="${empty item.scenic_is_voice || item.scenic_is_voice eq '2'}">
														  <span class="label label-success">不需要</span>  
														  </c:if>  
														</td>
														<td><c:if test="${item.scenic_status == 1}">
														 <span class="badge badge-success">显示</span> 
														</c:if> <c:if
																test="${item.scenic_status == 2}">
																 <span class="badge badge-danger">隐藏</span> 
																</c:if>
														</td>
														<td>${item.scenic_create_time}</td>
														<td class="center" style="width: 200px;"> 
														<%-- <c:if test="${QX.edit == 1}"> --%>
																<a
																	href="/sys/scenic/categoryeditPage?id=${item.id}"
																	class="btn btn-primary btn-sm" title="编辑"> <i
																	class="fa fa-pencil"></i>
																</a>
															<%-- </c:if>  --%>
															<%-- <c:if test="${QX.del == 1 }"> --%>
																<a href="javascript:void(0);"
																	onclick="deleteCate(${item.id});"
																	class="btn btn-warning btn-sm" title="删除"> <i
																	class="fa fa-trash"></i>
																</a>
															<%-- </c:if> --%>
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
	                                            <td colspan="8">
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
	<script src="/statics/plugin/treeGrid.js"></script>
	<script type="text/javascript">
	$(".tree-close-btn").trigger("click"); 
	//景区分类删除
	function deleteCate(category_id){
		bootbox.confirm("你确定要彻底删除！",function(result){
			if(result){
				$.ajax({
					   type: "POST",
					   url: "/sys/scenic/delete",
					   dataType : "json",
					   data : {
						   category_id: category_id
					   },
					   success: function(data){
						   if(data.code==200){
					      	   window.location.href='/sys/scenic/categorylist';
						   }else{
							   bootbox.alert(data.msg);
						   }
					   }
					});
			}
		})
	}
	</script>
</body>
</html>