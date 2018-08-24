;!function (win) {
    var config ={
        url:"/api/product/detail.shtm",
        getStockUrl:"/api/product/stockDetail.shtm",
        requestData:'',
        submitParam:{
            skuId:'',
            price:'',
            productId:''
        }
    },dom={_window:$(win),_document:$(win.document),_body:$("body"),_collect:$(".collect"),
        _buyNowBtn:$(".btn-buy"),
        _price:$(".pro-price"),
        _proOthers:$(".pro-others"),
        _productDetail:$(".img-wrap"),
        _saleAttr:$("#specificationPage"),
        _proNum:$(".pro-num"),
        _productContnet:$(".pro-bref"),
        _album:$("#swiper-container"),
        _productTitle:$(".bar-nav")
        },
        xrp = {};
    xrp.attrHtml = '<div class="spec-title padding-left-5">规格</div>\n'
        + '                <ul class="spec-list padding-left-5">\n'
        + '                    <li>礼盒包装</li>\n'
        + '                </ul>';
    xrp.initPinal = function () {
        xrp.request();
    };
    xrp.request = function(){
        Ajax.request(config.url,{"data":{"productId":$("#productId").val()},"success":function (data) {
           xrp.analysis(data);
        }});
    };
    xrp.analysis = function (data) {
        if(data.resultCode == globalConfig.SUCCESSCODE){
            if (!data.attrs || data.attrs.length == 0){
                dom._body.find(".pro-choose").hide();
            }else{
                dom._body.find(".pro-choose").show();
                dom._price.children().eq(0).text("￥"+data.shopPrice);
                dom._price.children().eq(1).text("￥"+data.marketPrice);
                dom._productContnet.find(".pro-title").text(data.goodsName);
                dom._productContnet.find(".pro-intro").text(data.goodsSummary);
                dom._productDetail.html(data.goodsDesc);
                $(".total-price").children().eq(0).text("￥"+data.shopPrice);

               /* dom._productTitle.find(".title").text(data.goodsName);*/
                dom._productTitle.find(".pro-img").html('<img style="width: 100px;height: 100px;" src="'+data.listImg+'"  onerror="this.src='+"'"+'/statics/app/img/10.jpg'+"'"+'"/>');
                var gghtml = '';
                var attrs = data.attrs;
                for (var i = 0 ; i < attrs.length ;i++){
                    gghtml = gghtml+ '<div class="spec-title padding-left-5">'+attrs[i].attrName+'</div><ul class="spec-list padding-left-5">';
                    var attrValues = attrs[i].attrValues.split(",");
                    for (var j = 0;j< attrValues.length ; j++){
                        gghtml = gghtml+'<li>'+attrValues[j]+'</li>';
                    }
                    gghtml = gghtml+'</ul>';
                }
                dom._proNum.before(gghtml);
                //轮播图片
                if(data.album.length == 0){
                    data.album.push(data.listImg);
                }
                for (var i = 0 ; i < data.album.length; i++){
                    dom._album.children().eq(0).append(' <div class="swiper-slide"><img style="height:250px;" src="'+data.album[i].originalImg+'" onerror="this.src='+"'"+'/statics/app/img/10.jpg'+"'"+'" alt=""/></div>');
                }
                //封装提交参数
                config.submitParam.skuId = data.skuId;
                config.submitParam.price = data.shopPrice;
                config.submitParam.productId = data.id;
                config.requestData = data;
            }
        }else{
            alert(data.errorDesc);
        }
    };
    xrp.buyNow = function () {
       if(config.requestData.attrs && config.requestData.attrs.length > 0){
           openModal();
       }
    };
    //请求属性组合的库存
    xrp.getStock = function () {
        Ajax.request(config.url,{"data":{"productId":$("#productId").val()},"success":function (data) {
                xrp.analysis(data);
            }});
    };
   (function(){
       xrp.initPinal();
       dom._buyNowBtn.click(function(){
           xrp.buyNow();
       });
    })();
}(window);