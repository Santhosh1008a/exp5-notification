package com.example.exp5notification.data

import android.os.Parcel
import android.os.Parcelable

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val genre: String,
    val description: String,
    val coverImage: String,
    val rating: Float,
    val publicationYear: Int,
    val pages: Int,
    var progress: Int = 0,
    var isFavorite: Boolean = false,
    val freeReadingUrl: String,
    val sourceName: String = "Project Gutenberg"
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readFloat(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(genre)
        parcel.writeString(description)
        parcel.writeString(coverImage)
        parcel.writeFloat(rating)
        parcel.writeInt(publicationYear)
        parcel.writeInt(pages)
        parcel.writeInt(progress)
        parcel.writeByte(if (isFavorite) 1 else 0)
        parcel.writeString(freeReadingUrl)
        parcel.writeString(sourceName)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book = Book(parcel)
        override fun newArray(size: Int): Array<Book?> = arrayOfNulls(size)
    }
}
