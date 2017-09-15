package com.app.davidwang.androidyishutansuo.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.app.davidwang.androidyishutansuo.R;

public class ThreadPoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);

        new MyAsyncTask("1").execute("");
        new MyAsyncTask("2").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
        new MyAsyncTask("3").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, String> {
        private String p;

        public MyAsyncTask(String params) {
            super();
            p = params;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return p;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("MyAsyncTask", s);
        }
    }
}
