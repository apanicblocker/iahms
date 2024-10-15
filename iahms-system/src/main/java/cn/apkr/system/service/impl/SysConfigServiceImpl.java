package cn.apkr.system.service.impl;

import cn.apkr.system.service.SysConfigService;
import org.springframework.stereotype.Service;

@Service
public class SysConfigServiceImpl implements SysConfigService {

    public boolean selectCaptchaEnabled() {
        return false;   // TODO
    }

}
