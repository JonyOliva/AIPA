package AsyncTasks;

import android.content.Context;

import java.util.TimerTask;

public class BackupUploadTimer extends TimerTask {
    Context context;
    public BackupUploadTimer(Context _context) {
        context = _context;
    }

    @Override
    public void run() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        UploadBackUp upload = new UploadBackUp(context);
                        upload.execute();
                    }
                }
        );
    }
}
