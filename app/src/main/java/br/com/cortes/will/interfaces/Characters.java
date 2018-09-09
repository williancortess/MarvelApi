package br.com.cortes.will.interfaces;

import br.com.cortes.will.models.Character;
import br.com.cortes.will.models.DataWrapper;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Characters {
    @GET("/v1/public/characters")
    Call<DataWrapper<Character>> listCharacters(@Query("limit") int limit
            , @Query("offset") int offset
            , @Query("ts") String timestamp
            , @Query("apikey") String apikey
            , @Query("hash") String hashSignature
            , @Query("name") String name);
}
