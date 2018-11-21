
package com.lucasisrael.usercrud.domain;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lucasisrael.usercrud.domain.annotation.TrackingUserActivity;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * TODO Definir documentação da classe. <br>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@Component
public class UserChangesInterceptor extends EmptyInterceptor {
    @Autowired
    @Qualifier ( "userActivity" )
    private ThreadLocal < UserActivity > userActivity;

    /**
     * @see org.hibernate.EmptyInterceptor#onFlushDirty(java.lang.Object,
     *      java.io.Serializable, java.lang.Object[], java.lang.Object[],
     *      java.lang.String[], org.hibernate.type.Type[])
     */
    @Override
    public boolean onFlushDirty ( final Object entity , final Serializable id , final Object[] currentState , final Object[] previousState , final String[] propertyNames , final Type[] types ) {
        if ( entity.getClass().isAnnotationPresent( TrackingUserActivity.class ) ) {
            for ( int i = 0 ; i < propertyNames.length ; i++ ) {
                final Object current = currentState[ i ];
                final Object previous = previousState[ i ];
                final boolean changed = current != null && ! current.equals( previous );
                if ( changed ) {
                    final ChangedData changedData = new ChangedData();
                    changedData.setAttribute( String.join( "." , entity.getClass().getSimpleName() , propertyNames[ i ] ) );
                    changedData.setNewValue( String.valueOf( current ) );
                    changedData.setOldValue( String.valueOf( previous ) );
                    userActivity.get().addChanges( changedData );
                }
            }
        }
        return super.onFlushDirty( entity , id , currentState , previousState , propertyNames , types );
    }
}
