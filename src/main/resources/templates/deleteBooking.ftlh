<!DOCTYPE html>
<html>
<head>
    <title>
        HunterHint
    </title>
</head>
<body>
<H1>HunterHint</H1><hr>
<form action="/booking/${id}/delete">
    <div>
        <p>
            <input type="checkbox" name="checkbox"/>Подтвердите удаление бронирования охоты
        </p>
    </div>
    <input type="submit" value="Удалить"/>
</form>
<hr>
<form action="/">
    <button type="submit">Отмена</button>
</form>
</body>
</html>