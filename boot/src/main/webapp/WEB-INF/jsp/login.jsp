<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Spring Demo</title>
    <link rel="stylesheet" href="https://www.bootstrapdash.com/demo/connect-plus/jquery/template/assets/css/demo_1/style.css">
    <link rel="shortcut icon" type="image/png" href="https://spring.io/images/favicon-9d25009f65637a49ac8d91eb1cf7b75e.ico"/>
</head>
<body>
<div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-center auth lock-full-bg">
            <div class="row w-100">
                <div class="col-lg-4 mx-auto">
                    <div class="auth-form-transparent text-left p-5 text-center">
                        <img src="https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg" class="lock-profile-img" alt="img">
                        <form class="pt-5" method="post" action="/" onsubmit="return false;">
                            <div class="form-group">
                                <label>Login to unlock</label>
                                <input type="text" class="form-control text-left text-white" required id="username" placeholder="Username/Email">
                                <input type="password" class="form-control text-left text-white mt-3" required id="password" placeholder="Password">
                            </div>
                            <div class="mt-5">
                                <button class="btn btn-block btn-success btn-lg font-weight-medium" id="login">Unlock</button>
                            </div>
                            <br>
                            <a href="/join" class="text-white mt-2">Create account</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
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