package cn.com.domain;

import java.io.Serializable;
import java.util.Date;

public class Version implements Serializable {
	private Long vid;
	private Long version;			//版本号
	private Date updatetime;
	private String title;
	private String content;	
	
	private Kynamic kynamic;

	public Long getVid() {
		return vid;
	}

	public void setVid(Long vid) {
		this.vid = vid;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Kynamic getKynamic() {
		return kynamic;
	}

	public void setKynamic(Kynamic kynamic) {
		this.kynamic = kynamic;
	}
}
