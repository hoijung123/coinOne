<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/coinone.css'/>">
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/css/jquery-ui.css'/>">
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/css/ui.jqgrid.css'/>">
<script src="<c:url value='/resources/js/jquery-1.11.0.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/i18n/grid.locale-kr.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/jquery.jqGrid.min.js'/>" type="text/javascript"></script>
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
	<h1>OrdersOpen</h1>
	<select name="currency" id="currency"
		onchange="change(this.value);">
		<option value="xrp"
			<c:if test="${currency eq 'xrp'}">selected</c:if> >xrp</option>
		<option value="bch"
				<c:if test="${currency eq 'bch'}">selected</c:if> >bch</option>
	</select> <br>
	qty : <input type="number" size=5 name="qty" id="qty">
	reqCnt : <input type="number" size= 2 name="reqCnt" id="reqCnt">
	<input type="button" value="Order생성" onclick="registerOrderReq()">

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


	<script type="text/javascript">

        $(function(){

            // 변수 선언

            var i, max, myData, grid = $("#list4");



            // grid 설정

            grid.jqGrid({

                datatype: "local",

                height: 'auto',

                colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'],

                colModel:[

                    {name:'id',index:'id', width:60, sorttype:"int"},

                    {name:'invdate',index:'invdate', width:90, sorttype:"date"},

                    {name:'name',index:'name', width:100},

                    {name:'amount',index:'amount', width:80, align:"right",sorttype:"float"},

                    {name:'tax',index:'tax', width:80, align:"right",sorttype:"float"},

                    {name:'total',index:'total', width:80,align:"right",sorttype:"float"},

                    {name:'note',index:'note', width:150, sortable:false}

                ],

                multiselect: true,

                caption: "Manipulating Array Data"

            });



            // 로컬 데이터

            myData = [

                {id:"1",invdate:"2007-10-01",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},

                {id:"2",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},

                {id:"3",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},

                {id:"4",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},

                {id:"5",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},

                {id:"6",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},

                {id:"7",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},

                {id:"8",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},

                {id:"9",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}

            ];



            // 데이터 추가

            for( i=0, max = myData.length ; i<=max ; i++ ){

                grid.jqGrid('addRowData', i+1, myData[i]);

            }

        });

	</script>

	<table id="list4"></table>
</body>

</html>