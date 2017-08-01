package net.zoranpavlovic.orachat.chats;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.zoranpavlovic.orachat.R;
import net.zoranpavlovic.orachat.chats.create.CreateChatModule;
import net.zoranpavlovic.orachat.chats.create.CreateChatPresenter;
import net.zoranpavlovic.orachat.chats.create.CreateChatView;
import net.zoranpavlovic.orachat.chats.create.model.CreateChatResponse;
import net.zoranpavlovic.orachat.chats.list.DaggerListChatsComponent;
import net.zoranpavlovic.orachat.chats.list.ListChatsAdapter;
import net.zoranpavlovic.orachat.chats.list.ListChatsModule;
import net.zoranpavlovic.orachat.chats.list.ListChatsPresenter;
import net.zoranpavlovic.orachat.chats.list.ListChatsView;
import net.zoranpavlovic.orachat.chats.list.models.ChatsResponse;
import net.zoranpavlovic.orachat.core.App;
import net.zoranpavlovic.orachat.core.EndlessRecyclerViewScrollListener;
import net.zoranpavlovic.orachat.core.di.component.AppComponent;
import net.zoranpavlovic.orachat.messages.list.models.MessagesResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.id;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatsFragment extends Fragment implements ListChatsView, CreateChatView {

    @BindView(R.id.rv_chats) RecyclerView rvChats;

    @Inject ListChatsPresenter listChatsPresenter;
    private ListChatsAdapter adapter;

    @Inject CreateChatPresenter createChatPresenter;
    private EndlessRecyclerViewScrollListener scrollListener;

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

        listChatsPresenter.getChats("", 1, 50);

        return v;
    }

    @OnClick(R.id.fab_add_chats)
    void onAddChatClick(){
        createChatPresenter.createChat("test chat", "Test message");
    }

    @Override
    public void onChatsLoaded(ChatsResponse chatsResponse) {
        adapter = new ListChatsAdapter(getActivity(), chatsResponse);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvChats.setLayoutManager(linearLayoutManager);
        rvChats.setAdapter(adapter);
        endlessScrolListener(chatsResponse, linearLayoutManager);

    }

    private void endlessScrolListener(final ChatsResponse response, final LinearLayoutManager linearLayoutManager) {
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if(response.getMeta().getPagination().getCurrentPage()+1 < response.getMeta().getPagination().getPageCount()) {
                    listChatsPresenter.getChats("", response.getMeta().getPagination().getCurrentPage() + 1, 50);
                }
            }
        };
    }

    @Override
    public void onChatsError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChatCreateSuccess(CreateChatResponse createChatResponse) {
        Toast.makeText(getActivity(), "Chat created!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChatCreateError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}
