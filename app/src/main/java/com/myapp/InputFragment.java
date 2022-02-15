package com.myapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InputFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InputFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InputFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InputFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InputFragment newInstance(String param1, String param2) {
        InputFragment fragment = new InputFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText et_number = view.findViewById(R.id.et_number);

        //判断ボタン選択時
        Button bt_judge = view.findViewById(R.id.bt_judge);
        bt_judge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String st_number = et_number.getText().toString();
                if(!TextUtils.isEmpty(st_number)) {

                    //OutputFragmentへ入力したデータを渡し遷移
                    int number = Integer.parseInt(st_number);
                    String result = judge_number(number);

                    InputFragmentDirections.ActionInputFragmentToOutputFragment action=
                            InputFragmentDirections.actionInputFragmentToOutputFragment(result);
                    Navigation.findNavController(v).navigate(action);
                }
            }
        });
    }

    //7の倍数かで表示結果を分岐
    public String judge_number(int number) {

        String result = "";

        if(number % 7 == 0) {

            result = getActivity().getResources().getString(R.string.tv_yes);

        } else {

            result = getActivity().getResources().getString(R.string.tv_no);

        }

        return result;
    }
}