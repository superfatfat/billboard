<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯公佈事項</title>
<s:head theme="xhtml" /><!-- 預設 -->
<sx:head debug="false" cache="false" parseContent="false"	compressed="false" extraLocales="zh-tw,en-us,ja,ko" />
</head>
<script type="text/javascript" src="<%=request.getContextPath() %>/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
tinymce.init({
	  selector: 'textarea',
	  height: 200,
	  menubar: false,
	  plugins: [
	    'advlist autolink lists link image charmap print preview anchor textcolor',
	    'searchreplace visualblocks code fullscreen',
	    'insertdatetime media table contextmenu paste code help'
	  ],
	  toolbar: 'insert | undo redo |  formatselect | bold italic backcolor  | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | removeformat | help',
	  content_css: [
	    '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
	    '//www.tinymce.com/css/codepen.min.css']
	});
</script>
<body>
	<s:form action="update" namespace="/billboard" method="post" >
	<table align="center">
	<s:hidden name="billboardVO.no" value="%{billboardVO.no}" />
	<s:textfield name="billboardVO.title" label="標題" value="%{billboardVO.title}"/>
 	<sx:datetimepicker name="billboardVO.releaseDate"   displayFormat="yyyy-MM-dd "  label="發佈日期" value="%{billboardVO.releaseDate}"/>
 	<sx:datetimepicker name="billboardVO.deadlineDate"  displayFormat="yyyy-MM-dd "  label="截止日期" value="%{billboardVO.deadlineDate}"/>
 	<s:textfield name="billboardVO.announcer" label="公佈者" value="%{billboardVO.announcer}" readonly="true"/>
 	<s:textarea name="billboardVO.content" label="公佈內容" value="%{billboardVO.content}"/>
 	<tr><th align="center" colspan="2"><input type="submit" value="確定"> <input type="button" value="返回首頁" onclick="window.location.href='<%=request.getContextPath()%>/index.html'"></th><tr>
 	</table>
</s:form>
</body>
</html>