package com.adesso.lklein.roomcontacttest.models;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;


@Entity(tableName = "projekt")
public class Projekt {


    private String projektname;
    private String arbeitername;

    @PrimaryKey
    @NonNull
    private Date createdDate;

    public String getProjektname() {
        return projektname;
    }

    public void setProjektname(String projektname) {
        this.projektname = projektname;
    }

    public String getArbeitername() {
        return arbeitername;
    }

    public void setArbeitername(String arbeitername) {
        this.arbeitername = arbeitername;
    }

    public Date getCreatedDate(){
        return createdDate;
    }

    public void setCreatedDate(Date createdDate){
        this.createdDate = createdDate;
    }
}
