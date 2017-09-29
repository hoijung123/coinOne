<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@page import="korbit.tran.util.*"%>
<jsp:include page="menu.jsp" flush="false"/>
<html>
<head>
<title>Home</title>
</head>
<script type="text/javascript">
	function change(currency_pair)
	{
		location.href="listOrdersOpen?currency_pair=" + currency_pair;
	}
</script>


<body>
	<h1>Transactions</h1>
	<select name="currency_pair" id="currency_pair" onchange="change(this.value);">
		<option value="eth_krw"  <c:if test="${currency_pair eq 'eth_krw'}">selected</c:if> >eth_krw</option>
		<option value="etc_krw" <c:if test="${currency_pair eq 'etc_krw'}">selected</c:if> >etc_krw</option>
	</select>

	<table border="1">
		<tr>
			<td width="100">id</td>
			<td width="50">completedAt</td>
			<td width="50">timestamp</td>
			<td width="50">type</td>
			<td width="100">fee</td>
			<td width="100">price</td>
			<td width="50">amount</td>
			<td width="50">orderId</td>
			<c:forEach var="sub" items="${transactionList}">
				<tr>
					<td>${sub.id}</td>
					<td>${sub.completedAt}</td>					
					<td>${sub.dateTime}</td>	
					<td>${sub.type}</td>				
					<td>${sub.fee.value}</td>
					<td align="right">${sub.fillsDetail.price.value}</td>
					<td align="right">${sub.fillsDetail.amount.value}</td>			
					<td align="center">${sub.fillsDetail.orderId}</td>			
				</tr>
			</c:forEach>
	</table>

</body>
</html>
