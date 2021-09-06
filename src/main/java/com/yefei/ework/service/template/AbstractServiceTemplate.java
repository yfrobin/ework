package com.yefei.ework.service.template;

import com.yefei.ework.common.enums.ResultEnum;
import com.yefei.ework.core.security.SecurityException;
import com.yefei.ework.entity.result.CommonResult;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

public abstract class AbstractServiceTemplate {

    public <T> CommonResult<T> execute(CallBack<T> callBack) {
        CommonResult<T> result = new CommonResult<>();

        try {
            T call = callBack.call();
            if (call != null) {
                result = CommonResult.ok(call);
            }
        } catch (AuthenticationException authenticationException) {
            result = processAuthenticationException(authenticationException);
        } catch (Throwable e) {
            result = CommonResult.error(ResultEnum.SYSTEM_ERROR);
        }
        return result;
    }

    protected CommonResult processAuthenticationException(AuthenticationException exception) {
        if (exception.getCause() instanceof SecurityException) {
            SecurityException securityException = (SecurityException) exception.getCause();
            return CommonResult.error(securityException.getCode(), securityException.getErrMsg());
        } else if (exception instanceof BadCredentialsException) {
            return CommonResult.error(ResultEnum.PASSWORD_ERROR);
        } else {
            return CommonResult.error(ResultEnum.UNAUTHORIZED);
        }
    }

}
