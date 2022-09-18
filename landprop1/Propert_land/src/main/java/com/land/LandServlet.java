package com.land;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/")
public class LandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private landDBUtil LandDBUtil;
  
    public LandServlet() {
        this.LandDBUtil = new landDBUtil();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertLand(request, response);
				break;
			case "/delete":
				deleteLand(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateLand(request, response);
				break;
			default:
				listLand(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	
	private void listLand(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<land> listLand = LandDBUtil.selectAllLands();
		request.setAttribute("listLand", listLand);
		RequestDispatcher dispatcher = request.getRequestDispatcher("land-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void updateLand(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		String price = request.getParameter("price");

		land land = new land(id, type, description, price);
		LandDBUtil.updateLand(land);
		response.sendRedirect("list");
	}
	
	private void deleteLand(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		LandDBUtil.deleteLand(id);
		response.sendRedirect("list");

	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		land existingLand = LandDBUtil.selectLand(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("land-form.jsp");
		request.setAttribute("land", existingLand);
		dispatcher.forward(request, response);

	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("land-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertLand(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		land newLand = new land(type, description, price);
		LandDBUtil.insertLand(newLand);
		response.sendRedirect("list");
	}


	

}
