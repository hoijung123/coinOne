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
	function change(currency_pair) {
		location.href = "listOrdersSell?currency_pair=" + currency_pair;
	}
</script>


<body>
	<h1>OrdersSell</h1>
	<select name="currency_pair" id="currency_pair"
		onchange="change(this.value);">
		<option value="eth_krw"
			<c:if test="${currency_pair eq 'eth_krw'}">selected</c:if> >eth_krw</option>
		<option value="etc_krw" <c:if test="${currency_pair eq 'etc_krw'}">selected</c:if> >etc_krw</option>
	</select>

	<table border="1">
		<tr>
			<td width="100">id</td>
			<td width="50">buy_seq</td>
			<td width="50">type</td>
			<td width="100">price</td>
			<td width="100">coin_amount</td>
			<td width="50">sell_date</td>
			<td width="50">order_id</td>
			<td width="50">status</td>
			<c:forEach var="sub" items="${ordersSellList}">
				<tr>
					<td>${sub.currency_pair}</td>
					<td></td>
					<td>${sub.currency_pair}</td>
					<td align="right">${sub.price}</td>
					<td>${sub.coin_amount}</td>
					<td></td>
					<td>${sub.order_id}</td>
					<td>${sub.status}</td>
				</tr>
			</c:forEach>
	</table>

</body>

</html>