/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maurizio
 */
@Entity
@Table(name = "manager")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manager.findAll", query = "SELECT m FROM Manager m")
    , @NamedQuery(name = "Manager.findByIdmanager", query = "SELECT m FROM Manager m WHERE m.idmanager = :idmanager")
    , @NamedQuery(name = "Manager.findByName", query = "SELECT m FROM Manager m WHERE m.name = :name")
    , @NamedQuery(name = "Manager.findByPassword", query = "SELECT m FROM Manager m WHERE m.password = :password")
    , @NamedQuery(name = "Manager.findByUsernameAndPassword", query = "SELECT m FROM Manager m WHERE m.name = :name AND m.password = :password")})

public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmanager")
    private Integer idmanager;
    // @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;
    //@Size(max = 45)
    @Column(name = "password", length = 45)
    private String password;

    public Manager() {
    }

    public Manager(Integer idmanager) {
        this.idmanager = idmanager;
    }

    public Integer getIdmanager() {
        return idmanager;
    }

    public void setIdmanager(Integer idmanager) {
        this.idmanager = idmanager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmanager != null ? idmanager.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manager)) {
            return false;
        }
        Manager other = (Manager) object;
        if ((this.idmanager == null && other.idmanager != null) || (this.idmanager != null && !this.idmanager.equals(other.idmanager))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Manager[ idmanager=" + idmanager + " ]";
    }
    
}
