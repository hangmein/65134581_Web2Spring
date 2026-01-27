package baith_1;

import jakarta.servlet.ServletException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class TestDoGet
 */
@WebServlet("/TestDoGet")
public class TestDoGet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestDoGet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter(); 
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><title>Thuc Hanh BT lab 1 </title></head>");
		out.println("<body>");
		out.println("<h1>Bài thực hành đầu tiên BT1</h1>");
		out.println("<h2>Test phương thức doGet</h2>");
		out.println("<p>Họ tên: Huỳnh Đức Nghĩa</p>");
		out.println("<p>MSSV: 65134581</p>");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
