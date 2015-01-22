package domain;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class TimeStrategy extends Strategy implements Serializable{

	private Integer maximTime;
	
	public TimeStrategy(){
		super();
	}
	public TimeStrategy(Integer id,Integer time) {
		this.id = id;
		maximTime = time;
	}

	public Integer getMaximTime() {
		return maximTime;
	}

	public void setMaximTime(Integer maximTime) {
		this.maximTime = maximTime;
	}
	@Override
	public String toString() {
		return "TimeStrategy [maximTime=" + maximTime + "]";
	}
	
}
