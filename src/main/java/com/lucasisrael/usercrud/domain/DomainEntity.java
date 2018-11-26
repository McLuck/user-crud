
package com.lucasisrael.usercrud.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Representação básica das classes de domínios que são identificáveis, ou seja,
 * entidades. Que possuem ID únicos e que são persistentes <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * Nov 21, 2018 - @author Lucas Israel - Primeira versão da classe. <br>
 * <br>
 * <br>
 * LISTA DE CLASSES INTERNAS: <br>
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class, 
        property = "id")
@MappedSuperclass
public abstract class DomainEntity extends DomainObject {
    @Id
    @GeneratedValue ( strategy = GenerationType.SEQUENCE )
    protected Long id;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull
    protected LocalDateTime insertionDate;

    @PrePersist
    protected void onCreate () {
        insertionDate = LocalDateTime.now();
    }

    public Long getId () {
        return id;
    }

    public void setId ( final Long id ) {
        this.id = id;
    }

    public LocalDateTime getInsertionDate () {
        return insertionDate;
    }

    public void setInsertionDate ( final LocalDateTime insertionDate ) {
        this.insertionDate = insertionDate;
    }

    /** 
     * TODO Descrição do Método
     * @return
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode () {
        return Objects.hash( id , insertionDate );
    }

    /** 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals ( final Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final DomainEntity other = ( DomainEntity ) obj;
        return Objects.equals( id , other.id ) && Objects.equals( insertionDate , other.insertionDate );
    }
    
}
