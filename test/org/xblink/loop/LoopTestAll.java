package org.xblink.loop;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ LoopTest1.class, LoopTest2_Array.class, LoopTest2_List.class,
		LoopTest2_Set.class, LoopTest3.class })
public class LoopTestAll {}
