package com.haishan.saleoa.GoodsSubFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haishan.saleoa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddGoodsFragment extends Fragment {


    public AddGoodsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_goodsfragment, container, false);
    }

}
