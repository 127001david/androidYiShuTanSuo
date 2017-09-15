package com.app.davidwang.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DavidWang on 2017/7/7.
 */

public class IStudent implements Parcelable {
    public static final int SEX_MALE = 1;
    public static final int SEX_FEMALE = 2;

    public int sno;
    public String name;
    public int sex;
    public int age;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.sno);
        dest.writeString(this.name);
        dest.writeInt(this.sex);
        dest.writeInt(this.age);
    }

    public IStudent() {
    }

    protected IStudent(Parcel in) {
        this.sno = in.readInt();
        this.name = in.readString();
        this.sex = in.readInt();
        this.age = in.readInt();
    }

    public static final Creator<IStudent> CREATOR = new Creator<IStudent>() {
        @Override
        public IStudent createFromParcel(Parcel source) {
            return new IStudent(source);
        }

        @Override
        public IStudent[] newArray(int size) {
            return new IStudent[size];
        }
    };
}
