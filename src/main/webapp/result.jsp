<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Shuffled Lines</title>
</head>
<body>
<h2>Shuffled Lines from the Uploaded File</h2>
<%
    List<String> shuffledLines = (List<String>) session.getAttribute("shuffledLines");
    if (shuffledLines != null && !shuffledLines.isEmpty()) {
        out.println("<ul>");
        for (String line : shuffledLines) {
            out.println("<li>" + line + "</li>");
        }
        out.println("</ul>");
    } else {
        out.println("<p>No lines found.</p>");
    }
%>
<a href="input.jsp">Upload Another File</a>
</body>
</html>
