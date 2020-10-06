package br.com.danilo.teste.apicadastro.web.api;

import br.com.danilo.teste.apicadastro.models.Pessoa;
import br.com.danilo.teste.apicadastro.service.PessoaService;
import br.com.danilo.teste.apicadastro.web.dto.PessoaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cadastro/pessoa")
public class PessoaController {

    private final Logger logger = LoggerFactory.getLogger(PessoaController.class);

    private final PessoaService service;

    @Autowired
    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> criar(@RequestBody PessoaDTO pessoaDTO) {
        try {
            Pessoa newPessoa = pessoaDTO.toEntity();
            if(service.existe(newPessoa)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            Pessoa pessoa = service.salvar(newPessoa);
            return new ResponseEntity<>(new PessoaDTO(pessoa), HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<PessoaDTO> atualizar(@PathVariable String cpf, @RequestBody PessoaDTO pessoaDTO) {

        Pessoa pessoa = pessoaDTO.toEntity();
        pessoa.setCpf(cpf);
        if(!service.existe(pessoa)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Pessoa pessoaAtualizada = service.atualizar(cpf, pessoa);
        return new ResponseEntity<>(new PessoaDTO(pessoaAtualizada), HttpStatus.OK);
    }
}
