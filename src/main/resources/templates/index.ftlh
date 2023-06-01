<!doctype html>
<html class="no-js" lang="">

<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta property="og:title" content="">
    <meta property="og:type" content="">
    <meta property="og:url" content="">
    <meta property="og:image" content="">

    <link rel="manifest" href="site.webmanifest">
    <link rel="apple-touch-icon" href="icon.png">
    <!-- Place favicon.ico in the root directory -->

    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/main.css">

    <meta name="theme-color" content="#fafafa">
</head>

<body>
<div class="container">
    <div class="head">
        <p class="logo">
        </p>
        <div class="menu">
            <div class="menuSub">
                <#if !sessionNotNull>
                    <a class="menuItem" href="/login">Войти</a>
                <#else >
                    <a class="menuItem" href="/login">Меню</a>
                </#if>

                <a class="menuItem" href="https://www.apple.com/">Item</a>
                <a class="menuItem" href="https://www.apple.com/">Item</a>
            </div>
            <div class="menuSub">
                <a class="menuItem" href="https://www.apple.com/">Item</a>
                <a class="menuItem" href="https://www.apple.com/">Item</a>
                <#if isOwnerOrAdmin>
                    <a class="menuItem" href="/area/add">Создать</a>
                </#if>
            </div>
        </div>
        =
    </div>
    <div class="body">
        <div class="advertisement">
            <p class="advertisementStart">Реклама</p>
            <a class="linkBlock" href="https://www.apple.com/">
                <div class="advertisementBock">
                    <img class="advertisementImg" src="img/apAd1.jpg" alt="">
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
                    <img class="advertisementImg" src="img/apAd1.jpg" alt="">
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
                    <img class="advertisementImg" src="img/apAd1.jpg" alt="">
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
                    <img class="advertisementImg" src="img/apAd1.jpg" alt="">
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
        <div class="mainPart">
            <div class="block">
                <input type="checkbox" id="read-more-checker" data-more-checker="read-more"/>
                <div class="limiter" data-limiter="searchFilter">
                    <div class="searchFilter">
                        <form id="search_form" action="/apply/" method="POST">
                            <div id="loader" class="hidden">Отправляем...</div>
                            <div class="flexBlock">
                                <div class="subBlock">
                                    <input id="cityInput" class="input_form" name="region" type="text"
                                           placeholder="Регион"
                                           autocomplete="off"><br><br>
                                    <input id="resourceInput" type="text" class="input_form" name="huntingResource"
                                           placeholder="Вид охотничьих ресурсов" autocomplete="off">
                                    <button id="filterButton" type="submit">Filter</button>
                                    <br>
                                    <br>
                                    <div id="resultsContainer"></div>
                                </div>
                                <div class="subBlock">
                                    <div>
                                        <label>
                                            <input type="text" name="district" placeholder="Муниципальный район">
                                        </label>
                                        <br>
                                        <p>
                                            <input type="checkbox" checked name="hotel"/>Гостиница
                                        </p>
                                        <p>
                                            <input type="checkbox" checked name="bath"/>Баня
                                        </p>

                                    </div>
                                    <div><label>
                                            <label for="start">Дата начала охоты</label>
                                            <input type="date" id="start" name="checkInDate"
                                                   value="2022-12-14"
                                                   min="2022-12-14" max="2023-06-31">
                                        </label> <br>
                                        <label>
                                            <label for="leave">Дата отъезда</label>
                                            <input type="date" id="leave" name="leaveDate"
                                                   value="2022-12-15"
                                                   min="2022-12-14" max="2023-06-31">
                                        </label> <br>
                                        <p>
                                            <input type="checkbox" checked name="noDate"/>Не учитывать даты
                                        </p><br>
                                        <label for="capacity">Колличество гостей</label>
                                        <input type="number" id="capacity1" name="guests"
                                               min="0" max="200" value="0"><br>
                                        <label for="capacity1">Колличество охотников</label>
                                        <input type="number" id="capacity2" name="hunters"
                                               min="1" max="200" value="1"><br>

                                        <label for="price">Стоимость охоты</label>
                                        <input type="number" id="price" name="price"
                                               min="1" max="5000000" value="100000"><br><br>
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
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="bottom"></div>
                </div>
                <label for="read-more-checker" class="read-more-button"></label>
            </div>
            <div class="content" id="groundsContainer">
            </div>
        </div>
    </div>
</div>

<script src="javaScript/vendor/modernizr-3.11.2.min.js"></script>
<script src="javaScript/plugins.js"></script>
<script src="javaScript/seachFilters.js"></script>
<script src="javaScript/sendFilters.js"></script>
<script src="javaScript/seachResourses.js"></script>
</body>

</html>
