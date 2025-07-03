let ticketsTemplate = document.getElementById('tickets-template');
let ticketsTable = document.getElementById('tickets-table');

axios.get('/api/ticket').then(response => {
    response.data.forEach(ticket => {
        let copy = ticketsTemplate.content.cloneNode(true);
        copy.querySelector('.id').innerText = ticket.id;
        copy.querySelector('.customer-email').innerText = ticket.customer.emailAddress;
        copy.querySelector('.match-home-away').innerText = `${ticket.match.homeTeam} - ${ticket.match.awayTeam}`;
        copy.querySelector('.stand').innerText = ticket.stand;
        copy.querySelector('.seat').innerText = ticket.seat;
        copy.querySelector('.price').innerText = ticket.price;
        
        if(ticket.paidAt != null){
            copy.querySelector('.paid-at').innerText = new Date(ticket.paidAt).toLocaleString('sr-RS');
        } else {
            copy.querySelector('.paid-at').innerText = 'N/A';
        }

        copy.querySelector('.created-at').innerText = new Date(ticket.createdAt).toLocaleString('sr-RS');
        
        if(ticket.updatedAt != null){
            copy.querySelector('.updated-at').innerText = new Date(ticket.updatedAt).toLocaleString('sr-RS');
        } else {
            copy.querySelector('.updated-at').innerText = 'N/A';
        }

        if(new Date(ticket.match.startsAt) < new Date()){
            copy.querySelector('.pay-button').style.display = 'none';
            copy.querySelector('.edit-button').style.display = 'none';
            copy.querySelector('.delete-button').style.display = 'none';
        }

        if(ticket.paidAt != null){
            copy.querySelector('.pay-button').style.display = 'none';
        }

        copy.querySelector('.pay-button').addEventListener('click', () => {
            axios.request({
                url: `/api/ticket/${ticket.id}/pay`,
                method: 'put'
            }).then(response => window.location.reload());
        });

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