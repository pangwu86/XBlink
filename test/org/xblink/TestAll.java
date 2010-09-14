package org.xblink;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.xblink.collection.CollectionTestAll;
import org.xblink.demo.DemoTestAll;
import org.xblink.loop.LoopTestAll;
import org.xblinkvsxstream.SchoolTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ CollectionTestAll.class, LoopTestAll.class, DemoTestAll.class,SchoolTest.class })
public class TestAll {}
