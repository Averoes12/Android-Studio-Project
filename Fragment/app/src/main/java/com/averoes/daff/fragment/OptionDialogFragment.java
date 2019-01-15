package com.averoes.daff.fragment;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionDialogFragment extends DialogFragment implements  View.OnClickListener{


    Button close, choose;
    RadioGroup choice;
    RadioButton bb, yk, tb, yo;
    OnOptionDialogListener optionDialogListener;


    public OptionDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        choose = view.findViewById(R.id.choose);
        close = view.findViewById(R.id.close);
        choice = view.findViewById(R.id.choice);
        bb = view.findViewById(R.id.bb);
        tb = view.findViewById(R.id.tb);
        yk = view.findViewById(R.id.yk);
        yo = view.findViewById(R.id.yo);

        choose.setOnClickListener(this);
        close.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context){

        super.onAttach(context);
            Fragment fragment = getParentFragment();

            if (fragment instanceof DetailCategoryFragment){

                DetailCategoryFragment detailCategoryFragment = (DetailCategoryFragment) fragment;
                this.optionDialogListener = detailCategoryFragment.optionDialogListener;
            }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.optionDialogListener = null;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                getDialog().cancel();
                break;
            case R.id.choose:
                int checkedRadioButtonId = choice.getCheckedRadioButtonId();
                if (checkedRadioButtonId != -1) {
                    String food = null;
                    switch (checkedRadioButtonId) {
                        case R.id.bb:
                            food = bb.getText().toString().trim();
                            break;
                        case R.id.tb:
                            food = tb.getText().toString().trim();
                            break;
                        case R.id.yk:
                            food = yk.getText().toString().trim();
                            break;
                        case R.id.yo:
                            food = yo.getText().toString().trim();
                            break;
                    }
                    if (optionDialogListener != null) {
                        optionDialogListener.onOptionChoose(food);
                    }
                    getDialog().dismiss();
                }
                break;
        }
    }

    public interface OnOptionDialogListener {
        void onOptionChoose(String text);
    }
}


