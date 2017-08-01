package net.zoranpavlovic.orachat.messages.list;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.zoranpavlovic.orachat.R;
import net.zoranpavlovic.orachat.chats.list.models.ChatsResponse;
import net.zoranpavlovic.orachat.chats.list.models.Datum;
import net.zoranpavlovic.orachat.core.Utils;
import net.zoranpavlovic.orachat.messages.MessagesActivity;
import net.zoranpavlovic.orachat.messages.list.models.MessagesResponse;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by osx on 01/08/2017.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private Activity activity;
    private MessagesResponse messagesResponse;


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_message_text) TextView tvMessage;
        @BindView(R.id.tv_user_and_date) TextView tvUserAndDate;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public MessagesAdapter(Activity activity, MessagesResponse messagesResponse) {
        this.activity = activity;
        this.messagesResponse = messagesResponse;
    }

    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_left_item, parent, false);
        MessagesAdapter.ViewHolder vh = new MessagesAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MessagesAdapter.ViewHolder holder, final int i) {
      if(messagesResponse != null){
          holder.tvMessage.setText(messagesResponse.getData().get(i).getMessage());
          String date = Utils.getDate(messagesResponse.getData().get(i).getCreatedAt());
          holder.tvUserAndDate.setText(messagesResponse.getData().get(i).getUser().getName()+" - "+
          date);
      }
    }

    @Override
    public int getItemCount() {
        return messagesResponse.getData().size();
    }
}