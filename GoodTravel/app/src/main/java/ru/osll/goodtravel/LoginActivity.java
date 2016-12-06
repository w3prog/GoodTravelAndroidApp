package ru.osll.goodtravel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ru.osll.goodtravel.R;

import com.vk.sdk.VKSdk;
import com.vk.sdk.util.VKUtil;

/**
 * Created by artem96 on 26.11.16.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle onSavedInstanceState) {

        super.onCreate(onSavedInstanceState);

        setContentView(R.layout.login_activity);

        Button vkSignIn = (Button) findViewById(R.id.vkAuthButton);
        vkSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] fingerprints = VKUtil.getCertificateFingerprint(LoginActivity.this,
                        LoginActivity.this.getPackageName());
                System.out.println(fingerprints[0]);
                VKSdk.login(LoginActivity.this, "profile");
            }
        });

    }
}
