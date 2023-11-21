const realFileBtn = document.getElementById("real-file");
const customBtn = document.getElementById("custom-button");
const realFileBtn_des = document.getElementById("real-file_des");
const customBtn_des = document.getElementById("custom-button_des");
const customTxt = document.getElementById("custom-text");
const customTxt_des = document.getElementById("custom-text_des");

customBtn.addEventListener("click", function () {
  realFileBtn.click();
});

customBtn_des.addEventListener("click", function () {
  realFileBtn_des.click();
  console.log("ola");
});

realFileBtn.addEventListener("change", function () {
  if (realFileBtn.value) {
    customTxt.innerHTML = realFileBtn.value.match(
      /[\/\\]([\w\d\s\.\-\(\)]+)$/
    )[1];
  } else {
    customTxt.innerHTML = "Aún no se ha elegido ningún archivo.";
  }
});

realFileBtn_des.addEventListener("change", function () {
  if (realFileBtn_des.value) {
    customTxt_des.innerHTML = realFileBtn_des.value.match(
      /[\/\\]([\w\d\s\.\-\(\)]+)$/
    )[1];
  } else {
    customTxt_des.innerHTML = "Aún no se ha elegido ningún archivo.";
  }
});


document.getElementById('real-file').addEventListener('change', function (event) {
  const archivoInput = event.target;
  const archivo = archivoInput.files[0];
  let text = document.getElementById("custom-text");
});

document.getElementById('real-file_des').addEventListener('change', function (event) {
  const archivoInput = event.target;
  const archivo = archivoInput.files[0];
  let text = document.getElementById("custom-text_des");
});

document.getElementById('key').addEventListener('input', function (event) {
  const textoInput = event.target;
  const valor = textoInput.value;

  if (valor.length > 8) {
    alert('El texto no puede tener más de 8 caracteres.');
    textoInput.value = valor.slice(0, 8); // Truncar el valor a 8 caracteres
  }
});
