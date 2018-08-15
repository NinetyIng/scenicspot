
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>文件管理器</title>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/css/font-awesome.min.css" rel="stylesheet">
    <link href="/assets/css/animate.min.css" rel="stylesheet">
    <link href="/assets/css/style.min.css" rel="stylesheet">
    <link href="/assets/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="row wrapper wrapper-content animated fadeInRight">
        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>  &nbsp&nbsp;${fn:substring(SETTINGPD.IMAGEPATH,7,19)}文件服务器文件预览</h5>
                    <div class="ibox-tools" >
                    </div>
                </div>
                <div class="ibox-content">
                    <div>
                        <div id="treeview11" class="test"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
           <div class="col-sm-8" >
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>文件详情</h5>
                    <div class="ibox-tools" >
                    </div>
                </div>
                  <div class="ibox-content" >
                              <div class="form-group" style="margin-bottom:45px!important;">
                                <label class="col-sm-3 control-label">文件路径：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="filePath" disabled="disabled"  class="form-control"> 
                                </div>
                            </div>
                            <div class="form-group" style="margin-bottom:45px!important;">
                                <label class="col-sm-3 control-label">创建时间：</label>
                                <div class="col-sm-9">
                                    <input type="text" id="createTime" disabled="disabled"  class="form-control"> 
                                </div>
                            </div>
                            <div class="form-group" style="display: none;" id="showImgModel" style="margin-bottom:45px!important;">
                            <label class="col-sm-3 control-label"></label>
                                <div class="col-sm-9">
                                    	<img id="showImg" onclick="window.open(this.src)" src="${SETTINGPD.IMAGEPATH}" onerror="javascript:this.src='/statics/img/no-img.jpg';"
																	height="100" width="200">
                                </div>
                            </div>
                            <div class="form-group" style="margin-bottom:45px!important;">
                            <label class="col-sm-3 control-label"></label>
                                <div class="col-sm-4">
                                    <button class="btn btn-sm btn-primary pull-right m-t-n-xs" onclick="removeFile()" type="button"><strong>移除该文件</strong>
                                        </button>
                                </div>
                            </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" value="${fileList}" id="menuJson"/>
    <script src="/statics/js/util/Ajax.js"></script>
    <script src="/assets/js/jquery-2.1.1.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <!-- 自定义js -->
    <script src="/assets/js/content.min.js"></script>
    <!-- Bootstrap-Treeview plugin javascript -->
    <script src="/assets/js/plugins/treeview/bootstrap-treeview.js"></script>
</body>
  <script type="text/javascript">
    var itemObj;
    $(function(){
    	/* var zn = '${menuJson}';
    	var bTreeNodes = eval(zn);
    	 $("#treeview11").treeview({
				color : "#428bca",
				data : bTreeNodes,
				onNodeSelected : function(e, o) {
					$("#treeFrame").attr("src",o.href);
				}
			}); */
    	$("#treeview11").treeview({
			color : "#000",
			data : '${fileList}',
			state:{
				expanded:false,
				selected:false,
				disabled:false
			},
			onNodeSelected : function(e, o) {
				itemObj = o;
				var target = o.tags.split(";");
				$("#filePath").val(target[1]);
				$("#createTime").val(target[2]);
				if(o.checked){ //文件
					$("#showImg").attr("src",'${SETTINGPD.IMAGEPATH}'+target[1]);
					$("#showImgModel").show();
				}else{
					  //文件夹
					$("#showImgModel").hide();
				}
			}
		});
    });
    //请求后台删除文件
    function removeFile(){
    	if(!$("#filePath").val()){
    		alert("您未选中任何文件");
    		return;
    	}
    	if(confirm("确认删除该文件及下级的所有文件吗？")){
    		Ajax.request("/sys/storage/deleteFile",{"data":{"pathName":$("#filePath").val()},"success":function(data){
        		if(data.result == "200"){
        			window.location.reload();
        		}
        	}});
    	}
    }
  </script>
  
</html>