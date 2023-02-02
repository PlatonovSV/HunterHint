<!DOCTYPE html>
<html>
<head>
    <title>
        HunterHint
    </title>
</head>
<body>
<H1>HunterHint</H1><hr>
<form action="/user/${id}/delete">
    <div>
        <p>
            <input type="checkbox" name="checkbox"/>Подтвердите удаление всех данных о пользователе
        </p>
    </div>
    <input type="submit" value="Удалить"/>
</form>
<hr>
<form action="/personal">
    <button type="submit">Отмена</button>
</form>
</body>
</html>