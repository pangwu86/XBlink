package org.xblink;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.xblink.adapter.AdapterFactoryTest;
import org.xblink.collection.CollectionTestAll;
import org.xblink.demo.DemoTestAll;
import org.xblink.loop.LoopTestAll;
import org.xblink.util.ClassScannerTest;
import org.xblinkvsxstream.SchoolTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AdapterFactoryTest.class, ClassScannerTest.class, CollectionTestAll.class,
		LoopTestAll.class, DemoTestAll.class, SchoolTest.class })
public class TestAll {
}
