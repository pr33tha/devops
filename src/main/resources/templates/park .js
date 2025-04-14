document.getElementById("booking-form").addEventListener("submit", async function (event) {
    event.preventDefault();

    const vehicleNumber = document.getElementById("vehicle-number").value;
    const ownerName = document.getElementById("owner-name").value;

    const bookingDetails = {
        vehicleNumber: vehicleNumber,
        ownerName: ownerName,
        parkingName: "Muthuvel Parking", // Hardcoded for this example
    };

    try {
        const response = await fetch("http://localhost:8080/api/bookings", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(bookingDetails),
        });

        if (response.ok) {
            alert("Booking successful!");
        } else {
            alert("Error booking slot. Please try again.");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Server error. Please try again later.");
    }
});
