function submit2()
{
    var username = document.getElementById('strUsername').value;
    var password = document.getElementById('strPassword').value;
    AndroidFunction.showToast("username: " + username + ", password: " 
            + password);
}
