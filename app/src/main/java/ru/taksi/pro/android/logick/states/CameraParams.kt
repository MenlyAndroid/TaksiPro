package ru.taksi.pro.android.logick.states

import android.hardware.camera2.CameraCharacteristics
import android.util.Size

class CameraParams {
    var cameraId: String? = null
    var previewSize: Size? = null
    var cameraCharacteristics: CameraCharacteristics? = null
}