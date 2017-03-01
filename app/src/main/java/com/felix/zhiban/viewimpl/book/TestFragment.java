package com.felix.zhiban.viewimpl.book;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.felix.zhiban.R;
import com.felix.zhiban.base.BaseFragment;

public class TestFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.test_fragment,container,false);
        return view;
    }
}
