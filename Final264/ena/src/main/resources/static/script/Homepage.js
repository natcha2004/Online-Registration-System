document.addEventListener("DOMContentLoaded", function() {
    // Get the anchor element by its tag name
    var professorAppointmentLink = document.querySelector('.sidebar a');

    // Add a click event listener to the anchor element
    professorAppointmentLink.addEventListener('click', function(event) {
        // Prevent the default behavior of the anchor tag
        event.preventDefault();

        window.location.href = "SearchProfessor.html";
    });
});