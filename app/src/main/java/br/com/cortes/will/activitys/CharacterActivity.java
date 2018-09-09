package br.com.cortes.will.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import br.com.cortes.will.R;
import br.com.cortes.will.adapters.ComicsAdapter;
import br.com.cortes.will.models.Character;
import br.com.cortes.will.models.ComicSummary;

public class CharacterActivity extends AppCompatActivity {
    private ImageView characterImage;
    private TextView characterName;
    private List<ComicSummary> mComicSummary;
    private RecyclerView mRecyclerView;
    private ComicsAdapter mAdapter;

    private ActionBar actionBar = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        characterImage = findViewById(R.id.character_details_image);
        characterName = findViewById(R.id.character_details_name);
        mRecyclerView = findViewById(R.id.character_rvComics);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Character character = (Character) extras.getSerializable(MainActivity.CHARACTER);
            createCharacterDetails(character);
            if (character != null) {
                mComicSummary = character.getComics().getItems();
            }
        }

        setupRecycler();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupRecycler() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new ComicsAdapter(mComicSummary);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });

    }

    private void createCharacterDetails(Character character) {
        if (character != null) {
            try {
                characterName.setText(character.getName());
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }

            new DownloadImageTask(characterImage).execute(character.getThumbnail().getUrl());
        }
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView mImage;

        public DownloadImageTask(ImageView bmImage) {
            this.mImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String mUrldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(mUrldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            mImage.setImageBitmap(result);
        }
    }
}
