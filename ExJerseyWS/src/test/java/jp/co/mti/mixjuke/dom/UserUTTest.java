package jp.co.mti.mixjuke.dom;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import jp.co.mti.mixjuke.ws.request.MemberStatus;
import jp.co.mti.mixjuke.ws.request.UserInput;
import jp.co.mti.mixjuke.ws.response.UserProfileInfo;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: nhphuoc Date: 11/19/13 Time: 11:29 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml",
        "/database/hibernate.xml" })
public class UserUTTest extends AbstractDom {
    @Test
    public void testBirthdayFormat() {
        Date date = new Date();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        String birthday = dateformat.format(date);

        User user = new User();
        user.setId("00001");
        user.setStatus((short) MemberStatus.FREE.getStatus());
        user.setBirthday(date);
        UserProfileInfo userInfo = user.toUserProfileInfo();
        assertTrue(userInfo.getBirthday().equals(birthday));
    }

    @Test
    public void testParserUserInput() {
        String userString1 = "{\"user\":{\"gender\":0}}";
        String userString2 = "{\"user\":{\"gender\":0,\"share_profile\":false}}";
        String userString3 = "{\"user\":{\"gender\":0,\"share_profile\":false,\"birthday\":\"19800101\"}}";
        String userString4 = "{\"user\":{\"gender\":0,\"share_profile\":false,\"birthday\":\"19800101\",\"name\":\"mixjuke2\"}}";
        String userString = "{\"user\":{\"gender\":0,\"share_profile\":false,\"birthday\":\"19800101\",\"name\":\"mixjuke2\",\"avator_url\":\"http://vapejuicebar.co.uk/images/appl.jpg\"}}";
        ObjectMapper om = new ObjectMapper();
        UserInput user = null;
        try {
            user = om.readValue(userString1, UserInput.class);
            user = om.readValue(userString2, UserInput.class);
            user = om.readValue(userString3, UserInput.class);
            user = om.readValue(userString4, UserInput.class);
            user = om.readValue(userString, UserInput.class);
        } catch (Exception e1) {
            assertTrue(false);
        }
        Assert.assertEquals(user.getUser().getName(), "mixjuke2");
        assertTrue(true);
    }
}
