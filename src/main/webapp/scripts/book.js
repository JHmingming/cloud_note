function addBook(){
		var bookname=$("#input_notebook").val();
		var userId=getCookie("userId");
		var ok=true;
		if(userId==null){
			window.location.href="log_in.html";
			ok=false;
		}
		if(bookname==""){
			$("#name_span").html("笔记本名为空");
			ok = false;
		}
		if(ok){
		$.ajax({
			url:path+"/book/add.do",
			type:"post",
			data:{"userId":userId,"bookname":bookname},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					var id=result.data.cn_notebook_id;
					var bookname=result.data.cn_notebook_name;
					cancle();
					createbook(userId,bookname);
				    
				    alert(result.msg);
				}
			},
			error:function(){
				
			}
			
		});
		}
}
function createbook(id,bookname){
	var sli = "";
	sli+='<li class="online">';
	sli+='  <a>';
	sli+='    <i class="fa fa-book" title="online" rel="tooltip-bottom">';
	sli+='    </i>';
	sli+=	bookname;
	sli+='  </a>';
    sli+='</li>';
    $li=$(sli);
    $li.data("bookId",id);
    $("#book_ul").append($li);
}
function loadBooks(){

	var userId=getCookie("userId");
	if(userId==null){
		window.loaction.href="log_in.html";
	}else{
		$.ajax({
			url:path+"/book/loadbooks.do",
			type:"post",
			
			data:{"userId":userId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
				var books=result.data;
				
				for(var i=0;i<books.length;i++){
					var id=books[i].cn_notebook_id;//这个值是给程序员看的，以后更新删除要用
					var name=books[i].cn_notebook_name;
					//创建一个笔记本列表项
					var sli="";
					
					sli+='<li class="online">';
					sli+='<a >';
					sli+='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
					sli+='</i>';
					sli+=name;
					sli+='</a></li>';
					var $li=$(sli);//将sli转化成jQuery对象li元素
					$li.data("bookId",id);//将值与jQuery对象绑定值
					$("#book_ul").append($li);
				}
			}
			},
			error:function(){
				alert("加载列表失败");
			}
			
		});
	}

}
