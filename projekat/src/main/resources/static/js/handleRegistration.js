$(document).ready(function(){
handleFormSubmission();



function checkEmptyFields(form) {
    const fields = form.querySelectorAll(
        'input[type="text"], input[type="number"], input[type="email"], input[type="password"]'
    );
    let isEmpty = false;

    fields.forEach(field => {
        const errorSpan = field.nextElementSibling;
        
        if (
            !field.value.trim()) 
        {
            isEmpty = true;
            errorSpan.textContent = 'This field is required';
        } 
        else {
            errorSpan.textContent = '';
        }
    });

    return isEmpty;
}

async function handleFormSubmission() {
    const form = document.getElementById("registrationForm");
    form.addEventListener('submit', async function(event) {
        event.preventDefault();

        if (checkEmptyFields(form)) {
            return;
        }
        
		const emailInput = form.querySelector('input[name="email"]');
        const email = emailInput ? emailInput.value.trim() : '';
        
        const usernameInput = form.querySelector('input[name="username"]');
        const username = usernameInput ? usernameInput.value.trim() : '';        

        const [emailTaken, usernameTaken] = await Promise.all([isEmailTaken(email), isUsernameTaken(username)]);

        if (emailTaken) {
            const emailErrorSpan = emailInput.nextElementSibling;
            emailErrorSpan.textContent = 'Email is already taken';
            return;
        }

        if (usernameTaken) {
            const usernameErrorSpan = usernameInput.nextElementSibling;
            usernameErrorSpan.textContent = 'Username is already taken';
            return;
        }


        try {
            await new Promise((resolve) => {
                form.submit();
                resolve();
            });
        } catch (error) {
            console.error('Error submitting form:', error);
        }
    });
}

function checkExistingUser(username, email) {
    return $.ajax({
        url: 'users/check',
        method: 'POST',
        data: {
            username: username,
            email: email
        },
        dataType: 'json'
    });
}

async function isUsernameTaken(username) {
    const response = await checkExistingUser(username, '');
    return response.usernameExists;
}


async function isEmailTaken(email) {
    const response = await checkExistingUser('', email);
    return response.emailExists;
}




})