$package('YiYa.tbDevparams');
YiYa.tbDevparams = function(){
	var _box = null;
	var getLogs = function(result){
		//先设置界面
		if(result.data.measuretype == 3){
			$('#measurenum').combobox({
				disabled: false,
				required: true
			});
		}else{
			$('#measurenum').combobox({
				disabled: true,
				required: false
			});
			$('#measurenum').combobox('clear');
		}
		setLimitspeed();
		//获取故障信息
		$.ajax({
	      type:"POST",
	      url: ctx+'/tbDevparams/getLogs.do',
	      dataType:"json",
	      data: {
			devid: result.data.devid
	      },
	      success: function(data){
			if(data.success){
				$('#logContent').html(data.data.logContent);
				$('#logIds').val(data.data.logIds);
				if(data.data.logIds){
					$("#btn-clearlog").linkbutton('enable');
				}else{
					$("#btn-clearlog").linkbutton('disable');
				}
				$('#visibility').focus();
			}else{
				YiYa.alert('提示',data.msg,'error');
			}
	      },
	      error:function(XMLHttpRequest, textStatus, errorThrown){
	          YiYa.alert('提示','获取故障信息失败','error');
	      }
	  });
	}
	var _this = {
		config:{
			event:{
				edit:function(){
					_box.handler.edit(getLogs);
				}
			},
  			dataGrid:{
  				title:'设备参数列表',
	   			url:'dataList.do',
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'devnm',title:'设备名称',width:120,sortable:true},
						{field:'devno',title:'设备编号',width:120,sortable:true},
						{field:'devstate',title:'系统状态',width:110,sortable:true,align:'center',formatter:function(value,row,index){
								if(value==null || value=='null')value='';
								if(value=='1'){
									return '系统开启';
								}else if(value=='0'){
									return '系统关闭';
								}else{
									return '';
								}
							}
						},
						{field:'worktype',title:'工作模式',width:80,sortable:true,align:'center'
							,formatter:function(value,row,index){
								if(value==null || value=='null')value='';
								if(value=='0'){
									return '雾天';
								}else if(value=='1'){
									return '夜间';
								}else{
									return '';
								}
							}
						},
						{field:'visibility',title:'能见度值',width:90,align:'right'},
						{field:'guidlights',title:'尾迹个数',width:90,align:'right'
							,formatter:function(value,row,index){
								if(value==null || value=='null')value='';
								if(value=='3'){
									return '3组（60米）';
								}else if(value=='4'){
									return '4组（80米）';
								}else if(value=='5'){
									return '5组（100米）';
								}else{
									return '';
								}
							}
						},
						{field:'luminance',title:'亮度等级',width:100,align:'right'
							,formatter:function(value,row,index){
								if(value==null || value=='null'){
									return '';
								}
								if(value=='1'){
									return '1HZ（1秒闪1次）';
								}else if(value=='2'){
									return '2HZ（1秒闪2次）';
								}else if(value=='3'){
									return '0.5HZ（2秒闪1次）';
								}else{
									return value;
								}
							}
						},
						{field:'flickerfrequency',title:'闪烁频率',width:110,align:'right'
							,formatter:function(value,row,index){
								if(value==null || value=='null'){
									return '';
								}
								return value+'HZ';
							}
						},
						/*{field:'luminancethreshold',title:'夜间开启照度阈值',width:110,align:'right'
							,formatter:function(value,row,index){
								if(value==null || value=='null'){
									return '';
								}
								return value+'级';
							}
						},*/
						{field:'guidlights',title:'尾迹个数',width:90,align:'right'}
				]],
				toolbar:[
					//{id:'btnadd',text:'新增',btnType:'add'},
					{id:'btnedit',text:'修改参数',btnType:'edit'}/*,
					{id:'btndelete',text:'删除',btnType:'remove'}*/
				]
			}
		},
		init:function(){
			_box = new YDataGrid(_this.config); 
			_box.init();
		}
	};
	return _this;
}();

$(function(){
	YiYa.tbDevparams.init();
});
function measuretypeSelect(obj){
	var type = obj.id.substr(-1,1);
	if(type == 3){
		$('#measurenum').combobox({
			disabled: false,
			required: true
		});
	}else{
		$('#measurenum').combobox({
			disabled: true,
			required: false
		});
		$('#measurenum').combobox('clear');
	}
}
function clearLogs(){
	var devid = $('#editForm input[name=devid]').val();
	var logIds = $('#logIds').val();
	if(logIds==''){
		return;
	}
	$.ajax({
      type:"POST",
      url: ctx+'/tbDevparams/clearLogs.do',
      dataType:"json",
      data: {
		devid: devid,
		logIds: logIds
      },
      success: function(data){
		if(data.success){
			$('#logContent').html('');
			$('#logIds').val('');
		}else{
			YiYa.alert('提示',data.msg,'error');
		}
      },
      error:function(XMLHttpRequest, textStatus, errorThrown){
          YiYa.alert('提示','操作失败','error');
      }
  });
}
function setLimitspeed(){
	var v = $('#visibility').numberbox('getValue');
	if(v > 500){
		$('#limitspeed').numberbox('setValue',120);
	}else if(v>=300){
		$('#limitspeed').numberbox('setValue',80);
	}else if(v>=200){
		$('#limitspeed').numberbox('setValue',60);
	}else {
		$('#limitspeed').numberbox('setValue',40);
	}
}
/**
 * 
 * @param {Object} name
 * @param {Object} type 当为1表示传递式开关机，2表示覆盖式开关机，都需要额外获取尾迹个数、闪烁频率、亮度等级、尾迹开启、尾迹时间
 * @param {Object} state 表示开关机状态1为开机，0为关机
 * @return {TypeName} 
 */
