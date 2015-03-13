package javaz.test.easymock;

import javaz.test.unit.LoginServiceTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)  
@SuiteClasses({ LoginServiceTest.class, StoreServiceTest.class })  
public class AllTests {

}
