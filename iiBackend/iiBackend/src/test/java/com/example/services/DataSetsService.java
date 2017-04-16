package com.example.services;

import com.example.models.Data;
import com.example.models.Dataset;
import com.example.models.OurResponse;
import com.example.models.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface DataSetsService {

    @GET("datasets/{id}/rows?$orderby=global_id")
    Call<List<OurResponse>> getDataSets(@Path("id") long id, @Query("$top") int top);

    @GET("datasets?$inlinecount=allpages")
    Call<Response<Data>> getDataSetByFilter(@Query("$filter") String filter);

}
