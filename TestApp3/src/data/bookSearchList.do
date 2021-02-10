









 




























<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<script>
	if(document.location.toString().indexOf("#") != -1){
		location = "?" + document.location.toString().split("#")[1];
	}
</script>
 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Title" content="HyLib 整合性圖書館自動化系統" />
<meta name="Keywords" content="Webpac 2.0 Chinese Version" />










<link rel="stylesheet" type="text/css" href="/css/default.css"></link>
<link rel="stylesheet" type="text/css" href="/css/design.css"></link>
<link rel="stylesheet" type="text/css" href="/css/SpryTabbedPanels.css"></link>
<link rel="stylesheet" type="text/css" href="/css/thickbox.css" media="screen"></link> 
<link rel="stylesheet" type="text/css" media="all" href="/css/calendar-win2k-1.css"></link>
<link rel="stylesheet" type="text/css" media="all" href="/css/ui.datepicker.css"></link>
<link rel="stylesheet" type="text/css" href="/css/autocomplete.css"></link>
<link rel="stylesheet" type="text/css" href="/css/cssreset.css"></link>
<link rel="stylesheet" type="text/css" href="/css/ui.all.css"></link>
<link rel="stylesheet" type="text/css" href="/css/vkeyboard.css"></link>
<link rel="stylesheet" type="text/css" href="/css/bigrid.css"></link>
<link rel="stylesheet" type="text/css" href="/js/jquery_multiselect/jquery.multiSelect.css"></link>
<link rel="stylesheet" type="text/css" href="/css/placeholder/jquery.placeholder.css"></link>
<link rel="stylesheet" type="text/css" href="/css/nanoscroller.css"></link>
<link rel="stylesheet" type="text/css" href="/css/rcarousel.css"></link > 









<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/jsmultilanguage.jsp"></script>
<script type="text/javascript" src="/js/jquery-ui-latest.js"></script>
<script type="text/javascript" src="/js/thickbox.js"></script> 
<script type="text/javascript" src="/js/jquery.copy.js"></script> 
<script type="text/javascript" src="/js/SpryTabbedPanels.js"></script>
<script type='text/javascript' src="/js/jquery.autocomplete.js"></script>
<script type='text/javascript' src="/js/calendar.js"></script>
<script type='text/javascript' src="/js/calendar-cn.js"></script>
<script type='text/javascript' src="/js/calendar-setup.js"></script>
<script type="text/javascript" src="/js/jquery.jcarousel.pack.js"></script>
<script type="text/javascript" src="/js/pagination.js"></script>
<script type="text/javascript" src="/js/swfobject.js"></script>
<script type="text/javascript" src="/js/validate.js"></script>
<script type="text/javascript" src="/js/ui.datepicker.js"></script>
<script type="text/javascript" src="/js/Map.js"></script>
<script type="text/javascript" src="/js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="/js/jquery.tablesorter.pager.js"></script>
<script type="text/javascript" src="/js/jquery.flash.js"></script>
<script type="text/javascript" src="/js/vkeyboard.jsp"></script>
<script type="text/javascript" src="/js/bigrid.js"></script>
<script type="text/javascript" src="/js/jquery.autoheight.js"></script>
<script type="text/javascript" src="/js/jquery_multiselect/jquery.multiSelect1.js"></script>
<script type="text/javascript" src="/js/countDown.js"></script>
<script type="text/javascript" src="/js/jQuery.dPassword.min.js"></script>
<script type="text/javascript" src="/js/placeholder/jquery.placeholder.js"></script>
<script type="text/javascript" src="/js/jquery.nanoscroller.js"></script>
<script type="text/javascript" src="/js/jquery.imagemapster.js"></script>
<script type="text/javascript" src="/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="/js/jquery.ui.rcarousel.js"></script>
<script type="text/javascript" src="/js/booksearchlist.js"></script>
<link rel="shortcut icon" href="https://webpac.ilccb.gov.tw/images/favacon.ico"/>


<script type="text/javascript" src="/js/groupingpage.js"></script>

