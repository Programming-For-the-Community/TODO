package professorchaos0802.todo.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import professorchaos0802.todo.Constants
import professorchaos0802.todo.R
import professorchaos0802.todo.models.ListViewModel
import professorchaos0802.todo.objects.Item
import professorchaos0802.todo.objects.MyList
import professorchaos0802.todo.ui.HomeFragment

class TodoListAdapter(val fragment: HomeFragment): RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>(){
    private val listModel = ViewModelProvider(fragment.requireActivity())[ListViewModel::class.java]

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_list_view, parent, false)
        return TodoListViewHolder(view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.bind(listModel.lists[position])
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount() = listModel.lists.size

    inner class TodoListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var owner: TextView = itemView.findViewById<TextView>(R.id.list_preview_owner)
        private var timestamp: TextView = itemView.findViewById<TextView>(R.id.list_preview_timestamp)
        private var title: TextView = itemView.findViewById<TextView>(R.id.list_preview_title)
        private var layout: RelativeLayout = itemView.findViewById<RelativeLayout>(R.id.list_preview_layout)

        init{
            // When the layout is clicked, set the current list to the list that was selected and then navigate to the list screen
            layout.setOnClickListener {
                listModel.updateList(listModel.lists[adapterPosition])
                fragment.findNavController().navigate(R.id.nav_list)
            }
        }

        fun bind(list: MyList){
            // Bind list specific info to each list
            title.text = list.title
            owner.text = list.owner
            timestamp.text = list.created!!.toDate().toString()

        }
    }
}