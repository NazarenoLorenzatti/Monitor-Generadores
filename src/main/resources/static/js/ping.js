// Configura el gráfico usando Chart.js
var ctx = document.getElementById('pingChart').getContext('2d');
var pingChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: [], // Etiquetas de tiempo
        datasets: [{
            label: 'Respuesta de Ping',
            data: [], // Datos de respuesta de ping
            borderColor: 'blue',
            fill: false
        }]
    },
    options: {
        // Configura las opciones del gráfico según tus necesidades
    }
});

// Configura la conexión WebSocket
var socket = new WebSocket('ws://localhost:8080/ping'); // Asegúrate de ajustar la URL del WebSocket según tu configuración

// Escucha mensajes WebSocket y actualiza el gráfico
socket.addEventListener('message', function(event) {
    var pingData = JSON.parse(event.data);

    // Agrega el tiempo actual como etiqueta de tiempo (puede ser una cadena de hora)
    pingChart.data.labels.push(new Date().toLocaleTimeString());
    // Agrega el valor de respuesta de ping
    pingChart.data.datasets[0].data.push(pingData.pingResponse);

    // Limita la cantidad de puntos en el gráfico (puedes ajustar este valor)
    var maxDataPoints = 10;
    if (pingChart.data.labels.length > maxDataPoints) {
        pingChart.data.labels.shift();
        pingChart.data.datasets[0].data.shift();
    }

    // Actualiza el gráfico
    pingChart.update();
});