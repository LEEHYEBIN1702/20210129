<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정하기</title>
</head>
<!-- Page content -->
<div class="w3-content" style="max-width: 2000px; margin-top: 46px" align="center">

	<div align="center">
		<div>
			<h1>게시글 수정</h1>
		</div>

		<div>
		<form id="frm" name="frm" action="boardUpdate.do" method="post">
			<table border="1">
				<tr>
					<th width="100">글 번호</th>
					<td width="100" align="center">
						<input type="text" id="bId" name="bId" value="${vo.bId }" readonly="readonly"></td>
					<th width="100">작성자</th>
					<td width="140" align="center">${vo.bName }</td>
					<th width="100">작성일자</th>
					<td width="120" align="center">${vo.bDate }</td>
					<th width="100">조회수</th>
					<td width="100" align="center">${vo.bHit }</td>
				</tr>
				<tr>
					<th width="100">제목</th>
					<td colspan="7">${vo.bTitle }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="7"><textarea id="bContent" name="bContent" rows="7" cols="125" >${vo.bContent }</textarea>
					</td>
				</tr>
			</table>
				<div> <br />
					<button type="submit">등록</button>&nbsp;&nbsp;&nbsp;
					<button type="reset">취소</button>&nbsp;&nbsp;&nbsp;
					<button type="button" onclick="location.href='boardList.do'">목록</button>
				</div>
			</form>
		</div>
	</div>
	</div>
</body>
</html>