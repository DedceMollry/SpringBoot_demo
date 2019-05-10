package com.zhou.demo.filter;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 跨域处理
 */
@Component
public class CorsFilter implements Filter {
	
	private static final Logger LOGGER = LoggerFactory.getClass(CorsFilter.class);
	
    /**
     * JSON web token 在请求头上的名字
     */
    private String tokenHeader = "X_Auth_Token";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("X_Auth_Token");
        System.out.println("token : " + token);
        String Origin = request.getHeader("Origin");
        System.out.println("Origin : "+ Origin);
        System.out.println("tokenHeader : " +  this.tokenHeader);
        Logger logger = Logger.getLogger(this.getClass());
        logger.info("Origin : "+ Origin);
        response.setHeader("Access-Control-Allow-Origin", Origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, " + this.tokenHeader);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() { }
}
