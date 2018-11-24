
package com.lucasisrael.usercrud.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;

import com.lucasisrael.usercrud.domain.annotation.TrackingUserActivity;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Representa a entidade usuário no domínio da aplicação <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@TrackingUserActivity
@Entity ( name = "registereduser" )
public class User extends DomainEntity {
    private String name;
    private LocalDateTime lastUpdateDate;
    @OneToMany ( mappedBy = "user" )
    private List < Email > emails;
    @OneToMany ( mappedBy = "user" )
    private List < Telephone > telephones;
    @OneToMany ( mappedBy = "user" )
    private List < AuthenticationMethod > authentications;

    @PreUpdate
    public void onChange () {
        lastUpdateDate = LocalDateTime.now();
    }

    /**
     * Método de recuperação do campo name
     *
     * @return valor do campo name
     */
    public String getName () {
        return name;
    }

    /**
     * Valor de name atribuído a name
     *
     * @param name
     *            Atributo da Classe
     */
    public void setName ( final String name ) {
        this.name = name;
    }

    /**
     * Método de recuperação do campo emails
     *
     * @return valor do campo emails
     */
    public List < Email > getEmails () {
        return emails;
    }

    /**
     * Valor de emails atribuído a emails
     *
     * @param emails
     *            Atributo da Classe
     */
    public void setEmails ( final List < Email > emails ) {
        this.emails = emails;
    }

    /**
     * Método de recuperação do campo telephones
     *
     * @return valor do campo telephones
     */
    public List < Telephone > getTelephones () {
        return telephones;
    }

    /**
     * Valor de telephones atribuído a telephones
     *
     * @param telephones
     *            Atributo da Classe
     */
    public void setTelephones ( final List < Telephone > telephones ) {
        this.telephones = telephones;
    }

    /**
     * Método de recuperação do campo authentications
     *
     * @return valor do campo authentications
     */
    public List < AuthenticationMethod > getAuthentications () {
        return authentications;
    }

    /**
     * Valor de authentications atribuído a authentications
     *
     * @param authentications
     *            Atributo da Classe
     */
    public void setAuthentications ( final List < AuthenticationMethod > authentications ) {
        this.authentications = authentications;
    }

    /**
     * Método de recuperação do campo lastUpdateDate
     *
     * @return valor do campo lastUpdateDate
     */
    public LocalDateTime getLastUpdateDate () {
        return lastUpdateDate;
    }

    /**
     * Valor de lastUpdateDate atribuído a lastUpdateDate
     *
     * @param lastUpdateDate
     *            Atributo da Classe
     */
    public void setLastUpdateDate ( final LocalDateTime lastUpdateDate ) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        final int maxLen = 10;
        final StringBuilder builder = new StringBuilder();
        builder.append( "User [" );
        if ( name != null ) {
            builder.append( "name=" ).append( name ).append( ", " );
        }
        if ( lastUpdateDate != null ) {
            builder.append( "lastUpdateDate=" ).append( lastUpdateDate ).append( ", " );
        }
        if ( emails != null ) {
            builder.append( "emails=" ).append( emails.subList( 0 , Math.min( emails.size() , maxLen ) ) ).append( ", " );
        }
        if ( telephones != null ) {
            builder.append( "telephones=" ).append( telephones.subList( 0 , Math.min( telephones.size() , maxLen ) ) ).append( ", " );
        }
        if ( authentications != null ) {
            builder.append( "authentications=" ).append( authentications.subList( 0 , Math.min( authentications.size() , maxLen ) ) ).append( ", " );
        }
        if ( id != null ) {
            builder.append( "id=" ).append( id ).append( ", " );
        }
        if ( insertionDate != null ) {
            builder.append( "insertionDate=" ).append( insertionDate );
        }
        builder.append( "]" );
        return builder.toString();
    }
}
