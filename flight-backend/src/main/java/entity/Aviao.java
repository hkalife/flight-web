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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Henrique
 */
@Entity
@Table(name = "aviao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aviao.findAll", query = "SELECT a FROM Aviao a")
    , @NamedQuery(name = "Aviao.findById", query = "SELECT a FROM Aviao a WHERE a.id = :id")
    , @NamedQuery(name = "Aviao.findByFabricante", query = "SELECT a FROM Aviao a WHERE a.fabricante = :fabricante")
    , @NamedQuery(name = "Aviao.findByPrefixo", query = "SELECT a FROM Aviao a WHERE a.prefixo = :prefixo")})
public class Aviao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Size(max = 255)
    @Column(name = "fabricante")
    private String fabricante;
    
    @Size(max = 255)
    @Column(name = "prefixo")
    private String prefixo;
    
    //@OneToMany(mappedBy = "aviaoId")
    //private Collection<Voo> vooCollection;

    public Aviao() {
    }

    public Aviao(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }

    /*@XmlTransient
    public Collection<Voo> getVooCollection() {
        return vooCollection;
    }

    public void setVooCollection(Collection<Voo> vooCollection) {
        this.vooCollection = vooCollection;
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
        if (!(object instanceof Aviao)) {
            return false;
        }
        Aviao other = (Aviao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Aviao[ id=" + id + " ]";
    }
    
}
