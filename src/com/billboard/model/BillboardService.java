package com.billboard.model;

import java.util.List;

public class BillboardService {

	private BillboardDAO_interface dao;

	public BillboardService() {
		dao = new BillboardDAO();
	}



	public void addBillboard(BillboardVO BillboardVO) {
		dao.insert(BillboardVO);
	}

	public BillboardVO updateBillboard(Integer no, String title, String announcer,
			java.sql.Date releaseDate, java.sql.Date deadlineDate, String content) {

		BillboardVO BillboardVO = new BillboardVO();

		BillboardVO.setNo(no);
		BillboardVO.setTitle(title);
		BillboardVO.setAnnouncer(announcer);
		BillboardVO.setReleaseDate(releaseDate);
		BillboardVO.setDeadlineDate(deadlineDate);
		BillboardVO.setContent(content);
		dao.update(BillboardVO);

		return dao.findByPrimaryKey(no);
	}

	public BillboardVO updateBillboard(BillboardVO BillboardVO) {
		dao.update(BillboardVO);
		return dao.findByPrimaryKey(BillboardVO.getNo());
	}

	public void deleteBillboard(Integer Billboardno) {
		dao.delete(Billboardno);
	}

	public BillboardVO getOneBillboard(Integer no) {
		return dao.findByPrimaryKey(no);
	}

	public List<BillboardVO> getAll() {
		return dao.getAll();
	}
	
	public List<BillboardVO> getAll(int firstResult, int maxResults){
		return dao.getAll(firstResult, maxResults);
	}
}
