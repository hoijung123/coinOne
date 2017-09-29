<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@page import="korbit.tran.util.*"%>
<jsp:include page="menu.jsp" flush="false" />
<html>
<head>
<title>Home</title>
</head>
<script type="text/javascript">
	alert("${currency_pair}");
	location.href = "listOrdersOpen?currency_pair=${currency_pair};
</script>

</html>