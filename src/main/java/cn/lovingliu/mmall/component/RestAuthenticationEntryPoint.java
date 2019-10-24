package cn.lovingliu.mmall.component;

import cn.hutool.json.JSONUtil;
import cn.lovingliu.mmall.enums.CommonCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author：LovingLiu
 * @Description: 当未登录或者token失效访问接口时，自定义的返回结果
 * @Date：Created in 2019-10-24
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonCode.AUTH_ERROR));
        response.getWriter().flush();
    }
}
