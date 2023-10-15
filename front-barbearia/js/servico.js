const urlParams = new URLSearchParams(window.location.search);
const barbeariaId = urlParams.get('id');  // Aqui, mude 'barbeariaId' para 'id'



window.onload = function() {
    const barbeariaIdInput = document.getElementById('barbeariaId');
    if (barbeariaIdInput) {
        barbeariaIdInput.value = barbeariaId;
    }
    listarServicos();
}




console.log(barbeariaId);

function cadastrarServico() {

    const nome = document.getElementById("servicoNome").value;
    const preco = document.getElementById("servicoPreco").value;

     const data = {
        nome: nome,
        preco: preco,
        barbearia: {
            id: barbeariaId
        }
    };

    console.log(data);
    

    fetch('http://localhost:8080/api/servicos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(() => {
        alert("Serviço cadastrado com sucesso!");
        listarServicos();
    })
    .catch((error) => {
        console.error('Erro:', error);
        alert("Erro ao cadastrar o serviço.");
    });
}

function listarServicos() {
    fetch(`http://localhost:8080/api/barbearia/${barbeariaId}`, {
        method: 'GET',
    })
    .then(response => response.json())
        .then(data => {

            document.getElementById("barbeariaTitle").textContent = `Cadastro de Serviços para Barbearia ${data.nome}`;
    
            const tableBody = document.getElementById("servicosTable").querySelector("tbody");
            tableBody.innerHTML = ""; 
        data.servicos.forEach(servico => {
            const row = tableBody.insertRow();
            row.insertCell(0).textContent = servico.nome;
            row.insertCell(1).textContent = `R$${servico.preco}`;
        });
    })
    .catch(error => console.error('Erro ao buscar serviços:', error));
}


window.onload = listarServicos;
