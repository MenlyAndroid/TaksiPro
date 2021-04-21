package ru.taksi.pro.android.logick.rx

import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraCaptureSession.CaptureCallback
import android.hardware.camera2.CaptureRequest
import android.os.Handler
import io.reactivex.rxjava3.core.Observable
import ru.taksi.pro.android.logick.rx.CaptureSessionDataCreator.CaptureSessionData


object ImageCreator {
    @Throws(CameraAccessException::class)
    fun capture(
        request: CaptureRequest?,
        listener: CaptureCallback?, handler: Handler?
    ): Int {
        //TODO
        return 0
    }

    fun fromCapture(
         captureSession: CameraCaptureSession,
         request: CaptureRequest?
    ): Observable<CaptureSessionData?>? {
        return Observable
            .create { observableEmitter ->
                captureSession.capture(
                    request!!,
                    CaptureSessionDataCreator.createCaptureCallback(observableEmitter),
                    null
                )
            }
    }
}