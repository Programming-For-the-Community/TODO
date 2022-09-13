package professorchaos0802.todo.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ListAdapter
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import professorchaos0802.todo.Constants
import professorchaos0802.todo.R
import professorchaos0802.todo.adapters.ItemPreviewAdapter
import professorchaos0802.todo.adapters.TodoListAdapter
import professorchaos0802.todo.databinding.FragmentHomeBinding
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var userModel: UserViewModel
    private lateinit var listModel: ListViewModel
    private var itemAdapters = ArrayList<ItemPreviewAdapter>()
    private var count = 0

    companion object{
        const val fragmentName = "HomeFragment"
        const val listListenerId = "HomeListListener"
        const val itemListenerId = "HomeItemListener"
    }

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
        Log.d(Constants.HOME, "Filtering lists")
        listModel.lists = listModel.lists.filter {
            it.owner == userModel.user!!.username ||
                    it.canEdit.contains(userModel.user!!.username) ||
                    it.canView.contains(userModel.user!!.username)
        } as ArrayList<MyList>

        listModel.lists.forEach {
            addItemListener(it)
        }

        // Reveal screen elements once all the data is loaded
        binding.newListFab.visibility = View.VISIBLE
        binding.todoLists.visibility = View.VISIBLE
        Log.d(Constants.HOME, "List Listener added")
    }

    /**
     * Add a Firebase listener to the items of each list
     */
    fun addItemListener(it: MyList){
        listModel.currentList = it
        listModel.addItemListener(it.id + itemListenerId){
            listModel.currentList.items.forEach {item -> Log.d(Constants.HOME, "List ${it.title}: Item -> ${item.text}") }

            Log.d(Constants.HOME, "${it.title} Items to Preview: ${it.items.size}")
            val itemsToPreview = if(it.items.size > 5){
                it.items.subList(0, 4) as ArrayList<Item>
            }else{
                it.items
            }

            itemAdapters.add(ItemPreviewAdapter(this, itemsToPreview))

            if(count == listModel.lists.size-1){
                Log.d(Constants.HOME, "$count")
                val listPreviewAdapter = TodoListAdapter(this, itemAdapters)
                var itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

                // Adapter for recycler view
                binding.todoLists.apply{
                    adapter = listPreviewAdapter
                    layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    addItemDecoration(itemDecoration)
                }

                listPreviewAdapter.update()
            }

            count++
            Log.d(Constants.HOME, "Item Listener added to List ${it.title}")
        }
    }
}