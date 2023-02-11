package de.phtp.worldguessr.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

import de.phtp.worldguessr.R;
import de.phtp.worldguessr.databinding.FragmentPictureBinding;
import de.phtp.worldguessr.view.activity.GameScreenActivity;

public class PictureFragment extends Fragment {

    private FragmentPictureBinding binding;
    private View root;

    private GameScreenActivity gameScreenActivity;

    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPictureBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        gameScreenActivity = (GameScreenActivity) getActivity();

        imageView = binding.fragmentPictureImageView;

        AsyncTask.execute(()->{
            String resName = gameScreenActivity.getDao().getPictureName(gameScreenActivity.getCurrentImageId());
            int pictureID = 0;
            try {
                Field idField = R.drawable.class.getDeclaredField(resName);
                pictureID = idField.getInt(idField);
            } catch (Exception e) {
                e.printStackTrace();
            }
            imageView.setImageResource(pictureID);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
