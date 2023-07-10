package br.com.eai.recruiting.livecode.mapper;

import br.com.eai.recruiting.livecode.domain.Address;
import br.com.eai.recruiting.livecode.model.request.AddressRequest;
import br.com.eai.recruiting.livecode.model.response.AddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mappings({
            @Mapping(source = "cepResponse.cep", target = "zipCode"),
            @Mapping(source = "cepResponse.logradouro", target = "street"),
            @Mapping(source = "cepResponse.bairro", target = "neighborhood"),
            @Mapping(source = "cepResponse.uf", target = "state"),
            @Mapping(source = "cepResponse.localidade", target = "city"),
    })
    Address toEntity(AddressRequest addressRequest);

    AddressResponse toResponse(Address address);
}
