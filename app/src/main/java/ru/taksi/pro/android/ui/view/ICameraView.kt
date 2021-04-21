package ru.taksi.pro.android.ui.view

import android.hardware.camera2.CameraDevice

interface ICameraView {
    fun createCameraPreviewSession(mCameraDevice: CameraDevice)
}