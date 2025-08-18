let segundos = 10;
let contador = document.getElementById("contador");

let intervalo = setInterval(function() {
    segundos--;
    contador.textContent = segundos;

    if (segundos <= 0) {
        clearInterval(intervalo);
        window.location.href = "/";
    }
}, 1000);