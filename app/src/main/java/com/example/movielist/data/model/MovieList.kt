package com.example.movielist.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Content(
    @SerializedName("name") var name: String? = null,
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: Int? = null,
    @SerializedName("poster-image") var posterImage: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(id)
        parcel.writeString(posterImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Content> {
        override fun createFromParcel(parcel: Parcel): Content {
            return Content(parcel)
        }

        override fun newArray(size: Int): Array<Content?> {
            return arrayOfNulls(size)
        }
    }
}

data class ContentItems(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: Int? = null,
    @SerializedName("content")
    var content: List<Content>?
)

data class MovieList(
    @SerializedName("page")
    var page: Page?
)


data class Page(
    @SerializedName("title")
    @Expose
    var title: String? = "",

    @SerializedName("total-content-items")
    @Expose
    var totalContentItems: String?,

    @SerializedName("page-num")
    @Expose
    var pageNum: String?,

    @SerializedName("page-size")
    @Expose
    var pageSize: String?,

    @SerializedName("content-items")
    @Expose
    var contentItems: ContentItems?

)