/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maurizio
 */
@Entity
@Table(name = "reservation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r")
    , @NamedQuery(name = "Reservation.findByIdreservation", query = "SELECT r FROM Reservation r WHERE r.idreservation = :idreservation")
    , @NamedQuery(name = "Reservation.findByIduser", query = "SELECT r FROM Reservation r WHERE r.iduser = :iduser")
    , @NamedQuery(name = "Reservation.findByIduserIdcourse", query = "SELECT r FROM Reservation r WHERE r.iduser = :iduser AND r.idcourse = :idcourse")})
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idreservation")
    private Integer idreservation;
    @JoinColumn(name = "idcourse", referencedColumnName = "idcourse")
    @ManyToOne
    private Course idcourse;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne
    private User iduser;

    public Reservation() {
    }

    public Reservation(Integer idreservation) {
        this.idreservation = idreservation;
    }
    
    public Reservation(Course course, User user) {
        this.idcourse = course;
        this.iduser = user;
    }

    public Integer getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(Integer idreservation) {
        this.idreservation = idreservation;
    }

    public Course getIdcourse() {
        return idcourse;
    }

    public void setIdcourse(Course idcourse) {
        this.idcourse = idcourse;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreservation != null ? idreservation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.idreservation == null && other.idreservation != null) || (this.idreservation != null && !this.idreservation.equals(other.idreservation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Reservation[ idreservation=" + idreservation + " ]";
    }
    
}
