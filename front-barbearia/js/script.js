
function cadastrarBarbearia() {
    const nome = document.getElementById("nome").value;
    const telefone = document.getElementById("telefone").value;

    const data = {
        nome: nome,
        telefone: telefone,
        servicos: []
    };

    fetch('http://localhost:8080/api/barbearia', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(response => {
        // Verifique se a resposta não é bem-sucedida
        if (!response.ok) {
            // Converta a resposta em JSON para obter detalhes do erro
            return response.json().then(errorData => {
                throw new Error(errorData.error);  // Lança um erro com a mensagem do servidor
            });
        }
        return response;
    })
    .then(() => {
        alert("Barbearia cadastrada com sucesso!");
        atualizarLista();
    })
    .catch((error) => {
        console.error('Erro:', error);
        alert(error.message);  // Exibe a mensagem de erro do servidor no alerta
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
            row.dataset.id = barbearia.id; // Adiciona o ID da barbearia como atributo 'data-id'
            row.insertCell(0).textContent = barbearia.id;
            row.insertCell(1).textContent = barbearia.nome;
            row.insertCell(2).textContent = barbearia.telefone;
            
            // Adiciona o evento de clique a cada linha
            row.addEventListener('click', function() {
                window.location.href = `servicos.html?id=${this.dataset.id}`;
            });
        });
    })
    .catch(error => console.error('Erro ao buscar barbearias:', error));
}




window.onload = atualizarLista;
