package news.agoda.com.sample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;

import news.agoda.com.sample.R;
import news.agoda.com.sample.adaptar.NewsListAdapter;
import news.agoda.com.sample.activity.base.BaseListActivity;
import news.agoda.com.sample.interceptor.NewsListInterceptor;
import news.agoda.com.sample.model.NewsEntity;
import news.agoda.com.sample.model.NewsResponseEntity;
import news.agoda.com.sample.presentar.DataPresenter;
import news.agoda.com.sample.views.BaseView;

public class MainActivity
        extends BaseListActivity
        implements BaseView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DataPresenter dataPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        setContext(this);
        dataPresenter = new DataPresenter(this, new NewsListInterceptor());
        getNewsData();

    }

    private void getNewsData() {
        dataPresenter.fetchNewsData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_refresh) {
            getNewsData();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showProgress() {
        showProcessing(getString(R.string.please_wait_text), getString(R.string.loading_text));
    }

    @Override
    public void hideProgress() {
        hideProcessing();
    }

    @Override
    public void onSuccess(NewsResponseEntity data) {
        loadData(data);
    }

    private void loadData(final NewsResponseEntity data) {

        NewsListAdapter adapter = new NewsListAdapter
                (MainActivity.this, R.layout.list_item_news, data.getResults());
        setListAdapter(adapter);
        ListView listView = getListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsEntity newsEntity = data.getResults().get(position);

                Bundle bundle = new Bundle();
                bundle.putString("storyURL", newsEntity.getArticleUrl());
                bundle.putString("title", newsEntity.getTitle());
                bundle.putString("summary", newsEntity.getSummary());

                if (newsEntity.getMediaEntity() != null)
                    if (newsEntity.getMediaEntity().get(0) != null)
                        bundle.putString("imageURL", newsEntity.getMediaEntity().get(0).getUrl());
                    else
                        bundle.putString("imageURL", "");
                else
                    bundle.putString("imageURL", "");


                Intent intent = new Intent(MainActivity.this, DetailViewActivity.class);
                intent.putExtra("parcel", bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onError(int message) {
        hideProcessing();
        showToast(getString(message));
    }

}
