package net.zoranpavlovic.orachat.messages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import net.zoranpavlovic.orachat.R;
import net.zoranpavlovic.orachat.core.App;
import net.zoranpavlovic.orachat.messages.create.CreateChatMessageModule;
import net.zoranpavlovic.orachat.messages.create.CreateChatMessageView;
import net.zoranpavlovic.orachat.messages.create.model.CreateChatMessageResponse;
import net.zoranpavlovic.orachat.messages.list.ListChatMessagesModule;
import net.zoranpavlovic.orachat.messages.list.ListChatMessagesPresenter;
import net.zoranpavlovic.orachat.messages.list.ListChatMessagesView;
import net.zoranpavlovic.orachat.messages.list.models.MessagesResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesActivity extends AppCompatActivity implements ListChatMessagesView, CreateChatMessageView {

    private int id;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Inject
    ListChatMessagesPresenter listMessagesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        ButterKnife.bind(this);

        getIdOfChat();

        initDagger();

        setUpToolbar();

        listMessagesPresenter.getMessagesForChat(id, 1, 50);

    }

    private void setUpToolbar() {
        toolbar.setTitle(getResources().getString(R.string.app_name));
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

    private void getIdOfChat() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            id = bundle.getInt("id");
        }
    }

    @Override
    public void onChatMessagesLoaded(MessagesResponse response) {
    }

    @Override
    public void onChatMessagesError(String error) {

    }

    @Override
    public void onChatMessageCreateSuccess(CreateChatMessageResponse body) {
        
    }

    @Override
    public void onChatMessageCreateError(String error) {

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
