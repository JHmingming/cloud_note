//移动笔记
function sureMove(){
		var $li=$("#note_ul a.checked").parent();
		var noteId=$li.data("noteId");
		var bookId=$("#moveSelect").val();
		
		//格式检查
		//发送ajax请求
		$.ajax({
			url:path+"/note/move.do",
			type:"post",
			data:{"bookId":bookId,"noteId":noteId},
			dataType:"json",
			success:function(result){
			if(result.status==0){
					
					//移除笔记li
					
					$li.remove();
					alert(result.msg);
				}
			},
			error:function(){
				alert("转移失败")
			}
		});
		
	}

//删除笔记
function sureDeleteNote(){
	
	var noteId=$("#note_ul a.checked").parent().data("noteId");
	$.ajax({
		url:path+"/note/delete.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var $li=$("#note_ul a.checked").parent();
				$li.remove();
				alert(result.msg);
				$("#input_note_title").val("");//设置笔记标题
				um.setContent("");//设置笔记内容
			}
		},
		error:function(){
			alert("删除失败");
		}
	});
}
//菜单笔记处理
function showNoteMenu(){
	$("#note_ul").on("click",".btn_slide_down",function(){
		//隐藏所有笔记菜单
		$("#note_ul div").hide();
		//显示当前点击菜单
		var note_menu=$(this).parents("li").find("div");
		note_menu.slideDown(1000);//note_menu.show(),这是加动画效果的
		//设置选中效果
		$("#note_ul a").removeClass("checked");
		$(this).parent().addClass("checked");
		//阻止li和body的click事件冒泡
		return false;
	});
	//点击页面其他地方将menu隐藏
	$("body").click(function(){
		$("#note_ul div").hide();//会发现下拉菜单也拉不了了，所以得阻止冒泡
	});
	
}

function sureaddnote(){
		var title=$("#input_note").val().trim();
		var userId=getCookie("userId");
		var $li=$("#book_ul a.checked").parent();
		var bookId=$li.data("bookId");
		var ok=true;
		
		if(userId==null){
			window.location.href="log_in.html";		
			ok=false;
			}
		if(title==null){
			$("#title_span").html("输入为空");
			ok=false;
		}
		if(ok){
			$.ajax({
				url:path+"/note/add.do",
				type:"post",
				data:{"userId":userId,"bookId":bookId,"title":title},
				dataType:"json",
				success:function(result){
					var note=result.data;
					if(result.status==0){
						var noteId=note.cn_note_id;
						var title=note.cn_note_title;
						
						cancle();
						createnote(title,noteId);
						alert(result.msg);
						
					}
				},
				error:function(){
				alert("添加笔记失败");
				}
			});
			
			
		}
	}

function update(){
		//找到
		var $li=$("#note_ul a.checked").parent();
		
		var noteId=$li.data("noteId");
		
		var title=$("#input_note_title").val();
		var body=um.getContent();
		$.ajax({
			url:path+"/note/update.do",
			type:"post",
			data:{"title":title,"body":body,"id":noteId},
			dataType:"json",
			success:function(result){
				//更新标题
				var str = "";
				str+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
				str+= title;
				str+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
				//将str替换到笔记li的a元素里
				$li.find("a").html(str);
				//提示成功
				alert(result.msg);

			},
			error:function(){
				alert("修改笔记失败");
				
				
			}
		});
	}
