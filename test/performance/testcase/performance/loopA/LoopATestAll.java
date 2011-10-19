package performance.testcase.performance.loopA;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ LoopA1.class, LoopA1.class, LoopA10.class, LoopA100.class, LoopA1000.class, LoopA10000.class })
public class LoopATestAll {
}
