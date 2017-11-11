package com.billboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.billboard.model.BillboardService;
import com.billboard.model.BillboardVO;
import com.billboard.pagination.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class BillboardAction extends ActionSupport{
	private Integer no;
	private BillboardVO billboardVO;
	private List<BillboardVO> billboardList;

	public void setNo(Integer no){
		this.no = no;
	}
	
	public Integer getNo(){
		return this.no;
	}
	
	public BillboardVO getBillboardVO() {
		return billboardVO;
	}

	public void setBillboardVO(BillboardVO billboardVO) {
		this.billboardVO = billboardVO;
	}
	
	public void setBillboardList(List<BillboardVO> billboardList){
		this.billboardList = billboardList;
	}
	
	public List<BillboardVO> getBillboardList(){
		return this.billboardList;
	}
	
	public String add(){
		System.out.println(billboardVO.getAnnouncer());
		BillboardService billboardSvc = new BillboardService();
		billboardSvc.addBillboard(billboardVO);
		System.out.println("資料庫 insert 成功");
		return "success";
	}
	public String update(){
		BillboardService billboardSvc = new BillboardService();
		BillboardVO billboardVO2 = billboardSvc.updateBillboard(billboardVO);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("billboardVO", billboardVO2);
		System.out.println("資料庫 update 成功");
		return "success";
	}
	public String delete(){
		BillboardService billboardSvc = new BillboardService();
		billboardSvc.deleteBillboard(getNo());
		System.out.println("資料庫 delete 成功");
		return "success";
	}
	public String list(){
		BillboardService billboardSvc = new BillboardService();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		Pagination pagination = new Pagination(request,response);
		pagination.setRecordCount(billboardSvc.getAll().size());

		this.billboardList = billboardSvc.getAll(pagination.getFirstResult(), pagination.getPageSize());
		request.setAttribute("pagination", pagination);
		System.out.println("資料庫 getAll 成功");
		return "success";
	}
	
	public String findByNo(){
		BillboardService billboardSvc = new BillboardService();
		this.billboardVO = billboardSvc.getOneBillboard(getNo());
		return "success";
	}
}
