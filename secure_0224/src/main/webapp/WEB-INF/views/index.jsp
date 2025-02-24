<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#t1{
		border-collapse: collapse;
		width: 400px;
	}
	#t1 caption{
		text-indent: -9999px;
		height: 0;
	}
	#t1 th, #t1 td{
		border: 1px solid black;
		padding: 4px;
	}
	#t1 thead tr{
		background-color: #cdcdcd;
	}
	
	.modal_bg {
		
		display: none;
		width: 100%;
		height: 100%;
		position: fixed; 
		top: 0;
		left: 0;
		right: 0;
		background: rgba(0, 0, 0, 0.6);
		z-index: 999; 

	}
	
	.modal_wrap {
		
		display: none;
		position: absolute; 
		top: 50%;
		left: 50%;
		transform:translate(-50%,-50%);
		width: 400px;
		height: 400px;
		background: #fff;
		z-index: 1000; 

	}
	.modal_btn{
		text-align: center; 
		padding-top: 20px;
		position: relative;
		bottom: 0px;
	}
	
	.table{
		border-collapse: collapse;
		width: 380px;
		margin: auto;
	}
	.table th, .table td{
		border: 1px solid black;
		padding: 4px;
	}
	.table thead tr{
		background-color: #cdcdcd;
	}
</style>
</head>
<body>
	<h1>메인페이지</h1>
	<hr/>
	<div id="box">
        <c:if test="${sessionScope.mvo ne null}">
            <a href="/mem_logout">로그아웃</a>
            <a href="/bbs?bname=bbs">게시판</a>
        </c:if>
        <c:if test="${sessionScope.mvo eq null}">
            <a href="/login">로그인</a>
            <a href="/reg">회원가입</a>
        </c:if>
    </div>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script>
        $(function(){
            <c:if test="${param.status == '1'}">
                alert("아이디 또는 비밀번호가 일치하지 않습니다.");
            </c:if>
        });
    </script>
</body>
</html>








