package bsr;
/*A code from www.bipinrupadiya.com*/
import java.util.*;
import java.net.*;
import java.io.*;
public class MySocket
{
 String serverName;
 int port;
 String frame;
 public MySocket()
 {
  this.serverName="localhost";
  this.port=6061;
  this.frame="BipinRupadiya";
 }
 public MySocket(int port)
 {
  this.port=port;
 }
 public MySocket(String serverName,int port,String frame)
 {
  this.serverName=serverName;
  this.port=port;
  this.frame=frame;
 }

 public void sendFrame(String frame) throws Exception
 {
  ServerSocket ss=new ServerSocket(port);
  Socket s=ss.accept();
  
  byte bFrame[]=frame.getBytes();
  OutputStream os=s.getOutputStream();
  os.write(bFrame);
  ss.close();
  s.close();
    
    }
 public String receiveFrame() throws Exception
 {
  Socket s=new Socket(serverName,port);
  InputStream is=s.getInputStream();
  BufferedReader br=new BufferedReader(new InputStreamReader(is));
  String str=br.readLine(); 
  s.close();
  return(str);  
    }  
 
}