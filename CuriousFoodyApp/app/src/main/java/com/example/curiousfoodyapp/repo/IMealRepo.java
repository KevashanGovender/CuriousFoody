package com.example.curiousfoodyapp.repo;

public interface IMealRepo {

    void searchByRecipe(ICallbackListener<Object> listener, String recipe);
    void searchById(ICallbackListener<Object> listener, long id);

}
