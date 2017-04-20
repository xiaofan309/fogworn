$package('YiYa.tbDev');
YiYa.tbDev = function(){
	var _box = null;
	var _this = {
		config:{
  			dataGrid:{
  				title:'设备列表',
	   			url:'dataList.do',
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'devno',title:'设备编号',width:110,sortable:true},
						{field:'devnm',title:'设备名称',width:140,sortable:true},
						{field:'devip',title:'设备IP',width:110},
						{field:'onlinetime',title:'上线时间',width:120},
						{field:'offlinetime',title:'离线时间',width:120},
						{field:'curstate',title:'当前状态',width:80,sortable:true,align:'center'},
						{field:'devtype',title:'设备类型',width:80,sortable:true,align:'center'}
				]],
				toolbar:[
					{id:'btnadd',text:'新增',btnType:'add'},
					{id:'btnedit',text:'修改',btnType:'edit'},
					{id:'btndelete',text:'删除',btnType:'remove'}
				]
			}
		},
		init:function(){
			_box = new YDataGrid(_this.config); 
			_box.init();
		}
	}
	return _this;
}();

$(function(){
	YiYa.tbDev.init();
});		