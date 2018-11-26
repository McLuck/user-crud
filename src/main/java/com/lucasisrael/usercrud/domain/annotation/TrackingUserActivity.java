package com.lucasisrael.usercrud.domain.annotation;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Anotação para marcar quais do domínio da aplicação devem sofrer o rastreio de
 * alterações realizadas pelo usuário <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@java.lang.annotation.Documented
@java.lang.annotation.Target(value={java.lang.annotation.ElementType.TYPE})
@java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface TrackingUserActivity {

}
