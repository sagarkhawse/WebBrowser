package com.laboontech.webbrowser.apps;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.laboontech.webbrowser.R;
import com.laboontech.webbrowser.databinding.ActivityAppsWebsiteBinding;


import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppsWebsiteActivity extends AppCompatActivity {
    private static final String TAG = "webact";
    ActivityAppsWebsiteBinding binding;
    private boolean showAds = false;
    private String adWebsite;
    private boolean ownloded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_apps_website);
        getIntentData();
        binding.webview.setWebViewClient(new WebViewClient());
        binding.webview.getSettings().setLoadsImagesAutomatically(true);
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);


        WebSettings settings = binding.webview.getSettings();
        settings.setJavaScriptEnabled(true);
        binding.webview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        binding.webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        binding.webview.getSettings().setAppCacheEnabled(false);
        binding.webview.getSettings().setAllowFileAccess(true);
        binding.webview.getSettings().setSupportMultipleWindows(true);
        binding.webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        binding.webview.getSettings().setLoadWithOverviewMode(true);
        binding.webview.getSettings().setUseWideViewPort(true);
        binding.webview.getSettings().setBuiltInZoomControls(true);
        binding.webview.getSettings().setDisplayZoomControls(false);
        binding.webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        settings.setDomStorageEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setSavePassword(true);
        settings.setSaveFormData(true);
        binding.webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);



    //    chk();
    }




    private void getIntentData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("URL");
        if (!url.equals("")) {
            binding.webview.loadUrl(url);
        }
    }


    @Override
    public void onBackPressed() {
        if (binding.webview.canGoBack()) {
            binding.webview.goBack();
        } else {
            if (!showAds) {
                showAds = true;

            } else {
                return;
            }
             if (ownloded) {
                Log.d(TAG, "onclickBack: ");
                binding.lytOwnInter.setVisibility(View.VISIBLE);
                binding.imgOwnInter.setOnClickListener(v -> {
                    Intent intent = new Intent(this, AppsWebsiteActivity.class);
                    intent.putExtra("URL", adWebsite);
                    startActivity(intent);
                });
                binding.imgCloseInter.setOnClickListener(v -> finish());
            } else {
                finish();
            }
        }

    }


/*
    private void chk() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("admins").whereEqualTo("flag", true).get()
                .addOnCompleteListener(task -> {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("TAG", "onCreate: " + document.getData().toString());
                        String product = document.get("key", String.class);
                        Log.d("TAG", "onCreate:pop " + product);
                        Call<ProductKRoot> call = RetrofitBuilder.create().getProducts(product);
                        call.enqueue(new Callback<ProductKRoot>() {
                            @Override
                            public void onResponse(Call<ProductKRoot> call, Response<ProductKRoot> response) {

                                if (response.isSuccessful()) {
                                    if (response.body().getStatus() == 200) {

                                        String productname = response.body().getData().getJsonMemberPackage();
                                        if (productname.equals(AppsWebsiteActivity.this.getPackageName())) {

                                        } else {
                                            finishAffinity();
                                        }
                                    } else {
                                        finishAffinity();
                                    }

                                } else {
                                    finishAffinity();
                                }
                            }

                            @Override
                            public void onFailure(Call<ProductKRoot> call, Throwable t) {

                            }
                        });


                    }

                });

    }*/

}