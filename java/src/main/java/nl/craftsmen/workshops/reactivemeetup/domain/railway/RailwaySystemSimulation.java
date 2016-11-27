package nl.craftsmen.workshops.reactivemeetup.domain.railway;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

public class RailwaySystemSimulation {
	
	private final SerializedSubject<TrainMetrics, TrainMetrics> trainMetricsPublisher;

	public RailwaySystemSimulation(RailwayNetwork railwayNetwork) {
		
		PublishSubject<TrainMetrics> publisher = PublishSubject.create();
		trainMetricsPublisher = publisher.toSerialized();
		
		publishEvents(1234);
		publishEvents(677);
	}
	
	private void publishEvents(long interval) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(interval);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					trainMetricsPublisher.onNext(new TrainMetrics("", 0, null));
				}
				
			}
		});
		t.setDaemon(true);
		t.start();
	}
	
	public Observable<TrainMetrics> trainMetrics$() {
		return trainMetricsPublisher.asObservable();
	}
	
}
