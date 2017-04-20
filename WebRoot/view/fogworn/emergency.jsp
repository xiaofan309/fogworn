<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>应急联动</title>
    <script type="text/javascript">
	var ctx = '${ctx}';
	</script>
  </head>
<body>
	<!-- Search panel start -->
 	 <%--<div class="ui-search-panel" region="north" style="height: 80px;" title="应急联动" data-options="striped: true,collapsible:false,iconCls:'icon-search',border:false" >  
 	 
     </div>--%> 
     <!--  Search panel end -->
     
     <!-- DataList  -->
     <%--<div region="center" border="false" >
     	<table id="data-list"></table>
     </div>--%>
     
    <div style="margin: 50px;">
    	<h3>交警电话：112</h3>
    	<h3>医院电话：120</h3>
    	<h3>公安电话：110</h3>
    </div>
  </body>
</html>
		