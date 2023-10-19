const desplazamiento = document.getElementById("desplazamiento");
var texto = document.getElementById("txt");
const textoCifrado = document.getElementById("cifrado");
let mensajeCifrado = '';



function cifrado(){
    //declarar el texto que se va a ingresar
    const textoIngresado = texto.value;
    textoCifrado.value = textoIngresado.split('').map(c =>{
        let mayus = (c === c.toUpperCase()) ? true : false;
        let valorEntero = c.toLowerCase().charCodeAt(0);
        if(valorEntero >= 97 && valorEntero <= 122){
            const valorDesplazamieto = parseInt(desplazamiento.value);

            valorEntero -= 97;
            valorEntero = ((valorEntero + valorDesplazamieto) % 26) + 97;
        }
        let cifrado = String.fromCharCode(valorEntero)
        return mayus ? cifrado.toUpperCase() : cifrado;
    }).join('');
}


function descifrado(){
    //declarar el texto que se va a ingresar
    const textoIngresado = texto.value;
    textoCifrado.value = textoIngresado.split('').map(c =>{
        let mayus = (c === c.toUpperCase()) ? true : false;
        let valorEntero = c.toLowerCase().charCodeAt(0);
        if(valorEntero >= 97 && valorEntero <= 122){
            const valorDesplazamieto = parseInt(desplazamiento.value);

            valorEntero -= 97;
            valorEntero = ((valorEntero - valorDesplazamieto) % 26);

            if(valorEntero<0){
                valorEntero = valorEntero +123
            }else{
                valorEntero= valorEntero+97
            }

        }
        let cifrado = String.fromCharCode(valorEntero)
        return mayus ? cifrado.toUpperCase() : cifrado;
    }).join('');
}

function camposVacios(){
    var texto = document.getElementById("txt").value;
    if(texto == ""){
        alert("Ingrese un texto para cifrar")
    }
}

function colocar(){
    var copiar = document.getElementById("cifrado").value;
    document.getElementById("txt").value = copiar
}

function reiniciar(){
    document.getElementById("txt").value = "";
    document.getElementById("cifrado").value = "";
}