<script type="text/javascript">	
	var hadLoading = false;
	var resid = "-1";
	
	(function($) {
		var re = /([^&=]+)=?([^&]*)/g;
		var decode = function(str) {
			return decodeURIComponent(str.replace(/\+/g, ' '));
		};
		$.parseParams = function(query) {
			function createElement(params, key, value) {
				if(params[key]==null){
					params[key]=value;
				}else{
					if($.isArray(params[key])){
						params[key].push(value);
					}else{
						var arr = [];
						arr.push(params[key]);
						arr.push(value);
						params[key] = arr;
					}
					
				}
			}
			query = query + '';
			if (query === '')
				query = window.location + '';
			var params = {}, e;
			if (query) {
				if (query.indexOf('#') !== -1) {
					query = query.substr(0, query.indexOf('#'));
				}
				if (query.indexOf('?') !== -1) {
					query = query.substr(query.indexOf('?') + 1, query.length);
				} else
					return {};
				if (query == '')
					return {};
				while (e = re.exec(query)) {
					var key = decode(e[1]);
					var value = decode(e[2]);
					createElement(params, key, value);
				}
			}
			return params;
		};
	})(jQuery);
	
	$(document).ready(function(){		
		$("#search_inputS").autocomplete("getcompletedata.do", {
			width: 300,			
			matchContains: true,
			selectFirst:false			
		});			
	});
	
	$(window).ready(function(){
		if($.parseParams(location.href).search_field==null||$.parseParams(location.href).search_input==null){
			noGroup();
		}else{
			hasGroup();			
		}		
	});
		
	function loadLeftFrame(){
		if(hadLoading==false){
			$("#leftFrame").attr({src:"booksearch.do?"+window.location.search.substring(1)});
			hadLoading = true;			
		}
	}
	function reloadPage(){
		location.reload();
	}
	function noGroup(){
		$("#rightFrame").attr({src:"maintain/rightFrame2.jsp?"});
		initIframe("rightFrame");
	}
	function hasGroup(){	
		$("#rightFrame").attr({src:"maintain/rightFrame.jsp?"+window.location.search.substring(1)});
		console.log("maintain/rightFrame.jsp?"+window.location.search.substring(1));
		/*
		if ( window.frames["rightFrame"].document.addEventListener ) {	
			window.frames["rightFrame"].document.addEventListener( "DOMContentLoaded", function(){			
				initIframe("rightFrame");
				initFlash();
			}, false );
		} else if ( window.frames["rightFrame"].document.attachEvent ) {	
			initIframe("rightFrame");
			initFlash();
		}*/	
	}
	
	function initIframe(frame_id){
		
		var iframe = document.getElementById(frame_id);		
		iframe.height = 0;	

		try{
			var bHeight = iframe.contentWindow.document.body.scrollHeight;
			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			var aa = iframe.contentWindow.document.documentElement.clientHeight;
			var bb = iframe.contentWindow.document.documentElement.offsetHeight;
			var cc = iframe.contentWindow.document.body.clientHeight;
			var dd = iframe.contentWindow.document.body.offsetHeight;
			var height  = Math.max(bHeight, dHeight);				
			iframe.height =  height;			
		}catch (ex){}
	}
		
	function removeFlash(){
		if(typeof(window.frames["rightFrame"].innerFlash) == undefined){
			setTimeout("removeFlash()",1000);
			return;
		}
		try{			
			if($(window.frames["rightFrame"].document).find("#flashMap").size() == 0){
				setTimeout("removeFlash()",1000);
				return;
			}else{
				$(window.frames["rightFrame"].document).find("#flashMap").parent("div").remove();
			}								
		}catch(e){
			alert(e);
		}				
	}
	
	function initFlash(){	
		if(typeof(window.frames["rightFrame"].innerFlash) == undefined){
			setTimeout("initFlash()",100);
			return;
		}
		try{			
			if($(window.frames["rightFrame"].document).find("#flashMap").html() == null){
				setTimeout("initFlash()",100);
				return;
			}								
			if(resid != $(window.frames["leftFrame"].document).find("#resid").val()){				
				if($(window.frames["leftFrame"].document).find("#resid").val() != undefined){
					resid = $(window.frames["leftFrame"].document).find("#resid").val();	
					window.frames["rightFrame"].innerFlash(resid);			
				}else{
					setTimeout("initFlash()",100);
					return;
				}			
			}
		}catch(e){
			alert(e);
		}
	}

	function removeFlashIframe(){
		$(window.frames["rightFrame"].document).find("#flashMap").parent().remove();
	}	
     
</script>

<style>
.allbook table {
    width:100%;
	margin:0 auto;	
}
</style>

<title>宜蘭縣公共圖書館館藏查詢</title>
</head>

<body>
	<div style="width:500;position: absolute;z-index: 100;background-color: #a8e8e1;display: none" id="vkeyboard">
	<div id="selectItem"><span>子集合：</span></div>
	<div id="containList" class="containList"></div>
       <div id="pagedown">
		<span id="page"></span>&nbsp;&nbsp;
		<a href="javascript:void(0)" id="prevbtn"><img src="images/prev01.gif" alt="上一頁"></a> 
		<a href="javascript:void(0)" id="nextbtn"><img src="images/next02.gif" alt="下一頁"></a>
	</div>
       <div id="usedselectItem"><span>最近用過的符號：</span></div>
       <div class="containList">
		<UL id="commonuse"></UL>
	</div>
    </div>
<div class="wrap">
	
	<div id="collection" style="background:white;width:260px;display:none;position:absolute;"></div>	
	
	<div id="header">
		
































<style>
.ac_loading {
	background: white url('images/jquery_autocomplete.gif') right center no-repeat;
}
</style>

