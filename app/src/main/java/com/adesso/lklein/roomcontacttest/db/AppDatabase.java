package com.adesso.lklein.roomcontacttest.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.adesso.lklein.roomcontacttest.models.Projekt;


@Database(entities = {Projekt.class}, version = 1)
public abstract class AppDatabase  extends RoomDatabase {
    public abstract RoomDAO getRoomDAO();

}
