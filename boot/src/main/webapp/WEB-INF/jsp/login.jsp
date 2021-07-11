<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> Login Page </title>
    <style>
        Body {
            font-family: Calibri, Helvetica, sans-serif;
            background-color: white;
        }

        button {
            background-color: #000000;
            width: 100%;
            color: white;
            padding: 15px;
            margin: 10px 0px;
            border: none;
            cursor: pointer;
        }

        input[type=text], input[type=password] {
            width: 100%;
            margin: 8px 0;
            padding: 12px 20px;
            display: inline-block;
            border: 2px solid green;
            box-sizing: border-box;
        }

        button:hover {
            opacity: 0.7;
        }


        .container {
            padding: 25px;
            background-color: white;
            margin: auto;
            width: 40%;
            border: 3px solid #73AD21;
        }
    </style>
</head>
<body>
<center><h1>Login Form </h1></center>
<div>
    <div class="container">
        <label>Username : </label>
        <input type="text" placeholder="Enter Username" id="username" required>
        <label>Password : </label>
        <input type="password" placeholder="Enter Password" id="password" required>
        <button type="submit" id="login">Login</button>
        Create <a href="/join">account?</a>
    </div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<scipt>
    <script>
        $("#login").click(function () {
            const jsonBody = {
                username: $("#username").val(),
                password: $("#password").val(),
                grant_type: "password",
                client_id: "en4gawrzrmjct4c196y6ia2o0t5kzb",
                client_secret: "w7swky0633apzw51stf8tm0ph1hbru3e7mgha6u3fuy4wtvec1scty715ui3"
            }
            $.post("/oauth/token", jsonBody, function (data, status) {
                if (status == 'success') {
                    setCookie("access_token", data.access_token, 7);
                    setCookie("refresh_token", data.refresh_token, 7);
                    window.location.href = "/";
                } else {
                    alert(data.error_description)
                }
            });
        });


        function setCookie(cname, cvalue, exdays) {
            const d = new Date();
            d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
            let expires = "expires=" + d.toUTCString();
            document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
        }
    </script>
</scipt>
</html>