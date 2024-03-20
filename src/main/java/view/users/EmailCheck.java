package view.users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.users.UserDAO;

@WebServlet("/EmailCheck")
public class EmailCheck extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetPost(request, response);
	}
	
	protected void doGetPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 처리
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 클라이언트에서 전송된 이메일 파라미터를 가져옴
        String email = request.getParameter("email");
        
        // 이메일 중복 확인을 위한 메소드 호출
        UserDAO udao = new UserDAO();
        boolean isDuplicate = udao.EmailCheck(email);
        
        System.out.println(isDuplicate);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Alert</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<script type='text/javascript'>");
        if(isDuplicate) {
        	out.println("alert('이미 사용중인 이메일입니다.');");
        }else {
        	out.println("alert('사용 가능한 이메일입니다.');");
        }
        out.println("history.go(-1);");
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");

	}

}
