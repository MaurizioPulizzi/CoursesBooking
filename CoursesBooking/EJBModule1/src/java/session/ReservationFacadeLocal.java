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
import javax.ejb.Local;

/**
 *
 * @author Maurizio
 */
@Local
public interface ReservationFacadeLocal {

    void create(Reservation reservation);

    void edit(Reservation reservation);

    void remove(Reservation reservation);

    Reservation find(Object id);

    List<Reservation> findAll();

    List<Reservation> findRange(int[] range);
    
    List<Reservation> findByIduser(User iduser);
    
    Reservation findByIduserIdcourse(User iduser, Course idcourse);

    int count();
    
}
