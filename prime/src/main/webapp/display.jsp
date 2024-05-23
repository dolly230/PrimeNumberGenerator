<%@page import="com.jspiders.prime.jdbc.PrimeJDBC"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prime Numbers Display</title>
</head>
<body>
    <div align="center">
        <h2>Prime Numbers Stored in Database</h2>
        <table border="1">
            <tr>
                <th>Prime Number</th>
            </tr>
            <%           
                List<Integer> primes = PrimeJDBC.getPrimesFromDB_new();
               
                for (int prime : primes) {
            %>
            <tr>
                <td><%= prime %></td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>
