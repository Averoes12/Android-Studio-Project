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
public class HomeFragment extends Fragment implements View.OnClickListener{


    public HomeFragment() {
        // Required empty public constructor
    }

    //Pada HomeFragment.java terdapat metode onCreateView() di mana layout interface didefinisikan dan ditransformasi dari layout berupa file xml kedalam obyek view dengan menggunakan metode inflate().


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Inflater.inflate() merupakan obyek dari LayoutInflater yang berfungsi untuk mengubah layout xml ke dalam bentuk obyek viewgroup atau widget melalui pemanggilan metode inflate().
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    //Pada HomeFragment.java terdapat juga metode onViewCreated()  yang akan bekerja setelah metode onCreateView()
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnCategory = view.findViewById(R.id.btn_category);
        btnCategory.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_category){
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null){
                FragmentCategory fragmentCategory = new FragmentCategory();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//method replace() akan mengganti obyek fragment yang sedang tampil saat ini, HomeFragment dengan obyek fragment yang baru, CategoryFragment.
                fragmentTransaction.replace(R.id.frame_container, fragmentCategory, FragmentCategory.class.getSimpleName());
                fragmentTransaction.addToBackStack(null);//agar saat di tekan tombol back tidak keluar dari aplikasi tapi akan menampilkan fragment pertama.
                fragmentTransaction.commit();
            }
        }

    }
}
