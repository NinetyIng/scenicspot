<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<form action="/sys/user/editAction.do" method="post"
				class="form-horizontal m-t" id="commentForm">
				<input type="hidden" value="${dataModel.user_id}" name="user_id" />
				<div class="col-sm-13">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>
								订单信息
							</h5>
						</div>
						<div class="ibox-content">
							<input type="hidden" name="id" value="${dataModel.id}" />
							<ul class="nav nav-tabs">
								<li class=""><a data-toggle="tab" href="#tab-1"
									aria-expanded="true">基本信息</a></li>
								<li class="active"><a data-toggle="tab" href="#tab-2"
									aria-expanded="true">捐赠记录</a></li>
							</ul>
							<div class="tab-content">
								<div id="tab-1" class="tab-pane">
									<div class="panel-body">
									   <div class="form-group">
											<label class="col-sm-3 control-label">微信标识：</label>
											<div class="col-sm-3">
												<input type="text"  class="form-control"
													value="${dataModel.open_id}" disabled="disabled" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">用户名：</label>
											<div class="col-sm-3">
												<input type="text"  class="form-control"
													value="${dataModel.nick_name}" disabled="disabled" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">用户图像：</label>
											<div class="col-sm-3">
												<img width="60px;" height="60px" 
											onerror="javascript:this.src='/statics/img/mis-img100.jpg';"
																		src="${dataModel.headimgurl}"
													 />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">注册时间：</label>
											<div class="col-sm-3">
												<input type="text" id="change_time" class="form-control"
													disabled="disabled" value="${dataModel.register_time}"
													required />
											</div>
										</div>
									</div>
								</div>
								<div id="tab-2" class="tab-pane active">
									<div class="panel-body">
									    <div class="project-list">
								<table id="simple-table"
									class="center table table-striped table-bordered table-hover">
									<thead>
										<tr>
										    <th>订单号</th>
											<th>捐赠项目</th>
											<th>捐赠金额</th>
											<th>支付状态</th>
											<th>捐赠时间</th>
											<th>捐赠寄语</th>
											<th>匿名捐赠</th>
											<th>组队信息</th>
											<th class="center">操作</th>
										</tr>
									</thead>
									<tbody>
										<!-- 开始循环 -->
										<c:choose>
											<c:when test="${not empty contributionList}">
												<c:forEach items="${contributionList}" var="item">
													<tr>
													    <td>
													    <a href="/sys/charitable/addPage?p_id=${item.p_id}">
													    ${item.c_order_no}
													    </a>
													    </td>
														<td>
														<a href="/sys/charitable/addPage?p_id=${item.p_id}">
														<p>${item.p_name}</p>
														</a>
														</td>
														<td>
														<span class="label label-primary">${item.c_money}元</span>
																		</td>
														<td>
														 <c:if test="${item.c_state eq 1}">
														     <span class="badge badge-info">支付成功</span>
														 </c:if>
														  <c:if test="${item.c_state eq 2}">
														     <span class="badge badge-warning">未支付</span>
														 </c:if>
														   <c:if test="${item.c_state eq 3}">
														     <span class="badge badge-danger">支付异常</span>
														 </c:if>
													    </td>				
														<td>${item.c_time}
														</td>
														<td>
													${item.c_comment}
													  <c:if test="${item.c_is_show_comment eq '1'}">
													  <span class="badge badge-danger">显示</span>
													  </c:if>
													   <c:if test="${empty item.c_is_show_comment || item.c_is_show_comment ne '1'}">
													   <span class="badge badge-danger">不显示</span>
													  </c:if>
														</td>
														<td>
														<c:if test="${item.c_anonymous eq 1}">
													       <span class="badge badge-danger">匿名</span>
														</c:if>
														<c:if test="${empty item.c_anonymous || item.c_anonymous ne 1}">
													       <span class="badge badge-info">否</span>
														</c:if>
														</td>
														<td>
														 <c:if test="${item.c_isteam eq '1'}">
														   <button type="button" class="btn btn-primary"  data-toggle="popover" >${item.c_ct_name}</button>
														 </c:if>
														</td>
														<td class="center" style="width: 200px;">
																<a href="/sys/contribution/addPage?c_id=${item.id}"
																	class="btn btn-primary btn-sm" title="详情"> 
																	<i class="fa fa-pencil"></i>
																</a>
															    <a href="/sys/charitable/addPage?c_id=${item.id}"
																	class="btn btn-primary btn-sm" title="系统日志"> 
																	<i class="fa fa fa-wrench"></i>
																</a>
															</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr class="main_info">
													<td colspan="10" class="center">没有相关数据</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../common/foot.jsp"%>
</body>
</html>