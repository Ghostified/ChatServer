package org.clientAndServer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.HashSet;
import java.util.Set;

public class ChatServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Set<PrintWriter> clients = new HashSet<>();
    @Override

    protected void doPost (HttpServletRequest request , HttpServletResponse response )
            throws ServerException, IOException {
        String message = request.getParameter("message");
        if (message != null && !message.isEmpty()){
            broadcast(message);
        }
    }

    private synchronized  void broadcast (String message) {
        for (PrintWriter client : clients) {
            client.println(message);
            client.flush();
        }
    }

    @Override
    public void init (ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Chat server initialized");
    }
    @Override
    protected void doGet (HttpServletRequest request , HttpServletResponse response)
        throws ServletException , IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body><title> Chat App </title></head><body>");
        out.println("<h1> Welcome to Chat App </h1>");
        out.println("<form method=\"post\">");
        out.println("<input type=\"text\" name=\"message\">");
        out.println("<input type=\"submit\" value=\"Send\">");
        out.println("</form>");
        out.println("</body></html>");
    }

}
