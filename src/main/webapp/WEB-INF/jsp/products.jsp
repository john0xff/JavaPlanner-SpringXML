<%@ include file="header.jsp" %>
<body>
	<%@ include file="menu.jsp" %>

    <c:forEach items="${cars}" var="car">
    	<c:out value="${car.model}" />
		${car.brand.name} ${car.model}: ${car.price}
		<br />
	</c:forEach>
</body>
</html>