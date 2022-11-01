// Generated by view binder compiler. Do not edit!
package professorchaos0802.todo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import professorchaos0802.todo.R;

public final class FragmentListBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RecyclerView listItemRecycler;

  @NonNull
  public final EditText listTitle;

  @NonNull
  public final Button listUncheckAll;

  @NonNull
  public final EditText newItem;

  private FragmentListBinding(@NonNull RelativeLayout rootView,
      @NonNull RecyclerView listItemRecycler, @NonNull EditText listTitle,
      @NonNull Button listUncheckAll, @NonNull EditText newItem) {
    this.rootView = rootView;
    this.listItemRecycler = listItemRecycler;
    this.listTitle = listTitle;
    this.listUncheckAll = listUncheckAll;
    this.newItem = newItem;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.list_item_recycler;
      RecyclerView listItemRecycler = ViewBindings.findChildViewById(rootView, id);
      if (listItemRecycler == null) {
        break missingId;
      }

      id = R.id.list_title;
      EditText listTitle = ViewBindings.findChildViewById(rootView, id);
      if (listTitle == null) {
        break missingId;
      }

      id = R.id.list_uncheck_all;
      Button listUncheckAll = ViewBindings.findChildViewById(rootView, id);
      if (listUncheckAll == null) {
        break missingId;
      }

      id = R.id.new_item;
      EditText newItem = ViewBindings.findChildViewById(rootView, id);
      if (newItem == null) {
        break missingId;
      }

      return new FragmentListBinding((RelativeLayout) rootView, listItemRecycler, listTitle,
          listUncheckAll, newItem);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
