package com.gannu.rr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.gannu.rr.graph.Route;

public class RailRoadSystemTest {
	public static RailRoadSystem rrSystem;

	@BeforeClass
	public static void initalSetup() throws IOException {
		rrSystem = new RailRoadSystem();

		rrSystem.build("input.txt");

		assertNotNull(rrSystem.getRrGraph());
	}

	@Test
	public void testDistanceOfPath() {

		// Tests 1 - 5
		Route dist = rrSystem.getRoute("A-B-C");
		System.out.println("Distance of " + dist);
		assertEquals(dist.getDistance(), 9);

		dist = rrSystem.getRoute("A-D");
		System.out.println("Distance of " + dist);
		assertEquals(dist.getDistance(), 5);

		dist = rrSystem.getRoute("A-D-C");
		System.out.println("Distance of " + dist);
		assertEquals(dist.getDistance(), 13);

		dist = rrSystem.getRoute("A-E-B-C-D");
		System.out.println("Distance of " + dist);
		assertEquals(dist.getDistance(), 22);

		dist = rrSystem.getRoute("A,E,D");
		System.out.println("Distance of A-E-D: " + dist);
		assertEquals(dist.toString(), Route.NO_SUCH_ROUTE);
	}

	@Test
	public void testGetAllPathsWithExpression() {
		// Test 6
		System.out.println("---------------------------------------\n Routes with max of 3 stops between C-C");
		List<Route> paths = rrSystem.getAllPaths("C", "C", (Object ex) -> ((Route) ex).getNodes().size() < 5);
		paths.forEach(p -> System.out.println(p));
		assertTrue(paths.size() == 2);
		System.out.println("---------------------------------------");

		// Test 7
		System.out.println("Routes with 4 stops between A-C");
		paths = rrSystem.getAllPaths("A", "C", (Object ex) -> ((Route) ex).getNodes().size() == 5);
		paths.forEach(p -> System.out.println(p));
		assertTrue(paths.size() > 0);
		System.out.println("---------------------------------------");

		// Test 10
		System.out.println("Routes with distance < 30 between C-C");
		paths = rrSystem.getAllPaths("C", "C", (Object ex) -> ((Route) ex).getDistance() < 30);
		paths.forEach(p -> System.out.println(p));
		assertTrue(paths.size() > 0);
		System.out.println("---------------------------------------");
	}

	@Test
	public void testShortestPath() {

		// Test 8
		Route shortPath = rrSystem.shortestPath("A", "C");
		System.out.println("Shortest path between A & C :" + shortPath);
		assertEquals(shortPath.getDistance(), 9);

		// Test 9
		shortPath = rrSystem.shortestPath("B", "B");
		System.out.println("Shortest path between B & B :" + shortPath);
		assertEquals(shortPath.getDistance(), 9);
	}

}
