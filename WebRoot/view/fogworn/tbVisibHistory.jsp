<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ page import="com.base.utils.DateUtil"%>
<%@ page import="java.util.Date"%>
<%@include file="/view/resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>浓度值历史数据</title>
    <script src="${ctx}/js/commons/echarts.common.min.js"></script>
    <script type="text/javascript">
    	var eDt = '<%= DateUtil.getNowPlusTime()%>';
    	var bDt = '<%=DateUtil.getPlusTime(DateUtil.increaseDay(new Date(), -1))%>';
    </script>
  </head>
<body class="easyui-layout">
	<!-- Search panel start -->
 	 <div class="ui-search-panel" region="north" style="height: 80px;" title="搜索框" data-options="striped: true,collapsible:false,iconCls:'icon-search',border:false" >  
 	 <form id="searchForm">
 	 	<p class="ui-fields">
            <label class="ui-label">设备: </label><input id="devid" class="easyui-combobox ui-text" style="width:200px;"
            	data-options="valueField:'id', textField:'devnm', editable:false, panelWidth:300, panelHeight:'auto',
            		url:ctx+'/tbVisibHistory/devList.do',
            		formatter:function(row){return row.devnm+' - '+row.devtype+'('+row.curstate+')';},
            		onSelect:function(rec){getData();}">
            <label class="ui-label">时间: </label><input class="easyui-datetimebox" id="beginDt" style="width:140px;"
            	data-options="showSeconds:true,editable:false">
            <label class="ui-label">至 </label><input class="easyui-datetimebox" id="endDt" style="width:140px;"
            	data-options="showSeconds:true,editable:false">
        </p>
        <a href="#" id="btn-search" class="easyui-linkbutton " iconCls="icon-search">查看</a>
      </form>  
     </div> 
     
     <div region="center" border="false" >
     	<div id="main" style="width: 960px; height: 380px; margin-top: 10px;"></div>
     </div>
  	 
<script type="text/javascript" src="${ctx}/js/ux/fogworn/tbVisibHistory.js"></script>
  </body>
</html>
		