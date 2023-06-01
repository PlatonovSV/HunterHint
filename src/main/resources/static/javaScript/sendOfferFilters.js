function serializeForm(formNode) {
    return new FormData(formNode)
}

async function handleFormSubmit(event) {
    event.preventDefault()
    const data = serializeForm(event.target)
    toggleLoader()
    const object = {};
    data.forEach(function (value, key) {
        object[key] = value;
    });
    const res = await (await sendData(JSON.stringify(object))).json()

    const groundContainer = document.getElementById('offersContainer');
    groundContainer.innerHTML = '';
    if (res.size == 0) {
        groundContainer.innerHTML = '<br><br><br><strong style="font-size: 70px; text-align: center;">Не найдено</strong>';
    } else {
        res.forEach(result => {
            const groundElement = document.createElement('a');
            groundElement.href = '/area/' + result.farmId + '/' + result.id + '/' + result.openingDate + '/' + result.closingDate;
            groundElement.classList.add('offer');

            const groundName = document.createElement('p');
            groundName.classList.add('groundName');
            groundName.textContent = result.resourcesTypeName;

            const groundDescription = document.createElement('p');
            groundDescription.classList.add('description');
            groundDescription.innerHTML = 'Открытие охоты:<br>' + result.openingDate + '<br>' +
                'Зактытие<br>' + result.closingDate + '<br>Cтоимость от ' + result.minCost + '&#8381;';

            const groundCity = document.createElement('p');
            groundCity.classList.add('place');
            groundCity.innerHTML = result.guidingPreferenceName + '<br>' + result.methodName;

            const groundDate = document.createElement('div');
            groundDate.classList.add('hotelDate');

            groundDate.appendChild(groundName);
            groundDate.appendChild(groundDescription);
            groundDate.appendChild(groundCity);
            groundElement.appendChild(groundDate);
            groundContainer.appendChild(groundElement);
        });
        toggleLoader()
    }

}

const applicantForm = document.getElementById('search_form')
applicantForm.addEventListener('submit', handleFormSubmit)

async function sendData(data) {

    return await fetch('http://localhost:8181/find-offer', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: data,
    })
}


function toggleLoader() {
    const loader = document.getElementById('loader')
    loader.classList.toggle('hidden')
}

// Получаем ссылку на кнопку
const button = document.getElementById('filterButton');

// Вызываем событие нажатия кнопки
button.click();
