<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@include file="/view/resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设备管理</title>
  </head>
<body class="easyui-layout">
	<!-- Search panel start -->
 	 <div class="ui-search-panel" region="north" style="height: 80px;" title="搜索框" data-options="striped: true,collapsible:false,iconCls:'icon-search',border:false" >  
 	 <form id="searchForm">
 	 	<p class="ui-fields">
 	 	    <label class="ui-label">设备编号:</label><input name="devno" class="easyui-box ui-text" style="width:100px;">
            <label class="ui-label">设备名称: </label><input name="devnm" class="easyui-box ui-text" style="width:100px;">
            <label class="ui-label">产品ID: </label><input name="pointid" class="easyui-box ui-text" style="width:100px;">
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

     <!-- Edit Form -->
     <div id="edit-win" class="easyui-dialog" title="查看" buttons="#editBtn" data-options="closed:true,iconCls:'icon-search',modal:true" style="width:420px;height:380px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" type="text" name="id">
     		 <div class="ui-edit">
	     	   <div class="ftitle">报警日志</div>    
	           <div class="fitem">
	               <label>设备编号:</label>  
	               <input class="easyui-validatebox" type="text" name="devno" readonly="readonly">
	           </div>  
	           <div class="fitem">  
	               <label>设备名称:</label>  
	               <input class="easyui-validatebox" type="text" name="devnm" readonly="readonly">
	           </div>
	           <div class="fitem">  
	               <label>产品ID:</label>  
	               <input class="easyui-validatebox" type="text" name="pointid" readonly="readonly">
	           </div>
	           <div class="fitem">  
	               <label>通信时间:</label>
	               <input class="easyui-validatebox" type="text" name="commtime" readonly="readonly">
	           </div>
	           <div class="fitem">  
	               <label>设备上线时间:</label>
	               <input class="easyui-validatebox" type="text" name="devonlinetime" readonly="readonly">
	           </div>
	           <div class="fitem">  
	               <label>备注:</label>
	               <textarea rows="4" style="resize:none;width:220px;" name="notes" readonly="readonly"></textarea>
	           </div>
	         </div>
     	</form>
     	<div id="editBtn" class="dialog-button">
            <a href="javascript:void(0)" class="easyui-linkbutton" id="btn-close">关闭</a>  
        </div>
  	 </div> 
  	 
<script type="text/javascript" src="${ctx}/js/ux/fogworn/tbDevlog.js"></script>
  </body>
</html>
		