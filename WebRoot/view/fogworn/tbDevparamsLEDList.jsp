<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@include file="/view/resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>设备参数</title>
    <style type="text/css">
    	.fitem label{width: 75px;}
    	.fitem fieldset{border: 1px solid #d5d5d5}
    	.fitem fieldset legend{font-weight: 600}
    	.left{float: left; width: 205px;}
    	.center{float: left; width: 230px;}
    	.right{float: left; width: 220px;}
    	.left .paramset label{width: 55px;}
    	.left .fitem{height: 27px;}
    	.center .fitem{height: 27px;}
    </style>
    <script type="text/javascript">
    	var roleIds = '${roleIds}';
    	var ADMINROLEID = '1';
    	$(function(){
    		var show = false;
			if(roleIds.length>0){
				var roles = roleIds.split(',');
				for(var i=0; i<roles.length; i++){
					if(roles[i] == ADMINROLEID){
						show = true; break;
					}
				}
			}
			if(show){
				$('#sendTypeFieldset').show();
			}else{
				$('#sendTypeFieldset').hide();
			}
		});
    </script>
  </head>
<body class="easyui-layout">
	<!-- Search panel start -->
 	 <div class="ui-search-panel" region="north" style="height: 80px;" title="搜索框" data-options="striped: true,collapsible:false,iconCls:'icon-search',border:false" >
 	 <form id="searchForm">
 	 	<p class="ui-fields">
 	 	    <label class="ui-label">设备名称:</label><input name="devnm" class="easyui-box ui-text" style="width:100px;">
 	 	    <label class="ui-label">设备编号:</label><input name="devno" class="easyui-box ui-text" style="width:100px;">
            <label class="ui-label">系统状态: </label><input name="devstate" class="easyui-combobox ui-text" style="width:100px;"
            	data-options="valueField:'id', textField:'text', editable:false, panelHeight:'auto',
            	data:[{id:'1',text:'系统开启'},{id:'0',text:'系统关闭'}]">
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
     <div id="edit-win" class="easyui-dialog" title="编辑" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:700px;">
     	<form id="editForm" class="ui-form" method="post">
     		 <input class="hidden" type="text" name="id" id="id">
     		 <input class="hidden" type="text" name="devid">
     		 <div class="ui-edit">
				<div class="ftitle">设备参数</div>  
				<div class="fitem">
					<label>设备名称:</label>
					<input class="easyui-validatebox" type="text" name="devnm" style="width:230px;background-color: #fcfcfc;" readonly="readonly">
					<label>能见度值:</label>
					<input class="easyui-numberbox" type="text" name="visibility" id="visibility" style="width:50px;background-color: #fcfcfc;" 
						readonly="readonly" data-options="min:0,max:100000">米
					<label>限速值:</label>
					<input class="easyui-numberbox" type="text" name="limitspeed" id="limitspeed" style="width:50px;" data-options="min:40,max:120">KM
				</div>
				<div class="fitem">
					<div class="left">
						<fieldset title="参数设置" class='paramset'>
							<legend>参数设置</legend>
							<div class="fitem">
								<label>工作模式</label>
								<input type="radio" value="0" id="worktype1" name="worktype" onclick="worktypeChange(this);">
								<label for="worktype1" style="width: 30px; text-align: left;">雾天</label>
								<input type="radio" value="1" id="worktype2" name="worktype" onclick="worktypeChange(this);">
								<label for="worktype2" style="width: 30px; text-align: left;">夜间</label>
							</div>
							<div class="fitem">
								<label>尾迹个数</label>
								<input name="guidlights" id="guidlights" class="easyui-combobox ui-text" style="width:108px;"
								 	data-options="required:true, valueField:'id', textField:'text', editable:false, panelHeight:'auto',
								 	data:[{id:'3',text:'3组（60米）'},{id:'4',text:'4组（80米）'},{id:'5',text:'5组（100米）'}],value:3">
							</div>
							<div class="fitem">
								<label>闪烁频率</label>
								<input name="flickerfrequency" id="flickerfrequency" class="easyui-combobox ui-text" style="width:108px;"
								 	data-options="required:true, valueField:'id', textField:'text', editable:false, panelHeight:'auto',
								 	data:[{id:'1',text:'1HZ'},{id:'2',text:'2HZ'},{id:'3',text:'0.5HZ'}],
								 	value:2">
							</div>
							<div class="fitem">
								<label>亮度等级</label>
								<input name="luminance" id="luminance" class="easyui-combobox ui-text" style="width:108px;"
								 	data-options="required:true, valueField:'id', textField:'text', editable:false, panelHeight:'auto',
								 	data:[{id:'1',text:'1级（最暗）'},{id:'2',text:'2级'},{id:'3',text:'3级'}
								 		,{id:'4',text:'4级'},{id:'5',text:'5级'},{id:'6',text:'6级'}
								 		,{id:'7',text:'7级'},{id:'8',text:'8级'},{id:'9',text:'9级（最亮）'}],value:1">
							</div>
							<div class="fitem">
								<label>尾迹开启</label>
								<input name="lightopen" id="lightopen" class="easyui-combobox ui-text" style="width:108px;"
								 	data-options="required:true, valueField:'id', textField:'text', editable:false, panelHeight:'auto',
								 	data:[{id:'1',text:'开'},{id:'0',text:'关'}],value:1">
							</div>
							<div class="fitem">
								<label>尾迹时间</label>
								<input name="lighttime" id="lighttime" class="easyui-combobox" type="text" style="width:108px;" 
									data-options="required:true, valueField:'id', textField:'text', editable:false, panelHeight:106,value:1,
								 	data:[{id:'1',text:'1'},{id:'2',text:'2'},{id:'3',text:'3'},{id:'4',text:'4'},{id:'5',text:'5'},{id:'6',text:'6'}
								 	,{id:'7',text:'7'},{id:'8',text:'8'},{id:'9',text:'9'},{id:'10',text:'10'},{id:'11',text:'11'},{id:'12',text:'12'}
								 	,{id:'13',text:'13'},{id:'14',text:'14'},{id:'15',text:'15'},{id:'16',text:'16'},{id:'17',text:'17'},{id:'18',text:'18'}
								 	,{id:'19',text:'19'},{id:'20',text:'20'},{id:'21',text:'21'},{id:'22',text:'22'},{id:'23',text:'23'},{id:'24',text:'24'}
								 	,{id:'25',text:'25'},{id:'26',text:'26'},{id:'27',text:'27'},{id:'28',text:'28'},{id:'29',text:'29'},{id:'30',text:'30'}]">
							</div>
							<div class="fitem" style="padding-top: 6px; text-align: right; width: 155px;">
								<a href="#" id="btn-measure" class="easyui-linkbutton" onclick="sendParam('params')" style="margin-left: 38px;">设置</a>
							</div>
						</fieldset>
					</div>
					<div class="center">
						<fieldset title="夜间自动开启">
							<legend>夜间自动开启</legend>
							<input type="radio" value="1" id="nightenable1" name="nightenable">
								<label for="nightenable1" style="width: 45px; text-align: left;">开启</label>
							<input type="radio" value="0" id="nightenable0" name="nightenable">
								<label for="nightenable0" style="width: 45px; text-align: left;">关闭</label>
							<a href="#" id="btn-nightenable" class="easyui-linkbutton" onclick="sendParam('nightenable')">设置</a>
						</fieldset>
						<fieldset title="能见度测量方式" style="margin-top: 30px;">
							<legend>能见度测量方式</legend>
							<input type="radio" value="1" id="measuretype1" name="measuretype" onclick="measuretypeSelect(this);">
								<label for="measuretype1" style="width: 55px; text-align: left;">瞬时值</label><br/>
							<input type="radio" value="2" id="measuretype2" name="measuretype" onclick="measuretypeSelect(this);">
								<label for="measuretype2" style="width: 55px; text-align: left;">平均值</label><br/>
							<input type="radio" value="3" id="measuretype3" name="measuretype" onclick="measuretypeSelect(this);">
								<label for="measuretype3" style="width: 55px; text-align: left;">固定值</label>
								<input name="measurenum" id="measurenum" class="easyui-combobox ui-text" style="width:110px;"
								 	data-options="required:true, valueField:'id', textField:'text', editable:false, panelHeight:'auto',
								 	data:[{id:'3',text:'3（LED限速40）'},{id:'4',text:'4（LED限速60）'},{id:'5',text:'5（LED限速80）'}
								 		,{id:'6',text:'6（LED限速120）'}]">
							<div style="width: 95%; margin-top: 5px; text-align: right;">
								<a href="#" id="btn-measure" class="easyui-linkbutton" onclick="sendParam('measuretype')">设置</a>
							</div>
						</fieldset>
						<div style="text-align: center; margin-top: 20px;">
							<a href="#" id="btn-turnon" class="easyui-linkbutton"  onclick="sendParam('devstate',1,1)">系统开启</a>
							<a href="#" id="btn-turnoff" class="easyui-linkbutton"  onclick="sendParam('devstate',1,0)">系统关闭</a>
						</div>
					</div>
					<div class="right">
						<fieldset title="故障信息">
							<legend>故障信息</legend>
							<div class="fitem">
								<input type="hidden" id="logIds">
								<div style="height: 138px; border: 1px solid #d1d1d1; overflow-y: auto;" id="logContent"></div>
							</div>
							<div class="fitem" style="text-align: right;">
								<a href="#" id="btn-clearlog" class="easyui-linkbutton"  onclick="clearLogs()">清除</a>
							</div>
						</fieldset>
						<fieldset title="发送方式设置" id="sendTypeFieldset">
							<legend>发送方式设置</legend>
							<input type="radio" value="0" id="sendtype1" name="sendtype">
								<label for="sendtype1" style="width: 40px; text-align: left;">传递式</label>
							<input type="radio" value="1" id="sendtype2" name="sendtype">
								<label for="sendtype2" style="width: 40px; text-align: left;">覆盖式</label>
							<a href="#" id="btn-sendtype" class="easyui-linkbutton" onclick="sendParam('sendtype')">设置</a>
						</fieldset>
					</div>
					<div style="clear: both;"></div>
				</div>
	         </div>
     	</form>
  	 </div> 
  	 
<script type="text/javascript" src="${ctx}/js/ux/fogworn/tbDevparams.js"></script>
  </body>
</html>
		