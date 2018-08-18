<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<table style="margin-top: 8px;">
								<tr height="35">
									<td style="width:69px;"><a href="javascript:Role.addRole(0);" class="btn btn-sm btn-success">新增组</a></td>
										<c:choose>
										<c:when test="${not empty roleList}">
										<c:forEach items="${roleList}" var="role" varStatus="vs">
											<td style="width:100px;" class="center" <c:choose><c:when test="${pd.ROLE_ID == role.ROLE_ID}">bgcolor="#FFC926" onMouseOut="javascript:this.bgColor='#FFC926';"</c:when><c:otherwise>bgcolor="#E5E5E5" onMouseOut="javascript:this.bgColor='#E5E5E5';"</c:otherwise></c:choose>  onMouseMove="javascript:this.bgColor='#FFC926';" >
												<a href="/role/list.do?ROLE_ID=${role.ROLE_ID }" style="text-decoration:none; display:block;"><i class="menu-icon fa fa-users"></i><font color="#666666">${role.ROLE_NAME }</font></a>
											</td>
											<td style="width:5px;"></td>
										</c:forEach>
										</c:when>
										<c:otherwise>
											<tr>
											<td colspan="100">没有相关数据</td>
											</tr>
										</c:otherwise>
										</c:choose>
									<td></td>
								</tr>
							</table>
							<table>
								<tr height="7px;"><td colspan="100"></td></tr>
								<tr>
								<td><font color="#808080">本组：</font></td>
								<td>
								<a class="btn btn-mini btn-info" onclick="javascript:Role.editRole('${var.ROLE_ID }');">修改组名称<i class="icon-arrow-right  icon-on-right"></i></a>
									<c:choose>
										<c:when test="${pd.ROLE_ID == '99'}">
										</c:when>
										<c:otherwise>
										<a class="btn btn-mini btn-success" onclick="Role.editRights('${pd.ROLE_ID }');"><i class="icon-pencil"></i>组菜单权限</a>
										</c:otherwise>
									</c:choose>
									<c:choose> 
										<c:when test="${pd.ROLE_ID == '1' or pd.ROLE_ID == '2'}">
										</c:when>
										<c:otherwise>
										 <a class='btn btn-mini btn-danger' title="删除" onclick="Role.delRole('${pd.ROLE_ID }','z','${pd.ROLE_NAME }');"><i class='ace-icon fa fa-trash-o bigger-130'></i></a>
										</c:otherwise>
									</c:choose>
								</td>
								</tr>
							</table>
							<table id="dynamic-table" class="table table-striped table-bordered table-hover" style="margin-top:7px;">
								<thead>
								<tr>
									<th class="center" style="width: 50px;">序号</th>
									<th class='center'>角色</th>
									<th class="center">增</th>
									<th class="center">删</th>
									<th class="center">改</th>
									<th class="center">查</th>
									<th style="width:255px;"  class="center">操作</th>
								</tr>
								</thead>
								<c:choose>
									<c:when test="${not empty roleList_z}">
										<c:forEach items="${roleList_z}" var="var" varStatus="vs">
										<tr>
										<td class='center' style="width:30px;">${vs.index+1}</td>
										<td id="ROLE_NAMETd${var.ROLE_ID }"><c:if test="${not empty var.SCHOOL_NAME}">（${var.SCHOOL_NAME}）</c:if>${var.ROLE_NAME }</td>
										<td style="width:30px;"><a onclick="Role.roleButton('${var.ROLE_ID }','add_qx');" class="btn btn-warning btn-mini" title="分配新增权限"><i class="ace-icon fa fa-wrench bigger-110 icon-only"></i></a></td>
										<td style="width:30px;"><a onclick="Role.roleButton('${var.ROLE_ID }','del_qx');" class="btn btn-warning btn-mini" title="分配删除权限"><i class="ace-icon fa fa-wrench bigger-110 icon-only"></i></a></td>
										<td style="width:30px;"><a onclick="Role.roleButton('${var.ROLE_ID }','edit_qx');" class="btn btn-warning btn-mini" title="分配修改权限"><i class="ace-icon fa fa-wrench bigger-110 icon-only"></i></a></td>
										<td style="width:30px;"><a onclick="Role.roleButton('${var.ROLE_ID }','cha_qx');" class="btn btn-warning btn-mini" title="分配查看权限"><i class="ace-icon fa fa-wrench bigger-110 icon-only"></i></a></td>
										<td style="width:155px;">
									<!-- 	<div style="width:100%;" class="center">
										<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
										</div> -->
										<button type="button" onclick="Role.editRights('${var.ROLE_ID }');" class="btn btn-mini btn-warning">菜单权限</button>
										<a class='btn btn-mini btn-info' title="编辑" onclick="javascript:Role.editRole('${var.ROLE_ID }');"><i class='ace-icon fa fa-pencil-square-o bigger-130'></i></a>
										<c:choose> 
											<c:when test="${var.ROLE_ID == '2' or var.ROLE_ID == '1'}">
											</c:when>
											<c:otherwise>
											 <a class='btn btn-mini btn-danger' title="删除" onclick="Role.delRole('${var.ROLE_ID }','c','${var.ROLE_NAME }');"><i class='ace-icon fa fa-trash-o bigger-130'></i></a>
											</c:otherwise>
										</c:choose>
										</td>
										</tr>
										</c:forEach>
										<!-- 	<tr>
												<td colspan="100" class="center">您无权查看</td>
											</tr> -->
									</c:when>
									<c:otherwise>
										<tr>
										<td colspan="100" class="center" >没有相关数据</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</table>
							<div>
								&nbsp;&nbsp;<a class="btn btn-sm btn-success" onclick="Role.addRole('${pd.ROLE_ID }');">新增角色</a>
							</div>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->
		<!-- 返回顶部 -->
	<!-- 	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a> -->

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../common/foot.jsp"%>
	<!-- 自定义js -->
    <script src="/statics/js/user/Role.js"></script>
	<!-- 删除时确认窗口 -->


</body>
</html>