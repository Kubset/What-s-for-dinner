package servlets;

import Model.Soup;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import services.SoupManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PrepareServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/dashBoard.html");
        if (dispatcher != null) dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Expires", "0");
        resp.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        resp.setHeader("Pragma", "public");
        resp.setContentType("application/pdf");

        String _components = req.getParameter("components");
        String _mealNames = req.getParameter("meal-names");
        String _recipes = req.getParameter("recipes");

        if(_components != null) {
            String[] components = _components.split("#");
            postShoppingListPDF(components, resp);
        } else if(_mealNames != null && _recipes != null) {
            String[] recipes = _recipes.split("#");
            String[] mealNames = _mealNames.split("#");
            postRecipeListPDF(recipes, mealNames, resp);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    private void postShoppingListPDF(String[] components, HttpServletResponse resp) throws IOException{
        try {
            Font f1 =new Font(Font.FontFamily.TIMES_ROMAN,30);

            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph("Shopping List:", f1));
            List list = new List(List.ALIGN_BOTTOM);
            for (String component : components) {
                list.add(component);
            }
            document.add(list);
            document.close();
            resp.setContentLength(baos.size());
            OutputStream os = resp.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        } catch (DocumentException e) {
            System.err.println("Can't generate PDF file with shopping list");
        }
    }

    private void postRecipeListPDF(String[] recipes, String[] mealNames, HttpServletResponse resp) throws IOException {
        try {
            Font f1 =new Font(Font.FontFamily.TIMES_ROMAN,30);
            Font f2 =new Font(Font.FontFamily.TIMES_ROMAN,20);

            Paragraph paragraph;
            Document document = new Document();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

            paragraph = new Paragraph("Recipes:", f1);
            document.add(paragraph);

            for (int i=0; i<recipes.length; i++) {
                paragraph = new Paragraph(mealNames[i], f2);
                document.add(paragraph);
                document.add(new Paragraph(recipes[i]));
            }

            document.close();
            resp.setContentLength(baos.size());
            OutputStream os = resp.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        } catch (DocumentException e) {
            System.err.println("Can't generate PDF file with shopping list");
        }
    }

}

