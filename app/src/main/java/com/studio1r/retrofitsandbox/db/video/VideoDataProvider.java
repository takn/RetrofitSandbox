package com.studio1r.retrofitsandbox.db.video;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.studio1r.retrofitsandbox.db.video.contract.VideoDetailContract;
import com.tjeannin.provigen.ProviGenOpenHelper;
import com.tjeannin.provigen.ProviGenProvider;

/**
 * Created by nelsonramirez on 9/1/14.
 */
public class VideoDataProvider extends ProviGenProvider {
    @Override
    public SQLiteOpenHelper openHelper(Context context) {
        return null;
    }

    @Override
    public Class[] contractClasses() {
        return new Class[]{VideoDetailContract.class};
    }

    private static final class VideoDetailDbOpenHelper extends ProviGenOpenHelper {

        /**
         * Create a helper object to create, open, and/or manage a database.
         * This method always returns very quickly.  The database is not actually
         * created or opened until one of {@link #getWritableDatabase} or
         * {@link #getReadableDatabase} is called.
         *
         * @param context         the context to use to open or create the database.
         * @param databaseName    the name of the database file, or null for an in-memory database.
         * @param factory         the factory to use for creating cursor objects, or null for the default.
         * @param version         the version of the database. Each time the version is increased, missing columns will be added.
         * @param contractClasses
         */
        public VideoDetailDbOpenHelper(Context context, String databaseName,
                                       SQLiteDatabase.CursorFactory factory, int version,
                                       Class[] contractClasses) {
            super(context, databaseName, factory, version, contractClasses);
        }
    }
}
