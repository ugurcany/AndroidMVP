package com.accenture.androidmvp.model;

import android.content.Context;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

/**
 * Created by ugurcan.yildirim on 30.01.2017.
 */
public class LocalDbInteractor<T> {

    private String tableName = "";
    private Context context;

    public LocalDbInteractor(Context context){
        this.context = context;
    }

    public void setTableName(String tableName){
        this.tableName = tableName;
    }

    public void getObject(OnResponseListener<T> responseListener, String key, Class<T> objClass){
        T object = null;

        try {
            DB db = DBFactory.open(context, tableName);

            object = db.getObject(key, objClass);

            db.close();
        } catch(SnappydbException ex) {
            ex.printStackTrace();
        }

        if(object != null) {
            responseListener.onLocallyExist(object);
        }
    }

    public void putObject(String key, T object){
        if(object == null)
            return;

        try {
            DB db = DBFactory.open(context, tableName);

            db.put(key, object);

            db.close();
        } catch(SnappydbException ex) {
            ex.printStackTrace();
        }
    }

}
