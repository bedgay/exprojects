package jp.co.mti.mixjuke.batch.archive;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jp.co.mti.mixjuke.batch.adapter.DBConnector;

/**
 * Archive data for mj_streaming_log table
 * @author natu
 *
 */
public class ArchiveTableStreaminglog extends ArchiveTableData {

	private static final String ARCHIVE_DATE_COL = "ArchiveDate";
	private static final String DELETE_DATE_COL = "DeleteDate";
	private static final String ARCHIVE_TIME_SQL = "SELECT ADDDATE(NOW(), -90) AS ArchiveDate, DATE_ADD(NOW(), INTERVAL -10 YEAR) AS DeleteDate";
	
	private static final String SELECT_SQL = "SELECT * FROM MJ_STREAMING_LOG WHERE upd_dt < '%s'";
	private static final String INSERT_SQL = "INSERT INTO MJ_STREAMING_LOG VALUES(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?, ?,?)";
	private static final String DELETE_SQL = "DELETE FROM MJ_STREAMING_LOG WHERE upd_dt < '%s'";
	private static final String DELETE_OLD_DATA_SQL = "DELETE FROM MJ_STREAMING_LOG WHERE upd_dt < '%s'";

	private String archiveDateTime = null;
	private String deleteDateTime = null;

	public ArchiveTableStreaminglog(DBConnector conSrs, DBConnector conDict) {
		super(conSrs, conDict);
	}

	/* (non-Javadoc)
	 * @see jp.co.mti.mixjuke.batch.archive.ArchiveTableData#transfer()
	 */
	@Override
	public int transfer() throws SQLException {
		setupArchiveTime();
		return super.transfer();
	}

	/* (non-Javadoc)
	 * @see jp.co.mti.mixjuke.batch.archive.ArchiveTableData#getTheSelectionSQL()
	 */
	@Override
	public String getTheSelectionSQL() {
		return String.format(SELECT_SQL, archiveDateTime);
	}

	@Override
	public String getTheInsertingSQL() {
		return INSERT_SQL;
	}

	/* (non-Javadoc)
	 * @see jp.co.mti.mixjuke.batch.archive.ArchiveTableData#getTypes()
	 */
	@Override
	public DataType[] getTypes() {
		return new DataType[] { DataType.INT, DataType.STRING, DataType.STRING,
				DataType.TIMESTAMP, DataType.STRING, DataType.STRING,
				DataType.STRING, DataType.INT, DataType.STRING, DataType.INT,
				DataType.INT, DataType.INT, DataType.TIMESTAMP, DataType.STRING,
				DataType.STRING, DataType.TIMESTAMP, DataType.INT, DataType.INT, DataType.INT };
	}
	
	/* (non-Javadoc)
	 * @see jp.co.mti.mixjuke.batch.archive.ArchiveTableData#execSqlBefore()
	 */
	@Override
	protected void execSqlBefore() throws SQLException {
		if (conDict != null) {
			Statement statement = conDict.createStatement();
			conDict.setAutoCommit(Boolean.FALSE);
			statement.execute(String.format(DELETE_OLD_DATA_SQL, deleteDateTime));
			conDict.commit();
		}
	}

	/* (non-Javadoc)
	 * @see jp.co.mti.mixjuke.batch.archive.ArchiveTableData#execOtherSql()
	 */
	@Override
	protected void execSqlAfter() throws SQLException {
		if (conSrc != null) {
			try {
				Statement statementSrc = conSrc.createStatement();
				conSrc.setAutoCommit(Boolean.FALSE);
				statementSrc.execute(String.format(DELETE_SQL, archiveDateTime));
				conSrc.commit();
			} catch (SQLException e) {
				try {
					conDict.rollback();
					conSrc.rollback();
					throw new SQLException(e);
				} catch (SQLException e1) {
					throw new SQLException(e1);
				}
			}
		}
	}
	
	private void setupArchiveTime() throws SQLException {
		if (conSrc != null) {
			try {
				Statement statement = conSrc.createStatement();
				ResultSet resultSet = statement.executeQuery(ARCHIVE_TIME_SQL);
				while (resultSet.next()) {
					archiveDateTime = resultSet.getString(ARCHIVE_DATE_COL);
					deleteDateTime = resultSet.getString(DELETE_DATE_COL);
					break;
				}
			} catch (SQLException e) {
				throw new SQLException(e);
			}
		}
	}

}