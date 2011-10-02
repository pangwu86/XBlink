package org.xblink.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.xblink.core.convert.ConvertTestAll;
import org.xblink.core.doc.DocTestAll;
import org.xblink.core.path.PathTestAll;
import org.xblink.core.serial.SerialTestAll;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DocTestAll.class, PathTestAll.class, SerialTestAll.class, ConvertTestAll.class })
public class CoreTestAll {
}