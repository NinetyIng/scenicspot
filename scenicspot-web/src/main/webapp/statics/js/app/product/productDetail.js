;!function(win) {
    var config ={
        url:"/api/product/detail.shtm",
        getStockUrl:"/api/product/stockDetail.shtm",
        requestData:'',
        attrNames:[],
        submitParam:{
            skuId:'',
            price:'',
            productId:'',
            stock:0
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
        _productTitle:$(".bar-nav"),
        _attrList:$(".spec-list"),
        _selectTip:$(".selectTip")
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
            }
            dom._price.children().eq(0).text("￥"+data.shopPrice);
            if(data.marketPrice){
                dom._price.children().eq(1).text("￥"+data.marketPrice);
            }
            dom._productContnet.find(".pro-title").text(data.goodsName);
            dom._productContnet.find(".pro-intro").text(data.goodsSummary);
            $(".pro-content").text(data.goodsSummary);
            dom._productDetail.html(data.goodsDesc);
            $(".total-price").children().eq(0).text("￥"+data.shopPrice);
            //设置属性选择时 价格
            alert(data.minPrice +"  "+ data.maxPrice);
            if(data.minPrice && data.maxPrice){
                dom._saleAttr.find(".title").text("￥"+data.minPrice +" - " + data.maxPrice);
            }
            //设置属性选择时图片
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
                config.attrNames.push(attrs[i].attrName);
            }
            dom._proNum.before(gghtml);
            //设置选择属性时候提示语
            dom._selectTip.text("请选择 "+config.attrNames.join(" "));
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
        }else{
            alert(data.errorDesc);
        }
    };
    //请求属性组合的库存
    xrp.getStock = function (selectResult) {
        if(selectResult.isComplete){
            var attrJson = [];
            for (var i = 0 ; i < selectResult.selectAtrr.length ; i++){
                attrJson.push(selectResult.selectAtrr[i].name+":" + selectResult.selectAtrr[i].value);
            }
            Ajax.request(config.getStockUrl,{"data":{"productId":$("#productId").val(),"attrVals":attrJson.join("|")},
                "success":function (data) {
                   if(data.resultCode == 1){
                       dom._saleAttr.find(".title").text("￥" + data.price);
                       config.submitParam.skuId = data.id;
                       config.submitParam.stock = data.stock;
                   }else{
                       alert(data.errorDesc);
                   }
            }});
        }
    };
    xrp.selectAttr = function (_this) {
       var $this = $(_this);
       if (!$this.hasClass("attrSelect")){
           $this.siblings().removeClass("attrSelect").addClass("attrNoSelect");
           $this.addClass("attrSelect").removeClass("attrNoSelect")
           //更改提示语
           dom._selectTip.text(dom._selectTip.text().replace($this.parent().prev().text(),""));
       }else {
           $this.addClass("attrNoSelect").removeClass("attrSelect")
       }
        var selectResult = xrp.addSelectTip();
        xrp.getStock(selectResult);
    };

    xrp.addSelectTip = function () {
        var tipAry = [];
        var aleardySelect = [];
        var aleardyNameMapAttr = [];
        var isSelectComplete = true;
        $(".spec-list").each(function(){
           var nameMapAttt = new Object();
           var lis = $(this).children();
           var isAppend = true;
           for (var i = 0 ; i < lis.length ; i++){
               if(lis.eq(i).hasClass("attrSelect")){
                   isAppend = false;
                   aleardySelect.push(lis.eq(i).text());
                   nameMapAttt.name = $(this).prev().text();
                   nameMapAttt.value = lis.eq(i).text();
               }
           }
           if(isAppend){
               isSelectComplete = false;
               tipAry.push($(this).prev().text());
           }
            aleardyNameMapAttr.push(nameMapAttt);
       });
       if(tipAry.length > 0){
         dom._selectTip.text("请选择 "+ tipAry.join(" "));
       }else{
         dom._selectTip.text("已选择 "+ aleardySelect.join(" "));
       }
       return {selectAtrr:aleardyNameMapAttr,isComplete:isSelectComplete};
    };
    xrp.buyNow = function () {
       if(config.requestData.attrs && config.requestData.attrs.length > 0){
           openModal();
           //判断是否选择完成
           if(dom._selectTip.text().indexOf("请选择")){
               return;
           }
       }
       if(config.submitParam.skuId){
           window.location.href = "/jumplink/confirmOrder?stockId=" + config.submitParam.skuId;
       }
    };
   (function(){
       xrp.initPinal();
       dom._buyNowBtn.click(function(){
           xrp.buyNow();
       });
       $(document).on('click',".spec-list li",function(){
           xrp.selectAttr(this);
       });
    })();
}(window);
