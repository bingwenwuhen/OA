package cn.com.struts2.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.domain.Menuitem;
import cn.com.service.PrivilegeService;
import cn.com.struts2.action.base.BaseAction;

@Controller("privilegeAction")
@Scope("prototype")
public class PrivilegeAction extends BaseAction<Menuitem> {
	@Resource(name="privilegeService")
	private PrivilegeService privilegeService;
	
	private Collection<Menuitem> privilegeList;

	public Collection<Menuitem> getPrivilegeList() {
		return privilegeList;
	}
	
	public String showPrivilege(){
		this.privilegeList=this.privilegeService.getPrivileges();
		return SUCCESS;
	}
}
