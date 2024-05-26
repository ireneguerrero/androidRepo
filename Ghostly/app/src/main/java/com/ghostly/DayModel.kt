package com.ghostly

import android.os.Parcel
import android.os.Parcelable

data class DayModel(val day: Int, var emotion: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(day)
        parcel.writeInt(emotion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DayModel> {
        override fun createFromParcel(parcel: Parcel): DayModel {
            return DayModel(parcel)
        }

        override fun newArray(size: Int): Array<DayModel?> {
            return arrayOfNulls(size)
        }
    }
}