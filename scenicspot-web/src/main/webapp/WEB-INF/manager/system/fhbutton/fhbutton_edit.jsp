<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh">
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
					<form action="button/${msg}" name="Form" id="Form" method="post">
						<input type="hidden" name="FHBUTTON_ID" id="FHBUTTON_ID" value="${pd.FHBUTTON_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:79px;text-align: right;padding-top: 13px;">名称:</td>
								<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="30" onkeyup="ValidateValue(this)" placeholder="这里输入名称" title="名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:79px;text-align: right;padding-top: 13px;">权限标识:</td>
								<td><input oninput="changecode(this.value)" type="text" name="QX_NAME" id="QX_NAME" onkeyup="ValidateValue(this)" value="${pd.QX_NAME}" maxlength="50" placeholder="这里输入权限标识" title="权限标识" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:79px;text-align: right;padding-top: 13px;">备注:</td>
								<td><input type="text" name="BZ" id="BZ" value="${pd.BZ}" maxlength="255" placeholder="这里输入备注" title="备注" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:78px;height:130px;text-align: right;padding-top: 13px;">代码:</td>
								<td >
									<textarea rows="5" cols="10" id="code"  style="width:98%;" readonly="readonly" title="代码区,禁止手动输入"></textarea>
								</td>
							</tr>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="closed();">取消</a>
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

		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		function closed(){
			parent.layer.close(index);
		}
		$(function() {
			var str1 = '<c'+':if test="'+'$'+'{QX.'+$("#QX_NAME").val();
			var str2 = ' == 1 }">这里放按钮<'+'/c:'+'if>';
			$("#code").val(str1+str2);
		});
		//拼代码
		function changecode(value){
			var str1 = '<c'+':if test="'+'$'+'{QX.';
			var str2 = ' == 1 }">这里放按钮<'+'/c:'+'if>';
			$("#code").val(str1 + value +str2);
		}
		//保存
		function save(){
			if($("#NAME").val()==""){
				$("#NAME").tips({
					side:3,
		            msg:'请输入名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#NAME").focus();
			return false;
			}
			if($("#QX_NAME").val()==""){
				$("#QX_NAME").tips({
					side:3,
		            msg:'请输入权限标识',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#QX_NAME").focus();
			return false;
			}
			if($("#BZ").val()==""){
				$("#BZ").tips({
					side:3,
		            msg:'请输入备注',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BZ").focus();
			return false;
			}
            Ajax.request($("#Form").attr("action"),{"data":$("#Form").serialize(),"success":function(item){
                if(item.result == 200){
                    parent.location.reload();
                    closed();
				}
            }});
		}
		//过滤特殊字符
        function ValidateValue(textbox)  
        {  
             var IllegalString = "\`~@#;,.!#$%^&*()+{}|\\:\"<>?-=/,\'";  
             var textboxvalue = textbox.value;  
             var index = textboxvalue.length - 1;  
             for(var i=index;i>=0;i--){
                 var s = textbox.value.charAt(i);  

                 if(IllegalString.indexOf(s)>=0)  
                 {  
                    s = textboxvalue.substring(0,i);  
                    textbox.value = s;  
                 }  
             }
        }  
		</script>
</body>
</html>