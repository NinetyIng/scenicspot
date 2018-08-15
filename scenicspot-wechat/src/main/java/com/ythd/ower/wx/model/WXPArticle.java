package com.ythd.ower.wx.model;

/**
 * 图文消息
 * @author WL
 *
 */
public class WXPArticle {
	private String title;
	private String desciprtion;
	private String pictureURL;
	private String articleURL;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesciprtion() {
		return desciprtion;
	}
	public void setDesciprtion(String desciprtion) {
		this.desciprtion = desciprtion;
	}
	public String getPictureURL() {
		return pictureURL;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}
	public String getArticleURL() {
		return articleURL;
	}
	public void setArticleURL(String articleURL) {
		this.articleURL = articleURL;
	}
}
