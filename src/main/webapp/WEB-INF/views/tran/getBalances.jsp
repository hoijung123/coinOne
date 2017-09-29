<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<jsp:include page="menu.jsp" flush="false" />
	<h1>Balances</h1>
	<br>
	<br>
	<table border="1">
		<tr>
			<td width="100">CURRENCY</td>
			<td width="100">available</td>
			<td width="100">trade_in_use</td>
			<td width="100">withdrawal_in_use</td>
			<td width="100">price</td>
			<td width="100">sum</td>
		</tr>
		<tr>
			<td>KRW</td>
			<td align="right">${balances.krw.available}</td>
			<td align="right">${balances.krw.trade_in_use}</td>
			<td align="right">${balances.krw.withdrawal_in_use}</td>
			<td align="right">&nbsp;</td>
			<td align="right">${balances.krw.available + balances.krw.trade_in_use}</td>
		</tr>
		<tr>
			<td>ETH</td>
			<td align="right">${balances.eth.available}</td>
			<td align="right">${balances.eth.trade_in_use}</td>
			<td align="right">${balances.eth.withdrawal_in_use}</td>
			<td align="right"><fmt:formatNumber
					value="${ethTicker.last}"
					pattern="###,###" /></td>
			<td align="right"><fmt:formatNumber
					value="${balances.eth.available * ethTicker.last + balances.eth.trade_in_use * ethTicker.last}"
					pattern="###,###" /></td>
		</tr>
		<tr>
			<td>ETC</td>
			<td align="right">${balances.etc.available}</td>
			<td align="right">${balances.etc.trade_in_use}</td>
			<td align="right">${balances.etc.withdrawal_in_use}</td>
			<td align="right"><fmt:formatNumber
					value="${etcTicker.last}"
					pattern="###,###" /></td>
			<td align="right"><fmt:formatNumber
					value="${balances.etc.available * etcTicker.last + balances.etc.trade_in_use * etcTicker.last}"
					pattern="###,###" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td align="right">&nbsp;</td>
			<td align="right">&nbsp;</td>
			<td align="right">&nbsp;</td>
			<td align="right">&nbsp;</td>
			<td align="right"><fmt:formatNumber
					value="${(balances.eth.available * ethTicker.last + balances.eth.trade_in_use * ethTicker.last)
								+ (balances.etc.available * etcTicker.last + balances.etc.trade_in_use * etcTicker.last) 
								+ (balances.krw.available + balances.krw.trade_in_use) }"
					pattern="###,###" /></td>
		</tr>
	</table>
	<br>

</body>
</html>
