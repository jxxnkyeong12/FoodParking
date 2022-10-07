package com.example.team_project01.home;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import com.example.team_project01.R;
import com.example.team_project01.order.ManagerOrderDetailActivity;
import com.example.team_project01.order.Order_infoVO;

import java.util.ArrayList;
import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {

    private Context context;
    private String channelId="alarm_channel";
    private int intId= 11;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        Intent busRouteIntent = new Intent(context, ManagerOrderDetailActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(busRouteIntent);
        PendingIntent busRoutePendingIntent =
                stackBuilder.getPendingIntent(1, PendingIntent.FLAG_CANCEL_CURRENT|PendingIntent.FLAG_UPDATE_CURRENT);
        ArrayList<Order_infoVO> list = (ArrayList<Order_infoVO>) intent.getSerializableExtra("list");
        int i = intent.getIntExtra("i", -1);

        final NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(context,channelId)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.mipmap.ic_launcher).setDefaults(Notification.DEFAULT_ALL)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
                .setContentTitle("주문 예약 알림")
                .setContentText(list.get(i).getOrder_date() + "일 " + list.get(i).getOrder_time() + "시 " + list.get(i).getOrder_peple() + "인 예약이 있습니다") // 내용 설정
                .setContentIntent(busRoutePendingIntent);


        final NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(channelId,"Channel human readable title",NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

       // int id=(int)System.currentTimeMillis(); <= 공지할때 여러건이 노출되게 하려면 사용하면 됨.

        notificationManager.notify(intId,notificationBuilder.build());

    }
}