package xy.com.cloud.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.bmob.push.PushConstants;
import xy.com.cloud.utils.NotificationUtil;

/**
 * author  :   xieyang
 * e-mail  :   1192171830@qq.com
 * time    :   2017/6/6
 * desc    :
 * version :   1.0
 */

public class PushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
            NotificationUtil.showNotification(context);

        }
    }

}
