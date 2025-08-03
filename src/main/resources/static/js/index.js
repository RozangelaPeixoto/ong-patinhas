var elementosDuvida = document.querySelectorAll('.duvida')

elementosDuvida.forEach(function (duvida){
   duvida.addEventListener('click', function(){
    duvida.classList.toggle('ativa')
   })
})

function enviarWhatsApp() {
   const numero = '38998011765';
   const mensagem = 'Quero adotar um cachorrinho';
   const url = `https://wa.me/${numero}?text=${encodeURIComponent(mensagem)}`;
   window.open(url, '_blank');
}

document.querySelectorAll('.accordion-header').forEach(header => {
   header.addEventListener('click', () => {
       const content = header.nextElementSibling;

       content.classList.toggle('show');
   });
});

