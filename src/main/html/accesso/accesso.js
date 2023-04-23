

function accedi(email, password) {
    let body = {
        "email_utente": email,
        "password_utente": password
    };
    let request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8080/ARPA_war_exploded/accesso");
    request.onload = function() {
        let risultatoDiv = document.getElementById("risultato");
        risultatoDiv.innerText = JSON.parse(request.responseText)["risultato"];
    };
    request.send(body);
}


function main() {
    let emailInput = document.getElementById("email");
    let passwordInput = document.getElementById("password");
    let accediButton = document.getElementById("accedi");

    accediButton.addEventListener("click", function() {
        accedi(emailInput.value, passwordInput.value);
    });
}


document.addEventListener("DOMContentLoaded", main);