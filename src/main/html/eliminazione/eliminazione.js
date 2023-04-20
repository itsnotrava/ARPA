function eliminazione(email) {
    let body = {
        "email_utente": email,

    };
    let request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8080/ARPA_war_exploded/eliminazione");
    request.onload = function() {
        let risultatoDiv = document.getElementById("risultato");
        risultatoDiv.innerText = JSON.parse(request.responseText)["risultato"];
    };
    request.send(body);
}


function main() {
    let emailInput = document.getElementById("email");
    let eliminaButton = document.getElementById("elimina");

    eliminaButton.addEventListener("click", function() {
        eliminazione(emailInput.value);
    });
}


document.addEventListener("DOMContentLoaded", main);