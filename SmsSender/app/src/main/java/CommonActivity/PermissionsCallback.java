package CommonActivity;

import android.support.annotation.NonNull;

/**
 * Created by lzhao on 6/1/17.
 */

public interface PermissionsCallback {
    void grantPermissionsCallback(@NonNull String... permissions);

    void deniedPermissionsCallback(@NonNull String... permissions);
}
