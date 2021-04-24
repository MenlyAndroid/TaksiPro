package ru.taksi.pro.android.camera.rx

import android.annotation.SuppressLint
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import androidx.annotation.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter

object CameraCreator {

    @SuppressLint("MissingPermission")
    fun openCamera(
        @NonNull cameraId: String?,
        @NonNull cameraManager: CameraManager
    ): Observable<Pair<DeviceStateEvents?, CameraDevice?>?>? {
        return Observable.create { observableEmitter: ObservableEmitter<Pair<DeviceStateEvents?, CameraDevice?>?> ->
            cameraManager.openCamera(
                cameraId!!, object : CameraDevice.StateCallback() {
                    override fun onOpened(@NonNull cameraDevice: CameraDevice) {
                        observableEmitter.onNext(Pair(DeviceStateEvents.ON_OPENED, cameraDevice))
                    }

                    override fun onClosed(@NonNull cameraDevice: CameraDevice) {
                        observableEmitter.onNext(Pair(DeviceStateEvents.ON_CLOSED, cameraDevice))
                        observableEmitter.onComplete()
                    }

                    override fun onDisconnected(@NonNull cameraDevice: CameraDevice) {
                        observableEmitter.onNext(
                            Pair(
                                DeviceStateEvents.ON_DISCONNECTED,
                                cameraDevice
                            )
                        )
                        observableEmitter.onComplete()
                    }

                    override fun onError(@NonNull camera: CameraDevice, error: Int) {
                        observableEmitter.onError(
                            CameraAccessException(error)
                        )
                    }
                }, null
            )
        }
    }

    enum class DeviceStateEvents {
        ON_OPENED,
        ON_CLOSED,
        ON_DISCONNECTED
    }
}