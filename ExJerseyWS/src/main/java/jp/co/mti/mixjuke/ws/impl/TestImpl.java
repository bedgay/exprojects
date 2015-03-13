package jp.co.mti.mixjuke.ws.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import jp.co.mti.mixjuke.dom.Artist;
import jp.co.mti.mixjuke.dom.Favorite;
import jp.co.mti.mixjuke.dom.PlaybackLog;
import jp.co.mti.mixjuke.dom.Song;
import jp.co.mti.mixjuke.dom.StreamAuthentication;
import jp.co.mti.mixjuke.dom.User;
import jp.co.mti.mixjuke.service.ArtistService;
import jp.co.mti.mixjuke.service.GenreService;
import jp.co.mti.mixjuke.service.PlaybackLogService;
import jp.co.mti.mixjuke.service.SongService;
import jp.co.mti.mixjuke.service.StreamAuthService;
import jp.co.mti.mixjuke.service.UserService;
import jp.co.mti.mixjuke.util.DateUtil;
import jp.co.mti.mixjuke.ws.AbstractWebService;
import jp.co.mti.mixjuke.ws.Test;
import jp.co.mti.mixjuke.ws.request.MemberStatus;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * User: naminh Date: 10/23/13 Time: 3:33 PM
 */
@Component("testWebService")
@Path("/test")
public class TestImpl extends AbstractWebService implements Test {

    private static final Logger LOGGER = LogManager.getLogger(TestImpl.class
            .getName());

    @Autowired
    private UserService userService;

    @Autowired
    private SongService songService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private GenreService genreService;
    
    @Autowired
    private PlaybackLogService playbackLogService;
    
    @Autowired
    StreamAuthService streamAuthService;

