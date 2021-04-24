package ru.taksi.pro.android.domain.helpers

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.util.Log
import androidx.core.app.ActivityCompat
import ru.taksi.pro.android.domain.view.ICameraView


class CameraHandler(
    private val cameraManager: CameraManager,
    private val context: Context,
    private val mCameraID: String,
    private val cameraView: ICameraView
) :
    CameraDevice.StateCallback() {
    private val TAG = "!!!CameraHandler"
    private var mCameraDevice: CameraDevice? = null

    fun isOpen(): Boolean {
        return mCameraDevice != null
    }

    fun openCamera() {
        try {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d(TAG, "openCamera $mCameraID")
                mCameraID?.let {
                    cameraManager.openCamera(it, this, null)
                }
            }
        } catch (e: CameraAccessException) {
            Log.i(TAG, e.message!!)
        }
    }

    fun closeCamera() {
        mCameraDevice?.close()
        mCameraDevice = null
    }

    override fun onOpened(mCameraDevice: CameraDevice) {
        this.mCameraDevice = mCameraDevice
        Log.d(TAG, "onCameraOpen: $mCameraDevice")
        this.mCameraDevice?.let {
            cameraView.createCameraPreviewSession(it)
        }
    }

    override fun onDisconnected(mCameraDevice: CameraDevice) {
        if (this.mCameraDevice == mCameraDevice) {
            this.mCameraDevice?.close()
            this.mCameraDevice = null
        }
        Log.d(TAG, "onCameraDisconnected: $mCameraDevice")
    }

    override fun onError(mCameraDevice: CameraDevice, p1: Int) {
        if (this.mCameraDevice == mCameraDevice) {
            this.mCameraDevice = null
        }
        Log.d(TAG, "onCameraError: $mCameraDevice, $p1")
    }
}
