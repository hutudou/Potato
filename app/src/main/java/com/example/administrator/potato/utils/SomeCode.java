package com.example.administrator.potato.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Administrator
 * @date 2019/4/8
 * 纪录一些常用的代码片段
 */

public class SomeCode {

     /*
        代码片段一:密码框明文和密文切换

     if (mPassword.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
     mPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
     textShowPassword.setText("显示密码");
     } else {
     mPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
     textShowPassword.setText("隐藏密码");
     }*/

    /*
       代码片段二:防止软键盘挡住editText(请在onCreate中的设置布局前使用)

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
     */

    /*
    代码片段三:防止布局中的editText自动获得焦点 可以使其父布局先获取焦点
        android:focusable="true"
        android:focusableInTouchMode="true"
     */

    /*
    代码片断四:大量文本汉字对齐的占位符，主要使用以下四个符号
            &#160;
            &#12288;
            &#8194;
            &#8197;
   换算关系:1个汉字 = 4个&#160; = 4个&#8197; = 1个&#12288; = 2个&#8194;
     */

    /*
    代码片段五:下载文件
        public void downloadFile(String path) {
        final String url = "http://192.168.1.47:10102" + path;
        final long startTime = System.currentTimeMillis();
        Log.i("DOWNLOAD", "startTime=" + startTime);
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                e.printStackTrace();
                Log.i("DOWNLOAD", "download failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                String savePath = AppConstant.FILE_DOWNLOAD_PATH;
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(savePath);
                    if (!file.exists()) {
                        boolean b = file.mkdirs();
                        if (b) {
                            AppLogger.e("创建成功");
                        }
                    }
                    String fileName = savePath + url.substring(url.lastIndexOf("/") + 1);
                    File file1 = new File(fileName);
                    if (file1.exists()) {
                        file1.delete();
                    }
                    fos = new FileOutputStream(file1);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                    }
                    fos.flush();
                    // 下载完成
                    Log.i("DOWNLOAD", "download success");
                    Log.i("DOWNLOAD", "totalTime=" + (System.currentTimeMillis() - startTime));
                } catch (Exception e) {
                    e.printStackTrace();
//                    listener.onDownloadFailed();
                    Log.i("DOWNLOAD", "download failed");
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        });
    }
     */
}
