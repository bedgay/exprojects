package javaz.spring.aop;

import javaz.common.logger.CustomLogger;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

/**
 * @author SUCCESS\tungo
 * @date Aug 6, 2014 3:35:07 PM
 */
public class CustomTransaction implements PlatformTransactionManager {

	private transient final CustomLogger log = CustomLogger.getLogger(this.getClass());

	@Override
	public TransactionStatus getTransaction(TransactionDefinition definition)
			throws TransactionException {
		log.info("getTransaction()");
		return new TransactionStatus() {

			@Override
			public Object createSavepoint() throws TransactionException {
				log.info("createSavepoint()");
				return null;
			}

			@Override
			public void rollbackToSavepoint(Object savepoint)
					throws TransactionException {
				log.info("rollbackToSavepoint()");
			}

			@Override
			public void releaseSavepoint(Object savepoint)
					throws TransactionException {
				log.info("releaseSavepoint()");
			}

			@Override
			public boolean isNewTransaction() {
				log.info("isNewTransaction()");
				return false;
			}

			@Override
			public boolean hasSavepoint() {
				log.info("hasSavepoint()");
				return false;
			}

			@Override
			public void setRollbackOnly() {
				log.info("setRollbackOnly()");
			}

			@Override
			public boolean isRollbackOnly() {
				log.info("isRollbackOnly()");
				return false;
			}

			@Override
			public void flush() {
				log.info("flush()");
			}

			@Override
			public boolean isCompleted() {
				log.info("isCompleted()");
				return true;
			}

		};
	}

	@Override
	public void commit(TransactionStatus status) throws TransactionException {
		log.info("commit()");
	}

	@Override
	public void rollback(TransactionStatus status) throws TransactionException {
		log.info("rollback()");
	}

}
