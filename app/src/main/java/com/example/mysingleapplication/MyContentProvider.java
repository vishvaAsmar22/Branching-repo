package com.example.mysingleapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider {
    public MyContentProvider() {
    }




    public static  final  String AUTHORITY = "com.app.notes";
    public  static  final  Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/Notes");
    static  int USER =1;
    static  int USER_ID =2;

    //class used to match Content_URL everytime you access ,because how many tables are there and all other things manage by UriMatcher class
    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    static {

        uriMatcher.addURI(AUTHORITY,"Notes",USER);
        uriMatcher.addURI(AUTHORITY,"Notes/#",USER_ID); //particular row will access
        //if you have mare than one table then we keep adding uriMatcher like above

    }




    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.

        NotesDao dao;
        Context context = getContext();

        dao= AppDatabase.getInstance(context).notesDao();

        Cursor cursor = dao.getAccessNotes();
        cursor.setNotificationUri(context.getContentResolver(),uri);

        Log.d("RESPONSE","Data is:"+cursor);
        return cursor;







    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}