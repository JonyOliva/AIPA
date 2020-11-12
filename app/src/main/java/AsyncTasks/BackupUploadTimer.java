package AsyncTasks;

import java.util.TimerTask;

public class BackupUploadTimer extends TimerTask {

    @Override
    public void run() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        UploadBackUp upload = new UploadBackUp();
                        upload.execute();
                    }
                }
        ).start();
    }
}
