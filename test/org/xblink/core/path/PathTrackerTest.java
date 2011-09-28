package org.xblink.core.path;

import org.junit.Test;

public class PathTrackerTest {

	@Test
	public void getPath() throws Exception {

		PathTracker pathTracker = new PathTracker();

		pathTracker.push("A");
		pathTracker.push("B");
		pathTracker.push("C");

		System.out.println(pathTracker.getPath());

		pathTracker.pop();
		pathTracker.push("D");
		pathTracker.push("E");
		System.out.println(pathTracker.getPath());

		pathTracker.pop();
		pathTracker.pop();
		pathTracker.pop();
		pathTracker.push("F");
		System.out.println(pathTracker.getPath());

	}
}
