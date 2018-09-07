package com.ythd.ower.member.model;


/**
 * Description:
 * @author: liujunbo
 * @since: 2018/9/7
 * @version: $Revision$
 */
public class UserModel {

  private Integer id;

  private String userName;

  private String phone;

  private String password;

  private String nikeName;

  private String registerChannel;

  private String registerTime;

  private Integer subscribe;

  private String headimgurl;

  private String subscribeTime;

  private String openId;

  private Integer buyer;

  private Integer loginer;

  private Integer commenter;

  public static UserModel builder(){
    return new UserModel();
  }

  public Integer getId() {
    return id;
  }

  public UserModel setId(Integer id) {
    this.id = id;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public UserModel setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public UserModel setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserModel setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getNikeName() {
    return nikeName;
  }

  public UserModel setNikeName(String nikeName) {
    this.nikeName = nikeName;
    return this;
  }

  public String getRegisterChannel() {
    return registerChannel;
  }

  public UserModel setRegisterChannel(String registerChannel) {
    this.registerChannel = registerChannel;
    return this;
  }

  public String getRegisterTime() {
    return registerTime;
  }

  public UserModel setRegisterTime(String registerTime) {
    this.registerTime = registerTime;
    return this;
  }

  public Integer getSubscribe() {
    return subscribe;
  }

  public UserModel setSubscribe(Integer subscribe) {
    this.subscribe = subscribe;
    return this;
  }

  public String getHeadimgurl() {
    return headimgurl;
  }

  public UserModel setHeadimgurl(String headimgurl) {
    this.headimgurl = headimgurl;
    return this;
  }

  public String getSubscribeTime() {
    return subscribeTime;
  }

  public UserModel setSubscribeTime(String subscribeTime) {
    this.subscribeTime = subscribeTime;
    return this;
  }

  public String getOpenId() {
    return openId;
  }

  public UserModel setOpenId(String openId) {
    this.openId = openId;
    return this;
  }

  public Integer getBuyer() {
    return buyer;
  }

  public UserModel setBuyer(Integer buyer) {
    this.buyer = buyer;
    return this;
  }

  public Integer getLoginer() {
    return loginer;
  }

  public UserModel setLoginer(Integer loginer) {
    this.loginer = loginer;
    return this;
  }

  public Integer getCommenter() {
    return commenter;
  }

  public UserModel setCommenter(Integer commenter) {
    this.commenter = commenter;
    return this;
  }
}
