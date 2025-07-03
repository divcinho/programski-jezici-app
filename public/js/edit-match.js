const params = new URLSearchParams(location.search);
const id = params.get('id');

if(!id || id == ''){
    window.location.href = '/match.html';
} else {
    axios.get('/api/match/' + id).then(response => {
        let matchId = document.getElementById('match-id');
        matchId.value = response.data.id;

        let homeTeam = document.getElementById('home-team');
        homeTeam.value = response.data.homeTeam;

        let awayTeam = document.getElementById('away-team');
        awayTeam.value = response.data.awayTeam;

        let competition = document.getElementById('competition');
        competition.value = response.data.competition;

        let startsAt = document.getElementById('starts-at');
        startsAt.value = response.data.startsAt;

        let stadium = document.getElementById('stadium');
        stadium.value = response.data.stadium;

        document.getElementById('edit-match').addEventListener('submit', (e) => {
            e.preventDefault();

            axios.request({
                url: `/api/match/${response.data.id}`,
                method: 'put',
                data: {
                    homeTeam: homeTeam.value,
                    awayTeam: awayTeam.value,
                    competition: competition.value,
                    startsAt: startsAt.value,
                    stadium: stadium.value
                }
            }).then(response => window.location.href = '/match.html')
        })
    })
}