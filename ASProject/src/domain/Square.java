package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Square implements Serializable{
	
	@Id
	@GeneratedValue (strategy=GenerationType.SEQUENCE, generator="squ_gen")
	@SequenceGenerator (name= "squ_gen", sequenceName = "squ_id_sq")
	private Integer id;
	
	private Integer rowNumber;
	private Integer columnNumber;
	private Integer value;
	
	@ManyToOne
	@JoinColumn
	private Game game;
	private boolean isUncovered; 
	private boolean isMarked;
	private boolean isMine;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}
	public Integer getColumnNumber() {
		return columnNumber;
	}
	public void setColumnNumber(Integer columnNumber) {
		this.columnNumber = columnNumber;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public boolean isUncovered() {
		return isUncovered;
	}
	public void setUncovered(boolean isUncovered) {
		this.isUncovered = isUncovered;
	}
	public boolean isMarked() {
		return isMarked;
	}
	public void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	}
	public boolean isMine() {
		return isMine;
	}
	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}
	@Override
	public String toString(){
		
		return ("Id: " + this.getId() + " RowNumber: " + this.getRowNumber() + " columNumber: " 
				+ this.getColumnNumber() + " value: " + this.getValue() + "isMarked: " + this.isMarked()+
				"isMine: " + this.isMine());

	}
	
	
	
}
