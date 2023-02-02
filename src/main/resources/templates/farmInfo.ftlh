<!DOCTYPE html>
<html>
<head>
    <title>
        HunterHint
    </title>
</head>
<body>
<H1>HunterHint</H1>
<hr>
<#if !sessionNotNull>
    <button onclick="window.location.href = '/login'">Log In</button>
</#if><br>
<div style="text-align: center">
<H2>${farm.name}</H2>
</div>
<#list images as img>
<div style="text-align: center">
    <img src="/images/${img.id}"
         style="border:2px solid black; border-radius: 20px" height="300">
</div>
</#list>

<b>${farm.regionName}</b><br>
<b>${farm.municipalDistrictName}</b><br>
Координаты лагеря в десятичном формате:<BR>
широта — ${farm.firstCord}, долгота — ${farm.secondCord}<br>
Площадь: ${farm.area}<br><br>
<#if farm.hotel>Вместительность гостиницы ${farm.hotelCapacity}<br>
Стоимость проживания ${farm.accommodationCost}</#if><br><br>
<#if farm.bath>Баня &#10003</#if><br>
${farm.description}<br>
<p>Директор охотхозяйства: ${user.lastLame} ${user.patronymic} ${user.nameSrt}<br>
    Тел. ${user.phoneNumber}<br>
    ${farm.companyStr}
</p>
<hr>

<form action="/area/${farm.id}" method="get">
    <div>
        <br>
        <label>
            <input type="text" name="huntingResource" placeholder="Вид охотничьих ресурсов">
        </label><br>
        <br><label>
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
        <input type="number" id="capacity" name="guests"
               min="0" max="200" value="0"><br>
        <label for="capacity">Колличество охотников</label>
        <input type="number" id="capacity" name="hunters"
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
    <input type="submit" value="Поиск"/>
</form>
<hr>
<#list offers as offer>
    <div>
        <p><b>${offer.resourcesTypeName}</b> <br>
            Стоимость охоты от <b>${offer.minCost + hotelPrice} &#8381</b><br>
            <b>${offer.methodName}</b><br>
            <b>${offer.guidingPreferenceName}</b><br>
            Дата открытия <b>${offer.openingDate.toString()}</b><br>
            Дата зактытия <b>${offer.closingDate.toString()}</b><br>
            <a href="/area/${farm.id}/${offer.id}/${checkInAtr}/${leaveAtr}">Подробнее</a></p>
    </div>
<#else>
    <p>Предложения об охоте не найдены</p>
</#list>
<hr>
<#list comments as comment>
    <div>
        <b>${comment.userName}</b>
        <p>${comment.review}</p>
        <#list comment.imageLinks as img>
            <img src="/images/${img.id}" height="400px"/><br><br>
        </#list>
    </div>
<#else>
    <p>Комментарии ещё не добавлены</p>
</#list>
<form action="/">
    <button type="submit">Вернуться на главную</button>
</form>
<br><br>
<#if thisOwnerOrAdmin>
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
</body>
</html>