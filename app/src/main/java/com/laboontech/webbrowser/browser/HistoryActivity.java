package com.laboontech.webbrowser.browser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;

import com.laboontech.webbrowser.R;
import com.laboontech.webbrowser.databinding.ActivityHistoryBinding;
import com.laboontech.webbrowser.utils.SessionManager;

import java.util.ArrayList;
import java.util.Random;

public class HistoryActivity extends AppCompatActivity implements HistoryAdapter.OnHistoryClickListnear {
    private static final String TAG = "historyact";
    public static final int HISTORY_ACTIVITY_CODE = 1001;
    ActivityHistoryBinding binding;
    private SessionManager sessonManager;
    private ArrayList<String> list = new ArrayList<>();
    private boolean showAds = false;
    private String adWebsite;
    private boolean ownloded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history);
        sessonManager = new SessionManager(this);
        initView();
        initListnear();
    }


    private void initListnear() {
        binding.tvClear.setOnClickListener(v -> clearBookmarks());
    }

    private void clearBookmarks() {
        ArrayList<String> b = sessonManager.getHistory();
        Log.d(TAG, "clearBookmarks: " + b.size());
        for (int i = 0; i < b.size(); i++) {
            sessonManager.removefromHistory(b.get(i));
        }
        Log.d(TAG, "clearBookmarks:size2  " + sessonManager.getHistory().size());
        initView();
    }

    private void initView() {
        list.clear();
        list = sessonManager.getHistory();
        Log.d(TAG, "clearBookmarks:size3  " + sessonManager.getHistory().size());
        Log.d(TAG, "clearBookmarks:size34  " + list.size());


        HistoryAdapter historyAdapter = new HistoryAdapter(list, this);
        binding.rvHistory.setAdapter(historyAdapter);


    }

    @Override
    public void onHistoryClick(String url, String work) {
        if (work.equals("DELETE")) {
            sessonManager.removefromHistory(url);
            initView();
        }
        if (work.equals("OPEN")) {
            Intent intent = new Intent();
            intent.putExtra("url", url);

            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void onclickBack(View view) {
        if (!showAds) {
            showAds = true;

        } else {
            return;
        }
       if (ownloded) {
            Log.d(TAG, "onclickBack: ");
            binding.lytOwnInter.setVisibility(View.VISIBLE);
            binding.imgOwnInter.setOnClickListener(v -> {
              /*  Intent intent = new Intent(this, AppsWebsiteActivity.class);
                intent.putExtra("URL", adWebsite);
                startActivity(intent);*/
            });
            binding.imgCloseInter.setOnClickListener(v -> finish());
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (!showAds) {
            showAds = true;

        } else {
            return;
        }
     if (ownloded) {
            Log.d(TAG, "onclickBack: ");
            binding.lytOwnInter.setVisibility(View.VISIBLE);
            binding.imgOwnInter.setOnClickListener(v -> {
          /*      Intent intent = new Intent(this, AppsWebsiteActivity.class);
                intent.putExtra("URL", adWebsite);
                startActivity(intent);*/
            });
            binding.imgCloseInter.setOnClickListener(v -> finish());
        } else {
            finish();
        }
    }


}