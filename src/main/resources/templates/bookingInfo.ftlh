<!DOCTYPE html>
<html>
<head>
    <title>
        HunterHint
    </title>
</head>
<body>
<H1>HunterHint</H1><hr>

<H2>Информация о бронировании охоты</H2>
<p><pre style="font-family:arial; font-size:18px;">${info}</pre></p>
Дата и время бронирования <b>${bookingTime}</b><br>
<hr>
<br>
<#list images as img>
    <img src="/images/${img.id}" height="400px"/><br><br>
</#list>
<br>
<form action="/booking/${id}" method="get">
    <DIV>
        <p><pre style="font-family:arial; font-size:18px;">${review}</pre></p>
        <p>Новый отзыв<Br>
            <textarea name="newReview" cols="70" rows="6"></textarea>
        </p><br>
    </DIV>
    <input type="submit" value="Добавить"/>
</form>
<hr>
<form action="/loadImage/${id}">
    <input type="submit" value="Добавить изображение"/>
</form><br>
<form action="/">
    <button type="submit">Вернуться на главную</button>
</form><br><br>
<form action="/booking/${id}/delete">
    <input type="submit" value="Удалить бронирование охоты"/>
</form>
</body>
</html>