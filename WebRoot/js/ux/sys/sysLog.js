$package('YiYa.sysLog');
YiYa.sysLog = function(){
	var _box = null;
	var _this = {
		config:{
			dontHasToolbar:true,
  			dataGrid:{
  				title:'系统日志列表',
	   			url:'dataList.do',
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'table_name',title:'操作表',width:110,sortable:true},
						{field:'primary_id',title:'被操作记录ID',width:140},
						{field:'do_user_name',title:'操作人姓名',width:110},
						{field:'description',title:'描述',width:200,formatter:function(value,row,index){
							 return '<div title='+value+'>'+value+'</div>';
							}
						},
						{field:'do_type',title:'操作类型',width:80,align:'center'},
						{field:'do_time',title:'操作时间',width:130,sortable:true,align:'center'}
				]]
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
	YiYa.sysLog.init();
});		