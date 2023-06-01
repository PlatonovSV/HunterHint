function serializeForm(formNode) {
    return new FormData(formNode)
}

async function handleFormSubmit(event) {
    event.preventDefault()
    const data = serializeForm(event.target)
    toggleLoader()

    const result = await (await sendData(data)).json()
    if (result === "alreadyExists") {
        onSuccess(event.target, "Пользователь с указанным email уже существует")
    } else {
        window.location.href = '/successful';
    }
    toggleLoader()

}



function onSuccess(formNode, result) {
    alert(result)
    console.log(result)
    formNode.classList.toggle('hidden')
}



const applicantForm = document.getElementById('reg_form')
applicantForm.addEventListener('submit', handleFormSubmit)

async function sendData(data) {
    return await fetch('http://localhost:3000/registration', {
        method: 'POST',
        //headers: { 'Content-Type': 'multipart/form-data' },
        body: data,
    })
}


function toggleLoader() {
    const loader = document.getElementById('loader')
    loader.classList.toggle('hidden')
}
function onError(error) {
    alert(error.message)
}

function checkValidity(event) {
    const formNode = event.target.form
    const isValid = formNode.checkValidity()

    formNode.querySelector('button').disabled = !isValid
}

applicantForm.addEventListener('input', checkValidity)
