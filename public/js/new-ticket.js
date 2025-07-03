let customerSelect = document.getElementById('customer-select');
axios.get('/api/customer').then(response => {
    response.data.forEach(customer => {
        let optionTag = document.createElement('option');
        optionTag.value = customer.id;
        optionTag.innerText = customer.emailAddress;
        customerSelect.appendChild(optionTag);
    });
});

let matchSelect = document.getElementById('match-select');
axios.get('/api/match').then(response => {
    response.data.forEach(match => {
        let optionTag = document.createElement('option');
        optionTag.value = match.id;
        optionTag.innerText = `${match.homeTeam} - ${match.awayTeam}`;
        matchSelect.appendChild(optionTag);
    });
});




document.getElementById('add-ticket').addEventListener('submit', (e) => {
    e.preventDefault();

    axios.request({
        url: `/api/ticket`,
        method: 'post',
        data: {
            customer: {id: document.getElementById('customer-select').value},
            match: {id: document.getElementById('match-select').value},
            stand: document.getElementById('stand').value,
            sector: document.getElementById('sector').value,
            seat: document.getElementById('seat').value,
            price: document.getElementById('price').value
        }
    }).then(response => window.location.href = '/ticket.html')
});