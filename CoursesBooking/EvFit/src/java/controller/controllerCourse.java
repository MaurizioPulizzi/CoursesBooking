/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "controllerCourse", urlPatterns = {"/course"})
public class controllerCourse extends HttpServlet {
    
    public static final String ADD="add";
    public static final String DELETE="delete";
    
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

    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
                    Enumeration<String> params = request.getParameterNames(); 
            while(params.hasMoreElements()){
             String paramName = params.nextElement();
             System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
            }
        String action = request.getParameter("action");
        if (action.compareTo(DELETE)==0){
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            coursesManager.deleteCourse(courseId);
        }
        else if(action.compareTo(ADD)==0){                
            String name = request.getParameter("corso");
            Date date; 
            Date startTime; 
            Date endTime; 
            int maxPeopleNumber;
            try{
                date = new SimpleDateFormat("yyyy-MM-dd").parse((String) request.getParameter("data"));
                startTime = new SimpleDateFormat("HH:mm").parse((String) request.getParameter("oraInizio"));
                endTime = new SimpleDateFormat("HH:mm").parse((String) request.getParameter("oraFine"));
                maxPeopleNumber = Integer.parseInt((String) request.getParameter("numPosti"));
                coursesManager.addCourse(name, date, startTime, endTime, maxPeopleNumber);              
            }catch(ParseException ex) {
                System.err.println("BAD DATE OR HOUR FORMAT FOR ADDING A NEW COURSE");
                ex.printStackTrace();
            }
        }
        
        Date dt;
        try {
            dt = new SimpleDateFormat("yyyy-MM-dd").parse((String) getServletContext().getAttribute("coursesDate"));
        } catch (ParseException ex) {
            dt = new Date();
        }
        
        getServletContext().setAttribute("courses", courseFacade.findByDate(dt)); 
        response.sendRedirect("./homeManager.jsp");
    }

    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
