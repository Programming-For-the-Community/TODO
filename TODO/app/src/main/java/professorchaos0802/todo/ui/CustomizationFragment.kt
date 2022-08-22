package professorchaos0802.todo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        binding = FragmentCustomizationBinding.inflate(inflater,container, false)
        userModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        setupSpinner()

        return binding.root
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
            TODO("Not yet implemented")
        }

        /**
         * Callback method to be invoked when the selection disappears from this
         * view. The selection can disappear for instance when touch is activated
         * or when the adapter becomes empty.
         *
         * @param parent The AdapterView that now contains no selected item.
         */
        override fun onNothingSelected(parent: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

    }
}