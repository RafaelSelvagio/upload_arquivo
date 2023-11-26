package br.com.spring.pais.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.pais.entity.Pais;
import br.com.spring.pais.repository.PaisRepository;



@RestController
@RequestMapping("/pais")
public class PaisResource {

	@Autowired
	private PaisRepository paisRepository;
	
	@GetMapping
	public Iterable<Pais> findAll(){
		return paisRepository.findAll();
	}
	
	@GetMapping("/nome/{nome}")
	public Iterable<Pais> findByNome(@PathVariable String nome){
		return paisRepository.findByNome(nome);
	}
	
	@GetMapping("/{codigo}")
	public Pais findById(@PathVariable Long codigo) {
		return paisRepository.findById(codigo).orElse(null);
	}
	
	@PostMapping
	public Pais createPais(@RequestBody Pais pais) {
		return paisRepository.save(pais);
	}
	
	@PutMapping("/{codigo}")
	public Pais updatePais(@PathVariable Long codigo, @RequestBody Pais pais) {
		pais.setCodigo(codigo);
		return paisRepository.save(pais);
	}
	
	@DeleteMapping("/{id}")
	public void deletePais(@PathVariable Long codigo) {
		paisRepository.deleteById(codigo);
	}
}
