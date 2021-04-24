package ru.taksi.pro.android.camera.states

import android.hardware.camera2.CameraCharacteristics
import android.util.Size

class CameraParams {
    var cameraId: String? = null
    var previewSize: Size? = null
    var cameraCharacteristics: CameraCharacteristics? = null
}