package professorchaos0802.todo.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import professorchaos0802.todo.Constants
import professorchaos0802.todo.databinding.FragmentHomeBinding
import professorchaos0802.todo.databinding.FragmentListBinding
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.models.UserViewModel


class ListFragment: Fragment() {
        private lateinit var binding: FragmentListBinding
        private lateinit var userModel: UserViewModel
        private lateinit var listModel: ListViewModel

        companion object{
            const val fragmentName = "ListFragment"
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            Log.d(Constants.HOME, "Loading $fragmentName")
            binding = FragmentListBinding.inflate(inflater, container, false)
            userModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
            listModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]

            // Inflate the layout for this fragment
            return binding.root
        }

        /**
         * Add in a customized toolbar menu for the home fragment in the onViewCreated method. Adds the custom menu and assigns actions to
         * the specific menu items.
         *
         * NOTE: traditional onCreateOptionsMenu() and onOptionsItemSelected() have been depreciated and this is now the new way to attach a custom menu
         */
//        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//            requireActivity().addMenuProvider(object : MenuProvider {
//                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater){
//                    menuInflater.inflate(R.menu.menu_home, menu)
//                }
//
//                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//                    return when(menuItem.itemId){
//                        R.id.profile_icon -> {
//                            findNavController().navigate(R.id.nav_profile)
//                            true
//                        }
//                        R.id.share_icon -> {
//                            true
//                        }
//                        else -> {
//                            true
//                        }
//                    }
//                }
//            })
//
//            super.onViewCreated(view, savedInstanceState)
//        }


}