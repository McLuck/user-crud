
package com.lucasisrael.usercrud.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lucasisrael.usercrud.domain.annotation.TrackingUserActivity;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Representa a entidade email no domínio da aplicação <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@Entity
@TrackingUserActivity
public class Email extends DomainEntity {
    private LocalDateTime activationDate;
    private LocalDateTime lastSentValidationDate;
    private String validationCode;
    private String alias;
    private String address;
    @ManyToOne ( optional = false )
    @JoinColumn ( foreignKey = @ForeignKey ( name = "FK_AUTHENTICATIONMETHOD_USER" ) , name = "IDUSER" )
    private User user;
    
    public LocalDateTime getActivationDate () {
        return activationDate;
    }
    public void setActivationDate ( final LocalDateTime activationDate ) {
        this.activationDate = activationDate;
    }
    public LocalDateTime getLastSentValidationDate () {
        return lastSentValidationDate;
    }
    public void setLastSentValidationDate ( final LocalDateTime lastSentValidationDate ) {
        this.lastSentValidationDate = lastSentValidationDate;
    }
    public String getValidationCode () {
        return validationCode;
    }
    public void setValidationCode ( final String validationCode ) {
        this.validationCode = validationCode;
    }
    public String getAlias () {
        return alias;
    }
    public void setAlias ( final String alias ) {
        this.alias = alias;
    }
    public String getAddress () {
        return address;
    }
    public void setAddress ( final String address ) {
        this.address = address;
    }
    /** 
     * TODO Descrição do Método
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        final StringBuilder builder = new StringBuilder();
        builder.append( "Email [" );
        if ( activationDate != null ) {
            builder.append( "activationDate=" ).append( activationDate ).append( ", " );
        }
        if ( lastSentValidationDate != null ) {
            builder.append( "lastSentValidationDate=" ).append( lastSentValidationDate ).append( ", " );
        }
        if ( validationCode != null ) {
            builder.append( "validationCode=" ).append( validationCode ).append( ", " );
        }
        if ( alias != null ) {
            builder.append( "alias=" ).append( alias ).append( ", " );
        }
        if ( address != null ) {
            builder.append( "address=" ).append( address ).append( ", " );
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
