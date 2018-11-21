package com.lucasisrael.usercrud;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import com.lucasisrael.usercrud.domain.UserChangesInterceptor;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Configuração específicas do hibernate <br>
 * Utilizado para aplicar o interceptor para detectar alterações de dados do
 * usuário <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@Configuration
public class UserChangesHibernatePropertyCustomizer implements HibernatePropertiesCustomizer {
    @Autowired
    UserChangesInterceptor userInterceptor;

    /**
     * @see org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer#customize(java.util.Map)
     */
    @Override
    public void customize ( final Map < String , Object > hibernateProperties ) {
        hibernateProperties.put( "hibernate.session_factory.interceptor" , userInterceptor );
    }
}