function loadnotes(){
	
		$("#book_ul li a").removeClass("checked");
		$(this).find("a").addClass("checked");
		
		var bookId=$(this).data("bookId");
		
		$.ajax({
			url:path+"/note/loadnotes.do",
			type:"post",
			data:{"bookId":bookId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					
				var notes=result.data;
				$("#note_ul li").remove();
				for(var i=0;i<notes.length;i++){
					
				var title=notes[i].cn_note_title;
				var id=notes[i].cn_note_id;
				var typeid=notes[i].cn_note_type_id;
				if(typeid=="2"){
					createnote1(title,id);
					
				
				}
				else{
					createnote(title,id);
					
				}
				
				
				}
				$("#pc_part_3").show();
				$("#pc_part_5").hide();
				$("#pc_part_2").show();
				$("#pc_part_4").hide();
				$("#pc_part_6").hide();
				$("#pc_part_7").hide();
				$("#pc_part_8").hide();
				}
			},
			error:function(){
				alert("加载笔记失败");
			}
			
		});
	
}
function loadnote(){
	
	$("#note_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	var noteId=$(this).data("noteId");
	
	$.ajax({
		url:path+"/note/load.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			//获取返回的笔记标题
			var title = 
				result.data.cn_note_title;
			//获取返回的笔记内容
			var body = 
				result.data.cn_note_body;
			//设置到编辑区
			$("#input_note_title").val(title);//设置笔记标题
			um.setContent(body);//设置笔记内容

		},
		error:function(){
			
		}
	});
}
function createnote(title,id){
	var sli="";
	sli+='<li class="online">';
		sli+='<a>';
		sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">';
		sli+='</i>';
		sli+=title;
		sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
		sli+='<i class="fa fa-chevron-down"></i></button>';
			sli+='</a>';
				sli+='<div class="note_menu" tabindex="-1">';
				sli+='<dl>';
					sli+='	<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至...""><i class="fa fa-random"></i></button></dt>';
					sli+='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
					sli+='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
					sli+='</dl>';
						sli+='</div>';
							sli+='</li>';
	var $li=$(sli);
	$li.data("noteId",id);
	$("#note_ul").append($li);
}
function createnote1(title,id){
	var sli="";
	sli+='<li class="online">';
		sli+='<a>';
		sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">';
		sli+='</i>';
		sli+=title;
		sli+='<i class="fa fa-sitemap"></i>'
		sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
		sli+='<i class="fa fa-chevron-down"></i></button>';
			sli+='</a>';
				sli+='<div class="note_menu" tabindex="-1">';
				sli+='<dl>';
					sli+='	<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至...""><i class="fa fa-random"></i></button></dt>';
					sli+='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
					sli+='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
					sli+='</dl>';
						sli+='</div>';
							sli+='</li>';
	var $li=$(sli);
	$li.data("noteId",id);
	$("#note_ul").append($li);
}
function recyclenote(){
	$("#recycle_ul li").remove();
	var userId=getCookie("userId");
	$("#recycle_ul li a").removeClass("checked");
	$(this).find("a").addClass("checked");
	
	$.ajax({
		url:path+"/note/recycle.do",
		type:"post",
		dataType:"json",
		data:{"userId":userId},
		success:function(result){
		  for(var i=0;i<result.data.length;i++){
				var noteId=result.data[i].cn_note_id;
				var title=result.data[i].cn_note_title;
			var str='';
			str+='<li class="disable">';
			str+='<a >';
			str+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">';
			str+='</i> ';
			str+=title;
			str+='<button type="button" class="btn btn-default btn-xs btn_position btn_delete">';
			str+='<i class="fa fa-times">';
			str+='</i>';
			str+='</button>';
			str+='<button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay">';
			str+='<i class="fa fa-reply">';
			str+='</i>'
			str+='</button>';
			str+='</a>';
			str+='</li>';
				var $li=$(str);
				$li.data("noteId",noteId);
				$("#recycle_ul").append($li);
			}
		},
		error:function(){
			
		}
	});
	$("#pc_part_5").hide();
	$("#pc_part_2").hide();
	$("#pc_part_4").show();
	$("#pc_part_6").hide();
	$("#pc_part_7").hide();
	$("#pc_part_8").hide();
}
function deleteComplete(){
	
	var noteId=$("#recycle_ul a.checked").parent().data("noteId");	
	alert(noteId);

	$.ajax({
		url:path+"/note/deletecomplete.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				recyclenote();
			}
		},
		error:function(){
			
		}
	});
}