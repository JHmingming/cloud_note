
function share(){
		var $li=$("#note_ul a.checked").parent();
		var noteId=$li.data("noteId");
		var noteName=$li.text();
		$.ajax({
			url:path+"/share/add.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					var sli="";
					sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">';
					sli+='</i>';
					sli+=noteName;
					sli+='<i class="fa fa-sitemap"></i>'
					sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
					sli+='<i class="fa fa-chevron-down"></i></button>';
					//将笔记li元素中的<a>替换掉
					$li.find("a").html(sli);
					
					alert("分享成功");
				}
			},
			error:function(){
				
			}
		});
	}
function setBegin(){
	var noteTitle=$("#search_note").val();
	begin+=5;
	searchresultajax(noteTitle,begin);
}
function searchresult(event){
	var code=event.keyCode;//获取键盘的code码
	
	if(code==13){
		$("#search_ul li").remove();
		var noteTitle=$("#search_note").val();
		begin=0;
		searchresultajax(noteTitle,begin);
	}
}
function searchresultajax(noteTitle,begin){
	
	$.ajax({
		url:path+"/share/search.do",
		type:"post",
		data:{"noteTitle":noteTitle,"begin":begin},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				
				
				var notes=result.data;
				
				for(var i=0;i<notes.length;i++){
					var shareTitle=notes[i].cn_share_title;
					var shareId=notes[i].cn_share_id;
					createShareList(shareTitle,shareId);
					

				}
				$("#pc_part_2").hide();
				$(" #pc_part_4").hide();
				$(" #pc_part_7").hide();
				$(" #pc_part_8").hide();
				$("#pc_part_6").show();
				
			}
		},
		error:function(){
		
		}
		
	});
}
function createShareList(shareTitle,shareId){
	//创建一个li元素
	var sli = "";
	sli+='<li class="online">';
	sli+='  <a>';
	sli+='	<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	sli+= shareTitle;
	sli+='	<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-star"></i></button>';
	sli+='  </a>';
	sli+='</li>';
	var $li = $(sli);
	$li.data("shareId",shareId);
	//添加到搜索结果列表ul中
	$("#search_ul").append($li);
}