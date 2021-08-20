package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.Action;
import service.ChangePwAction;
import service.IndexAction;
import service.LoginAction;
import service.LogoutAction;
import service.MoveToSignUpAction;
import service.ResetPwAction;
import service.SearchIdAction;
import service.SignupAction;
import service.UpdateImgAction;
import service.searchAction;

@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		Action action = null;
		
		System.out.println("넘어온 요청 : " + cmd);
		
		if(cmd.equals("loginPro")) {
			action = LoginAction.getLoginAction();
		} else if(cmd.equals("logout")) {
			action = new LogoutAction();
		} else if(cmd.equals("signUpPro")) {
			action = SignupAction.getSignupAction();
		} else if(cmd.equals("searchIdPro")) {
			action = SearchIdAction.getSearchIdAction();
		} else if(cmd.equals("resetPwPro")) {
			action = ResetPwAction.getResetPwAction();
		} else if(cmd.equals("updateImgPro")) {
			action = UpdateImgAction.getUpdateImgAction();
		} else if(cmd.equals("changePwPro")) {
			action = ChangePwAction.getChangePwAction();
		} else if(cmd.equals("index")) {
			action = new IndexAction();
		} else if(cmd.equals("searchId")) {
			action = new searchAction();
		} else if(cmd.equals("moveTosignUp")) {
			action = new MoveToSignUpAction();
		}
		
		action.excute(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		doGet(request, response);
	}

}
