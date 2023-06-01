function serializeForm(formNode) {
    return new FormData(formNode)
}

async function handleFormSubmit(event) {
    event.preventDefault()
    const data = serializeForm(event.target)

    const result = await (await sendData(data)).json()
    if (result === "false") {
        onSuccess(event.target, "Неверный логин или пароль")
    } else {
        const user = {
            id: result.id,
            accessLevel: result.accessLevel,
            email: result.email,
            lastName: result.lastName,
            nameId: result.nameId,
            patronymic: result.patronymic,
            phoneNumber: result.phoneNumber
        }
        const authentication = await fetch('/authentication', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(user)
        })
        if (await authentication.json() === "true") {
            window.location.href = '/';
        }
    }
}
function onSuccess(formNode, result) {
    alert(result)
    console.log(result)
    formNode.classList.toggle('hidden')
}

const applicantForm = document.getElementById('login_form')
applicantForm.addEventListener('submit', handleFormSubmit)

async function sendData(data) {
    return await fetch('http://localhost:3000/login', {
        method: 'POST',
        //headers: { 'Content-Type': 'multipart/form-data' },
        body: data,
    })
}
function checkValidity(event) {
    const formNode = event.target.form
    const isValid = formNode.checkValidity()

    formNode.querySelector('button').disabled = !isValid
}

applicantForm.addEventListener('input', checkValidity)
