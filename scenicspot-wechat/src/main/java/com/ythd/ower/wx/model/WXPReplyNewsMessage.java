package com.ythd.ower.wx.model;

import java.util.List;

import com.easaa.wechat.protocol.WXMessageType;

/**
 * 回复图文消息
 * @author WL
 *
 */
public class WXPReplyNewsMessage extends WXPReplyMessage {
	private int articleAmount;
	private List<WXPArticle> articles;
	public List<WXPArticle> getArticles() {
		return articles;
	}
	public void setArticles(List<WXPArticle> articles) {
		this.articles = articles;
	}
	public int getArticleAmount() {
		return articleAmount;
	}
	public void setArticleAmount(int articleAmount) {
		this.articleAmount = articleAmount;
	}
	@Override
	public String getMessageType() {
		return WXMessageType.NEWS;
	}
}
