<!DOCTYPE html>
<meta charset="utf-8">
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
<body>

	<!-- load the d3.js library -->
	<script src="http://d3js.org/d3.v3.min.js"></script>

	<script>

// Set the dimensions of the canvas / graph
var margin = {top: 30, right: 20, bottom: 30, left: 50},
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

var url = document.location.protocol+"//"+document.location.host +"/coinone/jsonTest";
	
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
}, 1000); 

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

</script>
</body>

