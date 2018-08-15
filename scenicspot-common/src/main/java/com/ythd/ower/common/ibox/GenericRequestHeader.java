package com.ythd.ower.common.ibox;

public class GenericRequestHeader {
    private static final long serialVersionUID = 6397649455306976585L;
    private String clientVersion;
    private String clientType;
    private String channel;
    private String osVersion;
    private String imei;
    private String manufacturer;
    private String model;
    private String sessionId;
    private String productName;

    public GenericRequestHeader() {
    }

    public String getClientVersion() {
        return this.clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getClientType() {
        return this.clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getImei() {
        return this.imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String toString() {
        return "GenericRequestHeader [clientVersion=" + this.clientVersion + ", clientType=" + this.clientType + ", osVersion=" + this.osVersion + ", imei=" + this.imei + ", manufacturer=" + this.manufacturer + ", model=" + this.model + "]";
    }
}
