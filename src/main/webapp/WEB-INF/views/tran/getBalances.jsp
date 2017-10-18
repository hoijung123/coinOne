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
<script>
    function getBalances() {
        location.href = "getBalances";
    }
</script>

<html>
<head>
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
            <h1>Balances</h1>
            <input type="button" value="조회" onclick="getBalances()">
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
            colNames: ['currency', 'avail', 'balance', 'Price', 'krwBalance', 'BuyDate', '취소'],
            colModel: [
                {name: 'currency', index: 'currency', width: 150, sorttype: "int"},
                {name: 'avail', index: 'avail', width: 50, align: "right", sorttype: "false", formatter: 'integer'},
                {name: 'balance', index: 'balance', align: "right", width: 50, formatter: 'integer'},
                {name: 'last', index: 'last', width: 60, align: "right", sorttype: "float", formatter: 'integer'},
                {name: 'krwBalance', index: 'krwBalance', width: 60, align: "right", sorttype: "float", formatter: 'integer'},
                {name: 'dateTime', index: 'dateTime', width: 110, align: "center", sorttype: "float"},
                {name: 'index', index: 'index', width: 50, align: "right", sortable: false}
            ],

            formatter: {
                integer: {thousandsSeparator: ",", defaultValue: '0'}
            },

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
                    cancelOrder(currency, orderId, qty, price, type);
                }
            }
        });
        // 로컬 데이터
        myData = ${balList};
        // 데이터 추가
        for (i = 0, max = myData.length; i <= max; i++) {
            grid.jqGrid('addRowData', i + 1, myData[i]);
        }

    });

    setTimeout("getBalances()", 10000);
</script>
</html>
