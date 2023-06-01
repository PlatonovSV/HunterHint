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
    const result = await (await sendData(JSON.stringify(object))).json()


    const groundContainer = document.getElementById('groundsContainer');
    groundContainer.innerHTML = '';
    result.forEach(ground => {
        const groundElement = document.createElement('a');
        groundElement.href = 'area/' + ground.id;
        groundElement.classList.add('ground');

        const groundImg = document.createElement('img');
        groundImg.classList.add('groundImg');
        groundImg.src = ground.preview;
        groundImg.alt = ground.name;

        const groundName = document.createElement('p');
        groundName.classList.add('groundName');
        groundName.textContent = ground.name;

        let bath;
        if (ground.bath) {
            bath = '&#9989;'
        } else {
            bath = '&#10005;'
        }
        let hotel;
        if (ground.hotel) {
            hotel = '&#9989;'
        } else {
            hotel = '&#10005;'
        }
        const groundDescription = document.createElement('p');
        groundDescription.classList.add('description');
        groundDescription.innerHTML = 'Гостиница ' + hotel + '<br>' +
            'Баня ' + bath + '<br>' +
            'Площадь ' + ground.area + '<br>' +
            'Стоимость охоты от ' + ground.minCost + ' &#8381;';

        const groundCity = document.createElement('p');
        groundCity.classList.add('place');
        groundCity.innerHTML = ground.regionName + '<br>' + ground.municipalDistrictName;

        const groundDate = document.createElement('div');
        groundDate.classList.add('hotelDate');

        groundDate.appendChild(groundName);
        groundDate.appendChild(groundDescription);
        groundDate.appendChild(groundCity);

        groundElement.appendChild(groundImg);
        groundElement.appendChild(groundDate);
        groundContainer.appendChild(groundElement);
    });


    toggleLoader()

}

const applicantForm = document.getElementById('search_form')
applicantForm.addEventListener('submit', handleFormSubmit)

async function sendData(data) {

    return await fetch('http://localhost:8181/find', {
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
