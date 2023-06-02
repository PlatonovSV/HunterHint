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
                Информация о бронировании охоты
            </p>
            <div class="detail">
                <p>
                <pre style="font-family:arial; font-size:18px;">${info}</pre>
                </p>
                Дата и время бронирования <b>${bookingTime}</b><br>
                <hr>
                <br>
                <#list images as img>
                    <img src="/images/${img.id}" height="400px"/><br><br>
                </#list>
                <br>
                <form action="/booking/${id}" method="get">
                    <DIV>
                        <p>
                        <pre style="font-family:arial; font-size:18px;">${review}</pre>
                        </p>
                        <p>Новый отзыв<Br>
                            <textarea name="newReview" cols="70" rows="6"></textarea>
                        </p><br>
                    </DIV>
                    <input type="submit" value="Отправить"/>
                </form>
                <hr>
                <form action="/loadImage/${id}">
                    <input type="submit" value="Добавить изображение"/>
                </form>
                <br>
                <form action="/">
                    <button type="submit">Вернуться на главную</button>
                </form>
                <br><br>
                <form action="/booking/${id}/delete">
                    <input type="submit" value="Удалить бронирование охоты"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

</html>
