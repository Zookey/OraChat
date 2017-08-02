package net.zoranpavlovic.orachat;

import net.zoranpavlovic.orachat.account.register.AccountResponse;
import net.zoranpavlovic.orachat.account.register.RegisterAccountPresenterImpl;
import net.zoranpavlovic.orachat.account.register.RegisterAccountView;
import net.zoranpavlovic.orachat.account.register.RegisterRepository;
import net.zoranpavlovic.orachat.chats.list.ListChatsPresenterImpl;
import net.zoranpavlovic.orachat.chats.list.ListChatsRepository;
import net.zoranpavlovic.orachat.chats.list.ListChatsView;
import net.zoranpavlovic.orachat.chats.list.models.ChatsResponse;

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
public class ListChatsPresenterTest {

    @Test
    public void shouldGetChats() {

        ListChatsRepository repository = mock(ListChatsRepository.class);
        ListChatsView view = mock(ListChatsView.class);
        ChatsResponse response = mock(ChatsResponse.class);

        String testName = "name";
        int testPage = 1;
        int testlimit = 50;

        Mockito.when(repository.getChats(testName, testPage, testlimit))
                .thenReturn(Flowable.just(response));

        ListChatsPresenterImpl presenter = new ListChatsPresenterImpl(repository, view);
        presenter.getChats(testName, testPage, testlimit);

        Mockito.verify(view).onChatsLoaded(response);
    }
}
