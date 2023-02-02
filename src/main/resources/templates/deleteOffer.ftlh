<!DOCTYPE html>
<html>
<head>
    <title>
        HunterHint
    </title>
</head>
<body>
<H1>HunterHint</H1><hr>
<form action="/area/${farmId}/${offerId}/delete">
    <div>
        <p>
            <input type="checkbox" name="checkbox"/>Подтвердите удаление всех данных о предложении об охоте
        </p>
    </div>
    <input type="submit" value="Удалить"/>
</form>
<hr>
<form action="/area/${farmId}">
    <button type="submit">Отмена</button>
</form>
</body>
</html>