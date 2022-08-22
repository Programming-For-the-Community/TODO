package professorchaos0802.todo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import professorchaos0802.todo.R
import professorchaos0802.todo.databinding.FragmentUserNameSetupBinding
import professorchaos0802.todo.models.UserViewModel

class UserNameSetupFragment: Fragment() {
    private lateinit var binding: FragmentUserNameSetupBinding
    private lateinit var userModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserNameSetupBinding.inflate(inflater, container, false)
        userModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        setupButtons()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setupButtons(){
        // Log-out the user if they choose to cancel the setup process
        binding.userNameCancelButton.setOnClickListener{
            findNavController().navigate(R.id.nav_splash)
            Firebase.auth.signOut()
            userModel.user = null
        }

        // Advance the user to the next screen when the select the next button
        binding.userNameCancelButton.setOnClickListener {
            userModel.updateName(binding.userNameSetupEditText.text.toString())
            findNavController().navigate(R.id.nav_customization)
        }
    }
}