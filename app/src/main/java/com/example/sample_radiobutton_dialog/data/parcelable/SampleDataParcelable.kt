package com.example.sample_radiobutton_dialog.data.parcelable

import android.os.Parcel
import android.os.Parcelable

data class SampleDataParcelable(var id: Int, var title: String? = null) : Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<SampleDataParcelable> {
        override fun createFromParcel(parcel: Parcel): SampleDataParcelable {
            return SampleDataParcelable(parcel.readInt(), parcel.readString())
        }

        override fun newArray(size: Int): Array<SampleDataParcelable?> = arrayOfNulls(size)
    }
}
