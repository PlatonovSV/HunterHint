<!DOCTYPE html>
<html>
<head>
    <title>
        HunterHint
    </title>
</head>
<body>
<H1>HunterHint</H1><hr>
<H4>Добавление охотничьих угодий</H4>
<form action="/area/add">
    <div><label>
            <input type="text" name="region" required placeholder="Регион">
        </label> <br>
        <label>
            <input type="text" name="district" required placeholder="Муниципальный район">
        </label>
        <br>
        <label>
            <input type="text" name="name" required placeholder="Заглавие">
        </label>
        <label>
            <input type="text" name="com" required placeholder="Название компании">
        </label>
        <br>
        <p>
            <input type="checkbox" checked name="hotel"/>Гостиница
        </p>
        <p>
            <input type="checkbox" checked name="bath"/>Баня
        </p>
        <label for="hotelPrice">Стоимость 1 дня проживания</label><br>
        <input type="number" id="hotelPrice" name="hotelPrice"
               min="1" max="5000000" value="2000"><br>
        <label for="hotelCapacity">Вместительность объекта размещения</label><br>
        <input type="hotelCapacity" id="hotelCapacity" name="hotelCapacity"
               min="1" max="500" value="20"><br><br>
        <label for="coordinatesOfCampLatitude">Координаты лагеря</label><br>
        <input type="number" id="coordinatesOfCampLatitude" name="coordinatesOfCampLatitude"
               min="-90.0000000" max="90.0000000" step="any" value="55.1357913"><br>
        <input type="number" id="coordinatesOfCampLongitude" name="coordinatesOfCampLongitude"
               min="-180.000000" max="180.000000" step="any" value="48.246802"><br><br><br>

        <label for="area">Площадь охотничьих угодий (кв. км)</label><br>
        <input type="number" id="area" name="area"
               min="0" max="100000.0000" value="100.321" step="any"><br><br>
        <label for="hotelPrice">Максимальное число охотников</label><br>
        <input type="maxNumberHunters" id="maxNumberHunters" name="maxNumberHunters"
               min="1" max="500" value="20"><br><br><br>
        <p>Описание<Br>
            <textarea name="description" cols="60" rows="5"></textarea></p>
        <br><br>
    </div>
    <input type="submit" value="Добавить"/>
</form>
</body>
</html>