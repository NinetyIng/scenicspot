package com.ythd.ower.common.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * The class  ${CLASSNAME}.
 *
 * Description:
 *
 * @author: liujunbo
 * @since: 2018/9/6
 * @version: $Revision$
 */
public class IdentityFactoryUtils {
  public static final int ORDER_SERIAL_RANDOM_LEN = 3;

  public static final String ORDER_SERIAL_PREFIX = "99";

  private static final int RANDOM_CHECK = 100_000;

  /**
   * 创建primary key  共18位 (格式 12位日期格式+6位随机数)
   * @return
   */
  public static String createPk() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
    return sdf.format(new Date()).concat(getNumber6FromRandom());
  }


  /**
   * 创建六位的随机数
   * @return
   */
  private static String getNumber6FromRandom() {
    Random r = new Random();
    int xx = r.nextInt(1_000_000);
    while (xx < RANDOM_CHECK) {
      xx = r.nextInt(1_000_000);
    }
    return String.valueOf(xx);
  }
}
