package com.contact_list.api;

import com.contact_list.model.AddressResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("{cep}/json")
    Observable<AddressResponse> getAddressData(
            @Path("cep") String cep
    );
}
