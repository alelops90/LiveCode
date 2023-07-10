package br.com.eai.recruiting.livecode.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressesRequest {

    private List<AddressRequest> addresses;
}
