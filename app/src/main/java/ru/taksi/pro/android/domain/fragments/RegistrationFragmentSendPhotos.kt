package ru.taksi.pro.android.domain.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ru.taksi.pro.android.R
import ru.taksi.pro.android.databinding.RegistrFragmentSendPhotoBinding
import ru.taksi.pro.android.domain.activities.MainActivity
import ru.taksi.pro.android.domain.activities.RegistrationActivity
import ru.taksi.pro.android.mvvm.data.PhotoSaver
import java.io.FileNotFoundException
import java.io.InputStream


class RegistrationFragmentSendPhotos(private val intent: Intent?) : Fragment() {
    private lateinit var binding: RegistrFragmentSendPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = RegistrFragmentSendPhotoBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onContentChanged()
        checkIntent()
        setPhotos()
        if (checkPermission()) {
            requestPermission()
        } else {
            initComponent()
        }
    }

    private fun checkIntent() {
        intent?.let {
            val imageUri: Uri? = it.getData()
            Log.d("!!!", imageUri.toString())
            val photoNumber = it.getIntExtra("photo", 0)
            when (photoNumber) {
                1 -> PhotoSaver.instance.firstPassportPhotoUri = imageUri
                2 -> PhotoSaver.instance.secondPassportPhotoUri = imageUri
                3 -> PhotoSaver.instance.firstDriversLicensePhotoUri = imageUri
                4 -> PhotoSaver.instance.secondDriversLicensePhotoUri = imageUri
            }
        }
    }

    private fun setPhotos() {
        try {
            PhotoSaver.instance.firstPassportPhotoUri?.let {
                val imageStream: InputStream? =
                    requireActivity().getContentResolver().openInputStream(it)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                binding.imgPassportFirstPhoto.setImageBitmap(selectedImage)
            }
            PhotoSaver.instance.secondPassportPhotoUri?.let {
                val imageStream: InputStream? =
                    requireActivity().getContentResolver().openInputStream(it)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                binding.imgPassportSecondPhoto.setImageBitmap(selectedImage)
            }
            PhotoSaver.instance.firstDriversLicensePhotoUri?.let {
                val imageStream: InputStream? =
                    requireActivity().getContentResolver().openInputStream(it)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                binding.imgDriverLicenseFirstPhoto.setImageBitmap(selectedImage)
            }
            PhotoSaver.instance.secondDriversLicensePhotoUri?.let {
                val imageStream: InputStream? =
                    requireActivity().getContentResolver().openInputStream(it)
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                binding.imgDriverLicenseSecondPhoto.setImageBitmap(selectedImage)
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun initComponent() {
        binding.imgPassportFirstPhoto.setOnClickListener {
            getImageFromAlbum(PhotoSaver.FIRST_PASSPORT_PHOTO)
        }
        binding.imgPassportSecondPhoto.setOnClickListener {
            getImageFromAlbum(PhotoSaver.SECOND_PASSPORT_PHOTO)
        }
        binding.imgDriverLicenseFirstPhoto.setOnClickListener {
            getImageFromAlbum(PhotoSaver.FIRST_DRIVERS_LICENSE_PHOTO)
        }
        binding.imgDriverLicenseSecondPhoto.setOnClickListener {
            getImageFromAlbum(PhotoSaver.SECOND_DRIVERS_LICENSE_PHOTO)
        }

        binding.leftPassportDeleteBtn.setOnClickListener{
            binding.imgPassportFirstPhoto.setImageResource(R.drawable.ic_add_photo)
            PhotoSaver.instance.firstPassportPhotoUri = null
            binding.buttonSendPhotos.isEnabled = false
        }
        binding.rightPassportDeleteBtn.setOnClickListener{
            binding.imgPassportSecondPhoto.setImageResource(R.drawable.ic_add_photo)
        }
        binding.leftLicenseDeleteBtn.setOnClickListener{
            binding.imgDriverLicenseFirstPhoto.setImageResource(R.drawable.ic_add_photo)
            PhotoSaver.instance.firstDriversLicensePhotoUri = null
        }
        binding.rightLicenseDeleteBtn.setOnClickListener{
            binding.imgDriverLicenseSecondPhoto.setImageResource(R.drawable.ic_add_photo)
            PhotoSaver.instance.secondDriversLicensePhotoUri = null
        }

        binding.buttonSendPhotos.setOnClickListener {
            if (PhotoSaver.instance.firstPassportPhotoUri != null &&
                PhotoSaver.instance.secondPassportPhotoUri != null &&
                PhotoSaver.instance.firstDriversLicensePhotoUri != null &&
                PhotoSaver.instance.secondDriversLicensePhotoUri != null) {
                startActivity(Intent(requireActivity(), MainActivity::class.java))
            }
        }
    }

    private fun getImageFromAlbum(numberPhoto: Int) {
        try {
            val i = Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            i.type = "image/*"
            requireActivity().startActivityForResult(i, numberPhoto)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            RegistrationActivity.READ_WRITE_STORAGE
        )
    }
}
