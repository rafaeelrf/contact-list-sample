package com.contact_list.interactors;

import com.contact_list.api.APIInterface;
import com.contact_list.model.AddressResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GetAddressInteractor {

    private APIInterface api;

    public GetAddressInteractor(APIInterface apiInterface) {
        this.api = apiInterface;
    }

    public Observable<AddressResponse> invoke(String cep) {
        return api.getAddressData(cep)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
