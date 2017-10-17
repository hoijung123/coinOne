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

    function cancelOrder(currency, orderId, qty, price, type) {
        if (!confirm("Order를 삭제하시겠습니까?")) {
            return;
        }
        var loc = "cancelOrder?currency=" + currency + "&orderId=" + orderId + "&qty=" + qty + "&price=" + price + "&type=" + type;
        location.href = loc;
    }

    function getDtl() {
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

    function  saveDtl() {
        if (!confirm("저장하시겠습니까?"))
        {
            return;
        }
        saveTranConfig.submit();
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
            <h1>TranConfig</h1>

            <input type="button" value="Order생성" onclick="registerOrderReq()">

            <table id="list4"></table>

            <form name="saveTranConfig" method="post" action="saveTranConfig">
            <table id="tbDtl" tabindex="0" role="presentation" aria-multiselectable="true" aria-labelledby="gbox_list4"
                   class="ui-jqgrid-btable ui-common-table" style="width: 600px;">
                <tbody>
                <tr class="jqgfirstrow" role="row">
                    <td role="gridcell" style="height:0px;width:30px;">currency</td>
                    <td role="gridcell" style="height:0px;width:30px;"><input type="text" name="currency" id="currency"></td>
                </tr>
                <tr class="jqgfirstrow" role="row">
                    <td role="gridcell" style="height:0px;width:30px;">tran_type</td>
                    <td role="gridcell" style="height:0px;width:30px;"><input type="text" name="tran_type" id="tran_type"></td>
                </tr>
                <tr class="jqgfirstrow" role="row">
                    <td role="gridcell" style="height:0px;width:30px;">tran_yn</td>
                    <td role="gridcell" style="height:0px;width:30px;"><input type="text" name="tran_yn" id="tran_yn"></td>
                </tr>
                <tr class="jqgfirstrow" role="row">
                    <td colspan="2"><input type="button" value="Save" onclick="saveDtl()"></td>
                </tr>
                </tbody>
            </table>
            </form>
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
</body>


<script type="text/javascript">
    $(function () {
        var lastsel2;
        // 변수 선언
        var i, max, myData, grid = $("#list4");

        // grid 설정
        grid.jqGrid({
            datatype: "local",
            height: 'auto',
            colNames: ['currency', 'tran_type', 'tran_yn', '취소'],
            colModel: [
                {name: 'currency', index: 'currency', width: 150, sorttype: "int"},
                {name: 'tran_type', index: 'tran_type', width: 50, align: "center", sorttype: "false"},
                {name: 'tran_yn', index: 'tran_yn', align: "right", width: 50},
                {name: 'currency', index: 'currency', width: 50, align: "right", sortable: false}
            ],
            multiselect: false,


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
            },

            onSelectRow: function (rowid) {
                if (rowid && rowid !== lastsel2) {
                    var cm = $("#list4").jqGrid('getGridParam', 'colModel');
                    var currency = $('#list4').jqGrid('getCell', rowid, 'currency');
                    var tran_yn = $('#list4').jqGrid('getCell', rowid, 'tran_yn');
                    var tran_type = $('#list4').jqGrid('getCell', rowid, 'tran_type');
                    $('input[name="currency"]').val(currency);
                    $('input[name="tran_yn"]').val(tran_yn);
                    $('input[name="tran_type"]').val(tran_type);
                    lastsel2 = rowid;
                }
            },
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