package com.example.levi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView user = view.findViewById(R.id.txtUser);
        TextView journey = view.findViewById(R.id.txtJourney);
        TextView begin = view.findViewById(R.id.txtBegin);
        TextView suggestions = view.findViewById(R.id.txtSuggestions);
        ImageView bcn = view.findViewById(R.id.imgBcn);
        ImageView bcn2 = view.findViewById(R.id.imgBcn2);
        ImageView bcn3 = view.findViewById(R.id.imgBcn3);
        TextView covid = view.findViewById(R.id.txtCovid);
        TextView restrictions = view.findViewById(R.id.txtRestrictions);
        TextView low = view.findViewById(R.id.txtLow);
        TextView moderate = view.findViewById(R.id.txtModerate);
        TextView unknown = view.findViewById(R.id.txtUnknown);
        ImageView green = view.findViewById(R.id.imgGreen);
        ImageView pink = view.findViewById(R.id.imgPink);
        ImageView yellow = view.findViewById(R.id.imgYellow);
        ImageView grey = view.findViewById(R.id.imgGrey);
        Button moreInfo = view.findViewById(R.id.btnMoreInfo);

        return view;

    }
}