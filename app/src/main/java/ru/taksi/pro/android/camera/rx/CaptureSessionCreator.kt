package ru.taksi.pro.android.camera.rx

import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.view.Surface
import androidx.annotation.NonNull
import io.reactivex.rxjava3.core.Observable


object CaptureSessionCreator {
    @NonNull
    fun createCaptureSession(
        @NonNull cameraDevice: CameraDevice,
        @NonNull surfaceList: List<Surface>
    ): Observable<Pair<CaptureSessionStateEvents?, CameraCaptureSession?>?>? {
        return Observable.create { observableEmitter ->
            cameraDevice.createCaptureSession(
                surfaceList,
                object : CameraCaptureSession.StateCallback() {
                    override fun onConfigured(@NonNull session: CameraCaptureSession) {
                        observableEmitter.onNext(
                            Pair(
                                CaptureSessionStateEvents.ON_CONFIGURED,
                                session
                            )
                        )
                    }

                    override fun onConfigureFailed(@NonNull session: CameraCaptureSession) {
                        observableEmitter.onError(IllegalStateException())
                    }

                    override fun onReady(@NonNull session: CameraCaptureSession) {
                        observableEmitter.onNext(Pair(CaptureSessionStateEvents.ON_READY, session))
                    }

                    override fun onActive(@NonNull session: CameraCaptureSession) {
                        observableEmitter.onNext(Pair(CaptureSessionStateEvents.ON_ACTIVE, session))
                    }

                    override fun onClosed(@NonNull session: CameraCaptureSession) {
                        observableEmitter.onNext(Pair(CaptureSessionStateEvents.ON_CLOSED, session))
                        observableEmitter.onComplete()
                    }

                    override fun onSurfacePrepared(
                        session: CameraCaptureSession,
                        surface: Surface
                    ) {
                        observableEmitter.onNext(
                            Pair(
                                CaptureSessionStateEvents.ON_SURFACE_PREPARED,
                                session
                            )
                        )
                    }
                },
                null
            )
        }
    }

    enum class CaptureSessionStateEvents {
        ON_CONFIGURED,
        ON_READY,
        ON_ACTIVE,
        ON_CLOSED,
        ON_SURFACE_PREPARED
    }
}