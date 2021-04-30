/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maurizio
 */
@Entity
@Table(name = "course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c")
    , @NamedQuery(name = "Course.findByIdcourse", query = "SELECT c FROM Course c WHERE c.idcourse = :idcourse")
    , @NamedQuery(name = "Course.findByName", query = "SELECT c FROM Course c WHERE c.name = :name")
    , @NamedQuery(name = "Course.findByDate", query = "SELECT c FROM Course c WHERE c.date = :date")
    , @NamedQuery(name = "Course.findByStartTime", query = "SELECT c FROM Course c WHERE c.startTime = :startTime")
    , @NamedQuery(name = "Course.findByEndTime", query = "SELECT c FROM Course c WHERE c.endTime = :endTime")
    , @NamedQuery(name = "Course.findByMaxPeopleNumber", query = "SELECT c FROM Course c WHERE c.maxPeopleNumber = :maxPeopleNumber")
    , @NamedQuery(name = "Course.findByRemainingPeopleNumber", query = "SELECT c FROM Course c WHERE c.remainingPeopleNumber = :remainingPeopleNumber")
    , @NamedQuery(name = "Course.getCourses", query = "SELECT c FROM Course c WHERE c.date >= current_date")})
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcourse")
    private Integer idcourse;
    @Column(name = "name", length = 45)
    private String name;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @Column(name = "max_people_number")
    private Integer maxPeopleNumber;
    @Column(name = "remaining_people_number")
    private Integer remainingPeopleNumber;
    @OneToMany(mappedBy = "idcourse")
    private Collection<Reservation> reservationCollection;

    public Course() {
    }

    public Course(Integer idcourse) {
        this.idcourse = idcourse;
    }

    public Integer getIdcourse() {
        return idcourse;
    }

    public void setIdcourse(Integer idcourse) {
        this.idcourse = idcourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getMaxPeopleNumber() {
        return maxPeopleNumber;
    }

    public void setMaxPeopleNumber(Integer maxPeopleNumber) {
        this.maxPeopleNumber = maxPeopleNumber;
    }

    public Integer getRemainingPeopleNumber() {
        return remainingPeopleNumber;
    }

    public void setRemainingPeopleNumber(Integer remainingPeopleNumber) {
        this.remainingPeopleNumber = remainingPeopleNumber;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcourse != null ? idcourse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.idcourse == null && other.idcourse != null) || (this.idcourse != null && !this.idcourse.equals(other.idcourse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Course[ idcourse=" + idcourse + " ]";
    }
    
}
