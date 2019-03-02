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
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/cats/all")
public class AllCatsServlet extends HttpServlet {
    private static final String ALL_CATS_PATH = "../../html/all-cats.html";
    private final HtmlReader htmlReader;

    @Inject
    public AllCatsServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter =resp.getWriter();
        if(req.getSession().getAttribute("cats")!=null)
        printWriter.println(htmlReader.readHtmlFile(ALL_CATS_PATH).replace("{{}}",
                ((Map<String,Cat>)req.getSession().getAttribute("cats")).keySet().stream().map(cat->
                String.format("<div style=\"margin:5px\"><a href=\"/profile?name=%s\">%s</a><br/></div>",cat,cat))
                .collect(Collectors.joining("")))+"<a href=\"/\">Back To Home</a>"
                +
        req.getSession()
        );
        else printWriter.println(htmlReader.readHtmlFile(ALL_CATS_PATH).replace("{{}}",
                "<div style=\"margin:5px\"><h3>There are no cats.</h3><br/></div>" +
                        "<div style=\"margin:5px\"><a href=\"/cats/create\">Create some</a>" +
                "<br/></div><div style=\"margin:15px\"><a href=\"/\">Back To Home</a></div>"));

    }
}
