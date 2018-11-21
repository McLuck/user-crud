
package com.lucasisrael.usercrud.domain;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Domain Transfer Object para autenticação do usuário no sistema <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class LoginDTO extends DomainObject {
    private String userId;
    private String userKey;
    private String authenticationTypeCode;
    private Boolean authenticated;
    private User user;
    private String message;

    /**
     * Método de recuperação do campo userId
     *
     * @return valor do campo userId
     */
    public String getUserId () {
        return userId;
    }

    /**
     * Valor de userId atribuído a userId
     *
     * @param userId
     *            Atributo da Classe
     */
    public void setUserId ( final String userId ) {
        this.userId = userId;
    }

    /**
     * Método de recuperação do campo userKey
     *
     * @return valor do campo userKey
     */
    public String getUserKey () {
        return userKey;
    }

    /**
     * Valor de userKey atribuído a userKey
     *
     * @param userKey
     *            Atributo da Classe
     */
    public void setUserKey ( final String userKey ) {
        this.userKey = userKey;
    }

    /**
     * Método de recuperação do campo authenticationTypeCode
     *
     * @return valor do campo authenticationTypeCode
     */
    public String getAuthenticationTypeCode () {
        return authenticationTypeCode;
    }

    /**
     * Valor de authenticationTypeCode atribuído a authenticationTypeCode
     *
     * @param authenticationTypeCode
     *            Atributo da Classe
     */
    public void setAuthenticationTypeCode ( final String authenticationTypeCode ) {
        this.authenticationTypeCode = authenticationTypeCode;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        final StringBuilder builder = new StringBuilder();
        builder.append( "LoginDTO [" );
        if ( userId != null ) {
            builder.append( "userId=" ).append( userId ).append( ", " );
        }
        if ( userKey != null ) {
            builder.append( "userKey=" ).append( userKey ).append( ", " );
        }
        if ( authenticationTypeCode != null ) {
            builder.append( "authenticationTypeCode=" ).append( authenticationTypeCode );
        }
        builder.append( "]" );
        return builder.toString();
    }

    /**
     * Método de recuperação do campo authenticated
     *
     * @return valor do campo authenticated
     */
    public Boolean getAuthenticated () {
        return authenticated;
    }

    /**
     * Valor de authenticated atribuído a authenticated
     *
     * @param authenticated Atributo da Classe
     */
    public void setAuthenticated ( final Boolean authenticated ) {
        this.authenticated = authenticated;
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

    /**
     * Método de recuperação do campo message
     *
     * @return valor do campo message
     */
    public String getMessage () {
        return message;
    }

    /**
     * Valor de message atribuído a message
     *
     * @param message Atributo da Classe
     */
    public void setMessage ( final String message ) {
        this.message = message;
    }
    
    /**
     * Informa que o login falhou
     * @param message
     */
    public void fail(final String message) {
        this.message = message;
        this.authenticated = false;
    }
    
    /**
     * Informa que o login foi realizado com sucesso
     * @param user
     */
    public void success(final User user) {
        this.message = "Sucesso!";
        this.authenticated = true;
        this.user = user;
    }
    
    public void resetLogin() {
        this.message = null;
        this.user = null;
        this.authenticated = null;
    }
}
