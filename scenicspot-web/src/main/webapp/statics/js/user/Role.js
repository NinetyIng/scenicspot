var Role = {
		
		addRole:function(pid){
		  layer.open({
				 type: 2,
				  title: '新增角色',
				  shadeClose: true,
				  shade: 0.5,
				  area: ['300px', '200px'],
				  content: '/role/toAdd.do?parent_id='+pid
				});
		},
		editRole:function(pid){
			layer.open({
				 type: 2,
				  title: '编辑角色',
				  shadeClose: true,
				  shade: 0.5,
				  area: ['300px', '200px'],
				  content: '/role/toEdit.do?ROLE_ID='+pid
				});
		},
		editRights:function(ROLE_ID){
			layer.open({
				 type: 2,
				  title: '菜单权限',
				  shadeClose: true,
				  shade: 0.5,
				  area: ['310px', '510px'],
				  content: '/role/menuqx?ROLE_ID='+ROLE_ID
				});
		},
		roleButton:function(ROLE_ID,msg){
			
			var Title;
			if(msg == 'add_qx'){
				Title = "授权新增权限(此角色下打勾的菜单拥有新增权限)";
			}else if(msg == 'del_qx'){
				Title = "授权删除权限(此角色下打勾的菜单拥有删除权限)";
			}else if(msg == 'edit_qx'){
				Title = "授权修改权限(此角色下打勾的菜单拥有修改权限)";
			}else if(msg == 'cha_qx'){
				Title = "授权查看权限(此角色下打勾的菜单拥有查看权限)";
			}
			layer.open({
				 type: 2,
				  title: Title,
				  shadeClose: true,
				  shade: 0.5,
				  area: ['310px', '510px'],
				  content: '/role/b4Button.do?ROLE_ID='+ROLE_ID+'&msg='+msg
				});
		},
		delRole:function(ROLE_ID,msg,ROLE_NAME){
			bootbox.confirm("确定要删除["+ROLE_NAME+"]吗?", function(result){
				if(result){
					var url = "/role/delete.do?guid="+new Date().getTime();
					var data = {"ROLE_ID":ROLE_ID};
					Ajax.request(url,{"data":data,"success":function(item){
						if(item.result == "200"){
							window.location.reload();
						}else{
							bootbox.alert(item.msg);
						}
					}});
				}
			});
		}
		
}