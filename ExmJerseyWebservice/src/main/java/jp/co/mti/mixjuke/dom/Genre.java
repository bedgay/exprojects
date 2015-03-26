package jp.co.mti.mixjuke.dom;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jp.co.mti.mixjuke.util.PropertyUtil;
import jp.co.mti.mixjuke.ws.response.GenreInfo;

@Entity
@Table(name = "MJ_GENRES")
public class Genre extends AbstractDomain implements java.io.Serializable {

    private static final long serialVersionUID = 681374592907548007L;

    private String name;
    private String iconUrl;
    private Set<Group> groups = new HashSet<Group>();

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

    @Column(name = "icon_url")
    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * @return the groups
     */
    @OneToMany(mappedBy = "genre")
    public Set<Group> getGroups() {
        return groups;
    }

    /**
     * @param groups
     *            the groups to set
     */
    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    /**
     * Build GenreInfo from Genre properties.
     * 
     * @return GenreInfo
     */
    public GenreInfo toGenreInfo() {
        GenreInfo genreInfo = new GenreInfo();
        genreInfo.setGid(this.getId());
        genreInfo.setName(this.getName());
        genreInfo.setIconUrl(PropertyUtil.getProperty("base_url")
                + this.getIconUrl());
        if (this.getId() != null) {
            String mix_url = PropertyUtil.getProperty("base_url")
                    + "songs?gid={0}";
            genreInfo.setMixUrl(MessageFormat.format(mix_url, this.getId()));
        }
        return genreInfo;
    }
}
