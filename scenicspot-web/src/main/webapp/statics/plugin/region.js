var regionselector={};
regionselector.level=3;
regionselector.init=function (){
	var province= $("#province"); 
	var city = $("#city"); 
	var area = $("#area"); 
	try{
		regionselector.province=$(province).attr("data-value");
		regionselector.city=$(city).attr("data-value");
		regionselector.area=$(area).attr("data-value");
	}catch(e){
	}
	if(province!=null){
		regionselector.loadP();
		$(province).change(regionselector.onProvinceChange);
	}
	if(city!=null){
		$(city).change(regionselector.onCityChange);
	}
	if(area!=null){
		$(area).change(regionselector.onAreaChange);
	}
};
regionselector.onProvinceChange=function (){
	var selOpt = $("#province").find("option:selected");
	if(selOpt!=null){
		$("#city").empty();
		$("#city").append('<option>请选择城市</option>');
		$("#area").empty();
		$("#area").append('<option>请选择区县</option>');
		regionselector.loadC($(selOpt).attr("data-id"));
	}
};
regionselector.onCityChange=function (){
	var selOpt = $("#city").find("option:selected");
	if(selOpt!=null){
		$("#area").empty();
		$("#area").append('<option>请选择区县</option>');
		regionselector.loadA($(selOpt).attr("data-id"));
	}
};
regionselector.onAreaChange=function (){
};
/**
 * 载入省
 */
regionselector.loadP=function(){
	app.post(null,"http://localhost:81/api/getArea",function(data){
		var province= $("#province");
		var oldValue=regionselector.province;
		var rList=data.data;
		for(var i=0;i<rList.length;i++){
			var item=rList[i];
			var isEQ=regionselector.appendOption(province,oldValue,item);
			if(isEQ){
				regionselector.loadC(item.region_id)
			}
		}
	},function(e){alert(e.msg)})
};
/**
 * 载入市
 */
regionselector.loadC=function(pid){
	app.post({"id":pid},"http://localhost:81/api/getArea",function(data){
		var city= $("#city");
		var oldValue=regionselector.city;
		var rList=data.data;
		for(var i=0;i<rList.length;i++){
			var item=rList[i];
			var isEQ=regionselector.appendOption(city,oldValue,item);
			if(isEQ){
				regionselector.loadA(item.region_id)
			}
		}
	},function(e){alert(e.msg)})
};
/**
 * 载入区
 */
regionselector.loadA=function(pid){
	app.post({"id":pid},"http://localhost:81/api/getArea",function(data){
		var area= $("#area");
		var oldValue=regionselector.area;
		var rList=data.data;
		for(var i=0;i<rList.length;i++){
			var item=rList[i];
			regionselector.appendOption(area,oldValue,item);
		}
	},function(e){alert(e.msg)})
};
regionselector.appendOption=function(selUI,oldValue,item){
	var isEq=false;
	if(oldValue==item.region_name){
		var option = '<option selected="selected"  data-id="'+item.region_id+'"  value="'+item.region_name+'">'+item.region_name+'</option>';
		isEq=true;
	}else{
		var option = '<option data-id="'+item.region_id+'" value="'+item.region_name+'">'+item.region_name+'</option>';
	}
	$(selUI).append(option);
	return isEq;
};
regionselector.init();