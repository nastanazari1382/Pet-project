<head>


<script src="HTML/js/confirmscript.js" defer ></script>


</head>

console.log("script indlæst");


const Knap=document.querySelector("#confirm");
  Knap.addEventListener("click", () => {
  CheckInput();
});


  function CheckInput() {


  const email = document.querySelector("#email").value;

  const masterPassword = document.querySelector('#mpass').value;
  const confirmPass = document.querySelector('#cmpass').value;


  // Evaluate conditions and update the action URL dynamically
  if (email === 99999999999 && masterPassword === length > 10
  && confirmPass.value === masterPassword.value) {
  Knap.action = "ConfirmationCode1.html"
} else {
  console.log("fejl kode")
}


}


