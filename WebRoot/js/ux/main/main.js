$package('YiYa.main');

YiYa.main = function(){
	var grid = null;
	return {
		treeSelect:function(){
			var _this = $(this);
			var title=_this.text();
			var url=_this.attr('href');
			YiYa.main.addTab(title,url);
			return false;
		},
		treeInit:function(){
			var  $items =  $('#tree-box').find(".menu-item");
			$items.bind('click',this.treeSelect);
		},
		addTab:function(_title,_url){
			if(_url == ctx){
				YiYa.alert('提示','功能暂未开放。','info');
				return;
			}
			var boxId = '#tab-box';
			if($(boxId).tabs('exists',_title) ){
				var tab = $(boxId).tabs('getTab',_title);
				var index = $(boxId).tabs('getTabIndex',tab);
				$(boxId).tabs('select',index);
				if(tab && tab.find('iframe').length > 0){  
					 var _refresh_ifram = tab.find('iframe')[0];  
				     _refresh_ifram.contentWindow.location.href=_url;  
			    } 
			}else{		
				var _content ="<iframe scrolling='auto' frameborder='0' src='"+_url+"' style='width:100%; height:100%'></iframe>";
				$(boxId).tabs('add',{
					    title:_title,
					    content:_content,
					    closable:true});
				
			}
		},
		menuHover:function(){
			//菜单鼠标进入效果
			$('.menu-item').hover(
				function () {
					$(this).stop().animate({ paddingLeft: "25px" }, 200,function(){
						$(this).addClass("orange");
					});
				}, 
				function () {
					$(this).stop().animate({ paddingLeft: "15px" },function(){
						$(this).removeClass("orange");
					});
				}
			);
		},
		modifyPwd:function(){
			var pwdForm = $("#pwdForm");
			if(pwdForm.form('validate')){
				var parentId =$('#search_parentId').val();
				$("#edit_parentId").val(parentId)
				YiYa.saveForm(pwdForm,function(data){
					$('#modify-pwd-win').dialog('close');
				    pwdForm.resetForm();
				});
			 }
		},
		logout:function(){
			YiYa.confirm('确认','确定退出系统？',function(r){
				if(r){
					window.location="logout.shtml";
				}
			})
		},
		config:{
			dontHasToolbar:true,
			dataGrid:{
				title:'设备参数列表',
		   		url:ctx+'/tbDevparams/dataList.do',
		   		columns:[[
					{field:'devnm',title:'设备名称',width:110,sortable:true
						,formatter:function(value,row,index){
							return '<span title="'+value+'">'+value+'</span>';
						}
					},
					{field:'devno',title:'设备编号',width:90,sortable:true},
					{field:'devip',title:'设备IP',width:100},
					{field:'onlinetime',title:'上线时间',width:120,align:'center'},
					{field:'offlinetime',title:'离线时间',width:120,align:'center'},
					{field:'curstate',title:'当前状态',width:50,sortable:true,align:'center'},
					{field:'devtype',title:'设备类型',width:60,sortable:true,align:'center'},
					{field:'devstate',title:'系统状态',width:55,sortable:true,align:'center',formatter:function(value,row,index){
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
					/*{field:'nightenable',title:'夜间开启',width:40,sortable:true,align:'center'},*/
					{field:'worktype',title:'工作模式',width:60,sortable:true,align:'center'
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
					{field:'visibility',title:'能见度值',width:50,align:'right'},
					{field:'limitspeed',title:'限速值',width:50,align:'right'
						,formatter:function(value,row,index){
							var a = '';
							if(row.visibility){
								if(row.visibility > 500){
									a = 120
								}else if(row.visibility > 200){
									a = 80
								}else if(row.visibility >100 ){
									a = 60
								}else if(row.visibility > 50){
									a = 40
								}else{
									a = 20
								}
							}
							return a;
						}
					},
					{field:'guidlights',title:'尾迹个数',width:80,align:'right'
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
					{field:'luminance',title:'LED亮度',width:50,align:'right'
						,formatter:function(value,row,index){
							if(value==null || value=='null'){
								return '';
							}
							return value+'级';
						}
					},
					{field:'flickerfrequency',title:'闪烁频率',width:60,align:'right'
						,formatter:function(value,row,index){
							if(value==null || value=='null'){
								return '';
							}
							if(value=='1'){
								return '1HZ';
							}else if(value=='2'){
								return '2HZ';
							}else if(value=='3'){
								return '0.5HZ';
							}else{
								return value;
							}
						}
					},
					/*{field:'luminancethreshold',title:'夜间照度阈值',width:60,align:'right'
						,formatter:function(value,row,index){
							if(value==null || value=='null'){
								return '';
							}
							return value+'级';
						}
					},*/
					{field:'workpoint',title:'工作数量',width:55,align:'right'}
				]]
			}
		},
		init:function(){
			this.treeInit();
			this.menuHover();
			
			//修改密码绑定事件
			$('.modify-pwd-btn').click(function(){
				$('#modify-pwd-win').dialog('open');
			});
			$('#btn-pwd-close').click(function(){
				$('#modify-pwd-win').dialog('close');
			});
			$('#btn-pwd-submit').click(this.modifyPwd);
			$('.logout-btn').click(this.logout);
			
			grid = new YDataGrid(this.config);
			grid.init();
		}
	};
}();

$(function(){
	YiYa.main.init();
});		