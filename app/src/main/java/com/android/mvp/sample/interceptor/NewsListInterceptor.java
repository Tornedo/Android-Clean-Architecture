package news.agoda.com.sample.interceptor;

import java.io.IOException;

import news.agoda.com.sample.service.NewsService;
import news.agoda.com.sample.R;
import news.agoda.com.sample.network.ServiceGenerator;
import news.agoda.com.sample.model.NewsResponseEntity;
import news.agoda.com.sample.utils.GsonUtil;
import news.agoda.com.sample.utils.Logger;
import retrofit2.Call;
import retrofit2.Response;

public class NewsListInterceptor {

    private NewsService service;
    private OnNewsFetchDataFinishedListener listener;

    public interface OnNewsFetchDataFinishedListener {
        void onError(int reason);

        void onSuccess(NewsResponseEntity data);
    }

    private retrofit2.Callback<NewsResponseEntity> newsCallBack
            = new retrofit2.Callback<NewsResponseEntity>() {
        @Override
        public void onResponse(Call<NewsResponseEntity> call, Response<NewsResponseEntity> response) {
            if (response.isSuccessful()) {
                NewsResponseEntity data = response.body();
                listener.onSuccess(data);
                Logger.e("news response ", GsonUtil.toJson(data));
            }
        }

        @Override
        public void onFailure(Call<NewsResponseEntity> call, Throwable t) {
            if (t instanceof IOException) {
                listener.onError(R.string.no_internet_connection);
            } else {
                listener.onError(R.string.something_went_wrong);
            }


        }
    };

    public void getNewsData(final OnNewsFetchDataFinishedListener listener) {
        this.listener = listener;
        this.service = ServiceGenerator.getProxyService(NewsService.class, true);
        service.getNewsData().enqueue(newsCallBack);
    }

}