<div id='header' class='header'>
	<h1>
		<a title="HyLib 整合性圖書館自動化系統" href="https://webpac.ilccb.gov.tw/">
			<img src="https://webpac.ilccb.gov.tw/images/logo.gif" alt="HyLib 整合性圖書館自動化系統" title="HyLib 整合性圖書館自動化系統" />
		</a>
	</h1>
	<a href="#" accesskey="U" class="accesskey" title="上方導覽區塊">:::</a>
	<div class='topnav'>
	<ul>  
		
		
		
				<li style='border-left:none'>
					<form method='post' style='display: inline'>
						<span>Language : </span>
						<select name='webpacLang' onchange='this.form.submit()'>
						
							<option value='zh_TW'  selected>中文(繁)</option>
						
							<option value='zh_CN' >中文(简)</option>
						
							<option value='en_US' >English</option>
						
							<option value='ja_JP' >日本語</option>
						
						</select>
 <input name="csrfToken" type="hidden" value=""/></form>
				</li>
		  
				<li ><a href="/index.jsp" title="回首頁">回首頁</a></li>		
				
				<li ><a href="http://www.ilccb.gov.tw"   target="_blank"  title="文化局首頁">文化局首頁</a></li>
				
				<li ><a href="http://webpac.ilccb.gov.tw/help/index.html"   target="_blank"  title="輔助說明">輔助說明</a></li>
						
				<BR/>	
		
				<li style='border-left:none'><a href="https://webpac.ilccb.gov.tw/indexRecommand.do" title="圖書推薦" target="_BLANK">圖書推薦</a></li>					
				
				<li ><a href="http://webpac.ilccb.gov.tw/ShowCalendar.jsp"   target="_blank"  title="開放時間">開放時間</a></li>
		
	</ul>
		
	
		<span class="logintitle"></span>
		<a href="forgetAuths.do" class="forgot" title="<font color=red>忘記帳號密碼?</font>"><font color=red>忘記帳號密碼?</font></a>
	</div>
</div>


<script>
	var logout_url = "";
	var eventQueue = {};
	
	$(document).ready(function(){
		$(".RegisterBtn").click(function(){
			tb_show("網路辦證", "/personalization/memberlogin2.jsp?height=410&width=665&inlineId=preview_temp1&TB_iframe=1",false);
		});
		
	});
	(function($){
	var currFFZoom = 1;
    var currIEZoom = 100;
		$('#zoomin').on('click',function(){
			if ($.browser.mozilla){
				var step = 0.02;
				currFFZoom += step; 
				$('body').css('MozTransform','scale(' + currFFZoom + ')');
			} else {
				var step = 2;
				currIEZoom += step;
				$('body').css('zoom', ' ' + currIEZoom + '%');
			}
		});

		$('#zoomout').on('click',function(){
			if ($.browser.mozilla){
				var step = 0.02;
				currFFZoom -= step;                 
				$('body').css('MozTransform','scale(' + currFFZoom + ')');

			} else {
				var step = 2;
				currIEZoom -= step;
				$('body').css('zoom', ' ' + currIEZoom + '%');
			}
		});
	})($)
</script>

	</div>
	














<div class="path">
	<a href="/index.jsp" title="回首頁"></a>
	
		
		<a href="/index.jsp">館藏書目查詢</a>
	
		&gt;
		<a href="">查詢結果 </a>
	
</div>

	
























<span class="personalbook">
<span class="login">

	 
			  <input type="button" id="loginBtn"   value="登 入" class="btn" alt="登入" title="登 入" onclick="javascript:tb_show('讀者登入', 'personalization/memberLogin.do?rdurl=&height=500&width=700&inlineId=preview_temp1', false)" /> 
	
	
</span>
</span>
<script>
	
		var min = $("input[name=account2]").width();
		var max = 150;
		var setwidth = "請輸入".length*12;
		var inputwidth = 0;
		if(setwidth>min){
			inputwidth = setwidth>max?max:setwidth ;
		}else{
			inputwidth = min;
		}
		$("input[name=account2]").width(inputwidth);		
		$('input[placeholder]').placeholder();
		
		
		
		var au4a83 = $("#au4a83");
		au4a83.val("身分證末4碼或戶號末4碼");
		$("input[name=account2],input[name=passwd2]").focus(function(){
			if($(this).attr("first")==null){
				$(this).val("");
				$(this).attr("first","N");
			}
		})
				
</script>

	<div id="searchbar" class="searchbar">










 


<script>
function simpleSearchSubmit(){
	if($("input[name=keepsitelimit][rel=outer]:checked").size()>0){
		$("#booksearchsimpleform").attr("action","DistributedSearchList.do");
	}else{
		$("#booksearchsimpleform").attr("action","bookSearchList.do");				
	}
}	
</script>

