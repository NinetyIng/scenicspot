<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
					<form action="dictionaries/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="DICTIONARIES_ID" id="DICTIONARIES_ID" value="${pd.DICTIONARIES_ID}"/>
						<input type="hidden" name="PARENT_ID" id="PARENT_ID" value="${null == pd.PARENT_ID ? DICTIONARIES_ID:pd.PARENT_ID}"/>
						<div id="zhongxin">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">上级:</td>
								<td>
									<div class="col-xs-4 label label-lg label-light arrowed-in arrowed-right">
										<b>${null == pds.NAME ?'(无) 此项为顶级':pds.NAME}</b>
									</div>
								</td>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">名称:</td>
								<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="50" placeholder="这里输入名称" title="名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">英文:</td>
								<td><input type="text" name="NAME_EN" id="NAME_EN" value="${pd.NAME_EN}" maxlength="50" placeholder="这里输入英文" title="英文" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">编码:</td>
								<td><input type="text" name="BIANMA" id="BIANMA" value="${pd.BIANMA}" maxlength="32" placeholder="这里输入编码 (不重复, 禁止修改)" title="编码" style="width:98%;" <c:if test="${null != pd.BIANMA}">readonly="readonly"</c:if> <c:if test="${null == pd.BIANMA}">onblur="Dictionnaries.hasBianma();"</c:if> /></td>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">排序:</td>
								<td><input type="number" name="ORDER_BY" id="ORDER_BY" value="${pd.ORDER_BY}" maxlength="32" placeholder="这里输入排序" title="排序" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">备注:</td>
								<td>
									<textarea rows="3" cols="46" name="BZ" id="BZ" placeholder="这里输入备注" title="备注"  style="width:98%;">${pd.BZ}</textarea>
								</td>
							</tr>
							<tr>
								<td style="width:70px;text-align: right;padding-top: 13px;">排查表:</td>
								<td><input type="text" name="TBSNAME" id="TBSNAME" value="${pd.TBSNAME}" maxlength="100" placeholder="这里输入表名, 多个用逗号分隔(非必录)" title="排查表" style="width:98%;"/></td>
							</tr>
							<tr>
								<td colspan="10" class="center"><p class="text-warning bigger-110 orange">排查表：删除此条数据时会去此表查询是否被占用(是:禁止删除)</p></td>
							</tr>
							<tr>
								<td class="center" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="Dictionnaries.save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="window.location.href='/dictionaries/list?DICTIONARIES_ID=${pd.DICTIONARIES_ID}'">取消</a>
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
<script type="text/javascript" src="/statics/js/user/Dictionnaries.js"></script>
</body>
</html>