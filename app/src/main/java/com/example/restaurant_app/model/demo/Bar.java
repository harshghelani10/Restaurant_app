package com.example.restaurant_app.model.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import javax.annotation.Generated;

import com.example.restaurant_app.Menu;
import com.example.restaurant_app.R;
import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.UserHome;
import com.example.restaurant_app.model.deletecartmodel.DeleteCart;
import com.example.restaurant_app.model.deletecartmodel.DeletedCart;
import com.example.restaurant_app.model.makeordermodel.MakeOrder;
import com.example.restaurant_app.model.viewcartmodel.Item;
import com.example.restaurant_app.model.viewcartmodel.ViewCart;
import com.example.restaurant_app.model.viewcartmodel.YourCart;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@Generated("jsonschema2pojo")
public class Bar {

@SerializedName("type")
@Expose
private String type;

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

}
-----------------------------------com.example.Baz.java-----------------------------------

package com.example;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Baz {

@SerializedName("type")
@Expose
private String type;

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

}
-----------------------------------com.example.Foo.java-----------------------------------

package com.example;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Foo {

@SerializedName("type")
@Expose
private String type;

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

}
-----------------------------------com.example.GetIngredients.java-----------------------------------

package com.example;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class GetIngredients {

@SerializedName("type")
@Expose
private String type;
@SerializedName("properties")
@Expose
private Properties properties;

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public Properties getProperties() {
return properties;
}

public void setProperties(Properties properties) {
this.properties = properties;
}

}
-----------------------------------com.example.Properties.java-----------------------------------

package com.example;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Properties {

@SerializedName("foo")
@Expose
private Foo foo;
@SerializedName("bar")
@Expose
private Bar bar;
@SerializedName("baz")
@Expose
private Baz baz;

public Foo getFoo() {
return foo;
}

public void setFoo(Foo foo) {
this.foo = foo;
}

public Bar getBar() {
return bar;
}

public void setBar(Bar bar) {
this.bar = bar;
}

public Baz getBaz() {
return baz;
}

public void setBaz(Baz baz) {
this.baz = baz;
}

}
