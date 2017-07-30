
package net.zoranpavlovic.orachat.chats.create.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("users")
    @Expose
    private List<User> users = null;
    @SerializedName("last_chat_message")
    @Expose
    private LastChatMessage lastChatMessage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public LastChatMessage getLastChatMessage() {
        return lastChatMessage;
    }

    public void setLastChatMessage(LastChatMessage lastChatMessage) {
        this.lastChatMessage = lastChatMessage;
    }

}
