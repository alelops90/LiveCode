package br.com.eai.recruiting.livecode.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private Long id;

    private String street;

    private String number;

    private String neighborhood;

    private String city;

    private String state;

    private String zipCode;
}
