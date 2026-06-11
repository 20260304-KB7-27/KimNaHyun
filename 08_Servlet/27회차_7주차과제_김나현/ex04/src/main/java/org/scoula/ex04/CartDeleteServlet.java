package org.scoula.ex04;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/cart_delete")
public class CartDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //MIME 타입 설정
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");


        // 자바 I/O
        PrintWriter out = response.getWriter();

        // products 세션 여부 검사
        HttpSession session = request.getSession(false);


        ArrayList<String> list = (ArrayList<String>) session.getAttribute("product");
        String product = request.getParameter("product");

        if (session != null) {
            session.invalidate();
        }
        // html 작성
        out.println("<html><body>");
        out.println("장바구니 비웠음!!");
        out.println("<a href=\"session_product.jsp\">상품 선택 페이지</a> <br/>");
        out.println("</body></html>");
    }
}