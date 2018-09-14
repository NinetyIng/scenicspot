package com.ythd.ower.api.common;

import com.ythd.ower.api.check.ProductParamCheck;
import com.ythd.ower.api.controller.product.AppProductIssue;
import com.ythd.ower.b2c.dto.ProductListDto;
import com.ythd.ower.b2c.model.ProductModel;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.ibox.DtoUtils;
import com.ythd.ower.common.ibox.GenericResponseDto;
import com.ythd.ower.common.ibox.Result;
import com.ythd.ower.common.tools.MapperUtil;
import com.ythd.ower.member.constant.UserConstant;
import com.ythd.ower.member.service.AppUserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class  ${CLASSNAME}.
 *
 * Description:
 *
 * @author: liujunbo
 * @since: 2018/9/11
 * @version: $Revision$
 */
@RestController
@RequestMapping("/api/address/")
public class AppAddressIssue extends BaseController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AppAddressIssue.class);

  @Autowired
  private AppUserService appUserService;

  /**
   * 用户地址列表接口
   * @param requestParam
   * @return
   */
  @RequestMapping(value = "list", method = RequestMethod.POST)
  public GenericResponseDto list(@RequestBody PageData requestParam){
    LOGGER.info("请求用户地址列表接口，请求参数：{}", MapperUtil.toJson(requestParam));
    Result<GenericResponseDto> checkResult = ProductParamCheck.checkAddressListParam(requestParam);
    if(checkResult.isFailure()){
      return checkResult.getData();
    }
    String result =  MapperUtil.toJson(appUserService.addressList(requestParam));
    LOGGER.info("用户地址列表接口响应数据为{}",result);
    return DtoUtils.getSuccessResponse(MapperUtil.toMap(result));
  }

  /**
   * 用户添加地址
   * @param requestParam
   * @return
   */
  @RequestMapping(value = "add", method = RequestMethod.POST)
  public GenericResponseDto add(@RequestBody PageData requestParam){
    LOGGER.info("请求用户添加地址接口，请求参数：{}", MapperUtil.toJson(requestParam));
    Result<GenericResponseDto> checkResult = ProductParamCheck.checkAddAddressParam(requestParam);
    if(checkResult.isFailure()){
      return checkResult.getData();
    }
    requestParam.put(UserConstant.USERID,getUser().getId());
    requestParam.put("def","1");
    appUserService.addAddress(requestParam);
    LOGGER.info("新增地址成功");
    return DtoUtils.getSuccessResponse();
  }

  /**
   * 用户删除地址
   * @param requestParam
   * @return
   */
  @RequestMapping(value = "del", method = RequestMethod.POST)
  public GenericResponseDto del(@RequestBody PageData requestParam){
    LOGGER.info("请求用户删除地址接口，请求参数：{}", MapperUtil.toJson(requestParam));
    Result<GenericResponseDto> checkResult = ProductParamCheck.checkIdParam(requestParam);
    if(checkResult.isFailure()){
      return checkResult.getData();
    }
    appUserService.deleteAddress(requestParam);
    LOGGER.info("删除地址成功");
    return DtoUtils.getSuccessResponse();
  }

}
