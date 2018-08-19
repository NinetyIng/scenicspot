package com.ythd.ower.b2c.service.impl;

import com.ythd.ower.b2c.constant.ProductConstant;
import com.ythd.ower.b2c.mapper.AppProductCommentMapper;
import com.ythd.ower.b2c.mapper.AppProductMapper;
import com.ythd.ower.b2c.mapper.AppProductStockMapper;
import com.ythd.ower.b2c.model.ProductModel;
import com.ythd.ower.b2c.service.AppProductService;
import com.ythd.ower.common.config.ConfigureManager;
import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品服务类 提供商品操作基础服务
 * 该服务类只提供基础的功能  列表和详情
 * 具体页面的返回值 在api项目中组装
 */
@Service
public class AppProductServiceImpl implements AppProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppProductServiceImpl.class);
    @Resource
    private AppProductStockMapper appProductStockMapper;

    @Resource
    private AppProductMapper appProductMapper;

    @Resource
    private AppProductCommentMapper appProductCommentMapper;

    @Override
    public List<ProductModel> productListByPage(PageData requestParam) {
        LOGGER.info("开始构建page对象");
        Page page = Page.builder(requestParam);
        //从配置中读取商品分页显示条数
        page.setShowCount(ConfigureManager.getAppConfig().getPuroductConfig().getPageSize());
        List<ProductModel> list = appProductMapper.findProductByPage(page);
        return list;
    }

    @Override
    public List<ProductModel> productList(PageData requestParam) {
        return  appProductMapper.findProduct(requestParam);
    }

    @Override
    public ProductModel productDetail(PageData requestParam) {
        return appProductMapper.findProductDetail(requestParam.getAsInteger(ProductConstant.PRODUCT_ID));
    }
}
