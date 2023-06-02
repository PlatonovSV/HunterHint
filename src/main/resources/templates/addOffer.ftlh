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
                Добавление предложения об охоте
            </p>
            <div class="detail">
                <form action="/area/${id}/add">

                    <label for="eventCost">Стоимость организации охоты</label><br>
                    <input type="number" id="eventCost" name="eventCost"
                           min="0" max="1000000" value="5000"><br>

                    <label>
                        <label for="start">Дата открытия охоты</label>
                        <input type="date" id="start" name="openingDate"
                               value="2022-12-14"
                               min="2022-12-14" max="2023-06-31">
                    </label> <br>
                    <label>
                        <label for="closingDate">Дата закрытия</label>
                        <input type="date" id="closingDate" name="closingDate"
                               value="2022-12-15"
                               min="2022-12-14" max="2023-06-31">
                    </label> <br>

                    <br>
                    <input id="resourceInput" class="input_form" type="text" name="huntingResource"
                           placeholder="Вид охотничьих ресурсов">
                    <div id="resultsContainer"></div>
                    <br>

                    <label for="method">Способ охоты:</label><br>

                    <select name="method" id="method">
                        <option value="0">Все способы</option>
                        <option value="1">Вольерная охота</option>
                        <option value="2">Натаска и нагонка собак</option>
                        <option value="3">Организация загонной охоты</option>
                        <option value="4">Организация охоты на реву</option>
                        <option value="5">Организация охоты с гончими собаками</option>
                        <option value="6">Организация охоты с засидки</option>
                        <option value="7">Организация охоты с лайками</option>
                        <option value="8">Организация охоты с легавыми собаками</option>
                        <option value="9">Организация охоты с луком/арболетом</option>
                        <option value="10">Организация охоты с подхода</option>
                        <option value="11">Самостоятельная охота</option>
                    </select><br><br>

                    <label for="guiding">Сопровождение:</label><br>

                    <select name="guiding" id="guiding">
                        <option value="0">Не выбрано</option>
                        <option value="1">Охота с егерем</option>
                        <option value="2">Охота без сопровождения</option>
                        <option value="3">Частичное сопровождение</option>
                    </select><br><br>
                    <p>Описание<Br>
                        <textarea name="description" cols="60" rows="5"></textarea></p><br>

                    <input type="submit" value="Добавить"/>
                </form>
                <br>
                <form action="/area/${id}">
                    <button type="submit">Отмена</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="/javaScript/seachResourses.js"></script>
</body>

</html>
