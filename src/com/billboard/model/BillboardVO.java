package com.billboard.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class BillboardVO implements java.io.Serializable{
	private Integer no;
	private String title;
	private String announcer;
	private Date releaseDate;
	private Date deadlineDate;
	private String content;
	

	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnnouncer() {
		return announcer;
	}
	public void setAnnouncer(String announcer) {
		this.announcer = announcer;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getDeadlineDate() {
		return deadlineDate;
	}
	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
