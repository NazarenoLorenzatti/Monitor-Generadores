var misGraficos = [];
var datalabels = []; // Almacena etiquetas para el eje X
var dataPoints = []; // Almacena los valores (0 o 1) para el gráfico
var host = [];


// Función para crear el gráfico de líneas
function crearGrafico() {
    document.querySelectorAll('.grafico canvas').forEach(function (canvas, index) {
        var ctx = canvas.getContext('2d');
        var points = [];
        var labels = [];

        dataPoints.push(points);
        datalabels.push(labels);
        misGraficos.push(new Chart(ctx, {
            type: 'line',
            data: {
                labels: datalabels[index],
                datasets: [{
                        label: '',
                        axis: 'x',
                        data: dataPoints[index],
                        fill: false,
                        borderColor: 'rgb(97, 130, 100)',
                        borderWidth: 2,
                        pointBackgroundColor: 'rgb(97, 130, 100)', // Color de los puntos
                        pointRadius: 5 // Tamaño de los puntos

                    }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        min: 0, // Valor mínimo del eje Y
                        max: 1, // Valor máximo del eje Y
                        beginAtZero: true, // No comenzar desde 0 (para que 0 esté debajo)
                        position: 'right',
                        bounds: 'data',
                        stacked: true
                    }
                }
            }
        }));
    });
}

// Función para realizar la solicitud de ping y actualizar el gráfico
function realizarPing() {
    document.querySelectorAll('.grafico canvas').forEach(function (canvas, index) {
        fetch('/host/ping/' + canvas.getAttribute('id'))
                .then(function (response) {
                    if (!response.ok) {
                        throw new Error('Error en la solicitud.');
                    }
                    return response.text();
                })
                .then(function (dataResponse) {

                    var response = JSON.parse(dataResponse);

                    //Agregar nombre a la Lista de nombres de Host
                    if (misGraficos[index] && misGraficos[index].data.datasets[0].label === '') {
                        misGraficos[index].data.datasets[0].label = response.hosts[0].nombreHost;
//                        misGraficos[index].update(); // Actualiza el gráfico
                    }

                    // Agregar etiqueta de tiempo actual
                    var currentTime = new Date().toLocaleTimeString();
                    var label = datalabels[index];
                    label.push(currentTime);
                    datalabels[index] = label;

                    // Agregar valor 0 o 1 en función de la respuesta
                    if (response.metadata[0].respuesta === "Dispositivo alcanzable") {
                        var point = dataPoints[index];
                        point.push(1);
                        dataPoints[index] = point;
                        misGraficos[index].data.datasets[0].borderColor = 'red';
                        misGraficos[index].data.datasets[0].pointBackgroundColor = 'red';

                        // Si es alcanzable, añadir la clase 
                        document.querySelectorAll('.grafico-container')[index].classList.add('order-1');
                    } else {
                        var point = dataPoints[index];
                        point.push(0);
                        dataPoints[index] = point;
                        misGraficos[index].data.datasets[0].borderColor = 'rgb(97, 130, 100)';
                        misGraficos[index].data.datasets[0].pointBackgroundColor = 'rgb(97, 130, 100)';

                        // Si no es alcanzable, quitar la clase 
                        document.querySelectorAll('.grafico-container')[index].classList.remove('order-1');
                    }

                    // Limitar el número de puntos mostrados en el gráfico (máximo 10 puntos)
                    if (datalabels[index].length > 20) {
                        datalabels[index].shift();
                        dataPoints[index].shift();
                    }

                    // Actualizar el gráfico
                    misGraficos[index].update();
                })
                .catch(function (error) {
                    console.error('Error:', error);
                });
    });
}

// Realizar la solicitud de ping cada 10 segundos
setInterval(realizarPing, 10000);
