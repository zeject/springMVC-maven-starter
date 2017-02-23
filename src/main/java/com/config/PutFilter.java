package com.config;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "httpPutFormcontentFilter", description = "过滤器", urlPatterns = "/*")
public class PutFilter extends org.springframework.web.filter.HiddenHttpMethodFilter {

}
