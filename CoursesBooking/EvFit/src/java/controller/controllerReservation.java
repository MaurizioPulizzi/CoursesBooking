/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Course;
import entity.Reservation;
import entity.User;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.CourseFacadeLocal;
import session.ReservationFacadeLocal;
import utility.coursesManager;
import utility.coursesManagerLocal;


/**
 *
 * @author Maurizio
 */
public class controllerReservation extends HttpServlet {
    
    @EJB
    private CourseFacadeLocal courseFacade;
    
    @EJB
    private ReservationFacadeLocal reservationFacade;
    
    coursesManagerLocal coursesManager=lookupCoursesManager();
    
    private coursesManagerLocal lookupCoursesManager(){
        try {
            Context c = new InitialContext();
            return (coursesManager) c.lookup("java:global/EvFit/coursesManager!utility.coursesManager");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
        
    }
    
    public static final String PRENOTA="1";
    public static final String ANNULLA="-1";
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        User user = (User)request.getSession().getAttribute("user");
        
        if(action.compareTo(PRENOTA) == 0){
            coursesManager.bookCourse(courseId, user.getIduser());
        }
        else if(action.compareTo(ANNULLA) == 0){
            coursesManager.deleteReservation(courseId, user.getIduser());
        }
        Date dt;
        try {
            dt = new SimpleDateFormat("yyyy-MM-dd").parse((String) getServletContext().getAttribute("coursesDate"));
        } catch (ParseException ex) {
            dt = new Date();
        }
        
        getServletContext().setAttribute("courses", courseFacade.findByDate(dt)); 
        
        
        List<Reservation> res = reservationFacade.findByIduser(user);
        List<Integer> idreservations = new ArrayList<Integer>();
        for(Reservation r: res){
            Course c = r.getIdcourse();
            if(c != null){
                idreservations.add(c.getIdcourse());
            } 
        }
        getServletContext().setAttribute("reservations", idreservations);

        response.sendRedirect("./homePrenotazioni.jsp");
    }


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for reservations handling";
    }// </editor-fold>

}
