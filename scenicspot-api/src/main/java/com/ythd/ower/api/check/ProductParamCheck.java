package com.ythd.ower.api.check;

import com.ythd.ower.b2c.constant.ProductConstant;
import com.ythd.ower.common.constants.ErrorCodesContants;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.exception.BizServiceException;
import com.ythd.ower.common.ibox.DtoUtils;
import com.ythd.ower.common.ibox.GenericResponseDto;
import com.ythd.ower.common.ibox.Result;
import org.apache.commons.lang3.StringUtils;

/**
 * 商品接口 参数校验
 * Created by junbo
 * on 2018/8/19
 */
public class ProductParamCheck {

    public interface  Regular{
        String POSITIVE_INTEGER = "^\\d+$";
    }
    public static Result<GenericResponseDto> checkProductDetailParam(PageData pageData){
        if(StringUtils.isEmpty(pageData.getAsString(ProductConstant.PRODUCT_ID))
                || !pageData.getAsString(ProductConstant.PRODUCT_ID).matches(Regular.POSITIVE_INTEGER)){
            return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.PARAM_ERROR));
        }
        return Result.success();
    }

    public static Result<GenericResponseDto> checkStockDetail(PageData pageData){
        if (StringUtils.isEmpty(pageData.getAsString(ProductConstant.ATTR_VALS))){
            return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.PARAM_ERROR));
        }
        if(StringUtils.isEmpty(pageData.getAsString(ProductConstant.PRODUCT_ID))
                || !pageData.getAsString(ProductConstant.PRODUCT_ID).matches(Regular.POSITIVE_INTEGER)){
            return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.PARAM_ERROR));
        }
        return Result.success();
    }


    public static Result<GenericResponseDto> checkProductList(PageData pageData){
        return Result.success();
    }


    public static void main(String[] args) {

        System.out.println("34".matches(Regular.POSITIVE_INTEGER));

    }

}
