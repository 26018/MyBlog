//package com.jh.blog.config;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class loginHandlerInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        System.out.println(request.getRequestURL());
//        String loginUserMail = (String) request.getSession().getAttribute("loginUser");
//
//        for (Cookie cookie : request.getCookies()) {
//            if (cookie.getName().equals("userMail")) {
//                System.out.println(loginUserMail);
//                System.out.println(cookie.getValue());
//                boolean res = cookie.getValue().equals(loginUserMail);
//                System.out.println(res);
//                if (!res) {
//                    response.sendRedirect("/error");
//                }
//                return res;
//            }
//        }
//        return true;
//    }
//}
