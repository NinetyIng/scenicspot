//确认订单控制js
;!function (win) {
    var config = {
        confirmUrl: "/api/product/confirmOrder.shtm", submitUrl: "/api/product/submitOrder.shtm"
    }, dom = {_window: $(win), _dom: $(win.document), _address: $(".address")}, xrp = {};
    rxp.productHtml = '<div class="pro-item">'
        + '      <img class="pull-left pro-img" src="/statics/app/img/icons/2.jpg"/>'
        + '             <div class="pull-right pro-right">'
        + '             <div class="pro-title">'
        + '              <div class="product-name"></div>'
        + '              <div class="color-96 font-12" style="margin-top: 5px;"></div>'
        + '                </div>'
        + '                <div class="pro-price">'
        + '                <div><span>￥</span><span style="font-size: 13px;">45.23</span> </div>'
        + '                    <div class="color-96 font-12" style="margin-top: 5px;"><span>X</span><span>1</span></div>'
        + '                </div>'
        + '             </div>'
        + '             <div class="clear"></div>'
        + '      </div>';
    xrp.xraddress = function () {

    };
    xrp.xrproduct = function () {
    };
    xrp.initRequest = function () {
        Ajax.request(config.confirmUrl, {
            "data": {"productId": $("#productId").val()}, "success": function (data) {
                xrp.analysis(data);
            }
        });
    };
}(window);