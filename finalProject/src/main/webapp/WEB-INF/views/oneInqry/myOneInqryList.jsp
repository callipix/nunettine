<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
@font-face {
    font-family: 'seolleimcool-SemiBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2312-1@1.1/seolleimcool-SemiBold.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
.card-body {
	margin-bottom: 20px;
}

.card {
	margin-bottom: 20px;
}

.accordion-button {
	cursor: pointer;
}

.accordion-content {
	display: none;
}

.background-icon {
	margin-top: -20px;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'GmarketSansLight';
}
a, a:link {
  color: #000;
  text-decoration: none;
  font-family: 'GmarketSansLight';
}

/* 네비게이션 메뉴 */
.sidenav {
  width: 200px;
  position: fixed;
  z-index: 1;
  font-size: 18px;
  top: 153px;
  left: 45px;
  border-bottom: 1px solid lightgray;
  overflow-x: hidden;
  padding: 15px 0;
  text-align: center;
}
.sidenav p {
  padding: 6px 8px 6px 8px; /*주희 수정 왼쪽 패딩 맞춤*/
  text-decoration: none;
  font-size: 18px;
  display: block;
  color: #000;
}
.sidenav a, .sidenav a:link {
  padding: 6px 8px 6px 8px; /*주희 수정 왼쪽 패딩 맞춤*/
  text-decoration: none;
  font-size: 18px;
  color: #000;
  display: block;
  color: #000;
  font-family: 'GmarketSansLight';
  position: relative;
  overflow: hidden;
}
.sidenav a::after {
  content: '';
  display: block;
  position: absolute;
  bottom: 2px; /*주희 수정 hover시 아래로 치우침 맞춤*/
  left: 50%; /* 배경의 시작 위치를 텍스트 중앙으로 설정 */
  transform: translateX(-50%); /* 가로 방향으로 이동시킴 */
  z-index: -1;
  width: 0; /* 초기 가로 크기를 0으로 설정 */
  height: 90%; /* 주희 수정 hover 간격 띄우기*/
  background: rgba(208, 207, 251, 0.5); /* 주희수정 색상 누네로 톤 맞춤 */
  transition: width .3s;
}
.sidenav a:hover::after {
	width: 90%; /* 호버 시 가로 크기를 100%로 확장 */
}
 
 #balloon{
	margin : -184px 0px -238px 379px;
	z-index : 1;
}

#lbrtybbsctt{
	font-family: 'seolleimcool-SemiBold'; 
	color:#4e4c7c; 
	text-shadow: -2px 0px white, 0px 2px white, 2px 0px white, 0px -2px white;
	margin: 54px 0 -109px 549px;
	position : relative;
	z-index: 2;
}
</style>
<script>
	