<form action="bookSearchList.do" id="booksearchsimpleform"  method="GET" name='form1' autocomplete="off" onsubmit="simpleSearchSubmit();return checkSearchFrom()" canEmpty="Y">
	<div class="body">
		<input type="hidden" name="searchtype" value="simplesearch"/>  
		<select id="search_field" name="search_field" class="txt">
			
						<option value="FullText" selected=selected>全文</option>
			
						<option value="TI" >題名 </option>
			
						<option value="PN" >個人作者</option>
			
						<option value="PU" >出版者 </option>
			
						<option value="CN" >團體作者</option>
			
						<option value="ISBN" >ISBN</option>
			
						<option value="SE" >叢書名 </option>
			
						<option value="CLAN" >分類號(館藏)</option>
			
						<option value="CNO" >索書號 </option>
			
						<option value="ACN" >條碼號 </option>
				
		</select>
	
		<input id='search_inputS' name="search_input" type="text" class="ac_input" value="java"/>	 
		
		<select id="searchsymbol" name="searchsymbol" class="txt">
			
				<option value="hyLibCore.webpac.search.common_symbol" selected=selected>一般</option>
			
				<option value="hyLibCore.webpac.search.spartial_symbol" >開頭符合</option>
			
		</select>
				
	
	
			<input type ="hidden" id="execodehidden" name="execodehidden" value="true"/>
			<select name="execode" class="txt">
				<option value="" selected='selected' >全部</option>
		
				<option value="webpac.dataType.book" >圖書</option>
			
				<option value="webpac.dataType.journal" >期刊</option>
			
				<option value="webpac.dataType.media" >多媒體</option>
			
			</select>
		
	<input type="hidden" name="ebook" value="">
	<label>
		<input type="submit" id="Submitaaa" value="搜 尋" class="btn" title="搜 尋"/>
	
	</label>
	<script>
	$("#resetbtn").click(function(){ 
			$("#search_inputS").val(''); 
	});
	</script>
	<label>		
		<div class="usebtn">
	    	<a href="javascript:void(0)" id="reducebtn" title="縮小範圍查詢">縮小範圍查詢</a>
	   </div> 
	</label>
	<label>
		<div class="usebtn">
		    <a href="javascript:void(0)" id="advSearchBtn" title="進階查詢">進階查詢</a>
		</div>
	</label>
	
	
	</div>
 <input name="csrfToken" type="hidden" value="0E729A812E1226FC45893FDF09433795"/></form>

<form id="reduceform" action="bookSearchList.do" method="GET">
	<div id="reduceinput" style="display: none">
		
			<span id="s0">
				
				<input type="hidden" name="search_field" value="FullText">				
				<input type="hidden" name="search_input" value="java">
				<input type="hidden" name="searchsymbol" value="hyLibCore.webpac.search.common_symbol">
			</span>
		
		
		

		<input type="hidden" name="language"	id="language" value="">
		<input type="hidden" name="execode"  	id="execode" value="">
		<input type="hidden" name="nowpage"  	id="nowpage" value="1" >

		<input type="hidden" name="sortfield"	id="sortfield" value="YEAR">	
		<input type="hidden" name="sorttype" 	id="sorttype" value="1">	
		<input type="hidden" name="showtuple" 	id="showtuple" value="10">	
		<input type="hidden" name="authoriz"	id="authoriz" value="1">	
		<input type="hidden" name="searchtype"	id="searchtype" value="simplesearch">	
		<input type="hidden" name="phonetic" 	id="phonetic" value="0">
		<input type="hidden" name="viewmode" 	id="viewmode" value="1">
		<input type="hidden" name='op' 			id="op" value='and'>		
		<input type="hidden" name="ebook" 		id="ebook" value="">
		<input type="hidden" name="categoraycode" 	id="categoraycode" value="">
		<span id="keepsitelimitblock">
			
				
			
		</span>		
		<span id="featurecodeblock">
			
		</span>

		
		
		
		
				
		<span id="pubyearblock">
			<input type="hidden" name="startyear" 	id="startyear" value="" >
			<input type="hidden" name="endyear" 	id="endyear" value="">
		</span>
	</div>	  		
 <input name="csrfToken" type="hidden" value="0E729A812E1226FC45893FDF09433795"/></form> 
<div id='advSearchBlock' style='display:none'> 	
	
	















 

