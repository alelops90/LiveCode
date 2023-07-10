package br.com.eai.recruiting.livecode.model.request;

import br.com.eai.recruiting.livecode.model.response.CepResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

    private String number;

    private String zipCode;

    private Integer version;

    private CepResponse cepResponse;

}
