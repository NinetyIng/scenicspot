/**
 * easaa 弹出框定义
 */
var LayerAlert = {}

LayerAlert.alert = function(conent, prompt) {
	prompt = prompt || '提示';
	if (!conent) {
		return;
	}
	this.html = '<div class="prompt"><b></b><div class="promptBox"><div class="pTop"><img src="/statics/images/close_icon.png" /></div> <div class="pWord"><img src="/statics/images/tishikuang.png" /><span>'
			+ prompt
			+ '</span></div><p>'
			+ conent
			+ '</p><div class="pInput"><input type="button" class="confirm_alert" value="确认" style="background:#009ff0; margin-right:22px;"/></div></div></div><script></script>'
	$("body").append(this.html);
	$(".confirm_alert").click(function() {
		var thisVla = $(this).val();
		$(this).parent().parent().parent().remove();
	/*	if (thisVla == "确认") {
			return true;
		} else {
			return false;
		}*/
	});
}

