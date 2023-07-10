package br.com.eai.recruiting.livecode.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CepResponse {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
