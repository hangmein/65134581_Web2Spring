package baith1_2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Servlet implementation class TestDoPost
 */
@WebServlet("/TestDoPost")
public class TestDoPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestDoPost() {
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
		out.println("<head><title>Bài tập thực hành 1_2</title></head>");
		out.println("<body>");
		out.println("<h1>Bài thực hành BT1_1</h1>");
		
		out.println("<h2>Test phương thức doPost</h2>");
		out.println("<form method = 'POST' action = 'TestDoPost'>");
		out.println("	Nhập tên: <input type = 'text' name = 'ho_ten' required/>");
		out.println("	<input type = 'submit' value = 'Gửi lời chào' />");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String tenNhanDuoc = request.getParameter("ho_ten");
		
		PrintWriter out = response.getWriter();
		out.println("<h1>Kết quả từ doPost: </h1>");
		out.println("<h2>Xin chào: " + tenNhanDuoc + "</h2>");
		
		out.println("<a href='TestDoPost'>Quay lại trang nhập</a>");
	}

}
