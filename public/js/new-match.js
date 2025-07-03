document.getElementById('add-match').addEventListener('submit', (e) => {
    e.preventDefault();

    axios.request({
        url: `/api/match`,
        method: 'post',
        data: {
            homeTeam: document.getElementById('home-team').value,
            awayTeam: document.getElementById('away-team').value,
            competition: document.getElementById('competition').value,
            startsAt: document.getElementById('starts-at').value,
            stadium: document.getElementById('stadium').value
        }
    }).then(response => window.location.href = '/match.html')
});