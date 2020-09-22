package apk.put.green4go.signup;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.navigation.Navigation;
import apk.put.green4go.R;
import apk.put.green4go.classes.API;
import apk.put.green4go.classes.Global;
import apk.put.green4go.classes.User;

public class RegistrationFragment extends Fragment {

    API api;
    Button registerButton;
    EditText emailInput;
    EditText usernameInput;
    EditText passwordInput;
    EditText repeatPasswordInput;
    ProgressBar progressBar;
    TextView errorsText;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        registerButton = requireView().findViewById(R.id.registerButton);
        emailInput = requireView().findViewById(R.id.emailInput);
        usernameInput = requireView().findViewById(R.id.usernameInput);
        passwordInput = requireView().findViewById(R.id.passwordInput);
        repeatPasswordInput = requireView().findViewById(R.id.repeatPasswordInput);
        progressBar = requireView().findViewById(R.id.progressBar);
        errorsText = requireView().findViewById(R.id.errorsText);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( TextUtils.isEmpty(emailInput.getText().toString()) ||
                    TextUtils.isEmpty(usernameInput.getText().toString()) ||
                    TextUtils.isEmpty(passwordInput.getText().toString()) ||
                    TextUtils.isEmpty(repeatPasswordInput.getText().toString())
                ) {
                    progressBar.setVisibility(View.GONE);
                    errorsText.setVisibility(View.VISIBLE);
                    errorsText.setText(R.string.fill_all_fields);
                } else {
                    if (passwordInput.getText().toString().equals(repeatPasswordInput.getText().toString())) {
                        if (Global.isValidEmail(emailInput.getText().toString())) {
                            api = new API(view.getContext(), progressBar, errorsText);
                            registerUser(usernameInput.getText().toString(), emailInput.getText().toString(), passwordInput.getText().toString(), repeatPasswordInput.getText().toString());
                        } else {
                            progressBar.setVisibility(View.GONE);
                            errorsText.setVisibility(View.VISIBLE);
                            errorsText.setText(R.string.please_enter_valid_email);
                        }
                    } else {
                        progressBar.setVisibility(View.GONE);
                        errorsText.setVisibility(View.VISIBLE);
                        errorsText.setText(R.string.passwords_not_matching);
                    }
                }
            }
        });
    }

    public void registerUser(String username, String email, String password, String repeatPassword) {
        new RegisterUserTask().execute(username, email, password, repeatPassword);
    }

    private final class RegisterUserTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            errorsText.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... params) {
            HashMap<String, String> parameters = new HashMap<String, String>();
            parameters.put("username", params[0]);
            parameters.put("email", params[1]);
            parameters.put("password", params[2]);
            parameters.put("repeatPassword", params[3]);
            ArrayList<String> responseArray = api.callAPI("/users/", "POST", parameters, false, true);
            if(responseArray.get(0).equals("200")) {
                Global.user = new User();
                try {
                    Map<String, Object> json = new ObjectMapper().readValue(responseArray.get(1), HashMap.class);
                    Global.user.setId(Integer.parseInt(Objects.requireNonNull(json.get("id")).toString()));
                    Global.user.setEmail(Objects.requireNonNull(json.get("email")).toString());
                    Global.user.setUsername(Objects.requireNonNull(json.get("username")).toString());
                    Global.user.setStreak(Integer.parseInt(Objects.requireNonNull(json.get("streak")).toString()));
                    Global.user.setXpPoints(Integer.parseInt(Objects.requireNonNull(json.get("xpPoints")).toString()));
                    Global.user.setAvatar(Objects.requireNonNull(json.get("avatar")).toString());
                    Log.v("TESTAPI", Global.user.getUsername());
                } catch (IOException e) {
                    return e.getMessage();
                }
                return "SUCCESS";
            } else {
                Map<String, Object> json = null;
                try {
                    json = new ObjectMapper().readValue(responseArray.get(1), HashMap.class);
                    return json.get("message").toString();
                } catch (IOException e) {
                    return e.getMessage();
                }
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if(result == "SUCCESS") {
                progressBar.setVisibility(View.GONE);
                errorsText.setVisibility(View.GONE);
                Navigation.findNavController(requireView()).navigate(R.id.register_to_dashboard);
            } else {
                progressBar.setVisibility(View.GONE);
                errorsText.setVisibility(View.VISIBLE);
                errorsText.setText(result);
            }
        }
    }
}