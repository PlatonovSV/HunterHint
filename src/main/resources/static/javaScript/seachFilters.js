let cityList = [];

fetch('http://localhost:3000/regiones')
  .then(response => response.json())
  .then(name => {
    cityList = name;

    const cityInput = document.getElementById('cityInput');
    cityInput.addEventListener('input', () => {
      const searchTerm = cityInput.value.trim().toLowerCase();
      const matchingCities = cityList.filter(city => city.name.toLowerCase().includes(searchTerm));
      const resultsContainer = document.getElementById('resultsContainer');
      resultsContainer.innerHTML = '';


      matchingCities.forEach(city => {
        const cityElement = document.createElement('div');
        cityElement.innerText = city.name;
        cityElement.classList.add('search');
        resultsContainer.appendChild(cityElement);
        cityElement.addEventListener('click', () => {
          cityInput.value = city.name;
          resultsContainer.innerHTML = '';
        });
      });
    });
  })
  .catch(error => {
    console.error('Error fetching JSON data:', error);
  });
