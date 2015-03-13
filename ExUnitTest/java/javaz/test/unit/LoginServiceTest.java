package javaz.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import javaz.security.service.LoginService;
import javaz.security.service.impl.LoginServiceImpl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class LoginServiceTest {
	
    @BeforeClass
    public static void oneTimeSetUp() {
        // one-time initialization code   
    	System.out.println("@BeforeClass - oneTimeSetUp");
    	
    	LoginService.user.put("admin", "admin");
    	LoginService.user.put("guest", "");
    }
 
    @AfterClass
    public static void oneTimeTearDown() {
        // one-time cleanup code
    	System.out.println("@AfterClass - oneTimeTearDown");
    	
    	LoginService.user.clear();
    }
 
    @Before
    public void setUp() {
        System.out.println("@Before - setUp");
    }
 
    @After
    public void tearDown() {
        System.out.println("@After - tearDown");
    }

    @Test
    public void testLoginEmpty() {
        System.out.println("@Test - testLoginEmpty");
    	LoginService loginService = new LoginServiceImpl();
    	assertNull(null, loginService.login("", ""));
    }

    @Test
    public void testLoginAdmin() {
        System.out.println("@Test - testLoginAdmin");
    	LoginService loginService = new LoginServiceImpl();
        assertEquals("admin", loginService.login("admin", "admin"));
    }
    
    @Test
//    @Ignore
    public void testChangePasswordEmpty() {
        System.out.println("@Test - testChangePasswordEmpty");
    	LoginService loginService = new LoginServiceImpl();
        assertFalse("", loginService.changePassword("", "", ""));
    }

    @Test
//    @Ignore
    public void testChangePasswordSuccess() {
        System.out.println("@Test - testChangePasswordEmpty");
    	LoginService loginService = new LoginServiceImpl();
        assertTrue("guest", loginService.changePassword("guest", "", "guest"));
    }

}
