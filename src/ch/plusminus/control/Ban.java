package ch.plusminus.control;

import java.util.UUID;

public class Ban {

	private UUID uuid;
	private String reason;
	private String from;
	private String time;

	public Ban(UUID uuid, String reason, String from, String time){
		this.uuid=uuid;
		this.reason=reason;
		this.from=from;
		this.time=time;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
