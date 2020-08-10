<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Begin Project!</h1>
	<P>"처음 만든 버튼"을 누르셨습니다!</P>

	<c:forEach var="item" items="${resultList}">
		<ul>
			<li>${item.name}
				<ul>
					<li>${item.dirCnt}
						<ul>
							<li>${item.depth}</li>
						</ul>
					</li>
				</ul>
			</li>
		</ul>

	</c:forEach>
</body>
</html>
