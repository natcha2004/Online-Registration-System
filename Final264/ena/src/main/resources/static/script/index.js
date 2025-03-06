
        //ตาเปิดปิด
    var passwordVisible = false;
    var eyeIcon = document.getElementById("eye-iconNew");

function PasswordVisibility() {
    var passwordInput1 = document.getElementById("newPassword");
    var passwordInput2 = document.getElementById("confirmPassword");

    passwordVisible = !passwordVisible;

    if (passwordVisible) {
        passwordInput1.type = "text";
        passwordInput2.type = "text";
        eyeIcon.src = "EyeOpenForNew.jpg"; // Change to the open eye icon
    } else {
        passwordInput1.type = "password";
        passwordInput2.type = "password";
        eyeIcon.src = "EyeCloseForNew.jpg"; // Change to the closed eye icon
    }
}


document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.querySelector("form");

    loginForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        // Create JSON from the received data
        const userData = {
            UserName: username,
            PassWord: password,
        };

        // Send JSON to the external authentication API
        fetch('https://restapi.tu.ac.th/api/v1/auth/Ad/verify', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Application-Key': 'TU1ae35116c1f4489dbb16ac00d3836debf452ed2565ec011de8ccb329d60b76d7654b2a54ea62ad6b83f6d33b1e2fbfe9'
            },
            body: JSON.stringify(userData)
        })

        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            if (data.status  == true) {
                // Redirect to the desired page upon successful login
                console.log(data);
                window.location.href = "Homepage.html";
            } else {
                // Display an error message or handle unsuccessful login
                window.location.href = "error.html";
                console.error("Authentication failed:", data.message);
                console.log(data);
                // You might want to show an error message to the user on the page
            }
        })
        .catch(error => { //ดัก error ทุกอย่าง -> reset รหัสผ่าน หมด
            //console.error('There has been a problem with your fetch operation:', error);
            window.location.href = "error.html";
            //console.log("FUCK YOU");
            //errorPage();
            //console.log(data);
        });
    });
});

//คลิกหน้า Pop up incorrect password เพื่อไปหน้าต่อไป
      /*document.addEventListener("DOMContentLoaded", function() {
          const tryAgainButton = document.getElementById("Try");
          const resetPasswordButton = document.getElementById("Reset");
          //try again กลับไปหน้า login
          tryAgainButton.addEventListener("click", function() {
              document.querySelector(".error").style.display = "none";
          });
          //reset password ไปหน้า reset password
          resetPasswordButton.addEventListener("click", function() {
              window.location.href = "Confirm Email.html";
          });
      });*/

      /*function errorPage(){ // will : แก้code ให้เด้ง pop-up ได้
      {
                const tryAgainButton = document.getElementById("Try");
                const resetPasswordButton = document.getElementById("Reset");
                //try again กลับไปหน้า login
                tryAgainButton.addEventListener("click", function() {
                    document.querySelector(".error").style.display = "none";
                });
                //reset password ไปหน้า reset password
                resetPasswordButton.addEventListener("click", function() {
                    window.location.href = "Confirm Email.html";
                });
            }}*/