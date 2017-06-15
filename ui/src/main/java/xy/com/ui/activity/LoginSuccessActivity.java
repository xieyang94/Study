package xy.com.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import xy.com.ui.R;

public class LoginSuccessActivity extends AppCompatActivity {

    private TextView email, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        email = (TextView) findViewById(R.id.email);
        pwd = (TextView) findViewById(R.id.pwd);
        String email = getIntent().getStringExtra("email");
        String pwd = getIntent().getStringExtra("pwd");
        this.email.setText(email);
        this.pwd.setText(pwd);
    }
}
