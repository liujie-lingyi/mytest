package cn.smbms.controller.user;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.service.user.UserServiceImpl;
import cn.smbms.tools.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class LoginController {
	@Resource
	private UserService userService;

	/**
	 * 用户登录
	 * @param model
	 * @param userCode
	 * @param userPassword
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doLogin")
	public String doLogin(Model model,
						  String userCode,
						  String userPassword,
						  HttpServletRequest request, HttpServletResponse response) {
		String view = null;
		//调用service方法，进行用户匹配
		User user = userService.login(userCode, userPassword);
		if (null != user) {//登录成功
			//放入session
			request.getSession().setAttribute(Constants.USER_SESSION, user);
			//页面跳转（frame.jsp)
        view = "redirect:/WEB-INF/jsp/frame.jsp";
    } else {
        //页面跳转（login.jsp）带出提示信息--转发
        request.setAttribute("error", "用户名或密码不正确");
        view = "dispatcher:/login.jsp";
        int i=1/0;
    }
		return view;
	}

//	/**
//	 * 定义一个局部的异常捕获方法
//	 * @param request
//	 * @return
//	 */
//	@ExceptionHandler(RuntimeException.class)
//	public String handerException() {
//		return "error2";
//	}
}
