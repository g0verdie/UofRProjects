README
Dan Scarafoni
dscarafo@u.rochester.edu

Report:
The Main class is the driver class for the project. It takes in a file of intersections and roads, and 2 intersections as input. It passes the information about the
roads and intersections to a Graph class, which sorts the data into hash tables, and a GUI class, which draws the map.

The Graph class contains two subclasses- an edge (road) class and a vertex (intersection) class. Each edge contained an id string, a distance double, references to the two connecting
vertecies, a flagged integer (to describe whether it was part of the minimal spanning tree or shortest distance path algorithm, and a pointer to a vertex called path (used
in computing shortest distance).
The vertex class contained an id string, an x and y coordinate, a list of attatched edges, two known booleans (for use with the minimum spanning tree and shortest distance algorithm,
a distance variable (useful for the shortest distance algorithm), and a pointer to an edge called path (also used in the shortest distanec algorithm).
An object oriented approach was used for vertecies and edges because it was more conducive to the drawing of the graph in the GUI class. Because the GUI had to access
and draw each edge and vertex, and each vertex and edge could have multiple states (part of the mimimum spanning tree, part of the shortest path algorithm, etc...) it was easier
to make them their own dedicated classes. 
A hash table was used over an adjascency matrix or array list because it helped to conserve memory and was much more efficient. The graph was very sparse, there were tens of thousands of intersections, few of which
had more than 2 connected edges. An adjascency matrix would have used a lot of memory, most of which would just be wasted. An array list would have helped with the memory problem, but part
of the insertion process involved assigning pointers to edges of each array. As such, it was imperative that each edge and vertex could be accessed quickly. The hash table solved this problem.
The "into" method took in a string[], created either a vertex to an edge from the data, and inserted it into the corresponding hash table.
The "minTree" method created a mimimum spanning tree and marked the edges used by changing the "flagged" variable to 1. It used a greedy algorithm to work. It marked 
the current node as known, and added all adjascent unknown nodes to a heap, sorted by distance of the connecting roads. It then polled from the heap and repeated the algorithm
from that next point.
The "shortestDistance" algorithm implemented Dijkstra's algorithm in order to find the shortest path between two points. It was based off of the "minTree" algorithm.
It differed in that it did not simply flag every edge it came across. It set the path of each vertex and edge to the previous edge/vertex in the path, this was assigned
by distance. It fed the end point into another algorithm called "paintShort" which ran from path to path in reverse order, flagging all nodes and edges as 2 to indicate
that they were used in the shortest distance.

The GUI class contained the methods necessary for drawing the visualization of the map. It drew all vertecies as filled ovals at their x and y coordinates. It drew edges
as a line between two points. Depending on how the edges were flagged (as part of the minimum spanning tree, shortest path between the 2 points, or neither) it colored 
them differently

Runtime:
The most complex method in my program is the shortest distance method. The main method includes a recursive call systematically looks at every
possible adjascent edge, inserts every possible edge at a given vertex holder into a heap, and chooses the minimum value from the heap for 
the next point. Thus, it runs in Elog(V) time, where E is the number of Edges, and V is the number of Vertecies of the graph.