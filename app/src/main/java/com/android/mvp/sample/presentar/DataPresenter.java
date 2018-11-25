package news.agoda.com.sample.presentar;

import news.agoda.com.sample.interceptor.NewsListInterceptor;
import news.agoda.com.sample.model.NewsResponseEntity;
import news.agoda.com.sample.views.BaseView;


public class DataPresenter implements NewsListInterceptor.OnNewsFetchDataFinishedListener {

    private BaseView view;
    private NewsListInterceptor newsListInterceptor;

    public DataPresenter(BaseView view, NewsListInterceptor newsListInterceptor) {
        this.view = view;
        this.newsListInterceptor = newsListInterceptor;
    }

    public void fetchNewsData() {
        view.showProgress();
        newsListInterceptor.getNewsData(this);
    }


    @Override
    public void onError(int reason) {
        view.hideProgress();
        view.onError(reason);
    }

    @Override
    public void onSuccess(NewsResponseEntity data) {
        view.hideProgress();
        view.onSuccess(data);
    }
}
