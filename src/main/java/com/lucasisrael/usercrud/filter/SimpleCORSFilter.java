
package com.lucasisrael.usercrud.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
public class SimpleCORSFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger( SimpleCORSFilter.class );

    public SimpleCORSFilter () {
        log.info( "SimpleCORSFilter init" );
    }

    @Override
    public void doFilter ( final ServletRequest req , final ServletResponse res , final FilterChain chain ) throws IOException , ServletException {

        final HttpServletRequest request = ( HttpServletRequest ) req;
        final HttpServletResponse response = ( HttpServletResponse ) res;

        response.setHeader( "Access-Control-Allow-Origin" , request.getHeader( "Origin" ) );
        response.setHeader( "Access-Control-Allow-Credentials" , "true" );
        response.setHeader( "Access-Control-Allow-Methods" , "POST, GET, OPTIONS, DELETE" );
        response.setHeader( "Access-Control-Max-Age" , "3600" );
        response.setHeader( "Access-Control-Allow-Headers" , "Content-Type, Accept, X-Requested-With, remember-me" );

        chain.doFilter( req , res );

    }

    @Override
    public void init ( final FilterConfig filterConfig ) {
    }

    @Override
    public void destroy () {
    }
}