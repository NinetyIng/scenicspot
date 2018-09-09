package com.ythd.ower.common.config;

/**
 * 应用端配置
 * Created by junbo
 * on 2018/8/19
 */
public class AppConfigure {

    private Integer pageSize;

    private ProductConfigure puroductConfig;

    public static AppConfigure bulider(){
        return new AppConfigure();
    }
    public  ProductConfigure builderProductConfig(){
        this.puroductConfig = new ProductConfigure();
        return puroductConfig;
    }
    public ProductConfigure getPuroductConfig() {
        return puroductConfig;
    }

    public AppConfigure setPuroductConfig(ProductConfigure puroductConfig) {
        this.puroductConfig = puroductConfig;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public AppConfigure setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public class ProductConfigure{
        /**
         * 首页推荐记录数
         */
        private Integer indexRecommendCount;
        /**
         * 首页最新发布记录数
         */
        private Integer indexLastCount;
        /**
         * 购物车商品上限
         */
         private Integer cartLimit;
        /**
         * 单个商品购买限制
         */
        private Integer singleProductBuyLimit;
        /**
         * 购买总数限制
         */
        private Integer buyCountLimit;

        private Double noPayOrderCancleTime;

        private String noPayTaskClassName;

        private String defaultMethodName;

        public Integer getCartLimit() {
            return cartLimit;
        }

        public ProductConfigure setCartLimit(Integer cartLimit) {
            this.cartLimit = cartLimit;
            return this;
        }

        public Integer getIndexRecommendCount() {
            return indexRecommendCount;
        }

        public Integer getSingleProductBuyLimit() {
            return singleProductBuyLimit;
        }

        public ProductConfigure setSingleProductBuyLimit(Integer singleProductBuyLimit) {
            this.singleProductBuyLimit = singleProductBuyLimit;
            return this;
        }

        public Integer getBuyCountLimit() {
            return buyCountLimit;
        }

        public ProductConfigure setBuyCountLimit(Integer buyCountLimit) {
            this.buyCountLimit = buyCountLimit;
            return this;
        }

        public ProductConfigure setIndexRecommendCount(Integer indexRecommendCount) {
            this.indexRecommendCount = indexRecommendCount;
            return this;
        }

        public Integer getIndexLastCount() {
            return indexLastCount;
        }

        public ProductConfigure setIndexLastCount(Integer indexLastCount) {
            this.indexLastCount = indexLastCount;
            return this;
        }


        public Double getNoPayOrderCancleTime() {
            return noPayOrderCancleTime;
        }

        public ProductConfigure setNoPayOrderCancleTime(Double noPayOrderCancleTime) {
            this.noPayOrderCancleTime = noPayOrderCancleTime;
            return this;
        }

        public String getNoPayTaskClassName() {
            return noPayTaskClassName;
        }

        public ProductConfigure setNoPayTaskClassName(String noPayTaskClassName) {
            this.noPayTaskClassName = noPayTaskClassName;
            return this;
        }

        public String getDefaultMethodName() {
            return defaultMethodName;
        }

        public ProductConfigure setDefaultMethodName(String defaultMethodName) {
            this.defaultMethodName = defaultMethodName;
            return this;
        }
    }



}
