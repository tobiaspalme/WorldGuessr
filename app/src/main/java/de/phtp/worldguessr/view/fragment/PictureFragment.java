package de.phtp.worldguessr.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.Nullable;

import de.phtp.worldguessr.control.GameControl;
import de.phtp.worldguessr.databinding.FragmentPictureBinding;

public class PictureFragment extends Fragment {

    private FragmentPictureBinding binding;
    private View root;

    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPictureBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        Log.d("P1", "PictureFragment Created");

        //GameControl gameControl = GameControl.getInstance();

        imageView = binding.fragmentPictureImageView;
        //imageView.setImageResource(gameControl.getPictureId());

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
