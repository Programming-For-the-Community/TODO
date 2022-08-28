package professorchaos0802.todo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import professorchaos0802.todo.databinding.ActivityMainBinding
import professorchaos0802.todo.databinding.FragmentSplashBinding
import professorchaos0802.todo.models.UserViewModel

class SplashFragment: Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private lateinit var userModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        userModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        // Inflate the layout for this fragment
        return binding.root
    }
}