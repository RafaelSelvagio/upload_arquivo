function gerarRelatorio() {
	fetch('http://localhost:8080/pdf/gerar')
		.then(response => {
			if (!response.ok) {
				throw new Error('Erro ao gerar relatório');
			}
			// Se a resposta estiver ok, você pode redirecionar ou realizar outras ações necessárias
			// Por exemplo, você pode abrir o relatório em uma nova janela
			window.open('http://localhost:8080/pdf/gerar');
		})
		.catch(error => {
			console.error('Erro:', error);
			// Aqui você pode exibir uma mensagem de erro para o usuário, se desejar
			Swal.fire({
				icon: 'error',
				title: 'Erro',
				text: 'Erro ao gerar relatório',
			});
		});
}

function buscaTodos() {
	fetch('http://localhost:8080/pais')
		.then(response => {
			if (!response.ok) {
				throw new Error('Erro ao buscar países');
			}
			return response.json();
		})
		.then(data => {
			const tabelaElement = document.getElementById('tabela_lista');

			// Limpar todas as linhas da tabela
			while (tabelaElement.firstChild) {
				tabelaElement.removeChild(tabelaElement.firstChild);
			}

			// Criar uma nova linha na tabela
			const row = document.createElement('tr');

			// Criar células na linha para cada propriedade do país
			const cellNome = document.createElement('th');
			cellNome.textContent = "Código";
			row.appendChild(cellNome);

			const cellCapital = document.createElement('th');
			cellCapital.textContent = "Nome do país";
			row.appendChild(cellCapital);

			const cellSigla2 = document.createElement('th');
			cellSigla2.textContent = "Sigla (2)";
			row.appendChild(cellSigla2);

			const cellSigla3 = document.createElement('th');
			cellSigla3.textContent = "Sigla (3)";
			row.appendChild(cellSigla3);


			tabelaElement.appendChild(row);

			// Criar elementos de lista (li) para cada país
			data.forEach(pais => {
				// Criar uma nova linha na tabela
				const row = document.createElement('tr');

				// Criar células na linha para cada propriedade do país
				const cellCodigo = document.createElement('td');
				cellCodigo.textContent = pais.codigo;
				row.appendChild(cellCodigo);

				const cellNome = document.createElement('td');
				cellNome.textContent = pais.nome;
				row.appendChild(cellNome);

				const cellSigla2 = document.createElement('td');
				cellSigla2.textContent = pais.sigla2;
				row.appendChild(cellSigla2);

				const cellSigla3 = document.createElement('td');
				cellSigla3.textContent = pais.sigla3;
				row.appendChild(cellSigla3);

				// Adicionar a linha à tabela
				tabelaElement.appendChild(row);
			});
		})
		.catch(error => {
			console.error('Erro:', error);
		});
}

// Função executada ao terminar o load da página
window.onload = buscaTodos;