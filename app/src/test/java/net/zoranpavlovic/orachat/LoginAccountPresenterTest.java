package net.zoranpavlovic.orachat;

import net.zoranpavlovic.orachat.account.login.LoginAccountPresenterImpl;
import net.zoranpavlovic.orachat.account.login.LoginAccountView;
import net.zoranpavlovic.orachat.account.login.LoginRepository;
import net.zoranpavlovic.orachat.account.register.AccountResponse;

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
public class LoginAccountPresenterTest {

    @Test
    public void shouldLogin() {

        LoginRepository loginRepository = mock(LoginRepository.class);
        LoginAccountView view = mock(LoginAccountView.class);
        AccountResponse accountResponse = mock(AccountResponse.class);

        String testemail = "testemail";
        String testpassword = "testpassword";

        Mockito.when(loginRepository.login(testemail, testpassword))
                .thenReturn(Flowable.just(accountResponse));

        LoginAccountPresenterImpl loginAccountPresenter = new LoginAccountPresenterImpl(loginRepository, view);
        loginAccountPresenter.login(testemail, testpassword);

        Mockito.verify(view).onLoginSuccess(accountResponse);
    }
}
