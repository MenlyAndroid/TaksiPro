package ru.taksi.pro.android.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.camera2.*
import android.media.Image
import android.media.ImageReader
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.Surface
import android.view.TextureView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.CameraLayoutBinding
import ru.taksi.pro.android.camera.states.CameraParams
import ru.taksi.pro.android.domain.helpers.CameraHandler
import ru.taksi.pro.android.domain.view.ICameraView
import java.io.File
import java.io.FileOutputStream
import java.util.*


class CameraActivity : AppCompatActivity(), ICameraView {
    private val TAG = "!!!CameraActivityTag"
    private var binding: CameraLayoutBinding? = null
    private val PERMISSION_REQUEST_CODE = 1100
    private val mOnSurfaceTextureAvailable = PublishSubject.create<SurfaceTexture>()
    private var mSurface: Surface? = null
    private val mCameraParams = CameraParams()
    private var mImageReader: ImageReader? = null
    private var mCameraManager: CameraManager? = null
    private var myCameras = mutableListOf<CameraHandler>()
    private var myCamerasId = mutableListOf<String>()
    private var mCaptureSession: CameraCaptureSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.camera_layout)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                ||
                ContextCompat.checkSelfPermission(
                    this@CameraActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ), PERMISSION_REQUEST_CODE
                )
            }
        }
        binding?.let {
            it.texture.surfaceTextureListener = object : TextureView.SurfaceTextureListener {
                override fun onSurfaceTextureAvailable(
                    surface: SurfaceTexture,
                    width: Int,
                    height: Int
                ) {
                    mOnSurfaceTextureAvailable.onNext(surface)
                }

                override fun onSurfaceTextureSizeChanged(p0: SurfaceTexture, p1: Int, p2: Int) {
                    TODO("Not yet implemented")
                }

                override fun onSurfaceTextureDestroyed(p0: SurfaceTexture): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onSurfaceTextureUpdated(p0: SurfaceTexture) {
                    TODO("Not yet implemented")
                }
            }
            it.btnChoseCamera.setOnClickListener {
                if (myCameras.size > 1) {
                    if (myCameras[0].isOpen()) {
                        myCameras[0].closeCamera()
                        myCameras[1].openCamera()
                    } else {
                        myCameras[1].closeCamera()
                        myCameras[0].openCamera()
                    }
                }
            }
        }
        mCameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        findCameras()
//        getCameraParams()
//        Log.d(TAG, "btn: ${myCameras.size}")
//        if (myCameras.size > 0) {
//            myCameras[0].openCamera()
//        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (requestCode == PERMISSION_REQUEST_CODE) {
                for (result in grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(
                            arrayOf(
                                Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            ), PERMISSION_REQUEST_CODE
                        )
                    }
                }
            }
        }
    }

    private fun setupSurface(surfaceTexture: SurfaceTexture) {
        mCameraParams.previewSize?.let { it ->
            surfaceTexture.setDefaultBufferSize(
                it.width,
                it.height
            )
        }
        Surface(surfaceTexture).also { mSurface = it }
    }

    fun createOnImageAvailableObservable(imageReader: ImageReader): Observable<ImageReader?>? {
        return Observable.create { subscriber ->
            val listener = ImageReader.OnImageAvailableListener { reader ->
                if (!subscriber.isDisposed()) {
                    subscriber.onNext(reader)
                }
            }
            imageReader.setOnImageAvailableListener(listener, null)
            subscriber.setCancellable {
                imageReader.setOnImageAvailableListener(
                    null,
                    null
                )
            } //remove listener on unsubscribe
        }
    }

    fun save(image: Image, file: File?): Single<File?>? {
        return Single.fromCallable {
            try {
                FileOutputStream(file).getChannel().use { output ->
                    output.write(image.getPlanes().get(0).getBuffer())
                    return@fromCallable file
                }
            } finally {
                image.close()
            }
        }
    }


