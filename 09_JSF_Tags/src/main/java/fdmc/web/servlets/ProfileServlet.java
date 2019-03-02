package fdmc.web.servlets;

import fdmc.domain.enitites.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private static final String PROFILE_PATH = "../../html/profile.html";
    private static final String NOT_FOUND_PATH = "../../html/not-found.html";
    private final HtmlReader htmlReader;

    @Inject
    public ProfileServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catName="";
        PrintWriter printWriter = resp.getWriter();
        if(req.getQueryString()!=null)
        catName = req.getQueryString().split("=")[1];
        Cat cat=((Map<String,Cat>)req.getSession().getAttribute("cats")).get(catName);
        if(cat!=null){
        printWriter.println(this.htmlReader.readHtmlFile(PROFILE_PATH).replace("{{name}}",
                cat.getName()).replace("{{breed}}", cat.getBreed()).replace("{{age}}",
                String.valueOf(cat.getAge())).replace("{{color}}",cat.getColour()));}
        else {
           printWriter.println(this.htmlReader.readHtmlFile(NOT_FOUND_PATH).replace("{{}}"
                   ,String.format("<h3>Cat with name %s was not found.</h3><br/> " +
                   "<a href=\"\\create\\cat\">Back</a>",catName)));
        }

    }
}
