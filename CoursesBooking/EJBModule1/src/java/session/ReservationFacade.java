/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Course;
import entity.Reservation;
import entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Maurizio
 */
@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> implements ReservationFacadeLocal {

    @PersistenceContext(unitName = "EJBModule1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservationFacade() {
        super(Reservation.class);
    }
    
    @Override
    public List<Reservation> findByIduser(User iduser){
        TypedQuery<Reservation> query = em.createNamedQuery("Reservation.findByIduser", Reservation.class);
        query.setParameter("iduser", iduser);
        return query.getResultList();
    }
    
    @Override
    public Reservation findByIduserIdcourse(User iduser, Course idcourse){
        TypedQuery<Reservation> query = em.createNamedQuery("Reservation.findByIduserIdcourse", Reservation.class);
        query.setParameter("iduser", iduser);
        query.setParameter("idcourse", idcourse);
        Reservation r;
        try{
            r = query.getResultList().get(0);
        }catch(IndexOutOfBoundsException e){
            r = null;
        }
        return r;
    }

    
}