    @Value("${jdbc.driverClassName}")
    private String jdbcDriver;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String execute(@QueryParam("caseId") int caseId,
            @QueryParam("act") int act) {

        LOGGER.info("Trigger testWebservice");

        switch (caseId) {
        case 24:
            if (act == 0) {
                User user = userService.findByUid("01111");
                if (user == null) {
                    user = new User();
                    user.setUid("01111");
                    userService.save(user);
                    LOGGER.info("TEST CASE 24. Registered a new user!");
                }
                User user8 = userService.findByUid("24008");
                if (user8 == null) {
                    user8 = new User();
                    user8.setUid("24008");
                    user8.setStatus((short)MemberStatus.CANCELED.getStatus());
                    userService.save(user8);
                }
            } else {
                User user1 = userService.findByUid("24001");
                if (user1 != null) {
                    userService.delete(user1);
                }
                User user = userService.findByUid("01111");
                if (user != null) {
                    userService.delete(user);
                }
                user = userService.findByUid("01112");
                if (user != null) {
                    userService.delete(user);
                }
                user = userService.findByUid("24004");
                if (user != null) {
                    userService.delete(user);
                }
                user = userService.findByUid("24008");
                if (user != null) {
                    userService.delete(user);
                }
                LOGGER.info("TEST CASE 24. Deleted user.!");
            }
            break;
        case 25:
            if (act == 0) {
                User user = userService.findByUid("25001");
                if (user == null) {
                    user = new User();
                    user.setUid("25001");
                    userService.save(user);
                }
                User user2 = userService.findByUid("25002");
                if (user2 == null) {
                    user2 = new User();
                    user2.setUid("25002");
                    userService.save(user2);
                }
                User user3 = userService.findByUid("25003");
                if (user3 == null) {
                	user3 = new User();
                	user3.setUid("25003");
                    userService.save(user3);
                }
                User user7 = userService.findByUid("25007");
                if (user7 == null) {
                	user7 = new User();
                	user7.setUid("25007");
                	user7.setStatus((short)0);
                    userService.save(user7);
                }
                LOGGER.info("TEST CASE 25. Registered a new user!");
            } else {
                User user = userService.findByUid("25001");
                if (user != null) {
                    userService.delete(user);
                }
                User user2 = userService.findByUid("25002");
                if (user2 != null) {
                    userService.delete(user2);
                }
                User user3 = userService.findByUid("25003");
                if (user3 != null) {
                    userService.delete(user3);
                }
                User user7 = userService.findByUid("25007");
                if (user7 != null) {
                    userService.delete(user7);
                }
                LOGGER.info("TEST CASE 25. Deleted user!");
            }
            break;
        case 17:
            if (act == 0) {
                User user = new User();
                user.setUid("01115");
                user.setDevid("DEV001");
                userService.save(user);
                
                User userCanceled = userService.findByUid("90009");
                if (userCanceled == null) {
                    userCanceled = new User();
                    userCanceled.setUid("90009");
                    userCanceled.setStatus((short)MemberStatus.CANCELED.getStatus());
                    userService.save(userCanceled);
                }
                LOGGER.info("TEST CASE 17. Registered a new user!");
            } else {
                User user = userService.findByUid("01115");
                if (user != null) {
                    userService.delete(user);
                }
                
                User userCanceled = userService.findByUid("90009");
                if (userCanceled != null) {
                    userService.delete(userCanceled);
                }
                LOGGER.info("TEST CASE 17. Deleted user!");
            }
            break;
        case 18:
            if (act == 0) {
                User user = new User();
                user.setUid("01118");
                user.setDevid("DEV001");
                user.setFreeSubscribeDatetime(new Date());
                user.setStatus((short)1);
                userService.save(user);
                LOGGER.info("TEST CASE 18. Registered a new user!");
            } else {
                User user = userService.findByUid("01118");
                if (user != null) {
                    userService.delete(user);
                }
                // Delete the PlaybackLog is inserted from WS
                List<PlaybackLog> list = playbackLogService.findLogByUID("01118");
                if (list.size() > 0) {
                	playbackLogService.delete(list.get(0));
                }
                LOGGER.info("TEST CASE 18. Deleted user!");
                LOGGER.info("TEST CASE 18. Deleted PlaybackLog!");
            }
            break;
        case 19:
            if (act == 0) {
                User user1 = new User();
                user1.setUid("01117");
                user1.setDevid("DEV001");
                user1.setFreeSubscribeDatetime(DateUtil.addDate(new Date(), -1, Calendar.DAY_OF_YEAR));
                user1.setStatus((short)1);
                userService.save(user1);

                User user2 = new User();
                user2.setUid("02227");
                user2.setDevid("DEV002");
                user2.setFreeSubscribeDatetime(new Date());
                user2.setStatus((short)1);
                userService.save(user2);

                User user3 = new User();
                user3.setUid("03337");
                user3.setDevid("DEV003");
                user3.setFreeSubscribeDatetime(new Date());
                user3.setStatus((short)1);
                userService.save(user3);
                
                User user4 = new User();
                user4.setUid("04447");
                user4.setDevid("DEV004");
                user4.setFreeSubscribeDatetime(new Date());
                user4.setStatus((short)1);
                userService.save(user4);

                Song song1 = new Song();
                song1.setId("01111111");
                song1.setTitle("title");
                song1.setTrialLength(100);
                song1.setProductId("DEV001PI");
                songService.save(song1);

                Song song2 = new Song();
                song2.setId("02222222");
                song2.setTitle("title");
                song2.setTrialLength(100);
                song2.setProductId("DEV002PI");
                songService.save(song2);

                Song song3 = new Song();
                song3.setId("03333333");
                song3.setTitle("title");
                song3.setTrialLength(100);
                song3.setProductId("DEV003PI");
                songService.save(song3);
                
                Song song4 = new Song();
                song4.setId("04444444");
                song4.setTitle("title");
                song4.setTrialLength(100);
                song4.setProductId("DEV004PI");
                songService.save(song4);

                StreamAuthentication aut1 = new StreamAuthentication();
                aut1.setId("01111111");
                aut1.setContentReleaseDate(DateUtil.addDate(new Date(), -10, Calendar.DAY_OF_YEAR));
                aut1.setWindowDays(5);
                aut1.setAndroidReleaseStartDatetime(DateUtil.addDate(new Date(), -1, Calendar.DAY_OF_YEAR));
                aut1.setAndroidReleaseEndDatetime(DateUtil.addDate(new Date(), 10, Calendar.DAY_OF_YEAR));
                aut1.setAndroidTrialEnableFlg(true);
                streamAuthService.save(aut1);

                StreamAuthentication aut2 = new StreamAuthentication();
                aut2.setId("02222222");
                aut2.setContentReleaseDate(DateUtil.addDate(new Date(), 0, Calendar.DAY_OF_YEAR));
                aut2.setWindowDays(0);
                aut2.setAndroidReleaseStartDatetime(DateUtil.addDate(new Date(), 0, Calendar.DAY_OF_YEAR));
                aut2.setAndroidReleaseEndDatetime(DateUtil.addDate(new Date(), -1, Calendar.DAY_OF_YEAR));
                aut2.setAndroidFreeEnableFlg(true);
                streamAuthService.save(aut2);

                StreamAuthentication aut3 = new StreamAuthentication();
                aut3.setId("03333333");
                aut3.setContentReleaseDate(DateUtil.addDate(new Date(), -10, Calendar.DAY_OF_YEAR));
                aut3.setWindowDays(5);
                aut3.setAndroidReleaseStartDatetime(DateUtil.addDate(new Date(), -10, Calendar.DAY_OF_YEAR));
                aut3.setAndroidReleaseEndDatetime(DateUtil.addDate(new Date(), 10, Calendar.DAY_OF_YEAR));
                aut3.setAndroidDeliveryEnableFlg(false);
                aut3.setAndroidFreeEnableFlg(false);
                streamAuthService.save(aut3);
                
                StreamAuthentication aut4 = new StreamAuthentication();
                aut4.setId("04444444");
                aut4.setContentReleaseDate(DateUtil.addDate(new Date(), -10, Calendar.DAY_OF_YEAR));
                aut4.setWindowDays(5);
                aut4.setAndroidReleaseStartDatetime(DateUtil.addDate(new Date(), -10, Calendar.DAY_OF_YEAR));
                aut4.setAndroidReleaseEndDatetime(DateUtil.addDate(new Date(), 10, Calendar.DAY_OF_YEAR));
                aut4.setAndroidFreeEnableFlg(true);
                streamAuthService.save(aut4);
                
                LOGGER.info("TEST CASE 19. Registered a new user!");
                LOGGER.info("TEST CASE 19. Registered a new song!");
                LOGGER.info("TEST CASE 19. Registered a new log!");
                LOGGER.info("TEST CASE 19. Registered a new Streaming!");

                User userCanceled = userService.findByUid("90009");
                if (userCanceled == null) {
                    userCanceled = new User();
                    userCanceled.setUid("90009");
                    userCanceled.setStatus((short)MemberStatus.CANCELED.getStatus());
                    userService.save(userCanceled);
                }
            } else {
                User userCanceled = userService.findByUid("90009");
                if (userCanceled != null) {
                    userService.delete(userCanceled);
                }
                
                User user = userService.findByUid("01117");
                if (user != null) {
                    userService.delete(user);
                }
                User user2 = userService.findByUid("02227");
                if (user2 != null) {
                    userService.delete(user2);
                }
                User user3 = userService.findByUid("03337");
                if (user3 != null) {
                    userService.delete(user3);
                }
                User user4 = userService.findByUid("04447");
                if (user4 != null) {
                    userService.delete(user4);
                }

                Song song = songService.findSongByProductId("DEV001PI");
                if (song != null) {
                    songService.delete(song);
                }
                Song song2 = songService.findSongByProductId("DEV002PI");
                if (song2 != null) {
                    songService.delete(song2);
                }
                Song song3 = songService.findSongByProductId("DEV003PI");
                if (song3 != null) {
                    songService.delete(song3);
                }
                Song song4 = songService.findSongByProductId("DEV004PI");
                if (song4 != null) {
                    songService.delete(song4);
                }
                
                // Delete the PlaybackLog is inserted from WS
                List<PlaybackLog> list = playbackLogService.findLogByUID("01117");
                if (list.size() > 0) {
                	playbackLogService.delete(list.get(0));
                }
                List<PlaybackLog> list2 = playbackLogService.findLogByUID("02227");
                if (list2.size() > 0) {
                	playbackLogService.delete(list2.get(0));
                }
                List<PlaybackLog> list3 = playbackLogService.findLogByUID("03337");
                if (list3.size() > 0) {
                	playbackLogService.delete(list3.get(0));
                }
                List<PlaybackLog> list4 = playbackLogService.findLogByUID("04447");
                if (list4.size() > 0) {
                	playbackLogService.delete(list4.get(0));
                }

                StreamAuthentication aut = streamAuthService.findById("01111111");
                streamAuthService.delete(aut);
                StreamAuthentication aut2 = streamAuthService.findById("02222222");
                streamAuthService.delete(aut2);
                StreamAuthentication aut3 = streamAuthService.findById("03333333");
                streamAuthService.delete(aut3);
                StreamAuthentication aut4 = streamAuthService.findById("04444444");
                streamAuthService.delete(aut4);
                
                LOGGER.info("TEST CASE 19. Deleted user!");
                LOGGER.info("TEST CASE 19. Deleted song!");
                LOGGER.info("TEST CASE 19. Deleted log!");
                LOGGER.info("TEST CASE 19. Deleted Streaming!");
            }
            break;
        case 800:
            if (act == 0) {
                User user = userService.findByUid("000800");
                if (user == null) {
                    User u = new User();
                    u.setUid("000800");
                    u.setName("Xuan");
                    u.setAvatarUrl("http://");
                    u.setGender(1);
                    userService.save(u);
                }

            } else {
                User user = userService.findByUid("000800");
                if (user != null) {
                    userService.delete(user);
                }
            }

            break;
        case 100:
            if (act == 0) {
                User user = userService.findByUid("000100");
                if (user == null) {
                    User u = new User();
                    u.setUid("000100");
                    userService.save(u);
                }
                
                User userCanceled = userService.findByUid("90009");
                if (userCanceled == null) {
                    userCanceled = new User();
                    userCanceled.setUid("90009");
                    userCanceled.setStatus((short)MemberStatus.CANCELED.getStatus());
                    userService.save(userCanceled);
                }

            } else {
                User user = userService.findByUid("000100");
                if (user != null) {
                    userService.delete(user);
                }

                User userCanceled = userService.findByUid("90009");
                if (userCanceled != null) {
                    userService.delete(userCanceled);
                }
            }

            break;
        case 1000:
            if (act == 0) {
                User user = userService.findByUid("000100");
                if (user == null) {
                    User u = new User();
                    u.setUid("000100");
                    userService.save(u);
                }
                Artist artist1 = artistService.findById("1");
                if (artist1 == null) {
                    artist1 = new Artist();
                    artist1.setId("1");
                    artist1.setName("Artist1");
                    artist1.setImageUrl("http://");
                    artist1.setFreeword("Unknow");
                    artist1.setExposeFlag(0);
                    artistService.saveOrUpdate(artist1);
                }
                Artist artist2 = artistService.findById("2");
                if (artist2 == null) {
                    artist2 = new Artist();
                    artist2.setId("2");
                    artist2.setName("Artist2");
                    artist2.setImageUrl("http://");
                    artist2.setFreeword("Unknow");
                    artist2.setExposeFlag(0);
                    artistService.saveOrUpdate(artist2);
                }
                Artist artist3 = artistService.findById("3");
                if (artist3 == null) {
                    artist3 = new Artist();
                    artist3.setId("3");
                    artist3.setName("Artist3");
                    artist3.setImageUrl("http://");
                    artist3.setFreeword("Unknow");
                    artist3.setExposeFlag(0);
                    artistService.saveOrUpdate(artist3);
                }

                user = userService.findByUidWFetchFavorite("000100");
                boolean b = false;
                for (Favorite f : user.getFavorites()) {
                    if (f.getArtist().getId().equals(artist1.getId())) {
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    Favorite favorite = new Favorite();
                    favorite.setArtist(artist1);
                    favorite.setUser(user);
                    user.getFavorites().add(favorite);
                    userService.saveOrUpdate(user);
                }

            } else {
                User user = userService.findByUid("000100");
                if (user != null) {
                    userService.delete(user);
                }
                Artist artist1 = artistService.findById("1");
                if (artist1 != null) {
                    artistService.delete(artist1);
                }

                Artist artist2 = artistService.findById("2");
                if (artist2 != null) {
                    artistService.delete(artist2);
                }
                Artist artist3 = artistService.findById("3");
                if (artist3 != null) {
                    artistService.delete(artist3);
                }
            }
            break;
        case 200:
            if (act == 0) {
                commit();
            } else {
//                clean();
            }
        }
        return new String("");
    }

    private void clean() {
        InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream("rollback.sql");
        try {
            Class.forName(jdbcDriver);
            new ScriptRunner(DriverManager.getConnection(jdbcUrl, jdbcUsername,
                    jdbcPassword)).runScript(new BufferedReader(
                    new InputStreamReader(in)));
        } catch (Exception e) {
            LOGGER.info("Rollback error");
            System.err.println(e);
        }
    }

    private void commit() {
        clean();
        InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream("commit.sql");
        try {
            Class.forName(jdbcDriver);
            new ScriptRunner(DriverManager.getConnection(jdbcUrl, jdbcUsername,
                    jdbcPassword)).runScript(new BufferedReader(
                    new InputStreamReader(in)));
        } catch (Exception e) {
            LOGGER.info("Commit error");
            // clean();
        }
    }
}
