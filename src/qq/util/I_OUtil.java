package qq.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class I_OUtil {
	public static void sendNews(Socket s, Object arg) throws IOException {
		new ObjectOutputStream(s.getOutputStream()).writeObject(arg);

	}

	public static Object receiveNews(Socket s) throws ClassNotFoundException, IOException {
		return new ObjectInputStream(s.getInputStream()).readObject();
	}

}
