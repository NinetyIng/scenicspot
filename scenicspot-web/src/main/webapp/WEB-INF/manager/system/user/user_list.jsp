<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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
						<h5>系统用户列表</h5>
					</div>
					<div class="ibox-content">
						<form action="/user/listUsers" method="post" name="Form" id="Form">
							<div class="search-condition row">
								<div class="col-md-2">
                                    <div class="input-group">
                                      <span class="input-group-addon">角色</span>
		                                    <select class="form-control m-b" name="ROLE_ID">
		                                        <option value="">全部</option>
		                                    <c:forEach items="${roleList}" var="item">
		                                        <option value="${item.ROLE_ID}" <c:if test="${pd.role eq item.ROLE_ID}"> selected ='selected' </c:if>  >${item.ROLE_NAME}</option>
		                                    </c:forEach>
		                                    </select>
		                            </div>
                                </div>
								<div class="col-md-2">
                                    <div class="input-group">
	    	                            <span class="input-group-addon">关键字</span>
		                                <input type="text" name="keywords" value="${pd.keywords }" class="form-control">
		                            </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="input-group">
	    	                           <button class="btn btn-primary" onclick="User.submitForm();" type="button" id="query_button_id">
													<i class="fa fa-search"></i>&nbsp;查询&nbsp;
									   </button>
		                            </div>
                                </div>
                                     <div class="col-md-5">
                                    <div class="input-group">
									    <button  class="btn btn-primary" onclick="User.add()" type="button"><i class="fa fa-plus"></i>&nbsp;添加</button>
		                            </div>
                                </div>
							</div>
							<div class="hr-line-dashed" style="margin: 10px 0;"></div>
							<div class="project-list">
 								<table id="simple-table" class="center table table-striped table-bordered table-hover">
									<thead>
										<th class="center">用户名</th>
										<th class="center">姓名</th>
										<th class="center">角色</th>
										<th class="center"><i class="ace-icon fa fa-envelope-o"></i>邮箱</th>
										<th class="center"><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>最近登录</th>
										<th class="center">上次登录IP</th>
										<th class="center">操作</th>
										</thead>
									<tbody>
										<c:choose>
								<c:when test="${not empty userList}">
								    <c:if test="${QX.cha == 1 }">
									<c:forEach items="${userList}" var="user" varStatus="vs">
										<tr>
											<td class="center"><a onclick="viewUser('${user.USERNAME}')" style="cursor:pointer;">${user.USERNAME }<c:if test="${not empty user.s_name}">（${user.s_name}）</c:if></a></td>
											<td class="center">${user.NAME }</td>
											<td class="center">${user.ROLE_NAME }</td>
											<td class="center"><a title="发送电子邮件" style="text-decoration:none;cursor:pointer;" <c:if test="${QX.email == 1 }">onclick="sendEmail('${user.EMAIL }');"</c:if>>${user.EMAIL }&nbsp;<i class="ace-icon fa fa-envelope-o"></i></a></td>
											<td class="center">${user.LAST_LOGIN}</td>
											<td class="center">${user.IP}</td>
											<td class="center">
												<c:if test="${QX.edit != 1 && QX.del != 1 }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<c:if test="${QX.edit == 1 }">
													<a class="btn btn-xs btn-success" title="编辑" onclick="User.editUser('${user.USER_ID}');">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
													</a>
													</c:if>
													<c:if test="${QX.del == 1 && user.USERNAME !='seller'}">
													<a class="btn btn-xs btn-danger" onclick="User.delUser('${user.USER_ID }','${user.USERNAME }');">
														<i class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>
													</a>
													</c:if>
												</div>
												<div class="hidden-md hidden-lg">
													<div class="inline pos-rel">
														<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
															<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
														</button>
														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
															<c:if test="${QX.edit == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="User.editUser('${user.USER_ID}');" class="tooltip-success" data-rel="tooltip" title="修改">
																	<span class="green">
																		<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																	</span>
																</a>
															</li>
															</c:if>
															<c:if test="${QX.del == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="User.delUser('${user.USER_ID }','${user.USERNAME }');" class="tooltip-error" data-rel="tooltip" title="删除">
																	<span class="red">
																		<i class="ace-icon fa fa-trash-o bigger-120"></i>
																	</span>
																</a>
															</li>
															</c:if>
														</ul>
													</div>
												</div>
											</td>
										</tr>
									
									</c:forEach>
									</c:if>
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
	                                            <td colspan="11" style="vertical-align:top;">
														<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
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
		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../common/foot.jsp"%>
	<script type="text/javascript" src="/statics/js/user/User.js"></script>
	
	</body>
</html>
