function showError(message) {
    alert(message);
}

function clearLocationAndRedirect() {
    localStorage.removeItem("selectedAddress");
    window.location.href = "/map";
}

document.addEventListener("DOMContentLoaded", function() {
    const selectedAddress = localStorage.getItem("selectedAddress");
    if (selectedAddress) {
        document.querySelector('input[name="location"]').value = selectedAddress;
        localStorage.removeItem("selectedAddress");
    }

    <!-- フェードイン -->
        $('#in').fadeIn(1000);
        $('#in2').fadeIn(1200);
        $('#in3').fadeIn(1400);
        $('#in4').fadeIn(1600);
        $('#in5').fadeIn(1800);
        $('#in6').fadeIn(2100);
        $('#in7').fadeIn(2100);
});
