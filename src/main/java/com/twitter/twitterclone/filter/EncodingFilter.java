//package com.twitter.twitterclone.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import java.io.IOException;
//
//@WebFilter(filterName = "EncodingFilter", value = "/*")
//public class EncodingFilter implements Filter {
//
//    private final String characterEncoding = "utf-8";
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        servletRequest.setCharacterEncoding(characterEncoding);
//        servletResponse.setCharacterEncoding(characterEncoding);
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}
