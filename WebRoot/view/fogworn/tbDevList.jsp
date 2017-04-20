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
            <label class="ui-label">当前状态: </label><input name="curstate" class="easyui-combobox ui-text" style="width:100px;"
            	data-options="valueField:'id', textField:'text', editable:false, panelHeight:'auto',
            	data:[{id:'在线',text:'在线'},{id:'离线',text:'离线'}]">
            <label class="ui-label">设备类型: </label><input name="devtype" class="easyui-combobox ui-text" style="width:100px;"
            	data-options="valueField:'id',textField:'text',panelHeight:'auto', editable:false,
            	data:[{id:'主控器',text:'主控器'},{id:'LED屏幕',text:'LED屏幕'}]">
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
     <div id="edit-win" class="easyui-dialog" title="编辑" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:300px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" type="text" name="id">
     		 <input class="hidden" name="deleted">
     		 <div class="ui-edit">
	     	   <div class="ftitle">设备信息</div>    
	           <div class="fitem">  
	               <label>设备编号:</label>  
	               <input class="easyui-validatebox" type="text" name="devno" data-options="required:true,validType:'length[1,60]'">
	           </div>  
	           <div class="fitem">  
	               <label>设备名称:</label>  
	               <input class="easyui-validatebox" type="text" name="devnm" data-options="required:true,validType:'length[1,60]'">
	           </div>
	           <div class="fitem">  
	               <label>设备IP:</label>  
	               <input class="easyui-validatebox" type="text" name="devip" data-options="required:true">
	           </div>
	           <div class="fitem">  
	               <label>设备类型:</label>
	               <select class="easyui-combobox" name="devtype" data-options="required:true,panelHeight:'auto',editable:false">
                    	<option value="主控器">主控器</option>
                    	<option value="LED屏幕">LED屏幕</option>
                   	</select>
	           </div> 
	           <%--<input type="hidden" name="curstate" value="">--%>
	         </div>
     	</form>
  	 </div> 
  	 
<script type="text/javascript" src="${ctx}/js/ux/fogworn/tbDev.js"></script>
  </body>
</html>
		