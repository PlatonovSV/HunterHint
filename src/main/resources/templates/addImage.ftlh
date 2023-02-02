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
<form action="/image/add/${id}" method="post" enctype="multipart/form-data">
    <p>>
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
</body>
</html>