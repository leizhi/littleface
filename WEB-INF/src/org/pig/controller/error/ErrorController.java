package org.pig.controller.error;

import org.pig.error.Error;
import org.pig.error.DbError;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public  class ErrorController extends DbError {

public void sendStateRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	      String message = getMessage();
            PrintWriter out = response.getWriter();
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            out.println("<response>");
            out.println("<msg>" + message + "</msg>");
            out.println("</response>");

            out.close();
}

}
