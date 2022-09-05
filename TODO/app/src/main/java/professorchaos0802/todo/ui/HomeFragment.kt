package professorchaos0802.todo.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import professorchaos0802.todo.Constants
import professorchaos0802.todo.databinding.FragmentHomeBinding
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var userModel: UserViewModel
    private lateinit var listModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(Constants.HOME, "Loading HomeFragment")
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        userModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        listModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]

        // Inflate the layout for this fragment
        return binding.root
    }

}