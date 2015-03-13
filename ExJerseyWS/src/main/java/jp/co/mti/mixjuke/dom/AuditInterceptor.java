/**
 * 
 */
package jp.co.mti.mixjuke.dom;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/**
 * @author ntnxuan
 * 
 */
public class AuditInterceptor extends EmptyInterceptor {

    private static final long serialVersionUID = -1842918504343546559L;

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {

        if (entity instanceof Favorite) {
//            System.out.println("onSave on Favorite");
            setValue(state, propertyNames, "addDatetime", new Date());

        }
        if (entity instanceof Rating) {
//            System.out.println("onSave on Rating");
            setValue(state, propertyNames, "updateDatetime", new Date());
        }
        return false;
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id,
            Object[] currentState, Object[] previousState,
            String[] propertyNames, Type[] types) {

        if (entity instanceof Rating) {
//            System.out.println("onFlushDirty on Rating");
            setValue(currentState, propertyNames, "updateDatetime", new Date());
        }
        return false;
    }

    private void setValue(Object[] state, String[] propertyNames,
            String propertyToSet, Object value) {
        int index = Arrays.asList(propertyNames).indexOf(propertyToSet);
        if (index >= 0) {
            state[index] = value;
        }
    }
}
