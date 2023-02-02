<!DOCTYPE html>
<html>
<head>
    <title>
        HunterHint
    </title>
</head>
<body>
<H1>HunterHint</H1><hr>
<H4>Добавление информации о стоимости трофея</H4>
<form action="/area/${farmId}/${offerId}/addCost">

    <label for="eventCost">Стоимость охотничьего трофея</label><br>
    <input type="number" id="cost" name="cost"
           min="0" max="1000000" value="5000"><br>
    <p>Описание<Br>
        <textarea name="description" cols="60" rows="5"></textarea></p><br>

    <input type="submit" value="Добавить"/>
</form>
<hr>
<form action=""/area/${farmId}">
    <button type="submit">Отмена</button>
</form>
</body>
</html>