function sendParam(name, type, state){
	var data = {
		paramName: name,
		state: state
	};
	//开关机
	if(name=='devstate'){
		var value = $('#editForm input[name=measuretype]:checked').val();
		if(value != 3 || $('#measurenum').combobox('getValue')==''){
			YiYa.alert('提示','请将能见度测量方式选择为固定值再进行手动操作','info');
			return false;
		}
		if(!checksendtype()){
			return;
		}
		if(!checknightenable()){
			return;
		}
		if(!checkparams()){
			return;
		}
	}else if(name=='sendtype'){
		if(!checksendtype()){
			return;
		}
	}else if(name=='measuretype'){
		if(!checkmeasuretype()){
			return;
		}
	}else if(name=='nightenable'){
		if(!checknightenable()){
			return;
		}
	}else if(name=='params'){
		if(!checkparams()){
			return;
		}
	}
	var paramArray = $("#editForm").serializeArray();
	$.each(paramArray, function(i, param){
		data[param.name] = param.value;
	});
	$.ajax({
      type:"POST",
      url: ctx+'/tbDevparams/sendParam.do',
      dataType:"json",
      data: data,
      success: function(data){
		if(data.success){
			YiYa.alert('提示',data.msg,'success');
		}else{
			YiYa.alert('提示',data.msg,'success');
		}
      },
      error:function(XMLHttpRequest, textStatus, errorThrown){
          YiYa.alert('提示','操作失败','error');
      }
  });
}
function checkmeasuretype(){
	var value = $('#editForm input[name=measuretype]:checked').val();
	if(value==null || value==''){
		YiYa.alert('提示','请先选择能见度测量方式','info');
		return false;
	}
	if(value == 3 && $('#measurenum').combobox('getValue')==''){
		YiYa.alert('提示','请先设置能见度测量方式的固定值','info');
		return false;
	}
	return true;
}
function checksendtype(){
	var type = $('#editForm input[name=sendtype]:checked').val();
	if(type==null || type=='' || type == 'undefined'){
		YiYa.alert('提示','请先选择发送方式','info');
		return false;
	}
	return true;
}
function checknightenable(){
	var type = $('#editForm input[name=nightenable]:checked').val();
	if(type==null || type=='' || type == 'undefined'){
		YiYa.alert('提示','请先选择夜间自动开启','info');
		return false;
	}
	return true;
}
function checkparams(){
	var type,v;
	type = $('#editForm input[name=worktype]:checked').val();
	if(type==null || type=='' || type == 'undefined'){
		YiYa.alert('提示','请先选择工作模式','info');
		return false;
	}
	v = $('#guidlights').combobox('getValue');
	if(v==''||v==null){
		YiYa.alert('提示','请先选择尾迹个数','info');
		return false;
	}
	v = $('#flickerfrequency').combobox('getValue');
	if(v==''||v==null){
		YiYa.alert('提示','请先选择闪烁频率','info');
		return false;
	}
	v = $('#luminance').combobox('getValue');
	if(v==''||v==null){
		YiYa.alert('提示','请先选择亮度等级','info');
		return false;
	}
	v = $('#lightopen').combobox('getValue');
	if(v==''||v==null){
		YiYa.alert('提示','请先选择尾迹是否开启','info');
		return false;
	}
	v = $('#lighttime').combobox('getValue');
	if(v==''||v==null){
		YiYa.alert('提示','请先设置尾迹时间','info');
		return false;
	}
	return true;
}

function worktypeChange(obj){
	if(obj.checked){
		$.ajax({
	      type:"POST",
	      url: ctx+'/tbDevparams/getParamByWorktype.do',
	      dataType:"json",
	      data: {
			id:$('#id').val(),
			worktype:obj.value
	      },
	      success: function(data){
			if(data && data.id){
				$('#editForm').form('load',data);
			}
	      },
	      error:function(XMLHttpRequest, textStatus, errorThrown){
	          YiYa.alert('提示','获取信息失败','error');
	      }
	  });
	}
}