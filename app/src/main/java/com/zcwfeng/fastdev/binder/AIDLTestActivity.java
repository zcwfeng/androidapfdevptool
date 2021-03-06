/*****************
 * Copyright (C), 2010-2015, FORYOU Tech. Co., Ltd.
 ********************/
package com.zcwfeng.fastdev.binder;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zcwfeng.fastdev.R;
import com.zcwfeng.fastdev.ui.activity.BaseActivity;


/**
 * @Filename: AIDLTestActivity.java
 * @Author: slcao
 * @CreateDate: 2011-5-16
 * @Description: description of the new class
 * @Others: comments
 * @ModifyHistory:
 */
public class AIDLTestActivity extends BaseActivity {


    private Button btnOk;
    private Button btnCancel;
    private Button btnCallBack;
    IServiceAidl mService;


    private void Log(String str) {
        Log.d("AIDL", "------ " + str + "------");
    }

    private IActivityAidl mCallback = new IActivityAidl.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void performAction() throws RemoteException {
            Log("AIDLActivity.performAction");
            Toast.makeText(AIDLTestActivity.this, "this toast is called from service", Toast.LENGTH_LONG).show();
        }

//		@Override
//		public void performAction(com.zcwfeng.fastdev.binder.Rect rect) throws RemoteException {
//
//            Log("AIDLActivity.performAction");
//			Log(MessageFormat.format("rect[bottom={0},top={1},left={2},right={3}]", rect.bottom,rect.top,rect.left,rect.right));
//			Toast.makeText(AIDLTestActivity.this, "this toast is called from service", Toast.LENGTH_LONG).show();
//
//		}
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log("connect service");
            mService = IServiceAidl.Stub.asInterface(service);
            try {
                mService.registerTestCall(mCallback);
            } catch (RemoteException e) {

            }
        }


        public void onServiceDisconnected(ComponentName className) {
            Log("disconnect service");
            mService = null;
        }
    };


    @Override
    public void onCreate(Bundle icicle) {
        Log("AIDLTestActivity.onCreate");
        super.onCreate(icicle);
        setContentView(R.layout.aidl_activity);
        btnOk = (Button) findViewById(R.id.btn_ok);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnCallBack = (Button) findViewById(R.id.btn_call_back);
        btnOk.setOnClickListener(v -> {
            Log("AIDLTestActivity.btnOk");
            Bundle args = new Bundle();

            Intent intent = new Intent(this, com.zcwfeng.fastdev.binder.AIDLTestService.class);
            intent.setAction("com.zcwfeng.fastdev.binder.AIDLTestService");
            intent.putExtras(args);
            bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
//            startService(intent);
            btnOk.setClickable(false);
        });
        btnCancel.setOnClickListener(v -> {
            Log("AIDLTestActivity.btnCancel");
            unbindService(mConnection);
            // stopService(intent);
            btnCancel.setClickable(false);
        });
        btnCallBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log("AIDLTestActivity.btnCallBack");
                try {
                    mService.invokCallBack();
                } catch (RemoteException e) {
                    e.printStackTrace();
                } finally {
                    btnCallBack.setClickable(false);
                }
            }
        });
    }

    public static void launch(Context scrollingActivity) {
        Intent intent = new Intent(scrollingActivity, AIDLTestActivity.class);
        scrollingActivity.startActivity(intent);
    }
}
