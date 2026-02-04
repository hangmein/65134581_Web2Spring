package baith1_3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AboutMe")
public class AboutMe extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AboutMe() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String htmlContent = """
			<!DOCTYPE html>
			<html lang="vi">
			<head>
			    <meta charset="UTF-8">
			    <meta name="viewport" content="width=device-width, initial-scale=1.0">
			    <title>mệt mún chớt</title>
			    <style>
			        body { font-family: 'Segoe UI', Arial, sans-serif; background-color: #f0f2f5; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }
			        
			        .card { background-color: white; width: 350px; padding: 40px; border-radius: 15px; box-shadow: 0 10px 25px rgba(0,0,0,0.1); text-align: center; }
			        
			        .subtitle { color: #777; font-size: 14px; margin-bottom: 20px; }
			        
			        .info-list {
			            text-align: left;
			            background-color: #fafafa; /
			            padding: 20px;
			            border-radius: 12px;
			            margin-top: 20px;
			        }
			        
			        .info-list p { margin: 10px 0; border-bottom: 1px dashed #ddd; padding-bottom: 5px; }
			        .info-list p:last-child { border-bottom: none; }
			        
			        img { border-radius: 50%; object-fit: cover; border: 4px solid #eee; margin-bottom: 10px; }
			    </style>
			</head>
			<body>
			    <div class="card">
			        <img src="images/nghia_dep_trai.jpg" alt="Huỳnh Đức Nghĩa" width="150" height="150">
			        
			        <h3>Huỳnh Đức Nghĩa</h3>
			        <p class="subtitle">Sinh viên CNTT - Đại học Nha Trang</p>
			        
			        <div class="info-list">
			            <p><b>MSSV:</b> 65134581</p>
			            <p><b>Lớp:</b> 65.CNTT-CLC</p>
			            <p><b>Sở thích:</b> Game</p>
			            <p><b>Email:</b> nghia@gmail.com</p>
			        </div>
			    </div>
			</body>
			</html>
		""";
		
		out.print(htmlContent);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}