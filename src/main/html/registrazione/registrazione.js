

function checkPasswords(password1, password2) {
    return password1===password2;
}

function registrati(email, password) {
    let body = {
        "email_utente": email,
        "password_utente": password
    };
    let request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8080/ARPA_war_exploded/registrazione");
    request.onload = function() {
        let risultatoDiv = document.getElementById("risultato");
        risultatoDiv.innerText = JSON.parse(request.responseText)["risultato"];
    };
    request.send(body);
}


function main() {
    let emailInput = document.getElementById("email");
    let passwordInput = document.getElementById("password");
    let passwordConfermaInput = document.getElementById("passwordConferma");
    let registratiButton = document.getElementById("registrati");

    registratiButton.addEventListener("click", function() {
        if (checkPasswords(passwordInput.value, passwordConfermaInput.value)) {
            registrati(emailInput.value, passwordInput.value);
        } else {
            let risultatoDiv = document.getElementById("risultato");
            risultatoDiv.innerText = "Le password non corrispondono"
        }
    });
}


document.addEventListener("DOMContentLoaded", main);