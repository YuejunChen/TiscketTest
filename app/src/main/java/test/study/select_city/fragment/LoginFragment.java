package test.study.select_city.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import test.study.select_city.Activity.MainActivity;
import test.study.select_city.R;
import test.study.select_city.utils.Okhttp;

/**
 * Created by Mr.Chen on 2017/8/24.
 */
public class LoginFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //用户名和密码的输入框
    private EditText userName,passWord;
    //登录按钮
    private Button loginButton;
    private Fragment meFragment;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container,
                false);
        loginButton=(Button)view.findViewById(R.id.login_btnLogin);
        userName=(EditText)view.findViewById(R.id.login_edtId);
        passWord=(EditText)view.findViewById(R.id.login_edtPwd);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=userName.getText().toString();
                String password=passWord.getText().toString();
                if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
                    Toast toast=Toast.makeText(getActivity(),"请输入用户名或密码",Toast.LENGTH_SHORT);
                    toast.show();
               }else{
                    new DataTask().execute(username,password);
                }}
        });
        return view;
    }
    class DataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String...params) {
            String s = Okhttp.LoginWithPost(params[0],params[1]);
            return s;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                if(s.equals("true")){
                    MainActivity activity=(MainActivity)getActivity();
                    //持久化存储数据
                    pref = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
                    //获取SharedPreferences.Editor对象
                    editor = pref.edit();
                    editor.putBoolean("userstate",true);
                    editor.putString("user",userName.getText().toString());
                    editor.commit();
                    if (getActivity().getIntent().getExtras() != null && getActivity().getIntent().getExtras().getString("className") != null){
                        startActivity();
                    }
                    else{
                        if (meFragment == null) {
                            meFragment = new MeFragment();
                        }
                        activity.addOrShowFragment(getActivity().getSupportFragmentManager().beginTransaction(), meFragment);
                    }

                }
                else {
                    Toast toast=Toast.makeText(getActivity(),"您输入的用户名或密码有误",Toast.LENGTH_SHORT);
                    toast.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void startActivity() {
            String className = getActivity().getIntent().getExtras().getString("className");
            getActivity().getIntent().removeExtra("className");
            if (className != null && !className.equals(getContext().getClass().getName())) {
                try {
                    ComponentName componentName = new ComponentName(getContext(), Class.forName(className));
                    startActivity(getActivity().getIntent().setComponent(componentName));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        getActivity().finish();
    }


}