package org.scoula.dynamicweb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/*
* URL 파턴은 중괄호로 묶어서 여러개 지정 가능
* */
@WebServlet(urlPatterns = {"/life-cycle", "/life"})
public class LifeCycleServlet extends HttpServlet {

    // 서블릿 요청될 때마다 호출
    // service : doPost, doGet로 나눠짐
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("===> SERVICE 호출");
    }

    // 서블릿 컨테이너가 종료될 때 호출
    @Override
    public void destroy() {
        System.out.println("===> DESTROY 호출");
    }

    // 최초 서블릿 요청 시 호출
    @Override
    public void init() throws ServletException {
        System.out.println("===> INIT 호출");
    }
}