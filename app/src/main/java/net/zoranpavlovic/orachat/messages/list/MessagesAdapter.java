package net.zoranpavlovic.orachat.messages.list;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.zoranpavlovic.orachat.R;
import net.zoranpavlovic.orachat.core.App;
import net.zoranpavlovic.orachat.core.Constants;
import net.zoranpavlovic.orachat.core.Utils;
import net.zoranpavlovic.orachat.messages.list.models.MessagesResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by osx on 01/08/2017.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {

    private Activity activity;
    private MessagesResponse messagesResponse;
    private int currentUserId;

    @Inject SharedPreferences sharedPreferences;

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_message_text) TextView tvMessage;
        @BindView(R.id.tv_user_and_date) TextView tvUserAndDate;
        @BindView(R.id.ll_message) LinearLayout llMessage;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public MessagesAdapter(Activity activity, MessagesResponse messagesResponse) {
        this.activity = activity;
        this.messagesResponse = messagesResponse;

        sharedPreferences = ((App) activity.getApplicationContext()).getAppComponent().getSharedPreferences();
        currentUserId = sharedPreferences.getInt(Constants.USER_ID, -1);
    }

    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_messages, parent, false);
        MessagesAdapter.ViewHolder vh = new MessagesAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MessagesAdapter.ViewHolder holder, final int i) {
      if(messagesResponse != null){
          styleMessage(holder, i);
          setData(holder, i);
      }
    }

    private void setData(ViewHolder holder, int i) {
        holder.tvMessage.setText(messagesResponse.getData().get(i).getMessage());
        String date = Utils.getDate(messagesResponse.getData().get(i).getCreatedAt());
        holder.tvUserAndDate.setText(messagesResponse.getData().get(i).getUser().getName()+" - "+
                date);
    }

    private void styleMessage(ViewHolder holder, int i) {
        if(currentUserId == messagesResponse.getData().get(i).getUserId()){
            styleRightMessage(holder);
        } else if(currentUserId != messagesResponse.getData().get(i).getId()){
            styleLeftMessage(holder);
        }
    }

    private void styleLeftMessage(ViewHolder holder) {
        holder.tvUserAndDate.setGravity(Gravity.LEFT);
        holder.llMessage.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.left_message_bg));
    }

    private void styleRightMessage(ViewHolder holder) {
        holder.tvUserAndDate.setGravity(Gravity.RIGHT);
        holder.llMessage.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.right_message_bg));
    }

    @Override
    public int getItemCount() {
        return messagesResponse.getData().size();
    }
}