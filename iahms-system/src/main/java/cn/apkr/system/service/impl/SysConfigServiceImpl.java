package cn.apkr.system.service.impl;

import cn.apkr.system.service.ISysConfigService;
import org.springframework.stereotype.Service;

@Service
public class SysConfigServiceImpl implements ISysConfigService {

    public boolean selectCaptchaEnabled() {
        return false;   // TODO
    }

}
