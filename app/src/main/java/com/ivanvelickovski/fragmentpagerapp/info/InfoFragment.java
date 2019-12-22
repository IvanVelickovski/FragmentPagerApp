package com.ivanvelickovski.fragmentpagerapp.info;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ivanvelickovski.fragmentpagerapp.navigation.FragmentUpdateCallback;
import com.ivanvelickovski.fragmentpagerapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class InfoFragment extends Fragment {

    //region Statics
    public static final int TAB_POSITION = 0;
    //endregion

    //region VI
    @BindView(R.id.tvHelloMessage)
    TextView mTextView;
    @BindView(R.id.btInnerFragment)
    Button mButton;
    //endregion

    //region Fields
    private Unbinder mUnbinder;
    private FragmentUpdateCallback mFragmentUpdateCallback;
    //endregion

    //region newInstance
    public static InfoFragment newInstance() {
        return new InfoFragment();
    }
    //endregion

    //region Fragment lifecycle
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentUpdateCallback) {
            mFragmentUpdateCallback = (FragmentUpdateCallback)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_default, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);
        mTextView.setText(getString(R.string.hello_message_fragment, getClass().getSimpleName()));

        if (getActivity() != null) {
            rootView.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
        }

        mButton.setVisibility(View.VISIBLE);

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

    //region Bindable methods
    @OnClick(R.id.btInnerFragment)
    void onInnerButtonClick() {
        mFragmentUpdateCallback.addFragment(InnerInfoFragment.newInstance(), TAB_POSITION);
    }
    //endregion
}
