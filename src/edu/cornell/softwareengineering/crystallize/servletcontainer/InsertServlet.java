package edu.cornell.softwareengineering.crystallize.servletcontainer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.cornell.softwareengineering.crystallize.util.common.ParameterParser;
import edu.cornell.softwareengineering.crystallize.util.staticdb.Insert;

/**
 * Servlet implementation class Insert
 */
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertServlet() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject parameters;
		try {
			parameters = ParameterParser.getParameterObject(request);
			out.append(parameters.toString() + "\n");

			JSONObject refinedParams = parseParameters(parameters);
			out.append(refinedParams.toString() + "\n");
			
			String result = Insert.insert(refinedParams);	
			out.append(result);
			
		} catch (Exception e) {
			out.append(e.getMessage());
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private JSONObject parseParameters(JSONObject parameters) throws Exception {
		JSONObject refined = new JSONObject();
		if(parameters.length() == 0) throw new Exception("No parameters found");
		
		// check collection parameter
		if(parameters.has("collection")) {
			JSONArray collectionArray = parameters.getJSONArray("collection");
			if(collectionArray.length() == 1) {
				refined.put("collection", collectionArray.get(0));
			}
			else if(collectionArray.length() > 1) { throw new Exception("Parameters 'collection' has more than one value"); }
			else { throw new Exception("Parameters 'collection' is empty"); }
		}
		else { throw new Exception("Parameter 'collection' missing"); }
		
		// check document parameter
		if(parameters.has("document")) {
			JSONArray documentArray = parameters.getJSONArray("document");
			if(documentArray.length() > 0) {
				refined.put("document", documentArray);
			}
			else { throw new Exception("Parameters 'document' is empty"); }		}
		else { throw new Exception("Parameter 'document' missing"); }
		
		return refined;
	}

}
