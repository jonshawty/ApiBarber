function cadastrarBarbearia() {
    const nome = document.getElementById("nome").value;
    const telefone = document.getElementById("telefone").value;

    const data = {
        nome: nome,
        telefone: telefone
    };

    fetch('http://localhost:8080/api/barbearia', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(() => {
        alert("Barbearia cadastrada com sucesso!");
        atualizarLista();
    })
    .catch((error) => {
        console.error('Erro:', error);
        alert("Erro ao cadastrar a barbearia.");
    });
}

function atualizarBarbearia() {
    const id = document.getElementById("updateId").value;
    const nome = document.getElementById("updateNome").value;
    const telefone = document.getElementById("updateTelefone").value;

    const data = {
        nome: nome,
        telefone: telefone
    };

    fetch(`http://localhost:8080/api/barbearia/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(() => {
        alert("Barbearia atualizada com sucesso!");
        atualizarLista();
    })
    .catch((error) => {
        console.error('Erro:', error);
        alert("Erro ao atualizar a barbearia.");
    });
}

function excluirBarbearia() {
    const id = document.getElementById("deleteId").value;

    fetch(`http://localhost:8080/api/barbearia/${id}`, {
        method: 'DELETE',
    })
    .then(() => {
        alert("Barbearia excluída com sucesso!");
        atualizarLista();
    })
    .catch((error) => {
        console.error('Erro:', error);
        alert("Erro ao excluir a barbearia.");
    });
}

function atualizarLista() {
    fetch('http://localhost:8080/api/barbearia', {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        const tableBody = document.getElementById("barbeariasTable").querySelector("tbody");
        tableBody.innerHTML = ""; // Limpa o conteúdo anterior
        
        data.forEach(barbearia => {
            const row = tableBody.insertRow();
            row.insertCell(0).textContent = barbearia.id;
            row.insertCell(1).textContent = barbearia.nome;
            row.insertCell(2).textContent = barbearia.telefone;
        });
    })
    .catch(error => console.error('Erro ao buscar barbearias:', error));
}


window.onload = atualizarLista;