$(function(){
    $(".dropdown-item").click(function() {
        var selectedText = $(this).text();
        $("#dropdownMenuSizeButton3").text(selectedText);
    });
    
    // 전체 목록 출력
    let currentPage = "${param.currentPage}";
    if(currentPage == ""){
    	currentPage = "1";
    }
    
    let data = {
    		"keyword" : "${param.keyword}",
			"currentPage" : currentPage
    }
    
    $.ajax({
    	url : "/oneInqry/mySearchList",
    	 contentType : "application/json; charset=utf-8",
 	    data : JSON.stringify(data),
 	    type : "post",
 	    dataType : "json",
 	    success : function(res){
 	    	console.log(res);
 	    	var str = "";
 	    	$("#allTabBody").html("");
 	    	$("#divPaging").html("");
 	    	var length = res.data.content.length;
 	    	var mngrId = res.mngrId;
 	    	console.log("관리자 아이디 : " ,mngrId);
            if(length == 0){
            	str = "<td colspan='5' style='font-size:15pt;'>문의 내역이 없습니다.</td>"
	            $("#allTabBody").html(str);
        	}	
 	    	$.each(res.data.content, function(i,v){
 	    		console.log("res.data.content", res.data.content);
 	    		switch(mngrId){
 	    		//관리자
 	    		case "testAdmin" :
 	    			str += "<tr>";
 	    			str += "<td id='num"+v.oneInqryNo+"'>"+v.num+"</td>";
 	    			str += "<td id='userId"+v.oneInqryNo+"'>"+v.userId+"</td>";
 	    			str += "<td id='oneInqrySj"+v.oneInqryNo+"'>";
 	    			str += "<a href='/oneInqry/oneInqryDetail?oneInqryNo="+v.oneInqryNo+"&userId="+v.userId+"'>"+v.oneInqrySj+"</a>";
 	    			str += "</td>"
	 	    			if(v.oneInqryAnswerVOList[0].oneInqryAnswerCn == null){
	 	    				str += "<td><label class='badge badge-secondary'>미답변</label></td>";
	 	    			}else if(v.oneInqryAnswerVOList[0].oneInqryAnswerCn != null){
	 	    				str += "<td><label class='badge badge-info'>답변완료</label></td>";
	 	    			}
					str += "<td>"+(v.oneInqryWritngDt).substr(0,10)+"</td>";
					str += "</tr>";
					$("#allTabBody").html(str);
					break;
	 	    		
 	    		default :
 	    			str += "<tr>";
 	    			str += "<td id='num"+v.oneInqryNo+"'>"+v.num+"</td>";
                    str += "<td id='userId" + v.oneInqryNo + "'>" + v.userId + "</td>";
 	    			str += "<td id='oneInqry"+v.oneInqryNo+"'>";
 	    			str += "<a href='/oneInqry/oneInqryDetail?oneInqryNo="+v.oneInqryNo+"'>"+v.oneInqrySj+"</a>";
 	    			str += "</td>"
 		 	    			if(v.oneInqryAnswerVOList[0].oneInqryAnswerCn == null){
 		 	    				str += "<td><label class='badge badge-secondary'>진행중</label></td>";
 		 	    			}else if(v.oneInqryAnswerVOList[0].oneInqryAnswerCn != null){
 		 	    				str += "<td><label class='badge badge-info'>답변완료</label></td>";
 		 	    			}
 	    			str += "<td>"+(v.oneInqryWritngDt).substr(0,10)+"</td>";
					str += "</tr>";
					$("#allTabBody").html(str);
					break;
 	    		}// switch문 끝
 	    	});// each 끝
 	    	if(res.data.total > 0){
        	    $("#divPaging").html(res.data.pagingArea);
            }else{
        	    $("#divPaging").html("");
            }
 	    } // success 끝
    });// 전체목록 출력 ajax 끝
    
    //검색
	$("#searchBtn").on("click",function(){
		let keyword = $("input[name='keyword']").val();
		if (keyword.trim() === '') {
	          Swal.fire({
	              title: '검색어가 없습니다.',
	              icon: 'warning',
	              confirmButtonText: '확인'
	          });
	          return; // 검색어가 없으면 더 이상 실행하지 않음
	      }
		
		// 검색 카테고리 설정
		let selectColumn = $("#dropdownMenuSizeButton3").text();
		if(selectColumn == "닉네임"){
			selectColumn = "A.USER_ID";
		}else if(selectColumn == "제목"){
			selectColumn = "A.ONE_INQRY_SJ";
		}else if(selectColumn == "전체"){
			selectColumn = "ALL";
		}

		let currentPage = "1";
		
		var data = {
				"keyword" : keyword,
				"selectColumn" : selectColumn,
				"currentPage" : currentPage
					};
		$.ajax({
	        url : "/oneInqry/mySearchList",
	        contentType : "application/json; charset=utf-8",
	        data : JSON.stringify(data),
	        type : "post",
	        dataType : "json",
	        success: function(res){
	            console.log("res", res);
	            $("#allTabBody").html("");
	            var total = res.total;
	            var mngrId = res.mngrId;
	            var str = "";
	            var length = res.data.content.length;
	            if(length == 0){
	            	str = "<td colspan='5' style='font-family: GmarketSansLight; font-size:15pt;'>'"+keyword+"' 로 검색된 결과가 없습니다</td>"
		            $("#allTabBody").html(str);
	        	}
	            str += "<tr><td>'"+keyword+"' 로 검색된 결과가"+res.total+"건 있습니다.<td/></tr>";
	            $.each(res.data.content, function(i, v){
	            	console.log(res.data.content);
	 	    		switch(mngrId){
	 	    		//관리자
	 	    		case "testAdmin" :
	 	    			console.log("관리자란다");
	 	    			str += "<tr>";
	 	    			str += "<td id='num"+v.oneInqryNo+"'>"+v.num+"</td>";
	 	    			str += "<td id='userId"+v.oneInqryNo+"'>"+v.userId+"</td>";
	 	    			str += "<td id='oneInqrySj"+v.oneInqryNo+"'>";
	 	    			str += "<a href='/oneInqry/oneInqryDetail?oneInqryNo="+v.oneInqryNo+"&userId="+v.userId+"'>"+v.oneInqrySj+"</a>";
	 	    			str += "</td>"
		 	    			if(v.oneInqryAnswerVOList[0].oneInqryAnswerCn == null){
		 	    				str += "<td><label class='badge badge-secondary'>미답변</label></td>";
		 	    			}else if(v.oneInqryAnswerVOList[0].oneInqryAnswerCn != null){
		 	    				str += "<td><label class='badge badge-info'>답변완료</label></td>";
		 	    			}
						str += "<td>"+(v.oneInqryWritngDt).substr(0,10)+"</td>";
						str += "</tr>";
						$("#allTabBody").html(str);
						break;
		 	    		
	 	    		default :
	 	    			str += "<tr>";
	 	    			str += "<td id='num"+v.oneInqryNo+"'>"+v.num+"</td>";
	                    str += "<td id='userId" + v.oneInqryNo + "'>" + v.userId + "</td>";
	 	    			str += "<td id='oneInqry"+v.oneInqryNo+"'>";
	 	    			str += "<a href='/oneInqry/oneInqryDetail?oneInqryNo="+v.oneInqryNo+"'>"+v.oneInqrySj+"</a>";
	 	    			str += "</td>"
	 		 	    			if(v.oneInqryAnswerVOList[0].oneInqryAnswerCn == null){
	 		 	    				str += "<td><label class='badge badge-secondary'>진행중</label></td>";
	 		 	    			}else if(v.oneInqryAnswerVOList[0].oneInqryAnswerCn != null){
	 		 	    				str += "<td><label class='badge badge-info'>답변완료</label></td>";
	 		 	    			}
	 	    			str += "<td>"+(v.oneInqryWritngDt).substr(0,10)+"</td>";
						str += "</tr>";
						$("#allTabBody").html(str);
						break;
	 	    		}// switch문 끝
	            });//each 끝;
	            if(total > 0){
	        	    $("#divPaging").html(res.data.pagingArea);
	            }else{
	        	    $("#divPaging").html("");
	            }
	        } // success 끝
	    }); // 검색 ajax 끝
	});// 검색 버튼 함수 끝
	
    //전체 탭 클릭 시
    $("#all-tab").on("click",function(){
    	$("#all-tab").addClass("active");
    	$("#noAnswer-tab").removeClass("active");
    	$("#success-tab").removeClass("active");
    	
    	// 나머지  탭 닫기
		$("#noAnswerTab").removeClass("active show");
		$("#successTab").removeClass("active show");
		
		// 현재 탭 열기
		$("#allTab").addClass("active show");
    });//전체목록 함수 끝
	
	// 미답변 탭 클릭
	$("#noAnswer-tab").on("click",function(){
    	$("#noAnswer-tab").addClass("active");
		$("#all-tab").removeClass("active");
    	$("#success-tab").removeClass("active");		
		
		// 나머지  탭 닫기
		$("#allTab").removeClass("active show");
		$("#successTab").removeClass("active show");
		// 현재 탭 열기
		$("#noAnswerTab").addClass("active show");
	});//미답변 목록 함수 끝
	
	// 답변완료 탭 클릭
	$("#success-tab").on("click",function(){
    	$("#success-tab").addClass("active");		
    	$("#noAnswer-tab").removeClass("active");
		$("#all-tab").removeClass("active");
		
		// 나머지  탭 닫기
		$("#allTab").removeClass("active show");
		$("#noAnswerTab").removeClass("active show");
		// 현재 탭 열기
		$("#successTab").addClass("active show");
	});//답변완료 목록 함수 끝
}); // 전체 함수 끝
</script>
<div class="container col-lg-12">
	<input type="hidden" id="sessionId" value="${memSession.userId}" />
	<div class="col-12 stretch-card grid-margin grid-margin-md-0">
		<div class="card">
		<div >
			<img alt="1대1문의" src="../resources/images/상담.png" style="width:100px; height:auto; margin:0 0 20px 600px;">
			<h2 id="noticeTitle" style="text-align:center; font-family: 'seolleimcool-SemiBold'; color:#4e4c7c; text-shadow: -2px 0px white, 0px 2px white, 2px 0px white, 0px -2px white;">1:1 문의</h2>
			<hr style="border-top: 50px solid #f5f7ff; margin:-50px 500px 0 500px;">
		</div>
		</div>
	</div>
