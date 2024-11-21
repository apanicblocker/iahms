package cn.apkr.framework.security.handle;

import cn.apkr.common.constant.Constants;
import cn.apkr.common.core.domain.AjaxResult;
import cn.apkr.common.core.domain.model.LoginUser;
import cn.apkr.common.utils.MessageUtils;
import cn.apkr.common.utils.ServletUtils;
import cn.apkr.framework.manager.AsyncManager;
import cn.apkr.framework.manager.factory.AsyncFactory;
import cn.apkr.framework.web.service.TokenService;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.util.Objects;

@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

	@Autowired
	private TokenService tokenService;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		LoginUser loginUser = tokenService.getLoginUser(request);
		if (Objects.nonNull(loginUser)) {
			String username = loginUser.getUsername();
			// 删除用户缓存记录
			tokenService.delLoginUser(loginUser.getToken());
			// 记录用户登出日志
			AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGOUT,
					MessageUtils.message("user.logout.success")));
		}
		ServletUtils.renderString(response,
				JSON.toJSONString(AjaxResult.success(MessageUtils.message("user.logout.success"))));
	}
}
