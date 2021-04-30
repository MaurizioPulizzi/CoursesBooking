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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CourseFacadeLocal;

/**
 *
 * @author Maurizio
 */
public class controllerDate extends HttpServlet {
    @EJB
    private CourseFacadeLocal courseFacade;

    

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
        HttpSession session = request.getSession();
        Date dt;
        try {
            dt = new SimpleDateFormat("yyyy-MM-dd").parse((String) getServletContext().getAttribute("coursesDate"));
        } catch (ParseException ex) {
            dt = new Date();
        }
        Calendar c = Calendar.getInstance(); 
        c.setTime(dt);
        String command = (String)request.getParameter("action");
        if(command.compareTo("next")==0){
            c.add(Calendar.DATE, 1);
        }else if(command.compareTo("previous")==0){
            c.add(Calendar.DATE, -1);
        }
        dt = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(dt);
        getServletContext().setAttribute("coursesDate", currentDate);
        getServletContext().setAttribute("courses", courseFacade.findByDate(dt));
        
        if(session.getAttribute("manager")!= null && (Boolean)session.getAttribute("manager")){
            response.sendRedirect("./homeManager.jsp");
        }else{
            response.sendRedirect("./homePrenotazioni.jsp");  
        }
    
    }

    
    @Override
    public String getServletInfo() {
        return "next button";
    }// </editor-fold>

}
