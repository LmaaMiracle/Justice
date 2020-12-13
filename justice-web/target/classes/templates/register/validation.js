const form = document.getElementById('form');
const email = document.getElementById('email');
const password = document.getElementById('password');
const speciality = document.getElementById('speciality');
const first_name = document.getElementById('firstname');
const last_name = document.getElementById('lastname');

form.addEventListener('submit', e => {
    e.preventDefault();

    checkInputs();
});

function checkInputs() {
    // trim to remove the whitespaces
    const emailValue = email.value.trim();
    console.log("i've got here wtffff");
    const passwordValue = password.value.trim();
    const specialityValue = speciality.value.trim();
    const first_nameValue = first_name.value.trim();
    const last_nameValue = last_name.value.trim();

    if (emailValue === '') {
        setErrorFor(email, 'Email cannot be blank');
    } else if (!isEmail(emailValue)) {
        setErrorFor(email, 'Not a valid email');
    } else {
        setSuccessFor(email);
    }

    if (passwordValue === '') {
        setErrorFor(password, 'Password cannot be blank');
    } else {
        setSuccessFor(password);
    }

    if (specialityValue === '') {
        setErrorFor(specialityValue, 'Speciality cannot be blank');
    } else {
        setSuccessFor(specialityValue);
    }

    if (first_nameValue === '') {
        setErrorFor(first_nameValue, 'First name cannot be blank');
    } else {
        setSuccessFor(first_nameValue);
    }

    if (last_nameValue === '') {
        setErrorFor(last_nameValue, 'Last name cannot be blank');
    } else {
        setSuccessFor(last_nameValue);
    }
}

function setErrorFor(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');
    formControl.className = 'form-control error';
    small.innerText = message;
}

function setSuccessFor(input) {
    const formControl = input.parentElement;
    formControl.className = 'form-control success';
}

function isEmail(email) {
    return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}