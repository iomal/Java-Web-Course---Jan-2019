package fdmc.web.servlets;

import fdmc.domain.enitites.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cats/create")
public class CreateCatServlet extends HttpServlet implements Serializable {
    private static final String CAT_CREATE_PATH = "../../html/cat-create.html";
    private final HtmlReader htmlReader;
    Map <String,Cat> cats = new HashMap<>();
    @Inject
    public CreateCatServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=\"UTF-8\"");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println(this.htmlReader.readHtmlFile(CAT_CREATE_PATH));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cat cat = new Cat(req.getParameter("name"), req.getParameter("breed"), req.getParameter("colour"),
                Integer.valueOf(req.getParameter("age")));
              cats.put(cat.getName(),cat);
        HttpSession request = req.getSession();
        request.setAttribute("cats", cats);
        resp.sendRedirect(String.format("/profile?name=%s",cat.getName()));}
 }