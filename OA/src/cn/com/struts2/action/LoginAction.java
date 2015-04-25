package cn.com.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.domain.User;
import cn.com.service.LoginService;
import cn.com.struts2.action.base.BaseAction;
import cn.com.utils.OAUtils;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction<User> {
	@Resource(name="loginService")
	private LoginService loginService;
	
	public String login(){
		User user=this.loginService.getUserByUAndP(this.getModel().getUsername(), this.getModel().getPassword());
		if(user!=null){					//成功
			OAUtils.putUser2Session(user);
			return "index";
		}else{							//失败
			//转向失败页面
			return null;
		}
	}
	
}
