<!DOCTYPE html>
<html lang="en">
<head>
    <title>HunterHint</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/registration.css">
</head>
<body>
<div class="background">
    <div class="shape"></div>
    <div class="shape"></div>
</div>
<form class="form" action="/registration" method="get">
    <h3>Регистрация</h3>
    <input type="text" placeholder="Имя" id="name" name="name">
    <input type="text" placeholder="Фамилия" id="lastName" name="lastName">
    <input type="text" placeholder="Отчество" id="patronymic" name="patronymic">

    <input type="text" placeholder="Email" id="email" name="email">
    <input type="text" placeholder="Телефон" id="phone" name="phone">

    <input type="password" placeholder="Пароль" id="password" name="password">

        <div class="go"><input type="submit" value="Регистрация"/></div>
    <br>
    <b>${message}</b>
</form>
</body>
</html>