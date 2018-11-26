
package com.lucasisrael.usercrud.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.lucasisrael.usercrud.domain.UserActivity;
import com.lucasisrael.usercrud.repository.ChangedDataRepository;
import com.lucasisrael.usercrud.repository.UserActivityRepository;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * TODO Definir documentação da classe. <br>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 26, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@Component
public class UserActivityHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    @Lazy
    ChangedDataRepository changedDataRepository;
    @Autowired
    @Lazy
    UserActivityRepository userActivityRepository;
    @Autowired
    @Lazy
    ThreadLocal < UserActivity > userActivity;

    /**
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      java.lang.Exception)
     */
    @Override
    public void afterCompletion ( final HttpServletRequest request , final HttpServletResponse response , final Object handler , final Exception ex ) throws Exception {
        HandlerInterceptor.super.afterCompletion( request , response , handler , ex );
        if ( userActivity.get() != null ) {
            UserActivity activity = userActivity.get();
            activity = userActivityRepository.save( activity );
            if ( ! activity.isEmpty() ) {
                activity.getChanges().forEach( c -> changedDataRepository.save( c ) );
            }
        }
    }
}
