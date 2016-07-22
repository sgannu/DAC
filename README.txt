About:
	Problem one: Trains
 
	The local commuter railroad services a number of towns in Kiwiland.  Because of monetary concerns, all of the tracks are 'one-way.'  
	That is, a route from Kaitaia to Invercargill does not imply the existence of a route from Invercargill to Kaitaia.  In fact, even if 
	both of these routes do happen to exist, they are distinct and are not necessarily the same distance!
 
	The purpose of this problem is to help the railroad provide its customers with information about the routes.  In particular, you will 
	compute the distance along a certain route, the number of different routes between two towns, and the shortest route between two towns.
 
	Input:  "input.txt"
		A directed graph where a node represents a town and an edge represents a route between two towns.  The weighting of the edge 
		represents the distance between the two towns.  A given route will never appear more than once, and for a given route, the starting 
		and ending town will not be the same town.
		eg: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
 
	Output:
		RailRoadSystemTest.java : contains the JUnits that will run the test scenarios and print results


Prerequisites:
	Java 1.8
	Maven 3.3 or later
	
Running application:
	1. in Linux/mac Shell or Windows CMD change the directory to application root
	2. run the below command to build and start the application.
		mvn package
	