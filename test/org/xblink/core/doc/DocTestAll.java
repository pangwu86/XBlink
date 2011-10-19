package org.xblink.core.doc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.xblink.core.doc.impl.XPP3WriterTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DocWorkerFactoryTest.class, XPP3WriterTest.class })
public class DocTestAll {
}
