package com.garage_inc.bureau.http;

import com.garage_inc.bureau.interactor.ConfirmScheduleInteractor;
import com.garage_inc.bureau.interactor.Interactor;
import com.garage_inc.bureau.interactor.ScheduleOrderInteractor;
import com.garage_inc.bureau.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/scheduler")
public class SchedulerController {

    final Interactor<ScheduleOrderInteractor.Input, ScheduleOrderInteractor.Output> scheduleOrderInteractor;

    final Interactor<ConfirmScheduleInteractor.Input, ConfirmScheduleInteractor.Output> confirmScheduleInteractor;

    public SchedulerController(@Autowired Interactor<ScheduleOrderInteractor.Input, ScheduleOrderInteractor.Output> scheduleOrderInteractor,
                               @Autowired Interactor<ConfirmScheduleInteractor.Input, ConfirmScheduleInteractor.Output> confirmScheduleInteractor) {
        this.scheduleOrderInteractor = scheduleOrderInteractor;
        this.confirmScheduleInteractor = confirmScheduleInteractor;
    }

    @RequestMapping(path = "/book", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Order> book(@RequestBody ScheduleOrderInteractor.Input input) {
        try {
            return ResponseEntity.ok(scheduleOrderInteractor.run(input).getOrder());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @RequestMapping(path = "/confirm", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Order> confirm(@RequestBody ConfirmScheduleInteractor.Input input) {
        try {
            return ResponseEntity.ok(confirmScheduleInteractor.run(input).getOrder());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
