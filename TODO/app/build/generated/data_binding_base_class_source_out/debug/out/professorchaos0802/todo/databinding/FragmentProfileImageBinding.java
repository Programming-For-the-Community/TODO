// Generated by view binder compiler. Do not edit!
package professorchaos0802.todo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import professorchaos0802.todo.R;

public final class FragmentProfileImageBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button profileImageCancelButton;

  @NonNull
  public final Button profileImageNextButton;

  @NonNull
  public final Button setupChooseImage;

  @NonNull
  public final ImageView setupProfileImage;

  private FragmentProfileImageBinding(@NonNull RelativeLayout rootView,
      @NonNull Button profileImageCancelButton, @NonNull Button profileImageNextButton,
      @NonNull Button setupChooseImage, @NonNull ImageView setupProfileImage) {
    this.rootView = rootView;
    this.profileImageCancelButton = profileImageCancelButton;
    this.profileImageNextButton = profileImageNextButton;
    this.setupChooseImage = setupChooseImage;
    this.setupProfileImage = setupProfileImage;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProfileImageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProfileImageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_profile_image, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProfileImageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.profile_image_cancel_button;
      Button profileImageCancelButton = ViewBindings.findChildViewById(rootView, id);
      if (profileImageCancelButton == null) {
        break missingId;
      }

      id = R.id.profile_image_next_button;
      Button profileImageNextButton = ViewBindings.findChildViewById(rootView, id);
      if (profileImageNextButton == null) {
        break missingId;
      }

      id = R.id.setup_choose_image;
      Button setupChooseImage = ViewBindings.findChildViewById(rootView, id);
      if (setupChooseImage == null) {
        break missingId;
      }

      id = R.id.setup_profile_image;
      ImageView setupProfileImage = ViewBindings.findChildViewById(rootView, id);
      if (setupProfileImage == null) {
        break missingId;
      }

      return new FragmentProfileImageBinding((RelativeLayout) rootView, profileImageCancelButton,
          profileImageNextButton, setupChooseImage, setupProfileImage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
