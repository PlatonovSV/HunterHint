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
            <div class="detail">
                <form action="/image/add/${id}" method="post" enctype="multipart/form-data">
                    <p>
                    <div>
                        Загрузите изображение <input type="file" name="file"/>
                    </div>
                    </p>
                    <input type="submit" value="Добавить изображение"/>
                </form>
                <hr>
                <form action="/">
                    <button type="submit">Вернуться на главную</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>