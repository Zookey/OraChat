package net.zoranpavlovic.orachat.messages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import net.zoranpavlovic.orachat.R;
import net.zoranpavlovic.orachat.core.App;
import net.zoranpavlovic.orachat.core.Constants;
import net.zoranpavlovic.orachat.messages.create.CreateChatMessageModule;
import net.zoranpavlovic.orachat.messages.create.CreateChatMessagePresenter;
import net.zoranpavlovic.orachat.messages.create.CreateChatMessageView;
import net.zoranpavlovic.orachat.messages.create.model.CreateChatMessageResponse;
import net.zoranpavlovic.orachat.messages.list.ListChatMessagesModule;
import net.zoranpavlovic.orachat.messages.list.ListChatMessagesPresenter;
import net.zoranpavlovic.orachat.messages.list.ListChatMessagesView;
import net.zoranpavlovic.orachat.messages.list.MessagesAdapter;
import net.zoranpavlovic.orachat.messages.list.models.MessagesResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessagesActivity extends AppCompatActivity implements ListChatMessagesView, CreateChatMessageView {

    private int id;
    private String title;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rv_messages) RecyclerView rvMessages;
    @BindView(R.id.et_message) EditText etMessage;

    @Inject ListChatMessagesPresenter listMessagesPresenter;
    @Inject CreateChatMessagePresenter createChatMessagePresenter;

    private MessagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        ButterKnife.bind(this);

        getChatDataFromBundle();

        initDagger();

        setUpToolbar();

        listMessagesPresenter.getMessagesForChat(id, 1, 50);

    }

    private void setUpToolbar() {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initDagger() {
        DaggerMessagesComponent.builder()
                .appComponent(((App) this.getApplicationContext()).getAppComponent())
                .listChatMessagesModule(new ListChatMessagesModule(this))
                .createChatMessageModule(new CreateChatMessageModule(this))
                .build()
                .inject(this);
    }

    private void getChatDataFromBundle() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            id = bundle.getInt(Constants.CHAT_ID);
            title = bundle.getString(Constants.CHAT_NAME);
        }
    }

    @OnClick(R.id.btn_message_send)
    void sendMessageClick(){
        if(!getMessage().isEmpty()){
            createChatMessagePresenter.createMessage(id, getMessage());
            etMessage.setText("");
        } else{
            Toast.makeText(this, R.string.empty_message, Toast.LENGTH_SHORT).show();
        }
    }

    private String getMessage(){
        return etMessage.getText().toString();
    }

    @Override
    public void onChatMessagesLoaded(MessagesResponse response) {
        adapter = new MessagesAdapter(this, response);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvMessages.setLayoutManager(linearLayoutManager);
        rvMessages.setAdapter(adapter);
    }

    @Override
    public void onChatMessagesError(String error) {

    }

    @Override
    public void onChatMessageCreateSuccess(CreateChatMessageResponse body) {
        Toast.makeText(this, "Message sent!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChatMessageCreateError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
