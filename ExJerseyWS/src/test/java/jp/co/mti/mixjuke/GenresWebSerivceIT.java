/**
 * 
 */
package jp.co.mti.mixjuke;

import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.ws.response.GenreListResponse;
import jp.co.mti.mixjuke.ws.response.ResultCode;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.WebResource;

/**
 * @author Xuan Nguyen
 * 
 */
public class GenresWebSerivceIT extends AbstractMixJukeJersey {
    /**
     * Call test service to prepare data
     */
    @Before
    public void preexecute() {
        WebResource webResource = resource();
        webResource.path("/test").queryParam("caseId", "100")
                .queryParam("act", "0").accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(String.class);
    }

    /**
     * Clean data after testing using test service
     */
    @After
    public void postexecute() {
        WebResource webResource = resource();
        webResource.path("/test").queryParam("caseId", "100")
                .queryParam("act", "1").accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get(String.class);
    }

    /**
     * Test 1.GenreList API
     * <p>
     * Pre-condition: uid is null
     * <p>
     * Case: Play fail
     */
    @Test
    public void testGetGenreList101() {
        WebResource webResource = resource();
        GenreListResponse response = webResource.path("/genres").get(
                GenreListResponse.class);
        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 1.GenreList API
     * <p>
     * Pre-condition: uid is blank
     * <p>
     * Case: Play fail
     */
    @Test
    public void testGetGenreList102() {
        WebResource webResource = resource();
        GenreListResponse response = webResource.path("/genres")
                .queryParam("uid", " ").get(GenreListResponse.class);
        Assert.assertEquals(ResultCode.ERROR_IN_PARAMETER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 1.GenreList API
     * <p>
     * Pre-condition: uid is canceled
     * <p>
     * Case: Play fail
     */
    @Test
    public void testGetGenreListWithCanceledMenber() {
        WebResource webResource = resource();
        GenreListResponse response = webResource.path("/genres")
                .queryParam("uid", "90009").get(GenreListResponse.class);
        Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 1.GenreList API
     * <p>
     * Pre-condition: uid is not in DB
     * <p>
     * Case: Play fail
     */
    @Test
    public void testGetGenreList103() {
        WebResource webResource = resource();
        GenreListResponse response = webResource.path("/genres")
                .queryParam("uid", "000000").get(GenreListResponse.class);
        Assert.assertEquals(ResultCode.NOT_A_MEMBER.getResultCode(),
                response.getResult());
    }

    /**
     * Test 1.GenreList API
     * <p>
     * Pre-condition: uid is valid
     * <p>
     * Case: Play success
     */
    @Test
    public void testGetGenreList104() {
        WebResource webResource = resource();

        GenreListResponse response = webResource.path("/genres")
                .queryParam("uid", "000100").get(GenreListResponse.class);

        Assert.assertEquals(ResultCode.NORMAL.getResultCode(),
                response.getResult());
    }

}
