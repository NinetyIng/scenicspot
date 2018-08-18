package com.ythd.ower.common.config;

/**
 * @author: liujunbo
 * @since: 2018/8/16
 * @version: $Revision$
 */
public class SystemConfigure {

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 域名
     */
    private String domain;


    private FtpConfigure ftpConfigure;

    public FtpConfigure getFtpConfigure(){
        return ftpConfigure;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public FtpConfigure buliderFtp(){
        ftpConfigure = new FtpConfigure();
        return ftpConfigure;
    }
    public class FtpConfigure{
        private String ip;
        private String username;
        private Integer port;
        private String password;
        private String imageShowPath;

        public String getIp() {
            return ip;
        }

        public FtpConfigure setIp(String ip) {
            this.ip = ip;
            return this;
        }
        public Integer getPort() {
            return port;
        }
        public FtpConfigure setPort(Integer port) {
            this.port = port;
            return this;
        }
        public String getUsername() {
            return username;
        }

        public FtpConfigure setUsername(String username) {
            this.username = username;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public FtpConfigure setPassword(String password) {
            this.password = password;
            return this;
        }

        public String getImageShowPath() {
            return imageShowPath;
        }

        public FtpConfigure setImageShowPath(String imageShowPath) {
            this.imageShowPath = imageShowPath;
            return this;
        }
    }
}
