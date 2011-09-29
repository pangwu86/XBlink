package performance.testcase.performance.do10;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ Do10.class, Do100.class, Do1000.class, Do10000.class, Do10NoCache.class, Do100NoCache.class,
		Do1000NoCache.class, Do10000NoCache.class, })
public class DoTestAll {

}