<script>
	var count = 1;
	
	$(document).ready(function(){	
		
		if($("#adclassTypeSelect").size()>0){
			$("#adclassTypeSelect").empty();
			$.each($.classtypedef,function(k,v){
			       $("<OPTION/>").text(v).val(k).appendTo($("#adclassTypeSelect"));
			});		
			
			$("#adclassTypeSelect").change(function(){			
				$("#adclanSelect").empty();
				$.each($.classno2[$("#adclassTypeSelect :selected").val()],function(k,v){
					var val = k=="all"?k:k.replace(/_/,"");
					$("<OPTION/>").text(v.name).val(val).appendTo($("#adclanSelect"));
				});						
			});
		}
					
		$("#optionAdvBtn").click(function(){		
			if($("#optionAdv").css("display") == "block"){
				$("#optionAdv").css("display","none");
				$("#optionAdvBtn").attr("src","images/sicon-2.gif");
			}else{
				$("#optionAdv").css("display","block");
				$("#optionAdvBtn").attr("src","images/sicon-1.gif");
			}
		});
		$("#queryField_addBtn").click(function(){	
			
			var code = "<li><select name='op'><option value='and'>AND</option><option value='or'>OR</option>"
					 + "<option value='not'>NOT</option>"
					 + "</select> <select name='search_field'> "
					 		
					 + "<option value='FullText'>全文</option>"
							
					 + "<option value='TI'>題名 </option>"
							
					 + "<option value='PN'>個人作者</option>"
							
					 + "<option value='CN'>團體作者</option>"
							
					 + "<option value='ISBN'>ISBN</option>"
							
					 + "<option value='ISSN'>ISSN </option>"
							
					 + "<option value='SU'>主題 </option>"
							
					 + "<option value='SE'>叢書名 </option>"
							
					 + "<option value='PU'>出版者 </option>"
									 
					 + "</select> "
					 + "<input type='text' size='50' name='search_input' class='ac_input' id='adsearch_input"+count+"'> "
					 
					 + "<select name='searchsymbol'>"
						
					 + "<option value='hyLibCore.webpac.search.common_symbol'>一般</option>"
						
					 + "<option value='hyLibCore.webpac.search.spartial_symbol'>開頭符合</option>"
						
					 + "<option value='hyLibCore.webpac.search.epartial_symbol'>結尾符合</option>"
						
					 + "<option value='hyLibCore.webpac.search.near_symbol'>近似</option>"
						
					 + "<option value='hyLibCore.webpac.search.eq_symbol'>完全符合</option>"
						
					 + "</select> "
											 
					 
					 + "<img onclick='queryField_delBtn(this)' src='images/adsearch_del.gif'></li>";
					 
			$("#newQueryField ul").append(code);
			
			$("#adsearch_input"+count).autocomplete("getcompletedata.do", {
				width: 300,			
				matchContains: true,
				selectFirst:false			
			});
			
			$("#adsearch_input"+count).get(0).focus();
			kindconfig.focus = $("#adsearch_input"+count);
			
			count++;			

			$(kindconfig.field).each(function(){
				$(this).focus(function(){
						kindconfig.focus = $(this);
				});
			});								
		});						
		
		$("#adsearch_input,#adsearch_input1").autocomplete("getcompletedata.do", {
			width: 300,			
			matchContains: true,
			selectFirst:false		
		});	
		
		$("#adsearchkeepsite input[name=keepsitelimit]").live("click",function(){
			if(!$(this).is(":checked")){
				$("#keepsitelimitblock input[value="+$(this).val()+"]").remove();			
			}
		});
	});

	function queryField_delBtn(self){
		$(self).closest("li").remove();
	}
	
	function adSearchSubmit(){				
		if($("#adsearchkeepsite input[name=keepsitelimit][rel=outer]:checked").size()>0){
			$("#adSearchForm").attr("action","DistributedSearchList.do");
		}else{
			$("#adSearchForm").attr("action","bookSearchList.do");				
		}
	}	
	
	function setAdvSort(val){
		var sortfield = val.split(",")[0];
		var sorttype  = val.split(",")[1];
		$("#optionAdv input[name=sortfield]").val(sortfield);
		$("#optionAdv input[name=sorttype]").val(sorttype);
	}		
</script>


<form action="bookSearchList.do" id="adSearchForm" onsubmit="adSearchSubmit();return checkAdSearchFrom()" canEmpty="Y">

