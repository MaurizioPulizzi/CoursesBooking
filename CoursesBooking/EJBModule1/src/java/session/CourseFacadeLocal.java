/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Course;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Maurizio
 */
@Local
public interface CourseFacadeLocal {

    void create(Course course);

    void edit(Course course);

    void remove(Course course);

    Course find(Object id);

    List<Course> findAll();

    List<Course> findRange(int[] range);
    
    List<Course>getCourses();
    
    List<Course> findByDate(java.util.Date date);
    
    int count();
    
}
