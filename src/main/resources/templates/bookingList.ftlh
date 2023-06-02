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
    <h3>Информация о бронированиях</h3>
    <br>
    <div style="text-align: center">
        <#list bookings as booking><p>
            <b>${booking.farmName}</b><br>
            ФИО охотника <b>${booking.userName}</b><br>
            Дата начала охоты <b>${booking.checkIn}</b><br>
            Дата отъезда <b>${booking.leave}</b><br>
            Дата бронирования ${booking.timestamp}<br>
            <div class="social">
                <div class="go"><a href="/booking/${booking.getIdStr()}">Подробнее</a></div>
            </div></p>
            <br>
            <br>
        <#else>
            <b>Бронирования не добавлены</b>
        </#list>
    </div>
</div>
</body>
</html>