</div>
<div class="sidenav">
	<p style="text-align: center;">
		<b>MYPAGE MENU</b>
	</p>
	<hr>
	<c:if test="${memSession.userId  != null && proSession.userId  == null}">
		<a href="/member/memberMypage">마이페이지</a>
		<a href="/srvcBtfInqry/myBtfInqryList">보낸 서비스 사전 문의</a>
	 	<a href="/srvcRequst/mySrvcRqList">보낸 서비스 요청서</a> 
		<a href="/member/memberOndyclList">원데이클래스</a>
		<a href="/member/memberPostList">게시글 관리</a> 
		<a href="/srvcRqReview/reMgmt" id="reviewMgmtLink">서비스 요청 리뷰 관리</a>
		<a href="/oneInqry/myOneInqryList">1:1 문의 내역</a>	
	</c:if>
	<c:if test="${proSession.userId  != null && memSession.userId == null}">
		<a href="/pro/proMypage">마이페이지</a>
		<a href="/srvcBtfInqry/myBtfInqryList">받은 서비스 사전 문의</a>
		<a href="/srvcRequst/mySrvcRqList">받은 서비스 요청서</a> 
		<a href="/pro/proMyClassList">원데이클래스</a>
		<a href="/pro/proPostList">게시글 관리</a> 
		<a href="/srvcRqReview/proReMgmt" id="reviewMgmtLink">서비스 요청 리뷰 관리</a>
	<a href="/oneInqry/myOneInqryList">1:1 문의 내역</a>
	</c:if>
