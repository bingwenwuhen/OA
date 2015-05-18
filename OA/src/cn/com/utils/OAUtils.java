package cn.com.utils;

import org.apache.struts2.ServletActionContext;

import cn.com.domain.User;

public class OAUtils {
	public static Long[] String2Longs(String ids){
		String[] s=ids.split(",");
		Long[] aa=new Long[s.length];
		int index=0;
		for(String str:s){
			aa[index]=Long.parseLong(str);
			index++;
		}
		return aa;
	}
	
	public  static User fromSession(){
		return (User) ServletActionContext.getRequest().getSession().getAttribute("user");
	}
	
	public static void putUser2Session(User user){
		ServletActionContext.getRequest().getSession().setAttribute("user", user);
	}
}
