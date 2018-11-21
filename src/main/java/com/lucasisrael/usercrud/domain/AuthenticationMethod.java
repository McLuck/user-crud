package com.lucasisrael.usercrud.domain;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lucasisrael.usercrud.domain.annotation.TrackingUserActivity;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Representa os métodos de autenticações no domínio da aplicação <br>
 * A ideia é possibilitar várias formas de autenticação para o mesmo usuário
 * <br>
 * O tipo de autenticação determina como cada uma delas será tratada <br>
 * Um tipo usuário e senha, por exemplo, o {@link #authenticationId} é o usuário
 * e o {@link #getAuthenticationKey()} a senha <br>
 * Os autenticadores podem ser implementados sob demanda. Cada nova autenticação
 * que for conveniente, poderá estar disponível para todos os usuários<br>
 * 
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@TrackingUserActivity
public class AuthenticationMethod extends DomainEntity {
    private AuthenticationType type;
    private String authenticationId;
    private String authenticationKey;
    @ManyToOne ( optional = false )
    @JoinColumn ( foreignKey = @ForeignKey ( name = "FK_AUTHENTICATIONMETHOD_USER" ) , name = "IDUSER" )
    private User user;

    /**
     * Método de recuperação do campo type
     *
     * @return valor do campo type
     */
    public AuthenticationType getType () {
        return type;
    }

    /**
     * Valor de type atribuído a type
     *
     * @param type
     *            Atributo da Classe
     */
    public void setType ( final AuthenticationType type ) {
        this.type = type;
    }

    /**
     * Método de recuperação do campo authenticationId
     *
     * @return valor do campo authenticationId
     */
    public String getAuthenticationId () {
        return authenticationId;
    }

    /**
     * Valor de authenticationId atribuído a authenticationId
     *
     * @param authenticationId
     *            Atributo da Classe
     */
    public void setAuthenticationId ( final String authenticationId ) {
        this.authenticationId = authenticationId;
    }

    /**
     * Método de recuperação do campo authenticationKey
     *
     * @return valor do campo authenticationKey
     */
    public String getAuthenticationKey () {
        return authenticationKey;
    }

    /**
     * Valor de authenticationKey atribuído a authenticationKey
     *
     * @param authenticationKey
     *            Atributo da Classe
     */
    public void setAuthenticationKey ( final String authenticationKey ) {
        this.authenticationKey = authenticationKey;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        final StringBuilder builder = new StringBuilder();
        builder.append( "AuthenticationMethod [" );
        if ( type != null ) {
            builder.append( "type=" ).append( type ).append( ", " );
        }
        if ( authenticationId != null ) {
            builder.append( "authenticationId=" ).append( authenticationId ).append( ", " );
        }
        if ( authenticationKey != null ) {
            builder.append( "authenticationKey=" ).append( authenticationKey ).append( ", " );
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
     * @param user
     *            Atributo da Classe
     */
    public void setUser ( final User user ) {
        this.user = user;
    }

}
