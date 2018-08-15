<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<link href="/assets/css/bootstrap-fileupload.css" rel="stylesheet">
	<%@ include file="../../common/top.jsp"%>
	<link href="/assets/css/plugins/summernote/summernote.css" rel="stylesheet">
	<link href="/assets/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
</head>
<body>
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>编辑菜单</h5>
                    </div>
                    <div class="ibox-content">
                        <form action="/menu/${MSG}" method="post" class="form-horizontal m-t" id="commentForm">
                            <input type="hidden" name="MENU_ID" id="menuId" value="${pd.MENU_ID }"/>
							<input type="hidden" name="MENU_TYPE" id="MENU_TYPE" value="${pd.MENU_TYPE }"/>
							<input type="hidden" name="MENU_STATE" id="MENU_STATE" value="${pd.MENU_STATE }"/>
							<input type="hidden" name="PARENT_ID" id="PARENT_ID" value="${null == pd.PARENT_ID ? MENU_ID:pd.PARENT_ID}"/>
								<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 上级 :</label>
								<div class="col-sm-9">
									<div style="padding-top:5px;">
										<div class="col-xs-7 label label-lg label-light arrowed-in arrowed-right">
											<b>${null == pds.MENU_NAME ?'(无) 此项为顶级菜单':pds.MENU_NAME}</b>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 名称 :</label>
								<div class="col-sm-9">
									<input type="text" name="MENU_NAME" id="menuName" value="${pd.MENU_NAME }" placeholder="这里输入菜单名称" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 链接  :</label>
								<div class="col-sm-9">
									<c:if test="${null != pds.MENU_NAME}">
									<input type="text" name="MENU_URL" id="menuUrl" value="${pd.MENU_URL }" placeholder="这里输入菜单链接" class="form-control" />
									</c:if>
									<c:if test="${null == pds.MENU_NAME}">
									<input type="text" name="MENU_URL" id="menuUrl" value="" readonly="readonly" placeholder="顶级菜单禁止输入" class="form-control" />
									</c:if>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 序号 : </label>
								<div class="col-sm-9">
									<input type="number" name="MENU_ORDER" id="menuOrder" value="${pd.MENU_ORDER}" placeholder="这里输入菜单序号" title="请输入正整数" class="form-control" />
								</div>
							</div>
							<c:if test="${'0' == MENU_ID}">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 类型 : </label>
								<div class="col-sm-9">
									<label style="float:left;padding-left: 8px;padding-top:7px;">
										<input name="form-field-radio" type="radio" class="ace" id="form-field-radio1" <c:if test="${pd.MENU_TYPE == '1' }">checked="checked"</c:if> onclick="setType('1','1');"/>
										<span class="lbl"> 系统菜单</span>
									</label>
									<label style="float:left;padding-left: 5px;padding-top:7px;">
										<input name="form-field-radio" type="radio" class="ace" id="form-field-radio2" <c:if test="${pd.MENU_TYPE == '2' }">checked="checked"</c:if> onclick="setType('1','2');"/>
										<span class="lbl"> 业务菜单</span>
									</label>
								</div>
							</div>
							</c:if>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 状态 : </label>
								<div class="col-sm-9">
									<label style="float:left;padding-left: 8px;padding-top:7px;">
										<input name="form-field-radio2" type="radio" class="ace" id="form-field-radio3" <c:if test="${pd.MENU_STATE == 1 }">checked="checked"</c:if> onclick="setType('2',1);"/>
										<span class="lbl"> 显示</span>
									</label>
									<label style="float:left;padding-left: 5px;padding-top:7px;">
										<input name="form-field-radio2" type="radio" class="ace" id="form-field-radio4" <c:if test="${pd.MENU_STATE == 0 }">checked="checked"</c:if> onclick="setType('2',0);"/>
										<span class="lbl"> 隐藏</span>
									</label>
								</div>
							</div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3">
                                    <button class="btn btn-primary" type="button" id="submitBtn"><i class="fa fa-check"></i>&nbsp;&nbsp;提   交&nbsp;&nbsp; </button>
                                    <button class="btn btn-warning" type="button" onclick="window.location.href='/menu/list?MENU_ID=${MENU_ID}'"><i class="fa fa-close"></i>&nbsp;&nbsp;取消&nbsp;&nbsp; </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../common/foot.jsp"%>
	 <script src="/statics/js/util/Ajax.js"></script>
<!-- 自定义js -->
    <script type="text/javascript">
         $(function(){
        	 $("#submitBtn").click(function(){
        		 var data = $("#commentForm").serialize();
            	 var url = $("#commentForm").attr("action");
                 Ajax.request(url,{"data":data,"success":function(item){
                	 if(item.result =="200"){
                		 window.parent.initTree();
                		 window.location.href="/menu/list?MENU_ID="+item.data.PARENT_ID;
                	 }
                 }});
        	 });
         });
     	//设置菜单类型or状态
 		function setType(type,value){
 			if(type == '1'){
 				$("#MENU_TYPE").val(value);
 			}else{
 				$("#MENU_STATE").val(value);
 			}
 		}
    </script>
</body>
</html>