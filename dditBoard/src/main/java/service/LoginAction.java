package service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.MemberDAO;
import util.SHA256Util;
import vo.MemberVO;

public class LoginAction implements Action{
	private static LoginAction instance;
	private LoginAction() {}
	public static LoginAction getLoginAction() {
		if(instance == null) {
			instance = new LoginAction();
		}
		return instance;
	}

	
	//@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		MemberDAO dao = MemberDAO.getMemberDAO();
		MemberVO vo = new MemberVO();
		HttpSession session = request.getSession();
		List<Integer> list = new ArrayList<>();
		//String rootPath = "/Users/andaegeun/Desktop/95main/dditBoard";
		//String rootPath = "/tomcat/webapps/ROOT";
		//String root = rootPath + "/src/main/webapp/storage";
		String root = "/adg0807/tomcat/webapps/ROOT/storage";
		
 		File path = new File(root);
 		SHA256Util hash = SHA256Util.getInstance();


 		
 		
 		vo.setMemId(request.getParameter("userId"));
		String pw = hash.encrypt(request.getParameter("userPw"));
		vo.setMemPw(pw);
		
		File[] fileList = path.listFiles();
		String imgName = "defaultImg.png";
		String temp;
		if(fileList != null) {
			for(int i = 0; i < fileList.length; i++) {
				temp = fileList[i].getName();
				int dot = temp.lastIndexOf(".");
				String result = temp.substring(0, dot);
				if(result.equals(vo.getMemId())) {
					imgName = temp;
					break;
				}
			}
		}
		
		PrintWriter out = response.getWriter();
		
		boolean flag = dao.login(vo);
		
		
		list.add(dao.boardCount(vo));
		list.add(dao.commentCount(vo));
		list.add(dao.likeCount(vo));
		list.add(dao.hateCount(vo));
		
		if(flag) {
			MemberVO user = new MemberVO();
			user = dao.selectInfo(vo);
			session.setAttribute("user", user);
			session.setAttribute("userImg", imgName);
			session.setAttribute("count", list);
			out.println("<script>");
			out.println("location.replace('/member?cmd=index')");
			out.println("</script>");
	        
		} else {
			out.println("<html>");
			out.println("<script>");
			out.println("alert('아이디, 비밀번호 확인')");
			out.println("history.back()");
			out.println("</script>");
			out.println("</html>");
		}
		
		
		
		
	}
}














