function searchshare(){
		$("#search_ul a").removeClass("checked");
		$(this).find("a").addClass("checked");
		var shareId=$(this).data("shareId");
		$.ajax({
			url:path+"/share/show.do",
			type:"post",
			data:{"shareId":shareId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					var notes=result.data;
					var noteBody=notes.cn_share_body;
					var noteTitle=notes.cn_share_title;
					
					$("#noput_note_title").html(noteTitle);
					$("#noput_note_body").html(noteBody);
						$("#pc_part_3").hide();
						$("#pc_part_5").show();
					
				}
			},
			error:function(){
				
			}
		});
	}