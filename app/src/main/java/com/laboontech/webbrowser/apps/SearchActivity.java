package com.laboontech.webbrowser.apps;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.laboontech.webbrowser.R;
import com.laboontech.webbrowser.adapter.AdapterTopApps;
import com.laboontech.webbrowser.adapter.SearchHistoryAdapter;
import com.laboontech.webbrowser.databinding.ActivitySearchBinding;
import com.laboontech.webbrowser.models.AppsRoot;
import com.laboontech.webbrowser.utils.SessionManager;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "searchact";
    ActivitySearchBinding binding;
    private FirebaseFirestore db;
    private List<AppsRoot> topApps = new ArrayList<>();
    private SessionManager sessonManager;
    private boolean showAds = false;
    private String adWebsite;
    private boolean ownloded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        db = FirebaseFirestore.getInstance();
        sessonManager = new SessionManager(this);
        initListnear();
        getSearchHistory();




    }


    private void getSearchHistory() {
        ArrayList<String> search;
        search = sessonManager.getSearch();
        SearchHistoryAdapter searchHistoryAdapter = new SearchHistoryAdapter(search);
        binding.rvSearchHistory.setAdapter(searchHistoryAdapter);


    }

    private void initListnear() {
        binding.tvSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//nn
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
//nn
            }
        });
        binding.tvSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String text = binding.tvSearch.getText().toString();
                sessonManager.addToSearch(text);

            }
            return true;
        });
    }

    private void getData(String toString) {

        db.collection("apps").orderBy("name").startAt("#" + toString).endAt(toString + "\uf8ff").get()
                .addOnCompleteListener(task -> {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        AppsRoot app = document.toObject(AppsRoot.class);
                        app.setId(document.getId());
                        Log.d("TAG", "onComplete:== " + app.getName());
                        topApps.add(app);
                    }

                    AdapterTopApps adapterTopApps = new AdapterTopApps(topApps, true);
                    binding.rvSearchApps.setAdapter(adapterTopApps);
                }).addOnFailureListener(e -> Log.d("TAG", "onFailure: " + e));
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
                Intent intent = new Intent(this, AppsWebsiteActivity.class);
                intent.putExtra("URL", adWebsite);
                startActivity(intent);
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
                Intent intent = new Intent(this, AppsWebsiteActivity.class);
                intent.putExtra("URL", adWebsite);
                startActivity(intent);
            });
            binding.imgCloseInter.setOnClickListener(v -> finish());
        } else {
            finish();
        }
    }



    public void onclickDelete(View view) {
        ArrayList<String> b = sessonManager.getSearch();
        Log.d(TAG, "clearBookmarks: " + b.size());
        for (int i = 0; i < b.size(); i++) {
            sessonManager.removefromSearch(b.get(i));
        }
        Log.d(TAG, "clearBookmarks:size2  " + sessonManager.getSearch().size());
        getSearchHistory();
    }
}