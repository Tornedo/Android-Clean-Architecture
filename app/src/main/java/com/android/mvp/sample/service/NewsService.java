package news.agoda.com.sample.service;


import news.agoda.com.sample.model.NewsResponseEntity;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {


    @GET("bins/nl6jh")
    Call<NewsResponseEntity> getNewsData();


}