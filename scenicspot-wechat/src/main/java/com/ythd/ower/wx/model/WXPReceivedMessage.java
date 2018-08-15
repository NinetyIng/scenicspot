package com.ythd.ower.wx.model;
/**
 * 收到的消息
 * @author WL
 *
 */
public class WXPReceivedMessage extends WXPMessage {
	
	private long messageId;

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	
}
