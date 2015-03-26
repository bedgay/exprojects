package jp.co.mti.mixjuke.batch.archive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jp.co.mti.mixjuke.batch.adapter.DBConnector;


/**
 * Archive data process abstraction
 * @author natu
 *
 */
public abstract class ArchiveTableData {

	public enum DataType {
		INT(0), LONG(1), FLOAT(2), DOUBLE(3), STRING(4), DATE(5), TIME(6), TIMESTAMP(7), BOOLEAN(8);
		
		private final int value;
		
		DataType(int value) {
			this.value = value;
		}
	}

	protected Connection conSrc;
	protected Connection conDict;

	/**
	 * Get the selection sql
	 * @return String
	 */
	public abstract String getTheSelectionSQL();
	
	/**
	 * Get the Inserting SQl
	 * @return String
	 */
	public abstract String getTheInsertingSQL();
	
	/**
	 * Get DataType configuration for many fields
	 * @return DataType[]
	 */
	public abstract DataType[] getTypes();

	/**
	 * Override this method to execute the sql before main process
	 */
	protected void execSqlBefore() throws SQLException {  }
	
	/**
	 * Override this method to execute the sql after main process
	 */
	protected void execSqlAfter() throws SQLException {  }
	
	/**
	 * Override this method to modify data row before committing
	 * @param resultSet
	 * @param preparedStatement
	 */
	protected void customizeDataRow(ResultSet resultSet,
			PreparedStatement preparedStatement) { }
	
	public ArchiveTableData(DBConnector conSrcConfig, DBConnector conDictConfig) {
		this.conSrc = conSrcConfig.getConnection();
		this.conDict = conDictConfig.getConnection();
	}
	
	/**
	 * The transfer data method
	 * @throws Exception
	 */
	public int transfer() throws SQLException {
		int count = 0;
		if (conSrc == null || conDict == null) {
			throw new SQLException("Can not connect to database to archive.");
		} else {		
			try {
				execSqlBefore();
				
				Statement statementSrc = conSrc.createStatement();
				ResultSet resultSet = statementSrc.executeQuery(getTheSelectionSQL());
				PreparedStatement preparedStatement = conDict.prepareStatement(getTheInsertingSQL());
				
				while (resultSet.next()) {
					prepareDataRow(resultSet, preparedStatement);
					preparedStatement.addBatch();
					count++;
				}
				
				if (count != 0) {
					conDict.setAutoCommit(Boolean.FALSE);
					preparedStatement.executeBatch();
					conDict.commit();
					execSqlAfter();
				}
				
				statementSrc.close();
				preparedStatement.close();
			} catch (SQLException e) {
				try {
					conDict.rollback();
				} catch (SQLException e1) {
					throw new SQLException(e1);
				}
				throw new SQLException(e);
			} finally {
				conSrc.close();
				conDict.close();
			}
		} 
		return count;
	}

	private void prepareDataRow(ResultSet resultSet,
			PreparedStatement preparedStatement) throws SQLException {
		int fieldIndex = 1;
		for (DataType type : getTypes()) {
			switch(type.value) {
				case 0 : preparedStatement.setInt(fieldIndex, resultSet.getInt(fieldIndex)); break;
				case 1 : preparedStatement.setLong(fieldIndex, resultSet.getLong(fieldIndex)); break;
				case 2 : preparedStatement.setFloat(fieldIndex, resultSet.getFloat(fieldIndex)); break;
				case 3 : preparedStatement.setDouble(fieldIndex, resultSet.getDouble(fieldIndex)); break;
				case 4 : preparedStatement.setString(fieldIndex, resultSet.getString(fieldIndex)); break;
				case 5 : preparedStatement.setDate(fieldIndex, resultSet.getDate(fieldIndex)); break;
				case 6 : preparedStatement.setTime(fieldIndex, resultSet.getTime(fieldIndex)); break;
				case 7 : preparedStatement.setTimestamp(fieldIndex, resultSet.getTimestamp(fieldIndex)); break;
				case 8 : preparedStatement.setBoolean(fieldIndex, resultSet.getBoolean(fieldIndex)); break;
			}
			fieldIndex++;
		}
		customizeDataRow(resultSet, preparedStatement);
	}

}
