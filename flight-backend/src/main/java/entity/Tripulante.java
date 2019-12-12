/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "tripulante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tripulante.findAll", query = "SELECT t FROM Tripulante t")
    , @NamedQuery(name = "Tripulante.findById", query = "SELECT t FROM Tripulante t WHERE t.id = :id")
    , @NamedQuery(name = "Tripulante.findByEmail", query = "SELECT t FROM Tripulante t WHERE t.email = :email")
    , @NamedQuery(name = "Tripulante.findByNome", query = "SELECT t FROM Tripulante t WHERE t.nome = :nome")})
public class Tripulante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    
    @Size(max = 255)
    @Column(name = "nome")
    private String nome;
    
    @JoinTable(name = "tripulante_voo", joinColumns = {
        @JoinColumn(name = "Tripulante_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "voo_id", referencedColumnName = "id")})
    @ManyToMany
    @JsonManagedReference
    private Collection<Voo> vooCollection;
    
    /*@ManyToMany(mappedBy = "tripulanteCollection1")
    @JsonManagedReference
    private Collection<Voo> vooCollection1;*/

    public Tripulante() {
    }

    public Tripulante(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Collection<Voo> getVooCollection() {
        return vooCollection;
    }

    public void setVooCollection(Collection<Voo> vooCollection) {
        this.vooCollection = vooCollection;
    }

    /*@XmlTransient
    public Collection<Voo> getVooCollection1() {
        return vooCollection1;
    }

    public void setVooCollection1(Collection<Voo> vooCollection1) {
        this.vooCollection1 = vooCollection1;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tripulante)) {
            return false;
        }
        Tripulante other = (Tripulante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tripulante[ id=" + id + " ]";
    }
    
}
