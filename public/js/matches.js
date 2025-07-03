let matchesTemplate = document.getElementById('matches-template');
let matchesTable = document.getElementById('matches-table');

axios.get('/api/match').then(response => {
    response.data.forEach(match => {
        let copy = matchesTemplate.content.cloneNode(true);
        copy.querySelector('.id').innerText = match.id;
        copy.querySelector('.home-team').innerText = match.homeTeam;
        copy.querySelector('.away-team').innerText = match.awayTeam;
        copy.querySelector('.competition').innerText = match.competition;
        copy.querySelector('.starts-at').innerText = new Date(match.startsAt).toLocaleString('sr-RS');
        copy.querySelector('.stadium').innerText = match.stadium;
        copy.querySelector('.created-at').innerText = new Date(match.createdAt).toLocaleString('sr-RS');
        
        if(match.updatedAt != null){
            copy.querySelector('.updated-at').innerText = new Date(match.updatedAt).toLocaleString('sr-RS');
        } else {
            copy.querySelector('.updated-at').innerText = 'N/A';
        }

        copy.querySelector('.edit-button-link').href = '/edit/match.html?id=' + match.id;

        copy.querySelector('.delete-button').addEventListener('click', () => {
            axios.request({
                url: `/api/match/${match.id}`,
                method: 'delete'
            }).then(response => window.location.reload());
        });

        matchesTable.appendChild(copy);
    });
});