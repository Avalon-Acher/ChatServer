package main;

import java.awt.EventQueue;
import java.sql.SQLException;

import db.DBManager;
import view.MainWindow;
import view.MainWindow.OnStartServersListener;

public class StartServers {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try{
					MainWindow frame=MainWindow.getMainWindow();
					DBManager dbManager=DBManager.getDBManager();
					ServerListener listener=new ServerListener();
					frame.setOnStartServersListener(new OnStartServersListener() {
						
						@Override
						public void stop() {
							// TODO Auto-generated method stub
							try{
								dbManager.getConnection().close();
								MainWindow.getMainWindow().setShowMsg("StartServers------DB connection is closed");
							}catch(SQLException e){
								MainWindow.getMainWindow().setShowMsg("StartServers------DB connection close faild");
								e.printStackTrace();
							}
						}
						
						@Override
						public void start() {
							// TODO Auto-generated method stub
							dbManager.addDBDriver();
							dbManager.connectDB();
							try{
								dbManager.initDB();
							}catch(Exception e){
								e.printStackTrace();
							}
							if(!listener.isAlive()){
								listener.start();
							}
							
						}
					});
					
					frame.setVisible(true);
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
}
