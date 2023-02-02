<!DOCTYPE html>
<html>
<head>
    <title>
        HunterHint
    </title>
</head>
<body>
<H1>HunterHint</H1><hr>
<form action="/area/${id}/delete">
    <div>
        <p>
            <input type="checkbox" name="checkbox"/>Подтвердите удаление всех данных об охотничьих угодьях
        </p>
    </div>
    <input type="submit" value="Удалить"/>
</form>
<hr>
<form action="/area/${id}">
    <button type="submit">Отмена</button>
</form>
</body>
</html>