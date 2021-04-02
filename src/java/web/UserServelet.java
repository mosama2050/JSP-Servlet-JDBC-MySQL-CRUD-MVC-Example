/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author moham
 */
@WebServlet("/")
public class UserServelet extends HttpServlet {

    private UserDao userDao;

    public UserServelet() {
        this.userDao = new UserDao();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertUser(request, response);
                break;
            case "/delete":
                deleteUser(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                UpdateUser(request, response);
                break;
            default:
                Listuser(request, response);
                break;

        }

    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.deleteuser(id);
        response.sendRedirect("list");
    } 
    
    private void Listuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
       List<User> listuser = userDao.selectallusers();
        System.out.println("   List<User> listuser " + listuser.get(0).getName());
      request.setAttribute("listUser", listuser);
       RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        
         dispatcher.forward(request, response);
    }
    private void UpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        System.out.println("UpdateUser");
       
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String counrty = request.getParameter("counrty");
        System.out.println(name + email);
        User book = new User(id, name, email, counrty);
        userDao.updateuser(book);
        response.sendRedirect("list");
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User selecteduser = userDao.selectuser(id);
         RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
         request.setAttribute("user", selecteduser);
         dispatcher.forward(request, response);

        
    }

    

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String counrty = request.getParameter("counrty");
        System.out.println("request.getParameter(\"name\");" +name);
        User newuser = new User(name, email, counrty);
        userDao.insertuser(newuser);
        response.sendRedirect("list");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserServelet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserServelet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
