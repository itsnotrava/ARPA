

function registrati(email, password) {
    let body = {
        "nome_utente": email,
        "password_utente": password
    };
    let request = new XMLHttpRequest();
    request.open("POST", "https://api.SorboleSensori/nuovo_utente");
    request.onload = function() {
        let risultatoDiv = document.getElementById("risultato");
        risultatoDiv.innerText = JSON.parse(request.responseText)["risultato"];
    };
    request.send(body);
}


function main() {
    let emailInput = document.getElementById("email");
    let passwordInput = document.getElementById("password");
    let registratiButton = document.getElementById("registrati");

    registratiButton.addEventListener("click", function() {
        registrati(emailInput.value, passwordInput.value);
    });
}


document.addEventListener("DOMContentLoaded", main);