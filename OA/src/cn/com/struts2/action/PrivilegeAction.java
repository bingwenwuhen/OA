package cn.com.struts2.action;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.domain.Menuitem;
import cn.com.domain.User;
import cn.com.service.MenuitemService;
import cn.com.service.PrivilegeService;
import cn.com.service.UserService;
import cn.com.struts2.action.base.BaseAction;
import cn.com.utils.OAUtils;

@Controller("privilegeAction")
@Scope("prototype")
public class PrivilegeAction extends BaseAction<Menuitem> {
	@Resource(name="privilegeService")
	private PrivilegeService privilegeService;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="menuitemService")
	private MenuitemService menuitemService;
	
	private Long uid;
	
	private String mids;
	
	public String getMids() {
		return mids;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public void setMids(String mids) {
		this.mids = mids;
	}

	private Collection<Menuitem> privilegeList;

	public Collection<Menuitem> getPrivilegeList() {
		return privilegeList;
	}
	
	public String showPrivilege(){
		this.privilegeList=this.privilegeService.getPrivileges();
		return SUCCESS;
	}
	
	/*
	 * 保存某一个用户的权限
	 */
	public String savePrivilege(){
		User user=this.userService.getUserById(this.uid);
		Set<Menuitem> menuitems=this.menuitemService.getMenuitemsByIDS(OAUtils.String2Longs(this.mids));
		user.setMenuitems(menuitems);
		this.userService.updateUser(user);
		return SUCCESS;
	}
}
