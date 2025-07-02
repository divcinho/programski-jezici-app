document.getElementById('add-customer').addEventListener('submit', (e) => {
    e.preventDefault();

    axios.request({
        url: `/api/customer`,
        method: 'post',
        data: {
            name: document.getElementById('name').value,
            surname: document.getElementById('surname').value,
            emailAddress: document.getElementById('email-address').value,
            phoneNumber: document.getElementById('phone-number').value
        }
    }).then(response => window.location.href = '/customer.html')
});