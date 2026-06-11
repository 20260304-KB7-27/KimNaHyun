package org.scoula.ex04;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/cart_save")
public class CartSaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();

        ArrayList<String> list = (ArrayList<String>) session.getAttribute("product");

        if (list == null) {
            list = new ArrayList<String>();
            session.setAttribute("product", list);
        }

        String product = request.getParameter("product");

        if (product != null) {
            list.add(product);
        }

        session.setAttribute("product", list);
        // html 작성
        out.println("<html><body>");
        out.println("Product 추가!!<br/>");
        out.println("<a href=\"session_product.jsp\">상품 선택 페이지</a> <br/>");
        out.println("<a href=\"cart_view\">장바구니 보기</a>");
        out.println("</body></html>");
    }
}