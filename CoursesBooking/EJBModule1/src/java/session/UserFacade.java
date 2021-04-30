/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.User;
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
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "EJBModule1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public User login(String username, String password) {
        TypedQuery<User> query = em.createNamedQuery("User.findByUsernameAndPassword", User.class);
        query.setParameter("name", username);
        query.setParameter("password", password);
        
        try {
            User u = query.getSingleResult();
            return u;
        }
        catch (NoResultException e) {
            return null;
        }
    }
    
    public UserFacade() {
        super(User.class);
    }
    
}
