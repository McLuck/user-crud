
package com.lucasisrael.usercrud.domain;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Representa um dado do usuário que sofreu alteração em alguma atividade do usuário <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class ChangedData extends DomainEntity {
    private UserActivity userActivity;
    private String oldValue;
    private String newValue;
    private String attribute;

    /**
     * Método de recuperação do campo userActivity
     *
     * @return valor do campo userActivity
     */
    public UserActivity getUserActivity () {
        return userActivity;
    }

    /**
     * Valor de userActivity atribuído a userActivity
     *
     * @param userActivity
     *            Atributo da Classe
     */
    public void setUserActivity ( final UserActivity userActivity ) {
        this.userActivity = userActivity;
    }

    /**
     * Método de recuperação do campo oldValue
     *
     * @return valor do campo oldValue
     */
    public String getOldValue () {
        return oldValue;
    }

    /**
     * Valor de oldValue atribuído a oldValue
     *
     * @param oldValue
     *            Atributo da Classe
     */
    public void setOldValue ( final String oldValue ) {
        this.oldValue = oldValue;
    }

    /**
     * Método de recuperação do campo newValue
     *
     * @return valor do campo newValue
     */
    public String getNewValue () {
        return newValue;
    }

    /**
     * Valor de newValue atribuído a newValue
     *
     * @param newValue
     *            Atributo da Classe
     */
    public void setNewValue ( final String newValue ) {
        this.newValue = newValue;
    }

    /**
     * Método de recuperação do campo attribute
     *
     * @return valor do campo attribute
     */
    public String getAttribute () {
        return attribute;
    }

    /**
     * Valor de attribute atribuído a attribute
     *
     * @param attribute
     *            Atributo da Classe
     */
    public void setAttribute ( final String attribute ) {
        this.attribute = attribute;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        final StringBuilder builder = new StringBuilder();
        builder.append( "ChangedData [" );
        if ( userActivity != null ) {
            builder.append( "userActivity=" ).append( userActivity ).append( ", " );
        }
        if ( oldValue != null ) {
            builder.append( "oldValue=" ).append( oldValue ).append( ", " );
        }
        if ( newValue != null ) {
            builder.append( "newValue=" ).append( newValue ).append( ", " );
        }
        if ( attribute != null ) {
            builder.append( "attribute=" ).append( attribute ).append( ", " );
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
}
