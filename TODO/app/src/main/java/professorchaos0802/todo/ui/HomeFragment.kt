package professorchaos0802.todo.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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

    companion object{
        const val fragmentName = "HomeFragment"
        const val listListenerId = "HomeListListener"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(Constants.HOME, "Loading $fragmentName")
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        userModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        listModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]

        // Prevent user from seeing the screen elements until all the data is loaded
        binding.newListFab.visibility = View.GONE
        binding.todoLists.visibility = View.GONE

        setupFAB()
        loadAdapter()
        swipeToDelete()

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        listModel.removeListener(listListenerId)
    }

    /**
     * Add in a customized toolbar menu for the home fragment in the onViewCreated method. Adds the custom menu and assigns actions to
     * the specific menu items.
     *
     * NOTE: traditional onCreateOptionsMenu() and onOptionsItemSelected() have been depreciated and this is now the new way to attach a custom menu
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater){
                menuInflater.inflate(R.menu.menu_home, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.profile_icon -> {
                        findNavController().navigate(R.id.nav_profile)
                        true
                    }
                    R.id.share_icon -> {
                        true
                    }
                    else -> {
                        true
                    }
                }
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * Setup the FAB to allow the user to create new lists
     */
    private fun setupFAB(){
        binding.newListFab.setOnClickListener {
            val newList = MyList(owner=userModel.user!!.username, title="Title")
            listModel.addNewList(newList)

            // Navigate to the list fragment
            findNavController().navigate(R.id.nav_list)
        }

    }

    /**
     * Create the adapter to show the lists available to the currently logged in user
     */
    private fun loadAdapter() {
        listModel.addListListener(listListenerId) { addListListener() }
    }

    /**
     * Add the Firebase listener to accurately display the lists available to the user
     */
    private fun addListListener(){
        val listPreviewAdapter = TodoListAdapter(this)
        var itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        // Adapter for recycler view
        binding.todoLists.apply{
            adapter = listPreviewAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(itemDecoration)
        }

        Log.d(Constants.HOME, "Filtering lists")
        listModel.lists = listModel.lists.filter {
            it.owner == userModel.user!!.username ||
                    it.canEdit.contains(userModel.user!!.username) ||
                    it.canView.contains(userModel.user!!.username)
        } as ArrayList<MyList>


        // Reveal screen elements once all the data is loaded
        binding.newListFab.visibility = View.VISIBLE
        if(listModel.lists.size > 0) { binding.todoLists.visibility = View.VISIBLE } // Only show the list view if there are lists assigned to the user
        Log.d(Constants.HOME, "List Listener added")
    }

    private fun swipeToDelete(){
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int){
                val pos = viewHolder.adapterPosition
                val listToDelete = listModel.lists[pos]
                listModel.deleteList(listToDelete)
                binding.todoLists.adapter!!.notifyItemRemoved(pos)

                Snackbar.make(binding.todoLists, "You deleted " + listToDelete.title, Snackbar.LENGTH_LONG)
                    .setAction("Undo",
                    View.OnClickListener {
                        listModel.addNewList(listToDelete)
                        binding.todoLists.adapter!!.notifyItemInserted(pos)
                    })
                    .show()
            }
        }).attachToRecyclerView(binding.todoLists)
    }
}