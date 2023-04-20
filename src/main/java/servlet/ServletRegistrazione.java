package servlet;

import java.io.*;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.UtenzeDao;
import exception.EmailAlreadyTakenException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "registrazione", value = "/registrazione")
public class ServletRegistrazione extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");

		String body = getBody(request);
		// CREDO UN JSON PER IL RISULTATO
		JsonObject temp = new Gson().fromJson(body, JsonObject.class);
		// fromJason => trasforma da stringa a Json, prende in input -stringa- -tipo destinazione-

		String email = temp.get("email_utente").toString(); // TROVO IL NOME
		String password = temp.get("password_utente").toString(); // TROVO LA PASSWORD

		UtenzeDao utenzeDao;
		JsonObject responseJson = new JsonObject();
		try {
			utenzeDao = new UtenzeDao(); // CREDO ISTANZA UTENZE_DAO
			// Controlla che la email non sia già registrata
			utenzeDao.insertUtenza(email, password); // CREO INSERISCO I DATI CHE VERRANNO MANDATI AL DB
		} catch (EmailAlreadyTakenException e) {
			responseJson.addProperty("risultato", "email già registrata, accedi!");
		} catch (SQLException e) {
			responseJson.addProperty("risultato", "errore nel server");
		} finally {
			responseJson.addProperty("risultato", "sul cesso!");
		}

		// Invio il risultato al client
		PrintWriter printWriter = response.getWriter();
		printWriter.println(responseJson.toString());
		printWriter.flush();
	}

	/**
	 * PRESA DA INTERNET, SI OCCUPA DI FARE IL BODY DELLA RICHIESTAA
	 * @param request
	 * @return String body
	 * @throws IOException
	 */
	private static String getBody(HttpServletRequest request) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
}