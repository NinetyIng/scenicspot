package com.ythd.ower.wx.model;

/**
 * 事件消息
 * @author WL
 *
 */
public class WXPReceivedEventMessage extends WXPReceivedMessage {
	private String event;
	private String eventKey;
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEventKey() {
		return eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}
