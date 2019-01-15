package com.averoes.daff.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCategory extends Fragment implements View.OnClickListener{


    public FragmentCategory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);
        Button lifeStyle = view.findViewById(R.id.btn_lifeStyle);
        lifeStyle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_lifeStyle){

            DetailCategoryFragment detailCategoryFragment = new DetailCategoryFragment();

            Bundle bundle = new Bundle();//kita menggunakan obyek Bundle untuk mengirimkan data antar fragment
            //Kelas Bundle merupakan kelas map data string untuk obyek-obyek parcelable. Di sini kita bisa menginputkan lebih dari satu parameter/variabel ke dalam obyek Bundle.

            bundle.putString(DetailCategoryFragment.EXTRA_NAME, "Lifestyle");
            String descrip = "Kategori ini berisi produk lifestyle";

            detailCategoryFragment.setArguments(bundle);
            detailCategoryFragment.setDescription(descrip);


            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null){

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, detailCategoryFragment, DetailCategoryFragment.class.getSimpleName());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }

    }
}
