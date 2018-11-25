package news.agoda.com.sample.utils;

import android.util.Log;

/**
 * Ths Class is a Logger Wrapper that should be used to log all the debug inside the app,
 * so it is easy to disable and enable before a release.
 */
public class Logger {
    public static final boolean DEBUG = true;

    /**
     * Send a Debug log message, this message will not show on the release version
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void d(final String tag, final String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    /**
     * Send a Verbose log message, this message will not show on the release version
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void v(final String tag, final String msg) {
        if (DEBUG) {
            Log.v(tag, msg);
        }
    }

    /**
     * Send a info log message, this message will not show on the release version
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void i(final String tag, final String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    /**
     * Send a error log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void e(final String tag, final String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }
}