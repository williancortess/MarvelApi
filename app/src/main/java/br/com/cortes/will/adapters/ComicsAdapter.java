package br.com.cortes.will.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.cortes.will.R;
import br.com.cortes.will.models.ComicSummary;

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.LineHolder> {
    private List<ComicSummary> mComicSummary;

    public ComicsAdapter(List<ComicSummary> characters) {
        mComicSummary = characters;
    }

    @Override
    public LineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_comics_adapter_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LineHolder holder, final int position) {
        ComicSummary comicSummary = mComicSummary.get(position);
        holder.mTitle.setText(comicSummary.getName());
    }

    @Override
    public int getItemCount() {
        return mComicSummary != null ? mComicSummary.size() : 0;
    }

    class LineHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;

        LineHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.list_comics_name);
        }
    }
}
