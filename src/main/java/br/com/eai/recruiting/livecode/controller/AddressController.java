package br.com.eai.recruiting.livecode.controller;

import br.com.eai.recruiting.livecode.model.request.AddressRequest;
import br.com.eai.recruiting.livecode.model.request.AddressesRequest;
import br.com.eai.recruiting.livecode.model.response.AddressResponse;
import br.com.eai.recruiting.livecode.model.response.AddressesResponse;
import br.com.eai.recruiting.livecode.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponse> create(@RequestBody AddressRequest addressRequest) {
        AddressResponse addressResponse = addressService.create(addressRequest);
        return ResponseEntity.ok(addressResponse);
    }

    @PostMapping("/create_batch")
    public ResponseEntity<AddressesResponse> creationBatch(@RequestBody AddressesRequest addressesRequest) {
        AddressesResponse addressesResponse = addressService.createBatch(addressesRequest);
        return ResponseEntity.ok(addressesResponse);
    }

    @GetMapping("/{zipCode}")
    public ResponseEntity<Page<AddressResponse>> getAllByZipCode(@PathVariable String zipCode) {
        Page<AddressResponse> addressesPage = addressService.getAllByZipCode(zipCode);
        return ResponseEntity.ok(addressesPage);
    }
}
