package com.example.lap60020_local.finalproject.Ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lap60020_local.finalproject.ModelData.Entity.Genre;
import com.example.lap60020_local.finalproject.ModelData.retrofit.MyApiClient;
import com.example.lap60020_local.finalproject.MyApplication;
import com.example.lap60020_local.finalproject.R;
import com.example.lap60020_local.finalproject.Ui.Adapter.GenrelistAdapter;
import com.example.lap60020_local.finalproject.ViewModel.GenreViewModel;
import com.example.lap60020_local.finalproject.ViewModel.MoviesViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ImageView mAccountImage;
    private Context context;

    @Nullable
    @BindView(R.id.main_activity_progressBar)
    ProgressBar progressBar;
    @Nullable
    @BindView(R.id.main_activity_toolbar)
    Toolbar mToolbar;
    @Nullable
    @BindView(R.id.main_activity_genres)
    RecyclerView mGenreList;
    @BindView(R.id.main_activity_tab)
    @Nullable
    TabLayout mTab;
    @Nullable
    @BindView(R.id.main_activity_pager)
    ViewPager mViewPager;

    private GenreViewModel genreViewModel;
    private CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        genreViewModel = ((MyApplication) getApplication()).getGenreViewModel();
        context = this;
        setSupportActionBar(mToolbar);
        MyPageAdapter adapter = new MyPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTab.setupWithViewPager(mViewPager);
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bind();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unBind();
    }

    public void bind() {
        progressBar.setVisibility(View.VISIBLE);
        disposable.add(genreViewModel.getListGenre()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new GenreObserver()));
    }

    public void unBind() {
        disposable.clear();
    }

    class GenreObserver extends DisposableObserver<List<Genre>> {

        @Override
        public void onNext(List<Genre> genres) {
            GenrelistAdapter genrelistAdapter = new GenrelistAdapter(genres,context);
            mGenreList.setAdapter(genrelistAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL,
                    false);
            mGenreList.setLayoutManager(linearLayoutManager);
            genrelistAdapter.notifyDataSetChanged();
        }

        @Override
        public void onError(Throwable e) {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplete() {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem accountItem = menu.findItem(R.id.main_menu_account);
        mAccountImage = (ImageView) accountItem.getActionView();
        mAccountImage.setOnClickListener(v->{
            Intent intent;
            if(((MyApplication) getApplication()).getSessionID().isEmpty()){
                intent = new Intent(this, LoginActivity.class);
            } else {
                intent = new Intent(this, Account_Activity.class);
            }
            startActivity(intent);
        });
        if (((MyApplication) getApplication()).getSessionID().isEmpty()) {
            mAccountImage.setImageResource(R.drawable.usernotlogin);
        }
        else {
            mAccountImage.setImageResource(R.drawable.userlogined);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.main_menu_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyPageAdapter extends FragmentPagerAdapter{

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            Drawable mDrawable = null;
            String title = null;
            switch (position) {
                case 0:
                    mDrawable = getDrawable(R.drawable.popular);
                    title = getString(R.string.popular_tab);
                    break;
                case 1:
                    mDrawable = getDrawable(R.drawable.toprated);
                    title = getString(R.string.toprated_tab);
                    break;
            }
            SpannableStringBuilder sb = new SpannableStringBuilder("   " + title);
            // space added before text for convenience
            try {
                mDrawable.setBounds(5, 5,
                        mDrawable.getIntrinsicWidth(),
                        mDrawable.getIntrinsicHeight());
                ImageSpan span = new ImageSpan(mDrawable,
                        DynamicDrawableSpan.ALIGN_BASELINE);
                sb.setSpan(span, 0, 1,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } catch (Exception e) {
                Log.e("Main Activity", "create icon for page Adapter Error");
            }
            return sb;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new PopularFragment();
                case 1:
                    return new TopratedFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
