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
import javax.persistence.OneToMany;

/**
 *
 * @author Henrique
 */
@Entity
public class Aviao {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String prefixo;
    private String fabricante;
    
    @OneToMany(mappedBy = "aviao", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Voo> voo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
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
