package org.iesalandalus.likendero.p11sqlite_javier_gonzalez_rives;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * @author Javier Gonzalez Rives
 *
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    /**
     * metodo constructor
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table articulos(" +
                "codigo int primary key," +
                "descripcion text," +
                "precio real" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        if(i==1 && i1>=2)
            database.execSQL(
                    "alter table articulos " +
                            "add column color text"
            );
    }
}
