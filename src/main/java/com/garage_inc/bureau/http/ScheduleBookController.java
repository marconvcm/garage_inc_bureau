package com.garage_inc.bureau.http;

import com.garage_inc.bureau.interactor.Interactor;
import com.garage_inc.bureau.interactor.ViewScheduleBookInteractor;
import com.garage_inc.bureau.model.ScheduleBook;
import com.garage_inc.bureau.presenter.ViewScheduleBookPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/schedule-book")
public class ScheduleBookController {

    final Interactor<ViewScheduleBookInteractor.Input, ViewScheduleBookInteractor.Output> viewScheduleBookInteractor;

    final ViewScheduleBookPresenter viewScheduleBookPresenter;

    public ScheduleBookController(@Autowired Interactor<ViewScheduleBookInteractor.Input, ViewScheduleBookInteractor.Output> viewScheduleBookInteractor,
                                  @Autowired ViewScheduleBookPresenter viewScheduleBookPresenter) {
        this.viewScheduleBookInteractor = viewScheduleBookInteractor;
        this.viewScheduleBookPresenter = viewScheduleBookPresenter;
    }

    @GetMapping(path = "/view/{when}", produces = "application/json")
    public ResponseEntity<ScheduleBook> view(@PathVariable("when") ViewScheduleBookInteractor.ViewWhen viewWhen) {
        if(viewWhen == null) { return ResponseEntity.notFound().build(); }
        try {
            ViewScheduleBookInteractor.Output output = viewScheduleBookInteractor.run(new ViewScheduleBookInteractor.Input(viewWhen));
            return ResponseEntity.ok(viewScheduleBookPresenter.present(output));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
