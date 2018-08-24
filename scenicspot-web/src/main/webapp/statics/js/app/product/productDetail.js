;!function (win) {
    var config ={
        url:"/api/product/detail.shtm",
        submitParam:{
            "skuId":'',

        }
    },dom={_window:$(win),_document:$(win.document),_body:$("body"),_collect:$(".collect"),
        _buyNowBtn:$(".btn-buy"),
        _productId:$("#productId"),
        _price:$(".pro-price"),
        _proOthers:$(".pro-others"),
        _productDetail:$(".img-wrap"),
        _saleAttr:$("#specificationPage")
        },
        xrp = {};
    xrp.attrHtml = '<div class="spec-title padding-left-5">规格</div>\n'
        + '                <ul class="spec-list padding-left-5">\n'
        + '                    <li>礼盒包装</li>\n'
        + '                </ul>';
    xrp.initPinal = function () {
    };

    xrp.request = function(){
        Ajax.request(config.url,{"data":{"productId":dom._productDetail.val()},"success":function (data) {
           xrp.analysis(data);
        }})
    };

    xrp.analysis = function (data) {
        if(data.resultCode == globalConfig.SUCCESSCODE){
            if (!data.attrs || data.attrs.length == 0){
                dom._body.find(".pro-choose").hide();
            }else{
                dom._body.find(".pro-choose").show();
                for (var i = 0 ; i < data.attrs.length ;i++){
                    var attrNode = $(xrp.attrHtml).clone();
                    var attrValues = data.attr.attrValues.split("\\,");
                    for (var j = 0;j< attrValues.length ; j++){
                        attrNode.append( $('<li></li>').text(attrValues[j]));
                    }
                }
            }



        }else{
            alert(data.errorDesc);
        }
    }
}(window);