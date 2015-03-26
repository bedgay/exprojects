package jp.co.mti.mixjuke;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
/**
 * Unit testing for ServiceAuthenticationImpl
 * User: naminh
 * Date: 10/24/13
 * Time: 1:53 PM
 */
public class AkamaiAuthCallbackWebServiceIT extends AbstractMixJukeJersey {
    private static final Logger LOGGER = LogManager.getLogger(AkamaiAuthCallbackWebServiceIT.class
            .getName());
    /**
     * Call test service to prepare data
     */
    @Before
    public void preexecute() {
        WebResource webResource = resource();
        webResource.path("/test").queryParam("caseId", "19").queryParam("act", "0")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(String.class);
    }

    /**
     * Test 19. Akamai Authentication API
     * Pre-condition: User with uid=null
     * Case: Response fail
     */
    @Test
    public void akamaiAuthenticationCase01() {
        LOGGER.info("Testcase:akamaiAuthenticationCase01");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		//.queryParam("uid", "")
                .queryParam("dev_unique_id", "DEV001")
                .queryParam("sv","http")
                .queryParam("prod_id","DEV001PI")                
                .queryParam("device_nm", "DEV001MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "0")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(304, response.getStatus());
    }

    /**
     * Test 19. Akamai Authentication API
     * Pre-condition: User with devid=null
     * Case: Response fail
     */
    @Test
    public void akamaiAuthenticationCase02() {
        LOGGER.info("Testcase:akamaiAuthenticationCase02");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		.queryParam("uid", "01117")
                //.queryParam("dev_unique_id", "")
                .queryParam("sv","http")
                .queryParam("prod_id","DEV001PI")                
                .queryParam("device_nm", "DEV001MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "0")
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(304, response.getStatus());
    }

    /**
     * Test 19. Akamai Authentication API
     * Pre-condition: User with sv=null
     * Case: Response fail
     */
    @Test
    public void akamaiAuthenticationCase03() {
        LOGGER.info("Testcase:akamaiAuthenticationCase03");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		.queryParam("uid", "01117")
                .queryParam("dev_unique_id", "DEV001")
                //.queryParam("sv","")
                .queryParam("prod_id","DEV001PI")                
                .queryParam("device_nm", "DEV001MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "0")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(Response.Status.NOT_MODIFIED.getStatusCode(), response.getStatus());
    }

    /**
     * Test 19. Akamai Authentication API
     * Pre-condition: User with pid=null
     * Case: Response fail
     */
    @Test
    public void akamaiAuthenticationCase04() {
        LOGGER.info("Testcase:akamaiAuthenticationCase04");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth").queryParam("prod_id", "")
        		.queryParam("uid", "01117")
                .queryParam("dev_unique_id", "DEV001")
                .queryParam("sv","http")
                //.queryParam("prod_id","")                
                .queryParam("device_nm", "DEV001MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "0")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(Response.Status.NOT_MODIFIED.getStatusCode(), response.getStatus());
    }

    /**
     * Test 19. Akamai Authentication API
     * Pre-condition: User with uid=not in db
     * Case: Response fail
     */
    @Test
    public void akamaiAuthenticationCase05() {
        LOGGER.info("Testcase:akamaiAuthenticationCase05");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		.queryParam("uid", "InvalidUser")
                .queryParam("dev_unique_id", "DEV001")
                .queryParam("sv","http")
                .queryParam("prod_id","DEV001PI")                
                .queryParam("device_nm", "DEV001MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "0")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(Response.Status.NOT_MODIFIED.getStatusCode(), response.getStatus());
    }

    /**
     * Test 19. Akamai Authentication API
     * Pre-condition: User with uid=in db,devid= not in db
     * Case: Response fail
     */
    @Test
    public void akamaiAuthenticationCase06() {
        LOGGER.info("Testcase:akamaiAuthenticationCase06");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		.queryParam("uid", "01117")
                .queryParam("dev_unique_id", "InvalidDevice")
                .queryParam("sv","http")
                .queryParam("prod_id","DEV001PI")                
                .queryParam("device_nm", "DEV001MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "0")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(Response.Status.NOT_MODIFIED.getStatusCode(), response.getStatus());
    }

    /**
     * Test 19. Akamai Authentication API
     * Pre-condition: User with uid=in db, pid=song is not in db
     * Case: Response fail
     */
    @Test
    public void akamaiAuthenticationCase07() {
        LOGGER.info("Testcase:akamaiAuthenticationCase07");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		.queryParam("uid", "01117")
                .queryParam("dev_unique_id", "DEV001")
                .queryParam("sv","http")
                .queryParam("prod_id","InvalidProduct")                
                .queryParam("device_nm", "DEV001MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "0")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(Response.Status.NOT_MODIFIED.getStatusCode(), response.getStatus());
    }

    /**
     * Test 19. Akamai Authentication API
     * Pre-condition: All are correct but play_session_id is null
     * Case: Response fail
     */
    @Test
    public void akamaiAuthenticationCase08() {
        LOGGER.info("Testcase:akamaiAuthenticationCase08");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		.queryParam("uid", "01117")
                .queryParam("dev_unique_id", "DEV001")
                .queryParam("sv","http")
                .queryParam("prod_id","DEV001PI")                
                .queryParam("device_nm", "DEV001MD")
                .queryParam("streaming_dt", "20131205153637")
                //.queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "0")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(Response.Status.NOT_MODIFIED.getStatusCode(), response.getStatus());
    }
    
    /**
     * Test 19. Akamai Authentication API
     * Pre-condition: All are correct but play_type is null
     * Case: Response fail
     */
    @Test
    public void akamaiAuthenticationCase09() {
        LOGGER.info("Testcase:akamaiAuthenticationCase09");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		.queryParam("uid", "01117")
                .queryParam("dev_unique_id", "DEV001")
                .queryParam("sv","http")
                .queryParam("prod_id","DEV001PI")                
                .queryParam("device_nm", "DEV001MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                //.queryParam("play_type", "0")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(Response.Status.NOT_MODIFIED.getStatusCode(), response.getStatus());
    }

    /**
     * Test 19. Akamai Authentication API
     * Pre-condition: All are correct but play_type isn't a number
     * Case: Response fail
     */
    @Test
    public void akamaiAuthenticationCase10() {
        LOGGER.info("Testcase:akamaiAuthenticationCase10");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		.queryParam("uid", "01117")
                .queryParam("dev_unique_id", "DEV001")
                .queryParam("sv","http")
                .queryParam("prod_id","DEV001PI")                
                .queryParam("device_nm", "DEV001MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "Not a number")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(Response.Status.NOT_MODIFIED.getStatusCode(), response.getStatus());
    }

    /**
     * Test 19. Akamai Authentication API
     * Pre-condition: All are correct but rule1 is wrong
     * Case: Response fail
     */
    @Test
    public void akamaiAuthenticationCase11() {
        LOGGER.info("Testcase:akamaiAuthenticationCase11");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		.queryParam("uid", "02227")
                .queryParam("dev_unique_id", "DEV002")
                .queryParam("sv","http")
                .queryParam("prod_id","DEV002PI")                
                .queryParam("device_nm", "DEV002MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "1")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(Response.Status.NOT_MODIFIED.getStatusCode(), response.getStatus());
    }    

    /**
     * Test 19. Akamai Authentication API
     * Pre-condition: All are correct but rule2 is wrong
     * Case: Response fail
     */
    @Test
    public void akamaiAuthenticationCase12() {
        LOGGER.info("Testcase:akamaiAuthenticationCase12");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		.queryParam("uid", "03337")
                .queryParam("dev_unique_id", "DEV003")
                .queryParam("sv","http")
                .queryParam("prod_id","DEV003PI")                
                .queryParam("device_nm", "DEV003MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "1")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(Response.Status.NOT_MODIFIED.getStatusCode(), response.getStatus());
    }   

    /**
     * Test 19. Akamai Authentication API
     * Pre-condition: All are correct but rule3 is wrong
     * Case: Response fail
     */
    @Test
    public void akamaiAuthenticationCase13() {
        LOGGER.info("Testcase:akamaiAuthenticationCase13");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		.queryParam("uid", "04447")
                .queryParam("dev_unique_id", "DEV004")
                .queryParam("sv","http")
                .queryParam("prod_id","DEV004PI")                
                .queryParam("device_nm", "DEV004MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "1")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(Response.Status.NOT_MODIFIED.getStatusCode(), response.getStatus());
    }   
    
    /**
     * Test 19. Akamai Authentication API
     * Pre-condition: All are correct
     * Case: Response OK
     */
    @Test
    public void akamaiAuthenticationCase14() {
        LOGGER.info("Testcase:akamaiAuthenticationCase14");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		.queryParam("uid", "01117")
                .queryParam("dev_unique_id", "DEV001")
                .queryParam("sv","http")
                .queryParam("prod_id","DEV001PI")                
                .queryParam("device_nm", "DEV001MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "1")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    /**
     * Test 15. Akamai Authentication API
     * Pre-condition: All are correct
     * Case: Response OK
     */
    @Test
    public void akamaiAuthenticationCase15WithSeedArtist() {
        LOGGER.info("Testcase:akamaiAuthenticationCase15WithSeedArtist");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		.queryParam("uid", "01117")
                .queryParam("dev_unique_id", "DEV001")
                .queryParam("sv","http")
                .queryParam("prod_id","DEV001PI")                
                .queryParam("device_nm", "DEV001MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "1")
                .queryParam("seed_artist_id", "191111")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    /**
     * Test 15. Akamai Authentication API
     * Pre-condition: All are correct
     * Case: Response OK
     */
    @Test
    public void akamaiAuthenticationCase16WithSeedGenre() {
        LOGGER.info("Testcase:akamaiAuthenticationCase16WithSeedGenre");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		.queryParam("uid", "01117")
                .queryParam("dev_unique_id", "DEV001")
                .queryParam("sv","http")
                .queryParam("prod_id","DEV001PI")                
                .queryParam("device_nm", "DEV001MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "1")
                .queryParam("seed_genre_id", "1922222")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    /**
     * Test 15. Akamai Authentication API
     * Pre-condition: All are correct
     * Case: Response fail
     */
    @Test
    public void akamaiAuthenticationCase17WithCanceledMember() {
        LOGGER.info("akamaiAuthenticationCase17WithCanceledMember");
        WebResource webResource = resource();
        ClientResponse response;
        response = webResource.path("/service/auth")
        		.queryParam("uid", "90009")
                .queryParam("dev_unique_id", "DEV001")
                .queryParam("sv","http")
                .queryParam("prod_id","DEV001PI")                
                .queryParam("device_nm", "DEV001MD")
                .queryParam("streaming_dt", "20131205153637")
                .queryParam("play_session_id", "SID0123456789")
                .queryParam("play_type", "1")
                .queryParam("seed_genre_id", "1922222")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        Assert.assertEquals(Response.Status.NOT_MODIFIED.getStatusCode(), response.getStatus());
    }

    /**
     * Clean data after testing using test service
     */
    @After
    public void postexecute() {
        WebResource webResource = resource();
        webResource.path("/test").queryParam("caseId", "19").queryParam("act", "1")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(String.class);
    }

}
