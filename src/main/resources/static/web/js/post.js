// JavaScript Document
function enviarForm() {
    'use strict';
    var url = 'http://localhost:1000/zoo/animal';
    var http = new XMLHttpRequest();
    http.open("POST", url, true);
    http.setRequestHeader('Content-Type', 'application/json');
    var zoo_id = document.getElementById('zoo_id');
    var especie = document.getElementById('especie');
    var cantidad = document.getElementById('cantidad');

    var json = JSON.stringify({especie: especie.value, cantidad: cantidad.value, zoo: {id: zoo_id.value}});

    http.onload = function () {
        if (http.readyState === 4 && http.status === 200) {
            window.location.replace('http://localhost:1000/web/index.html');
        }
    };

    http.send(json);
}