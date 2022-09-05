package professorchaos0802.todo.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import professorchaos0802.todo.Constants
import professorchaos0802.todo.R
import professorchaos0802.todo.adapters.TodoListAdapter
import professorchaos0802.todo.databinding.FragmentHomeBinding
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.MyList

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var userModel: UserViewModel
    private lateinit var listModel: ListViewModel

    private val fragmentName = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(Constants.HOME, "Loading HomeFragment")
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        userModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        listModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]

        // Prevent user from seeing the screen elements until all the data is loaded
        binding.newListFab.visibility = View.GONE
        binding.todoLists.visibility = View.GONE

        setupFAB()
        loadAdapter()

        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     * Setup the FAB to allow the user to create new lists
     */
    private fun setupFAB(){
        binding.newListFab.setOnClickListener {
            val newList = MyList(owner=userModel.user!!.username)
            listModel.addNewList(newList)

            // Navigate to the list fragment
            findNavController().navigate(R.id.nav_list)
        }

    }

    /**
     * Create the adapter to show the lists available to the currently logged in user
     */
    private fun loadAdapter() {
        listModel.addListListener(fragmentName) {
            listModel.lists = listModel.lists.filter {
                it.owner == userModel.user!!.username ||
                        it.canEdit.contains(userModel.user!!.username) ||
                        it.canView.contains(userModel.user!!.username)
            } as ArrayList<MyList>

            // Reveal screen elements once all the data is loaded
            binding.newListFab.visibility = View.VISIBLE
            binding.todoLists.visibility = View.VISIBLE

        }

        var itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        binding.todoLists.apply{
            adapter = TodoListAdapter(findFragment())
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(itemDecoration)
        }
    }
}