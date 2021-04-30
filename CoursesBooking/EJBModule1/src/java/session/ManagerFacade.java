/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Manager;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Maurizio
 */
@Stateless
public class ManagerFacade extends AbstractFacade<Manager> implements ManagerFacadeLocal {

    @PersistenceContext(unitName = "EJBModule1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Manager login(String username, String password) {
        TypedQuery<Manager> query = em.createNamedQuery("Manager.findByUsernameAndPassword", Manager.class);
        query.setParameter("name", username);
        query.setParameter("password", password);
        
        try {
            Manager u = query.getSingleResult();
            return u;
        }
        catch (NoResultException e) {
            return null;
        }
    }

    public ManagerFacade() {
        super(Manager.class);
    }
    
}
