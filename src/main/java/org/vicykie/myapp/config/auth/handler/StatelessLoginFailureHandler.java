package org.vicykie.myapp.config.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.vicykie.myapp.util.HttpUtils;
import org.vicykie.myapp.vo.ResponseVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vicykie on 2016/6/3.
 */
@Component("loginFailureHandler")
public class StatelessLoginFailureHandler implements AuthenticationFailureHandler {
    private static Logger logger = LogManager.getLogger(StatelessLoginFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String contentType = request.getContentType();
        logger.error(e.getMessage());
        SecurityContextHolder.clearContext();
        logger.info("clear security context");

        if (!HttpUtils.isAjax(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getLocalizedMessage());
        } else {
            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(ResponseVO.loginError(e.getLocalizedMessage()));
            PrintWriter writer = response.getWriter();
            writer.write(str);
            writer.flush();
            writer.close();
        }
    }
}
