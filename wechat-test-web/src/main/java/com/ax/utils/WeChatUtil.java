package com.ax.utils;

import com.ax.pojo.AccessToken;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

//     https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
public class WeChatUtil {

    private static final String APPID = "wx1588c68a81acb07b";  //第三方用户唯一凭证
    private static final String APPSECRET = "6055ab031a2d3cb469881135b320b253";  // 第三方用户唯一凭证秘钥，及appsecret
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;//

    private  static final String  UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    /**
     *  编写Get请求的方法，但没有参数传递的时候，可以使用Get请求
     *  @param  url 需要请求的url
     *  @return 将请求Url后返回的数据，转换为Json格式，并return
     * */
    public static JSONObject doGetStr (String url) throws IOException {
        DefaultHttpClient client  = new DefaultHttpClient();  //获取DefaultHttpClient请求

        HttpGet httpGet = new HttpGet(url); //httpGet 将使用Get方式发送请求Url

        JSONObject  jsonObject = null;

        HttpResponse  response = client.execute(httpGet); //使用HttpRespose接收cliet执行httpGet的结果
        HttpEntity entity = response.getEntity();//从response 中获取结果，类型为HttpEntity
        if(entity != null ) {
            String result = EntityUtils.toString(entity, "utf-8");  //HttpEntity转为字符串类型
            jsonObject = JSONObject.fromObject(result);   //字符串类型转为JSON类型
        }

        return jsonObject;
    }

    /**
     * 编写Post请求的方法，当我们需要参数传递的时候，可以使用post请求
     * @Param url  需要请求的url
     * @param  outstr  需要传递的参数
     *           @return 将请求url 后返回的数据，转换为JSON格式，并return
     * */
    public  static JSONObject  doPostStr (String url ,String outstr) throws IOException {
        JSONObject jsonObject = null;

        DefaultHttpClient client = new DefaultHttpClient(); //获取DefaultHttpClient请求
        HttpPost httpPost = new HttpPost(url); //HttpPost将使用Post方式发送请求URL

        httpPost.setEntity(new StringEntity(outstr,"utf-8"));  //使用setEntity方法，将我们传进来的参数放入到请求中
        HttpResponse  response = client.execute(httpPost); //使用HttpResponse 接收clint执行httpPost的结果

        String result = EntityUtils.toString(response.getEntity(), "UTF-8"); //HttpEntity转换为字符串类型
        jsonObject = JSONObject.fromObject(result);   //字符串类型转化为JSON类型

        return jsonObject;
    }

    /**
     * 获取AccessToken
     * @return 返回拿到的sccess_token以及有效期
     * */
    public static AccessToken  getAccessToken() throws IOException {
        AccessToken token = new AccessToken();

        JSONObject jsonObject = WeChatUtil.doGetStr(WeChatUtil.ACCESS_TOKEN_URL);

        if(jsonObject != null ) {
            token.setToken(jsonObject.getString("access_token")); //取出access_token
            token.setExpiresIn(jsonObject.getInt("expires_in"));
        }

        return  token;
    }

    /**
     *   上传图片
     *   @param  filePath
     *   @param  accessToken
     *   @param type
     *
     *   @return String
     * */
    public  static String upload(String filePath,String accessToken,String type) throws IOException {
        File file = new  File(filePath);

        if(!file.exists()) {
            throw  new  IOException("文件不存在");
        }

        String url  = WeChatUtil.UPLOAD_URL.replace("ACCESS_TOKEN",accessToken).replace("TYPE",type);

        URL urlObject = new URL(url);

        HttpURLConnection connection  = (HttpURLConnection) urlObject.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);

        connection.setRequestProperty("Connection","Keep-Alive");
        connection.setRequestProperty("Charset","UTF-8");

        String BOUNDARY = "----------" +System.currentTimeMillis();
        connection.setRequestProperty("Content-Type","multipart/from-data; boundary="+BOUNDARY);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("--");
        stringBuilder.append(BOUNDARY);
        stringBuilder.append("\r\n");
        stringBuilder.append("Content-Disposition: from-data;name=\"file\";filename=\""+ file.getName()+"\"\r\n");
        stringBuilder.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = stringBuilder.toString().getBytes("UTF-8");

        OutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());

        dataOutputStream.write(head);

        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = dataInputStream.read(bufferOut)) != -1) {
            dataOutputStream.write(bufferOut,0,bytes);
        }
        dataInputStream.close();

        byte[] foot = ("\r\n--"+BOUNDARY+"--\r\n").getBytes("UTF-8");

        dataOutputStream.write(foot);
        dataOutputStream.flush();
        dataOutputStream.close();

        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        String result = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
            if (result == null) {
                result = stringBuffer.toString();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(bufferedReader != null) {
                bufferedReader.close();
            }
        }

        JSONObject jsonObject  = JSONObject.fromObject(result);
        System.out.println(jsonObject);

        String typeName = "media_id";
        if(!"image".equals(type)) {
            typeName = type + "_media_id";
        }
        String mediaId = jsonObject.getString(typeName);
        return mediaId;
    }












}



































