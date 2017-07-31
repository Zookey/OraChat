package net.zoranpavlovic.orachat.chats.list;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.zoranpavlovic.orachat.R;
import net.zoranpavlovic.orachat.chats.list.models.ChatsResponse;
import net.zoranpavlovic.orachat.chats.list.models.Datum;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by osx on 31/07/2017.
 */

public class ListChatsAdapter extends RecyclerView.Adapter<ListChatsAdapter.ViewHolder> {

    private Activity activity;
    private ChatsResponse chatsResponse;


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_chat_name) TextView tvChatName;
        @BindView(R.id.tv_last_message) TextView tvLastChatMessage;
        @BindView(R.id.tv_date) TextView tvDate;
        @BindView(R.id.tv_title) TextView tvTitle;
        @BindView(R.id.rl_chats_root) RelativeLayout rlRoot;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public ListChatsAdapter(Activity activity, ChatsResponse chatsResponse) {
        this.activity = activity;
        this.chatsResponse = chatsResponse;
    }

    @Override
    public ListChatsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chats, parent, false);
        ListChatsAdapter.ViewHolder vh = new ListChatsAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ListChatsAdapter.ViewHolder holder, final int i) {
        if (chatsResponse != null) {
            Datum datum = chatsResponse.getData().get(i);
            String name = datum.getName();
            String lastMessage = datum.getLastChatMessage().getMessage();
            String createdAt = datum.getLastChatMessage().getCreatedAt();
            String user = datum.getUsers().get(0).getName();

            holder.tvDate.setText(createdAt);
            holder.tvChatName.setText(name);
            holder.tvLastChatMessage.setText(lastMessage);
            holder.tvTitle.setText(user+" "+createdAt);
        }
    }

    @Override
    public int getItemCount() {
        return chatsResponse.getData().size();
    }
}