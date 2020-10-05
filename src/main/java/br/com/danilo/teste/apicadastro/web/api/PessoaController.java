package br.com.danilo.teste.apicadastro.web.api;

import br.com.danilo.teste.apicadastro.models.Pessoa;
import br.com.danilo.teste.apicadastro.service.PessoaService;
import br.com.danilo.teste.apicadastro.web.dto.PessoaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            Pessoa pessoa = service.salvar(pessoaDTO.toEntity());
            return new ResponseEntity<>(new PessoaDTO(pessoa), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
