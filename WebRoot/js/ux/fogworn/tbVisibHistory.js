var myChart = echarts.init(document.getElementById('main'));
$(function(){
	$('#endDt').datetimebox('setValue',eDt);
	$('#beginDt').datetimebox('setValue',bDt);
	$('#btn-search').click(function(){
		getData();
	});
});
var getData = function(){
	var devid = $('#devid').combobox('getValue');
	if(!devid){
		YiYa.alert('提示','请选择设备!','warning');
		return;
	}
	$.ajax({
		type: "POST",
		data: {
			devid:devid,
			beginDt:$('#beginDt').datetimebox('getValue'),
			endDt:$('#endDt').datetimebox('getValue')},
		url: ctx+'/tbVisibHistory/listByDevid.do',
		success: function(result){
			if(!result && result.length<1){
				return ;
			}
			var cata = [], data = [];
			for(var i=0; i<result.length; i++){
				cata.push(result[i].dt);
				data.push(result[i].visibility);
			}
			var subTitle = $('#devid').combobox('getText');
			// 基于准备好的dom，初始化echarts图表
            var option = {
            	title: {
            		text :'设备历史能见度',
            		x: 'center',
            		subtext : subTitle,
            	},
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
			            animation: false
			        }
                },
                grid: {
                	right: 35,
			        bottom: 80
			    },
                legend: {
			    	x: 'right',
                    data:['能见度']
                },
                dataZoom: [
			        {
			            show: true,
			            realtime: true,
			            start: 50,
			            end: 100
			        }
			    ],
                xAxis : [
                    {
                        type : 'category',
                        axisLine: {onZero: false},
                        boundaryGap : false,
                        data : cata.map(function (str) {
			                return str.replace(' ', '\n')
			            })
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":"能见度",
                        "type":"line",
                        "data":data
                    }
                ]
            };
            // 为echarts对象加载数据
            myChart.setOption(option);
		}
	});
}