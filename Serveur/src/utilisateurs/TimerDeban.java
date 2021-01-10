package utilisateurs;

import java.util.TimerTask;

public class TimerDeban extends TimerTask {
	Abonne abo;

	public TimerDeban(Abonne abo) {
		this.abo = abo;
	}
	
	@Override
	public void run() {
		abo.setDeban();
	}


}
