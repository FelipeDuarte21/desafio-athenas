const PORT = 4200;

const express = require('express');
const app = new express();

app.use(express.static(`${__dirname}/dist/client`));

app.get('/**',(req,resp) => {
    resp.sendFile(`${__dirname}/dist/client/index.html`);
});

app.listen(PORT, () => {
    console.log(`Servidor iniciado na porta ${PORT} - Sistema de Calculo de Peso Ideal`);
});