package com.ythd.ower.server.startload;

import com.ythd.ower.common.config.ConfigureManager;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.server.mapper.upm.SystemConfigMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ConfigureLoadListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigureLoadListener.class);

    @Autowired
    private SystemConfigMapper systemConfigMapper;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null)
        {
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
            LOGGER.info("开始加载系统配置文件");
            PageData pd = systemConfigMapper.selectConfig();
            if (pd == null){
                LOGGER.error("配置加载失败，服务即将停止");
                System.exit(0);
            }
            String systemConfigJson = pd.getAsString("systemconfig");
            String wxConfigJson = pd.getAsString("wxconfig");
            if(StringUtils.isEmpty(systemConfigJson) || StringUtils.isEmpty(wxConfigJson)){
                LOGGER.error("配置加载失败，请配置系统配置和微信配置，服务即将退出");
                System.exit(0);
            }
            ConfigureManager.parseWxConfig(wxConfigJson);
            ConfigureManager.parseSystemConfig(systemConfigJson);
        }
    }
}