//    private fun initImageReader() {
//        val sizeForImageReader: Size = CameraStrategy.getStillImageSize(
//            mCameraParams.cameraCharacteristics,
//            mCameraParams.previewSize
//        )
//        mImageReader = ImageReader.newInstance(
//            sizeForImageReader.getWidth(),
//            sizeForImageReader.getHeight(),
//            ImageFormat.JPEG,
//            1
//        )
//        mCompositeDisposable.add(
//            ImageSaverRxWrapper.createOnImageAvailableObservable(mImageReader)
//                .observeOn(Schedulers.io())
//                .flatMap { imageReader ->
//                    ImageSaverRxWrapper.save(imageReader.acquireLatestImage(), mFile).toObservable()
//                }
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { file ->
//                    mCallback.onPhotoTaken(
//                        file.getAbsolutePath(),
//                        getLensFacingPhotoType()
//                    )
//                }
//        )
//    }

    private fun findCameras() {
        try {
            // Получение списка камер с устройства
            mCameraManager?.let {
                myCamerasId = it.cameraIdList.toMutableList()
                // выводим информацию по камере
                for (cameraID in myCamerasId) {
                    Log.i(TAG, "cameraID: $cameraID")
                    val id = cameraID.toInt()
                    myCameras.add(CameraHandler(it, this, cameraID, this))

//                    // Получениe характеристик камеры
//                    val cc = it.getCameraCharacteristics(cameraID)
//                    // Получения списка выходного формата, который поддерживает камера
//                    val configurationMap =
//                        cc.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
//
//                    //  Определение какая камера куда смотрит
//                    val faceing = cc.get(CameraCharacteristics.LENS_FACING)!!
//                    if (faceing == CameraCharacteristics.LENS_FACING_FRONT) {
//                        Log.i(
//                            TAG,
//                            "Camera with ID: $cameraID  is FRONT CAMERA  "
//                        )
//                    }
//                    if (faceing == CameraCharacteristics.LENS_FACING_BACK) {
//                        Log.i(
//                            TAG,
//                            "Camera with: ID $cameraID is BACK CAMERA  "
//                        )
//                    }
//
//                    // Получения списка разрешений которые поддерживаются для формата jpeg
//                    val sizesJPEG: Array<Size>? =
//                        configurationMap!!.getOutputSizes(ImageFormat.JPEG)
//                    if (sizesJPEG != null) {
//                        for (item in sizesJPEG) {
//                            Log.i(
//                                TAG,
//                                "w:" + item.getWidth().toString() + " h:" + item.getHeight()
//                            )
//                        }
//                    } else {
//                        Log.i(TAG, "camera don`t support JPEG")
//                    }
                }
            }
        } catch (e: CameraAccessException) {
            Log.e(TAG, e.message!!)
            e.printStackTrace()
        }
    }

    private fun getCameraParams(number: Int) {
        mCameraParams.cameraId = myCameras[number].toString()
        mCameraParams.cameraCharacteristics =
            mCameraParams.cameraId?.let { mCameraManager?.getCameraCharacteristics(it) }
        mCameraParams.previewSize = getSize()
    }

    private fun getSize(): Size? {
        var size: Size? = null
        mCameraParams.cameraCharacteristics?.let { it ->
            val configurationMap = it.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
            val sizesJPEG: Array<Size>? =
                configurationMap?.getOutputSizes(ImageFormat.JPEG)
            sizesJPEG?.let{sizes ->
                for (item in sizes) {
                    if (item.width == 1920 && item.height == 1080) {
                        size = item
                    }
                    Log.i(
                        TAG,
                        "w:" + item.getWidth().toString() + " h:" + item.getHeight()
                    )
                }
            }
        }
        return size
    }

    override fun createCameraPreviewSession(mCameraDevice: CameraDevice) {
        binding?.texture?.surfaceTexture?.let {
            val surface = Surface(it)
            try {
                val builder: CaptureRequest.Builder =
                    mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                builder.addTarget(surface)
                mCameraDevice.createCaptureSession(
                    Arrays.asList(surface),
                    object : CameraCaptureSession.StateCallback() {
                        override fun onConfigured(session: CameraCaptureSession) {
                            mCaptureSession = session
                            try {
                                mCaptureSession?.setRepeatingRequest(builder.build(), null, null)
                            } catch (e: CameraAccessException) {
                                e.printStackTrace()
                            }
                        }

                        override fun onConfigureFailed(session: CameraCaptureSession) {}
                    }, null
                )
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }
        }
    }

//    var cameraDeviceObservable: Observable<Pair<CameraCreator.DeviceStateEvents, CameraDevice>> =
//        mOnSurfaceTextureAvailable
//            .firstElement()
//            .doAfterSuccess(::setupSurface)
//            .doAfterSuccess { __: SurfaceTexture? -> initImageReader() }
//            .toObservable()
//            .flatMap<Any> { __: SurfaceTexture? ->
//                mCameraManager?.let {
//                    CameraCreator.openCamera(
//                        cameraId,
//                        it
//                    )
//                }
//            }
//            .share()

//    var cameraDeviceObservable: Observable<Pair<CameraCreator.DeviceStateEvents, CameraDevice>> =
//        mOnSurfaceTextureAvailable
//            .firstElement()
//            .doAfterSuccess(this::setupSurface)
//            .toObservable()
//            .flatMap(__ -> CameraCreater.openCamera(mCameraParams.cameraId, mCameraManager))
//    .filter(pair -> pair.first == CameraRxWrapper.DeviceStateEvents.ON_OPENED)
//    .map(pair -> pair.second)
//    .flatMap(cameraDevice -> CameraRxWrapper
//    .createCaptureSession(cameraDevice, Arrays.asList(mSurface, mImageReader.getSurface()))
//    )
//    .filter(pair -> pair.first == CameraRxWrapper.CaptureSessionStateEvents.ON_CONFIGURED)
//    .map(pair -> pair.second)
//    .flatMap(cameraCaptureSession ->
//    {
//        CaptureRequest.Builder previewBuilder = createPreviewBuilder (cameraCaptureSession, mSurface);
//        return CameraRxWrapper.fromSetRepeatingRequest(
//            cameraCaptureSession,
//            previewBuilder.build()
//        );
//    })
//    .subscribe()
}