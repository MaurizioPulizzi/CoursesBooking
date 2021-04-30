/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Course;
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
public class CourseFacade extends AbstractFacade<Course> implements CourseFacadeLocal {

    @PersistenceContext(unitName = "EJBModule1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public List<Course> getCourses() {
        TypedQuery<Course> query = em.createNamedQuery("Course.getCourses", Course.class);        
        return query.getResultList();
    }
    
    @Override
     public List<Course> findByDate(java.util.Date date){
        TypedQuery<Course> query = em.createNamedQuery("Course.findByDate", Course.class);
        query.setParameter("date", date);
        return query.getResultList();
     }

    public CourseFacade() {
        super(Course.class);
    }
    
}
