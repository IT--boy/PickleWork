package com.pickcle.picklework.autojs.script;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.pickcle.picklework.autojs.AutoJs;
import com.stardust.app.GlobalAppContext;
import com.stardust.autojs.execution.ExecutionConfig;
import com.stardust.autojs.execution.ScriptExecution;
import com.stardust.autojs.execution.ScriptExecutionListener;
import com.stardust.autojs.execution.SimpleScriptExecutionListener;
import com.stardust.autojs.runtime.exception.ScriptInterruptedException;
import com.stardust.autojs.script.ScriptSource;

import org.mozilla.javascript.RhinoException;

import java.io.File;

/**
 * Created by Stardust on 2017/5/3.
 */

public class Scripts {

    public static final String ACTION_ON_EXECUTION_FINISHED = "ACTION_ON_EXECUTION_FINISHED";
    public static final String EXTRA_EXCEPTION_MESSAGE = "message";
    public static final String EXTRA_EXCEPTION_LINE_NUMBER = "lineNumber";
    public static final String EXTRA_EXCEPTION_COLUMN_NUMBER = "columnNumber";

    private static final ScriptExecutionListener BROADCAST_SENDER_SCRIPT_EXECUTION_LISTENER = new SimpleScriptExecutionListener() {

        @Override
        public void onSuccess(ScriptExecution execution, Object result) {
            GlobalAppContext.get().sendBroadcast(new Intent(ACTION_ON_EXECUTION_FINISHED));
        }

        @Override
        public void onException(ScriptExecution execution, Exception e) {
            RhinoException rhinoException = getRhinoException(e);
            int line = -1, col = 0;
            if (rhinoException != null) {
                line = rhinoException.lineNumber();
                col = rhinoException.columnNumber();
            }
            if (ScriptInterruptedException.causedByInterrupted(e)) {
                GlobalAppContext.get().sendBroadcast(new Intent(ACTION_ON_EXECUTION_FINISHED)
                        .putExtra(EXTRA_EXCEPTION_LINE_NUMBER, line)
                        .putExtra(EXTRA_EXCEPTION_COLUMN_NUMBER, col));
            } else {
                GlobalAppContext.get().sendBroadcast(new Intent(ACTION_ON_EXECUTION_FINISHED)
                        .putExtra(EXTRA_EXCEPTION_MESSAGE, e.getMessage())
                        .putExtra(EXTRA_EXCEPTION_LINE_NUMBER, line)
                        .putExtra(EXTRA_EXCEPTION_COLUMN_NUMBER, col));
            }
        }

    };


    public static void openByOtherApps(String path) {
        Uri uri = Uri.parse("file://" + path);
        GlobalAppContext.get().startActivity(new Intent(Intent.ACTION_VIEW).setDataAndType(uri, "text/plain").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public static void openByOtherApps(File file) {
        openByOtherApps(file.getPath());
    }


    public static ScriptExecution run(ScriptFile file) {
        return AutoJs.getInstance().getScriptEngineService().execute(file.toSource(), new ExecutionConfig()
                .executePath(file.getParent()));
    }


    public static ScriptExecution runWithBroadcastSender(File file) {
        return AutoJs.getInstance().getScriptEngineService().execute(new ScriptFile(file).toSource(), BROADCAST_SENDER_SCRIPT_EXECUTION_LISTENER,
                new ExecutionConfig().executePath(file.getParent()));
    }


    public static ScriptExecution runRepeatedly(ScriptFile scriptFile, int loopTimes, long delay, long interval) {
        ScriptSource source = scriptFile.toSource();
        String directoryPath = scriptFile.getParent();
        return AutoJs.getInstance().getScriptEngineService().execute(source, new ExecutionConfig()
                .executePath(directoryPath)
                .loop(delay, loopTimes, interval));
    }

    @Nullable
    public static RhinoException getRhinoException(Throwable e) {
        while (e != null) {
            if (e instanceof RhinoException) {
                return (RhinoException) e;
            }
            e = e.getCause();
        }
        return null;
    }
}
