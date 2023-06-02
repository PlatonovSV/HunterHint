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
    <br><br>
    <div style="text-align: center">
        <#list users as user><p>
            <b>${user.lastLame}</b> <b>${user.nameSrt}</b> <b>${user.patronymic}</b><br>
            ${user.email}<br>
            ${user.phoneNumber}<br>
            <b>${user.accessLevelStr}</b><br>
            <div class="social">
                <div class="go"><a href="/personality/${user.getIdStr()}">Подробнее</a></div>
            </div></p>
            <br>
            <br>
        <#else>
            <b>Охотхозяйства ещё не добавлены</b>
        </#list>
    </div>
</div>
</body>
</html>
