package net.zoranpavlovic.orachat.chats;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.zoranpavlovic.orachat.R;
import net.zoranpavlovic.orachat.chats.create.CreateChatModule;
import net.zoranpavlovic.orachat.chats.create.CreateChatView;
import net.zoranpavlovic.orachat.chats.create.model.CreateChatResponse;
import net.zoranpavlovic.orachat.chats.list.DaggerListChatsComponent;
import net.zoranpavlovic.orachat.chats.list.ListChatsAdapter;
import net.zoranpavlovic.orachat.chats.list.ListChatsModule;
import net.zoranpavlovic.orachat.chats.list.ListChatsPresenter;
import net.zoranpavlovic.orachat.chats.list.ListChatsView;
import net.zoranpavlovic.orachat.chats.list.models.ChatsResponse;
import net.zoranpavlovic.orachat.core.App;
import net.zoranpavlovic.orachat.core.di.component.AppComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatsFragment extends Fragment implements ListChatsView, CreateChatView {

    @BindView(R.id.rv_chats) RecyclerView rvChats;

    @Inject
    ListChatsPresenter listChatsPresenter;
    private ListChatsAdapter adapter;

    public ChatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_chats, container, false);

        ButterKnife.bind(this, v);

        DaggerListChatsComponent.builder()
                .appComponent(((App) getActivity().getApplicationContext()).getAppComponent())
                .listChatsModule(new ListChatsModule(this))
                .createChatModule(new CreateChatModule(this))
                .build()
                .inject(this);

        listChatsPresenter.getChats("null", 1, 50);

        return v;
    }

    @OnClick(R.id.fab_add_chats)
    void onAddChatClick(){

    }

    @Override
    public void onChatsLoaded(ChatsResponse chatsResponse) {
        Log.d("TAG", chatsResponse.toString());
        adapter = new ListChatsAdapter(getActivity(), chatsResponse);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvChats.setLayoutManager(linearLayoutManager);
        rvChats.setAdapter(adapter);
    }

    @Override
    public void onChatsError(String error) {

    }

    @Override
    public void onChatCreateSuccess(CreateChatResponse createChatResponse) {

    }

    @Override
    public void onChatCreateError(String error) {

    }
}
