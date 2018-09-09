package br.com.cortes.will.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import br.com.cortes.will.R;
import br.com.cortes.will.activitys.CharacterActivity;
import br.com.cortes.will.activitys.MainActivity;
import br.com.cortes.will.models.Character;


public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.LineHolder> {
    private List<Character> mCharacters;
    private Context mContext;

    public CharactersAdapter(Context context, List<Character> characters) {
        mContext = context;
        mCharacters = characters;
    }

    @Override
    public LineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_characters_adapter_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LineHolder holder, final int position) {
        Character character = mCharacters.get(position);
        holder.mTitle.setText(character.getName());
        holder.mMoreButton.setOnClickListener(view -> {
            showMore(character);
        });
    }

    private void showMore(Character character) {
        Intent intent = new Intent(mContext, CharacterActivity.class);
        intent.putExtra(MainActivity.CHARACTER, character);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mCharacters != null ? mCharacters.size() : 0;
    }

    public void updateList(List<Character> characters) {
        int size = getItemCount();
        mCharacters.addAll(characters);
        notifyItemRangeInserted(size, getItemCount());
    }

    class LineHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private ImageButton mMoreButton;

        LineHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.list_characters_name);
            mMoreButton = itemView.findViewById(R.id.list_characters_details);
        }
    }
}
