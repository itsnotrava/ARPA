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
		String body = getBody(request);
		// CREDO UN JSON PER IL RISULTATO
		JsonObject temp = new Gson().fromJson(body, JsonObject.class);
		// fromJason => trasforma da stringa a Json, prende in input -stringa- -tipo destinazione-

		String nome = temp.get("nome_utente").toString(); // TROVO IL NOME (email)
		String password = temp.get("password_utente").toString(); // TROVO IL NOME (email)
		// CREO ISTANZA DAO e Json di risposta
		UtenzeDao DataAccessObject = null;
		JsonObject responseJson = new JsonObject(); // CREO JsonObject per la risposta
		try {
			DataAccessObject = new UtenzeDao(); // CREDO ISTANZA UTENZE_DAO
			DataAccessObject.verifyUtenza(nome, password); // VERIFICO UTENZA

		} catch (Exception e) {
			responseJson.addProperty("risultato", "Boia, errore");
		} finally {
			responseJson.addProperty("risultato", "sul cesso!");
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