package br.com.danilo.teste.apicadastro.web.api.it;

import br.com.danilo.teste.apicadastro.models.Pessoa;
import br.com.danilo.teste.apicadastro.service.PessoaService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PessoaApiRestIT {

    private static final String API_V1_CADASTRO = "/api/v1/cadastro";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService service;

    @Mock
    private Pessoa pessoa;

    private String pessoaFisicaJson;

    private ResultActions result;

    @Test
    void criaPessoaFisicaComSucesso() throws Exception {
        dadoUmJsonValidoDePessoaFisica();
        savarPessoa();
        aoExcutarPostParaCriaPessoa();
        deveCadastrarUmaNovaPessoa();
        deveRetornarCreated();
    }

    @Test
    void retornarCode409ConflictAoCadastrarPessoaExistente() throws Exception {
        dadoUmJsonValidoDePessoaFisica();
        existeItemCadastrado();
        aoExcutarPostParaCriaPessoa();
        naoDeveCadastrarUmaNovaPessoa();
        deveRetornarConflict();
    }

    private void naoDeveCadastrarUmaNovaPessoa() {
        verify(service, times(0)).salvar(any(Pessoa.class));
    }

    private void existeItemCadastrado() {
        when(service.existe(any(Pessoa.class))).thenReturn(true);
    }

    private void savarPessoa() {
        when(service.existe(any(Pessoa.class))).thenReturn(false);
        when(service.salvar(any(Pessoa.class))).thenReturn(pessoa);
    }

    private void deveRetornarConflict() throws Exception {
        result.andExpect(status().isConflict());
    }

    private void deveCadastrarUmaNovaPessoa() {
        verify(service, times(1)).salvar(any(Pessoa.class));
    }

    private void deveRetornarCreated() throws Exception {
        result.andExpect(status().isCreated());
    }

    private void aoExcutarPostParaCriaPessoa() throws Exception {
        result = mockMvc.perform(post(API_V1_CADASTRO + "/pessoa")
                .contentType("application/json")
                .content(pessoaFisicaJson));
    }

    private void dadoUmJsonValidoDePessoaFisica() throws JSONException {
        JSONArray enderecosJsonObject = obterEnderecosJsonObject();
        JSONArray dependentesJsonObject = obterDependentesJsonObject();
        JSONArray telefonesJsonObject = obterTelefonesJsonObject();

        JSONObject pessoaFisicaJsonObject = new JSONObject();
        pessoaFisicaJsonObject.put("nome", "João José");
        pessoaFisicaJsonObject.put("apelido", "Jão");
        pessoaFisicaJsonObject.put("cpf", "34037000032");
        pessoaFisicaJsonObject.put("enderecos", enderecosJsonObject);
        pessoaFisicaJsonObject.put("profissao", "Desenvolvedor");
        pessoaFisicaJsonObject.put("salario", "1001.36");
        pessoaFisicaJsonObject.put("dependentes", dependentesJsonObject);
        pessoaFisicaJsonObject.put("dataNascimento", "1987-12-11");
        pessoaFisicaJsonObject.put("telefones", telefonesJsonObject);

        pessoaFisicaJson = pessoaFisicaJsonObject.toString();
    }

    private JSONArray obterTelefonesJsonObject() throws JSONException {
        JSONArray telefonesJson = new JSONArray();

        JSONObject telefoneJson = new JSONObject();
        telefoneJson.put("codigoPais", 55);
        telefoneJson.put("ddd", 11);
        telefoneJson.put("numero", "97000-0000");
        telefoneJson.put("tipo", "Residencial");
        telefonesJson.put(telefoneJson);

        return telefonesJson;
    }

    private JSONArray obterDependentesJsonObject() throws JSONException {
        JSONArray dependentesJson = new JSONArray();

        JSONObject dependenteJson = new JSONObject();
        dependenteJson.put("cpf", "86044484033");
        dependenteJson.put("nome", "João José Jr.");
        dependenteJson.put("tipo", "Filho");
        dependentesJson.put(dependenteJson);

        return dependentesJson;
    }

    private JSONArray obterEnderecosJsonObject() throws JSONException {
        JSONArray enderecosJson = new JSONArray();

        JSONObject enderecoJson = new JSONObject();
        enderecoJson.put("tipoEndereco", "Comercial");
        enderecoJson.put("tipo", "Rua");
        enderecoJson.put("nome", "Castelo Branco");
        enderecoJson.put("numero", 312);
        enderecoJson.put("complemento", "1º andar");
        enderecoJson.put("cep", "09321000");
        enderecoJson.put("bairro", "Matriz");
        enderecoJson.put("cidade", "Mauá");
        enderecoJson.put("estado", "São Paulo");
        enderecoJson.put("pais", "Brasil");
        enderecosJson.put(enderecoJson);

        JSONObject enderecoJson2 = new JSONObject();
        enderecoJson2.put("tipoEndereco", "Residencial");
        enderecoJson2.put("tipo", "Rua");
        enderecoJson2.put("nome", "Castelo Branco");
        enderecoJson2.put("numero", 312);
        enderecoJson2.put("complemento", "2º andar");
        enderecoJson2.put("cep", "09321000");
        enderecoJson2.put("bairro", "Matriz");
        enderecoJson2.put("cidade", "Mauá");
        enderecoJson2.put("estado", "São Paulo");
        enderecoJson2.put("pais", "Brasil");
        enderecosJson.put(enderecoJson2);

        return enderecosJson;
    }
}
