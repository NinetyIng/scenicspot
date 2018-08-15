package com.ythd.ower.wx.model;
/**
 * 回复音乐
 * @author WL
 *
 */
public class WXPReplyMusicMessage extends WXPReplyMessage {
	private String title;
	private String description;
	private String musicURL;
	private String HQMusicURL;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMusicURL() {
		return musicURL;
	}
	public void setMusicURL(String musicURL) {
		this.musicURL = musicURL;
	}
	public String getHQMusicURL() {
		return HQMusicURL;
	}
	public void setHQMusicURL(String hQMusicURL) {
		HQMusicURL = hQMusicURL;
	}
}
