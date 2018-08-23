;!function(win,undefined){
   var config = {
       url:"/api/product/list.shtm",
       reqParam:{"currentPage":0,"priceOrder":0,"saleOrder":0,"categoryId":0,"pageSize":10}
   },dom = {_window:$(win),_document:$(document),_html:$('html'),_body:$('body'),_loadmore:$('.load-more'),_content:$("#sortDefault"),_condition:$(".control-item")},xrp={};
    xrp.html = '     <li class="table-view-cell media">\n'
        + '                <a class="navigate-right">\n'
        + '                    <img class="media-object pull-left" src="/statics/app/img/dstc/3.jpg"/>\n'
        + '                    <div class="media-body">\n'
        + '                        <div class="title-name">\n'
        + '                            鱼包韭菜\n'
        + '                            <div class="recomment">特别推荐</div>\n'
        + '                        </div>\n'
        + '                        <p><i class="rating-star-50"></i><span class="score">5.0</span></p>\n'
        + '                        <p class="moods">230人付款</p>\n'
        + '                        <div class="price">¥ 200.00</div>\n'
        + '                    </div>\n'
        + '                </a>\n'
        + '            </li>';
   //初始化面板方法
    xrp.initPanel = function(){
        dom._content.empty();
        dom._loadmore.hide();
        xrp.request(true);
    };

   xrp.request = function (first) {
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
        dom._loadmore.attr("disabled",true);
        xrp.request();
     });
   };
   xrp.isRequest = function(){
       return config.reqParam.pageSize == globalConfig.pageSize;
   };
   xrp.analysis = function(data){
       dom._loadmore.removeAttr("disabled");
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
               $createNode.find(".media-object").attr("src",[data.imagePrix,productList[i].listImg].join(""));
               $createNode.find(".title-name").text(productList[i].goodsName);
               $createNode.find(".moods").text(productList[i].virtualSales+"人付款");
               $createNode.find(".price").text("￥"+productList[i].shopPrice)
               dom._content.append($createNode);
           }
       }else{
           alert(data.errorDesc);
       }
   };
   xrp.xr = (function(){
       xrp.initPanel();
       xrp.loadMore();
       dom._condition.click(function(){
       });
   }());
}(window);


