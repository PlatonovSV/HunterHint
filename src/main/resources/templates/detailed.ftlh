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
                ${farm.name}
            </p>
            <div class="slideshow-container">
                <#list images as image>
                    <div class="mySlides fade">
                        <div class="numberMain"> ${image?index + 1} / ${images?size}</div>
                        <img src="${image.getSource()}" style="width:100%" alt="">
                    </div>
                </#list>

                <a class="prev" onclick="mainSl.plusSlides(-1)">&#10094;</a>
                <a class="next" onclick="mainSl.plusSlides(1)">&#10095;</a>
            </div>
            <div class="detail">
                <b>${farm.regionName}</b><br>
                <b>${farm.municipalDistrictName}</b><br><br>
                Координаты лагеря в десятичном формате:<BR>
                широта — ${farm.firstCord}, долгота — ${farm.secondCord}<br>
                Площадь: ${farm.area} Га<br><br>
                <#if farm.hotel>Вместительность гостиницы ${farm.hotelCapacity}<br>
                Стоимость проживания ${farm.accommodationCost} &#8381;</#if><br><br>
                <#if farm.bath>Баня &#10003</#if><br>
                ${farm.description}<br>
                <p>Директор охотхозяйства: ${user.lastLame} ${user.patronymic} ${user.nameSrt}<br>
                    Тел. ${user.phoneNumber}<br>
                    ${farm.companyStr}
                </p>
            </div>
            <div class="flexBlock">
                <div class="subBlock">
                    <form class="select_offer" id="search_form" action="/apply/" method="POST">
                        <div>
                            <div id="loader" class="hidden">Отправляем...</div>
                            <br>
                            <input id="resourceInput" class="input_form" type="text" name="huntingResource"
                                   placeholder="Вид охотничьих ресурсов">
                            <div id="resultsContainer"></div>
                            <br><label>
                                <label for="start">Дата начала охоты</label>
                                <input class="input_form" type="date" id="start" name="checkInDate"
                                       value="2022-12-14"
                                       min="2022-12-14" max="2023-06-31">
                            </label> <br>
                            <label>
                                <label for="leave">Дата отъезда</label>
                                <input class="input_form" type="date" id="leave" name="leaveDate"
                                       value="2022-12-15"
                                       min="2022-12-14" max="2023-06-31">
                            </label> <br>
                            <p>
                                <input type="checkbox" class="custom_checkbox" checked name="noDate"/> Не учитывать даты
                            </p><br>
                            <label for="capacity">Колличество гостей</label>
                            <input class="input_form" type="number" id="capacity" name="guests"
                                   min="0" max="200" value="0"><br>
                            <label for="capacity">Колличество охотников</label>
                            <input class="input_form" type="number" id="capacity" name="hunters"
                                   min="1" max="200" value="1"><br>
                        </div>
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
                        <input type="hidden" name="groundId" value="${farm.id}">
                        <button id="filterButton" type="submit">Filter</button>
                    </form>
                </div>
                <div class="subBlock">
                    <div id="offersContainer">

                    </div>
                </div>
            </div>
            <br><br>
            <#if isOwnerOrAdmin>
                <hr>
                <form action="/area/${farm.id}/add">
                    <input type="submit" value="Добавить предложение об охоте"/>
                </form><br>
                <form action="/loadImage/${farm.id}">
                    <input type="submit" value="Добавить изображение"/>
                </form><br>
                <form action="/area/${farm.id}/delete">
                    <input type="submit" value="Удалить данные об охотничьем хозяйстве"/>
                </form>
            </#if>
        </div>
    </div>
    <div class="comments">
        <#list comments as comment>
            <div class="block">
                <input type="checkbox" id="read-more-checker${comment?index + 1}"
                       data-more-checker="read-more${comment?index + 1}"/>
                <div class="limiter">
                    <div class="userComment">
                        <div class="commentHeadAndImage">
                            <div class="userNameAndDate">
                                <div class="commentUserName">${comment.userName}<br>
                                    ${comment.lastName}
                                </div>
                                <div class="commentDate">${comment.date}</div>
                            </div>
                            <div class="slideshowContainerComment">
                                <#list comment.links as link>
                                    <div class="mySlidesComment${comment?index + 1} fade">
                                        <div class="numberTextComment">${link?index + 1} / ${comment.links?size}</div>
                                        <img src="${link.getSource()}" style="width:100%" alt="">
                                    </div>
                                </#list>

                                <a class="prevComment" onclick="comSl${comment?index + 1}.plusSlides(-1)">&#10094;</a>
                                <a class="nextComment" onclick="comSl${comment?index + 1}.plusSlides(1)">&#10095;</a>
                            </div>
                        </div>
                        <div class="commentText">
                            ${comment.comment}
                        </div>
                    </div>
                    <div class="bottom"></div>
                </div>
                <label for="read-more-checker${comment?index + 1}" class="read-more-button"></label>
            </div>
        </#list>
    </div>
</div>

<script src="/javaScript/slides.js"></script>
<script src="/javaScript/sendOfferFilters.js"></script>
<script src="/javaScript/seachResourses.js"></script>
</body>

</html>
