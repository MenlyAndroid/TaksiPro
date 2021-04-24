package ru.taksi.pro.android.domain.view

import android.hardware.camera2.CameraDevice

interface ICameraView {
    fun createCameraPreviewSession(mCameraDevice: CameraDevice)
}