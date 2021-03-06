package com.example.curiousfoodyapp.repo;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface ICallbackListener<T> extends Callback<T> {

    @Override
    void onResponse(@NonNull Call<T> call, @NonNull Response<T> response);

    @Override
    void onFailure(@NonNull Call<T> call, @NonNull Throwable t);

    void onStart();
}
