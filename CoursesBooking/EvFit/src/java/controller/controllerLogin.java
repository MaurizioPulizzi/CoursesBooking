/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Course;
import entity.Manager;
import entity.Reservation;
import entity.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CourseFacadeLocal;
import session.UserFacadeLocal;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import session.ManagerFacadeLocal;
import session.ReservationFacadeLocal;

/**
 *
 * @author Maurizio
 */
@WebServlet(name = "controllerLogin", urlPatterns = {"/login"}, loadOnStartup = 1)
public class controllerLogin extends HttpServlet {

    @EJB
    private UserFacadeLocal userFacade;
    
    @EJB
    private ManagerFacadeLocal managerFacade;
    
    @EJB
    private CourseFacadeLocal courseFacade;
    
    @EJB
    private ReservationFacadeLocal reservationFacade;
    
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        super.init(servletConfig);
                
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(dt);
        getServletContext().setAttribute("coursesDate", currentDate);
        getServletContext().setAttribute("courses", courseFacade.findByDate(dt)); 
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();               
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        boolean ismanager= false;
        User user = userFacade.login(username, password);
        Manager manager = null;
        if(user == null){
            manager = managerFacade.login(username, password);
            if (manager != null)
                ismanager = true;
        }
        session.setAttribute("loggedIn", (user != null || manager != null));
        if (ismanager)
            session.setAttribute("manager", true);

        if(user != null) {
            session.setAttribute("user", user);
            List<Reservation> res = reservationFacade.findByIduser(user);
            System.out.println(res.size());
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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
        else if(manager != null){
            session.setAttribute("user", manager);
            response.sendRedirect("./homeManager.jsp"); 
        }
        else {
            response.sendRedirect("./index.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for login";
    }// </editor-fold>

}
