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
    <h3>Информация об охотничьих хозяйствах</h3>
    <br>
    <div style="text-align: center">
        <#list farms as farm><p>
            <b>${farm.name}</b><br>
            <b>${farm.companyStr}</b><br>
            <b>${farm.regionName}</b><br>
            <b>${farm.municipalDistrictName}</b><br>
            площадь <b> ${farm.area}</b> кв.км<br>
            <div class="social">
                <div class="go"><a href="/area/${farm.id}">Подробнее</a></div>
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
