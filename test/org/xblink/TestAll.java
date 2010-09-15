package org.xblink;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.xblink.adapter.AdapterFactoryTest;
import org.xblink.collection.CollectionTestAll;
import org.xblink.demo.DemoTestAll;
import org.xblink.loop.LoopTestAll;
import org.xblink.performance.PerformanceTestAll;
import org.xblink.util.ClassScannerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ PerformanceTestAll.class, AdapterFactoryTest.class, ClassScannerTest.class,
		CollectionTestAll.class, LoopTestAll.class, DemoTestAll.class })
public class TestAll {
}
