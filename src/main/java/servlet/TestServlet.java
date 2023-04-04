package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.UtenzeDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "servlet.TestServlet", value = "/TestServlet")
public class TestServlet extends HttpServlet {
	private String getBody(HttpServletRequest request) throws IOException {
		BufferedReader bufferedReader = request.getReader();
		StringBuilder body = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			body.append(line);
		}
		return body.toString();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String body = getBody(request);

		// Convertiamo in JsonObject
		Gson gson = new Gson();
		JsonObject jsonBody = gson.fromJson(body, JsonObject.class);

		String email = jsonBody.get("email").getAsString();
		String password = jsonBody.get("password").getAsString();

		try {
			UtenzeDao utenzeDao = new UtenzeDao();
			utenzeDao.insertUtenza(email, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
