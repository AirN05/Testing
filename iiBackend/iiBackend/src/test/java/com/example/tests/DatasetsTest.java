package com.example.tests;

import com.example.models.Data;
import com.example.models.OurResponse;
import com.example.models.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.Test;
import retrofit2.Call;
import com.example.services.DataSetsService;
import com.example.services.BaseService;
import java.io.IOException;
import java.util.List;

public class DatasetsTest {

    private final static Logger logger = Logger.getLogger(DatasetsTest.class);
    private DataSetsService service;
    private final String CATEGORY = "'Площадки для выгула (дрессировки) собак'";
    private long idCategory;

    public DatasetsTest(){
        service = BaseService.getRetrofit().create(DataSetsService.class);
    }



    @Test
    public void getGlobalIdDataSet(){

        try {
            findIdCategory();
            Call<List<OurResponse>> responseCall = service.getDataSets(idCategory,1);
            List<OurResponse> response = responseCall.execute().body();
            logger.debug(response);

            Assert.assertEquals(response.get(0).getGlobal_id(),272394056);
        } catch (IOException e) {
            logger.error(e);
            throw new TestException(e);
        }

    }

    @Test
    public void getNumberDataSet(){

        try {
            findIdCategory();
            Call<List<OurResponse>> responseCall = service.getDataSets(idCategory,1);
            List<OurResponse> response = responseCall.execute().body();
            logger.debug(response);

            Assert.assertEquals(response.get(0).getNumber(),107);

        } catch (IOException e) {
            logger.error(e);
            throw new TestException(e);
        }

    }

    @Test
    public void getCellsDataSet(){

        try {
            findIdCategory();
            Call<List<OurResponse>> responseCall = service.getDataSets(idCategory,1);
            List<OurResponse> response = responseCall.execute().body();
            logger.debug(response);

            Assert.assertEquals(response.get(0).getCells().getAdmArea(),"Зеленоградский административный округ");
            Assert.assertEquals(response.get(0).getCells().getLocation(),"Панфиловский проспект, дом 3, строение 1");
            Assert.assertEquals(response.get(0).getCells().getFencing(),"да");
            Assert.assertEquals(response.get(0).getCells().getDogParkArea(),"250");
        } catch (IOException e) {
            logger.error(e);
            throw new TestException(e);
        }

    }

    private void findIdCategory(){

        try {
            Call<Response<Data>> responseCall = service.getDataSetByFilter("Caption eq 'Площадки для выгула (дрессировки) собак'");
            Response<Data> response = responseCall.execute().body();

            if (response.getItems().size() != 0) {
                idCategory = response.getItems().get(0).getId();
                logger.debug(String.format("category id - %s", idCategory));
            }else {
                logger.debug(String.format("Category does not exist %s", CATEGORY));
                throw new IllegalArgumentException("Category does not exist: "+ CATEGORY);
            }
        } catch (IllegalArgumentException | IOException e) {
            logger.debug(e.getMessage());
            throw new TestException(e);
        }
    }

}
