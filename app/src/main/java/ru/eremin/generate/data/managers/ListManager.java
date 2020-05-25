package ru.eremin.generate.data.managers;

import android.os.Parcel;
import android.os.Parcelable;

public class ListManager implements Parcelable{
    private String mId;
    private String mSurname;
    private String mName;
    private String mJobplace;
    private String mGender;
    private String mArea;
    private String mAvto;
    private String mAge;
    public ListManager(String id, String surname, String name, String jobplace, String gender, String area, String avto, String age){
        setmId(id);
        setmSurname(surname);
        setmName(name);
        setmJobplace(jobplace);
        setmGender(gender);
        setmArea(area);
        setmAvto(avto);
        setmAge(age);
    }

    private ListManager(Parcel in) {
        mId = in.readString();
        mSurname = in.readString();
        mName = in.readString();
        mJobplace = in.readString();
        mGender = in.readString();
        mArea = in.readString();
        mAvto = in.readString();
        mAge = in.readString();
    }

    public static final Creator<ListManager> CREATOR = new Creator<ListManager>() {
        @Override
        public ListManager createFromParcel(Parcel in) {
            return new ListManager(in);
        }

        @Override
        public ListManager[] newArray(int size) {
            return new ListManager[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mSurname);
        dest.writeString(mName);
        dest.writeString(mJobplace);
        dest.writeString(mGender);
        dest.writeString(mArea);
        dest.writeString(mAvto);
        dest.writeString(mAge);
    }
    public String getmId() {
        return mId;
    }
    private void setmId(String mId) {
        this.mId = mId;
    }

    public String getmSurname() {
        return mSurname;
    }
    private void setmSurname(String mSurname) {
        this.mSurname = mSurname;
    }

    public String getmName() {
        return mName;
    }
    private void setmName(String mName) {
        this.mName = mName;
    }

    public String getmJobplace() {
        return mJobplace;
    }
    private void setmJobplace(String mJobplace) {
        this.mJobplace = mJobplace;
    }

    public String getmGender() {
        return mGender;
    }
    private void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public String getmArea() {
        return mArea;
    }
    private void setmArea(String mArea) {
        this.mArea = mArea;
    }

    public String getmAvto() {
        return mAvto;
    }
    private void setmAvto(String mAvto) {
        this.mAvto = mAvto;
    }

    public String getmAge() {
        return mAge;
    }
    private void setmAge(String mAge) {
        this.mAge = mAge;
    }

}
