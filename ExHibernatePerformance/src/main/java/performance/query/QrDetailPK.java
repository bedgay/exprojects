package performance.query;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class QrDetailPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "invoiceId")
	private Integer invoiceId;

	@Column(name = "lineId")
	private Integer lineId;

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof QrDetailPK) {
			QrDetailPK qrDetailPK = (QrDetailPK) obj;
			return qrDetailPK.getInvoiceId().equals(getInvoiceId()) && qrDetailPK.getLineId().equals(getLineId());
		}
		return Boolean.FALSE;
	}

	@Override
	public int hashCode() {
		return getInvoiceId() + getLineId();
	}
	
}
