package br.com.eai.recruiting.livecode.service.impl;

import br.com.eai.recruiting.livecode.consumer.CepConsumer;
import br.com.eai.recruiting.livecode.domain.Address;
import br.com.eai.recruiting.livecode.exception.BusinessException;
import br.com.eai.recruiting.livecode.mapper.AddressMapper;
import br.com.eai.recruiting.livecode.repository.AddressRepository;
import br.com.eai.recruiting.livecode.model.request.AddressRequest;
import br.com.eai.recruiting.livecode.model.request.AddressesRequest;
import br.com.eai.recruiting.livecode.model.response.AddressResponse;
import br.com.eai.recruiting.livecode.model.response.AddressesResponse;
import br.com.eai.recruiting.livecode.model.response.CepResponse;
import br.com.eai.recruiting.livecode.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    private CepConsumer cepConsumer;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public AddressResponse create(AddressRequest addressRequest) {
        log.info("[AddressServiceImpl] {create} " + addressRequest.getZipCode());
        CepResponse cepRetorno = cepConsumer.getViaCep(addressRequest);
        addressRequest.setCepResponse(cepRetorno);
        Address address = addressMapper.toEntity(addressRequest);
        addressRepository.save(address);
        return addressMapper.toResponse(address);
    }

    @Override
    public AddressesResponse createBatch(AddressesRequest addressesRequest) {
        AddressesResponse addressesResponse = new AddressesResponse();
        List<AddressResponse> addressesList = new ArrayList<>();
        if (hasDuplicateAddress(addressesRequest.getAddresses())) {
            throw new BusinessException("There are duplicate addresses");
        }
        addressesRequest.getAddresses().forEach(addressRequest -> {
            log.info("[AddressServiceImpl] {createBatch} " + addressRequest.getZipCode());
            CepResponse cepRetorno = cepConsumer.getViaCep(addressRequest);
            addressRequest.setCepResponse(cepRetorno);
            Address address = addressMapper.toEntity(addressRequest);
            addressRepository.save(address);
            addressesList.add(addressMapper.toResponse(address));
        });
        addressesResponse.setAddresses(addressesList);
        return addressesResponse;
    }

    @Override
    public Page<AddressResponse> getAllByZipCode(String zipCode) {
        log.info("[AddressServiceImpl] {getAllByZipCode} " + zipCode);
        String zipCodeFormated = formatZipCode(zipCode);
        List<Address> addresses = addressRepository.findByZipCode(zipCodeFormated);
        List<AddressResponse> addressResponses = addresses.stream().map(address -> addressMapper.toResponse(address)).collect(Collectors.toList());
        return new PageImpl<>(addressResponses);
    }

    private Boolean hasDuplicateAddress(List<AddressRequest> addressesRequest) {
        log.info("[AddressServiceImpl] {hasDuplicateAddress} validation of batch");
        List<String> zipCodes = addressesRequest.stream()
                .map(AddressRequest::getZipCode)
                .collect(Collectors.toList());
        return zipCodes.size() != zipCodes.stream().distinct().count();
    }

    public String formatZipCode(String zipCode) {
        log.info("[AddressServiceImpl] {formatZipCode} " + zipCode);
        if (zipCode == null || zipCode.length() != 8) {
            throw new BusinessException("The Zip Code is Invalid");
        }
        String firstPart = zipCode.substring(0, 5);
        String secondPart = zipCode.substring(5);
        return firstPart + "-" + secondPart;
    }
}
