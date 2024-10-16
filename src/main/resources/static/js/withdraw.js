document.getElementById("withdrawForm").addEventListener("submit", function(event){
    event.preventDefault();
    // Clear all previous error messages
    document
        .querySelectorAll(".error-message")
        .forEach((el) => (el.style.display = "none"));
    let validWithdrawal = true;
    let validBalance = true;
    let validAmount = true;
    const balance = parseFloat(document.getElementById("balance").value.trim());
    const withdrawAmount = parseFloat(document.getElementById("amount").value.trim());
   if(withdrawAmount > balance)
    {
        validWithdrawal = false;
        document.getElementById("invalidWithdrawError").style.display = "block";
    }else if((balance-withdrawAmount)<50)
    {
        validBalance = false;
        document.getElementById("minimumBalanceError").style.display = "block";
    }

    if(validWithdrawal&&validBalance){
        this.submit();
    }
});
