package ru.taksi.pro.android.mvvm.data

import android.net.Uri

class PhotoSaver {

    val aggregators: MutableSet<String> = mutableSetOf()

    companion object{
        val instance = PhotoSaver()
        val FIRST_PASSPORT_PHOTO = 1
        val SECOND_PASSPORT_PHOTO = 2
        val FIRST_DRIVERS_LICENSE_PHOTO = 3
        val SECOND_DRIVERS_LICENSE_PHOTO = 4
    }

    var firstPassportPhotoUri: Uri? = null
    var secondPassportPhotoUri: Uri? = null
    var firstDriversLicensePhotoUri: Uri? = null
    var secondDriversLicensePhotoUri: Uri? = null
}