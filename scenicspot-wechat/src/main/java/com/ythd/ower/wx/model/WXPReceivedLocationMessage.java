package com.ythd.ower.wx.model;
/**
 * 位置消息
 * @author WL
 *
 */
public class WXPReceivedLocationMessage extends WXPReceivedMessage{
	private double latitude;
	private double longitude;
	private int scale;
	private String label;
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