<div class="advSearchCon"> 
	<img src="images/advsTitle-1_zh_TW.gif" alt="館藏書目進階檢索"/><p>
		<input type="hidden" name="searchtype" id="searchtype" value="adsearch"/>
		<div id='newQueryField'>
		<ul>
			<li>
				<select name="search_field">
				
					<option value="FullText">全文</option>
				
					<option value="TI">題名 </option>
				
					<option value="PN">個人作者</option>
								
					<option value="CN">團體作者</option>
				
					<option value="ISBN">ISBN</option>
				
					<option value="ISSN">ISSN </option>
				
					<option value="SU">主題 </option>
				
					<option value="SE">叢書名 </option>
				
					<option value="PU">出版者 </option>
				
				</select>
				<input type='text' size='50' name='search_input' id="adsearch_input" class="ac_input">
				
				<select name="searchsymbol">
				
				<option value="hyLibCore.webpac.search.common_symbol">一般</option>
				
				<option value="hyLibCore.webpac.search.spartial_symbol">開頭符合</option>
				
				<option value="hyLibCore.webpac.search.epartial_symbol">結尾符合</option>
				
				<option value="hyLibCore.webpac.search.near_symbol">近似</option>
				
				<option value="hyLibCore.webpac.search.eq_symbol">完全符合</option>
				
				</select>
				
				<img id="queryField_addBtn" src='images/adsearch_add.gif'>
				
				<a href=javascript:void(0) id="vkeyboradbtn" class="vkeyboradbtn" title="語言小鍵盤"><img src="images/keyboard.gif" align="absmiddle"><br>語言小鍵盤</a>
							
			</li>
			<li>
				<select name="op">
					<option value="and">AND</option>
					<option value="or">OR</option>
					<option value="not">NOT</option>
				</select>
				<select id="search_field" name="search_field">
				
					<option value="FullText">全文</option>
				
				
					<option value="TI">題名 </option>
				
				
					<option value="PN">個人作者</option>
				
						
					<option value="CN">團體作者</option>
				
					<option value="ISBN">ISBN</option>
				
				
					<option value="ISSN">ISSN </option>
				
				
					<option value="SU">主題 </option>
				
				
					<option value="SE">叢書名 </option>
				
				
					<option value="PU">出版者 </option>
				
				
				</select>
				<input type="text" id="adsearch_input1" name="search_input" size="50" autocomplete="off" class="ac_input">
				
				<select name="searchsymbol">
	            
				<option value="hyLibCore.webpac.search.common_symbol">一般</option>
				
				<option value="hyLibCore.webpac.search.spartial_symbol">開頭符合</option>
				
				<option value="hyLibCore.webpac.search.epartial_symbol">結尾符合</option>
				
				<option value="hyLibCore.webpac.search.near_symbol">近似</option>
				
				<option value="hyLibCore.webpac.search.eq_symbol">完全符合</option>
				
				</select>
												
			</li>		
		</ul>
	</div>
	
	<img id="adv_img" border="0" align="absmiddle" src="images/advsTitle-2_zh_TW.gif">	
	<div id="optionAdv" class="advSearchBtn">
		<p>
			<label title="每頁顯示">每頁顯示：</label>
			<select name="showtuple">
				<option selected=selected>10</option>
				<option >30</option>
				<option >50</option>
			</select>&nbsp;筆資料
		</p>
		<p>
		<label title="排序條件">排序條件：</label>						
			<select onchange="setAdvSort(this.value)">
				











	
	
		
		
	


	
		
		
					
			<option value="YEAR,0" >出版年舊到新</option>
			<option value="YEAR,1" selected="selected">出版年新到舊</option>
		
	

	
		
		
					
			<option value="OPN,0" >著者-筆劃升冪</option>
			<option value="OPN,1" >著者-筆劃降冪</option>
		
	

	
		
		
					
			<option value="OTI,0" >題名-筆劃升冪</option>
			<option value="OTI,1" >題名-筆劃降冪</option>
		
	
 

			</select>			
			<input type="hidden" name="sortfield" value="YEAR"/>
			<input type="hidden" name="sorttype" value="1"/>
		</p>
		
		<input name="authoriz" type="hidden" value="1"/>
		
		
		<p>
		<label title="查詢方式">查詢方式：</label>
		<input type="radio" name="querytype" value="0" checked=checked/>&nbsp;<var title="精確">精確</var>
		<input type="radio" name="querytype" value="1"  />&nbsp;<var title="模糊查詢">模糊查詢</var>
		<input type="radio" name="querytype" value="2"  />&nbsp;<var title="自然語言">自然語言</var>
		<input type="radio" name="querytype" value="3"  />&nbsp;<var title="相關度查詢">相關度查詢</var>		
		</p>
		
		
		
		<p>
		<label title="中文拼音">中文拼音：</label>
		
		<input name="phonetic" id="phonetic" type="radio" value="1" />&nbsp;<var title="同音">同音</var>
		<input name="phonetic" id="phonetic" type="radio" value="2" />&nbsp;<var title="羅馬拼音">羅馬拼音</var>
		<input name="phonetic" id="phonetic" type="radio" value="3" />&nbsp;<var title="漢語拼音">漢語拼音</var>
		<input name="phonetic" id="phonetic" type="radio" value="4" />&nbsp;<var title="通用拼音">通用拼音</var>
		
		</p>
		
		
				
		<p>
		<label title="出版年區間">出版年區間：</label>
		<input type="text" name="startyear" maxlength="4" size="4" value="">&nbsp;年 ~ 	<input type="text" name="endyear" maxlength="4" size="4" value=""> 年
		</p>
		
		
		
		
		<p>
		<label title="語文">語文：</label>
				
		<input type="radio" name="language" checked="checked" value="" checked=checked>&nbsp;<var title="全部">全部</var>
		
		<input type="radio" name="language" value="chi"  >&nbsp;<var title="中文"> 中文 </var>
		
		<input type="radio" name="language" value="eng"  >&nbsp;<var title="英語"> 英語 </var>
		
		<input type="radio" name="language" value="jpn"  >&nbsp;<var title="日語"> 日語 </var>
		
		<input type="radio" name="language" value="kor"  >&nbsp;<var title="韓語"> 韓語 </var>
		
		<input type="radio" name="language" value="other"  >&nbsp;<var title="其他語文">其他語文</var>
		</p>
		
		
		
		
		
		
		<p>	
			<label title="特藏">特藏：</label>
			<select name="cln" id="clnSelect">
			<option value="all" >全部</option>
			
				<option value="BOOK" >一般書              </option>
			
				<option value="ML" >大陸書              </option>
			
				<option value="OP" >公務書              </option>
			
				<option value="W" >手抄本              </option>
			
				<option value="JP" >日文書              </option>
			
				<option value="F" >外文專櫃            </option>
			
				<option value="Z" >未對應代碼</option>
			
				<option value="DR" >光碟片              </option>
			
				<option value="L" >地方性資料          </option>
			
				<option value="M" >地圖資料            </option>
			
				<option value="MB" >多元圖書</option>
			
				<option value="E" >西文書              </option>
			
				<option value="CI" >巡迴書              </option>
			
				<option value="J" >兒童書              </option>
			
				<option value="BR" >宜蘭分區資源中心專書</option>
			
				<option value="I" >宜蘭專櫃            </option>
			
				<option value="JS" >玩具書              </option>
			
				<option value="GV" >政府出版品          </option>
			
				<option value="O" >原住民資料          </option>
			
				<option value="R" >參考書              </option>
			
				<option value="AD" >唱盤                </option>
			
				<option value="G" >族譜專櫃            </option>
			
				<option value="D" >博物館資料          </option>
			
				<option value="P" >期刊                </option>
			
				<option value="S" >貴重資料            </option>
			
				<option value="VP" >裝訂期刊</option>
			
				<option value="AP" >雷射唱片            </option>
			
				<option value="VB" >影碟片              </option>
			
				<option value="TU" >學位論文            </option>
			
				<option value="IP" >縣籍人士作品        </option>
			
				<option value="IW" >縣籍作家資料        </option>
			
				<option value="AC" >錄音帶              </option>
			
				<option value="VC" >錄影帶              </option>
			
				<option value="AT" >藝術資料            </option>
			
				<option value="T" >觀光資料            </option>
			
			</select>
		</p>
		
		
		
		
				
		<p>
		<label title="特色館藏">特色館藏：</label>
		<select name="featurecode" id="featurecode">
			<option value=""> 請選擇</option>
			
			<option value="0001" isselected="N">青少年</option>
			
			<option value="0002" isselected="N">0~5歲</option>
			
			<option value="0003" isselected="N">縣籍作家多元族群(文化局)</option>
			
			<option value="0004" isselected="N">音樂(宜蘭市)</option>
			
			<option value="0005" isselected="N">投資理財(羅東鎮)</option>
			
			<option value="0006" isselected="N">海事、攝影(蘇澳鎮)</option>
			
			<option value="0007" isselected="N">建築(頭城鎮)</option>
			
			<option value="0008" isselected="N">溫泉、養生(礁溪鄉)</option>
			
			<option value="0009" isselected="N">家政、家事(壯圍鄉)</option>
			
			<option value="0010" isselected="N">傳記、台灣歷史(員山鄉)</option>
			
			<option value="0011" isselected="N">運動休閒(冬山鄉)</option>
			
			<option value="0012" isselected="N">幼教(五結鄉)</option>
			
			<option value="0013" isselected="N">農業、花藝、園藝、茶藝、家庭工藝、飲食(三星鄉)</option>
			
			<option value="0014" isselected="N">舞蹈、原住民(大同鄉)</option>
			
			<option value="0015" isselected="N">原住民(南澳鄉)</option>
			
			<option value="0016" isselected="N">繪本</option>
			
			<option value="0017" isselected="N">多元文化</option>
			
		</select>		
		</p>
		
				

		
				
		<p>
			<label title="分類法">分類法：</label>
			<select name="CLASSTYPE" id="adclassTypeSelect">
				<option value="all" >全部</option>
			</select> 
									
			<select name="CLAN" id="adclanSelect">
				<option value="all" >全部</option>
			</select>
		</p>
		
				

					
				
		<p>  
			<span id="adsearchkeepsite">								
				<label title="館藏地/室">館藏地/室：</label>
				<select id="keepsitelimit" >
					
					<option value=""> 請選擇</option>   		
				 								
					<option value="001" isselected="N"> 文化局圖書館                              </option>
				 								
					<option value="01" isselected="N"> 宜蘭市立圖書館                                  </option>
				 								
					<option value="02" isselected="N"> 羅東總館(李科永)                                  </option>
				 								
					<option value="022" isselected="N"> 羅東仁愛館</option>
				 								
					<option value="023" isselected="N"> 羅東兒美</option>
				 								
					<option value="03" isselected="N"> 蘇澳鎮立圖書館                                  </option>
				 								
					<option value="04" isselected="N"> 頭城鎮立圖書館                                  </option>
				 								
					<option value="05" isselected="N"> 礁溪鄉立圖書館                                  </option>
				 								
					<option value="06" isselected="N"> 壯圍鄉立圖書館                                  </option>
				 								
					<option value="07" isselected="N"> 員山鄉立圖書館                                  </option>
				 								
					<option value="08" isselected="N"> 冬山鄉立圖書館順安館                             </option>
				 								
					<option value="082" isselected="N"> 冬山鄉立圖書館冬山館</option>
				 								
					<option value="09" isselected="N"> 五結鄉立圖書館                                  </option>
				 								
					<option value="10" isselected="N"> 三星鄉立圖書館                                  </option>
				 								
					<option value="11" isselected="N"> 大同鄉立圖書館                                  </option>
				 								
					<option value="12" isselected="N"> 南澳鄉立圖書館                                  </option>
				 								
					<option value="13" isselected="N"> 臺灣戲劇館戲劇視聽圖書室                        </option>
				 								
					<option value="AL" isselected="N"> 樹藝景觀所</option>
				 								
					<option value="IH  " isselected="N"> 宜蘭縣史館</option>
				
				
																				      
				</select>
			</span>	  
		</p>		
									
					
		
							
		
		<!-- 教資館客製 -->
		
		<!-- 教資館客製 -->
		
	</div><br>
	 
	<input type="submit" style="display:none" id="reduceSubmitButton" value="縮小範圍查詢" title='縮小搜尋' class='btn'>
	<input type="submit" id="advSubmitButton" value="搜 尋" title='搜尋' class='btn'>
	<input type="reset" value="清除" title='清除' class='btn'>
	
	<div id="reduceBlock" style="display:none">		
	</div>
