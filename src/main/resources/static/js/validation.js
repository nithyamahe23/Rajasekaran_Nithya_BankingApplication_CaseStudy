document.getElementById("signupform").addEventListener("submit", function(event){
    alert("register");
    event.preventDefault();

    // Clear all previous error messages
    document.querySelectorAll(".error-message").forEach((el) => (el.style.display = "none"));

    //get all the form values
    const email = document.getElementById("email").value.trim();
    alert(email);
    const password = document.getElementById("password").value.trim();
    const contactNumber = document.getElementById("contactNo").value.trim();
    const firstName = document.getElementById("firstName").value.trim();
    const lastName = document.getElementById("lastName").value.trim();
    const address = document.getElementById("address").value.trim();


    let validDetails = true;

    //validate email
    if (!validateEmail(email)) {
        validDetails = false;
        document.getElementById("emailError").style.display = "block";
        document.getElementById("emailError").style.paddingLeft = "32em";
    }

    //Validate contact number
    if (!validatePhone(contactNumber)) {
        validDetails = false;
        document.getElementById("contactNoError").style.display = "block";
        document.getElementById("contactNoError").style.paddingLeft = "32em";
    }

    //validate password
    if (!validatePassword(password)) {
        validDetails = false;
        document.getElementById("passwordError").style.display = "block";
        document.getElementById("passwordError").style.paddingLeft = "32em";
    }

    //validate first name
    if(!firstName)
    {
        validDetails = false;
        document.getElementById("firstNameError").style.display = "block";
        document.getElementById("firstNameError").style.paddingLeft = "32em";
    }

    //validate address
    if(!address)
    {
        validDetails = false;
        document.getElementById("addressError").style.display = "block";
        document.getElementById("addressError").style.paddingLeft = "32em";
    }
    //validate last name
    if(!lastName)
    {
        validDetails = false;
        document.getElementById("lastNameError").style.display = "block";
        document.getElementById("lastNameError").style.paddingLeft = "32em";
    }

    //validate gender
    if(!validateGender())
    {
        validDetails = false;
        document.getElementById("genderError").style.display = "block";
        document.getElementById("genderError").style.paddingLeft = "32em";
    }

    //if the details are valid - submit the form
    if (validDetails) {
        this.submit();
    }
});

//Email validation - the email should contain email string followed by @, then a string and ends with .com
function validateEmail(email){
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

//Contact number validation - should contain 10 digits
function validatePhone(phone) {
    const re = /^\+?[1-9]\d{9}$/;
    return re.test(phone);
}

//Password - should be more than 8 characters and should contain an upper case letters, lower case letters and numbers only.
//No Special characters
function validatePassword(password) {
    const re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,20}$/;
    return re.test(password);
}

//Gender validation - male or female should be selected
function validateGender()
{
    if ((!document.getElementById("male").checked) && (!document.getElementById("female").checked))
    {
        return false;
    }else{
        return true;
    }
}

//Country validation - user should select a country
function validateCountry(country)
{
    if(country == -1)
    {
        return false;
    }else{
        return true;
    }
}