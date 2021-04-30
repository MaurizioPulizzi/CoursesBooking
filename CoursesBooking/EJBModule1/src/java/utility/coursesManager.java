/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import entity.Course;
import entity.Reservation;
import entity.User;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.PersistenceContext;
import session.CourseFacadeLocal;
import session.ReservationFacadeLocal;
import session.UserFacadeLocal;

/**
 *
 * @author Maurizio
 */

@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Singleton
@LocalBean
public class coursesManager implements coursesManagerLocal {
    
    // <editor-fold defaultstate="collapsed" desc="coursesManager Implementation..">
    /**
     * 
     */
    // </editor-fold>    


    @EJB
    private CourseFacadeLocal courseFacade;
    
    @EJB
    private ReservationFacadeLocal reservationFacade;
    
    @EJB
    private UserFacadeLocal userFacade;
    
    private ReadWriteLock locksMapLock;
    private HashMap<Integer, Lock> locks;
    private ConcurrentHashMap<Integer, Integer> coursesMap;
    
    
    private int maxIdCourse=0;
    
     
    @PostConstruct
    private void init() {
        locksMapLock = new ReentrantReadWriteLock();
        coursesMap = new ConcurrentHashMap<>();
        locks = new HashMap<>();
        
        List<Course> courses =  courseFacade.findAll();
        for(Course c: courses){
            int key = c.getIdcourse();
            maxIdCourse = key > maxIdCourse ? key : maxIdCourse;
            locks.put(key, new ReentrantLock());
            coursesMap.put(key, c.getRemainingPeopleNumber());
            
        }
    }
    
    @Override
    public boolean bookCourse(int idCourse, int idUser) {
        boolean res = false;
        locksMapLock.readLock().lock();
        Lock lock = locks.get(idCourse);
        lock.lock();
        try{
            int currentseats=coursesMap.get(idCourse);
            if(currentseats > 0){
                //DO RESERVATION
                Course course=courseFacade.find(idCourse);
                Reservation r = new Reservation(course, userFacade.find(idUser));
                reservationFacade.create(r);
                course.setRemainingPeopleNumber(currentseats-1);
                courseFacade.edit(course);
                //UPDATE COURSEMAP
                coursesMap.compute(idCourse, (k, v) -> (currentseats-1));
                res = true;
            }
        }finally{
            lock.unlock();
            locksMapLock.readLock().unlock();
        }
       return res; 
    }

    @Override
    public boolean deleteReservation(int idCourse, int idUser){
        boolean res = false;
        locksMapLock.readLock().lock();
        Lock lock = locks.get(idCourse);
        lock.lock();
        try{
            int currentseats=coursesMap.get(idCourse);
            //DO DELETE
            Course course=courseFacade.find(idCourse);
            User user = userFacade.find(idUser);
            Reservation r = reservationFacade.findByIduserIdcourse(user, course);
            reservationFacade.remove(r);
            course.setRemainingPeopleNumber(currentseats+1);
            courseFacade.edit(course);
            //UPDATE COURSEMAP
            coursesMap.compute(idCourse, (k, v) -> (currentseats+1));
            res = true;
        }finally{
            lock.unlock();
            locksMapLock.readLock().unlock();
        }
        return res;
    }
    
    @Override
    public boolean deleteCourse(int idCourse) {
        boolean res = false;
        locksMapLock.writeLock().lock();
        Lock lock = locks.get(idCourse);
        lock.lock();
        try{
            //COURSE DELETION
            Course course=courseFacade.find(idCourse);
            courseFacade.remove(course);
            //REMOVING COURSE LOCK FROM THE LOCK LIST
            coursesMap.remove(idCourse);
            locks.remove(idCourse);
            
            res = true;
        }finally{
            lock.unlock();
            locksMapLock.writeLock().unlock();
        }
       return res; 
    }
    
    private Course createCourse(String name, Date date, Date startTime, Date endTime, int maxPeopleNumber){
        Course course = new Course();
        course.setName(name);
        course.setDate(date);
        course.setStartTime(startTime);
        course.setEndTime(endTime);
        course.setMaxPeopleNumber(maxPeopleNumber);
        course.setRemainingPeopleNumber(maxPeopleNumber);
        maxIdCourse+=1;
        course.setIdcourse(maxIdCourse);
        return course;
    }
    
    public boolean addCourse(String name, Date date,
            Date startTime, Date endTime, int maxPeopleNumber){
        boolean res=false;
        
        Course course = createCourse(name, date, startTime, endTime, maxPeopleNumber);
        
        locksMapLock.writeLock().lock();
        //NEW LOCK CREATION
        Lock lock = new ReentrantLock();
        locks.put(maxIdCourse, lock);
        coursesMap.put(maxIdCourse, maxPeopleNumber);
        
        lock.lock();
        locksMapLock.writeLock().unlock();
        locksMapLock.readLock().lock();
         
        try{
            courseFacade.create(course);
            res = true;
        }finally{
            lock.unlock();
            locksMapLock.readLock().unlock();
        }
        return res;
    }
}
