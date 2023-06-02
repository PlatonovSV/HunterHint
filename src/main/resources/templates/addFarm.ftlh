<!doctype html>
<html lang="ru">

<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="description" content="Book hunt">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="/img/img_1.png">

    <link rel="stylesheet" href="/css/normalize.css">
    <link rel="stylesheet" href="/css/main.css">

    <meta name="theme-color" content="#fafafa">
</head>

<body>
<div class="container">
    <div class="head">
        <a href="/"><p class="logo"></p></a>
        <div class="menu">
            <div class="menuSub">
                <#if !sessionNotNull>
                    <a class="menuItem" href="/login">Войти</a>
                <#else >
                    <a class="menuItem" href="/logout">Выйти</a>
                </#if>

                <#if sessionNotNull>
                    <a class="menuItem" href="/personal">Кабинет</a>
                </#if>
                <#if sessionNotNull>
                    <a class="menuItem" href="/bookings">Бронирования</a>
                </#if>
            </div>
            <div class="menuSub">
                <#if isAdmin>
                    <a class="menuItem" href="/users">Пользователи</a>
                </#if>
                <#if isOwnerOrAdmin>
                    <a class="menuItem" href="/areas">Хозяйства</a>
                </#if>
                <#if isOwnerOrAdmin>
                    <a class="menuItem" href="/area/add">Создать</a>
                </#if>
            </div>
        </div>
    </div>
    <div class="body">
        <div class="advertisement">
            <p class="advertisementStart">Реклама</p>
            <a class="linkBlock" href="https://www.apple.com/">
                <div class="advertisementBock">
                    <img class="advertisementImg" src="/img/apAd1.jpg" alt="">
                    <p class="advertisementText">
                        Apple<br>
                        Купи<br>
                        сейчас <br>
                        на<br>
                        apple.com
                    </p>
                </div>
            </a>

            <a class="linkBlock" href="https://www.apple.com/">
                <div class="advertisementBock">
                    <img class="advertisementImg" src="/img/apAd1.jpg" alt="">
                    <p class="advertisementText">
                        Apple<br>
                        Купи<br>
                        сейчас <br>
                        на<br>
                        apple.com
                    </p>
                </div>
            </a>

            <a class="linkBlock" href="https://www.apple.com/">
                <div class="advertisementBock">
                    <img class="advertisementImg" src="/img/apAd1.jpg" alt="">
                    <p class="advertisementText">
                        Apple<br>
                        Купи<br>
                        сейчас <br>
                        на<br>
                        apple.com
                    </p>
                </div>
            </a>

            <a class="linkBlock" href="https://www.apple.com/">
                <div class="advertisementBock">
                    <img class="advertisementImg" src="/img/apAd1.jpg" alt="">
                    <p class="advertisementText">
                        Apple<br>
                        Купи<br>
                        сейчас <br>
                        на<br>
                        apple.com
                    </p>
                </div>
            </a>
        </div>
        <div class="contentDetailed">
            <p class="pageName">
                Добавление охотничьих угодий
            </p>
            <div class="detail">
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
                        <br>
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
            </div>
        </div>
    </div>
</div>
</body>

</html>
