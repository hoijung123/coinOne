<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/coinone.css'/>">
<jsp:include page="menu.jsp" flush="false" />
<html>
<head>
<title>Home</title>
</head>
<script type="text/javascript">
	function change(currency_pair) {
		location.href = "listOrdersOpen?currency_pair=" + currency_pair;
	}
	
	function cancelOrder(currency, orderId, qty, price, type) {
        if (!confirm("Order를 삭제하시겠습니까?"))
        {
            return;
        }
		var loc = "cancelOrder?currency=" + currency + "&orderId=" + orderId + "&qty=" + qty + "&price=" + price + "&type=" + type;
		location.href = loc;
	}

    function registerOrderReq() {
	    if (!confirm("Order를 생성하시겠습니까?"))
		{
		    return;
		}
        var loc = "registerOrderReq";
        location.href = loc;
    }
</script>


<body>
	<h1>OrdersOpen</h1>
	<select name="currency_pair" id="currency_pair"
		onchange="change(this.value);">
		<option value="eth_krw"
			<c:if test="${currency_pair eq 'eth_krw'}">selected</c:if> >eth_krw</option>
		<option value="etc_krw" <c:if test="${currency_pair eq 'etc_krw'}">selected</c:if> >etc_krw</option>
	</select> <input type="button" value="Order생성" onclick="registerOrderReq()">

	<table border="1">
		<tr>
			<td width="100">id</td>
			<td width="50">type</td>
			<td width="50">seq</td>
			<td width="100">price</td>
			<td width="100">qty</td>
			<td width="50">buy_date</td>
			<td width="50">order_id</td>
			<td width="50">취소</td>
			<c:forEach var="sub" items="${ordersOpenList}">
				<tr>
					<td>${sub.orderId}</td>
					<td><fmt:formatNumber value="${sub.price}"
							pattern="###" /></td>
					<td>${sub.price}</td>
					<td align="right"><fmt:formatNumber value="${sub.price}"
							pattern="###,###" /></td>
					<td>${sub.dateTime}</td>
					<td>${sub.dateTime}</td>
					<td>${sub.type}</td>
					<td><a href="javascript:cancelOrder('${currency}','${sub.orderId}','${sub.qty}','${sub.price}','${sub.type}');">${sub.index}</a></td>
				</tr>
			</c:forEach>
	</table>

</body>

</html>