package edu.cornell.softwareengineering.crystallize.servletcontainer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cornell.softwareengineering.crystallize.util.staticdb.*;

/**
 * Servlet implementation class Query
 */
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Map<String, String[]> parameters = request.getParameterMap();
		String result = Query.queryTerms("Test", parameters);
		out.append(checkParameters(request));
		out.append(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String checkParameters(HttpServletRequest request) {
		Map<String, String[]> parameters = request.getParameterMap();
		String result = "";
		Iterator parameterIter = parameters.entrySet().iterator();
		while(parameterIter.hasNext()) {
			Map.Entry<String, String[]> pair = (Map.Entry<String, String[]>)parameterIter.next();
			String key = pair.getKey();
			String[] values = pair.getValue();
			result += key + ": " + values[0] + "\n";		
		}
		return result;
	}
}
