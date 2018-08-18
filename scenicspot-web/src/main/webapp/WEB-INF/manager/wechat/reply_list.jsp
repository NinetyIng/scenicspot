<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="util" uri="/WEB-INF/tld/util-tags.tld"%> --%>
<%-- <%@ taglib prefix="page" uri="/WEB-INF/tld/page-tags.tld"%> --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<%@ include file="../common/top.jsp"%>
</head>
<body>
 <div class="wrapper wrapper-content animated fadeInUp">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						<h5>微信回复列表</h5>
						<div class="ibox-tools">
							<a href="/sys/wechat/edit_reply.do" class="btn btn-primary btn-xs"><i class="fa fa-plus"></i>&nbsp;新增</a>
						</div>
					</div>
					<div class="ibox-content">
						<form action="lot/list.do" method="post" name="Form" id="Form">
							<div class="project-list">
 								<table id="simple-table" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center" width="25px">#</th>
											<th>搜索关键词</th>
											<th>回复类型</th>
											<th>是否可用</th>
											<th>创建人</th>
											<th>创建时间</th>
											<th class="center" style="width: 85px">操作</th>
										</tr>
									</thead>

									<tbody>
										<!-- 开始循环 -->
										<c:choose>
											<c:when test="${not empty dataList}">
												 <c:forEach items="${dataList}" var="item">
													<tr>
														<td align="center">${item.id}</td>
														<td>${item.key_word}</td>
														<td>
															<c:if test="${item.reply_type == '0' }"><span class="label label-sm label-warning">文字</span></c:if>
															<c:if test="${item.reply_type == '1' }"><span class="label label-success arrowed-in arrowed-in-right">图文</span></c:if>
															<c:if test="${item.reply_type == '2' }"><span class="label label-sm label-info arrowed arrowed-righ">图片</span></c:if>
														</td>
														<td>
															<c:if test="${item.status == '2' }"><span class="label label-important arrowed-in">无效</span></c:if>
															<c:if test="${item.status == '1' }"><span class="label label-success arrowed">有效</span></c:if>
														</td>
														<td>${item.creator}</td>
														<td>${item.create_time}</td>

														<td class="center">
															<a href="/sys/wechat/edit_reply.do?id=${item.id}" class="btn btn-primary btn-sm" title="编辑">
                           										<i class="fa fa-pencil"></i>
                       										</a>
                       										<a href="/sys/wechat/del_reply?id=${item.id}" class="btn btn-warning btn-sm" title="删除" OnClick="return confirm('确认删除吗？')">
                           										<i class="fa fa-trash"></i>
                       										</a>
                       									</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr class="main_info">
													<td colspan="100" class="center">没有相关数据</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
									<c:if test="${dataList!=null}">
										<tfoot>
	                                        <tr>
	                                            <td colspan="7">
	                                               	<div class="dataTables_paginate paging_bootstrap pull-right">
	                                               		${page.pageStr}
													</div>	
	                                            </td>
	                                        </tr>
	                                    </tfoot>
									</c:if>
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
	<%@ include file="../common/foot.jsp"%>
</body>
</html>