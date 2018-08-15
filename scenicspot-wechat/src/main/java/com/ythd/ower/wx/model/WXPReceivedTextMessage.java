package com.ythd.ower.wx.model;
/**
 * 文本消息
 * @author WL
 *
 */
public class WXPReceivedTextMessage extends WXPReceivedMessage {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
