//确认订单控制js
;!function (win) {
    var config = {
        confirmUrl: "/api/product/confirmOrder.shtm", submitUrl: "/api/product/submitOrder.shtm"
    }, dom = {_window: $(win), _dom: $(win.document), _address: $(".address"),_product:$(".pro-list"),_buyNum:$(".buy-num"),_totalMoney:$(".total-money")}, xrp = {};
    xrp.productHtml = '<div class="pro-item">'
        + '      <img class="pull-left pro-img" src="/statics/app/img/icons/2.jpg"/>'
        + '             <div class="pull-right pro-right">'
        + '             <div class="pro-title">'
        + '              <div class="product-name"></div>'
        + '              <div class="color-96 font-12" style="margin-top: 5px;"></div>'
        + '                </div>'
        + '                <div class="pro-price">'
        + '                <div><span>￥</span><span class="pro-price" style="font-size: 13px;">45.23</span> </div>'
        + '                    <div class="color-96 font-12" style="margin-top: 5px;"><span>X</span><span class="pro-num">1</span></div>'
        + '                </div>'
        + '             </div>'
        + '             <div class="clear"></div>'
        + '      </div>';
    xrp.quarantine = '<div style="background-color: #FFF;width: 100%;height: 8px;"></div>';
    xrp.initRequest = function () {
        Ajax.request(config.confirmUrl, {
            "data": {"productId": $("#productId").val()}, "success": function (data) {
                xrp.analysis(data);
            }
        });
    };
    xrp.analysis = function (data) {
       if(data.resultCode != globalConfig.SUCCESSCODE){
           alert(data.errorDesc);
           return;
       }
       //渲染地址
        if(data.userAddressModel){
            dom._address.find(".pull-left").text("收货人："+data.userAddressModel.conName);
            dom._address.find(".pull-right").text(data.userAddressModel.conPhone);
            dom._address.find(".con-address").text(data.userAddressModel.province +"&nbsp;&nbsp;" + data.userAddressModel.city + "&nbsp;&nbsp;" + data.userAddressModel.area +"&nbsp;&nbsp;"+data.userAddressModel+conAddress);
        }
        //渲染商品信息
        if(data.stockList && data.stockList.length > 0){
           for (var i = 0 ; i < data.stockList.length ; i++){
               var target =  $(xrp.productHtml).clone();
               target.find(".pro-img").attr("src",globalConfig.showUrl + data.stockList[i].listImg);
               target.find(".product-name").text(data.stockList[i].goodsName);
               target.find(".pro-price").text(data.stockList[i].price);
               target.find(".pro-num").text(data.stockList[i].number);
               dom._product.append(target);
               if (i < data.stockList.length - 1){
                   dom._product.append($(xrp.quarantine).clone())
               }
           }
        }
        //渲染支付信息
        dom._buyNum.text(data.totalNumber);
        dom._totalMoney.text(data.totalMoney);
    };
    xrp.init = function () {
        xrp.initRequest();
        dom._address.click(function(){
            window.location.href="";
        });
    };
    xrp.init();
}(window);