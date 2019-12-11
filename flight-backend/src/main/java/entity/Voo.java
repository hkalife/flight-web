/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Henrique
 */
@Entity
@Table(name = "voo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voo.findAll", query = "SELECT v FROM Voo v")
    , @NamedQuery(name = "Voo.findById", query = "SELECT v FROM Voo v WHERE v.id = :id")
    , @NamedQuery(name = "Voo.findByDestino", query = "SELECT v FROM Voo v WHERE v.destino = :destino")
    , @NamedQuery(name = "Voo.findByOrigem", query = "SELECT v FROM Voo v WHERE v.origem = :origem")})
public class Voo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "destino")
    private String destino;
    @Size(max = 255)
    @Column(name = "origem")
    private String origem;
    @ManyToMany(mappedBy = "vooCollection")
    private Collection<Tripulante> tripulanteCollection;
    @JoinTable(name = "voo_tripulante", joinColumns = {
        @JoinColumn(name = "Voo_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "tripulante_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Tripulante> tripulanteCollection1;
    @JoinColumn(name = "aviao_id", referencedColumnName = "id")
    @ManyToOne
    private Aviao aviaoId;

    public Voo() {
    }

    public Voo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @XmlTransient
    public Collection<Tripulante> getTripulanteCollection() {
        return tripulanteCollection;
    }

    public void setTripulanteCollection(Collection<Tripulante> tripulanteCollection) {
        this.tripulanteCollection = tripulanteCollection;
    }

    @XmlTransient
    public Collection<Tripulante> getTripulanteCollection1() {
        return tripulanteCollection1;
    }

    public void setTripulanteCollection1(Collection<Tripulante> tripulanteCollection1) {
        this.tripulanteCollection1 = tripulanteCollection1;
    }

    public Aviao getAviaoId() {
        return aviaoId;
    }

    public void setAviaoId(Aviao aviaoId) {
        this.aviaoId = aviaoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voo)) {
            return false;
        }
        Voo other = (Voo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Voo[ id=" + id + " ]";
    }
    
}
