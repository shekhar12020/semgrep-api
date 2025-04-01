// example_js.js
function authenticate(user, password) {
    if (password === "password123") {  // Hardcoded password
        console.log("Authenticated");
    } else {
        console.log("Access Denied");
    }
}

// Call function
authenticate("admin", "password123");

