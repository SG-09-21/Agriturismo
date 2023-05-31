const toggleBtn = document.querySelector('.toggle_btn')
        const toggleBtnIcon = document.querySelector('.toggle_btn i')
        const dropDownMenu = document.querySelector('.dropdown_menu')

        toggleBtn.onclick = function(){
           dropDownMenu.classList.toggle('open') 
           const isOpen = dropDownMenu.classList.contains('open')

           toggleBtnIcon.classList = isOpen
           ? 'fa-solid fa-xmark'
           : 'fa-solid fa-bars'
        }
        
// Get references to the necessary elements
let passwordHidden = document.getElementById("passwordHidden");
let showPasswordButton = document.getElementById("showPasswordButton");
let showPw = document.getElementById("showPw");

// Add a click event listener to the button
showPasswordButton.addEventListener("click", function() {
  // Toggle the visibility of the password output element
  if (showPw.style.display === "none") {
    showPw.style.display = "block";
    passwordHidden.style.display = "none";
    
  } else {
	  
	  showPw.style.display = "none";
	  passwordHidden.style.display = "block";
  }
  
});