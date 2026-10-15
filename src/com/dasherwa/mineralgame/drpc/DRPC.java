package com.dasherwa.mineralgame.drpc;

import com.dasherwa.mineralgame.main.Main;
import dev.firstdark.rpc.DiscordRpc;
import dev.firstdark.rpc.enums.ActivityType;
import dev.firstdark.rpc.enums.ErrorCode;
import dev.firstdark.rpc.exceptions.PipeAccessDenied;
import dev.firstdark.rpc.exceptions.UnsupportedOsType;
import dev.firstdark.rpc.handlers.RPCEventHandler;
import dev.firstdark.rpc.models.DiscordRichPresence;
import dev.firstdark.rpc.models.User;

public class DRPC {

	String app_id;
	DiscordRpc drpc;
	RPCEventHandler handler;
	DiscordRichPresence presence;
	float counter;
	long start_time_stamp;
	
	public DRPC(String app_id) {
		
		this.app_id = app_id;
		
	}
		
	public void InitDiscordRPC(){
		
		start_time_stamp = System.currentTimeMillis() / 1000L;
		
		counter = 0f;
		
		drpc = new DiscordRpc();
		handler = new RPCEventHandler() {
			
			public void ready(User user) {
				presence = DiscordRichPresence.builder()
						.details("Total Money: " + Main.ui.GetTotalBalance())
		                .largeImageKey("quarry_drpc_main")
		                .activityType(ActivityType.PLAYING)
		                .state("Quarry Count: " + Main.ui.GetQuarryCount())
		                .startTimestamp(start_time_stamp)
		                .build();
				
				drpc.updatePresence(presence);
				System.out.println(user.getUsername());
			}
			
			@Override
		    public void disconnected(ErrorCode errorCode, String message) {
		        System.out.println("Disconnected " + errorCode + " - " + message);
		    }

		    @Override
		    public void errored(ErrorCode errorCode, String message) {
		        System.out.println("Errored " + errorCode + " - " + message);
		    }
			
		};
		
		try {
			drpc.init(app_id, handler, false);
		} catch (PipeAccessDenied e) {
			e.printStackTrace();
		} catch (UnsupportedOsType e) {
			e.printStackTrace();
		}
		
	}
	
	public void UpdateDiscordRPC() {
		
		presence = DiscordRichPresence.builder()
				.details("Total Money: " + Main.ui.GetTotalBalance())
                .largeImageKey("quarry_drpc_main")
                .activityType(ActivityType.PLAYING)
                .state("Quarry Count: " + Main.ui.GetQuarryCount())
                .startTimestamp(start_time_stamp)
                .build();
		
		drpc.updatePresence(presence);
		
	}
	
	public void EndDiscordRPC() {
		drpc.shutdown();
	}
	
	public void SetCounter(float counter) {
		this.counter = counter;
	}
	
	public float GetCounter() {
		return counter;
	}
	
}
