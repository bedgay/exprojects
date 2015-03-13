package jp.co.mti.mixjuke.batch.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nhphuoc on 12/11/13.
 */
public class StrDl extends AbstractSync {

	public StrDl(Connection con1, Connection con2, boolean isFirstTime) {
		super(con1, con2, isFirstTime);
	}

	public void run() {
		LOGGER.info("Starting synchronization MJ_STR_DL table");
		super.run();
	}

	protected void sync() throws SQLException {
		String msSQL = "SELECT * FROM (SELECT Row_Number() OVER (ORDER BY STR_PRODUCT_ID) as RowIndex, "
				+ "STR_PRODUCT_ID,STR_REGION_ID,DL_PRODUCT_ID,DL_REGION_ID FROM dbo.V_SITESTG_METALINK_STR_DL_TBL) "
				+ "AS Sub WHERE Sub.RowIndex >= ? AND Sub.RowIndex < ?";
		PreparedStatement msStatement = con1.prepareStatement(msSQL);
		String mySQL = "INSERT INTO MJ_STR_DL(str_prod_id,str_region_id,dl_prod_id,dl_region_id) VALUES (?,?,?,?)";
		PreparedStatement pmyStatement = con2.prepareStatement(mySQL);
		int page = 0;
		boolean repeat;
		int pageSize = 1000;
		int count;
		// Delete all data from table MJ_STR_DL
		java.sql.Statement delStatement = con2.createStatement();
		delStatement.execute("TRUNCATE TABLE MJ_STR_DL");
		delStatement.close();
		// Copy data from V_SITESTG_METALINK_STR_DL_TBL
		do {
			msStatement.setInt(1, page * pageSize);
			msStatement.setInt(2, (page + 1) * pageSize);
			ResultSet msRS = msStatement.executeQuery();
			count = 0;
			while (msRS.next()) {
				count++;
				pmyStatement.setString(1, msRS.getString("STR_PRODUCT_ID"));
				pmyStatement.setInt(2, msRS.getInt("STR_REGION_ID"));
				pmyStatement.setString(3, msRS.getString("DL_PRODUCT_ID"));
				pmyStatement.setInt(4, msRS.getInt("DL_REGION_ID"));
				pmyStatement.execute();
			}
			if (count > 0) {
				page++;
				repeat = true;
			} else {
				repeat = false;
			}
		} while (repeat);
		pmyStatement.close();
	}
	
}
