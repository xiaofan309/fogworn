<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%@include file="/view/resource.jsp" %>
  </head>
	<body class="easyui-layout">
	<!-- Search panel start -->
 	 <div class="ui-search-panel" region="north" style="height: 80px;" title="搜索框" data-options="striped: true,collapsible:false,iconCls:'icon-search',border:false" >  
 	 <form id="searchForm">
 	 	<p class="ui-fields">
 	 	    <label class="ui-label">用户名:</label><input name="email" class="easyui-box ui-text" style="width:100px;">
            <label class="ui-label">姓名: </label><input name="nickName" class="easyui-box ui-text" style="width:100px;">
        </p>
        <a href="#" id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
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
	     	   <div class="ftitle">用户信息</div>    
	           <div class="fitem">  
	               <label>用户名:</label>  
	               <input class="easyui-validatebox" type="text" name="email" data-options="required:true,validType:'length[4,20]'">
	           </div>  
	            
	           <div class="fitem">  
	               <label>姓名:</label>  
	               <input class="easyui-validatebox" type="text" name="nickName" data-options="required:true,validType:'length[1,10]'">
	           </div>
	            <div class="fitem">  
	               <label>状态:</label>
	               <select class="easyui-combobox" name="state" data-options="required:true,panelHeight:'auto',editable:false">
                    	<option value="0" selected="selected">可用</option>
                    	<option value="1">禁用</option>
                   	</select>
	           </div> 
	         </div>
     	</form>
  	 </div> 
  	 
  	 <!-- Edit Password Form -->
     <div id="edit-pwd-win" class="easyui-dialog" buttons="#editPwdbtn" title="修改密码" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:300px;">
     	<form id="pwdForm" class="ui-form" method="post">  
     		 <input class="hidden" name="id">
     		 <div class="ui-edit">
	     	   <div class="ftitle">用户信息</div>    
	           <div class="fitem">  
	               <label>用户名</label>  
	               <input class="easyui-validatebox" type="text" readonly="readonly" name="email" data-options="required:true,validType:'length[4,20]'">
	           </div>  
	           <div class="fitem">  
	              <label>旧密码:</label>  
	              <input id="oldPwd" name="oldPwd" type="password" class="easyui-validatebox"/>
	           </div>
	            <div class="fitem">  
	               <label>新密码:</label>  
	               <input id="newPwd" name="newPwd" type="password" class="easyui-validatebox" data-options="required:true" />
	           </div> 
	           <div class="fitem">  
	               <label>再次输入:</label>  
	              <input id="rpwd" name="rpwd" type="password" class="easyui-validatebox"   required="required" validType="equals['#newPwd']" />
	           </div> 
	         </div>
     	</form>
     	 <div id="editPwdbtn" class="dialog-button">  
            <a href="javascript:void(0)" class="easyui-linkbutton" id="btn-pwd-submit">提交</a>  
            <a href="javascript:void(0)" class="easyui-linkbutton" id="btn-pwd-close">关闭</a>  
        </div>
  	 </div> 
<script type="text/javascript" src="${ctx}/js/ux/sys/sysUser.js"></script>
  </body>
</html>
