document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Prevent form submission
  
    // Get input values
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
  
    // Simple validation
    if (username === 'admin' && password === 'password') {
      alert('Signup successful!');
      // Redirect or perform other actions here
    } else {
      document.getElementById('error-message').textContent = 'Invalid username or password';
    }
  });