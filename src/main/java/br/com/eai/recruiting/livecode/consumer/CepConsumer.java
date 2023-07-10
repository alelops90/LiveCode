package br.com.eai.recruiting.livecode.consumer;

import br.com.eai.recruiting.livecode.model.request.AddressRequest;
import br.com.eai.recruiting.livecode.model.response.CepResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static br.com.eai.recruiting.livecode.utils.ViaCepConstants.CORREIOS_URL;
import static br.com.eai.recruiting.livecode.utils.ViaCepConstants.VIA_CEP_URL;

@Service
@Slf4j
public class CepConsumer {

    public CepResponse getViaCep(AddressRequest addressRequest) {
        log.info("[CepConsumer] {getViaCep} " + addressRequest.getZipCode() + " version: " + addressRequest.getVersion());
        RestTemplate viaCep = new RestTemplate();
        if (addressRequest.getVersion() == 1) {
            ResponseEntity<CepResponse> responseCep = viaCep.getForEntity(VIA_CEP_URL + addressRequest.getZipCode() + "/json/", CepResponse.class);
            return responseCep.getBody();
        }
        ResponseEntity<CepResponse> responseCep = viaCep.getForEntity(CORREIOS_URL + addressRequest.getZipCode() + "/json/", CepResponse.class);
        return responseCep.getBody();
    }
}
