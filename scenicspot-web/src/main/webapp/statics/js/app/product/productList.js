;!function(win,undefined){
   var config = {
       url:"/api/product/list.shtm",
       detailUrl:"/jumplink/productDetail.shtm",
       reqParam:{
           currentPage:0,
           priceOrder:0,
           saleOrder:0,
           putimeOrder:0,
           recommendOrder:0,
           categoryId:0,
           pageSize:globalConfig.pageSize
       }
   },dom = {_window:$(win),_document:$(document),_html:$('html'),_body:$('body'),_loadmore:$('.load-more'),_content:$("#sortDefault"),
       _condition:$(".control-item"),_putimeOrder:$("#putimeOrder"),_recommendOrder:$("#recommendOrder")},xrp={};
    xrp.html = '     <li  class="table-view-cell media">\n'
        + '                <a class="navigate-right">\n'
        + '                    <img class="media-object pull-left" onerror="this.src='+"'"+'/statics/app/img/10.jpg'+"'"+'"  src="/statics/app/img/dstc/3.jpg"/>\n'
        + '                    <div class="media-body" style="padding-right: 40px;">\n'
        + '                        <div class="title-name" style="height: 42px;overflow: hidden;">\n'
        + '                            鱼包韭菜\n'
        + '                        </div>\n'
        + '                        <p><i class="rating-star-50"></i></p>\n'
        + '                        <p class="moods">230人付款</p>\n'
        + '                        <div class="price">¥ 200.00</div>\n'
        + '                    </div>\n'
        + '                </a>\n'
        + '            </li>';
   //初始化面板方法
    xrp.initPanel = function(){
        dom._content.empty();
        dom._loadmore.hide();
        if(dom._putimeOrder && dom._putimeOrder.val() == 1){
            config.reqParam.putimeOrder = 1;
        }
        if(dom._recommendOrder && dom._recommendOrder == 1){
            config.reqParam.recommendOrder = 1;
        }
        xrp.request(true);
    };

   xrp.request = function (first) {
       loadMore();
       if(!first && !xrp.isRequest()){
           return;
       }
       config.reqParam.currentPage ++;
       Ajax.request(config.url,{"data":config.reqParam,"success":function (data) {
           xrp.analysis(data);
       }});
   };
   xrp.loadMore = function(){
     dom._loadmore.click(function(){
        xrp.request();
     });
   };
   xrp.isRequest = function(){
       return config.reqParam.pageSize == globalConfig.pageSize;
   };
   xrp.analysis = function(data){
       if(data.resultCode == globalConfig.SUCCESSCODE){
           if(data.productList.length <= 0){
               //渲染无数据页面
               return;
           }
           var $nodeModel = $(xrp.html);
           var productList = data.productList;
           config.reqParam.pageSize = productList.length;
           for (var i = 0 ; i < productList.length ; i++){
               var $createNode = $nodeModel.clone();
               $createNode.attr("onclick","window.location.href='"+config.detailUrl+"?productId="+productList[i].id+"'");
               $createNode.find(".media-object").attr("src",globalConfig.showUrl + productList[i].listImg);
               $createNode.find(".title-name").text(productList[i].goodsName);
               $createNode.find(".moods").text(productList[i].virtualSales+"人付款");
               $createNode.find(".price").text("￥"+productList[i].shopPrice)
               dom._content.append($createNode);
           }
           if(xrp.isRequest()){
               dom._loadmore.show();
           }else{
               dom._loadmore.hide();
           }
       }else{
           alert(data.errorDesc);
       }
   };
   xrp.initRequestParam = function () {
       dom._content.empty();
       config.reqParam.saleOrder = 0;
       config.reqParam.priceOrder = 0;
       config.reqParam.currentPage = 0;
       config.reqParam.putimeOrder = 0;
       config.reqParam.recommendOrder = 0;
       config.reqParam.pageSize = globalConfig.pageSize;
   };
   xrp.saleOrder = function(){
       xrp.initRequestParam();
       config.reqParam.saleOrder = 1;
       xrp.request();
   };
   xrp.priceOrder = function () {
       var priceOrder = config.reqParam.priceOrder == 1 ?  0 : 1;
       xrp.initRequestParam();
       config.reqParam.priceOrder = priceOrder;
       xrp.request();
   };
   xrp.defaultOrder = function(){
       xrp.initRequestParam();
       xrp.request();
   };
   xrp.putimeOrder = function () {
       dom._content.empty();
       config.reqParam.saleOrder = 0;
       config.reqParam.priceOrder = 0;
       config.reqParam.currentPage = 0;
       config.reqParam.putimeOrder = 1;
       xrp.request();
   }
   xrp.xr = (function(){
       xrp.initPanel();
       xrp.loadMore();
       dom._condition.click(function(){
           if($(this).hasClass("saleOrder")){
               xrp.saleOrder();
           }else  if ($(this).hasClass("priceOrder")){
               xrp.priceOrder();
           }else  if ($(this).hasClass("putimeOrder")){
               xrp.putimeOrder();
           }else  if ($(this).hasClass("putimeOrder")){
               xrp.defaultOrder();
           }else{
               xrp.defaultOrder();
           }
       });
   }());
}(window);


