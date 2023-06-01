let list = [];

fetch('http://localhost:3000/seach-resourses')
  .then(response => response.json())
  .then(name => {
    list = name;

    const resInput = document.getElementById('resourceInput');
    resInput.addEventListener('input', () => {
      const searchTerm = resInput.value.trim().toLowerCase();
      const matchingCities = list.filter(resource => resource.name.toLowerCase().includes(searchTerm));
      const resultsContainer = document.getElementById('resultsContainer');
      resultsContainer.innerHTML = '';


      matchingCities.forEach(resource => {
        const resElement = document.createElement('div');
        resElement.innerText = resource.name;
        resElement.classList.add('search');
        resultsContainer.appendChild(resElement);
        resElement.addEventListener('click', () => {
          resInput.value = resource.name;
          resultsContainer.innerHTML = '';
        });
      });
    });
  })
  .catch(error => {
    console.error('Error fetching JSON data:', error);
  });
