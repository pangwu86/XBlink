package org;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.xblink.core.doc.DocTestAll;
import org.xblink.core.serial.SerialTestAll;
import org.xblink.util.UtilTestAll;

@RunWith(Suite.class)
@Suite.SuiteClasses({ UtilTestAll.class, DocTestAll.class, SerialTestAll.class })
public class XBlinkTestAll {
}
