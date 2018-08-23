;!function (win,undefined) {
    var config ={url:"/api/product/index.shtm"},
        dom = {
        _window:$(win),
        _document:$(win.document),
        _html:$("html"),
        _body:$("body"),
        _newest:$(".favourite-wrap"),
        _slide:$(".slide-box")
    },
        xrp = {};
    xrp.initPanel = function(){
      dom._newest.empty();
      dom._slide.empty();
      xrp.request();
    };
    xrp.sileHtml = ' <div class="slide-item" onclick="">\n'
        + '                    <img src="/statics/app/img/spsy/3.jpg" onerror="this.src='+"'"+'/statics/app/img/10.jpg'+"'"+'" alt="">\n'
        + '                    <p>江音琵琶乐器花...</p>\n'
        + '                    <div class="price">￥4630.00</div>\n'
        + '                </div>';
    xrp.newestHtml = '<div class="row">\n'
        + '                    <div class="favourite-item">\n'
        + '                        <img src="/statics/app/img/spsy/5.jpg" onerror="this.src='+"'"+'/statics/app/img/10.jpg'+"'"+'" alt="">\n'
        + '                        <p>红木中阮民族弹拨乐器</p>\n'
        + '                        <div class="price">￥4630.00</div>\n'
        + '                    </div>\n'
        + '                    <div class="favourite-item">\n'
        + '                        <img src="/statics/app/img/spsy/6.jpg" alt="">\n'
        + '                        <p>运智琵琶乐器专业儿童......</p>\n'
        + '                        <div class="price">￥4630.00</div>\n'
        + '                    </div>\n'
        + '                </div>';
    xrp.request = function(){
        Ajax.request(config.url,{"data":{},"success":function(data){
            xrp.analysis(data);
        }});
    };
    xrp.cloneNode = function(type){
        if('side' == type){
            return $(xrp.sileHtml).clone();
        }else{
            return $(xrp.newestHtml).clone();
        }
    };
    xrp.analysis = function(data){
       if(data.resultCode == globalConfig.SUCCESSCODE){
           var recommeds = data.recommeds;
           var latestRelease = data.latestRelease;
           if(recommeds.length > 0){
               for(var i = 0 ; i < recommeds.length ; i++){
                 var node =  xrp.cloneNode('side')
                 node.find("img").attr("src",globalConfig.showUrl + recommeds[i]["listImg"]);
                 node.find("p").text(recommeds[i]["goodsName"]);
                 node.find(".price").text("￥"+recommeds[i]["shopPrice"]);
                 dom._slide.append(node);
               }
           }
          if (latestRelease.length > 0){
               for (var i = 0 ; i < latestRelease.length ; i=i+2){
                   var node =  xrp.cloneNode()
                   var childNode = node.find(".favourite-item").first().clone();
                   node.empty();
                   for (var j = 0; j < 2;j++){
                       childNode.find("img").attr("src",globalConfig.showUrl + latestRelease[i]["listImg"]);
                       childNode.find("p").text(latestRelease[i]["goodsName"]);
                       childNode.find(".price").text("￥"+latestRelease[i]["shopPrice"]);
                       node.append(childNode);
                       childNode = childNode.clone();
                   }
                   dom._newest.append(node);
               }
           }
       }
    };
    (function(){xrp.initPanel();})();
}(window);