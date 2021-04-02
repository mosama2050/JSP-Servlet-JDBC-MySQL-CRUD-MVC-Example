<%-- 
    Document   : user-list
    Created on : Apr 2, 2021, 3:01:13 AM
    Author     : moham
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

  
<!DOCTYPE html>
<%  List<User> eList = (ArrayList) request.getAttribute("listUser");

%>
<html>
    <head>
        <title>User Management Application</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>

        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: tomato">
                <div>
                    <a href="https://www.javaguides.net" class="navbar-brand"> User
                        Management App </a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list"
                           class="nav-link">Users</a></li>
                </ul>
            </nav>
        </header>
        <br>

        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

            <div class="container">
                <h3 class="text-center">List of Users</h3>
                <hr>
                <div class="container text-left">

                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                        New User</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Country</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>

                        
                        <%
                            for (User u : eList) {
                        %> 
                        <tr>
                            <td>  <%= u.getId()%> </td>
                            <td>  <%= u.getName()%> </td>
                            <td>  <%= u.getEmail()%> </td>
                            <td>  <%= u.getCountry()%> </td>
                            <td><a href="edit?id=<%= u.getId()%>"   >Edit</a>
                                &nbsp;&nbsp;&nbsp;&nbsp; 
                                 <a href="delete?id=<%= u.getId()%>">Delete</a></td>
                        </tr>
                        <%
                            }
                        %>


                    </tbody>

                </table>
            </div>
        </div>
    </body>
</html>
