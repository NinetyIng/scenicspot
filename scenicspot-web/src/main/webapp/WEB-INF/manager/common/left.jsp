﻿<!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" class="img-circle" style="width: 80px;height:80px;" src="/statics/img/admin.jpg" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                                <span class="clear">
                                <span class="text-xs block font-bold">${admin.NAME}<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                              <!--   <li><a class="J_menuItem" href="form_avatar.html">修改头像</a>
                                </li> -->
                                <li ><a class="J_menuItem" href="/user/goEditU.do?USER_ID=${admin.USER_ID}&fx=head" data-index="1">个人资料</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="main/logout">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">
                        </div>
                    </li>
                    <c:forEach items="${menuList}" var="menu1">
                    <c:if test="${menu1.hasMenu && '1' == menu1.MENU_STATE}">
                    <li class="" id="lm${menu1.MENU_ID }">
                        <a href="index.html#"><i class="${menu1.MENU_ICON}"></i> <span class="nav-label">${menu1.MENU_NAME }</span><span class="fa arrow"></span></a>
                        <c:if test="${'[]' != menu1.subMenu}">
                             <ul class="nav nav-second-level">
                                 <c:forEach items="${menu1.subMenu}" var="menu2">
                                      <li>
                                           <a 
                                                <c:choose>
                                                   <c:when test="${not empty menu2.MENU_URL && '#' != menu2.MENU_URL}"> class="J_menuItem"   href="${menu2.MENU_URL}"</c:when>
                                                   <c:otherwise>   href="index.html#" </c:otherwise>
                                                </c:choose>
                                              ><span class="nav-label">${menu2.MENU_NAME}</span> 
                                               <c:choose>
                                                 <c:when test="${'[]' != menu2.subMenu}"> 
                                                     <span class="fa arrow"></span></a>
	                                                 <ul class="nav nav-third-level collapse" aria-expanded="false" style="">
	                                                      <c:forEach items="${menu2.subMenu}" var="menu3">
						                                       <li ><a class="J_menuItem" href="${menu3.MENU_URL}">${menu3.MENU_NAME}</a></li>
						                                  </c:forEach>
						                                </ul>   
                                                 </c:when>
                                                 <c:otherwise>
                                                   </a>
                                                 </c:otherwise>
                                               </c:choose> 
                                      </li>
                                </c:forEach>
                             </ul>
                             </c:if>
                    </li>
                    </c:if>
                    </c:forEach>
                    
                   <!--  <li>
                        <a href="index.html#"><i class="fa fa-edit"></i> <span class="nav-label">开发管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="code/codePage">代码辅助</a></li>
                            <li><a class="J_menuItem" href="/test/listPage">列表</a></li>
                            <li><a class="J_menuItem" href="/test/treeGridPage">树形列表</a></li>
                            <li><a class="J_menuItem" href="/test/editPage">表单带选项卡</a></li>
                            <li><a class="J_menuItem" href="/test/editPage">表单</a></li>
                        </ul>
                    </li>
                   	<li>
                        <a href="index.html#"><i class="fa fa-edit"></i> <span class="nav-label">系统管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/user/listUsers">管理员列表</a></li>
                            <li><a class="J_menuItem" href="/role/list">角色列表</a></li>
                            <li><a class="J_menuItem" href="/menu/main">菜单管理</a></li>
                        </ul>
                    </li>

					<li>
                        <a href="index.html#"><i class="fa fa-edit"></i> <span class="nav-label">商品管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/sys/goods/goods/listPage">商品列表</a></li>
                            <li><a class="J_menuItem" href="/sys/goods/goodsCategory/listPage">商品分类</a></li>
                            <li><a class="J_menuItem" href="/sys/goods/goodsBrand/listPage">商品品牌</a></li> 
                            <li><a class="J_menuItem" href="/sys/goods/goodsType/listPage">商品类型</a></li>
                            <li><a class="J_menuItem" href="/sys/goods/designer/listPage">设计师</a></li>
                            <li><a class="J_menuItem" href="/sys/goods/goodsTypeAttr/listPage">商品类型属性</a></li>
                            <li><a class="J_menuItem" href="/test/listPage">商品自动上/下架</a></li>
                            <li><a class="J_menuItem" href="/test/listPage">商品评论</a></li>	
                        </ul>
                    </li>
                    
					<li>
                        <a href="index.html#"><i class="fa fa-edit"></i> <span class="nav-label">高端订制</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/sys/haute_couture/application/listPage">订制申请</a></li>
                            <li><a class="J_menuItem" href="/sys/haute_couture/order/listPage">订制订单</a></li>
                            <li><a class="J_menuItem" href="/sys/haute_couture/designs/listPage">设计稿</a></li>
                            <li><a class="J_menuItem" href="/sys/haute_couture/dialog/listPage">反馈记录</a></li> 
                            <li><a class="J_menuItem" href="/sys/haute_couture/images/listPage">图片整理</a></li>
                            <li><a class="J_menuItem" href="/sys/haute_couture/material/listPage">订制材料</a></li>
                            <li><a class="J_menuItem" href="/sys/haute_couture/category/listPage">订制品分类</a></li>
                        </ul>
                    </li>                                                                                                                                                 

					<li>
                        <a href="index.html#"><i class="fa fa-edit"></i> <span class="nav-label">订单管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/sys/order/orders/listPage">订单列表</a></li>
                            <li><a class="J_menuItem" href="/sys/order/ordersShipping/listPage">发货单列表</a></li>
                            <li><a class="J_menuItem" href="/sys/goods/goodsType/listPage">退/换货管理</a></li>
                        </ul>
                    </li>
                     <li>
                        <a href="index.html#"><i class="fa fa-edit"></i> <span class="nav-label">分销管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/sys/rebate/configPage">分销设置</a></li>
                            <li><a class="J_menuItem" href="/sys/rebate/orderList">分销订单</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-edit"></i> <span class="nav-label">会员管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/sys/user/listPage">会员列表</a></li>
                            <li><a class="J_menuItem" href="/sys/user/userWithdraw/listPage">提现列表</a></li>
                            <li><a class="J_menuItem" href="/sys/user/userWithdraw/listCharge">充值列表</a></li>
                            <li><a class="J_menuItem" href="/sys/user/userWithdraw/listBinding">银行卡绑定列表</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-edit"></i> <span class="nav-label">模块管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/sys/upm/template/templateList">模块列表</a></li>
                            <li><a class="J_menuItem" href="/sys/upm/template/templateItemList">模块内容列表</a></li>
                        </ul>
                    </li>
                     <li>
                        <a href="index.html#"><i class="fa fa-edit"></i> <span class="nav-label">体验馆管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/sys/upm/store/listPage">体验馆列表</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="index.html#"><i class="fa fa-edit"></i> <span class="nav-label">文案管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/sys/article/article/listPage">文案列表</a></li>
                        </ul>
                    </li> -->
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        
        