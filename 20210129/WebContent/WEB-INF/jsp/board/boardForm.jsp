<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
	<div align="center">
		<div>
			<h1>게시글 작성</h1>
		</div>
		<div>
			<form id="frm" name="frm" action="boardInsert.do" method="post">
				<table border="1">
					<tr>
						<th width="100">작성자</th>
						<td align="center" width="150">
						<input type="text" id="bName" name="bName" size="30" required="required">
						</td>
						<th width="100">작성일자</th>
						<td align="center" width="150">
						<input type="date" id="bDate" name="bDate" size="20" required="required">
						</td>
					</tr>
					<tr>
						<th width="100">제 목</th>
						<td colspan="3">
						<input type="text" id="bTitle" name="bTitle" size="76" required="required">
						</td>
					</tr>
					<tr>
						<th width="100">내용</th>
						<td colspan="3"><textarea id="bContent" name="bContent" rows="7" cols="78" required="required"></textarea></td>
					</tr>
				</table><br/>		
				<div>
			<button type="submit">등록</button> &nbsp;&nbsp;
			<button type="reset">취소</button> &nbsp;&nbsp;
			<button type="button" onclick="location.href='boardList.do'">목록</button> &nbsp;&nbsp;
		</div>
			</form>
		</div>
		<br />
		
	</div>
</body>
</html>