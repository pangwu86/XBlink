package bug;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import bug.issue9.Issue9Test;

@RunWith(Suite.class)
@Suite.SuiteClasses({ Issue9Test.class })
public class BugTestAll {
}
