package com.billboard.pagination;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class Pagination {
		protected Log log = LogFactory.getLog(this.getClass());

		// 每頁記錄數 預設為 20
		private int pageSize = 20;

		// 目前頁數
		private int pageNum = 1;

		// 記錄總數，需要從資料庫中查詢出來
		private int recordCount;

		// 頁數，根據 recordCount 與 pageSize 計算出來
		private int pageCount;

		// 本頁的第一條記錄，根據 pageSize 與 pageNum 計算出來
		private int firstResult;

		// 頁面 URL
		private String pageUrl;

		public Pagination(HttpServletRequest request, HttpServletResponse response) {

			try {
				// 從 request 中取目前頁數
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			} catch (Exception e) {
			}
			
			try{
				for (Cookie cookie : request.getCookies()) {
					// 從 cookie 中取自定義的每頁記錄數
					if ("pageSize".equals(cookie.getName())) {
						
							pageSize = Integer.parseInt(cookie.getValue());
						
					}
				}
			}catch(Exception e){}

			try {
				// 自定義的每頁記錄數
				pageSize = Integer.parseInt(request.getParameter("pageSize"));

				// 記錄自定義的每頁記錄數到 Cookie 中
				Cookie cookie = new Cookie("pageSize", Integer.toString(pageSize));
				cookie.setMaxAge(Integer.MAX_VALUE);

				response.addCookie(cookie);

			} catch (Exception e) {
			}

			StringBuffer queryString = new StringBuffer();

			// 獲得所有的 QueryString 參數
			for (Object parameterName : request.getParameterMap().keySet()) {
				String name = (String) parameterName;

				if ("pageNum".equals(name) || "pageSize".equals(name)) {
					continue;
				}

				for (String value : request.getParameterValues(name)) {
					if (queryString.length() > 0) {
						queryString.append("&");
					}

					try {
						queryString.append(name + "="
								+ URLEncoder.encode(value, "UTF-8"));
					} catch (Exception e) {
						queryString.append(name + "=" + value);
					}
				}
			}

			pageUrl = request.getRequestURI() + "?" + queryString.toString();
		}

		private void calculate() {
			pageCount = (recordCount + pageSize - 1) / pageSize;
			firstResult = (pageNum - 1) * pageSize;
		}

		/**
		 * 產生分頁資訊 包括第一頁，上一頁，下一頁，最後一頁等等。
		 * 
		 * @param pageNum
		 *            目前頁數
		 * @param pageCount
		 *            總頁數
		 * @param recordCount
		 *            總記錄數
		 * @param pageUrl
		 *            頁面 URL
		 * @return
		 */
		public String toString() {

			calculate();

			String url = pageUrl.contains("?") ? pageUrl : pageUrl + "?";

			StringBuffer buffer = new StringBuffer();

			/**
			 * <a href="thread-htm-fid-82-page-1.html"
			 * style="font-weight:bold">&laquo;</a><b> 1 </b><a
			 * href="thread-htm-fid-82-page-2.html">2</a><a
			 * href="thread-htm-fid-82-page-3.html">3</a><a
			 * href="thread-htm-fid-82-page-4.html">4</a><a
			 * href="thread-htm-fid-82-page-5.html">5</a> <input type="text"
			 * size="3" onkeydown="javascript: if(event.keyCode==13){
			 * location='thread.php?fid=82&page='+this.value+'';return false;}"> <a
			 * href="thread-htm-fid-82-page-42.html"
			 * style="font-weight:bold">&raquo;</a> Pages: ( 1/42 total )
			 */

			buffer.append("每頁");

			buffer
					.append("<select name=ibm_crl_scm_page_size_select onchange='setPageSize(value); ' >");
			buffer.append(" <option value=20"
					+ (pageSize == 20 ? " selected " : "") + ">20</option>");
			buffer.append(" <option value=40"
					+ (pageSize == 40 ? " selected " : "") + ">40</option>");
			buffer.append(" <option value=60"
					+ (pageSize == 60 ? " selected " : "") + ">60</option>");
			buffer.append(" <option value=80"
					+ (pageSize == 80 ? " selected " : "") + ">80</option>");
			buffer.append(" <option value=100"
					+ (pageSize == 100 ? " selected " : "") + ">100</option>");
			buffer.append("</select>條");

			buffer.append(" 第" + pageNum + "/" + pageCount + "頁  ");

			buffer.append(" 共" + recordCount + "條 ");

			buffer.append(pageCount == 0 || pageNum == 1 ? " 第一頁 " : " <a href='"
					+ url + "&pageNum=1'>第一頁</a> ");

			buffer.append("    ");

			buffer.append(pageCount == 0 || pageNum == 1 ? " 上一頁 " : " <a href='"
					+ url + "&pageNum=" + (pageNum - 1) + "'>上一頁</a> ");

			buffer.append("    ");

			buffer.append(pageCount == 0 || pageNum == pageCount ? " 下一頁 "
					: " <a href='" + url + "&pageNum=" + (pageNum + 1)
							+ "'>下一頁</a> ");

			buffer.append("    ");

			buffer.append(pageCount == 0 || pageNum == pageCount ? " 最後一頁 "
					: " <a href='" + url + "&pageNum=" + pageCount + "'>最後一頁</a> ");

			buffer.append("   到<input type='text' name='ibm_crl_scm_goto_input' "
					+ " style='width:20px; font-size:12px; text-align:center; '>頁");

			buffer.append("<input type='button' "
					+ " name='ibm_crl_scm_goto_button' value='Go' class='button'>");

			buffer.append("<script language='javascript'>");
			buffer.append("function helloweenvsfei_enter(){");
			buffer.append(" if(event.keyCode == 13){");
			buffer.append("     helloweenvsfei_goto();");
			buffer.append("     return false;");
			buffer.append(" }");
			buffer.append(" return true;");
			buffer.append("} ");
			buffer.append("function setPageSize(pageSize){");
			buffer.append(" location='" + url + "&pageSize=' + pageSize;");
			buffer.append("} ");
			buffer.append("function helloweenvsfei_goto(){");
			buffer
					.append(" var numText = document.getElementsByName('ibm_crl_scm_goto_input')[0].value;");
			buffer.append(" var num = parseInt(numText, 10);");
			buffer.append(" if(!num){");
			buffer.append("     alert('頁數必須為數字');   ");
			buffer.append("     return;");
			buffer.append(" }");
			buffer.append(" if(num<1 || num>" + pageCount + "){");
			buffer.append("     alert('頁數需介於 1 與 " + pageCount + " 之間. ');    ");
			buffer.append("     return;");
			buffer.append(" }");
			buffer.append(" location='" + url + "&pageNum=' + num;");
			buffer.append("}");
			buffer
					.append("document.getElementsByName('ibm_crl_scm_goto_input')[0].onkeypress = helloweenvsfei_enter;");
			buffer
					.append("document.getElementsByName('ibm_crl_scm_goto_button')[0].onclick = helloweenvsfei_goto;");
			buffer.append("</script>");

			return buffer.toString();

		}

		public int getPageSize() {
			calculate();
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
			calculate();
		}

		public int getRecordCount() {
			calculate();
			return recordCount;
		}

		public void setRecordCount(int recordCount) {
			this.recordCount = recordCount;
			calculate();
		}

		public int getFirstResult() {
			calculate();
			return firstResult;
		}

		public void setFirstResult(int firstResult) {
			this.firstResult = firstResult;
			calculate();
		}

		public String getPageUrl() {
			return pageUrl + "&pageNum=" + pageNum;
		}

		public void setPageUrl(String pageUrl) {
			this.pageUrl = pageUrl;
		}

		public int getPageNum() {
			return pageNum;
		}

		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}

	

}
