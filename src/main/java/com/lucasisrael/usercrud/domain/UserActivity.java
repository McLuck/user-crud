
package com.lucasisrael.usercrud.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Representa as atividades do usuário no domínio da aplicação <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@Entity
public class UserActivity extends DomainEntity {
    @OneToMany ( cascade=CascadeType.ALL )
    private List < ChangedData > changes;
    private String originDetails;
    @ManyToOne ( optional = false )
    @JoinColumn ( foreignKey = @ForeignKey ( name = "FK_AUTHENTICATIONMETHOD_USER" ) , name = "IDUSER" )
    private User user;

    /**
     * Método de recuperação do campo changes
     *
     * @return valor do campo changes
     */
    public List < ChangedData > getChanges () {
        return changes;
    }

    /**
     * Valor de changes atribuído a changes
     *
     * @param changes
     *            Atributo da Classe
     */
    public void setChanges ( final List < ChangedData > changes ) {
        this.changes = changes;
    }

    /**
     * Método de recuperação do campo originDetails
     *
     * @return valor do campo originDetails
     */
    public String getOriginDetails () {
        return originDetails;
    }

    /**
     * Valor de originDetails atribuído a originDetails
     *
     * @param originDetails
     *            Atributo da Classe
     */
    public void setOriginDetails ( final String originDetails ) {
        this.originDetails = originDetails;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        final int maxLen = 10;
        final StringBuilder builder = new StringBuilder();
        builder.append( "UserActivity [" );
        if ( changes != null ) {
            builder.append( "changes=" ).append( changes.subList( 0 , Math.min( changes.size() , maxLen ) ) ).append( ", " );
        }
        if ( originDetails != null ) {
            builder.append( "originDetails=" ).append( originDetails ).append( ", " );
        }
        if ( id != null ) {
            builder.append( "id=" ).append( id ).append( ", " );
        }
        if ( insertionDate != null ) {
            builder.append( "insertionDate=" ).append( insertionDate ).append( ", " );
        }
        builder.append( "]" );
        return builder.toString();
    }

    /**
     * Adiciona nova alteração na ação do usuário
     * 
     * @param changedData
     */
    public void addChanges ( final ChangedData changedData ) {
        if ( this.getChanges() == null ) {
            changes = new ArrayList <>();
        }
        getChanges().add( changedData );
        changedData.setUserActivity( this );
    }
    
    /**
     * Informa se a atividade do usuário está sem qualquer alteração
     * @return um booleano informando se houve ou não alterações do usuário
     */
    public boolean isEmpty() {
        return this.changes == null || this.changes.isEmpty();
    }

    /**
     * Método de recuperação do campo user
     *
     * @return valor do campo user
     */
    public User getUser () {
        return user;
    }

    /**
     * Valor de user atribuído a user
     *
     * @param user Atributo da Classe
     */
    public void setUser ( final User user ) {
        this.user = user;
    }
}
