<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="../../common/top.jsp"%>
</head>
<body class="no-skin">
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox">
				<div class="ibox-title">
					<h5>SEO信息</h5> &nbsp;&nbsp;
					<span style="color:red;">操作说明：该功能不支持删除和新增操作。只提供主页的seo值和网站标题，详情页参照产品标题</span>
				</div>
				<div class="ibox-content">
					<form action="" method="post" name="Form" id="Form">
						<div class="search-condition row">
							<div class="col-md-5">
								<div class="input-group">
								<%-- 	<c:if test="${QX.add == 1}">
										<button class="btn btn-primary" onclick="javascript:window.location.href='/seo/seoAddEdit'"
											type="button">
											<i class="fa fa-plus"></i>&nbsp;添加
										</button>
									</c:if> --%>
								</div>
							</div>
						</div>
						<div class="hr-line-dashed" style="margin: 10px 0;"></div>
						<div class="project-list">
							<table id="simple-table"
								class="center table table-striped table-bordered table-hover">
								<thead>
									<th class="center">网页名称</th>
									<th class="center">网页title</th>
									<th class="center">SEO-CODE值</th>
									<th class="center">SEO-搜索值</th>
									<th class="center">创建时间</th>
									<th class="center">操作</th>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${not empty seolist}">
											<c:forEach items="${seolist}" var="item" varStatus="vs">
												<tr>
													<td class="center">${item.seo_title}</td>
													<td class="center">${item.seo_web_title}</td>
													<td class="center">${item.seo_code}</td>
													<td class="center">${item.seo_value}</td>
													<td class="center">${item.create_time}</td>
													<td class="center">
													<c:if test="${QX.edit != 1 && QX.del != 1 }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
														<div class="hidden-sm hidden-xs btn-group">
															<c:if test="${seolist.size()>0}">
																<c:if test="${QX.edit == 1}">
																	<a class="btn btn-xs btn-success" title="编辑"
																		onclick="javascript:window.location.href='/seo/seoAddEdit.do?id=${item.id}'">
																		<i class="ace-icon fa fa-pencil-square-o bigger-120"
																		title="编辑"></i>
																	</a>
																</c:if>
															<%-- 	 <c:if test="${QX.del == 1}">
																	<a class="btn btn-xs btn-danger" 
																		onclick="delseo(${item.id});"> <i
																		class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>
																	</a>
																</c:if> --%>
															</c:if>
														</div>
													</td>
												</tr>

											</c:forEach>
											<c:if test="${QX.cha == 0 }">
												<tr>
													<td colspan="10" class="center">您无权查看</td>
												</tr>
											</c:if>
										</c:when>
										<c:otherwise>
											<tr class="main_info">
												<td colspan="10" class="center">没有相关数据</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="11" style="vertical-align: top;">
											<div class="pagination"
												style="float: right; padding-top: 0px; margin-top: 0px;">${page.pageStr}</div>
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

	<!-- 页面底部js¨ -->
	<%@ include file="../../common/foot.jsp"%>
	<script type="text/javascript" src="/statics/js/user/User.js"></script>
	<script type="text/javascript">
	
	  function delseo(id){
		if(window.confirm("当前项正在使用中，您确定要删除吗？")){
			Ajax.request("/seo/deleteseo",{"data":{"id":id},"success":function(data){
				   if(data.result == "200"){
					   bootbox.alert("删除成功！");
					   window.location.reload();
				   }else{
					   bootbox.alert("删除失败！");
				   }
				}});
			}
		}

	
	
	</script>

</body>
</html>