</div>

 <input name="csrfToken" type="hidden" value="0E729A812E1226FC45893FDF09433795"/></form>
	 
</div>
<script>

	var adtype = ""; 
	$(document).ready(function(){		
		$("#advSearchBtn").click(function(){					
			$("#"+adtype+"reduceBlock").html('');
			$("#"+adtype+"reduceSubmitButton").css('display','none');
			$("#"+adtype+"advSubmitButton").css('display','inline-block');
			$("#advSearchBlock").toggle("slow");
			$("#content").show();			
			/******查詢列表頁，縮小查詢&進階查詢******/
			if($("#optionAdv").closest(".TabbedPanelsContent").size()==0){
				var multiselectlang = 
				{
					noneSelected:" 請選擇",
					selectAllText:" 全部",
					oneOrMoreSelected:" 已選擇 % 筆"
				};
							
						$("#adsearchkeepsite > #keepsitelimit").attr({name:"keepsitelimit"});				
				
			} 
		});
		$("#reducebtn").click(function(){
			$("#adSearchForm").attr("canempty","Y");
			$("#searchtype").val("reduce");
			$("#"+adtype+"reduceBlock").html($('#reduceinput').html());
			$("#"+adtype+"reduceBlock #keepsitelimitblock").empty();
			$("#"+adtype+"advSubmitButton").css('display','none'); 
			$("#"+adtype+"reduceSubmitButton").css('display','inline-block');
			$("#advSearchBlock").toggle("slow");
			$("#content").show();		
			/******查詢列表頁，縮小查詢&進階查詢******/
			if($("#optionAdv").closest(".TabbedPanelsContent").size()==0){
				var multiselectlang = 
				{
					noneSelected:" 請選擇",
					selectAllText:" 全部",
					oneOrMoreSelected:" 已選擇 % 筆"
				};
							
						$("#adsearchkeepsite > #keepsitelimit").attr({name:"keepsitelimit"});				
				
			} 
		});
	});
