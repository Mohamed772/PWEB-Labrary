package documents;

import java.util.TimerTask;

public class TimerReservation extends TimerTask {
	IDocument doc;

	public TimerReservation(IDocument doc) {
		this.doc = doc;
	}
	
	@Override
	public void run() {
		doc.retour();
	}

}
