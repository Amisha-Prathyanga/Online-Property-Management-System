package com.wanted;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;





@WebServlet("/")
public class WantedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private wantedDBUtil WantedDBUtil;
	
	public WantedServlet() {
		this.WantedDBUtil = new wantedDBUtil();
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
				insertWanted(request, response);
				break;
			case "/delete":
				deleteWanted(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateWanted(request, response);
				break;
			default:
				listWanted(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
		}
	
	private void listWanted(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<wanted> listWanted = WantedDBUtil.selectAllWanted();
		request.setAttribute("listWanted", listWanted);
		RequestDispatcher dispatcher = request.getRequestDispatcher("wanted-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void updateWanted(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String location = request.getParameter("location");

		wanted wanted = new wanted(id, type, description, price, name, phone, location);
		WantedDBUtil.updateWanted(wanted);
		response.sendRedirect("list");
	}
	
	
	private void deleteWanted(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		WantedDBUtil.deleteWanted(id);
		response.sendRedirect("list");

	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		wanted existingwanted = WantedDBUtil.selectWanted(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("wanted-form.jsp");
		request.setAttribute("wanted", existingwanted);
		dispatcher.forward(request, response);

	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("wanted-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertWanted(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String location = request.getParameter("location");
		wanted newWanted = new wanted(type, description, price, name, phone, location);
		WantedDBUtil.insertWanted(newWanted);
		response.sendRedirect("list");
	}
	
	
}

	
	


