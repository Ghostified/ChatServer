package org.clientAndServer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

public class Main {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello world!");

        Server server  = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new ChatServlet()), "/");
        server.start();
        server.join();

    }
}