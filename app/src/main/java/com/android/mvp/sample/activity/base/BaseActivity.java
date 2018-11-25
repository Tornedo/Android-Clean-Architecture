package news.agoda.com.sample.activity.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import news.agoda.com.sample.interfaces.MyCallback;
import news.agoda.com.sample.views.Processing;


public abstract class BaseActivity extends Activity implements Processing {

    protected String TAG = this.getClass().getName();

    private Context context;
    private ProgressDialog progressDialog;
    private boolean isProgress = false;

    public BaseActivity() {

    }


    /**
     * Set context and other pre-init task we will add here
     *
     * @param activity
     */
    protected void setContext(Activity activity) {
        context = activity;

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(true);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    // ToDo: cancel is not working
                    dialog.cancel();
                }
            });
        }
    }

    @Override
    public void showProcessing(String message, String title) {
        if (!isProgress) {
            isProgress = true;
            progressDialog = ProgressDialog.show(context, title, message);
        }
    }

    @Override
    public void hideProcessing() {
        try {
            if (isProgress && progressDialog != null) {
                progressDialog.dismiss();
                isProgress = false;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }


    public void showAlert(String title, String message, final MyCallback callback) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton(getString(android.R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (callback != null) callback.run();
                        dialog.dismiss();
                    }
                });
        alert.show();
    }


    protected void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    protected void keypadOffonStart() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
