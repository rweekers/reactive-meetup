package nl.craftsmen.workshops.reactivemeetup.domain.railway;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

public class RailwaySystemSimulation {
	
	private final SerializedSubject<TrainMetrics, TrainMetrics> trainMetricsPublisher;

	public RailwaySystemSimulation() {
		
		PublishSubject<TrainMetrics> publisher = PublishSubject.create();
		trainMetricsPublisher = publisher.toSerialized();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					trainMetricsPublisher.onNext(new TrainMetrics("", 0, null));
				}
				
			}
		});
		
	}
	
	public Observable<TrainMetrics> trainMetrics$() {
		return trainMetricsPublisher.asObservable();
	}
	
}
