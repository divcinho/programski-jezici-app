let ticketsTemplate = document.getElementById('tickets-template');
let ticketsTable = document.getElementById('tickets-table');

axios.get('/api/ticket').then(response => {
    response.data.forEach(ticket => {
        let copy = ticketsTemplate.content.cloneNode(true);
        copy.querySelector('.id').innerText = ticket.id;
        copy.querySelector('.customer-name-surname').innerText = `${ticket.customer.name} ${ticket.customer.surname}`;
        copy.querySelector('.match-home-away').innerText = `${ticket.match.homeTeam} - ${ticket.match.awayTeam}`;
        copy.querySelector('.stand').innerText = ticket.stand;
        copy.querySelector('.sector').innerText = ticket.sector;
        copy.querySelector('.seat').innerText = ticket.seat;
        copy.querySelector('.price').innerText = ticket.price;
        
        if(ticket.paidAt != null){
            copy.querySelector('.paid-at').innerText = new Date(ticket.paidAt).toLocaleString('sr-RS');
        } else {
            copy.querySelector('.paid-at').innerText = 'N/A';
        }

        if(ticket.usedAt != null){
            copy.querySelector('.used-at').innerText = new Date(ticket.usedAt).toLocaleString('sr-RS');
        } else {
            copy.querySelector('.used-at').innerText = 'N/A';
        }

        copy.querySelector('.created-at').innerText = new Date(ticket.createdAt).toLocaleString('sr-RS');
        
        if(ticket.updatedAt != null){
            copy.querySelector('.updated-at').innerText = new Date(ticket.updatedAt).toLocaleString('sr-RS');
        } else {
            copy.querySelector('.updated-at').innerText = 'N/A';
        }

        copy.querySelector('.edit-button-link').href = '/edit/ticket.html?id=' + ticket.id;

        copy.querySelector('.delete-button').addEventListener('click', () => {
            axios.request({
                url: `/api/ticket/${ticket.id}`,
                method: 'delete'
            }).then(response => window.location.reload());
        });

        ticketsTable.appendChild(copy);
    });
});