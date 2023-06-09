package servlet;

import java.io.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.UtenzeDao;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "accesso", value = "/accesso")
public class ServletAccesso extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");

		String body = getBody(request);
		// CREDO UN JSON PER IL RISULTATO
		JsonObject temp = new Gson().fromJson(body, JsonObject.class);
		// fromJason => trasforma da stringa a Json, prende in input -stringa- -tipo destinazione-

		String nome = temp.get("email_utente").toString(); // TROVO IL NOME (email)
		String password = temp.get("password_utente").toString(); // TROVO IL NOME (email)
		// CREO ISTANZA DAO e Json di risposta
		UtenzeDao DataAccessObject = null;
		JsonObject responseJson = new JsonObject(); // CREO JsonObject per la risposta
		try {
			DataAccessObject = new UtenzeDao(); // CREDO ISTANZA UTENZE_DAO
			boolean verifica = DataAccessObject.verifyUtenza(nome, password); // VERIFICO UTENZA
			if (verifica) {
				responseJson.addProperty("risultato", "sul cesso!");
			} else {
				responseJson.addProperty("risultato", "nome o password errati");
			}
		} catch (Exception e) {
			responseJson.addProperty("risultato", "Boia, errore");
		}

		// Invio il risultato al client
		PrintWriter printWriter = response.getWriter();
		printWriter.println(responseJson.toString());
		printWriter.flush();

	}

	// PRESA DA INTERNET, SI OCCUPA DI FARE IL BODY DELLA RICHIESTAA
	public static String getBody(HttpServletRequest request) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}

}