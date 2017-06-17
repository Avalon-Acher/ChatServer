package main;

import java.util.ArrayList;
import java.util.List;

public class ChatManager {
	private ChatManager(){}
	
	List<SocketMsg> socketList=new ArrayList<>();
	private static final ChatManager chatManager=new ChatManager();
	public static ChatManager getChatManager(){
		return chatManager;
	}
	public void add(SocketMsg socketMsg){
		socketList.add(socketMsg);
	}
	
	public void remove(SocketMsg socketMsg){
		socketList.remove(socketMsg);
	}
}
