<!DOCTYPE html>
<meta charset="utf-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style> /* set the CSS */
body {
	font: 12px Arial;
}

path {
	stroke: steelblue;
	stroke-width: 2;
	fill: none;
}

.axis path, .axis line {
	fill: none;
	stroke: grey;
	stroke-width: 1;
	shape-rendering: crispEdges;
}
</style>
<script src="/coinone/resources/js/jquery-3.2.1.js"></script>
<script>
	function change(currency) {
		var loc = "lineChart?currency=" + currency;
		location.href = loc;
	}
</script>
<body>

	<select name="currency" id="currency" onchange="change(this.value);">
		<option value="bch"
			<c:if test="${currency eq 'bch'}" >selected</c:if> >bch</option>
		<option value="btc" <c:if test="${currency eq 'btc'}" >selected</c:if> >btc</option>		
		<option value="eth" <c:if test="${currency eq 'eth'}" >selected</c:if> >eth</option>
		<option value="eth_krw" <c:if test="${currency eq 'eth_krw'}" >selected</c:if> >eth_krw</option>
		<option value="etc" <c:if test="${currency eq 'etc'}" >selected</c:if> >etc</option>
		<option value="xrp" <c:if test="${currency eq 'xrp'}" >selected</c:if> >xrp</option>		
		<option value="qtum" <c:if test="${currency eq 'qtum'}" >selected</c:if> >qtum</option>
	</select> &nbsp; &nbsp;<label id="last"><fmt:formatNumber value="${last}" pattern="###,###" /></label>
	
	<br>

	<!-- load the d3.js library -->
	<script src="http://d3js.org/d3.v3.min.js"></script>

	<script>
	
	

// Set the dimensions of the canvas / graph
var margin = {top: 30, right: 20, bottom: 30, left: 60},
    width = 600 - margin.left - margin.right,
    height = 270 - margin.top - margin.bottom;

// Parse the date / time
var parseDate = d3.time.format("%Y-%m-%d %H:%M:%S").parse;

// Set the ranges
var x = d3.time.scale().range([0, width]);
var y = d3.scale.linear().range([height, 0]);

// Define the axes
var xAxis = d3.svg.axis().scale(x)
    .orient("bottom").ticks(5);

var yAxis = d3.svg.axis().scale(y)
    .orient("left").ticks(5);

// Define the line
var valueline = d3.svg.line()
    .x(function(d) { return x(d.date); })
    .y(function(d) { return y(d.close); });
    
// Adds the svg canvas
var svg = d3.select("body")
    .append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
    .append("g")
        .attr("transform", 
              "translate(" + margin.left + "," + margin.top + ")");

var app = "coinone"; 
if ("eth_krw" == "${currency}")
{
		app = "korbit"; 
} 
var url = document.location.protocol+"//"+document.location.host +"/" + app +"/jsonTickerList?currency=${currency}";
	
// Get the data
d3.json(url, function (error, json) {	
	json.forEach(function(d) {
        d.date = parseDate(d.dateTime);
        d.close = +d.last;
    });

    // Scale the range of the data
    x.domain(d3.extent(json, function(d) { return d.date; }));
    y.domain([d3.min(json, function(d) { return d.close; }) - d3.min(json, function(d) { return d.close; }) * 0.001, d3.max(json, function(d) { return d.close; })]);

    // Add the valueline path.
    svg.append("path")
        .attr("class", "line")
        .attr("d", valueline(json));

    // Add the X Axis
    svg.append("g")
        .attr("class", "x axis")
        .attr("transform", "translate(0," + height + ")")
        .call(xAxis);

    // Add the Y Axis
    svg.append("g")
        .attr("class", "y axis")
        .call(yAxis);

});


var inter = setInterval(function() {
    updateData();
    updateLast();
}, 5000); 

//** Update data section (Called from the onclick)
function updateData() {
// Get the data again
	d3.json(url, function (error, json) {	
		json.forEach(function(d) {
	        d.date = parseDate(d.dateTime);
	        d.close = +d.last;
	    });

	    // Scale the range of the data
	    x.domain(d3.extent(json, function(d) { return d.date; }));
	    y.domain([d3.min(json, function(d) { return d.close; }) - d3.min(json, function(d) { return d.close; }) * 0.001, d3.max(json, function(d) { return d.close; })]);

	    var svg = d3.select("body").transition();
	    
	    svg.select(".line")   // change the line
        .duration(750)
        .attr("d", valueline(json));
    svg.select(".x.axis") // change the x axis
        .duration(750)
        .call(xAxis);
    svg.select(".y.axis") // change the y axis
        .duration(750)
        .call(yAxis);

	});
}


function updateLast() {
	var app = "coinone"; 
	if ("eth_krw" == "${currency}")
	{
		app = "korbit"; 
	} 
	var url = document.location.protocol+"//"+document.location.host +"/"+ app +"/jsonTicker"; 
    var params="currency=${currency}";  
  
    $.ajax({      
        type:"GET",  
        url:url,  
        dataType:"json",    
        data:params,      
        success:function(args){   
        	console.log(args.last);
            $("#last").html(comma(args.last));      
        },           
        error:function(e){  
           // alert(e.responseText);  
        }  
    });  
}

currency.focus();

function comma(num){
    var len, point, str; 
       
    num = num + ""; 
    point = num.length % 3 ;
    len = num.length; 
   
    str = num.substring(0, point); 
    while (point < len) { 
        if (str != "") str += ","; 
        str += num.substring(point, point + 3); 
        point += 3; 
    } 
     
    return str;
 
}

updateLast();
</script>
</body>

