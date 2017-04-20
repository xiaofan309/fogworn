<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@include file="/view/resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设备参数</title>
  </head>
<body class="easyui-layout">
	<!-- Search panel start -->
 	 <div class="ui-search-panel" region="north" style="height: 80px;" title="搜索框" data-options="striped: true,collapsible:false,iconCls:'icon-search',border:false" >  
 	 <form id="searchForm">
 	 	<p class="ui-fields">
            <label class="ui-label">操作类型: </label><input name="do_type" class="easyui-combobox ui-text" style="width:100px;"
            	data-options="valueField:'id', textField:'text', editable:false, panelHeight:'auto',
            	data:[{id:'登录',text:'登录'},{id:'退出',text:'退出'},{id:'新增',text:'新增'},{id:'修改',text:'修改'},{id:'删除',text:'删除'}]">
            <label class="ui-label">操作时间：</label><input class="easyui-datetimebox" name="do_time_b"     
        		data-options="editable:false,showSeconds:false" style="width:130px">
            <label class="ui-label">-</label><input class="easyui-datetimebox" name="do_time_e"     
        		data-options="editable:false,showSeconds:false" style="width:130px">
        </p>
        <a href="#" id="btn-search" class="easyui-linkbutton " iconCls="icon-search">查询</a>
        <a href="#" id="btn-reset" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
      </form>  
     </div> 
     <!--  Search panel end -->
     
     <!-- DataList  -->
     <div region="center" border="false" >
     	<table id="data-list"></table>
     </div>
<script type="text/javascript" src="${ctx}/js/ux/sys/sysLog.js"></script>
  </body>
</html>
		