package cn.com.struts2.action;

import com.opensymphony.xwork2.ActionSupport;

public class ForwardAction extends ActionSupport {
	
	public String left(){
		return "left";
	}
	
	public String right(){
		return "right";
	}
	
	public String top(){
		return "top";
	}
	
	public String bottom(){
		return "bottom";
	}
	
	public String kynamic(){
		return "kynamic";
	}
}
