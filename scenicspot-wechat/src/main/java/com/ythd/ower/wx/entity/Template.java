package com.ythd.ower.wx.entity;

import java.util.List;
import lombok.Data;


@Data
public class Template {
  /**
   * 消息接收方
   */
  private String toUser;
  /**
   * 模板id
   */
    private String templateId;
  /**
   * 详情连接
   */
  private String url;
  /**
   * 参数列表
    */
  private List<TemplateParam> templateParamList;

  public String toJSON() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("{");
    buffer.append(String.format("\"touser\":\"%s\"", this.toUser)).append(",");
    buffer.append(String.format("\"template_id\":\"%s\"", this.templateId)).append(",");
    buffer.append(String.format("\"url\":\"%s\"", this.url)).append(",");
    buffer.append("\"data\":{");
    TemplateParam param = null;
    for (int i = 0; i < this.templateParamList.size(); i++) {
      param = templateParamList.get(i);
      // 判断是否追加逗号
      if (i < this.templateParamList.size() - 1){
        buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"},", param.getName(), param.getValue(), param.getColor()));
      }else{
        buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"}", param.getName(), param.getValue(), param.getColor()));
      }
    }
    buffer.append("}");
    buffer.append("}");
    return buffer.toString();
  }

}
