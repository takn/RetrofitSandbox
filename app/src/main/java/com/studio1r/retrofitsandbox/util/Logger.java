package com.studio1r.retrofitsandbox.util;

import android.util.Log;

/**
 * Custom logger for NFL Now.
 */
public final class Logger {
    public static final String BATTERY_MONITOR_TAG = "Battery";

    /**
     * Constructor for logger.
     */
    private Logger() {
        //Do nothing.
    }

    /**
     * Writes to verbose log.
     *
     * @param tag Tag to use
     * @param t   Throwable to write to log.
     * @return Success code.
     */
    public static int v(final String tag, final Throwable t) {
        return println(Log.VERBOSE, tag, Log.getStackTraceString(t));
    }

    /**
     * Writes to verbose log and also prints the throwable message.
     *
     * @param tag       Tag to use
     * @param throwable Throwable to write to log.
     * @param format    Format to use.
     * @param args      Arguments to write to log.
     * @return Success code.
     */
    public static int v(final String tag, final Throwable throwable, final String format,
            final Object... args) {
        final String message = (args.length > 0 ? String.format(format, args) : format)
                + '\n' + Log.getStackTraceString(throwable);
        return println(Log.VERBOSE, tag, message);
    }

    /**
     * Writes to verbose log using printf.
     *
     * @param tag    Tag to use
     * @param format Format to use.
     * @param args   Arguments to write to log.
     * @return Success code.
     */
    public static int v(final String tag, final String format, final Object... args) {
        final String message = args.length > 0 ? String.format(format, args) : format;
        return println(Log.VERBOSE, tag, message);
    }

    /**
     * Writes to debug log.
     *
     * @param tag Tag to use
     * @param t   Throwable to write to log.
     * @return Success code.
     */
    public static int d(final String tag, final Throwable t) {
        return println(Log.DEBUG, tag, Log.getStackTraceString(t));
    }

    /**
     * Writes to debug log and also prints the throwable message.
     *
     * @param tag       Tag to use
     * @param throwable Throwable to write to log.
     * @param format    Format to use.
     * @param args      Arguments to write to log.
     * @return Success code.
     */
    public static int d(final String tag, final Throwable throwable, final String format,
            final Object... args) {
        final String message = (args.length > 0 ? String.format(format, args) : format)
                + '\n' + Log.getStackTraceString(throwable);
        return println(Log.DEBUG, tag, message);
    }

    /**
     * Writes to debug log using printf.
     *
     * @param tag    Tag to use
     * @param format Format to use.
     * @param args   Arguments to write to log.
     * @return Success code.
     */
    public static int d(final String tag, final String format, final Object... args) {
        final String message = args.length > 0 ? String.format(format, args) : format;
        return println(Log.DEBUG, tag, message);
    }

    /**
     * Writes to info log.
     *
     * @param tag Tag to use
     * @param t   Throwable to write to log.
     * @return Success code.
     */
    public static int i(final String tag, final Throwable t) {
        return println(Log.INFO, tag, Log.getStackTraceString(t));
    }

    /**
     * Writes to info log and also prints the throwable message.
     *
     * @param tag       Tag to use
     * @param throwable Throwable to write to log.
     * @param format    Format to use.
     * @param args      Arguments to write to log.
     * @return Success code.
     */
    public static int i(final String tag, final Throwable throwable, final String format,
            final Object... args) {
        final String message = (args.length > 0 ? String.format(format, args) : format)
                + '\n' + Log.getStackTraceString(throwable);
        return println(Log.INFO, tag, message);
    }

    /**
     * Writes to info log using printf.
     *
     * @param tag    Tag to use
     * @param format Format to use.
     * @param args   Arguments to write to log.
     * @return Success code.
     */
    public static int i(final String tag, final String format, final Object... args) {
        final String message = args.length > 0 ? String.format(format, args) : format;
        return println(Log.INFO, tag, message);
    }

    /**
     * Writes to warning log.
     *
     * @param tag Tag to use
     * @param t   Throwable to write to log.
     * @return Success code.
     */
    public static int w(final String tag, final Throwable t) {
        return println(Log.WARN, tag, Log.getStackTraceString(t));
    }

    /**
     * Writes to warning log and also prints the throwable message.
     *
     * @param tag       Tag to use
     * @param throwable Throwable to write to log.
     * @param format    Format to use.
     * @param args      Arguments to write to log.
     * @return Success code.
     */
    public static int w(final String tag, final Throwable throwable, final String format,
            final Object... args) {
        final String message = (args.length > 0 ? String.format(format, args) : format)
                + '\n' + Log.getStackTraceString(throwable);
        return println(Log.WARN, tag, message);
    }

    /**
     * Writes to warning log using printf.
     *
     * @param tag    Tag to use
     * @param format Format to use.
     * @param args   Arguments to write to log.
     * @return Success code.
     */
    public static int w(final String tag, final String format, final Object... args) {
        final String message = args.length > 0 ? String.format(format, args) : format;
        return println(Log.WARN, tag, message);
    }

    /**
     * Writes to error log.
     *
     * @param tag Tag to use
     * @param t   Throwable to write to log.
     * @return Success code.
     */
    public static int e(final String tag, final Throwable t) {
        return println(Log.ERROR, tag, Log.getStackTraceString(t));
    }

    /**
     * Writes to error log and also prints the throwable message.
     *
     * @param tag       Tag to use
     * @param throwable Throwable to write to log.
     * @param format    Format to use.
     * @param args      Arguments to write to log.
     * @return Success code.
     */
    public static int e(final String tag, final Throwable throwable, final String format,
                        final Object... args) {
        final String message = (args.length > 0 ? String.format(format, args) : format)
                + '\n' + Log.getStackTraceString(throwable);
        return println(Log.ERROR, tag, message);
    }

    /**
     * Writes to error log using printf.
     *
     * @param tag    Tag to use
     * @param format Format to use.
     * @param args   Arguments to write to log.
     * @return Success code.
     */
    public static int e(final String tag, final String format, final Object... args) {
        final String message = args.length > 0 ? String.format(format, args) : format;
        return println(Log.ERROR, tag, message);
    }

    /**
     * Prints directly to log.
     *
     * @param priority Logging priority
     * @param tag      Tag to use
     * @param msg      Message to print.
     * @return Success code.
     */
    private static int println(final int priority, final String tag, final String msg) {
        return Log.println(priority, tag, String.format("[%s] %s",
                Thread.currentThread().getName(), msg));
    }
}
