package org.iesalandalus.likendero.p11bsqllite_javier_gonzalez_rives;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class AdminHelper extends SQLiteOpenHelper {

    public AdminHelper(Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table if not exists patatas(" +
                        "id int primary key," +
                        "variedad text," +
                        "kilos real," +
                        "comentarios text" +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
