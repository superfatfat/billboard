<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style >
table.TB_COLLAPSE {
  width:100%;
  border-collapse:collapse;
}
table.TB_COLLAPSE caption {
  padding:10px;
  font-size:24px;
  background-color:#f3f6f9;
}
table.TB_COLLAPSE thead th {
  padding:5px 0px;
  color:#fff;
  background-color:#915957;
}
table.TB_COLLAPSE tbody td {
  padding:5px 0px;
  color:#555;
  text-align:center;
  background-color:#fff;
  border-bottom:1px solid #915957;
}
table.TB_COLLAPSE tfoot td {
  padding:5px 0px;
  text-align:center;
  background-color:#d6d6a5;
}
 table.TB_COLLAPSE {
  width:100%;
  border-collapse:separate;
} 
</style>
<title>瀏覽公告事項</title>
</head>
<body>
<s:actionerror/>

<table id="billboard" class="TB_COLLAPSE">
<thead>
 <tr>
  <th>標題</th>
  <th>發佈日期</th>
  <th>截止日期</th>
  <th>修改</th>
  <th>刪除</th>
 </tr>
 </thead>
 <s:iterator value="billboardList">
  <tr>
   <td><s:property value="title" /></td>
   <td><s:property value="releaseDate" /></td>
   <td><s:property value="deadlineDate" /></td>
   <td><a href="billboard/findByNo.action?no=<s:property value="no" />">update</a></td>
   <td><a href="billboard/delete.action?no=<s:property value="no" />">delete</a></td>
  </tr>
 </s:iterator>
</table>
	<div >
		${ pagination }
	</div>
	<div align="center">
	<button  onclick="window.location.href='<%=request.getContextPath()%>/billboard/add.jsp'">新增</button>
	</div>
</body>
</html>