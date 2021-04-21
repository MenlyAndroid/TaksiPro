package ru.taksi.pro.android.logick.rx

import android.hardware.camera2.*
import android.hardware.camera2.CameraCaptureSession.CaptureCallback
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter


object CaptureSessionDataCreator {

    fun fromSetRepeatingRequest(
         captureSession: CameraCaptureSession,
         request: CaptureRequest?
    ): Observable<CaptureSessionData?>? {
        return Observable
            .create { observableEmitter ->
                captureSession.setRepeatingRequest(
                    request!!,
                    createCaptureCallback(observableEmitter),
                    null
                )
            }
    }

    fun createCaptureCallback(observableEmitter: @NonNull ObservableEmitter<CaptureSessionData?>): CaptureCallback? {
        return object : CaptureCallback() {
            override fun onCaptureStarted(
                session: CameraCaptureSession,
                request: CaptureRequest,
                timestamp: Long,
                frameNumber: Long
            ) {
            }

            override fun onCaptureProgressed(
                session: CameraCaptureSession,
                request: CaptureRequest,
                partialResult: CaptureResult
            ) {
            }

            override fun onCaptureCompleted(
                session: CameraCaptureSession,
                request: CaptureRequest,
                result: TotalCaptureResult
            ) {
                if (!observableEmitter.isDisposed()) {
                    observableEmitter.onNext(
                        CaptureSessionData(
                            CaptureSessionEvents.ON_COMPLETED,
                            session,
                            request,
                            result
                        )
                    )
                }
            }

            override fun onCaptureFailed(
                session: CameraCaptureSession,
                request: CaptureRequest,
                failure: CaptureFailure
            ) {
                if (!observableEmitter.isDisposed()) {
                    observableEmitter.onError(IllegalStateException())
                }
            }

            override fun onCaptureSequenceCompleted(
                session: CameraCaptureSession,
                sequenceId: Int,
                frameNumber: Long
            ) {
            }

            override fun onCaptureSequenceAborted(
                session: CameraCaptureSession,
                sequenceId: Int
            ) {
            }
        }
    }

    class CaptureSessionData internal constructor(
        val event: CaptureSessionEvents,
        val session: CameraCaptureSession,
        val request: CaptureRequest,
        val result: CaptureResult
    )

    enum class CaptureSessionEvents {
        ON_STARTED, ON_PROGRESSED, ON_COMPLETED, ON_SEQUENCE_COMPLETED, ON_SEQUENCE_ABORTED
    }
}