package org.xblink;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.xblink.core.CoreTestAll;
import org.xblink.util.UtilTestAll;

@RunWith(Suite.class)
@Suite.SuiteClasses({ UtilTestAll.class, CoreTestAll.class })
public class XBlinkTestAll {
}
