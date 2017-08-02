package net.zoranpavlovic.orachat;

import net.zoranpavlovic.orachat.account.login.LoginAccountPresenterImpl;
import net.zoranpavlovic.orachat.account.login.LoginAccountView;
import net.zoranpavlovic.orachat.account.login.LoginRepository;
import net.zoranpavlovic.orachat.account.register.AccountResponse;
import net.zoranpavlovic.orachat.account.register.RegisterAccountPresenterImpl;
import net.zoranpavlovic.orachat.account.register.RegisterAccountView;
import net.zoranpavlovic.orachat.account.register.RegisterRepository;

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
public class RegisterAccountPresenterTest {

    @Test
    public void shouldRegister() {

        RegisterRepository registerRepository = mock(RegisterRepository.class);
        RegisterAccountView view = mock(RegisterAccountView.class);
        AccountResponse accountResponse = mock(AccountResponse.class);

        String testName = "name";
        String testemail = "testemail";
        String testpassword = "testpassword";

        Mockito.when(registerRepository.register(testName, testemail, testpassword, testpassword))
                .thenReturn(Flowable.just(accountResponse));

        RegisterAccountPresenterImpl presenter = new RegisterAccountPresenterImpl(registerRepository, view);
        presenter.register(testName, testemail, testpassword, testpassword);

        Mockito.verify(view).onRegisterAccountSuccess(accountResponse);
    }
}
