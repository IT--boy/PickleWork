package com.pickcle.picklework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.pickcle.picklework.autojs.AutoJs;
import com.pickcle.picklework.launch.GlobalProjectLauncher;
import com.stardust.autojs.core.console.ConsoleView;
import com.stardust.autojs.core.console.StardustConsole;

public class LogActivity extends AppCompatActivity {


    public static final String EXTRA_LAUNCH_SCRIPT = "launch_script";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        if (getIntent().getBooleanExtra(EXTRA_LAUNCH_SCRIPT, false)) {
            GlobalProjectLauncher.getInstance().launch(this);
        }
    }

    private void setupView() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ConsoleView consoleView = (ConsoleView) findViewById(R.id.console);
        consoleView.setConsole((StardustConsole) AutoJs.getInstance().getGlobalConsole());
        consoleView.findViewById(R.id.input_container).setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(this, SettingsActivity.class));
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
