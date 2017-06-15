package xy.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import xy.com.ui.activity.LoginSuccessActivity;

public class MainActivity extends AppCompatActivity {

    private EditText email, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.email);
        pwd = (EditText) findViewById(R.id.pwd);
    }

    public void onLogin(View view) {
        startActivity(new Intent(MainActivity.this, LoginSuccessActivity.class)
                .putExtra("email", "" + email.getText().toString().trim())
                .putExtra("pwd", "" + pwd.getText().toString().trim()));
    }
}
