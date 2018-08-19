package com.ythd.ower.api.dto;

import com.ythd.ower.b2c.constant.ProductConstant;
import com.ythd.ower.b2c.model.ProductModel;
import com.ythd.ower.common.dto.PageData;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.List;

/**
 * 首页数据结构
 * Created by junbo
 * on 2018/8/19
 */

public class ProductIndexDto {

    /**
     * 商品广告
     */
    //private List<AdModel> ads;

    /**
     * 人气推荐
     */
    private List<ProductModel> recommeds;

    /**
     * 最新上架
     */
    private List<ProductModel> latestRelease;


    public static ProductIndexDto builder(){
        return new ProductIndexDto();
    }


    public List<ProductModel> getRecommeds() {
        return recommeds;
    }

    /**
     *
     * @param recommeds
     * @return
     */
    public ProductIndexDto setRecommeds(List<ProductModel> recommeds) {
        this.recommeds = recommeds;
        return this;
    }

    public List<ProductModel> getLatestRelease() {
        return latestRelease;
    }

    public ProductIndexDto setLatestRelease(List<ProductModel> latestRelease) {
        this.latestRelease = latestRelease;
        return this;
    }
}
