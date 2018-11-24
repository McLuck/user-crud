
package com.lucasisrael.usercrud.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.lucasisrael.usercrud.domain.annotation.TrackingUserActivity;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Representa a entidade telefone no domínio da aplicação <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@Entity
@TrackingUserActivity
public class Telephone extends DomainEntity {
    @NotNull
    private String number;
    private LocalDateTime lastContact;
    @ManyToOne ( optional = false )
    @JoinColumn ( foreignKey = @ForeignKey ( name = "FK_AUTHENTICATIONMETHOD_USER" ) , name = "IDUSER" )
    private User user;
    /**
     * Método de recuperação do campo number
     *
     * @return valor do campo number
     */
    public String getNumber () {
        return number;
    }
    /**
     * Valor de number atribuído a number
     *
     * @param number Atributo da Classe
     */
    public void setNumber ( final String number ) {
        this.number = number;
    }
    /**
     * Método de recuperação do campo lastContact
     *
     * @return valor do campo lastContact
     */
    public LocalDateTime getLastContact () {
        return lastContact;
    }
    /**
     * Valor de lastContact atribuído a lastContact
     *
     * @param lastContact Atributo da Classe
     */
    public void setLastContact ( final LocalDateTime lastContact ) {
        this.lastContact = lastContact;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        final StringBuilder builder = new StringBuilder();
        builder.append( "Telephone [" );
        if ( number != null ) {
            builder.append( "number=" ).append( number ).append( ", " );
        }
        if ( lastContact != null ) {
            builder.append( "lastContact=" ).append( lastContact ).append( ", " );
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
