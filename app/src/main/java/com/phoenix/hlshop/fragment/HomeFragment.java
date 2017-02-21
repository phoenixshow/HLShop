package com.phoenix.hlshop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phoenix.hlshop.R;
import com.phoenix.hlshop.TestActivity;
import com.phoenix.hlshop.adapter.HomeCategoryAdapter;
import com.phoenix.hlshop.adapter.decoration.DividerItemDecoration;
import com.phoenix.hlshop.entity.Banner;
import com.phoenix.hlshop.entity.Campaign;
import com.phoenix.hlshop.entity.HomeCampaign;
import com.phoenix.hlshop.entity.HomeCategory;
import com.phoenix.hlshop.http.BaseCallBack;
import com.phoenix.hlshop.http.Contants;
import com.phoenix.hlshop.http.OkHttpHelper;
import com.phoenix.hlshop.http.SpotsCallBack;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by flashing on 2017/1/12.
 */
public class HomeFragment extends Fragment {
    private SliderLayout mSliderLayout;
    private PagerIndicator mIndicator;
    private RecyclerView mRecyclerView;
    private HomeCategoryAdapter mAdapter;
    private Gson mGson = new Gson();
    private List<Banner> mBanners;
    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mSliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        mIndicator = (PagerIndicator) view.findViewById(R.id.custom_indicator);

        requestImages();
        initRecyclerView(view);
        return view;
    }

    private void requestImages(){
        String url = Contants.API.BANNER+"?type=1";

        okHttpHelper.get(url, new SpotsCallBack<List<Banner>>(getContext()) {
            @Override
            public void onSuccess(Response response, List<Banner> banners) {
                mBanners = banners;
                initSlider();
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    private void initRecyclerView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_rv);
        okHttpHelper.get(Contants.API.CAMPAIGN_HOME,
                new BaseCallBack<List<HomeCampaign>>() {
            @Override
            public void onRequestBefore(Request request) {
            }

            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onSuccess(Response response, List<HomeCampaign> homeCampaigns) {
                initData(homeCampaigns);
            }

            @Override
            public void onError(Response response, int code, Exception e) {
            }

            @Override
            public void onResponse(Response response) {
            }
        });
    }

    private void initData(List<HomeCampaign> homeCampaigns) {
        mAdapter = new HomeCategoryAdapter(homeCampaigns, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                this.getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration());
        mAdapter.setOnCampaignClickListener(new HomeCategoryAdapter.OnCampaignClickListener() {
            @Override
            public void onClick(View view, Campaign campaign) {
                Toast.makeText(getContext(), "title="+campaign.getTitle(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initSlider(){
        if (mBanners != null){
            for (Banner banner : mBanners){
                TextSliderView textSliderView = new TextSliderView(getActivity());
                textSliderView
                        .description(banner.getDescription())
                        .image(banner.getImgUrl())
                        .setScaleType(BaseSliderView.ScaleType.Fit);
                mSliderLayout.addSlider(textSliderView);
            }
        }

//        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//设置默认指示器
        mSliderLayout.setCustomIndicator(mIndicator);//设置自定义指示器
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());//设置自定义动画
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);//设置转场效果
        mSliderLayout.setDuration(3000);
    }

    @Override
    public void onStart() {
        mSliderLayout.startAutoCycle();
        super.onStart();
    }

    @Override
    public void onStop() {
        mSliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            mSliderLayout.stopAutoCycle();
        } else {
            mSliderLayout.startAutoCycle();
        }
        super.onHiddenChanged(hidden);
    }
}
