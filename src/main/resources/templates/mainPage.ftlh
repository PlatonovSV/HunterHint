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
    <#else >
        <button onclick="window.location.href = '/personal'">Личный кабинет</button>
</#if>

<H4>Найдите подходящие для вас охотничьи угодья</H4>
<form>
    <div><label>
            <input type="text" name="region" placeholder="Регион">
        </label> <br>
        <label>
            <input type="text" name="district" placeholder="Муниципальный район">
        </label>
        <br>
        <label>
            <input type="text" name="huntingResource" placeholder="Вид охотничьих ресурсов">
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
        <input type="number" id="capacity" name="guests"
               min="1" max="200" value="1"><br>
        <label for="capacity">Колличество охотников</label>
        <input type="number" id="capacity" name="hunters"
               min="1" max="200" value="1"><br>

        <label for="price">Стоимость охоты</label>
        <input type="number" id="price" name="price"
               min="1" max="5000000" value="10000"><br><br>
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
<#list farms as farm>
    <div>
        <p><b>${farm.name}</b><br>
            <b>${farm.regionName}</b>,<br>
            <b>${farm.municipalDistrictName}</b><br>
            площадь <b> ${farm.area}</b> кв.км<br>
            от <b>${farm.minCost} &#8381</b><br>
            <a href="/area/${farm.id}">Подробнее</a></p>
    </div>
<#else>
    <h3>Охотничьи хозяйства не найдены</h3>
</#list>
<#if isOwnerOrAdmin>
    <form action="/area/add">
        <button type="submit">Добавьте свои охотничьи угодья</button>
    </form>
</#if>
</body>
</html>