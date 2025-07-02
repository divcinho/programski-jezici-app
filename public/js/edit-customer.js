const params = new URLSearchParams(location.search);
const id = params.get('id');

if(!id || id == ''){
    window.location.href = '/customer.html';
} else {
    axios.get('/api/customer/' + id).then(response => {
        let customerId = document.getElementById('customer-id');
        customerId.value = response.data.id;

        let name = document.getElementById('name');
        name.value = response.data.name;

        let surname = document.getElementById('surname');
        surname.value = response.data.surname;

        let emailAddress = document.getElementById('email-address');
        emailAddress.value = response.data.emailAddress;

        let phoneNumber = document.getElementById('phone-number');
        phoneNumber.value = response.data.phoneNumber;

        document.getElementById('edit-customer').addEventListener('submit', (e) => {
            e.preventDefault();

            axios.request({
                url: `/api/customer/${response.data.id}`,
                method: 'put',
                data: {
                    name: name.value,
                    surname: surname.value,
                    emailAddress: emailAddress.value,
                    phoneNumber: phoneNumber.value
                }
            }).then(response => window.location.href = '/customer.html')
        })
    })
}