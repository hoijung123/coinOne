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
		location.href = "listLimitOrders?currency=" + currency_pair;
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
        if ("" == qty.value)
        {
            qty.focus();
            return;
        }
        if ("" == reqCnt.value)
        {
            reqCnt.focus();
            return;
        }
	    if (!confirm("Order를 생성하시겠습니까?"))
		{
		    return;
		}
        var loc = "registerOrderReq?currency=" + currency.value + "&qty=" + qty.value + "&reqCnt=" + reqCnt.value;
        location.href = loc;
    }
</script>


<body>
	<h1>TranConfig</h1>

	<table border="1">
		<tr>
			<td width="100">id</td>
			<td width="50">type</td>
			<td width="50">seq</td>
			<td width="100">price</td>
			<td width="50">tran_yn</td>
			<td width="50">order_id</td>
			<td width="50">저장</td>
			<c:forEach var="sub" items="${tranConfigVOList}">
				<tr>
					<td>${sub.currency}</td>
					<td></td>
					<td>${sub.currency}</td>
					<td>${sub.dateTime}</td>
					<td>${sub.tran_yn}</td>
					<td>${sub.currency}</td>
					<td><a href="javascript:cancelOrder('${currency}');">${sub.currency}</a></td>
				</tr>
			</c:forEach>
	</table>

	<input type="button" value="Order생성" onclick="registerOrderReq()">

</body>

</html>