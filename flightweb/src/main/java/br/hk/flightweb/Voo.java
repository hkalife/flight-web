/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.hk.flightweb;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Henrique
 */
public class Voo {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String destino;
    private String origem;
    
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Aviao> aviao;
    
    @ManyToMany
    @JsonBackReference
    private Set<Tripulante> tripulante;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Set<Aviao> getAviao() {
        return aviao;
    }

    public void setAviao(Set<Aviao> aviao) {
        this.aviao = aviao;
    }

    public Set<Tripulante> getTripulante() {
        return tripulante;
    }

    public void setTripulante(Set<Tripulante> tripulante) {
        this.tripulante = tripulante;
    }
    
    public void addAviao(Aviao aviao){
        this.aviao.add(aviao);
    }
    
    public void addTripulante(Tripulante tripulante){
        this.tripulante.add(tripulante);
    }
    
}
