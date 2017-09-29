<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<jsp:include page="menu.jsp" flush="false"/>

<P>  Token is ${ACCESS_TOKEN}. </P>
<br>
<P>  Pub_time is ${Pub_time}. </P>


</body>
</html>
