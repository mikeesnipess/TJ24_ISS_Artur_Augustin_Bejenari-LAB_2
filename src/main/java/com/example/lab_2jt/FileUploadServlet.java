package com.example.lab_2jt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        InputStream fileContent = filePart.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
        List<String> lines = new ArrayList<>();
        String line;

        // Read the lines from the file and store in a List
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        // Shuffle the lines
        Collections.shuffle(lines);

        // Store the shuffled lines in the session
        request.getSession().setAttribute("shuffledLines", lines);

        // Redirect to result.jsp
        response.sendRedirect("result.jsp");
    }
}
