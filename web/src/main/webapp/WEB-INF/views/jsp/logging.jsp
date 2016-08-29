<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Log in to Yggdrasil Library Robot</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../../../resources/core/css/reset.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Latest compiled Bootstrap -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
<body>

<div class="logging">
    <section class="loginForm">
        <form name="login" action="submit" method="get" accept-charset="utf-8">
            <ul>
                <li><label >Email</label>
                    <input type="email" name="usermail" placeholder="yourname@email.com" required></li>
                <li><label for="password">Password</label>
                    <input type="password" name="password" placeholder="password" required></li>
                <li>
                    <input type="submit" value="Login"></li>
            </ul>
        </form>
    </section>
    <button type="button">Log in</button>
    <br>
    Forgot password?<br>
    Register.
</div>

</body>
</html>