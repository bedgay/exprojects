package jp.co.mti.mixjuke.dom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author natu
 * @date 2014-01-17
 */
@Entity
@Table(name = "MJ_HOT_ARTISTS")
public class HotArtist extends AbstractDomain {

	@Override
	@Id
	@Column(name = "artist_id")
	public String getId() {
		return id;
	}

}
