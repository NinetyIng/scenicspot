<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
						<form action="role/${msg}" name="form1" id="form1"  method="post">
						<input type="hidden" name="ROLE_ID" id="id" value="${pd.ROLE_ID}"/>
						<input name="PARENT_ID" id="parent_id" value="${pd.parent_id }" type="hidden">
							<div id="zhongxin" style="padding-top:13px;">
							<table class="center" style="width:100%;">
								<tr style="text-align: center;">
									<td><input type="text" name="ROLE_NAME" id="roleName" placeholder="这里输入名称" value="${pd.ROLE_NAME}"  title="名称" style="width:99%;"/></td>
								</tr>
								<%-- 当系统管理员添加教师时需要下拉表选学校 --%>
								<c:if test="${!empty requestScope.schools }">
								<tr style="text-align: center;">
									<td>
										<select name="SCHOOL_ID" id="SCHOOL_ID" class="form-control">
										  <c:forEach items="${schools}" var="sch" varStatus="status">
											<option value="${sch.s_id}" <c:if test="${sch.s_id eq pd.SCHOOL_ID}">selected="selected"</c:if>>${sch.s_name}</option>
										  </c:forEach>
										</select>
									</td>
								</tr>
								</c:if>
								<tr>
									<td style="text-align: center;padding-top:5px;">
										<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
										<a class="btn btn-mini btn-danger" onclick="javascript:parent.location.reload();">取消</a>
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
	<!-- 页面底部js¨ -->
	<%@ include file="../../common/foot.jsp"%>
	<!--提示框-->
	<script type="text/javascript">
	//保存
	function save(){
		if($("#roleName").val()==""){
			$("#roleName").tips({
				side:3,
	            msg:'请输入',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#roleName").focus();
			return false;
		}
		var url = $("#form1").attr("action");
		var data = $("#form1").serialize();
	    Ajax.request(url,{"data":data,"success":function(item){
	    	if(item.result == "200"){
	    		parent.location.reload();
	    	}else{
	    		alert(item.msg);
	    	}
	    }});
	}
	</script>
</body>
</html>

