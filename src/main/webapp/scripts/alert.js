//弹出创建笔记本对话框
function alertAddBookWindow(){
		//弹出添加笔记本对话框
		$("#can").load("alert/alert_notebook.html");
		//相当于使用ajax请求，访问alert中的页面，是一个封装
		//显示对话框的背景
		$(".opacity_bg").show();
	
		
	}
function cancle(){
	
		$("#can").html("");
		$(".opacity_bg").hide();
	
}
//弹出创建笔记对话框
function alertAddNoteWindow(){
	//判断是否有笔记本被选中
	var $li=$("#book_ul a.checked").parent();
	if($li.length==0){
		alert("请选择笔记本");
	}else{
	$("#can").load("alert/alert_note.html");
	$(".opacity_bg").show();}
}
function alertDeleteNoteWindow(){
	$("#can").load("alert/alert_delete_note.html");
	$(".opacity_bg").show();
}
function alertDeleteRollBack(){
	$("#can").load("alert/alert_delete_rollback.html");
	$(".opacity_bg").show();
}
function alertReplay(){
	$("#can").load("alert/alert_replay.html",function(){
		$(".opacity_bg").show();
		var lis=$("#book_ul li");
		//<option value="笔记本id"></option>
		for(var i=0;i<lis.length;i++){
			var $li=$(lis[i]);//将li转成jQuery对象
			var bookId=$li.data("bookId");
			var bookName=$li.text().trim();
			var opt="";
			opt+='<option value="';
			opt	+=bookId;
			opt	+='">';
			opt+=bookName;
			opt+='</option>';
		    //转成jQuery是为了绑定data，写这样也可以
			$("#replaySelect").append(opt);
		}
	});
	
	
}
function alertMove(){
	$("#can").load("alert/alert_move.html",function(){
		$(".opacity_bg").show();
		//加载对话框中的option笔记本项
		var lis=$("#book_ul li");
		//<option value="笔记本id"></option>
		for(var i=0;i<lis.length;i++){
			var $li=$(lis[i]);//将li转成jQuery对象
			var bookId=$li.data("bookId");
			var bookName=$li.text().trim();
			var opt="";
			opt+='<option value="';
			opt	+=bookId;
			opt	+='">';
			opt+=bookName;
			opt+='</option>';
		    //转成jQuery是为了绑定data，写这样也可以
			$("#moveSelect").append(opt);
		}
	});
	
}