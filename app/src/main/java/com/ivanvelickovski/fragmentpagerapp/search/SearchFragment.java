package com.ivanvelickovski.fragmentpagerapp.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ivanvelickovski.fragmentpagerapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SearchFragment extends Fragment {

    //region Statics
    public static final int TAB_POSITION = 1;
    //endregion

    //region VI
    @BindView(R.id.tvHelloMessage)
    TextView mTextView;
    //endregion

    //region Fields
    private Unbinder mUnbinder;
    //endregion

    //region newInstance
    public static Fragment newInstance() {
        return new SearchFragment();
    }
    //endregion

    //region Fragment lifecycle
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_default, container, false);
        
        mUnbinder = ButterKnife.bind(this, rootView);
        mTextView.setText(getString(R.string.hello_message_fragment, getClass().getSimpleName()));

        if (getActivity() != null) {
            rootView.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
    //endregion
}
