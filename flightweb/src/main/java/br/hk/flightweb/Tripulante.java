/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hk.flightweb;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Henrique
 */
@Entity
public class Tripulante {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String nome;
    private String email;
    
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Voo> voo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Voo> getVoo() {
        return voo;
    }

    public void setVoo(Set<Voo> voo) {
        this.voo = voo;
    }
    
    public void addVoo(Voo voo){
        this.voo.add(voo);
    }
    
}
