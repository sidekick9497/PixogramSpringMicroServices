package com.sidekick.pixogram.apigateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

// Registers this class as Zuul Filter
// ZullFilter is an abstract class
// a Zuul filter have object access similar to Servlet
@Component
public class LoggingFilter extends ZuulFilter{

	private Logger logger  = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		// true : this filter is active
		// false : inactive
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		// filter logic goes here
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		this.logger.info("Zuul Intercepts : " + request.getRequestURL());
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		// pre : will intercept the incoming request
		// post : will intercept outgoing response
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		// lower value will have higher priority
		return 0;
	}

}
