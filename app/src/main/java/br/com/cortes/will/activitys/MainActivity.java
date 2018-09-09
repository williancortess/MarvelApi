package br.com.cortes.will.activitys;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.cortes.will.R;
import br.com.cortes.will.adapters.CharactersAdapter;
import br.com.cortes.will.config.RetrofitConfig;
import br.com.cortes.will.models.Character;
import br.com.cortes.will.models.DataWrapper;
import br.com.cortes.will.request.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String CHARACTER = "CHARACTER";

    private int mLimit = 20;

    private RecyclerView mRecyclerView;
    private CharactersAdapter mAdapter;

    private List<Character> mCharacters;
    private boolean mLoading;
    private int mPastVisibleItems;
    private int mVisibleItemCount;
    private int mTotalItemCount;
    private int mOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.Main_rvCharacters);

        init();
        setupRecycler();
        start();
    }

    private void init() {
        mCharacters = new ArrayList<>();
        mLoading = true;
        mOffset = 0;
    }

    private void setupRecycler() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new CharactersAdapter(MainActivity.this, mCharacters);
        mRecyclerView.setAdapter(mAdapter);

        // Divider Decoration
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //check for scroll down
                if (dy > 0) {
                    mVisibleItemCount = layoutManager.getChildCount();
                    mTotalItemCount = layoutManager.getItemCount();
                    mPastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (mLoading) {
                        if ((mVisibleItemCount + mPastVisibleItems) >= mTotalItemCount) {
                            mLoading = false;
                            start();
                        }
                    }
                }
            }
        });

    }

    public void start() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.msg_loading));
        progressDialog.show();

        Request request = new Request();
        request.setLimit(mLimit);
        request.setOffset(mOffset);

        Call<DataWrapper<Character>> call = new RetrofitConfig().getCharactersService().listCharacters(
                request.getLimit(),
                request.getOffset(),
                request.getTs(),
                request.getPublicKey(),
                request.getHash(),
                request.getName());
        call.enqueue(new Callback<DataWrapper<Character>>() {
            @Override
            public void onResponse(Call<DataWrapper<Character>> call, Response<DataWrapper<Character>> response) {
                DataWrapper<Character> result = response.body();
                mCharacters = result.getData().getResults();
                mAdapter.updateList(mCharacters);
                mOffset = mOffset + mLimit;
                mLoading = true;
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<DataWrapper<Character>> call, Throwable t) {
                msgFailure();
                progressDialog.dismiss();

            }
        });
    }

    private void msgFailure() {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, getResources().getString(R.string.erro_conexao), Snackbar.LENGTH_LONG).show();
    }
}