
package net.zoranpavlovic.orachat.account.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountResponse {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
