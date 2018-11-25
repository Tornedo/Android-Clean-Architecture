package news.agoda.com.sample.views;

public interface Processing {



    /**
     * Show processing dialog
     *
     * @param message
     * @param title
     */
    void showProcessing(String message, String title);

    /**
     * Hide processing dialog
     */
    void hideProcessing();

}
