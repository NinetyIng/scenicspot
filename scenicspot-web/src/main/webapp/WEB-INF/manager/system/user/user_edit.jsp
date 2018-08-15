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
								<form action="user/${msg }.do" name="userForm" id="userForm" method="post">
									<input type="hidden" name="USER_ID" id="user_id" value="${pd.USER_ID }"/>
									<div id="zhongxin" style="padding-top: 13px;">
									<table id="table_report" class="table table-striped table-bordered table-hover">
										<c:if test="${fx != 'head'}">
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">角色:</td>
											<td id="juese">
											<select class="chosen-select form-control" <c:if test="${fx == 'head'}">disabled='disabled'</c:if> name="ROLE_ID" id="role_id" data-placeholder="请选择角色" style="vertical-align:top;" class="form-control">
											<option value=""></option>
											<c:forEach items="${roleList}" var="role">
												<option value="${role.ROLE_ID }" <c:if test="${role.ROLE_ID == pd.ROLE_ID }">selected</c:if>>${role.ROLE_NAME }</option>
											</c:forEach>
											</select>
											</td>
										</tr>
										</c:if>
										<c:if test="${fx == 'head'}">
											<input name="ROLE_ID" id="role_id" value="${pd.ROLE_ID }" type="hidden" />
										</c:if>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">用户名:</td>
											<td>
											<input type="hidden" value="${pd.USERNAME }" />
											<input type="text" name="USERNAME" <c:if test="${fx == 'head'}">disabled='disabled'</c:if> id="loginname" value="${pd.USERNAME }" maxlength="32" placeholder="这里输入用户名" title="用户名" class="form-control"  onblur = "User.hasExist({'id':'loginname','msg':'用户名已经存在','oldValue':'${pd.USERNAME}'});" /></td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">编号:</td>
											<td>
											<input type="hidden" value="${pd.NUMBER }" />
											<input type="text" name="NUMBER" id="NUMBER" <c:if test="${fx == 'head'}">disabled='disabled'</c:if> value="${pd.NUMBER }" maxlength="32" placeholder="这里输入编号" title="编号"  onblur="User.hasExist({'id':'NUMBER','msg':'编号已经存在','oldValue':'${pd.NUMBER}'});" class="form-control"/></td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">密码:</td>
											<td><input type="password" name="PASSWORD" id="password"  maxlength="32" placeholder="输入密码" title="密码" class="form-control"/></td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">确认密码:</td>
											<td><input type="password" name="chkpwd" id="chkpwd"  maxlength="32" placeholder="确认密码" title="确认密码" class="form-control"/></td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">姓名:</td>
											<td><input type="text" name="NAME" id="name"  value="${pd.NAME }"  maxlength="32" placeholder="这里输入姓名" title="姓名" class="form-control"/></td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">手机号:</td>
											<td><input type="number" name="PHONE" id="PHONE"  value="${pd.PHONE }"  maxlength="32" placeholder="这里输入手机号" title="手机号" class="form-control"/></td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">邮箱:</td>
											<td>
											   <input type="hidden"  value="${pd.EMAIL }" />
											  <input type="email" name="EMAIL" id="EMAIL"  value="${pd.EMAIL }" maxlength="32" placeholder="这里输入邮箱" title="邮箱"  onblur="User.hasExist({'id':'EMAIL','msg':'邮箱已经存在','oldValue':'${pd.EMAIL}'});"  class="form-control"/>
											</td>
										</tr>
										<tr>
											<td style="width:79px;text-align: right;padding-top: 13px;">备注:</td>
											<td><input type="text" name="BZ" id="BZ"value="${pd.BZ }" placeholder="这里输入备注" maxlength="64" title="备注" class="form-control"/></td>
										</tr>
										<tr>
											<td style="text-align: center;" colspan="10">
												<a class="btn btn-mini btn-primary" onclick="User.save();">保存</a>
												<a class="btn btn-mini btn-danger" onclick="parent.location.reload();">取消</a>
											</td>
										</tr>
									</table>
									</div>
								</form>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../common/foot.jsp"%>
	<script type="text/javascript" src="/statics/js/user/User.js"></script>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		if($("#user_id").val() != ""){
			$("#loginname").attr("readonly","readonly");
			$("#loginname").css("color","gray");
		}
	});
	$(function() {
		//下拉框
		 $("#chooseSchool").hide();
		$("#role_id").change(function(){
		    if($(this).val() == 'e7758f56d2a44114ab367e8243a4abab' ){
		      $("#chooseSchool").show();
		    }else{
		     $("#chooseSchool").hide();
		    }
		});
	});
</script>
</html>