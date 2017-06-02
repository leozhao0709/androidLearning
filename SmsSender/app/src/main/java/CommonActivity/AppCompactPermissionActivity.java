package CommonActivity;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lzhao on 6/1/17.
 */


public class AppCompactPermissionActivity extends AppCompatActivity {

    private int permissionRequestCode = 88;
    private PermissionsCallback permissionsCallback;


    protected boolean hasPermission(@NonNull String permission) {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    protected void getPermissions(@NonNull PermissionsCallback permissionsCallback, @NonNull String... permissions) {
        this.permissionsCallback = permissionsCallback;
        if (this.allPermissionsGranted(permissions))
        {
            permissionsCallback.grantPermissionsCallback(permissions);
        } else
        {
            ActivityCompat.requestPermissions(this, permissions, this.permissionRequestCode);
        }
    }

    private boolean allPermissionsGranted(String... permissions) {
        for (String permission : permissions)
        {
            if (!this.hasPermission(permission))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == this.permissionRequestCode)
        {
            if (this.allGrantResultsGranted(grantResults))
            {
                this.permissionsCallback.grantPermissionsCallback(permissions);
            } else
            {
                this.permissionsCallback.deniedPermissionsCallback(permissions);
            }
        }
    }

    private boolean allGrantResultsGranted(@NonNull int[] grantResults) {
        for (int result : grantResults)
        {
            if (result != PackageManager.PERMISSION_GRANTED)
            {
                return false;
            }
        }

        return true;
    }
}