</script></div>
	<div id="content">
		<!-- Iframe -->
		<table class="layout" summary="layout table">
			<tr>
				<td class="leftbg hidden">
					<div class="side">
						<div class="accesskey">
							<a href="#" title="左方導覽區塊" accesskey="L">:::</a>
						</div>
						<div id="zone.SideBarLeft"></div>
					</div>
			</td>
			<td class="center">
				<div class="accesskey">
					<a href="#" title="中央內容區塊" accesskey="C">:::</a>
				</div>
				<div id="zone.content11">
					<table summary="layout table">
					</table>
				</div>	
			<div class="accesskey">
				<a href="#" title="中央內容區塊" accesskey="C">:::</a></div>
				<iframe id="leftFrame" onload="initIframe(this.id);loadLeftFrame();"  name="leftFrame"  width="765" background="none" frameborder="0" allowtransparency="true" scrolling="no" src="loadingPage.jsp">
				
				</iframe>
			</td>
			<td class="rightbg">
				<div class="side">
					<div class="accesskey"><a href="#" title="右方相關資訊區塊" accesskey="R">:::</a></div>
					<div id="zone.SideBarRight">		
						<iframe id="rightFrame" onload="initIframe(this.id);" name="rightFrame" width="230" background="none" frameborder="0" allowtransparency="true" scrolling="no" src=""></iframe>			
					</div>
				</div>
			</td>
			</tr>
		</table>
		<!-- Iframe -->	
	</div>
	<div id="footer">











<link href="../css/jquery.alerts.css" rel="stylesheet" type="text/css"  media="screen" />	
<script type="text/javascript" src="../js/jquery.alerts.js"></script>




<div id="footer" class='footer'>
	<div class="copyright">
		<table>
			<tr>  
				<td class="fLeft"></td>  
				<td class="fCenter">
					<p>
											
						©宜蘭縣政府文化局&nbsp;&nbsp;&nbsp; 地址：宜蘭縣宜蘭市復興路二段101號 &nbsp;&nbsp;&nbsp;TEL：(03)9322440&nbsp;&nbsp;&nbsp; 本系統由凌網科技股份有限公司建置
					</p>
				</td>
				<td class="fRight"><br /></td>
			</tr>
		</table>
	</div>
</div>



<script>
$().ready(function(){  	
	(function($){
		if(!$.isEmptyObject(eventQueue)){
			if(eventQueue.ischangePW!=null&&eventQueue.ischangePW.ischangePW=='1'){			
				parent.tb_show("密碼變更","https://webpac.ilccb.gov.tw/personalization/ChangePW.jsp?height=150&width=280&inlineId=preview_temp1&close=CLOSE_logout",false); 
			}else if(eventQueue.integratReserve!=null&&eventQueue.integratReserve!=null){				
				parent.tb_show("???zh_TW.webpac.reserve.placeHold???","IntegratHold.do?action=getReserveHold&marcid="+eventQueue.integratReserve.id+"&height=520&width=660&inlineId=preview_temp1",false);
			}else if(eventQueue.Authorize!=null&&eventQueue.Authorize.id!=null){
				parent.tb_show('讀者授權','personalization/authorize.jsp?id='+eventQueue.Authorize.id+'&height=500&width=700&TB_iframe=1', false);
			}
		}		 
	})($);
})
</script>





<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'G-6NKEP4WKX5
']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
</div>
</div>			
</body>
</html>	
