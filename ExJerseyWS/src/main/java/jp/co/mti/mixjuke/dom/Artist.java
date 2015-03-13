package jp.co.mti.mixjuke.dom;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jp.co.mti.mixjuke.util.PropertyUtil;
import jp.co.mti.mixjuke.ws.response.ArtistInfo;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

@Entity
@FilterDefs({
        @FilterDef(name = "favoriteFilter", parameters = @ParamDef(name = "userFavoriteFilterParam", type = "java.lang.String")),
        @FilterDef(name = "albumFilter", parameters = @ParamDef(name = "albumFilterParam", type = "java.lang.String")) })
@Table(name = "MJ_ARTISTS")
public class Artist extends AbstractDomain implements java.io.Serializable {

    private static final long serialVersionUID = -7108223197327349711L;

    private String name;
    private String nameKana;
    private String nameAlpha;
    private String imageUrl;
    private String freeword;
    private int exposeFlag;
    private Set<Favorite> favorites = new HashSet<Favorite>();
    private Set<Performance> performances = new HashSet<Performance>();
    private Set<GroupArtist> groups = new HashSet<GroupArtist>();

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "name_kana")
    public String getNameKana() {
        return nameKana;
    }

    public void setNameKana(String nameKana) {
        this.nameKana = nameKana;
    }

    @Column(name = "name_alpha")
    public String getNameAlpha() {
        return nameAlpha;
    }

    public void setNameAlpha(String nameAlpha) {
        this.nameAlpha = nameAlpha;
    }

    /**
     * @return the imageUrl
     */
    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl
     *            the imageUrl to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return the freeword
     */
    @Column(name = "freeword", nullable = false)
    public String getFreeword() {
        return freeword;
    }

    /**
     * @param freeword
     *            the freeword to set
     */
    public void setFreeword(String freeword) {
        this.freeword = freeword;
    }

    /**
     * @return the exposeFlag
     */
    @Column(name = "expose_flag", nullable = false)
    public int getExposeFlag() {
        return exposeFlag;
    }

    /**
     * @param exposeFlag
     *            the exposeFlag to set
     */
    public void setExposeFlag(int exposeFlag) {
        this.exposeFlag = exposeFlag;
    }

    /**
     * @return the favorites
     */
    @OneToMany(mappedBy = "artist")
    @Filter(name = "favoriteFilter", condition = "uid = :userFavoriteFilterParam")
    public Set<Favorite> getFavorites() {
        return favorites;
    }

    /**
     * @param favorites
     *            the favorites to set
     */
    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    /**
     * @return the performances
     */
    @OneToMany(mappedBy = "artist")
    @Filter(name = "albumFilter", condition = "aid = :albumFilterParam")
    public Set<Performance> getPerformances() {
        return performances;
    }

    /**
     * @param performances
     *            the performances to set
     */
    public void setPerformances(Set<Performance> performances) {
        this.performances = performances;
    }

    /**
     * @return the groups
     */
    @OneToMany(mappedBy = "artist")
    public Set<GroupArtist> getGroups() {
        return groups;
    }

    /**
     * @param groups
     *            the groups to set
     */
    public void setGroups(Set<GroupArtist> groups) {
        this.groups = groups;
    }

    /**
     * Build ArtistInfo from Artist properties.
     * 
     * @param mjuid
     *            The user identifier
     * @return ArtistInfo obj
     */
    public ArtistInfo toArtistInfo(String mjuid) {
        ArtistInfo artistInfo = new ArtistInfo();
        artistInfo.setAid(this.getId());
        artistInfo.setName(this.getName());
        if (this.getId() != null) {
            artistInfo
                    .setDetailUrl(MessageFormat.format(
                            PropertyUtil.getProperty("artist_detail_url"),
                            this.getId()));
        }
        if (this.getImageUrl() != null) {
            artistInfo.setImageUrl(MessageFormat.format(
                    PropertyUtil.getProperty("artist_image_url"),
                    this.getImageUrl()));
        } else {// in case imageUrl is null -> get latest albumUrl.
            if (this.getPerformances().size() > 0) {
                String albumUrl = null;
                Date maxDate = null;
                int index = 0;
                for (Performance p : this.getPerformances()) {
                    if (index == 0
                            || p.getAlbum().getSaleDate().compareTo(maxDate) > 0) {
                        maxDate = p.getAlbum().getSaleDate();
                        albumUrl = p.getAlbum().getJacketImg();
                    }
                    index++;
                }
                if (albumUrl != null) {
                    artistInfo.setImageUrl(MessageFormat.format(
                            PropertyUtil.getProperty("song_album_art_url"),
                            albumUrl));
                }
            }
        }
        if (this.getId() != null) {
            String mix_url = PropertyUtil.getProperty("base_url")
                    + "artists/{0}/songs";
            artistInfo.setMixUrl(MessageFormat.format(mix_url, this.getId()));
        }
        for (Favorite f : this.getFavorites()) {
            if (mjuid.equals(f.getUser().getId())) {
                artistInfo.setFavorited(true);
                break;
            }
        }
        return artistInfo;
    }
}
