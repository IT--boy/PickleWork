package com.pickcle.picklework;

import android.os.Bundle;
import android.view.View;

import com.pickcle.picklework.autojs.AutoJs;
import com.pickcle.picklework.launch.GlobalProjectLauncher;
import com.pickcle.picklework.ui.BaseTitleCompatActivity;
import com.stardust.autojs.core.console.ConsoleView;
import com.stardust.autojs.core.console.StardustConsole;

import butterknife.BindView;

public class LogActivity extends BaseTitleCompatActivity {
    public static final String EXTRA_LAUNCH_SCRIPT = "launch_script";
    @BindView(R.id.console)
    ConsoleView consoleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        if (getIntent().getBooleanExtra(EXTRA_LAUNCH_SCRIPT, false)) {
            GlobalProjectLauncher.getInstance().launch(this);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_log_layout;
    }

    private void initView() {
        setBackView();
        setTitleText("日志列表");
        consoleView.setConsole((StardustConsole) AutoJs.getInstance().getGlobalConsole());
        consoleView.findViewById(R.id.input_container).setVisibility(View.GONE);
    }

}
