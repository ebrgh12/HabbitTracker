/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.habbittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database helper for habbit app. Manages database creation and version management.
 */
public class HabbitDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabbitDbHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "habbit.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link HabbitDbHelper}.
     *
     * @param context of the app
     */
    public HabbitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PERSON_TABLE = "CREATE TABLE " + HabbitContract.HabbitEntry.TABLE_NAME + " ("
                + HabbitContract.HabbitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabbitContract.HabbitEntry.COLUMN_PERSON_NAME + " TEXT NOT NULL, "
                + HabbitContract.HabbitEntry.COLUMN_PERSON_HABBIT + " TEXT, "
                + HabbitContract.HabbitEntry.COLUMN_PERSON_GENDER + " INTEGER NOT NULL, "
                + HabbitContract.HabbitEntry.COLUMN_PERSON_TIME + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PERSON_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }

    //Method to update the db
    public void updateDatabase(Context context, String dbName) {

        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues dataToInsert = new ContentValues();
            dataToInsert.put("name", "abc");
            dataToInsert.put("gender", "qwe");

            String where = "_id";
            String[] whereArgs = new String[]{String.valueOf(null)};
            db.update("person", dataToInsert, where, whereArgs);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void deleteDatabase(Context context, String shelter) {

        context.deleteDatabase("habbit.db");


    }
}