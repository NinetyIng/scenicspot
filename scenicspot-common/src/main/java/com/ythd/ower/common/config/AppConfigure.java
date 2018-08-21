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

    }



}
