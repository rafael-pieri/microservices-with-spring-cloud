package com.microservices.zuulapigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ZuulInterceptor extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String REQUEST_REQUEST_URI = "request -> {} request uri -> {}";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        final HttpServletRequest request = RequestContext.getCurrentContext().getRequest();

        logger.info(REQUEST_REQUEST_URI, request, request.getRequestURI());

        return null;
    }
}