package cn.lovingliu.mmall.component;

import cn.hutool.json.JSONUtil;
import cn.lovingliu.mmall.enums.CommonCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author：LovingLiu
 * @Description: 添加RestfulAccessDeniedHandler 当访问接口没有权限时，自定义的返回结果
 * @Date：Created in 2019-10-24
 */
@Component
public class RestfulAccessDeniedHandler  implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonCode.AUTH_NOT_ALLOW));
        response.getWriter().flush();
    }
}
