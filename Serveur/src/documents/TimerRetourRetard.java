package documents;

import java.time.LocalDate;
import java.util.TimerTask;

import utilisateurs.Abonne;

public class TimerRetourRetard extends TimerTask {
	Abonne abo;

	public TimerRetourRetard(Abonne abo) {
		this.abo = abo;
	}
	
	@Override
	public void run() {
		abo.setDateban(LocalDate.now());
	}

}
