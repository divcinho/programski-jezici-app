const params = new URLSearchParams(location.search);
const id = params.get('id');

if(!id || id == ''){
    window.location.href = '/ticket.html';
} else {
    axios.get('/api/ticket/' + id).then(response => {
        let ticketId = document.getElementById('ticket-id');
        ticketId.value = response.data.id;

        let customerSelect = document.getElementById('customer-select');
        let selectedCustomer = response.data.customer.id;
        axios.get('/api/customer').then(response => {
            response.data.forEach(customer => {
                let optionTag = document.createElement('option');
                optionTag.value = customer.id;
                optionTag.innerText = customer.emailAddress;
                customerSelect.appendChild(optionTag);
            });
            customerSelect.value = selectedCustomer;
        });

        let matchSelect = document.getElementById('match-select');
        let selectedMatch = response.data.match.id;
        axios.get('/api/match').then(response => {
            response.data.forEach(match => {
                let optionTag = document.createElement('option');
                optionTag.value = match.id;
                optionTag.innerText = `${match.homeTeam} - ${match.awayTeam}`;
                matchSelect.appendChild(optionTag);
            });
            matchSelect.value = selectedMatch;
        });

        let stand = document.getElementById('stand');
        stand.value = response.data.stand;

        let sector = document.getElementById('sector');
        sector.value = response.data.sector;

        let seat = document.getElementById('seat');
        seat.value = response.data.seat;

        let price = document.getElementById('price');
        price.value = response.data.price;

        document.getElementById('edit-ticket').addEventListener('submit', (e) => {
            e.preventDefault();

            axios.request({
                url: `/api/ticket/${response.data.id}`,
                method: 'put',
                data: {
                    customer: {id: customerSelect.value},
                    match: {id: matchSelect.value},
                    stand: stand.value,
                    sector: sector.value,
                    seat: seat.value,
                    price: price.value
                }
            }).then(response => window.location.href = '/ticket.html')
        })
    })
}