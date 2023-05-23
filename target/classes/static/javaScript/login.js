// Ottieni i riferimenti agli elementi del DOM
const registerLink = document.getElementById("registerLink");
const loginForm = document.getElementById("loginForm");
const registratiForm = document.getElementById("registratiForm");

// Aggiungi l'ascoltatore di eventi per il click sul link "Registrati"
registerLink.addEventListener("click", function (event) {
    event.preventDefault(); // Evita il comportamento predefinito del link

    // Nascondi il form di login
    loginForm.style.display = "none";

    // Mostra il form di registrazione
    registratiForm.style.display = "block";
});
