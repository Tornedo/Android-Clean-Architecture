package news.agoda.com.sample.views;

import news.agoda.com.sample.model.NewsResponseEntity;

public interface BaseView {
    void showProgress();

    void hideProgress();

    void onSuccess(NewsResponseEntity data);

    void onError(int message);
}
