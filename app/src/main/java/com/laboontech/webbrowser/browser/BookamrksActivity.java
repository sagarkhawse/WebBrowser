package com.laboontech.webbrowser.browser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.laboontech.webbrowser.R;
import com.laboontech.webbrowser.databinding.ActivityBookamrksBinding;
import com.laboontech.webbrowser.utils.SessionManager;


import java.util.ArrayList;
import java.util.Random;

public class BookamrksActivity extends AppCompatActivity implements BookamrksAdapter.OnBookmarkClickListner {

    private static final String TAG = "bookmarkact";
    public static final int BOOKMAR_ACTIVITY_REQUEST_CODE = 1002;
    ActivityBookamrksBinding binding;
    private SessionManager sessonManager;
    private ArrayList<String> list = new ArrayList<>();
    private boolean showAds = false;
    private String adWebsite;
    private boolean ownloded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bookamrks);
        sessonManager = new SessionManager(this);
        initView();
        initListnear();

    }



    private void initListnear() {
        binding.tvClear.setOnClickListener(v -> clearBookmarks());
    }

    private void clearBookmarks() {
        ArrayList<String> b = sessonManager.getBookamrks();
        for (int i = 0; i <= b.size() - 1; i++) {
            sessonManager.toggleBookmark(b.get(i));
        }
        initView();
    }

    private void initView() {
        list.clear();
        list = sessonManager.getBookamrks();
        if (!list.isEmpty()) {
            BookamrksAdapter bookamrksAdapter = new BookamrksAdapter(list, this);
            binding.rvBookamrk.setAdapter(bookamrksAdapter);
        } else {
            Toast.makeText(this, "Bookmarks not found", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBookmarkClick(String url, String work) {
        if (work.equals("DELETE")) {
            int i = sessonManager.toggleBookmark(url);
            if (i == 0) {
                Toast.makeText(this, "Bookmark Removed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Bookmarked", Toast.LENGTH_SHORT).show();
            }
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
               /* Intent intent = new Intent(this, AppsWebsiteActivity.class);
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
               /* Intent intent = new Intent(this, AppsWebsiteActivity.class);
                intent.putExtra("URL", adWebsite);
                startActivity(intent);*/
            });
            binding.imgCloseInter.setOnClickListener(v -> finish());
        } else {
            finish();
        }
    }

}