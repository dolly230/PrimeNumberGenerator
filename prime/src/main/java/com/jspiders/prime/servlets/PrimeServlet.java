package com.jspiders.prime.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspiders.prime.jdbc.PrimeJDBC;

@WebServlet("/prime")
public class PrimeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int min = Integer.parseInt(req.getParameter("min"));
        int max = Integer.parseInt(req.getParameter("max"));
        String strategy = req.getParameter("strategy");

        List<Integer> primes;
        if ("simple".equals(strategy)) {
            primes = PrimeGenerator.generatePrimesSimple(min, max);
        } else {
            primes = PrimeGenerator.generatePrimesSieve(min, max);
        }

        PrimeJDBC.savePrimes(primes); // Save primes to database

        resp.sendRedirect("display.jsp"); // Redirect to display page
    }
}
