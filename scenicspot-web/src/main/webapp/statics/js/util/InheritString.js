/*对js的string类封装   公用方法可继续封装在此类中*/

String.prototype.isNotEmpty = function() {
	return this && this.trim() != '';
}

String.prototype.isEmpty = function() {
	return !(this && this.trim() != '');
}
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.isEmail = function() {
	var patrn = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)*\.[\w-]+$/i;
	return patrn.test(this);
}
String.prototype.isUrl = function() {
	var patrn = /^http(s)?:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\:+!]*([^<>])*$/;
	return patrn.test(this);
}
String.prototype.isPhone = function() {
	var patrn = /^((13[0-9])|(15[0-35-9])|(18[0,2,3,5-9]))\d{8}$/;
	return patrn.test(this);
}
String.prototype.isMoney = function(){
	var patrn = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	return  patrn.test(this);
}
String.prototype.isZHCN = function() {
	var patrn = /^[\u4e00-\u9fa5\w]+$/;
	return patrn.test(this);
}
String.prototype.isNumber = function() {
	var patrn = /^\d+$/;
	return patrn.test(this);
}
String.prototype.isEHCN = function() {
	var patrn = /^[a-zA-Z., ]+$/;
	return patrn.test(this);
}
String.prototype.PadLeft = function(totalWidth, paddingChar) {
	if (paddingChar != null) {
		return this.PadHelper(totalWidth, paddingChar, false);
	} else {
		return this.PadHelper(totalWidth, ' ', false);
	}
}
String.prototype.PadRight = function(totalWidth, paddingChar) {
	if (paddingChar != null) {
		return this.PadHelper(totalWidth, paddingChar, true);
	} else {
		return this.PadHelper(totalWidth, ' ', true);
	}
}
String.prototype.replaceAll = function(s1,s2){return this.replace(new RegExp(s1,"gm"),s2);}
//类似jquery append方法
String.prototype.append = function(target) {
	this.ary = [ this ];
	this.ary.push(target);
	return this.ary.join("");
}
