package org.scoula.ex05;

import org.scoula.ex05.domain.Member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/scope")
public class ScopeServlet extends HttpServlet {

    // ApplicationScope
    ServletContext sc;

    /*
    * EL Scope
    *
    * page -> request -> session -> Application
    * - EL은 내부적으로 getter 메서드를 사용해 객체의 프로퍼티에 접근한다.
    * */

    @Override
    public void init(ServletConfig config) throws ServletException {
        sc = config.getServletContext();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        sc.setAttribute("scopeName", "applicationScope 값"); // Application Scope

        // Session 만들고 sessionScope에
        HttpSession session = req.getSession(); // Session Scope

        session.setAttribute("scopeName", "sessionScope 값");

        req.setAttribute("scopeName", "requestScope 값"); // Request Scope

        Member member = new Member("홍길동", "hong");

        req.setAttribute("member", member);

        req.getRequestDispatcher("scope.jsp").forward(req, res);
    }
}