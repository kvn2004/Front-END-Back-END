import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.CrudUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * --------------------------------------------
 * Author: Vihanga Nimsara(kvn2004)
 * GitHub: https://github.com/kvn2004
 * --------------------------------------------
 * Created: 5/27/2025 6:08 PM
 * Project: crudApp
 * --------------------------------------------
 **/
@WebServlet("/student")
public class Main extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        try {
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));
            String address = req.getParameter("address");

            boolean isSaved = CrudUtil.execute("INSERT INTO student (StudentID, StudentNAME, StudentAGE, StudentADDRESS) VALUES (?,?,?,?)",
                    id, name, age, address);

            if (isSaved) {
                resp.getWriter().write("Saved");
            } else {
                resp.getWriter().write("Failed to save");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
