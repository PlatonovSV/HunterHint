<!DOCTYPE html>
<html>
<head>
    <title>
        HunterHint
    </title>
</head>
<body>
<H1>HunterHint</H1><hr>
<H2>${farm.name}</H2>

<b>${farm.regionName}</b><br>
<b>${farm.municipalDistrictName}</b><br>
Координаты лагеря в десятичном формате:<BR>
широта — ${farm.firstCord}, долгота — ${farm.secondCord}<br>
Площадь: ${farm.area}<br>
<#if farm.hotel>Вместительность гостиницы ${farm.hotelCapacity}<br>
Стоимость проживания ${farm.accommodationCost}</#if><br>
<#if farm.bath>Баня &#10003</#if><br>
<hr>
<b>${offer.methodName}</b><br>
<b>${offer.guidingPreferenceName}</b><br>
Дата открытия <b>${offer.openingDate.toString()}</b><br>
Дата зактытия <b>${offer.closingDate.toString()}</b><br>
Стоимость организации охоты: <b>${offer.eventCost}</b></p>
<p>${offer.description}</p>
    <b>${offer.resourcesTypeName}</b><br><br>

<form action="/area/${farm.id}/${offer.id}/${checkInDate}/${leaveDate}" method="get">
    <div>
        <#list resources as resource>
            <p>
            <input type="radio" id="${resource.id}"
                   name="resId" value="${resource.id}">
            <label for="${resource.id}">${resource.trophyValue} <b>${resource.cost} &#8381</b></label>
            </p>
        <#else>
            <p>
                Информация о стоимости трофеев не добавлена
            </p>
        </#list>
        <br>

        <p>${dates}</p>

        <label for="guests">Колличество гостей</label>
        <input type="number" id="guests" name="guests"
               min="0" max="200" value="1">
        <br>
        <label for="hunters">Колличество охотников</label>
        <input type="number" id="hunters" name="hunters"
               min="1" max="200" value="1">
        <br>
        <p>
            <input type="checkbox" checked name="hotel"/>Необходима гостиница
        </p>
        <p>Пожелания<Br>
            <textarea name="description" cols="60" rows="5"></textarea></p>
        <br>
        <p>
            <input type="checkbox" name="book"/>Подтвердить бронирование
        </p>
    </div>
    <input type="submit" value="Забронировать"/>
</form>
<hr>
<form action="/area/${farm.id}">
    <button type="submit">Назад</button>
</form><br><br>
<form action="/">
    <button type="submit">Вернуться на главную</button>
</form><br><br>
<#if thisOwnerOrAdmin>
<hr>
<form action="/area/${farm.id}/${offer.id}/addCost">
    <input type="submit" value="Добавить информацию о стоимости трофея"/>
</form><br>
<#list resources as resource>
    <p>
        ${resource.trophyValue} <b>${resource.cost} &#8381</b>
    <form action="/area/${farm.id}/${offer.id}/${resource.id}/delete">
        <input type="submit" value="Удалить"/>
    </form>
    </p>
<#else>
    <p>
        Информация о стоимости трофеев не добавлена
    </p>
</#list>
<form action="/area/${farm.id}/${offer.id}/delete">
    <input type="submit" value="Удалить данные о предложении об охоте"/>
</form>
</#if>
</body>
</html>