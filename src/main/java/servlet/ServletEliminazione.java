package servlet;

import java.io.*;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.UtenzeDao;
import exception.EmailAlreadyTakenException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "eliminazione", value = "/eliminazione")
public class ServletEliminazione extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String body = getBody(request);
		// CREDO UN JSON PER IL RISULTATO
		JsonObject temp = new Gson().fromJson(body, JsonObject.class);
		// fromJason => trasforma da stringa a Json, prende in input -stringa- -tipo destinazione-

		String nome = temp.get("nome_utente").toString(); // TROVO IL NOME (email)
		// CREO ISTANZA DAO e Json di risposta
		UtenzeDao DataAccessObject = null;
		JsonObject responseJson = new JsonObject(); // CREO JsonObject per la risposta
		try {
			DataAccessObject = new UtenzeDao(); // CREDO ISTANZA UTENZE_DAO
			int id_utente = DataAccessObject.getIdFromUtenza(nome); // TROVO IL ID DI UTENTE
			DataAccessObject.deleteUtenza(id_utente); // CANCELLO UTENZA
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
		String body;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			}
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		body = stringBuilder.toString();
		return body;


	}

}