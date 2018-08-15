<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/top.jsp"%>
</head>
<body>
	<div class="wrapper wrapper-content animated fadeInUp">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						<h5>文章栏目</h5>
						<div class="ibox-tools">
						<a href="/sys/content/category/edit?CATEGORY_ID=0"
							class="btn btn-primary btn-xs"> <i class="fa fa-plus"></i>&nbsp;新增</a>
					</div>
					</div>
					<div class="ibox-content">
						<form action="/sys/user/listPage" method="post" name="Form"
							id="Form">
							<div class="project-list">
								<table 
									class="center table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>名称</th>
											<th>编码</th>
											<th>栏目搜索标签</th>
											<th>状态</th>
											<th class="center" style="width: 155px">操作</th>
										</tr>
									</thead>
									<tbody class="tree-grid">
										<!-- 开始循环 -->
										<c:choose>
											<c:when test="${not empty varList}">
												<c:forEach items="${varList}" var="item">
													<tr data-pid="${item.PARENT_ID}"
														data-id="${item.CATEGORY_ID}">
														<td><img alt="" width="44px" height="58px"
															onerror="javascript:this.src='/statics/img/iphone6.png';"
															src="${SETTINGPD.IMAGEPATH}${item.CATEGORY_ICON}" />
															${item.CATEGORY_NAME}</td>
														<td>${item.CATEGORY_CODE}</td>
														<td>${item.CATEGORY_TAGS}</td>
														<td>
														  <c:if test="${item.DISABLED}">
														  <a class="btn btn-primary btn-rounded">正常</a>
														  </c:if>
														  <c:if test="${!item.DISABLED}">
														      <a class="btn btn-danger btn-rounded">已删除</a>
														  </c:if>
														</td>
														<td class="center">
															<a href="/sys/content/category/edit?CATEGORY_ID=${item.CATEGORY_ID}"
																	class="btn btn-primary btn-sm" title="编辑"> <i
																		class="fa fa-pencil"></i>
															</a>
															<c:if test="${item.CATEGORY_CODE != 'pb2wN' && item.CATEGORY_CODE != 'WgHM8'}">
																 <a href="javascript:del('${item.CATEGORY_CODE}');"
																	class="btn btn-warning btn-sm" title="删除"> <i
																		class="fa fa-trash"></i>
																</a>
															</c:if>
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
											<td colspan="10">
												<div class="dataTables_paginate paging_bootstrap pull-right">
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
	<!-- 页面底部js¨ -->
	<%@ include file="../common/foot.jsp"%>
	<script type="text/javascript">
		$(".tree-close-btn").trigger("click");
		function del(code){
			bootbox.confirm("确认删除该类目？",function(result){
				if(result){
					Ajax.request("/sys/content/category/del",{"data":{"CATEGORY_CODE":code},
						"success":function(data){
							if(data.result == 200){
							    window.location.reload();	
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