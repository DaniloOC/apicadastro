package br.com.danilo.teste.apicadastro.web.rest;

import br.com.danilo.teste.apicadastro.models.Pessoa;
import br.com.danilo.teste.apicadastro.service.PessoaService;
import br.com.danilo.teste.apicadastro.web.dto.PessoaDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cadastro/pessoa")
@Api(value = "Pessoa")
public class PessoaController {

    private final Logger logger = LoggerFactory.getLogger(PessoaController.class);

    private final PessoaService service;

    @Autowired
    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @ApiOperation(value = "Cria uma nova pessoa")
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

    @ApiOperation(value = "Atualiza pessoa pelo cpf")
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

    @ApiOperation(value = "Lista todas pessoas paginando.")
    @GetMapping
    public Page<PessoaDTO> findAll(
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0") int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "10") int size) {
        return service.findAll(page, size).map(PessoaDTO::new);
    }

    @ApiOperation(value = "Retorna pessoa pelo cpf")
    @GetMapping("/{cpf}")
    public ResponseEntity<PessoaDTO> findByCpf(@PathVariable String cpf) {
        Optional<PessoaDTO> pessoaDTOOptional = service.findByCpf(cpf).map(PessoaDTO::new);
        return pessoaDTOOptional
                .map(pessoaDTO -> new ResponseEntity<>(pessoaDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @ApiOperation(value = "Remove uma pessoa pelo cpf")
    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> delete(@PathVariable String cpf) {
        return service.findByCpf(cpf)
                .map(pessoa -> {
                    service.delete(pessoa);
                    return new ResponseEntity<>(HttpStatus.OK);})
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
