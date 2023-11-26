function uploadFile() {
	var fileInput = document.getElementById('fileInput');
	var file = fileInput.files[0];


	if (file) {
		var formData = new FormData();
		formData.append('file', file);

		var xhr = new XMLHttpRequest();
		xhr.open('POST', 'http://localhost:8080/upload', true);

		console.log(xhr);

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					Swal.fire({
						icon: 'success',
						title: 'Arquivo importado com sucesso.',
						text: '',
					});
				} else {
					Swal.fire({
						icon: 'error',
						title: 'Falha ao importar o arquivo.',
						text: '',
					});
				}
			}
		};

		xhr.send(formData);
	} else {
		alert('Selecione um arquivo antes de enviar.');
	}
}