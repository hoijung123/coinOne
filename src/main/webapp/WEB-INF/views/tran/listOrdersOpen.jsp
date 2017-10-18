<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/css/coinone.css'/>">
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/css/jquery-ui.css'/>">
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/css/ui.jqgrid.css'/>">
<script src="<c:url value='/resources/js/jquery-1.11.0.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/resources/js/i18n/grid.locale-kr.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    jQuery.jgrid.no_legacy_api = true;
</script>
<script src="<c:url value='/resources/js/jquery.jqGrid.min.js'/>" type="text/javascript"></script>

<html>
<head>
    <title>Home</title>
</head>
<script type="text/javascript">
    function change(currency_pair) {
        location.href = "listLimitOrders?currency=" + currency_pair;
    }

    function listLimitOrders(currency_pair) {
        location.href = "listLimitOrders?currency=" + currency_pair;
    }

    function cancelOrder(currency, orderId, qty, price, type) {
        if (!confirm("Order를 삭제하시겠습니까?")) {
            return;
        }
        var loc = "cancelOrder?currency=" + currency + "&orderId=" + orderId + "&qty=" + qty + "&price=" + price + "&type=" + type;
        location.href = loc;
    }

    function registerOrderReq() {
        if ("" == qty.value) {
            qty.focus();
            return;
        }
        if ("" == reqCnt.value) {
            reqCnt.focus();
            return;
        }
        if (!confirm("Order를 생성하시겠습니까?")) {
            return;
        }
        var loc = "registerOrderReq?currency=" + currency.value + "&qty=" + qty.value + "&reqCnt=" + reqCnt.value;
        location.href = loc;
    }
</script>

<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
<!-- 전체 레이어 시작 -->
<div id="wrap">
    <!-- header 시작 -->
    <div id="header">
        <jsp:include page="header.jsp" flush="false"/>
    </div>
    <!-- //header 끝 -->
    <!-- container 시작 -->
    <div id="container">
        <!-- 좌측메뉴 시작 -->
        <div id="leftmenu">
            <jsp:include page="menu.jsp" flush="false"/>
        </div>
        <!-- //좌측메뉴 끝 -->


        <!-- content 시작 -->
        <div id="content_pop">

            <select name="currency" id="currency"
                    onchange="change(this.value);">
                <option value="xrp"
                        <c:if test="${currency eq 'xrp'}">selected</c:if> >xrp
                </option>
                <option value="bch"
                        <c:if test="${currency eq 'bch'}">selected</c:if> >bch
                </option>
                <option value="btc"
                        <c:if test="${currency eq 'btc'}">selected</c:if> >btc
                </option>
                <option value="eth"
                        <c:if test="${currency eq 'eth'}">selected</c:if> >eth
                </option>
                <option value="etc"
                        <c:if test="${currency eq 'etc'}">selected</c:if> >etc
                </option>
                <option value="qtum"
                        <c:if test="${currency eq 'qtum'}">selected</c:if> >qtum
                </option>
            </select> <br>
            qty : <input type="number" size=5 name="qty" id="qty">
            reqCnt : <input type="number" size=2 name="reqCnt" id="reqCnt">
            <input type="button" value="Order생성" onclick="registerOrderReq()">
            <input type="button" value="조회" onclick="listLimitOrders('${currency}')">

            <table id="list4"></table>

        </div>
        <!-- //content 끝-->
    </div>
    <!-- //container 끝-->
    <!-- footer 시작 -->
    <div id="footer">
        <jsp:include page="footer.jsp" flush="false"/>
    </div>
    <!-- //footer 끝 -->
</div>
<!--// 전체 레이어 끝 -->

</body>


<script type="text/javascript">
    $(function () {

        // 변수 선언
        var i, max, myData, grid = $("#list4");

        // grid 설정
        grid.jqGrid({
            datatype: "local",
            height: 'auto',
            colNames: ['OrderId', 'Type', 'feeRate', 'Price', 'Qty', 'BuyDate', '취소'],
            colModel: [
                {name: 'orderId', index: 'orderId', width: 150, sorttype: "int"},
                {name: 'type', index: 'type', width: 50, align: "center", sorttype: "false"},
                {name: 'feeRate', index: 'feeRate', align: "right", width: 50},
                {name: 'price', index: 'price', width: 60, align: "right", sorttype: "float"},
                {name: 'qty', index: 'qty', width: 60, align: "right", sorttype: "float"},
                {name: 'dateTime', index: 'dateTime', width: 110, align: "center", sorttype: "float"},
                {name: 'index', index: 'index', width: 50, align: "right", sortable: false}
            ],
            multiselect: true,


            caption: "Manipulating Array Data",

            onCellSelect: function (rowid, index, contents, event) {
                var cm = $("#list4").jqGrid('getGridParam', 'colModel');
                if (cm[index].name == "index") {
                    var mygrid = jQuery("#list4")[0];
                    var currency = $('#list4').jqGrid('getCell', rowid, 'currency');
                    var orderId = $('#list4').jqGrid('getCell', rowid, 'orderId');
                    var qty = $('#list4').jqGrid('getCell', rowid, 'qty');
                    var price = $('#list4').jqGrid('getCell', rowid, 'price');
                    var type = $('#list4').jqGrid('getCell', rowid, 'type');
                    cancelOrder('${currency}', orderId, qty, price, type);
                }
            }
        });
        // 로컬 데이터
        myData = ${json};
        // 데이터 추가
        for (i = 0, max = myData.length; i <= max; i++) {
            grid.jqGrid('addRowData', i + 1, myData[i]);
        }

    });

</script>
</html>