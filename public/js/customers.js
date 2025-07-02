let customersTemplate = document.getElementById('customers-template');
let customersTable = document.getElementById('customers-table');

axios.get('/api/customer').then(response => {
    response.data.forEach(customer => {
        let copy = customersTemplate.content.cloneNode(true);
        copy.querySelector('.id').innerText = customer.id;
        copy.querySelector('.name').innerText = `${customer.name} ${customer.surname}`;
        copy.querySelector('.email-address').innerText = customer.emailAddress;
        copy.querySelector('.phone-number').innerText = customer.phoneNumber;
        copy.querySelector('.created-at').innerText = new Date(customer.createdAt).toLocaleString('sr-RS');
        
        if(customer.updatedAt != null){
            copy.querySelector('.updated-at').innerText = new Date(customer.updatedAt).toLocaleString('sr-RS');
        } else {
            copy.querySelector('.updated-at').innerText = 'N/A';
        }

        copy.querySelector('.edit-button-link').href = '/edit/customer.html?id=' + customer.id;

        copy.querySelector('.delete-button').addEventListener('click', () => {
            axios.request({
                url: `/api/customer/${customer.id}`,
                method: 'delete'
            }).then(response => window.location.reload());
        });

        customersTable.appendChild(copy);
    });
});