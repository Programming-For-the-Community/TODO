package professorchaos0802.todo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import professorchaos0802.todo.R
import professorchaos0802.todo.databinding.FragmentProfileImageBinding
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.User

class ProfileImageFragment: Fragment() {
    private lateinit var binding: FragmentProfileImageBinding
    private lateinit var userModel: UserViewModel

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

    private fun setupChoosePhotoButton(){

    }

    private fun setupProgressionButtons(){

    }
}