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
import biz.users.UserVO;

@WebServlet("/MemberJoinCtrl")
public class MemberJoinCtrl extends HttpServlet {

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
		
		String email = request.getParameter("email")+ "@" +request.getParameter("email2");
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth1") +"-"+ request.getParameter("birth2") +"-"+ request.getParameter("birth3");
		String tel = request.getParameter("tel1") +"-"+ request.getParameter("tel2") +"-"+ request.getParameter("tel3");
		String agree1 = request.getParameter("agree1");
		String agree2 = request.getParameter("agree2");
		
		// 중복이면 회원 가입 안되도록
		UserDAO udao = new UserDAO();
		boolean isDuplicate = udao.EmailCheck(email);
		
		UserVO bean = new UserVO();
		bean.setEmail(email);
		bean.setPassword(password);
		bean.setName(name);
		bean.setGender(gender);
		bean.setBirth(birth);
		bean.setTel(tel);
		
		// 응답에 HTML과 JavaScript 포함
	    PrintWriter out = response.getWriter();
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Alert</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<script type='text/javascript'>");
		if(email == null || password == null || name == null || gender == null || birth == null || tel == null || agree1 == null || agree2 == null) {
			out.println("alert('모든 필수 정보를 입력해주세요.');");
			out.println("history.go(-1);");
		}else if(isDuplicate){
			out.println("alert('이미 사용중인 이메일입니다.');");
			out.println("history.go(-1);");
		}else if(password.equals(password1)){
		    udao.insertUser(bean);
		    out.println("alert('회원가입 성공');");
		    out.println("location.href='login.jsp';");
		}else{
			out.println("alert('비밀번호를 확인해주세요.');");
			out.println("history.go(-1);");
		}
	    out.println("</script>");
	    out.println("</body>");
	    out.println("</html>");
		
		
	
		
	}

}
