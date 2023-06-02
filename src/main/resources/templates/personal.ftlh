<!DOCTYPE html>
<html lang="en">
<head>
    <title>HunterHint</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/registration.css">
</head>
<body>
<div class="background">
    <div class="shape"></div>
    <div class="shape"></div>
</div>
<div class="form">
    <div style="text-align: center">
        <img src="/images/${img.id}"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.lastLame} ${user.nameSrt} ${user.patronymic}</h2>
        <br>
        Номер телефона: <b>${user.phoneNumber}</b>
        Email: <b>${user.email}</b>
        <br>
        <div class="social">
            <div class="go"><a class="none" href="/loadImage/${user.getIdStr()}">Загрузить фотографию</a></div>
        </div>
        <div class="social">
            <div class="go"><a class="none" href="/bookings">Просмотр бронирований</a></div>
            <br>
            <#if thisOwnerOrAdmin>
        </div>
        <div class="social">
            <div class="go"><a class="none" href="/areas">Просмотр охотничьих хозяйств</a></div>
        </div>
        <br>
        </#if>
        <#if isAdmin>
            <div class="social">
                <div class="go"><a class="none" href="/users">Просмотр информации о пользователях</a></div>
            </div>
            <br>
        </#if>
        <br>
        <div class="social">
            <div class="go" style="margin-right: 50px"><a class="none" href="/user/${user.idStr}/delete">Удалить</a>
            </div>
            <div class="go"><a class="none" href="/logout">Выйти</a></div>
        </div>
    </div>
</div>
</body>
</html>
