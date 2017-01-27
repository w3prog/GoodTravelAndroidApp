package ru.osll.goodtravel.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ru.osll.goodtravel.R;

import com.vk.sdk.VKSdk;
import com.vk.sdk.util.VKUtil;

/**
 * Активити для входа в приложение.
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
                // TODO: 27.01.17 Необходимо решить где в приложении должна быть логика для авторизации.
            }
        });

        Button continueWithoutSignin = (Button) findViewById(R.id.continue_without_signin_button);
        continueWithoutSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MarketingActivity.class);
                startActivity(intent);
            }
        });

    }
}
