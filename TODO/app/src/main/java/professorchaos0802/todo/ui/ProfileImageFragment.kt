package professorchaos0802.todo.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.CircleCropTransformation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import professorchaos0802.todo.R
import professorchaos0802.todo.databinding.FragmentProfileImageBinding
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.User


class ProfileImageFragment: Fragment() {
    private lateinit var binding: FragmentProfileImageBinding
    private lateinit var userModel: UserViewModel
    var galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    val pickImageResult =
        registerForActivityResult(ActivityResultContracts.GetContent()){ newImage ->
            userModel.addImageFromUri(this, newImage)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileImageBinding.inflate(inflater, container, false)
        userModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        setupProfileImage()
        setupChoosePhotoButton()
        setupProgressionButtons()

        return binding.root
    }

    /**
     * Loads the user profile image to the screen
     */
    private fun setupProfileImage(){
        var img: ImageView = binding.setupProfileImage
        var user: User = userModel.user!!

        if(user.img == ""){
            img.setImageDrawable(requireActivity().getDrawable(R.drawable.ic_profile_img))
        }else{
            img.load(user.img){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }
    }

    /**
     * Updates the profile image
     */
    private fun updateProfileImage(){
        binding.setupProfileImage.load(userModel.user!!.img){
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }

    /**
     * Gets permissions for and then launches the choose image Activity
     */
    private fun setupChoosePhotoButton(){
        binding.setupChooseImage.setOnClickListener {
            chooseImageGallery()
        }
    }

    private fun chooseImageGallery(){
        lifecycleScope.launchWhenStarted{
            userModel.getTmpFileUri(fragment = this@ProfileImageFragment).let {uri ->
                pickImageResult.launch(uri.toString())
            }
        }
    }

    private fun setupProgressionButtons(){
        // Log-out the user if they choose to cancel the setup process
        binding.profileImageCancelButton.setOnClickListener{
            findNavController().navigate(R.id.nav_splash)
            Firebase.auth.signOut()
            userModel.user = null
        }

        // Advance the user to the next screen when the select the next button
        binding.profileImageNextButton.setOnClickListener {
            userModel.update()
            findNavController().navigate(R.id.nav_home)
        }
    }
}