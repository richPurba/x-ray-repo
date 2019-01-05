package main.com.xray.http;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RESTClient {

    public static final String PATH="/x-ray/resources/hits";
    public InetAddress inetAddress;
    private int port;
    private String path;
    private static final Logger logger = Logger.getLogger(RESTClient.class.getName());
    public static final String HEADER_NAME_PREFIX = "xray_";
    private static final int TIMEOUT = 1000;

    public RESTClient(String hostName, int port, String path){
        try{
            this.inetAddress = InetAddress.getByName(hostName);
            this.port = port;
            this.path = path;
        }catch(UnknownHostException ex){
            throw new IllegalArgumentException("Wrong: "+ hostName + "Reason: "+ex ,ex);
        }
    }

    public RESTClient(URL url){
        this(url.getHost(), url.getPort(),url.getPath());
    }

    public RESTClient(){
        this("localhost",8080,PATH);
    }

    public String put(String content, Map<String,String> headers){
        Socket socket = null;
        BufferedWriter wr = null;
        InputStreamReader is = null;

        try{
            socket = new Socket(inetAddress,port);
            // socket above is the endpoint where machine-to-machine talks one another.
            socket.setSoTimeout(TIMEOUT);
            wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF8"));
            //note: wr above is getting the socket outputStream, and encoded it into byte with specified charset UTF8!
            is = new InputStreamReader(socket.getInputStream());
            //note: is above is also similarly working like wr, get the input from socket
            wr.write("PUT "+path+" HTTP/1.0\r\n");
            wr.write(getFormattedHeader("Content-Length",""+content.length()));
            wr.write(getFormattedHeader("Content-Type","text/plain"));

            for(Map.Entry<String,String> header : headers.entrySet()){
                wr.write(getFormattedHeader(HEADER_NAME_PREFIX + header.getKey(),header.getValue()));
            }
            wr.write("\r\n");
            wr.write(content);
            wr.flush();
            char[] buffer = new char[1024];
            StringWriter stringWriter = new StringWriter();
            while(is.read(buffer) != -1){// ( because -1 is when reading() reaches the end of the array
                //is.read(buffer) basically reads out the is into buffer until it reaches the end of the array.
                //character stream that collects its output in a string buffer, which can then be used to construct a string.
                stringWriter.write(buffer);//writes down the buffer into a stringwriter
            }
            return stringWriter.toString();
        }catch (Exception e){
            logger.log(Level.SEVERE,"problem communicating with x-ray services:{0}",e);
            return "--";
        }finally{
            try{
                wr.close();
            }catch (IOException ex){ }
            try{
                is.close();
            }catch (IOException e){}
            try{
                socket.close();
            }catch(IOException e){}

        }
    }

    String getFormattedHeader(String key, String value){
        return key + ": " + value + "\r\n";
    }
    public InetAddress getInetAddress(){return inetAddress;}

    public String getPath(){return path};

    public int getPort(){return port;}

}
