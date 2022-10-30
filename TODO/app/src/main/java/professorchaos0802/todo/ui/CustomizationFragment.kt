package professorchaos0802.todo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import professorchaos0802.todo.Constants
import professorchaos0802.todo.R
import professorchaos0802.todo.databinding.FragmentCustomizationBinding
import professorchaos0802.todo.models.UserViewModel

class CustomizationFragment: Fragment() {
    private lateinit var binding: FragmentCustomizationBinding
    private lateinit var userModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(Constants.SETUP, "Loading CustomizationFragment")
        binding = FragmentCustomizationBinding.inflate(inflater,container, false)
        userModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        setupSpinner()
        setupButtons()

        return binding.root
    }

    /**
     * Setup the radio buttons and the next and cancel buttons
     */
    private fun setupButtons(){
        var radioGroup = binding.setupRadioGroup

        // Set the radio group selection the match the current status of the user profile
        if(userModel.user!!.isVisible){
            radioGroup.check(R.id.setup_profile_public)
        }else{
            radioGroup.check(R.id.setup_profile_private)
        }

        // Attach listener to the radio group for selecting profile visibility
        radioGroup.setOnCheckedChangeListener { RadioGroup, checkedId ->
            when(checkedId){
                R.id.setup_profile_private -> userModel.user!!.isVisible = false
                R.id.setup_profile_public -> userModel.user!!.isVisible = true
                else -> Log.d(Constants.SETUP, "An invalid visibility option was selected")
            }
        }

        // Log-out the user if the cancel the setup process
        binding.customizationCancelButton.setOnClickListener{
            Log.d(Constants.SETUP, "Logging out from CustomizationFragment")
//            findNavController().navigate(R.id.nav_splash)
            Firebase.auth.signOut()
            userModel.user = null
        }

        // Send the user to the next setup screen when they are ready to continue
        binding.customizationNextButton.setOnClickListener {
            userModel.update()
//            Log.d(Constants.SETUP, "Navigating to ProfileImage: ${R.id.nav_profile_image}")
//            findNavController().navigate(R.id.nav_profile_image)
        }
        
    }

    /**
     * Setup the theme spinner
     */
    private fun setupSpinner(){
        val themes: Spinner = binding.seutpThemeSpinner

        // Create Array adapter using the defined custom themes array and the default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.custom_themes_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Apply a custom layout to the spinner dropdown menu
            adapter.setDropDownViewResource(R.layout.theme_spinner_item_dropdown)

            // Apply adapter to spinner
            themes.adapter = adapter
        }

        var options = requireContext().resources.getStringArray(R.array.custom_themes_array)

        // Set the selection of the spinner to match the current theme chosen by the user
        themes.setSelection(options.indexOf(userModel.user!!.theme))
        themes.onItemSelectedListener = ThemeSpinnerListener()
    }

    inner class ThemeSpinnerListener: AdapterView.OnItemSelectedListener{
        /**
         *
         * Callback method to be invoked when an item in this view has been
         * selected. This callback is invoked only when the newly selected
         * position is different from the previously selected position or if
         * there was no selected item.
         *
         * Implementers can call getItemAtPosition(position) if they need to access the
         * data associated with the selected item.
         *
         * @param parent The AdapterView where the selection happened
         * @param view The view within the AdapterView that was clicked
         * @param position The position of the view in the adapter
         * @param id The row id of the item that is selected
         */
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val theme = binding.seutpThemeSpinner.getItemAtPosition(position)
            userModel.user!!.theme = theme as String
        }

        /**
         * Callback method to be invoked when the selection disappears from this
         * view. The selection can disappear for instance when touch is activated
         * or when the adapter becomes empty.
         *
         * @param parent The AdapterView that now contains no selected item.
         */
        override fun onNothingSelected(parent: AdapterView<*>?) {
            var options = requireContext().resources.getStringArray(R.array.custom_themes_array)
            userModel.user!!.theme = binding.seutpThemeSpinner.getItemAtPosition(options.indexOf(userModel.user!!.theme)) as String
        }

    }
}