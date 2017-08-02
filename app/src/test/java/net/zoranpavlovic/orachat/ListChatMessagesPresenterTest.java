package net.zoranpavlovic.orachat;

import net.zoranpavlovic.orachat.chats.list.ListChatsPresenterImpl;
import net.zoranpavlovic.orachat.chats.list.ListChatsRepository;
import net.zoranpavlovic.orachat.chats.list.ListChatsView;
import net.zoranpavlovic.orachat.chats.list.models.ChatsResponse;
import net.zoranpavlovic.orachat.messages.list.ListChatMessagesPresenterImpl;
import net.zoranpavlovic.orachat.messages.list.ListChatMessagesRepository;
import net.zoranpavlovic.orachat.messages.list.ListChatMessagesView;
import net.zoranpavlovic.orachat.messages.list.models.MessagesResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Flowable;

import static org.mockito.Mockito.mock;

/**
 * Created by osx on 02/08/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ListChatMessagesPresenterTest {


    @Test
    public void shouldGetMessages() {

        ListChatMessagesRepository repository = mock(ListChatMessagesRepository.class);
        ListChatMessagesView view = mock(ListChatMessagesView.class);
        MessagesResponse response = mock(MessagesResponse.class);

        int testPage = 1;
        int testlimit = 50;

        Mockito.when(repository.getMessagesForChat(1, testPage, testlimit))
                .thenReturn(Flowable.just(response));

        ListChatMessagesPresenterImpl presenter = new ListChatMessagesPresenterImpl(repository, view);
        presenter.getMessagesForChat(1, testPage, testlimit);

        Mockito.verify(view).onChatMessagesLoaded(response);
    }
}
