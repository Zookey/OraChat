package net.zoranpavlovic.orachat.chats.create;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import net.zoranpavlovic.orachat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by osx on 01/08/2017.
 */

public class CreateChatDialog extends Dialog {

    @BindView(R.id.et_chat_name) EditText etName;
    @BindView(R.id.et_chat_message) EditText etMessage;

    private CreateChatCallback callback;

    public CreateChatDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_create_chat);
        ButterKnife.bind(this);
    }

    public void setCallback(CreateChatCallback callback){
        this.callback = callback;
    }

    @OnClick(R.id.btn_chat_create)
    void onCreateChatClick(){
        if(isValid()) {
            callback.onChatCreated(getName(), getMessage());
            dismiss();
        } else {
            Toast.makeText(getContext(), R.string.enter_chat_name_and_message, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValid(){
        if(getName().isEmpty() || getMessage().isEmpty()){
            return false;
        }
        return true;
    }

    private String getName(){
        return etName.getText().toString();
    }

    private String getMessage(){
        return etMessage.getText().toString();
    }


}
