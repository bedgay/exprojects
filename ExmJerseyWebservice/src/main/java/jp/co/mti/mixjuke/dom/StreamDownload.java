/**
 * 
 */
package jp.co.mti.mixjuke.dom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ntnxuan
 * 
 */
@Entity
@Table(name = "MJ_STR_DL")
public class StreamDownload extends AbstractDomain {

	private String strRegionId;
	private String dlProdId;
	private String dlRegionId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.co.mti.mixjuke.dom.AbstractDomain#getId()
	 */
	@Override
	@Id
	@Column(name = "str_prod_id")
	public String getId() {
		return id;
	}

	@Column(name = "str_region_id")
	public String getStrRegionId() {
		return strRegionId;
	}

	public void setStrRegionId(String strRegionId) {
		this.strRegionId = strRegionId;
	}

	@Column(name = "dl_prod_id")
	public String getDlProdId() {
		return dlProdId;
	}

	public void setDlProdId(String dlProdId) {
		this.dlProdId = dlProdId;
	}

	@Column(name = "dl_region_id")
	public String getDlRegionId() {
		return dlRegionId;
	}

	public void setDlRegionId(String dlRegionId) {
		this.dlRegionId = dlRegionId;
	}

}
