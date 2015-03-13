/**
 * 
 */
package jp.co.mti.mixjuke.dom;

import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ntnxuan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml","/database/hibernate.xml"})
public class AbstractDom {
    /****** Prepare data for testing ******/
    public void printResult(Object o) {
        ObjectMapper om = new ObjectMapper();
        try {
            System.out.println(om.writeValueAsString(o));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Favorite createNewFavorite(User user, Artist artist) {
        Favorite fa = new Favorite();
        fa.setArtist(artist);
        fa.setUser(user);
        return fa;
    }

    public Album createNewAlbum(String id, String name, User user, Song song, String jacketimg) {
        Album album = new Album();
        album.setId(id);
        album.setTitle(name);
        album.setJacketImg(jacketimg);

        Set<Performance> performances = new HashSet<Performance>();
        performances.add(createNewPerformance(
                createNewArtist("111", "artist name", user), song, album));
        album.setPerformances(performances);
        return album;
    }

    /**
     * @param user
     *            if user is null, It mean User have not favorite this artist
     * @return
     */
    public Artist createNewArtist(String id, String name, User user) {
        Artist ar = new Artist();
        ar.setId(id);
        ar.setName(name);
        ar.setImageUrl("image.url");
        Set<Favorite> favorites = new HashSet<Favorite>();
        if (user != null) {
            favorites.add(createNewFavorite(user, ar));
            ar.setFavorites(favorites);
        }
        return ar;
    }

    public StreamAuthentication createNewStreamAuthentication() {
        StreamAuthentication auth = new StreamAuthentication();
        auth.setAndroidDeliveryEnableFlg(true);
        return auth;
    }

    public Performance createNewPerformance(Artist ar, Song song, Album album) {
        Performance per = new Performance();
        per.setAlbum(album);
        per.setArtist(ar);
        per.setSong(song);
        return per;
    }

    public Rating createNewRating(int flag, User user, Song song) {
        Rating ra = new Rating();
        ra.setSflag(flag);
        ra.setSong(song);
        ra.setUser(user);
        return ra;
    }

    public Song createNewSong(String id, String fileName, String pid, User user) {
        Song song = new Song();
        StreamAuthentication auth = createNewStreamAuthentication();

        Set<Performance> performances = new HashSet<Performance>();
        performances.add(createNewPerformance(
                createNewArtist("111", "artist name", user), song,
                createNewAlbum("111", "album name", user, song, "jacket image")));

        Set<Rating> ratings = new HashSet<Rating>();
        ratings.add(createNewRating(2, user, song));

        song.setFileName(fileName);
        song.setId(id);
        song.setProductId(pid);
        song.setRealId(pid);
        song.setTitle("song title");
        song.setTrialLength(10);
        song.setLength(11);
        song.setAuth(auth);
        song.setPerformances(performances);
        song.setRatings(ratings);

        auth.setSong(song);
        return song;
    }

    public User createNewUser(String id, String uid) {
        User user = new User();
        user.setId(id);
        user.setUid(uid);
        return user;
    }

}
