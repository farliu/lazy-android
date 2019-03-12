package com.ofcoder.lazy.window.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ofcoder.lazy.R;
import com.ofcoder.lazy.domain.enums.IntentExtraEnum;
import com.ofcoder.lazy.net.Client;
import com.ofcoder.lazy.window.view.GracefullyToast;
import com.yinfork.linedlayout.LinedRelativeLayout;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private LinedRelativeLayout layout;
    private Button btn_connect;
    private EditText et_connect_host, et_connect_port;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        layout = findViewById(R.id.layout_edit);
        et_connect_host = findViewById(R.id.et_connect_host);
        et_connect_port = findViewById(R.id.et_connect_port);
        btn_connect = findViewById(R.id.btn_connect);
    }

    @Override
    protected void initLoad() {
        layout.setIgnoreFirstFocus(true);
        layout.setLineWidth(3);
        layout.setLineColor(getResources().getColor(R.color.colorPrimary));
        layout.setBendLength(70);
        layout.setLinePaddingBottom(5);
        layout.setLinePaddingLeft(0);
        layout.setLinePaddingRight(0);
        layout.setAnimDuration(300);
        btn_connect.setOnClickListener(this);

        client.start();
    }


    Client client = new Client();
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_connect:
                String host = et_connect_host.getText().toString();
                Integer port = Integer.valueOf(et_connect_port.getText().toString());
                String msg = ((EditText)findViewById(R.id.xxxxx)).getText().toString();

                try {
                    client.send(msg, host, port);
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                if (StringUtils.isEmpty(host) || StringUtils.isEmpty(port)) {
//                    GracefullyToast.show(this, "host, port is required param.");
//                    return;
//                }
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.putExtra(IntentExtraEnum.HOST.name(), host);
//                intent.putExtra(IntentExtraEnum.PORT.name(), Integer.parseInt(port));
//                startActivity(intent);
//                this.finish();
                break;
            default:
                break;
        }
    }
}
