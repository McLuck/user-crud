
package com.lucasisrael.usercrud.domain;

import javax.persistence.Entity;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Representa os tipos de autenticações da aplicação
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@Entity
public class AuthenticationType extends DomainEntity {
    public static final String EMAIL_AND_PASSWORD = "EMAIL_PWD";
    private String code;
    private String description;
    private boolean available;
    /**
     * Método de recuperação do campo code
     *
     * @return valor do campo code
     */
    public String getCode () {
        return code;
    }
    /**
     * Valor de code atribuído a code
     *
     * @param code Atributo da Classe
     */
    public void setCode ( final String code ) {
        this.code = code;
    }
    /**
     * Método de recuperação do campo description
     *
     * @return valor do campo description
     */
    public String getDescription () {
        return description;
    }
    /**
     * Valor de description atribuído a description
     *
     * @param description Atributo da Classe
     */
    public void setDescription ( final String description ) {
        this.description = description;
    }
    /**
     * Método de recuperação do campo available
     *
     * @return valor do campo available
     */
    public boolean isAvailable () {
        return available;
    }
    /**
     * Valor de available atribuído a available
     *
     * @param available Atributo da Classe
     */
    public void setAvailable ( final boolean available ) {
        this.available = available;
    }
    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        final StringBuilder builder = new StringBuilder();
        builder.append( "AuthenticationType [" );
        if ( code != null ) {
            builder.append( "code=" ).append( code ).append( ", " );
        }
        if ( description != null ) {
            builder.append( "description=" ).append( description ).append( ", " );
        }
        builder.append( "available=" ).append( available ).append( ", " );
        if ( id != null ) {
            builder.append( "id=" ).append( id ).append( ", " );
        }
        if ( insertionDate != null ) {
            builder.append( "insertionDate=" ).append( insertionDate ).append( ", " );
        }
        builder.append( "]" );
        return builder.toString();
    }
    
}
