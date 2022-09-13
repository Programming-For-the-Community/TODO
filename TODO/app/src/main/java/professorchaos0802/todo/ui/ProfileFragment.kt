package professorchaos0802.todo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import professorchaos0802.todo.Constants
import professorchaos0802.todo.databinding.FragmentProfileBinding
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel

class ProfileFragment: Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var userModel: UserViewModel
    private lateinit var listModel: ListViewModel

    companion object{
        const val fragmentName = "ProfileFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(Constants.HOME, "Loading $fragmentName")
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        userModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        listModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]

        // Inflate the layout for this fragment
        return binding.root
    }
}