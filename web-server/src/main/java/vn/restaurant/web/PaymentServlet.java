package vn.restaurant.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Demonstration servlet endpoint; Swing client uses its own local demo repository. */
@WebServlet("/api/tables")
public class PaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        String json = "[{\"id\":\"01\",\"status\":\"Trống\"},"
                + "{\"id\":\"05\",\"status\":\"Đang sử dụng\"}]";
        response.getWriter().write(json);
    }
}
