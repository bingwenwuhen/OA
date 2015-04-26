package cn.com.struts2.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.com.domain.Kynamic;
import cn.com.domain.Version;
import cn.com.service.KynamicService;
import cn.com.struts2.action.base.BaseAction;

@Controller("kynamicAction")
@Scope("prototype")
public class KynamicAction extends BaseAction<Kynamic> {
	@Resource(name="kynamicService")
	private KynamicService kynamicService;
	
	public Collection<Kynamic> getKynamicList() {
		return kynamicList;
	}

	private Collection<Kynamic> kynamicList;
	
	private String message;
	private Long kid;
	private Kynamic kynamic;
	private Collection<Version> versionList;
	
	public Collection<Version> getVersionList() {
		return versionList;
	}

	public Kynamic getKynamic() {
		return kynamic;
	}

	public Long getKid() {
		return kid;
	}

	public String getMessage() {
		return message;
	}

	public String showKynamicTree(){
		this.kynamicList=this.kynamicService.getAllKynamic();
		return SUCCESS;
	}
	
	public String saveKynamic(){
		Kynamic kynamic=new Kynamic();
		BeanUtils.copyProperties(this.getModel(), kynamic);
		this.kynamicService.saveKynamic(kynamic);
		this.message="操作成功！";
		this.kid=kynamic.getKid();
		return SUCCESS;
	}
	
	public String isExistName(){
		boolean flag=this.kynamicService.exsitName(this.getModel().getName());
		if(flag){
			this.message="1";
		}else{
			this.message="2";
		}
		return SUCCESS;
	}
	
	public String deleteNode(){
		this.kynamicService.deleteKynamicByID(this.getModel().getKid());
		this.message="操作成功";
		return SUCCESS;
	}	
	
	public String showSiblingNodes(){
		this.kynamicList=this.kynamicService.getSiblingNodes(this.getModel().getKid());
		return SUCCESS;
	}
	
	public String showParentNode(){
		this.kynamic=this.kynamicService.getParentNode(this.getModel().getKid());
		return SUCCESS;
	}
	
	public String updateKynamic(){
		Kynamic kynamic=this.kynamicService.getKynamicById(this.getModel().getKid());
		kynamic.setName(this.getModel().getName());
		this.kynamicService.updateNode(kynamic);
		return SUCCESS;
	}
	
	public String showVersionsByKid(){
		this.versionList=this.kynamicService.getVersionByKid(this.getModel().getKid());
		return SUCCESS;
	}
}	
