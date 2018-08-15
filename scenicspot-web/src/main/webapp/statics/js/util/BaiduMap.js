BaiduMap = function() {
	/**
     * 创建地图对象
     * divId 地图展示div的id
     */
    function getMap(divId) {
    	var map = new BMap.Map(divId);
    	
    	map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
    	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
    	map.addControl(new BMap.NavigationControl());  	//添加默认缩放平移控件
    	map.addControl(new BMap.OverviewMapControl()); 	//添加默认缩略地图控件
    	map.addControl(new BMap.OverviewMapControl({ 	//右下角，打开
    		isOpen: true, 
    		anchor: BMAP_ANCHOR_BOTTOM_RIGHT 
    	})); 
    	
    	return map;
    }
    
    /**
     * 根据经纬度对象定位
     * map 地图对象
     * point 经纬度对象
     * level:地图显示的大小，默认为12
     */
    function searchByPoint(map,point,level) {
    	map.centerAndZoom(point,level?level:12);
		map.addOverlay(new BMap.Marker(point));
    }
    
    /**
     * 根据地址定位
     * map 地图对象
     * address 地址
     * level 地图大小，默认12
     */
    function searchByAddress(map,address,level) {
		searchByPosition(map,{keyword:address},level);
    }
    
    /**
     * 根据经纬度定位
     * map 地图对象
     * lng:经度值
     * lat:纬度值
     * level:地图显示的大小，默认为12
     */
    function searchByLnglat(map,lng,lat,level) {
    	searchByPoint(map,new BMap.Point(lng,lat),level);
    }
    
    /**
     * 根据文字定位，并将经纬度值赋值给组件
     * map 地图对象
     * obj 参数对象
     * obj keyword:查询关键字
     * obj lngId:将获得的经度值赋给id为lngId的组件
     * obj latId:将获得的纬度值赋给id为latId的组件
     */
    function searchByPosition(map,obj,level) {
    	var localSearch = new BMap.LocalSearch(map);	//构建查询
    	localSearch.enableAutoViewport(); 	//允许自动调节窗体大小
    	map.clearOverlays();				//清空原来的标注
    	
    	localSearch.setSearchCompleteCallback(function (searchResult) {
	    	var poi = searchResult.getPoi(0);
	    	map.centerAndZoom(poi.point, level?level:12);
	    	var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地址对应的经纬度

	    	map.addOverlay(marker); 
	    	var content = obj.keyword + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat; 
	    	var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>"); 
	    	marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); }); 
	    	// marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画 
	    	if(obj.lngId) document.getElementById(obj.lngId).value = poi.point.lng;	//如果lngId存在
	    	if(obj.latId) document.getElementById(obj.latId).value = poi.point.lat;	//如果latId存在
    	}); 
    	localSearch.search(obj.keyword);
    }
    
    return {
    	getMap: getMap,
    	searchByPoint: searchByPoint,
    	searchByAddress: searchByAddress,
    	searchByLnglat: searchByLnglat,
    	searchByPosition: searchByPosition
    };
    
}();