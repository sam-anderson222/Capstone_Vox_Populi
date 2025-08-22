import React, { useRef, useEffect } from "react";
import * as d3 from "d3";

const PieChart = ({ data, size = 300 }) => {
  const ref = useRef();

  // Only render the label if it takes up more than 10% of pie 
  function doShowOptionLabel(d) {
        let totalNumberOfVotes = 0
        data.map((d) => {
            totalNumberOfVotes += d.optionVotes
        })

        if (d.optionVotes / totalNumberOfVotes <= 0.10) {
            return "";
        } else {
            return d.optionName;
        }
  }

  useEffect(() => {
    const svg = d3.select(ref.current); // gets a D3 object pointing to the svg that we return
    svg.selectAll("*").remove(); // Delete old drawings.

    const radius = size / 2.1;

    const color = d3.scaleOrdinal(d3.schemeCategory10);

    const pie = d3.pie().value(d => d.optionVotes).padAngle(0.02); // We want the size of the pie piece to be based on optionVotes.
    const arcs = pie(data); // Creates an array of arc that represent a slice of the pie graph

    const arc = d3.arc() // Allows use to bind each arc to a path which we use in g.selectAll("path")
      .innerRadius(5) // We want it to be a pie not a donut.
      .outerRadius(radius); // What is the radius we want for the pie?

    const g = svg.append("g") // We create a group for our SVG element (this will hold the pie graph)
      .attr("transform", `translate(${size / 2}, ${size / 2})`); // We then center the group to the middle of the SVG element.

    // This draws the arcs
    g.selectAll("path") // This is just magic... Just copy and paste. 
        .data(arcs) // Binds the arcs array
        .join("path") 
        .attr("d", arc) // Call the arc shape generator on each item from the data.
        .attr("fill", (d, i) => (color(i % 10)))  // Tells how to render each arc of the pie. Made mod 10 so colors repeat in an loop.
        .attr("stroke", "#000")  // Border color
        .attr("stroke-width", 1) // Border width

    // This adds labels for each arc
    g.selectAll("text")
        .data(arcs)
        .join("text")
        .attr("transform", d => `translate(${arc.centroid(d)})`) // Put the text label in the middle of the pie piece
        .attr("text-anchor", "middle")
        .attr("font-size", "0.7rem")
        .attr("font-weight", "bold")
        .text(d => doShowOptionLabel(d.data));
  }, [data, size]);

  return (
    <svg
      ref={ref} // sets the reference for useRef so we can get in the SVG and use it in our pieChart function.
      viewBox={`0 0 ${size} ${size}`} // defines the units for the svg to use
      preserveAspectRatio="xMidYMid meet" // Ensure the aspect ratio doesn't change
      style={{ width: "60%", height: "auto" }} // Makes the svg responsive to the container's width
    ></svg>
  );
};

export default PieChart;