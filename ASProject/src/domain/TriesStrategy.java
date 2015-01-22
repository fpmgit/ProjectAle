package domain;
import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class TriesStrategy extends Strategy implements Serializable {

		private Integer maximTries;
		
		public TriesStrategy() {
			super();
			
		}
		
		public TriesStrategy(Integer id, Integer tries) {
			this.id = id;
			maximTries = tries;
		}
		public Integer getMaximTries() {
			return maximTries;
		}

		public void setMaximTries(Integer maximTries) {
			this.maximTries = maximTries;
		}


		@Override
		public String toString() {
			return "TriesStrategy [maximTries=" + maximTries + "]";
		}
		
}
