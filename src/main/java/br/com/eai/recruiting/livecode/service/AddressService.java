package br.com.eai.recruiting.livecode.service;

import br.com.eai.recruiting.livecode.model.request.AddressRequest;
import br.com.eai.recruiting.livecode.model.request.AddressesRequest;
import br.com.eai.recruiting.livecode.model.response.AddressResponse;
import br.com.eai.recruiting.livecode.model.response.AddressesResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    AddressResponse create(AddressRequest addressRequest);

    AddressesResponse createBatch(AddressesRequest addressesRequest);

    Page<AddressResponse> getAllByZipCode(String zipCode);

}