</div>
<div class="col-12 grid-margin stretch-card">
<div class="card">
		<div class="dropdown show"
				style="float: right; position: absolute; margin : 26px 0 0 860px; z-index: 2;">
				<button type="button" class="btn btn-outline-primary btn-sm"onclick="location.href='/oneInqry/oneInqryCreate'">문의하기</button>
				<button class="btn btn-inverse-primary btn-sm dropdown-toggle" style="font-family: 'seolleimcool-SemiBold';"
					type="button" id="dropdownMenuSizeButton3" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="true">전
					체</button>
				<input type="text" name="keyword" id="keyword"
					style="width: auto; border-radius: 15px; border: 0; outline: none; background-color: rgb(233, 233, 233); height: 30px; padding-left:11px;" />
				<button type="button" class="btn btn-inverse-primary btn-sm"
					id="searchBtn">
					<i class="mdi mdi-yeast"></i>
				</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuSizeButton3"
					style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 31px, 0px);"
					x-placement="bottom-start">
					<p class="dropdown-item" style="font-family: 'seolleimcool-SemiBold';">전체</p>
					<p class="dropdown-item" style="font-family: 'seolleimcool-SemiBold';">닉네임</p>
					<p class="dropdown-item" style="font-family: 'seolleimcool-SemiBold';">제목</p>
				</div>
			</div>
	<div class="card-body">
		<ul class="nav nav-tabs" role="tablist">
			<li class="nav-item" role="presentation"><a
				class="nav-link active" id="all-tab"  aria-controls="all-1" href="/oneInqry/myOneInqryList"
				aria-selected="true">전체</a></li>
			<li class="nav-item" role="presentation"><a class="nav-link"
				id="noAnswer-tab"  href="/oneInqry/myOneInqryNoAnswerList"
				aria-controls="noAnswer-1" aria-selected="false" tabindex="-1">미답변</a>
			</li>
			<li class="nav-item" role="presentation"><a class="nav-link"
				id="success-tab"  href="/oneInqry/myOneInqrySuccessList"
				aria-controls="success-1" aria-selected="false" tabindex="-1">완료</a>
			</li>
		</ul>
		<!-- 전체 탭 -->
		<div class="tab-content">
			<div class="tab-pane fade active show" id="allTab" role="tabpanel"
				aria-labelledby="all-tab" >
				<div class="table-responsive" id="btfInqryList">
					<table class="table" style="text-align: center;">
						<thead>
							<tr>
								<th style="font-size: 15pt;">번 호</th>
								<th style="font-size: 15pt;">아이디</th>
								<th style="font-size: 15pt;">제 목</th>
								<th style="font-size: 15pt;">진행상태</th>
								<th style="font-size: 15pt;">문의일자</th>
							</tr>
						</thead>
						<tbody id="allTabBody">
						</tbody>
					</table>
					<div id="divPaging"
						style="position: relative; margin-left: 48%; margin-top: 20px;"></div>
				</div>
			</div>
			<!-- 미답변 탭 -->
			<div class="tab-pane fade " id="noAnswerTab" role="tabpanel"
				aria-labelledby="noAnswer-tab">
				<div class="table-responsive" id="btfInqryList">
					<table class="table" style="text-align: center;">
						<thead>
							<tr>
								<th style="font-size: 15pt;">번 호</th>
								<th style="font-size: 15pt;">아이디</th>
								<th style="font-size: 15pt;">제 목</th>
								<th style="font-size: 15pt;">진행상태</th>
								<th style="font-size: 15pt;">문의일자</th>
							</tr>
						</thead>
						<tbody id="noAnswerTabBody">
						</tbody>
					</table>
					<div id="No_divPaging"
						style="position: relative; margin-left: 48%; margin-top: 20px;"></div>
				</div>
			</div>
			<!-- 완료 탭 -->
			<div class="tab-pane fade" id="successTab" role="tabpanel"
				aria-labelledby="success-tab">
				<div class="table-responsive" id="btfInqryList">
					<table class="table" style="text-align: center;">
						<thead>
							<tr>
								<th style="font-size: 15pt;">번 호</th>
								<th style="font-size: 15pt;">아이디</th>
								<th style="font-size: 15pt;">제 목</th>
								<th style="font-size: 15pt;">진행상태</th>
								<th style="font-size: 15pt;">문의일자</th>
							</tr>
						</thead>
						<tbody id="successTabBody">
						</tbody>
					</table>
					<div id="S_divPaging"
						style="position: relative; margin-left: 48%; margin-top: 20px;"></div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>