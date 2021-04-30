/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Maurizio
 */
@Local
public interface coursesManagerLocal {
    
    boolean bookCourse(int idCourse, int idUser);
    
    boolean deleteReservation(int idCourse, int idUser);
    
    boolean deleteCourse(int idCourse);
    
    boolean addCourse(String name, Date date, Date startTime, Date endTime, int maxPeopleNumber);
}
