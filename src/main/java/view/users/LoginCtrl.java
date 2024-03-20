package view.users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import biz.users.UserDAO;
import biz.users.UserVO;

@WebServlet("/LoginCtrl")
public class LoginCtrl extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetPost(request, response);
	}
	
	protected void doGetPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 데이터
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserDAO udao = new UserDAO();
		UserVO user = udao.getUser(email, password);
	    PrintWriter out = response.getWriter();
	    
		if(user != null) {
			HttpSession session = request.getSession();
			// 바인딩 = name , name값
			session.setAttribute("name", user.getName());
			response.sendRedirect("index.jsp");
		}else {
			out.println("<!DOCTYPE html>");
		    out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Alert</title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<script type='text/javascript'>");
		    out.println("alert('회원정보가 없습니다.');");
		    out.println("history.go(-1);");
		    out.println("</script>");
		    out.println("</body>");
		    out.println("</html>");
		}
	}

}
