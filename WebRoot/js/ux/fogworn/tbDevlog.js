$package('YiYa.tbDevlog');
YiYa.tbDevlog = function(){
	var _box = null;
	var _this = {
		editForm:function(){
			return $("#editForm");
		},
		editWin:function(){
			return $("#edit-win");
		},
		initBtn:function(){
			_this.editWin().find("#btn-close").click(function(){	
				_this.editWin().dialog('close');
			});
		},
		config:{
  			dataGrid:{
  				title:'报警日志列表',
	   			url:'dataList.do',
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'devno',title:'设备编号',width:110,sortable:true},
						{field:'devnm',title:'设备名称',width:140,sortable:true},
						{field:'pointid',title:'产品ID',width:110},
						{field:'commtime',title:'通信时间',width:120},
						{field:'devonlinetime',title:'设备上线时间',width:120},
						{field:'notes',title:'日志说明',width:300,formatter:function(value,row,index){
							 return '<div title='+value+'>'+value+'</div>';
							}
						}
				]],
				toolbar:[
					{id:'btnview',text:'查看',btnType:'view',iconCls:'icon-search',handler:function(){
							var record = _box.utils.getCheckedRows();
							if (_box.utils.checkSelectOne(record)){
								YiYa.progress();
								var data ={id:record[0].id};
								YiYa.getById('getId.do',data,function(result){
									YiYa.closeProgress();
									_this.editForm().form('load',result.data);
									_this.editWin().window('open'); 
								});
							}
						}}
				]
			}
		},
		init:function(){
			_this.initBtn();
			_box = new YDataGrid(_this.config); 
			_box.init();
		}
	}
	return _this;
}();

$(function(){
	YiYa.tbDevlog.init();
});		